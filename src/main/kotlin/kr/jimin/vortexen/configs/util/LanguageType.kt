package kr.jimin.vortexen.configs.util

enum class LanguageType(val lang: String) {
    KOREAN("ko"),
    ENGLISH("en");

    companion object {
        fun checkLang(lang: String): LanguageType? = entries.find { it.lang == lang }
    }
}