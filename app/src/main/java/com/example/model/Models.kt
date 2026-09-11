package com.example.model

enum class AppLanguage(val code: String, val displayName: String, val nativeName: String, val isRtl: Boolean) {
    URDU("ur", "Urdu", "اردو", true),
    ENGLISH("en", "English", "English", false),
    ARABIC("ar", "Arabic", "العربية", true);

    companion object {
        fun fromCode(code: String): AppLanguage =
            entries.find { it.code.equals(code, ignoreCase = true) } ?: URDU
    }
}

data class Fatwa(
    val slug: String,
    val number: String,
    val category: String,
    val date: String,
    val scholar: String,
    val titleEn: String,
    val titleUr: String,
    val questionEn: String,
    val questionUr: String,
    val answerEn: String,
    val answerUr: String,
    val reference: String,
    val topics: List<String> = emptyList(),
    val pdfUrl: String? = null
) {
    fun localizedTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.URDU -> titleUr.ifBlank { titleEn }
        AppLanguage.ARABIC -> titleUr.ifBlank { titleEn }
        AppLanguage.ENGLISH -> titleEn.ifBlank { titleUr }
    }

    fun localizedQuestion(lang: AppLanguage): String = when (lang) {
        AppLanguage.URDU -> questionUr.ifBlank { questionEn }
        AppLanguage.ARABIC -> questionUr.ifBlank { questionEn }
        AppLanguage.ENGLISH -> questionEn.ifBlank { questionUr }
    }

    fun localizedAnswer(lang: AppLanguage): String = when (lang) {
        AppLanguage.URDU -> answerUr.ifBlank { answerEn }
        AppLanguage.ARABIC -> answerUr.ifBlank { answerEn }
        AppLanguage.ENGLISH -> answerEn.ifBlank { answerUr }
    }
}

data class MultilingualText(
    val en: String = "",
    val ur: String = "",
    val ar: String = ""
) {
    fun get(lang: AppLanguage): String = when (lang) {
        AppLanguage.URDU -> ur.ifBlank { en }
        AppLanguage.ARABIC -> ar.ifBlank { ur.ifBlank { en } }
        AppLanguage.ENGLISH -> en.ifBlank { ur }
    }
}

data class Scholar(
    val id: Long,
    val slug: String,
    val nameEn: String,
    val nameUr: String,
    val nameAr: String = "",
    val titleEn: String,
    val titleUr: String,
    val titleAr: String = "",
    val organizationEn: String,
    val organizationUr: String,
    val organizationAr: String = "",
    val shortIntroductionEn: String,
    val shortIntroductionUr: String,
    val shortIntroductionAr: String = "",
    val introductionEn: String,
    val introductionUr: String,
    val introductionAr: String = "",
    val educationalBackgroundEn: String,
    val educationalBackgroundUr: String,
    val educationalBackgroundAr: String = "",
    val qualifications: List<MultilingualText> = emptyList(),
    val areasOfExpertise: List<MultilingualText> = emptyList(),
    val responsibilities: List<MultilingualText> = emptyList(),
    val services: List<MultilingualText> = emptyList(),
    val additionalInfoEn: String = "",
    val additionalInfoUr: String = "",
    val additionalInfoAr: String = ""
) {
    fun localizedName(lang: AppLanguage): String = when (lang) {
        AppLanguage.URDU -> nameUr.ifBlank { nameEn }
        AppLanguage.ARABIC -> nameAr.ifBlank { nameUr.ifBlank { nameEn } }
        AppLanguage.ENGLISH -> nameEn.ifBlank { nameUr }
    }

    fun localizedTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.URDU -> titleUr.ifBlank { titleEn }
        AppLanguage.ARABIC -> titleAr.ifBlank { titleUr.ifBlank { titleEn } }
        AppLanguage.ENGLISH -> titleEn.ifBlank { titleUr }
    }

    fun localizedIntro(lang: AppLanguage): String = when (lang) {
        AppLanguage.URDU -> introductionUr.ifBlank { introductionEn }
        AppLanguage.ARABIC -> introductionAr.ifBlank { introductionUr.ifBlank { introductionEn } }
        AppLanguage.ENGLISH -> introductionEn.ifBlank { introductionUr }
    }

    fun localizedShortIntro(lang: AppLanguage): String = when (lang) {
        AppLanguage.URDU -> shortIntroductionUr.ifBlank { shortIntroductionEn }
        AppLanguage.ARABIC -> shortIntroductionAr.ifBlank { shortIntroductionUr.ifBlank { shortIntroductionEn } }
        AppLanguage.ENGLISH -> shortIntroductionEn.ifBlank { shortIntroductionUr }
    }

    fun localizedEdu(lang: AppLanguage): String = when (lang) {
        AppLanguage.URDU -> educationalBackgroundUr.ifBlank { educationalBackgroundEn }
        AppLanguage.ARABIC -> educationalBackgroundAr.ifBlank { educationalBackgroundUr.ifBlank { educationalBackgroundEn } }
        AppLanguage.ENGLISH -> educationalBackgroundEn.ifBlank { educationalBackgroundUr }
    }
}

data class DailyPost(
    val id: Long,
    val slug: String,
    val titleEn: String? = null,
    val titleUr: String? = null,
    val titleAr: String? = null,
    val contentEn: String? = null,
    val contentUr: String? = null,
    val contentAr: String? = null,
    val authorName: String = "WhatsApp Channel",
    val publishedAt: String = "",
    val featuredImageUrl: String? = null,
    val originalPostUrl: String = "https://whatsapp.com/channel/0029VaCc5dK7T8bclVGHu20Q"
) {
    fun localizedTitle(lang: AppLanguage): String {
        val t = when (lang) {
            AppLanguage.URDU -> titleUr ?: titleEn ?: titleAr
            AppLanguage.ARABIC -> titleAr ?: titleUr ?: titleEn
            AppLanguage.ENGLISH -> titleEn ?: titleUr ?: titleAr
        }
        return t?.takeIf { it.isNotBlank() } ?: (if (lang == AppLanguage.ENGLISH) "Daily Hadith" else if (lang == AppLanguage.ARABIC) "حديث اليوم" else "آج کی حدیث")
    }

    fun localizedContent(lang: AppLanguage): String {
        val c = when (lang) {
            AppLanguage.URDU -> contentUr ?: contentEn ?: contentAr
            AppLanguage.ARABIC -> contentAr ?: contentUr ?: contentEn
            AppLanguage.ENGLISH -> contentEn ?: contentUr ?: contentAr
        }
        return c?.takeIf { it.isNotBlank() && it != "." }
            ?: (if (lang == AppLanguage.ENGLISH) "Authentic daily guidance and Prophetic teachings from Darul Ifta Irshad Us Saileen."
            else if (lang == AppLanguage.ARABIC) "توجيهات يومية نبوية من دار الإفتاء إرشاد السائلين كراتشي."
            else "دارالافتاء ارشاد السائلین کی جانب سے روزانہ مستند شرعی رہنمائی اور حدیث نبوی۔")
    }
}

data class ServiceItem(
    val id: String,
    val title: MultilingualText,
    val description: MultilingualText,
    val iconName: String
)

data class MethodologyItem(
    val index: String,
    val title: MultilingualText,
    val detail: MultilingualText
)
