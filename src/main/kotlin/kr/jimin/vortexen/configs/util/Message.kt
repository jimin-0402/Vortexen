package kr.jimin.vortexen.configs.util

import kr.jimin.vortexen.VortexenPlugin
import kr.jimin.vortexen.util.MessagesUtils
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import org.bukkit.command.CommandSender

enum class Message(private val path: String) {

    // general
    PREFIX("general.prefix"),
    RELOAD("general.reload");

    fun getString(): String {
        return VortexenPlugin.instance.getLanguageManager().getLangString(path)
    }

    fun send(sender: CommandSender?, vararg placeholders: TagResolver) {
        sender ?: return

        val lang = getString()
        if (lang.isEmpty()) return // 빈 메시지 처리

        // MessagesUtils를 사용하여 메시지를 처리
        val messageToSend: Component = MessagesUtils.processMessage(lang)

        // 최종 메시지 전송
        VortexenPlugin.instance.getAudience().sender(sender).sendMessage(messageToSend)
    }
}
