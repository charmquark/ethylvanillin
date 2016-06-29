package com.invironz.ethylvanillin.common.tile.capabilities;

import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

/**
 * Created by charmquark on 6/27/2016.
 */
public class EVItemStackHandler extends ItemStackHandler {

    public EVItemStackHandler(int size) {
        super(size);
    }


    public void dropItems(@Nonnull World world, @Nonnull BlockPos pos) {
        int size = getSlots();
        for (int slot = 0; slot < size; ++slot) {
            ItemStack stack = getStackInSlot(slot);
            if (stack != null) {
                InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);
            }
        }
    }

    public boolean isEmptySlot(int slot) {
        ItemStack stack = getStackInSlot(slot);
        return stack == null || stack.stackSize == 0;
    }

}
