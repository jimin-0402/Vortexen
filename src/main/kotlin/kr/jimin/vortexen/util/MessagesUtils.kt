package kr.jimin.vortexen.util

import kr.jimin.vortexen.configs.util.Message
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.Tag
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer

object MessagesUtils {
    val MINI_MESSAGE: MiniMessage = MiniMessage.miniMessage()

    // PluginTagResolver 정의
    private val PluginTagResolver: TagResolver = TagResolver.resolver(
        TagResolver.standard(),
        TagResolver.resolver(
            "prefix",
            Tag.selfClosingInserting(MINI_MESSAGE.deserialize(Message.PREFIX.getString()))
        )
    )

    // MINI_MESSAGE 초기화
    val MINI_MESSAGE_WITH_TAGS: MiniMessage = MiniMessage.builder().tags(PluginTagResolver).build()

    // LegacySerializer 정의
    val LEGACY_SERIALIZER: LegacyComponentSerializer =
        LegacyComponentSerializer.builder().hexColors().useUnusualXRepeatedCharacterHexFormat().build()

    // 메시지를 처리하는 메서드
//    fun processMessage(message: String): Component {
//        val parts = message.split(";", limit = 2)
//        return if (parts.size > 1 && parts[0].trim().equals("legacy", ignoreCase = true)) {
//            // 레거시 메시지를 처리
//            val legacyContent = parts[1].trim().replace("&", "§")
//            LEGACY_SERIALIZER.deserialize(legacyContent)
//        } else {
//            // 미니 메시지를 처리
//            MINI_MESSAGE_WITH_TAGS.deserialize(parts[0].trim())
//        }
//    }
    fun processMessage(message: String): Component {
        // 메시지를 legacy; 기준으로 분리
        val parts = message.split("legacy;", limit = 2)

        return if (parts.size > 1) {
            // legacy; 뒤의 내용을 레거시 메시지로 처리
            val legacyContent = parts[1].trim().replace("&", "§")
            val legacyComponent = LEGACY_SERIALIZER.deserialize(legacyContent)

            // prefix와 레거시 메시지를 결합
            val prefix = parts[0].trim() // legacy; 앞의 부분을 미니 메시지로 처리
            val prefixComponent = MINI_MESSAGE_WITH_TAGS.deserialize(prefix)

            // 두 컴포넌트를 결합
            prefixComponent.append(legacyComponent)
        } else {
            // legacy;가 없는 경우, 전체 메시지를 미니 메시지로 처리
            MINI_MESSAGE_WITH_TAGS.deserialize(message.trim())
        }
    }


    // 태그 리졸버 메서드
    fun tagResolver(string: String?): TagResolver {
        return TagResolver.resolver(string ?: "", Tag.selfClosingInserting(processMessage(string ?: "")))
    }

    // 태그 리졸버 메서드 (Component 형태)
    fun tagResolver(string: String?, tag: Component?): TagResolver {
        return TagResolver.resolver(string!!, Tag.selfClosingInserting(tag!!))
    }
}
