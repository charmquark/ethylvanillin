package com.invironz.ethylvanillin.common.tile.base;

import com.invironz.ethylvanillin.common.tile.capabilities.EVItemStackHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by charmquark on 6/27/2016.
 */
public abstract class EVTileEntityContainer extends EVTileEntity {

    @CapabilityInject(IItemHandler.class)
    public static Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = null;


    private static final String NBT_KEY_INVENTORY = "Inventory";


    private EVItemStackHandler inventory = createNewInventory();


    public EVTileEntityContainer(String evName) {
        super(evName);
    }


    @Override @Nonnull
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return (T) inventory;
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nonnull EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.hasKey(NBT_KEY_INVENTORY)) {
            getInventory().deserializeNBT(nbt.getCompoundTag(NBT_KEY_INVENTORY));
        }
    }

    @Override @Nonnull
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setTag(NBT_KEY_INVENTORY, getInventory().serializeNBT());
        return nbt;
    }


    @Nonnull
    protected EVItemStackHandler getInventory() {
        return inventory;
    }

    @Nonnull
    protected abstract EVItemStackHandler createNewInventory();

}
