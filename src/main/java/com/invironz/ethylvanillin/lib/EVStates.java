package com.invironz.ethylvanillin.lib;

import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.util.EnumFacing;

import java.util.Arrays;

/**
 * Created by charmquark on 6/9/2016.
 */
public final class EVStates {

    public static final PropertyBool BURNING = PropertyBool.create("burning");
    public static final PropertyDirection FACING = PropertyDirection.create("facing", Arrays.asList(EnumFacing.HORIZONTALS));


    public static EnumFacing getRotatedFacing(EnumFacing facing) {
        switch (facing) {
            case NORTH:
                return EnumFacing.EAST;

            case EAST:
                return EnumFacing.SOUTH;

            case SOUTH:
                return EnumFacing.WEST;

            default:
                return EnumFacing.NORTH;
        }
    }

}
