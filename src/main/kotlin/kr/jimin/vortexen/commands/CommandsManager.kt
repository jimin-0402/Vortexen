package kr.jimin.vortexen.commands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.executors.CommandExecutor
import kr.jimin.vortexen.VortexenPlugin
import kr.jimin.vortexen.configs.util.Config
import kr.jimin.vortexen.configs.util.Message

class CommandsManager(private val plugin: VortexenPlugin) {

    fun loadsCommands() {
        CommandAPICommand("vortexen")
            .withAliases("v", "vor")
            .withPermission("vortexen.command")
            .withSubcommands(
                ReloadCommand(plugin).getReloadCommand(),
                GiveCommand(plugin).getGiveCommand()
            )
            .executes(CommandExecutor { sender, args ->
                Message.PREFIX.send(sender)

                sender.sendMessage(Config.LANGUAGE.getConfigValue().toString())

            })
            .register()
    }
}