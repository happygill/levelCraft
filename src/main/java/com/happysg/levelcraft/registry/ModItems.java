package com.happysg.levelcraft.registry;

import com.happysg.levelcraft.LevelCraft;
import com.happysg.levelcraft.item.ResearchTableBlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LevelCraft.MODID);

    public static final RegistryObject<ResearchTableBlockItem> RESEARCH_TABLE_BLOCK_ITEM =
            ITEMS.register("research_table", ResearchTableBlockItem::new);

    public static void register(IEventBus eventBus) {
        LevelCraft.LOGGER.info("Registering items");
        ITEMS.register(eventBus);
    }
}
