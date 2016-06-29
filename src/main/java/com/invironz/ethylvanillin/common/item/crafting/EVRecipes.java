package com.invironz.ethylvanillin.common.item.crafting;

import com.google.common.collect.Lists;
import com.invironz.ethylvanillin.lib.EVLog;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by charmquark on 6/13/2016.
 */
public class EVRecipes {

    private static final int GENERIC_STACK_META = 0x7fff;

    private static List<IEVRecipe> grindingList = Lists.newArrayList();


    public static void addGrinding(Item input, Item output, int workNeeded, boolean ignoreMetadata) {
        addGrinding(new GrindingRecipe(
            new ItemStack(input, 1, GENERIC_STACK_META),
            new ItemStack(output),
            workNeeded,
            ignoreMetadata
        ));
    }

    public static void addGrinding(Item input, Item output, int workNeeded) {
        addGrinding(input, output, workNeeded, true);
    }

    public static void addGrinding(Item input, ItemStack output, int workNeeded, boolean ignoreMetadata) {
        addGrinding(new GrindingRecipe(
            new ItemStack(input, 1, GENERIC_STACK_META),
            output,
            workNeeded,
            ignoreMetadata
        ));
    }

    public static void addGrinding(Item input, ItemStack output, int workNeeded) {
        addGrinding(input, output, workNeeded, true);
    }

    public static void addGrinding(GrindingRecipe recipe) {
        if (getGrindingRecipe(recipe.getInput()) != null) {
            EVLog.info("Ignored grinding recipe with conflicting input: " + recipe);
            return;
        }
        grindingList.add(recipe);
    }

    @Nullable
    public static GrindingRecipe getGrindingRecipe(ItemStack stack) {
        return (GrindingRecipe) getCraftingRecipe(stack, grindingList);
    }


    @Nullable
    private static IEVRecipe getCraftingRecipe(@Nullable ItemStack stack, List<IEVRecipe> craftingList) {
        if (stack != null && stack.stackSize > 0) {
            for (IEVRecipe recipe : craftingList) {
                if (recipe.matches(stack)) {
                    return recipe;
                }
            }
        }
        return null;
    }

}
