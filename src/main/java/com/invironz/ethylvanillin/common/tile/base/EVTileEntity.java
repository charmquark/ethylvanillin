package com.invironz.ethylvanillin.common.tile.base;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by charmquark on 6/9/2016.
 */
public abstract class EVTileEntity extends TileEntity {

    public boolean preserve = false;

    private String evName;


    public EVTileEntity(String evName) {
        this.evName = evName;
    }


    public String getEVName() {
        return evName;
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(getPos(), getBlockMetadata(), getUpdateTag());
    }
}
