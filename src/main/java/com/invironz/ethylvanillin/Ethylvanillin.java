package com.invironz.ethylvanillin;

import com.invironz.ethylvanillin.lib.EVNames;
import com.invironz.ethylvanillin.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by charmquark on 6/9/2016.
 */
@Mod(modid = EVNames.MOD_ID, name = EVNames.MOD_NAME, version = EVNames.VERSION, useMetadata = true)
public final class Ethylvanillin {

    @Mod.Instance(EVNames.MOD_ID)
    public static Ethylvanillin instance;

    @SidedProxy(clientSide = EVNames.PROXY_CLIENT, serverSide = EVNames.PROXY_SERVER)
    public static CommonProxy proxy;


    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

}
