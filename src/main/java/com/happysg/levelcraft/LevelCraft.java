package com.happysg.levelcraft;

import com.happysg.levelcraft.lock.LockSavedData;
import com.happysg.levelcraft.network.ModMessages;
import com.happysg.levelcraft.registry.ModBlockEntities;
import com.happysg.levelcraft.registry.ModBlocks;
import com.happysg.levelcraft.registry.ModItems;
import com.happysg.levelcraft.registry.ModMenus;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.stream.Collectors;

@Mod(LevelCraft.MODID)
public class LevelCraft {
    public static final String MODID = "levelcraft";
    public static final Logger LOGGER = LogUtils.getLogger();

    public LevelCraft(FMLJavaModLoadingContext context) {
        LOGGER.info("Initializing LevelCraft");
        IEventBus modEventBus = context.getModEventBus();
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenus.register(modEventBus);
        modEventBus.addListener(LevelCraft::commonInit);
        modEventBus.addListener(ModMenus::registerScreens);
        modEventBus.addListener(LevelCraft::addToCreativeTab);
    }


    @Mod.EventBusSubscriber(modid = MODID)
    public static class CommonEvents {
        @SubscribeEvent
        public static void onLoadWorld(LevelEvent.Load event) {
            LevelAccessor world = event.getLevel();
            MinecraftServer server = world.getServer();
            if (server == null) return;
            LockSavedData.load(server);
        }
    }

    public static void commonInit(final FMLCommonSetupEvent event) {
        event.enqueueWork(ModMessages::register);
    }

    public static void addToCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(ModItems.RESEARCH_TABLE_BLOCK_ITEM);
        }
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MODID, path);
    }

    public static String toHumanReadable(String key) {
        String s = key.replaceAll("_", " ");
        s = Arrays.stream(StringUtils.splitByCharacterTypeCamelCase(s)).map(StringUtils::capitalize).collect(Collectors.joining(" "));
        s = StringUtils.normalizeSpace(s);
        return s;
    }
}
