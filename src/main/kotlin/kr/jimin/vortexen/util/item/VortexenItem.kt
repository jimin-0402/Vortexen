package kr.jimin.vortexen.item

import kr.jimin.vortexen.util.MessagesUtils
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class VortexenItem(
    private val item: ItemStack,
    val displayName: String?,
    val description: MutableList<String>?,
    val amount: Int,
    val modelData: Int
) {


   fun giveItem(player: Player, amount: Int) {
        val iS = getItem()
        iS.amount = amount

        player.inventory.addItem(iS)
    }

    fun giveToPlayer(player: Player) {
        val inventory: Inventory = player.inventory

        inventory.addItem(getItem())

        player.sendMessage("아이템이 지급되었습니다: ${displayName ?: "아이템"}")
    }

    fun getUnmodifiedItem(): ItemStack {
        return item
    }

    fun getItem(): ItemStack {
        val iS = getUnmodifiedItem()
        val im = iS.itemMeta ?: return iS

        displayName?.let { name ->
            im.displayName(MessagesUtils.processMessage(name).decoration(TextDecoration.ITALIC, false))
        }

        description?.let { descList ->
            im.lore(descList.map { line -> MessagesUtils.processMessage(line).decoration(TextDecoration.ITALIC, false)})
        }

        if (modelData > 0) {
            im.setCustomModelData(modelData)
        }

        iS.itemMeta = im

        iS.amount = amount
        return iS
    }

}