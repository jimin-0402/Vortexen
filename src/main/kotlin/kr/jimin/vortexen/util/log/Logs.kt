package kr.jimin.vortexen.util.log

import kr.jimin.vortexen.VortexenPlugin
import kr.jimin.vortexen.util.MessagesUtils
import net.kyori.adventure.text.Component

object Logs {

    fun logInfo(message: String) {
        if (message.isNotEmpty()) logInfo(message, false)
    }

    private fun logInfo(message: String, newline: Boolean) {
        logMessage(message, "<prefix><#529ced>", newline)
    }

    fun logSuccess(message: String) {
        if (message.isNotEmpty()) logSuccess(message, false)
    }

    private fun logSuccess(message: String, newline: Boolean) {
        logMessage(message, "<prefix><#55ffa4>", newline)
    }

    fun logError(message: String) {
        if (message.isNotEmpty()) logError(message, false)
    }

    private fun logError(message: String, newline: Boolean) {
        logMessage(message, "<prefix><#e73f34>", newline)
    }

    fun logWarning(message: String) {
        if (message.isNotEmpty()) logWarning(message, false)
    }

    private fun logWarning(message: String, newline: Boolean) {
        logMessage(message, "<prefix><#f9f178>", newline)
    }

    private fun logMessage(message: String, colorPrefix: String, newline: Boolean) {
        val formattedMessage = MessagesUtils.MINI_MESSAGE.deserialize("$colorPrefix$message</#>")
        VortexenPlugin.instance.getAudience().console().sendMessage(
            if (newline) formattedMessage.append(Component.newline()) else formattedMessage
        )
    }

    fun newline() {
        return VortexenPlugin.instance.getAudience().console().sendMessage(Component.empty())
    }

    // Debug 메서드들 (주석 처리된 부분은 필요시 활성화 가능)
    /*
    fun debug(obj: Any) {
        if (Settings.DEBUG.toBool()) Bukkit.broadcastMessage(obj.toString())
    }

    fun debug(obj: Any, prefix: String) {
        if (Settings.DEBUG.toBool()) Bukkit.broadcastMessage("$prefix$obj")
    }

    fun <T> debugVal(obj: T): T {
        if (Settings.DEBUG.toBool()) Bukkit.broadcastMessage(obj.toString())
        return obj
    }

    fun <T> debugVal(obj: T, prefix: String): T {
        if (Settings.DEBUG.toBool()) Bukkit.broadcastMessage("$prefix$obj")
        return obj
    }

    fun debug(component: Component?) {
        if (Settings.DEBUG.toBool()) {
            VortexenPlugin.instance.getAudience().console().sendMessage(component ?: Component.text("null"))
        }
    }
    */
}
