package com.kamth.zeldamod.networking;

import com.kamth.zeldamod.item.items.SwingActionItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SwordSwingPacket {

    public SwordSwingPacket() {}

    public static void encode(SwordSwingPacket packet, FriendlyByteBuf buf) {

    }

    public static SwordSwingPacket decode(FriendlyByteBuf buf) {
        return new SwordSwingPacket();
    }

    public static void handle(SwordSwingPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            Level world = player.level();
            Item item = player.getMainHandItem().getItem();

            if (item instanceof SwingActionItem actionItem) {
                actionItem.swingSword(world, player);
            }
        });
        context.setPacketHandled(true);
    }
}
