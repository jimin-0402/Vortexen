package kr.jimin.vortexen.util.item.factories

import org.bukkit.inventory.ItemStack
import org.joml.sampling.BestCandidateSampling.Disk

interface ItemFactory {
    fun create(
        id: String,
        displayName: String?,
        description: MutableList<String>?,
        amount: Int,
        modelData: Int
        ): ItemStack
}