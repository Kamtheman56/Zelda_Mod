package com.kamth.zeldamod.item.masks;

import be.florens.expandability.api.forge.PlayerSwimEvent;
import com.kamth.zeldamod.item.ModItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;

public class GoronMask extends ArmorItem {
    public GoronMask(ArmorMaterial p_40386_, EquipmentSlot p_40387_, Properties p_40388_) {
        super(p_40386_, p_40387_, p_40388_);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerSwim);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {

        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 10, 0, true, false));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 10,1,true,false));
        player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 10,1,true,false));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 10,1,true,false));
    }

    public void onPlayerSwim(PlayerSwimEvent event) {

        if (event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.GORON_MASK.get()) {
            event.setResult(Event.Result.DENY);



        }
    }






}
