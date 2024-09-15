package com.happysg.levelcraft.lock;

import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class LockManager {
    static final List<ResourceLocation> UNLOCKED_RECIPES = new ArrayList<>();

    public static boolean isUnlocked(ResourceLocation recipeKey) {
        return !LockRegistry.hasRecipe(recipeKey) || UNLOCKED_RECIPES.contains(recipeKey);
    }

}
