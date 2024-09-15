package com.happysg.levelcraft.mixin;

import com.happysg.levelcraft.lock.LockManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {

    @Inject(method = "byType", at = @At("RETURN"), cancellable = true)
    private <C extends Container, T extends Recipe<C>> void filterUnlockedRecipes(RecipeType<T> pRecipeType, CallbackInfoReturnable<Map<ResourceLocation, T>> cir) {
        Map<ResourceLocation, T> recipes = cir.getReturnValue();
        if (recipes != null) {
            Map<ResourceLocation, T> filteredRecipes = recipes.entrySet().stream()
                    .filter(entry -> LockManager.isUnlocked(entry.getKey()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            cir.setReturnValue(filteredRecipes);
        } else {
            cir.setReturnValue(Collections.emptyMap());
        }
    }
}
