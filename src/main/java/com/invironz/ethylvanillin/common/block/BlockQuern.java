package com.invironz.ethylvanillin.common.block;

import com.invironz.ethylvanillin.common.block.base.EVBlockOrientable;
import com.invironz.ethylvanillin.lib.EVNames;
import com.invironz.ethylvanillin.common.tile.TileQuern;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by charmquark on 6/9/2016.
 */
public class BlockQuern extends EVBlockOrientable implements ITileEntityProvider {

    private static boolean canActivateWith(@Nullable ItemStack stack) {
        return (stack != null) && (stack.stackSize > 0) && canActivateWith(stack.getItem());
    }

    private static boolean canActivateWith(@Nullable Item item) {
        return item == Items.STICK;
    }

    @Nullable
    private static TileQuern getTileEntity(@Nonnull World world, @Nonnull BlockPos pos) {
        TileEntity entity = world.getTileEntity(pos);
        if (entity != null && entity instanceof TileQuern) {
            return (TileQuern) entity;
        } else {
            return null;
        }
    }


    BlockQuern() {
        super(Material.ROCK, EVNames.QUERN);
        setCreativeTab(CreativeTabs.INVENTORY);
        setHardness(3);
    }


    @Override
    public void breakBlock(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state)
    {
        if (!world.isRemote) {
            TileQuern entity = getTileEntity(world, pos);
            if (entity != null) {
                entity.whenBlockBroken(world, pos, this);
            }
        }
        super.breakBlock(world, pos, state);
    }

    @Override @Nonnull
    public TileEntity createNewTileEntity(@Nonnull World world, int meta) {
        return new TileQuern();
    }

    @Override
    public boolean onBlockActivated(
            @Nonnull World world,
            @Nonnull BlockPos pos,
            IBlockState state,
            EntityPlayer player,
            @Nonnull EnumHand hand,
            @Nullable ItemStack heldItem,
            @Nonnull EnumFacing side,
            float hitX,
            float hitY,
            float hitZ
    ) {
        if (!world.isRemote && canActivateWith(heldItem)) {
            TileQuern entity = getTileEntity(world, pos);
            if (entity != null) {
                entity.whenBlockActivated(world, pos, side);
            }
        }
        return true;
    }

}
