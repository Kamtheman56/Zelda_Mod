package com.kamth.zeldamod.networking.packets.base_packet;

import com.kamth.zeldamod.networking.ZeldaNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public abstract class AbstractS2CPacket<T extends AbstractBasePacket<T>> extends AbstractBasePacket<T> {

    @Override
    protected final void handle(NetworkEvent.Context context) {
        checkDirection(context.getDirection(), NetworkDirection.PLAY_TO_CLIENT);

        Minecraft client = Minecraft.getInstance();
        LocalPlayer player = client.player;
        ClientLevel level = client.level;
        runOnClient(context, client, player, level);
    }

    protected abstract void runOnClient(NetworkEvent.Context context, Minecraft client, LocalPlayer player, ClientLevel level);
}
