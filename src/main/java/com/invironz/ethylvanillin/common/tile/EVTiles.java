package com.invironz.ethylvanillin.common.tile;

import com.invironz.ethylvanillin.lib.EVNames;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by charmquark on 6/10/2016.
 */
public final class EVTiles {

    public static void init() {}

    public static void preInit() {
        register(TileOven.class, EVNames.OVEN);
        register(TileQuern.class, EVNames.QUERN);
    }

    private static void register(Class<? extends TileEntity> tileClass, String name) {
        GameRegistry.registerTileEntity(tileClass, EVNames.PREFIX + name);
    }

}
