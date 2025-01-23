package com.kamth.zeldamod.networking.packets.base_packet;

import com.kamth.zeldamod.networking.ZeldaNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public abstract class AbstractC2SPacket<T extends AbstractBasePacket<T>> extends AbstractBasePacket<T> {

    @Override
    protected final void handle(NetworkEvent.Context context) {

        checkDirection(context.getDirection(), NetworkDirection.PLAY_TO_SERVER);

        ServerPlayer player = context.getSender();
        if (player == null) {
            throw new IllegalStateException("Packet sent to null Player");
        }

        ServerLevel level = (ServerLevel) player.level();
        MinecraftServer server = player.getServer();

        runOnServer(context, server, player, level);
    }

    protected abstract void runOnServer(NetworkEvent.Context context, MinecraftServer client, ServerPlayer player, ServerLevel level);
}
