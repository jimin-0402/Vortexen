package kr.jimin.vortexen.configs.util

import dev.dejvokep.boostedyaml.YamlDocument
import kr.jimin.vortexen.VortexenPlugin
import kr.jimin.vortexen.configs.ConfigsManager

enum class Config(private val path: String) {

    LANGUAGE("Plugin.language"),

    ITEM_MATERIAL("Item.material"),
    ITEM_DISPLAY_NAME("Item.display-name"),
    ITEM_LORE("Item.lore"),
    ITEM_MODEL("Item.model-data");

    fun getConfigValue(): Any {
        // 값의 타입에 따라 적절한 메서드 호출
        return when (val value = VortexenPlugin.instance.getConfigsManager().getConfigValue(path)) {
            is String -> toStringValue(value)
            is Int -> toIntValue(value)
            is List<*> -> toListValue(value)
            is Map<*, *> -> toMapValue(value)
            else -> getDefaultValue()
        }
    }

    private fun toStringValue(value: String): String {
        // String 처리 로직
        return value
    }

    private fun toIntValue(value: Int): Int {
        // Int 처리 로직
        return value
    }

    private fun toListValue(value: List<*>): List<*> {
        // List 처리 로직
        return value
    }

    private fun toMapValue(value: Map<*, *>): Map<*, *> {
        // Map 처리 로직
        return value
    }

    private fun getDefaultValue(): List<String> {
        // 기본값 처리: null일 경우 빈 리스트 반환
        return emptyList()
    }


}