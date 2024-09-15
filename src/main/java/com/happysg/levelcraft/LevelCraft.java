package com.happysg.levelcraft;

import com.happysg.levelcraft.registry.ModBlockEntities;
import com.happysg.levelcraft.registry.ModBlocks;
import com.happysg.levelcraft.registry.ModItems;
import com.happysg.levelcraft.registry.ModMenus;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
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
        modEventBus.addListener(ModMenus::registerScreens);
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
