package com.invironz.ethylvanillin.common.tile.capabilities.inventory;

import com.invironz.ethylvanillin.common.tile.capabilities.EVItemStackHandler;
import net.minecraft.item.ItemStack;

/**
 * Created by charmquark on 6/27/2016.
 */
public class OneToOneCraftingInventory extends EVItemStackHandler {

    public static final int SLOT_INPUT = 0;
    public static final int SLOT_OUTPUT = 1;


    public OneToOneCraftingInventory() {
        super(2);
    }


    public boolean canProduce(ItemStack stack) {
        ItemStack output = getStackInSlot(SLOT_OUTPUT);
        return output == null
            || (stack.isItemEqual(output) && (stack.stackSize + output.stackSize) <= output.getMaxStackSize());
    }

    public void consumeAndProduce(int amount, ItemStack stack, boolean simulate) {
        extractItem(SLOT_INPUT, 1, simulate);
        insertItem(SLOT_OUTPUT, stack, simulate);
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return (slot == SLOT_OUTPUT) ? super.extractItem(slot, amount, simulate) : null;
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        return (slot == SLOT_INPUT) ? super.insertItem(slot, stack, simulate) : stack;
    }

}
