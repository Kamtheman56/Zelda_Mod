package com.kamth.zeldamod.item.items.rings;

import com.kamth.zeldamod.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import javax.annotation.Nullable;
import java.util.List;

public class PurpleRingItem extends Item {
    public PurpleRingItem(Properties pProperties) {
        super(pProperties);
        MinecraftForge.EVENT_BUS.addListener(this::onLivingHurtEvent);
    }

    public void onLivingHurtEvent(LivingHurtEvent event){
        if (event.getEntity() instanceof Player) { // Check if the entity taking damage is a Player
            Player player = (Player) event.getEntity();
            ItemStack itemstack = player.getItemInHand(InteractionHand.OFF_HAND);
            if (event.getEntity().getItemBySlot(EquipmentSlot.OFFHAND).getItem() == ModItems.PURPLE_RING.get()) {
                event.setAmount(event.getAmount() / 2f);
                itemstack.hurtAndBreak(7, player, (p_43296_) ->
                    p_43296_.broadcastBreakEvent(EquipmentSlot.OFFHAND));
            }}}
    public boolean isFoil(ItemStack pStack) {
        return true;
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
            components.add(Component.translatable("item.ring_description").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            components.add(Component.translatable("item.purple_ring.description_basic2").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));

    }

}

