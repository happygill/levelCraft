package com.happysg.levelcraft.registry;

import com.happysg.levelcraft.LevelCraft;
import com.happysg.levelcraft.gui.ResearchMenu;
import com.happysg.levelcraft.gui.ResearchScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, LevelCraft.MODID);


    public static final RegistryObject<MenuType<ResearchMenu>> RESEARCH_MENU = MENUS.register("research_menu", () ->
            IForgeMenuType.create((id, playerInv, data) -> new ResearchMenu(id, playerInv, data.readBlockPos())));


    public static void register(IEventBus eventBus) {
        LevelCraft.LOGGER.info("Registering menus");
        MENUS.register(eventBus);
    }

    public static void registerScreens(FMLClientSetupEvent event) {
        LevelCraft.LOGGER.info("Registering screens");
        event.enqueueWork(
                () -> MenuScreens.register(RESEARCH_MENU.get(), ResearchScreen::new)
        );
    }
}
