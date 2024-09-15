package com.happysg.levelcraft.lock;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.happysg.levelcraft.LevelCraft;
import com.happysg.levelcraft.network.ModMessages;
import com.happysg.levelcraft.network.SimplePacketBase;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.OnDatapackSyncEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkEvent;

import java.util.Map;

@Mod.EventBusSubscriber(modid = LevelCraft.MODID)
public class LockDataLoader extends SimpleJsonResourceReloadListener {

    private static final Gson GSON = new Gson();
    public static final LockDataLoader INSTANCE = new LockDataLoader();

    public LockDataLoader() {
        super(GSON, "xp_lock");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> pObject, ResourceManager pResourceManager, ProfilerFiller pProfiler) {
        for (Map.Entry<ResourceLocation, JsonElement> entry : pObject.entrySet()) {
            JsonElement json = entry.getValue();
            JsonObject recipes = json.getAsJsonObject().get("recipes").getAsJsonObject();
            for (Map.Entry<String, JsonElement> recipe : recipes.entrySet()) {
                String recipeId = recipe.getKey();
                int xp = recipe.getValue().getAsInt();
                LockRegistry.RECIPE_LOCKS.put(ResourceLocation.tryParse(recipeId), xp);
                LevelCraft.LOGGER.info("Loaded lock for recipe " + recipeId + " with xp " + xp);
            }
        }

    }

    @SubscribeEvent
    public static void addReloadListeners(AddReloadListenerEvent event) {
        event.addListener(LockDataLoader.INSTANCE);
    }

    @SubscribeEvent
    public static void onDatapackSync(OnDatapackSyncEvent event) {
        ServerPlayer player = event.getPlayer();
        if (player != null)
            syncTo(player);
        else
            syncToAll();

    }

    public static void syncTo(ServerPlayer player) {
        ModMessages.sendToPlayer(new SyncPacket(), player);
    }

    public static void syncToAll() {
        ModMessages.sendToClients(new SyncPacket());
    }


    public static class SyncPacket extends SimplePacketBase {

        private FriendlyByteBuf buffer;

        public SyncPacket() {
        }

        public SyncPacket(FriendlyByteBuf buffer) {
            this.buffer = buffer;
        }

        @Override
        public void write(FriendlyByteBuf buffer) {
            buffer.writeMap(LockRegistry.RECIPE_LOCKS, FriendlyByteBuf::writeResourceLocation, FriendlyByteBuf::writeInt);
        }

        @Override
        public boolean handle(NetworkEvent.Context context) {
            context.enqueueWork(() -> {
                LockRegistry.RECIPE_LOCKS.clear();
                LockRegistry.RECIPE_LOCKS.putAll(buffer.readMap(FriendlyByteBuf::readResourceLocation, FriendlyByteBuf::readInt));
            });
            return true;
        }

    }

}
