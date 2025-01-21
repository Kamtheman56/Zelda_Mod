package com.kamth.zeldamod.networking;

import com.kamth.zeldamod.ZeldaMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ZeldaNetworking {

    private static int packetId = 0;

    public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(ZeldaMod.MOD_ID, "messages"))
            .networkProtocolVersion(() -> "1.0")
            .clientAcceptedVersions(s -> true)
            .serverAcceptedVersions(s -> true)
            .simpleChannel();

    private static int id() {
        return packetId++;
    }

    public static void registerPackets() {
        CHANNEL.registerMessage(
                id(),
                SwordSwingPacket.class,
                SwordSwingPacket::encode,
                SwordSwingPacket::decode,
                SwordSwingPacket::handle
        );
    }

    public static void sendSwordSwingPacket() {
        CHANNEL.sendToServer(new SwordSwingPacket());
    }
}
