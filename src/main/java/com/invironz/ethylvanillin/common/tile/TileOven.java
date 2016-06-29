package com.invironz.ethylvanillin.common.tile;

import com.invironz.ethylvanillin.common.block.BlockOven;
import com.invironz.ethylvanillin.lib.EVNames;
import com.invironz.ethylvanillin.common.tile.base.EVTileEntityContainer;
import com.invironz.ethylvanillin.common.tile.capabilities.EVItemStackHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by charmquark on 6/27/2016.
 */
public class TileOven extends EVTileEntityContainer {

    private static class Inventory extends EVItemStackHandler {

        Inventory() {
            super(3);
        }


        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            return (slot == SLOT_OUTPUT) ? super.extractItem(slot, amount, simulate) : null;
        }

        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
            return (slot != SLOT_OUTPUT) ? super.insertItem(slot, stack, simulate) : stack;
        }


        ItemStack extractFuelItem(int amount, boolean simulate) {
            return super.extractItem(SLOT_FUEL, amount, simulate);
        }

        ItemStack extractInputItem(int amount, boolean simulate) {
            return super.extractItem(SLOT_INPUT, amount, simulate);
        }

        ItemStack insertOutputItem(ItemStack stack, boolean simulate) {
            return super.insertItem(SLOT_OUTPUT, stack, simulate);
        }

    }


    private static final int SLOT_INPUT = 0;
    private static final int SLOT_FUEL = 1;
    private static final int SLOT_OUTPUT = 2;


    public TileOven() {
        super(EVNames.OVEN);
    }


    public void whenBlockActivated(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull EnumFacing side) {
    }

    public void whenBlockBroken(@Nonnull World world, @Nonnull BlockPos pos, @Nullable BlockOven block) {
        if (!preserve) {
            getInventory().dropItems(world, pos);
            world.updateComparatorOutputLevel(pos, block);
        }
    }


    @Override @Nonnull
    protected EVItemStackHandler createNewInventory() {
        return new Inventory();
    }

}
