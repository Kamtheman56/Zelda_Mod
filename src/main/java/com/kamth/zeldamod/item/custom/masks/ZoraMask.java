package com.kamth.zeldamod.item.custom.masks;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;

import java.util.Map;


import java.util.Map;

public class ZoraMask extends ArmorItem {


    public ZoraMask(ArmorMaterial p_40386_, EquipmentSlot p_40387_, Properties p_40388_) {
        super(p_40386_, p_40387_, p_40388_);
    }
    //this adds effects that do not create particles.
    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {

        player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 10, 10, true, false));
        player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 10,0,true,false));
        player.addEffect(new MobEffectInstance(MobEffects.CONDUIT_POWER, 10,0,true,false));
    }}