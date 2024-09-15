package com.happysg.levelcraft.registry;

import com.happysg.levelcraft.LevelCraft;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LevelCraft.MODID);


    public static void register(IEventBus eventBus) {
        LevelCraft.LOGGER.info("Registering items");
        ITEMS.register(eventBus);
    }
}
