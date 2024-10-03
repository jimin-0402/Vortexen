package kr.jimin.vortexen.util.item

import kr.jimin.vortexen.item.VortexenItem
import kr.jimin.vortexen.util.item.factories.ItemFactory
import kr.jimin.vortexen.util.item.factories.impl.IAFactory
import kr.jimin.vortexen.util.item.factories.impl.OraxenFactory
import org.bukkit.Material
import org.bukkit.inventory.ItemStack


object ItemHandler {

    val factories: MutableMap<String, ItemFactory> = mutableMapOf(
        "ITEMSADDER" to IAFactory,
        "ORAXEN" to OraxenFactory
    )

    fun createItem(
        namespace: String,
        displayName: String?,
        description: MutableList<String>?,
        amount: Int,
        modelData: Int
    ) : VortexenItem? {
        val item = if (namespace.contains(":")) {
            val id = namespace.split(":").first().uppercase()
            val factory = factories[id] ?: return null
            factory.create(namespace.substring(id.length + 1), displayName, description, amount, modelData)
        } else { ItemStack(Material.valueOf(namespace.uppercase())) }
        return createItem(item, displayName, description, amount, modelData)
    }

    private fun createItem(
        item: ItemStack,
        displayName: String?,
        description: MutableList<String>?,
        amount: Int,
        modelData: Int
    ) : VortexenItem {
        return VortexenItem(
            item,
            displayName,
            description,
            amount,
            modelData
        )
    }

}