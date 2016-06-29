package com.invironz.ethylvanillin.common.item;

import com.invironz.ethylvanillin.common.item.base.EVItem;
import com.invironz.ethylvanillin.common.item.crafting.EVRecipes;
import com.invironz.ethylvanillin.lib.EVLibCrafting;
import com.invironz.ethylvanillin.lib.EVNames;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by charmquark on 6/9/2016.
 */
public final class EVItems {

    public static final EVItem WHEAT_BREAD = new EVItem(EVNames.WHEAT_BREAD, CreativeTabs.FOOD);
    public static final EVItem WHEAT_DOUGH = new EVItem(EVNames.WHEAT_DOUGH, CreativeTabs.FOOD);
    public static final EVItem WHEAT_FLOUR = new EVItem(EVNames.WHEAT_FLOUR, CreativeTabs.FOOD);


    public static void init() {
        initVanillaOverrides();
        initBasicRecipes();
        initFurnaceRecipes();
        initQuernRecipes();
    }

    public static void preInit() {
        register(WHEAT_BREAD);
        register(WHEAT_DOUGH);
        register(WHEAT_FLOUR);
    }


    private static void initFurnaceRecipes() {
        FurnaceRecipes furnace = FurnaceRecipes.instance();
        furnace.addSmelting(WHEAT_DOUGH, new ItemStack(Items.BREAD, 1), 0);
    }

    private static void initBasicRecipes() {
        GameRegistry.addShapedRecipe(new ItemStack(WHEAT_DOUGH, 4), "fff", "fwf", "fff", 'f', WHEAT_FLOUR, 'w', Items.WATER_BUCKET);
    }

    private static void initQuernRecipes() {
        EVRecipes.addGrinding(Items.WHEAT, WHEAT_FLOUR, 3);
    }

    private static void initVanillaOverrides() {
        EVLibCrafting.removeRecipesFor(new ItemStack(Items.BREAD, 1));
    }


    private static void register(Item item) {
        GameRegistry.register(item);
        if (item instanceof EVItem) {
            ((EVItem) item).registerItemModel();
        }
    }

}
