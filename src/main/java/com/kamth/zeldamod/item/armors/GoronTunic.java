package com.kamth.zeldamod.item.armors;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Random;

public class GoronTunic extends ArmorItem {
    public GoronTunic(ArmorMaterial p_40386_, Type type, Properties p_40388_) {
        super(p_40386_, type, p_40388_);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {

        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 10, 0, true, false, false));
        player.removeEffect(MobEffects.POISON);
        //damages the armor if they're on fire only
        if(player.isOnFire()) {
            if (world.random.nextInt(20) == 0) {
                this.damageItem(stack, 4, player,  (p_43296_) -> {
                    p_43296_.broadcastBreakEvent(EquipmentSlot.CHEST);
                } );
            }
    }

        if (player.isOnFire()){
            player.setRemainingFireTicks(0);

    }
    }
}