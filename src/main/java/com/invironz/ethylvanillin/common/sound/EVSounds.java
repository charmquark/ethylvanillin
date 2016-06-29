package com.invironz.ethylvanillin.common.sound;

import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by charmquark on 6/10/2016.
 */
public final class EVSounds {

    public static final EVSoundEvent ENTITY_QUERN_USE = new EVSoundEvent("entity.quern.use", SoundCategory.BLOCKS, 0.5f, 0.8f);


    public static void preInit() {
        GameRegistry.register(ENTITY_QUERN_USE);
    }

}
