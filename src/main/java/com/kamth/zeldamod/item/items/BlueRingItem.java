package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageTypes;
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

public class BlueRingItem extends Item {
    public BlueRingItem(Properties pProperties) {
        super(pProperties);
        MinecraftForge.EVENT_BUS.addListener(this::onLivingHurtEvent);
    }

    public void onLivingHurtEvent(LivingHurtEvent event){
        if (event.getEntity() instanceof Player) { // Check if the entity taking damage is a Player
            Player player = (Player) event.getEntity();
            ItemStack itemstack = player.getItemInHand(InteractionHand.OFF_HAND);
            if (event.getEntity().getItemBySlot(EquipmentSlot.OFFHAND).getItem() == ModItems.BLUE_RING.get() && !event.getSource().is(DamageTypeTags.BYPASSES_SHIELD)) {
                event.setAmount(event.getAmount() / 2f);
                itemstack.hurtAndBreak(4, player, (p_43296_) ->
                        p_43296_.broadcastBreakEvent(EquipmentSlot.OFFHAND));}}}
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.literal("Offers protection").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        components.add(Component.literal("from physical attacks").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));

    }
  }

