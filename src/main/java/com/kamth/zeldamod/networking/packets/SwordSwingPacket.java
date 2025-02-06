package com.kamth.zeldamod.networking.packets;

import com.kamth.zeldamod.item.modifiers.swing.SwingActionItem;
import com.kamth.zeldamod.networking.ZeldaNetworking;
import com.kamth.zeldamod.networking.packets.base_packet.AbstractC2SPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraftforge.network.NetworkEvent;

public final class SwordSwingPacket extends AbstractC2SPacket<SwordSwingPacket> {

    public SwordSwingPacket() {}

    @Override
    public void encode(FriendlyByteBuf buf) {}

    @Override
    public SwordSwingPacket decode(FriendlyByteBuf buf) {
        return new SwordSwingPacket();
    }

    @Override
    protected void runOnServer(NetworkEvent.Context context, MinecraftServer client, ServerPlayer player, ServerLevel level) {
        context.enqueueWork(() -> {

            Item item = player.getMainHandItem().getItem();

            if (item instanceof SwingActionItem actionItem) {
                actionItem.swingSword(level, player);
            }
        });
    }

    public static void sendPacket() {
        ZeldaNetworking.sendToServer(new SwordSwingPacket());
    }
}
