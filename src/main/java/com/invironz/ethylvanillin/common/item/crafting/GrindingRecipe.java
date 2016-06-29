package com.invironz.ethylvanillin.common.item.crafting;

import com.invironz.ethylvanillin.lib.EVLog;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

/**
 * Created by charmquark on 6/27/2016.
 */
public class GrindingRecipe implements IEVRecipe {

    private boolean   ignoreMetadata;
    private ItemStack input;
    private ItemStack output;
    private int       workNeeded;


    public GrindingRecipe(@Nonnull ItemStack input, @Nonnull ItemStack output, int workNeeded, boolean ignoreMetadata) {
        this.ignoreMetadata   = ignoreMetadata;
        this.input            = input;
        this.output           = output;
        this.workNeeded       = workNeeded;
    }

    public GrindingRecipe(@Nonnull ItemStack input, @Nonnull ItemStack output, int workNeeded) {
        this(input, output, workNeeded, true);
    }


    @Nonnull
    public ItemStack getOutput() {
        return output;
    }

    public boolean matches(@Nonnull ItemStack stack) {
        boolean match = stack.isItemEqual(input);
        if (match && !ignoreMetadata) {
            match = stack.getMetadata() == input.getMetadata();
        }
        return match;
    }

    public int getWorkNeeded() {
        return workNeeded;
    }

    @Nonnull
    ItemStack getInput() {
        return input;
    }

}
