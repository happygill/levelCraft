package com.happysg.levelcraft.registry;

import com.happysg.levelcraft.LevelCraft;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, LevelCraft.MODID);


    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }

    public static void registerScreens(FMLClientSetupEvent event) {

    }
}
