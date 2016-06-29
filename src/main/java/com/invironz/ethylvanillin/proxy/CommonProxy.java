package com.invironz.ethylvanillin.proxy;

import com.invironz.ethylvanillin.common.block.EVBlocks;
import com.invironz.ethylvanillin.common.item.EVItems;
import com.invironz.ethylvanillin.common.sound.EVSounds;
import com.invironz.ethylvanillin.common.tile.EVTiles;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by charmquark on 6/9/2016.
 */
public class CommonProxy {
    public void init(FMLInitializationEvent event) {
        EVItems.init();
        EVBlocks.init();
        EVTiles.init();
    }

    public void postInit(FMLPostInitializationEvent event) {}

    public void preInit(FMLPreInitializationEvent event) {
        EVItems.preInit();
        EVBlocks.preInit();
        EVTiles.preInit();
        EVSounds.preInit();
    }

    public void registerItemRenderer(Item item, int meta) {}

}
