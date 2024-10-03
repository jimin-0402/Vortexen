package kr.jimin.vortexen.commands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.executors.CommandExecutor
import kr.jimin.vortexen.VortexenPlugin
import kr.jimin.vortexen.configs.util.Message

class ReloadCommand(private val plugin: VortexenPlugin) {

    private val configsManager = plugin.getConfigsManager() // ConfigsManager 인스턴스 가져오기

    fun getReloadCommand(): CommandAPICommand {
        return CommandAPICommand("reload")
            .withPermission("vortexen.command.reload")
            .executes(CommandExecutor { sender, args ->
                // 설정 리로드
                configsManager.reload()
                // 리로드 완료 메시지 전송
                Message.RELOAD.send(sender)
            })
    }
}
