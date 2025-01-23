package com.kamth.zeldamod.networking.packets.base_packet;

import com.kamth.zeldamod.networking.ZeldaNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public abstract class AbstractBasePacket<T extends AbstractBasePacket<T>> {

    /**
     * Send Variables via packet
     * @param buf - used to add the variables to the packet (use buf.writeXYZ)
     */

    public abstract void encode(FriendlyByteBuf buf);

    /**
     * read Variables from packet
     * @param buf - used to read the variables from the packet (use buf.readXYZ)
     */

    public abstract T decode(FriendlyByteBuf buf);

    /**
     * Stuff here is run in the destination
     * @param context
     */
    protected abstract void handle(NetworkEvent.Context context);

    /**
     * Send Packet
     */

    /**
     * Makes sure that the packet is being sent to the proper destination, otherwise, will crash game
     */
    public final void checkDirection(NetworkDirection currentDirection, NetworkDirection targetDirection) {
        if (currentDirection != targetDirection) {
            throw new IllegalStateException("Networking Packet sent from wrong side: " + currentDirection);
        }
    }


    public static void encodePacket(AbstractBasePacket<?> packet, FriendlyByteBuf buf) {
        packet.encode(buf);
    }

    public static <T extends AbstractBasePacket<T>> T decodePacket(T packet, FriendlyByteBuf buf) {
        return packet.decode(buf);
    }

    public static void handlePacket(AbstractBasePacket<?> packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        packet.handle(context);
        context.setPacketHandled(true);
    }
}
