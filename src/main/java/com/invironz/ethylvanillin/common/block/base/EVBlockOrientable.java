package com.invironz.ethylvanillin.common.block.base;

import com.invironz.ethylvanillin.lib.EVStates;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * Created by charmquark on 6/9/2016.
 */
public abstract class EVBlockOrientable extends EVBlock {

    protected static final PropertyDirection FACING = EVStates.FACING;
    protected static final int FACING_MASK = 0x7;
    protected static final int FACING_META = 0x4;


    private static final EnumFacing DEFAULT_FACING = EnumFacing.NORTH;


    public static void rotateFacing(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        EnumFacing facing = EVStates.getRotatedFacing(state.getValue(FACING));
        setState(world, pos, state.withProperty(FACING, facing));
    }


    public EVBlockOrientable(Material material, String name) {
        super(material, name);
        setDefaultState(blockState.getBaseState().withProperty(FACING, DEFAULT_FACING));
    }


    @Override @Nonnull
    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        meta = meta & FACING_MASK;
        EnumFacing enumFacing = EnumFacing.getFront(meta);
        if (enumFacing.getAxis() == EnumFacing.Axis.Y) {
            enumFacing = DEFAULT_FACING;
        }
        return this.getDefaultState().withProperty(FACING, enumFacing);
    }

    @Override
    public IBlockState onBlockPlaced(
            World worldIn,
            BlockPos pos,
            EnumFacing blockFaceClickedOn,
            float hitX, float hitY, float hitZ,
            int meta,
            EntityLivingBase placer
    ) {
        EnumFacing enumFacing = (placer == null) ? DEFAULT_FACING : placer.getHorizontalFacing().getOpposite();
        return this.getDefaultState().withProperty(FACING, enumFacing);
    }

}
