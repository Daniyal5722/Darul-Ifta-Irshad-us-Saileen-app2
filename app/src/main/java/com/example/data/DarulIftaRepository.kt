package com.example.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.model.AppLanguage
import com.example.model.DailyPost
import com.example.model.Fatwa
import com.example.model.MethodologyItem
import com.example.model.MultilingualText
import com.example.model.Scholar
import com.example.model.ServiceItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class DarulIftaRepository(private val context: Context) {

    private val _fatwas = MutableStateFlow<List<Fatwa>>(emptyList())
    val fatwas: StateFlow<List<Fatwa>> = _fatwas.asStateFlow()

    private val _scholars = MutableStateFlow<List<Scholar>>(emptyList())
    val scholars: StateFlow<List<Scholar>> = _scholars.asStateFlow()

    private val _dailyPosts = MutableStateFlow<List<DailyPost>>(emptyList())
    val dailyPosts: StateFlow<List<DailyPost>> = _dailyPosts.asStateFlow()

    private val _isOnline = MutableStateFlow(false)
    val isOnline: StateFlow<Boolean> = _isOnline.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    val services: List<ServiceItem> = listOf(
        ServiceItem(
            id = "fatwa_service",
            title = MultilingualText(
                en = "Fatwa Service",
                ur = "فتاویٰ و استفسارات",
                ar = "خدمة الفتاوى والاستفتاء"
            ),
            description = MultilingualText(
                en = "Written fatwas according to Hanafi jurisprudence issued under rigorous supervision of reputable scholars.",
                ur = "مستند فقہ حنفی کے مطابق تحریری فتاویٰ کا باقاعدہ اجراء اور شرعی مسائل کا مستند حل۔",
                ar = "إصدار الفتاوى المكتوبة الموثوقة وفق الفقه الحنفي تحت إشراف كبار العلماء والمفتين."
            ),
            iconName = "fatwa"
        ),
        ServiceItem(
            id = "islamic_education",
            title = MultilingualText(
                en = "Islamic Education",
                ur = "دینی تعلیم و تربیت",
                ar = "التعليم الشرعي"
            ),
            description = MultilingualText(
                en = "Courses, educational reminders, lectures, and beneficial Islamic literature for modern life.",
                ur = "اسلامی کورسز، دینی دروس، اور جدید مسائل میں رہنمائی کے لیے مستند تحقیقی مواد۔",
                ar = "دورات تعليمية ومحاضرات علمية نافعة ومواد فقهية لمواكبة قضايا العصر."
            ),
            iconName = "education"
        ),
        ServiceItem(
            id = "publications",
            title = MultilingualText(
                en = "Publications & Research",
                ur = "اشاعت و تحقیقی مقالات",
                ar = "البحوث والمنشورات"
            ),
            description = MultilingualText(
                en = "Publication of classical treatises, contemporary research papers, and regular Hadith reminders.",
                ur = "کتب، رسائل، تحقیقی مقالات اور روزانہ کی بنیاد پر احادیثِ نبویہ کی اشاعت۔",
                ar = "نشر الكتب والرسائل الفقهية والبحوث العلمية المعاصرة والأحاديث اليومية."
            ),
            iconName = "publications"
        ),
        ServiceItem(
            id = "scholarly_consultation",
            title = MultilingualText(
                en = "Scholarly Consultation",
                ur = "علماء سے براہِ راست مشورہ",
                ar = "الاستشارات العلمية"
            ),
            description = MultilingualText(
                en = "Direct confidential consultations with senior Muftis for personal and community matters.",
                ur = "ذاتی، ازدواجی اور اجتماعی معاملات میں معتمد مفتیانِ کرام سے بالمشافہ و فون پر مشورہ۔",
                ar = "استشارات خاصة مع كبار المفتين في المسائل الأسرية والفردية والاجتماعية."
            ),
            iconName = "consultation"
        ),
        ServiceItem(
            id = "inheritance_disputes",
            title = MultilingualText(
                en = "Inheritance & Disputes",
                ur = "تقسیمِ وراثت و تنازعات",
                ar = "حساب التركات وفض المنازعات"
            ),
            description = MultilingualText(
                en = "Shariah compliant inheritance distribution, estate shares calculation, and arbitration.",
                ur = "قرآن و سنت کی روشنی میں ترکہ کی شرعی تقسیم، حصے داریوں کا تعین اور باہمی تنازعات کا تصفیہ۔",
                ar = "قسمة التركات الشرعية، حساب الأنصبة، وفض النزاعات المالية والعائلية وفق الشريعة."
            ),
            iconName = "inheritance"
        )
    )

    val methodology: List<MethodologyItem> = listOf(
        MethodologyItem(
            index = "01",
            title = MultilingualText(
                en = "Qur’an and Sunnah Foundation",
                ur = "قرآن و سنت کی اساس",
                ar = "الاستناد إلى القرآن والسنة"
            ),
            detail = MultilingualText(
                en = "Every ruling is firmly rooted in the Holy Qur’an, authentic Sunnah, and classical consensus of Ahl us-Sunnah wal-Jama’ah according to Hanafi jurisprudence.",
                ur = "تمام فتاویٰ کی بنیاد قرآن کریم، سنتِ نبویہ ﷺ، اجماعِ امت اور فقہ حنفی کے معتبر متون پر رکھی جاتی ہے۔",
                ar = "تستند جميع الفتاوى والأحكام إلى القرآن الكريم والسنة النبوية الصحيحة وإجماع الأمة وفق الفقه الحنفي الأصيل."
            )
        ),
        MethodologyItem(
            index = "02",
            title = MultilingualText(
                en = "Rigorous Scholarly Verification",
                ur = "تحقیقی تدقیق اور علمی سرپرستی",
                ar = "التحقيق العلمي الدقيق"
            ),
            detail = MultilingualText(
                en = "Drafted with careful reference verification (such as Radd al-Muhtar, Al-Fatawa al-Hindiyyah) and reviewed by senior muftis before issuance.",
                ur = "معتبر فتاویٰ کے بنیادی مآخذ (مثلاً رد المحتار، فتاویٰ ہندیہ وغیرہ) سے تقابل اور جید مفتیانِ کرام کی تصدیق کے بعد جواب جاری کیا جاتا ہے۔",
                ar = "مراجعة المصادر الفقهية المعتمدة وتدقيق الفتاوى من قبل كبار المشايخ والمفتين قبل إصدارها رسمياً."
            )
        ),
        MethodologyItem(
            index = "03",
            title = MultilingualText(
                en = "Confidentiality & Care",
                ur = "مکمل رازداری اور ہمدردانہ رہنمائی",
                ar = "السرية التامة ورعاية المستفتين"
            ),
            detail = MultilingualText(
                en = "Inquirers' personal circumstances and identities are preserved with strict Islamic confidentiality and pastoral empathy.",
                ur = "سائلین کے ذاتی احوال اور معلومات کی مکمل رازداری ملحوظ رکھی جاتی ہے اور اسلامی شفقت کے ساتھ رہنمائی کی جاتی ہے۔",
                ar = "الحفاظ على سرية بيانات المستفتين وظروفهم الشخصية مع التعامل بأمانة شرعية ورعاية فائقة."
            )
        )
    )

    init {
        checkConnectivity()
    }

    fun checkConnectivity(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        val activeNetwork = cm?.activeNetwork
        val caps = cm?.getNetworkCapabilities(activeNetwork)
        val online = caps != null && (
            caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
            caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
            caps.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        )
        _isOnline.value = online
        return online
    }

    suspend fun loadInitialData() {
        withContext(Dispatchers.IO) {
            _isLoading.value = true
            checkConnectivity()

            // 1. Load Fatwas from local assets
            val localFatwas = loadFatwasFromAssets()
            _fatwas.value = localFatwas

            // 2. Load Scholars from local assets
            val localScholars = loadScholarsFromAssets()
            _scholars.value = localScholars

            // 3. Load Daily Posts from local assets
            val localPosts = loadDailyPostsFromAssets()
            _dailyPosts.value = localPosts

            _isLoading.value = false

            // If online, optionally sync newest posts & scholars
            if (_isOnline.value) {
                try {
                    syncOnlineDailyPosts()
                    syncOnlineScholars()
                } catch (_: Exception) {
                    // Fallback to local data seamlessly
                }
            }
        }
    }

    private fun loadFatwasFromAssets(): List<Fatwa> {
        val result = mutableListOf<Fatwa>()
        try {
            val jsonString = context.assets.open("fatwas.json").bufferedReader().use { it.readText() }
            val array = JSONArray(jsonString)
            for (i in 0 until array.length()) {
                val obj = array.getJSONObject(i)
                val topicsList = mutableListOf<String>()
                val topicsArr = obj.optJSONArray("topics")
                if (topicsArr != null) {
                    for (t in 0 until topicsArr.length()) {
                        topicsList.add(topicsArr.getString(t))
                    }
                }
                result.add(
                    Fatwa(
                        slug = obj.optString("slug"),
                        number = obj.optString("number"),
                        category = obj.optString("category"),
                        date = obj.optString("date"),
                        scholar = obj.optString("scholar"),
                        titleEn = obj.optString("titleEn"),
                        titleUr = obj.optString("titleUr"),
                        questionEn = obj.optString("questionEn"),
                        questionUr = obj.optString("questionUr"),
                        answerEn = obj.optString("answerEn"),
                        answerUr = obj.optString("answerUr"),
                        reference = obj.optString("reference"),
                        topics = topicsList,
                        pdfUrl = obj.optString("pdfUrl").takeIf { it.isNotBlank() }
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    private fun loadScholarsFromAssets(): List<Scholar> {
        val result = mutableListOf<Scholar>()
        try {
            val jsonString = context.assets.open("scholars.json").bufferedReader().use { it.readText() }
            val array = JSONArray(jsonString)
            for (i in 0 until array.length()) {
                val obj = array.getJSONObject(i)
                result.add(parseScholarJson(obj))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    private fun parseScholarJson(obj: JSONObject): Scholar {
        val quals = parseMultilingualList(obj.optJSONArray("islamicQualifications"))
        val expertise = parseMultilingualList(obj.optJSONArray("areasOfExpertise"))
        val responsibilities = parseMultilingualList(obj.optJSONArray("responsibilities"))
        val servicesList = parseMultilingualList(obj.optJSONArray("services"))

        return Scholar(
            id = obj.optLong("id", 1L),
            slug = obj.optString("slug", "hazrat-maulana-mufti-abdul-mannan-sahib"),
            nameEn = obj.optString("nameEn"),
            nameUr = obj.optString("nameUr"),
            titleEn = obj.optString("titleEn"),
            titleUr = obj.optString("titleUr"),
            organizationEn = obj.optString("organizationEn"),
            organizationUr = obj.optString("organizationUr"),
            shortIntroductionEn = obj.optString("shortIntroductionEn"),
            shortIntroductionUr = obj.optString("shortIntroductionUr"),
            introductionEn = obj.optString("introductionEn"),
            introductionUr = obj.optString("introductionUr"),
            educationalBackgroundEn = obj.optString("educationalBackgroundEn"),
            educationalBackgroundUr = obj.optString("educationalBackgroundUr"),
            qualifications = quals,
            areasOfExpertise = expertise,
            responsibilities = responsibilities,
            services = servicesList,
            additionalInfoEn = obj.optString("additionalInformationEn"),
            additionalInfoUr = obj.optString("additionalInformationUr")
        )
    }

    private fun parseMultilingualList(arr: JSONArray?): List<MultilingualText> {
        if (arr == null) return emptyList()
        val list = mutableListOf<MultilingualText>()
        for (i in 0 until arr.length()) {
            val item = arr.opt(i)
            if (item is JSONObject) {
                list.add(
                    MultilingualText(
                        en = item.optString("en"),
                        ur = item.optString("ur"),
                        ar = item.optString("ar")
                    )
                )
            } else if (item is String) {
                list.add(MultilingualText(en = item, ur = item, ar = item))
            }
        }
        return list
    }

    private fun loadDailyPostsFromAssets(): List<DailyPost> {
        val result = mutableListOf<DailyPost>()
        try {
            val jsonString = context.assets.open("daily_posts.json").bufferedReader().use { it.readText() }
            val root = JSONObject(jsonString)
            val items = root.optJSONArray("items") ?: JSONArray()
            for (i in 0 until items.length()) {
                val obj = items.getJSONObject(i)
                result.add(parseDailyPostJson(obj))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    private fun parseDailyPostJson(obj: JSONObject): DailyPost {
        return DailyPost(
            id = obj.optLong("id"),
            slug = obj.optString("slug"),
            titleEn = obj.optString("titleEn").takeIf { it.isNotBlank() },
            titleUr = obj.optString("titleUr").takeIf { it.isNotBlank() },
            titleAr = obj.optString("titleAr").takeIf { it.isNotBlank() },
            contentEn = obj.optString("contentEn").takeIf { it.isNotBlank() },
            contentUr = obj.optString("contentUr").takeIf { it.isNotBlank() },
            contentAr = obj.optString("contentAr").takeIf { it.isNotBlank() },
            authorName = obj.optString("authorName", "WhatsApp Channel"),
            publishedAt = obj.optString("sourcePublishedAt").ifBlank { obj.optString("publishedAt") },
            featuredImageUrl = obj.optString("featuredImageUrl").takeIf { it.isNotBlank() },
            originalPostUrl = obj.optString("originalPostUrl", "https://whatsapp.com/channel/0029VaCc5dK7T8bclVGHu20Q")
        )
    }

    private fun syncOnlineDailyPosts() {
        try {
            val url = URL("https://darulifta-bkfbzf6u.manus.space/api/daily-posts")
            val connection = url.openConnection() as HttpURLConnection
            connection.connectTimeout = 5000
            connection.readTimeout = 5000
            connection.requestMethod = "GET"
            if (connection.responseCode == 200) {
                val response = connection.inputStream.bufferedReader().use { it.readText() }
                val root = JSONObject(response)
                val items = root.optJSONArray("items") ?: JSONArray()
                if (items.length() > 0) {
                    val list = mutableListOf<DailyPost>()
                    for (i in 0 until items.length()) {
                        list.add(parseDailyPostJson(items.getJSONObject(i)))
                    }
                    _dailyPosts.value = list
                }
            }
        } catch (_: Exception) {}
    }

    private fun syncOnlineScholars() {
        try {
            val url = URL("https://darulifta-bkfbzf6u.manus.space/api/scholars")
            val connection = url.openConnection() as HttpURLConnection
            connection.connectTimeout = 5000
            connection.readTimeout = 5000
            connection.requestMethod = "GET"
            if (connection.responseCode == 200) {
                val response = connection.inputStream.bufferedReader().use { it.readText() }
                val items = JSONArray(response)
                if (items.length() > 0) {
                    val list = mutableListOf<Scholar>()
                    for (i in 0 until items.length()) {
                        list.add(parseScholarJson(items.getJSONObject(i)))
                    }
                    _scholars.value = list
                }
            }
        } catch (_: Exception) {}
    }

    fun getFatwaBySlug(slug: String): Fatwa? {
        return _fatwas.value.find { it.slug == slug }
    }

    fun getScholarBySlug(slug: String): Scholar? {
        return _scholars.value.find { it.slug == slug } ?: _scholars.value.firstOrNull()
    }

    fun getDailyPostBySlug(slug: String): DailyPost? {
        return _dailyPosts.value.find { it.slug == slug }
    }
}
