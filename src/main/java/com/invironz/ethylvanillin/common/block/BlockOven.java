package com.invironz.ethylvanillin.common.block;

import com.invironz.ethylvanillin.common.block.base.EVBlockOrientable;
import com.invironz.ethylvanillin.lib.EVNames;
import com.invironz.ethylvanillin.lib.EVStates;
import com.invironz.ethylvanillin.common.tile.TileOven;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

/**
 * Created by charmquark on 6/27/2016.
 */
public class BlockOven extends EVBlockOrientable implements ITileEntityProvider {

    public static void setBurning(@Nonnull World world, @Nonnull BlockPos pos, boolean burning) {
        IBlockState state = world.getBlockState(pos);
        setState(world, pos, state);
    }


    @Nullable
    private static TileOven getTileEntity(@Nonnull World world, @Nonnull BlockPos pos) {
        TileEntity entity = world.getTileEntity(pos);
        if (entity != null && entity instanceof TileOven) {
            return (TileOven) entity;
        } else {
            return null;
        }
    }


    private boolean isBurning = false;


    BlockOven(boolean isBurning) {
        super(Material.ROCK, isBurning ? EVNames.LIT_OVEN : EVNames.OVEN);
        this.isBurning = isBurning;
        if (!isBurning) {
            setCreativeTab(CreativeTabs.DECORATIONS);
        }
        setHardness(3);
    }


    @Override
    public void breakBlock(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
        if (!world.isRemote) {
            TileOven entity = getTileEntity(world, pos);
            if (entity != null) {
                entity.whenBlockBroken(world, pos, this);
            }
        }
        super.breakBlock(world, pos, state);
    }

    @Override @Nonnull
    public TileEntity createNewTileEntity(@Nonnull World world, int meta) {
        return new TileOven();
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
        if (!world.isRemote) {
            TileOven entity = getTileEntity(world, pos);
            if (entity != null) {
                entity.whenBlockActivated(world, pos, side);
            }
        }
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(
        @Nonnull IBlockState state,
        @Nonnull World world,
        @Nonnull BlockPos pos,
        @Nonnull Random rand
    ) {
        if (!isBurning) return;
        spawnParticles(world, pos, state, rand);
        playSound(world, pos, rand);
    }


    @SideOnly(Side.CLIENT)
    private void playSound(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull Random rand) {
        if (rand.nextDouble() < 0.1D)
        {
            world.playSound(
                pos.getX() + 0.5D,
                pos.getY(),
                pos.getZ() + 0.5D,
                SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE,
                SoundCategory.BLOCKS,
                1.0F,
                1.0F,
                false
            );
        }
    }

    @SideOnly(Side.CLIENT)
    private void spawnParticles(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state, Random rand) {
        EnumFacing facing = state.getValue(FACING);
        double x = pos.getX() + 0.5d;
        double y = pos.getY() + (rand.nextDouble() * 6d / 16d);
        double z = pos.getZ() + 0.5d;
        double offset1 = 0.52d;
        double offset2 = (rand.nextDouble() * 6d) - 0.3d;
        switch (facing) {
            case WEST:
                x -= offset1;
                z += offset2;
                break;
            case EAST:
                x += offset1;
                z += offset2;
                break;
            case NORTH:
                x += offset2;
                z -= offset1;
                break;
            case SOUTH:
                x += offset2;
                z += offset1;
                break;
            default:
        }
        world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x, y, z, 0d, 0d, 0d);
        world.spawnParticle(EnumParticleTypes.FLAME, x, y, z, 0d, 0d, 0d);
    }

}
