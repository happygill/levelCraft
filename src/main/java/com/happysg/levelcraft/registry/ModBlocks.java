package com.happysg.levelcraft.registry;

import com.happysg.levelcraft.LevelCraft;
import com.happysg.levelcraft.block.ResearchTableBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, LevelCraft.MODID);

    public static final RegistryObject<ResearchTableBlock> RESEARCH_TABLE =
            BLOCKS.register("research_table", ResearchTableBlock::new);

    public static void register(IEventBus eventBus) {
        LevelCraft.LOGGER.info("Registering blocks");
        BLOCKS.register(eventBus);
    }
}
