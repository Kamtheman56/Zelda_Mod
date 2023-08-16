package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.item.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;

public class RedRingItem extends Item {
    public RedRingItem(Properties pProperties) {
        super(pProperties);
        MinecraftForge.EVENT_BUS.addListener(this::onLivingHurtEvent);
    }

    public void onLivingHurtEvent(LivingHurtEvent event){
        if (event.getEntity() instanceof Player) { // Check if the entity taking damage is a Player
            Player player = (Player) event.getEntity();
            ItemStack stack = new ItemStack(ModItems.RED_EMERALD.get());
            if (event.getEntity().getItemBySlot(EquipmentSlot.OFFHAND).getItem() == ModItems.RED_EMERALD.get()) {
             stack.hurtAndBreak(4, player, (p_40665_) -> p_40665_.broadcastBreakEvent(player.getItemInHand(InteractionHand.OFF_HAND).getEquipmentSlot()));
                event.setAmount(event.getAmount() / 2f);
            }
        }
    }
  }

