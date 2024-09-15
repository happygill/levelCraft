package com.happysg.levelcraft.lock;

import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LockRegistry {
    static final Map<ResourceLocation, Integer> RECIPE_LOCKS = new HashMap<>();

    public static boolean hasRecipe(ResourceLocation recipeKey) {
        return RECIPE_LOCKS.containsKey(recipeKey);
    }

    static void register(ResourceLocation recipeKey, int xpLevelRequired) {
        RECIPE_LOCKS.put(recipeKey, xpLevelRequired);
    }

    public static int getLevelRequired(ResourceLocation recipeKey) {
        return RECIPE_LOCKS.get(recipeKey);
    }

    public static List<ResourceLocation> getLockedRecipes() {
        return new ArrayList<>(RECIPE_LOCKS.keySet());
    }

}
