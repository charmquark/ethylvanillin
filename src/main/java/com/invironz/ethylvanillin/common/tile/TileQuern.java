package com.invironz.ethylvanillin.common.tile;

import com.invironz.ethylvanillin.common.block.BlockQuern;
import com.invironz.ethylvanillin.common.block.base.EVBlockOrientable;
import com.invironz.ethylvanillin.common.item.crafting.GrindingRecipe;
import com.invironz.ethylvanillin.common.item.crafting.EVRecipes;
import com.invironz.ethylvanillin.lib.EVLog;
import com.invironz.ethylvanillin.lib.EVNames;
import com.invironz.ethylvanillin.common.sound.EVSounds;
import com.invironz.ethylvanillin.common.tile.base.EVTileEntityContainer;
import com.invironz.ethylvanillin.common.tile.capabilities.EVItemStackHandler;
import com.invironz.ethylvanillin.common.tile.capabilities.inventory.OneToOneCraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.invironz.ethylvanillin.common.tile.capabilities.inventory.OneToOneCraftingInventory.SLOT_INPUT;
import static com.invironz.ethylvanillin.common.tile.capabilities.inventory.OneToOneCraftingInventory.SLOT_OUTPUT;

/**
 * Created by charmquark on 6/9/2016.
 */
public class TileQuern extends EVTileEntityContainer {

    private static final long USE_COOLDOWN = 5;
    private static final String NBT_KEY_WORK = "Work";
    private static final long SOUND_COOLDOWN = 20;
    private static final int WORK_NEEDED = 3;


    private long useCooldownTime = 0;
    private long soundCooldownTime = 0;
    private int workDone = 0;


    public TileQuern() {
        super(EVNames.QUERN);
    }


    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.hasKey(NBT_KEY_WORK)) {
            workDone = (int) nbt.getByte(NBT_KEY_WORK);
        }
    }

    public void whenBlockActivated(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull EnumFacing side) {
        long time = world.getTotalWorldTime();
        if (time > useCooldownTime && side != EnumFacing.DOWN) {
            useCooldownTime = time + USE_COOLDOWN;

            GrindingRecipe recipe = canCraft();
            if (recipe != null && craft(recipe)) {
                EVBlockOrientable.rotateFacing(world, pos);

                if (time >= soundCooldownTime) {
                    EVSounds.ENTITY_QUERN_USE.play(world, pos);
                    soundCooldownTime = time + SOUND_COOLDOWN;
                }

                if (world instanceof WorldServer && world.rand.nextInt(5) == 0) {
                    ((WorldServer) world).spawnParticle(
                        EnumParticleTypes.SMOKE_NORMAL,
                        false,
                        pos.getX() + 0.5d,
                        pos.getY() + 0.5d,
                        pos.getZ() + 0.5d,
                        3,
                        world.rand.nextGaussian() * 0.3d,
                        0d,
                        world.rand.nextGaussian() * 0.3d,
                        0d
                    );
                }

                markDirty();
            }
        }
    }

    public void whenBlockBroken(@Nonnull World world, @Nonnull BlockPos pos, @Nullable BlockQuern block) {
        if (!preserve) {
            getInventory().dropItems(world, pos);
            world.updateComparatorOutputLevel(pos, block);
        }
    }

    @Override @Nonnull
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setByte(NBT_KEY_WORK, (byte) workDone);
        return nbt;
    }


    @Override @Nonnull
    protected EVItemStackHandler createNewInventory() {
        return new OneToOneCraftingInventory();
    }


    private GrindingRecipe canCraft() {
        OneToOneCraftingInventory inventory = (OneToOneCraftingInventory) getInventory();
        if (inventory.isEmptySlot(SLOT_INPUT)) return null;

        GrindingRecipe recipe = EVRecipes.getGrindingRecipe(inventory.getStackInSlot(SLOT_INPUT));
        if (recipe == null) return null;
        if (!inventory.canProduce(recipe.getOutput())) return null;

        return recipe;
    }

    private boolean craft(GrindingRecipe recipe) {
        ++workDone;
        if (workDone < recipe.getWorkNeeded()) return false;

        ((OneToOneCraftingInventory) getInventory()).consumeAndProduce(1, recipe.getOutput().copy(), false);
        return true;
    }

}
