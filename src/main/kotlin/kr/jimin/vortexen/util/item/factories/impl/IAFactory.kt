package kr.jimin.vortexen.util.item.factories.impl

import dev.lone.itemsadder.api.CustomStack
import kr.jimin.vortexen.util.item.factories.ItemFactory
import org.bukkit.inventory.ItemStack

object IAFactory: ItemFactory {
    override fun create(
        id: String,
        displayName: String?,
        description: MutableList<String>?,
        amount: Int,
        modelData: Int
    ): ItemStack {
        return CustomStack.getInstance(id)!!.itemStack
    }
}