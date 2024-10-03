package kr.jimin.vortexen.commands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.IntegerArgument
import dev.jorel.commandapi.arguments.PlayerArgument
import dev.jorel.commandapi.executors.CommandExecutor
import kr.jimin.vortexen.VortexenPlugin
import kr.jimin.vortexen.configs.ConfigsManager
import kr.jimin.vortexen.configs.util.Config
import kr.jimin.vortexen.item.VortexenItem
import kr.jimin.vortexen.util.item.ItemHandler
import org.bukkit.entity.Player


class GiveCommand(private val plugin: VortexenPlugin) {

    private val configsManager = ConfigsManager(plugin)

    fun getGiveCommand(): CommandAPICommand {
        return CommandAPICommand("give")
            .withPermission("vortexen.command.give")
            .withArguments(
                PlayerArgument("player")
            )
            .withOptionalArguments(
                IntegerArgument("amount")
            )
            .executes(CommandExecutor executes@{ sender, args ->

                val player = args["player"] as Player
                val amount: Int = args.getOrDefault("amount", 1) as Int

                val namespace: String = Config.ITEM_MATERIAL.getConfigValue().toString()
                val displayName: String = Config.ITEM_DISPLAY_NAME.getConfigValue() as String
                val lore: MutableList<String> = when (val loreValue = Config.ITEM_LORE.getConfigValue()) {
                    is List<*> -> loreValue.filterIsInstance<String>().toMutableList()
                    else -> mutableListOf()
                }
                val modelData: Int = Config.ITEM_MODEL.getConfigValue() as? Int ?: 0

                val vortexenItem: VortexenItem? = ItemHandler.createItem(namespace, displayName, lore, amount, modelData)

                vortexenItem?.giveItem(player, amount)



            })
    }
}