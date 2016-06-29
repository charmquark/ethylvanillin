package com.invironz.ethylvanillin.common.block;

import com.invironz.ethylvanillin.common.block.base.EVBlock;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by charmquark on 6/9/2016.
 */
public class EVBlocks {

    public static final BlockOven  LIT_OVEN = new BlockOven(true);
    public static final BlockOven  OVEN     = new BlockOven(false);
    public static final BlockQuern QUERN    = new BlockQuern();


    public static void init() {
        initBasicRecipes();
    }

    public static void preInit() {
        register(LIT_OVEN);
        register(OVEN);
        register(QUERN);
    }


    private static void initBasicRecipes() {
        ItemStack bricks          = new ItemStack(Blocks.BRICK_BLOCK, 1, 0);
        ItemStack polishedGranite = new ItemStack(Blocks.STONE,       1, 2);
        ItemStack stone           = new ItemStack(Blocks.STONE,       1, 0);

        GameRegistry.addShapedRecipe(new ItemStack(OVEN),  "bbb", "b b", "bbb", 'b', bricks);
        GameRegistry.addShapedRecipe(new ItemStack(QUERN), " g ", "ggg", "sss", 'g', polishedGranite, 's', stone);
    }

    private static void register(EVBlock block, ItemBlock itemBlock) {
        GameRegistry.register(block);
        GameRegistry.register(itemBlock);
        block.registerItemModel(itemBlock);
    }

    private static void register(EVBlock block) {
        ItemBlock itemBlock = new ItemBlock(block);
        itemBlock.setRegistryName(block.getRegistryName());
        register(block, itemBlock);
    }

}
