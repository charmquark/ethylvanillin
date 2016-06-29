package com.invironz.ethylvanillin.common.item.crafting;

import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

/**
 * Created by charmquark on 6/27/2016.
 */
public interface IEVRecipe {

    @Nonnull
    ItemStack getOutput();

    boolean matches(@Nonnull ItemStack stack);


}
