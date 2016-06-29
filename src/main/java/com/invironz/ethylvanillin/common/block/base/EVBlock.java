package com.invironz.ethylvanillin.common.block.base;

import com.invironz.ethylvanillin.Ethylvanillin;
import com.invironz.ethylvanillin.lib.EVNames;
import com.invironz.ethylvanillin.common.tile.base.EVTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * Created by charmquark on 6/9/2016.
 */
public class EVBlock extends Block {

    /**
     * Sets the block state at a given location. Flag 1 will cause a block update. Flag 2 will send the change to
     * clients (you almost always want this). Flag 4 prevents the block from being re-rendered, if this is a client
     * world. Flags can be added together.
     */
    protected static void setState(@Nonnull World world, @Nonnull BlockPos pos, IBlockState state, int flags) {
        TileEntity   entity   = world.getTileEntity(pos);
        EVTileEntity evEntity = null;
        if (entity instanceof EVTileEntity) {
            evEntity = (EVTileEntity) entity;
            evEntity.preserve = true;
        }

        world.setBlockState(pos, state, flags);
        if (evEntity != null) {
            evEntity.preserve = false;
        }
        if (entity != null) {
            entity.validate();
            world.setTileEntity(pos, entity);
        }
    }

    protected static void setState(@Nonnull World world, @Nonnull BlockPos pos, IBlockState state) {
        setState(world, pos, state, 3);
    }


    EVBlock(Material material, String name) {
        super(material);
        setName(name);
    }


    public void registerItemModel(ItemBlock itemBlock) {
        Ethylvanillin.proxy.registerItemRenderer(itemBlock, 0);
    }

    @Override @Nonnull
    public EVBlock setCreativeTab(@Nonnull CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }


    private void setName(String name) {
        setUnlocalizedName(EVNames.PREFIX + name);
        setRegistryName(name);
    }

}
