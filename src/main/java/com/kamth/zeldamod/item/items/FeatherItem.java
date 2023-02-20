package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.item.ModItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FeatherItem extends Item {
    public FeatherItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int pSlotId, boolean pIsSelected) {
        if (!world.isClientSide){
            if (entity instanceof Player){
                Player player = (Player)entity;

            }
            if ( entity instanceof Player && ((Player)entity).getOffhandItem().getItem() == ModItems.RED_EMERALD.get()){
                ((Player) entity).addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 10, 0, true, false));

            }
            if ( entity instanceof Player && ((Player)entity).getMainHandItem().getItem() == ModItems.RED_EMERALD.get()){
                ((Player) entity).addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 10, 0, true, false));}}
}}
