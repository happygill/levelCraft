package com.happysg.levelcraft.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent.Context;

public abstract class SimplePacketBase {

    public abstract void write(FriendlyByteBuf buffer);

    public abstract boolean handle(Context context);

}
