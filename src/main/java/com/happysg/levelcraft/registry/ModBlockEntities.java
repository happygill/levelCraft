package com.happysg.levelcraft.registry;

import com.happysg.levelcraft.LevelCraft;
import com.happysg.levelcraft.block.ResearchTableBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, LevelCraft.MODID);

    public static final RegistryObject<BlockEntityType<ResearchTableBlockEntity>> RESEARCH_TABLE = BLOCK_ENTITIES
            .register("research_table", () -> BlockEntityType.Builder.of(ResearchTableBlockEntity::new, ModBlocks.RESEARCH_TABLE.get()).build(null));


    public static void register(IEventBus eventBus) {
        LevelCraft.LOGGER.info("Registering block entities");
        BLOCK_ENTITIES.register(eventBus);
    }
}
