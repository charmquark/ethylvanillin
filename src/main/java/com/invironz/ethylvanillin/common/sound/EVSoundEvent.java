package com.invironz.ethylvanillin.common.sound;

import com.invironz.ethylvanillin.lib.EVNames;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by charmquark on 6/13/2016.
 */
public class EVSoundEvent extends SoundEvent {

    private float pitch = 1f;
    private SoundCategory soundCategory;
    private float volume = 1f;


    public EVSoundEvent(String name, SoundCategory soundCategory) {
        super(new ResourceLocation(EVNames.MOD_ID, name));
        setRegistryName(EVNames.MOD_ID, name);
        this.soundCategory = soundCategory;
    }

    public EVSoundEvent(String name, SoundCategory soundCategory, float volume, float pitch) {
        this(name, soundCategory);
        this.volume = volume;
        this.pitch = pitch;
    }


    public float getPitch() {
        return pitch;
    }

    public SoundCategory getSoundCategory() {
        return soundCategory;
    }

    public float getVolume() {
        return volume;
    }

    public void play(EntityPlayer player, World world, BlockPos pos) {
        world.playSound(player, pos, this, soundCategory, volume, pitch);
    }

    public void play(World world, BlockPos pos) {
        play(null, world, pos);
    }

}
