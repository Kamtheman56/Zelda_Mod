package com.kamth.zeldamod.networking;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.networking.packets.base_packet.AbstractBasePacket;
import com.kamth.zeldamod.networking.packets.SwordSwingPacket;
import com.kamth.zeldamod.networking.packets.base_packet.AbstractC2SPacket;
import com.kamth.zeldamod.networking.packets.base_packet.AbstractS2CPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.Supplier;

public class ZeldaNetworking {

    private static int packetId = 0;

    public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(ZeldaMod.MOD_ID, "messages"))
            .networkProtocolVersion(() -> "1.0")
            .clientAcceptedVersions(s -> true)
            .serverAcceptedVersions(s -> true)
            .simpleChannel();

    public static void registerPackets() {
        registerPacket(SwordSwingPacket.class, SwordSwingPacket::new);
    }

    public static <T extends AbstractBasePacket<T>> void registerPacket(Class<T> packetClass, Supplier<T> supplier) {
        CHANNEL.registerMessage(packetId++, packetClass, T::encodePacket, buf -> T.decodePacket(supplier.get(), buf), T::handlePacket);
    }

    public static void sendToClient(ServerPlayer player, AbstractBasePacket<?> packet) {
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), packet);
    }

    public static void sendToServer(AbstractBasePacket<?> packet) {
        CHANNEL.sendToServer(packet);
    }
}
