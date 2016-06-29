package com.invironz.ethylvanillin.lib;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLLog;

/**
 * Created by charmquark on 6/14/2016.
 */
public class EVLog {

    private static final String PREFIX = "[" + EVNames.MOD_NAME + "] ";


    public static void info(String format, Object... data) {
        FMLLog.info(PREFIX + format, data);
    }

    public static void removedRecipe(ItemStack result) {
        info("Removed a recipe for " + result.getDisplayName());
    }

}
