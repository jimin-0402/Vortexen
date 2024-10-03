package kr.jimin.vortexen.util.item.factories.impl

import io.th0rgal.oraxen.api.OraxenItems
import kr.jimin.vortexen.util.item.factories.ItemFactory
import net.kyori.adventure.text.Component
import org.bukkit.inventory.ItemStack

object OraxenFactory: ItemFactory {
    override fun create(
        id: String,
        displayName: String?,
        description: MutableList<String>?,
        amount: Int,
        modelData: Int
    ): ItemStack {
        return OraxenItems.getItemById(id).build()
    }


}