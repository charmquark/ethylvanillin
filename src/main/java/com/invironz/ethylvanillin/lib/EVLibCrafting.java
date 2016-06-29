package com.invironz.ethylvanillin.lib;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

import java.util.List;

/**
 * Created by charmquark on 6/14/2016.
 */
public final class EVLibCrafting {

    public static void removeRecipesFor(ItemStack stack) {
        ItemStack recipeResult;
        List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
        for (int index = 0, size = recipes.size(); index < size; ++index) {
            recipeResult = recipes.get(index).getRecipeOutput();
            if (ItemStack.areItemStacksEqual(stack, recipeResult)) {
                recipes.remove(index--);
                --size;
                EVLog.removedRecipe(recipeResult);
            }
        }
    }

}
