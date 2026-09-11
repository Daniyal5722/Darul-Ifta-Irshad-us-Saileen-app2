package com.example.localization

import com.example.model.AppLanguage

object AppStrings {
    // App Names & Headings
    fun appTitle(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "دارالافتاء ارشاد السائلین"
        AppLanguage.ARABIC -> "دار الإفتاء إرشاد السائلين"
        AppLanguage.ENGLISH -> "Darul Ifta Irshad Us Saileen"
    }

    fun appSubTitle(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "کراچی - پاکستان"
        AppLanguage.ARABIC -> "كراتشي - باكستان"
        AppLanguage.ENGLISH -> "Karachi, Pakistan"
    }

    fun bismillah(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ"
        AppLanguage.ARABIC -> "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ"
        AppLanguage.ENGLISH -> "In the name of Allah, the Most Gracious, the Most Merciful"
    }

    // Navigation Items
    fun navHome(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "سرورق"
        AppLanguage.ARABIC -> "الرئيسية"
        AppLanguage.ENGLISH -> "Home"
    }

    fun navFatwas(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "فتاویٰ"
        AppLanguage.ARABIC -> "الفتاوى"
        AppLanguage.ENGLISH -> "Fatwas"
    }

    fun navDailyPosts(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "روزانہ پیغامات"
        AppLanguage.ARABIC -> "المنشورات اليومية"
        AppLanguage.ENGLISH -> "Daily Posts"
    }

    fun navScholars(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "علماء"
        AppLanguage.ARABIC -> "العلماء"
        AppLanguage.ENGLISH -> "Scholars"
    }

    fun navServices(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "خدمات"
        AppLanguage.ARABIC -> "الخدمات"
        AppLanguage.ENGLISH -> "Services"
    }

    fun navContact(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "رابطہ"
        AppLanguage.ARABIC -> "اتصل بنا"
        AppLanguage.ENGLISH -> "Contact"
    }

    fun navAskFatwa(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "فتویٰ طلب کریں"
        AppLanguage.ARABIC -> "طلب فتوى"
        AppLanguage.ENGLISH -> "Ask a Fatwa"
    }

    fun navMethodology(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "منہج و طریقہ کار"
        AppLanguage.ARABIC -> "المنهج والأسلوب"
        AppLanguage.ENGLISH -> "Methodology"
    }

    fun navDonate(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "عطیات و تعاون"
        AppLanguage.ARABIC -> "التبرعات"
        AppLanguage.ENGLISH -> "Donations"
    }

    // Language Selector
    fun changeLanguage(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "زبان تبدیل کریں"
        AppLanguage.ARABIC -> "تغيير اللغة"
        AppLanguage.ENGLISH -> "Change Language"
    }

    fun selectLanguage(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "اپنی پسندیدہ زبان منتخب کریں"
        AppLanguage.ARABIC -> "اختر لغتك المفضلة"
        AppLanguage.ENGLISH -> "Select Preferred Language"
    }

    // Home Screen Sections
    fun heroIntro(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "قرآن و سنت کی روشنی میں معتبر، تحقیقی اور ذمہ دار شرعی رہنمائی کا مرکز۔"
        AppLanguage.ARABIC -> "مركز معتمد للتوجيه الشرعي والبحث الفقهي المستنير على ضوء القرآن والسنة النبوية."
        AppLanguage.ENGLISH -> "A trusted center for sound Islamic guidance grounded in the Qur’an, Sunnah, and recognised fiqh principles."
    }

    fun quickActions(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "اہم سہولیات"
        AppLanguage.ARABIC -> "الإجراءات السريعة"
        AppLanguage.ENGLISH -> "Quick Actions"
    }

    fun viewAll(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "سب دیکھیں"
        AppLanguage.ARABIC -> "عرض الكل"
        AppLanguage.ENGLISH -> "View All"
    }

    fun readMore(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "مزید پڑھیں"
        AppLanguage.ARABIC -> "اقرأ المزيد"
        AppLanguage.ENGLISH -> "Read More"
    }

    fun featuredFatwas(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "نمایاں فتاویٰ"
        AppLanguage.ARABIC -> "فتاوى مختارة"
        AppLanguage.ENGLISH -> "Featured Fatwas"
    }

    fun recentPosts(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "تازہ ترین احادیث و پیغامات"
        AppLanguage.ARABIC -> "أحدث الأحاديث والمنشورات"
        AppLanguage.ENGLISH -> "Latest Guidance & Posts"
    }

    fun supervisorTitle(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "علمی سرپرست"
        AppLanguage.ARABIC -> "المشرف العلمي"
        AppLanguage.ENGLISH -> "Scholarly Supervision"
    }

    // Fatwas Screen
    fun searchFatwaHint(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "عنوان، مسئلہ یا فتویٰ نمبر سے تلاش کریں..."
        AppLanguage.ARABIC -> "ابحث بالعنوان أو المسألة أو رقم الفتوى..."
        AppLanguage.ENGLISH -> "Search fatwas by topic, title or number..."
    }

    fun allCategories(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "تمام زمرہ جات"
        AppLanguage.ARABIC -> "جميع الأقسام"
        AppLanguage.ENGLISH -> "All Categories"
    }

    fun categoryInheritance(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "وراثت و ترکہ"
        AppLanguage.ARABIC -> "الميراث والوصايا"
        AppLanguage.ENGLISH -> "Inheritance & Wills"
    }

    fun categoryMarriage(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "نکاح و طلاق"
        AppLanguage.ARABIC -> "الزواج والطلاق"
        AppLanguage.ENGLISH -> "Marriage & Divorce"
    }

    fun categoryTransactions(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "معاملات و تجارت"
        AppLanguage.ARABIC -> "المعاملات والتجارة"
        AppLanguage.ENGLISH -> "Transactions & Business"
    }

    fun categoryWorship(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "عبادات و طہارت"
        AppLanguage.ARABIC -> "العبادات والطهارة"
        AppLanguage.ENGLISH -> "Worship & Purity"
    }

    fun categoryBelief(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "عقائد و اخلاق"
        AppLanguage.ARABIC -> "العقيدة والسلوك"
        AppLanguage.ENGLISH -> "Belief & Conduct"
    }

    fun fatwaNumber(lang: AppLanguage, number: String) = when (lang) {
        AppLanguage.URDU -> "فتویٰ نمبر: $number"
        AppLanguage.ARABIC -> "رقم الفتوى: $number"
        AppLanguage.ENGLISH -> "Fatwa No: $number"
    }

    fun questionLabel(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "سوال:"
        AppLanguage.ARABIC -> "السؤال:"
        AppLanguage.ENGLISH -> "Question:"
    }

    fun answerLabel(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "الجواب وباللہ التوفیق:"
        AppLanguage.ARABIC -> "الجواب وبالله التوفيق:"
        AppLanguage.ENGLISH -> "Answer:"
    }

    fun referenceLabel(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "حوالہ و مآخذ:"
        AppLanguage.ARABIC -> "المراجع والمصادر:"
        AppLanguage.ENGLISH -> "Reference:"
    }

    fun viewPdfDocument(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "اصل پی ڈی ایف فتویٰ دیکھیں"
        AppLanguage.ARABIC -> "عرض مستند الفتوى الأصلي (PDF)"
        AppLanguage.ENGLISH -> "View Original Fatwa PDF"
    }

    fun shareFatwa(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "فتویٰ شیئر کریں"
        AppLanguage.ARABIC -> "مشاركة الفتوى"
        AppLanguage.ENGLISH -> "Share Fatwa"
    }

    fun copyText(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "کاپی کریں"
        AppLanguage.ARABIC -> "نسخ"
        AppLanguage.ENGLISH -> "Copy Text"
    }

    fun textCopied(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "متن کاپی ہو گیا ہے"
        AppLanguage.ARABIC -> "تم نسخ النص"
        AppLanguage.ENGLISH -> "Text copied to clipboard"
    }

    fun textSize(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "حجمِ خط"
        AppLanguage.ARABIC -> "حجم الخط"
        AppLanguage.ENGLISH -> "Text Size"
    }

    // Daily Posts
    fun dailyGuidanceDesc(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "یاددہانیاں، روزانہ احادیث اور مفید اسلامی علم باوقار انداز میں۔"
        AppLanguage.ARABIC -> "تذكيرات وأحاديث نبوية شريفة يومية وفائدة إسلامية للمطالعة."
        AppLanguage.ENGLISH -> "Thoughtful reminders, daily Hadith, and beneficial Islamic knowledge."
    }

    fun openWhatsAppChannel(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "واٹس ایپ چینل میں شامل ہوں"
        AppLanguage.ARABIC -> "انضم إلى قناة الواتساب"
        AppLanguage.ENGLISH -> "Join WhatsApp Channel"
    }

    // Contact & Details
    fun getInTouch(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "دارالافتاء سے رابطہ کریں"
        AppLanguage.ARABIC -> "تواصل مع دار الإفتاء"
        AppLanguage.ENGLISH -> "Get in Touch"
    }

    fun contactDesc(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "شرعی رہنمائی، علمی استفسارات یا دیگر معلومات کے لیے ہم سے رابطہ فرمائیں۔"
        AppLanguage.ARABIC -> "يسعدنا تواصلكم للاستفسارات الشرعية والتوجيه الفقهي."
        AppLanguage.ENGLISH -> "Reach out to us for Shariah consultations, religious guidance, or questions."
    }

    fun callUs(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "فون پر رابطہ"
        AppLanguage.ARABIC -> "اتصال هاتفي"
        AppLanguage.ENGLISH -> "Call Us"
    }

    fun whatsAppUs(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "واٹس ایپ میسج"
        AppLanguage.ARABIC -> "واتساب مباشر"
        AppLanguage.ENGLISH -> "WhatsApp Us"
    }

    fun sendEmail(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "ای میل ارسال کریں"
        AppLanguage.ARABIC -> "البريد الإلكتروني"
        AppLanguage.ENGLISH -> "Email Us"
    }

    fun openMaps(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "نقشہ میں مقام دیکھیں"
        AppLanguage.ARABIC -> "عرض الموقع في الخريطة"
        AppLanguage.ENGLISH -> "Open in Maps"
    }

    fun watchYouTube(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "یوٹیوب بیانات"
        AppLanguage.ARABIC -> "قناة اليوتيوب"
        AppLanguage.ENGLISH -> "YouTube Channel"
    }

    fun addressTitle(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "پتہ و مقام:"
        AppLanguage.ARABIC -> "العنوان والمقر:"
        AppLanguage.ENGLISH -> "Address:"
    }

    // Ask Fatwa Form
    fun askFatwaTitle(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "شرعی سوال ارسال کریں"
        AppLanguage.ARABIC -> "إرسال سؤال شرعي"
        AppLanguage.ENGLISH -> "Submit a Shariah Question"
    }

    fun askFatwaDesc(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "اپنا سوال تفصیلاً تحریر کریں تاکہ دارالافتاء کے مفتیانِ کرام تسلی بخش رہنمائی فرما سکیں۔"
        AppLanguage.ARABIC -> "اكتب سؤالك بالتفصيل ليتمكن المشايخ من الإجابة عليه بدقة وفق الضوابط الشرعية."
        AppLanguage.ENGLISH -> "Write your question with relevant context so scholars can provide accurate guidance."
    }

    fun labelFullName(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "مکمل نام *"
        AppLanguage.ARABIC -> "الاسم الكامل *"
        AppLanguage.ENGLISH -> "Full Name *"
    }

    fun formInstructions(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "براہِ کرم اپنا سوال اور خاندانی/مالی پس منظر واضح انداز میں تحریر فرمائیں تاکہ شرعی رہنمائی میں سہولت ہو۔"
        AppLanguage.ARABIC -> "يرجى بيان المسألة والظروف المحيطة بها بوضوح لتيسير الفتوى الشرعية."
        AppLanguage.ENGLISH -> "Please explain your question and relevant background clearly so our scholars can provide tailored Shariah guidance."
    }

    fun fieldName(lang: AppLanguage) = labelFullName(lang)
    fun fieldPhone(lang: AppLanguage) = labelPhone(lang)
    fun fieldEmail(lang: AppLanguage) = labelEmail(lang)
    fun fieldCategory(lang: AppLanguage) = labelCategory(lang)
    fun fieldSubject(lang: AppLanguage) = labelQuestionTitle(lang)
    fun fieldQuestion(lang: AppLanguage) = labelQuestionDetails(lang)

    fun submitViaWhatsApp(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "واٹس ایپ کے ذریعے ارسال کریں"
        AppLanguage.ARABIC -> "إرسال عبر واتساب"
        AppLanguage.ENGLISH -> "Submit via WhatsApp"
    }

    fun submitViaEmail(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "ای میل کے ذریعے ارسال کریں"
        AppLanguage.ARABIC -> "إرسال عبر البريد الإلكتروني"
        AppLanguage.ENGLISH -> "Submit via Email"
    }

    fun labelPhone(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "فون یا واٹس ایپ نمبر *"
        AppLanguage.ARABIC -> "رقم الهاتف / الواتساب *"
        AppLanguage.ENGLISH -> "Phone / WhatsApp *"
    }

    fun labelEmail(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "ای میل پتہ (اختیاری)"
        AppLanguage.ARABIC -> "البريد الإلكتروني (اختياري)"
        AppLanguage.ENGLISH -> "Email Address (Optional)"
    }

    fun labelCategory(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "سوال کا زمرہ"
        AppLanguage.ARABIC -> "مجال السؤال"
        AppLanguage.ENGLISH -> "Category"
    }

    fun labelQuestionTitle(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "سوال کا مختصر عنوان *"
        AppLanguage.ARABIC -> "عنوان السؤال الموجز *"
        AppLanguage.ENGLISH -> "Question Subject / Title *"
    }

    fun labelQuestionDetails(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "سوال کی مکمل تفصیل *"
        AppLanguage.ARABIC -> "تفاصيل المسألة بدقة *"
        AppLanguage.ENGLISH -> "Detailed Question *"
    }

    fun btnSubmitQuestion(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "سوال ارسال کریں (ای میل / واٹس ایپ)"
        AppLanguage.ARABIC -> "إرسال السؤال"
        AppLanguage.ENGLISH -> "Submit Question"
    }

    fun fillRequiredFields(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "براہِ کرم تمام لازمی خانے پُر کریں۔"
        AppLanguage.ARABIC -> "يرجى ملء جميع الحقول المطلوبة."
        AppLanguage.ENGLISH -> "Please fill all required fields."
    }

    // Donations
    fun donationTitle(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "دینی و علمی خدمات میں تعاون"
        AppLanguage.ARABIC -> "دعم الخدمات الدينية والعلمية"
        AppLanguage.ENGLISH -> "Support Beneficial Religious Work"
    }

    fun donationDesc(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "دارالافتاء کی اشاعتی، تعلیمی اور تحقیقی خدمات کے تسلسل کے لیے اپنے عطیات و تعاون پیش کریں۔"
        AppLanguage.ARABIC -> "ساهم في استمرار خدمات دار الإفتاء التعليمية والبحثية ونشر الفتاوى."
        AppLanguage.ENGLISH -> "Support the ongoing publication, education, and Shariah research services of Darul Ifta."
    }

    fun accountTitle(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "بینک اکاؤنٹ کی تفصیلات"
        AppLanguage.ARABIC -> "بيانات الحساب المصرفي"
        AppLanguage.ENGLISH -> "Bank Account Details"
    }

    fun accountHolder(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "اکاؤنٹ ہولڈر:"
        AppLanguage.ARABIC -> "صاحب الحساب:"
        AppLanguage.ENGLISH -> "Account Title:"
    }

    fun bankName(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "بینک کا نام:"
        AppLanguage.ARABIC -> "اسم المصرف:"
        AppLanguage.ENGLISH -> "Bank Name:"
    }

    fun ibanNumber(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "آئی بین (IBAN):"
        AppLanguage.ARABIC -> "رقم الآيبان (IBAN):"
        AppLanguage.ENGLISH -> "IBAN:"
    }

    // Offline & States
    fun noInternetTitle(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "انٹرنیٹ کنکشن دستیاب نہیں"
        AppLanguage.ARABIC -> "لا يوجد اتصال بالإنترنت"
        AppLanguage.ENGLISH -> "No Internet Connection"
    }

    fun noInternetDesc(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "آپ آف لائن ہیں۔ محفوظ شدہ تمام فتاویٰ اور معلومات بغیر انٹرنیٹ کے دستیاب ہیں۔"
        AppLanguage.ARABIC -> "أنت في وضع عدم الاتصال. جميع الفتاوى المحفوظة والمعلومات متاحة محلياً."
        AppLanguage.ENGLISH -> "You are currently offline. All 200+ archived fatwas and scholar details remain available offline."
    }

    fun retry(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "دوبارہ کوشش کریں"
        AppLanguage.ARABIC -> "إعادة المحاولة"
        AppLanguage.ENGLISH -> "Retry"
    }

    fun emptySearchResults(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "آپ کی تلاش کے مطابق کوئی فتویٰ نہیں ملا۔"
        AppLanguage.ARABIC -> "لم يتم العثور على فتاوى مطابقة للبحث."
        AppLanguage.ENGLISH -> "No fatwas matched your search criteria."
    }

    fun clearFilters(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "فلٹر صاف کریں"
        AppLanguage.ARABIC -> "مسح عوامل التصفية"
        AppLanguage.ENGLISH -> "Clear Filters"
    }

    fun pdfLoading(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "پی ڈی ایف دستاویز لوڈ ہو رہی ہے..."
        AppLanguage.ARABIC -> "جارٍ تحميل مستند PDF..."
        AppLanguage.ENGLISH -> "Loading PDF document..."
    }

    fun pdfLoadError(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "پی ڈی ایف لوڈ کرنے میں دقت ہوئی۔ براہ کرم انٹرنیٹ چیک کریں یا دوبارہ کوشش کریں۔"
        AppLanguage.ARABIC -> "تعذر فتح المستند. يرجى التحقق من الاتصال والمحاولة مجدداً."
        AppLanguage.ENGLISH -> "Could not load PDF. Please check connection and retry."
    }

    fun downloadPdf(lang: AppLanguage) = when (lang) {
        AppLanguage.URDU -> "پی ڈی ایف ڈاؤن لوڈ / کھولیں"
        AppLanguage.ARABIC -> "تحميل / فتح المستند"
        AppLanguage.ENGLISH -> "Download / Open PDF"
    }
}
