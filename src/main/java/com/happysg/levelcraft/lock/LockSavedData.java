package com.happysg.levelcraft.lock;

import com.happysg.levelcraft.LevelCraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;

import static com.happysg.levelcraft.lock.LockManager.UNLOCKED_RECIPES;

public class LockSavedData extends SavedData {

    private LockSavedData() {
    }


    @Override
    public CompoundTag save(CompoundTag compoundTag) {
        CompoundTag unlockedRecipes = new CompoundTag();
        int index = 0;
        for (ResourceLocation recipe : UNLOCKED_RECIPES) {
            unlockedRecipes.putString("recipe_" + index, recipe.toString());
            index++;
        }
        unlockedRecipes.putInt("size", index);
        return unlockedRecipes;
    }


    private static LockSavedData load(CompoundTag nbt) {
        LockSavedData sd = new LockSavedData();
        UNLOCKED_RECIPES.clear();
        int size = nbt.getInt("size");
        for (int i = 0; i < size; i++) {
            UNLOCKED_RECIPES.add(ResourceLocation.tryParse(nbt.getString("recipe_" + i)));
        }
        return sd;
    }

    public static LockSavedData load(MinecraftServer server) {
        return server.overworld()
                .getDataStorage()
                .computeIfAbsent(LockSavedData::load, LockSavedData::new, LevelCraft.MODID + "_lock");
    }


}
