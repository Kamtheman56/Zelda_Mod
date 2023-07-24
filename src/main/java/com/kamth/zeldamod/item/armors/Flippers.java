package com.kamth.zeldamod.item.armors;

import be.florens.expandability.api.forge.PlayerSwimEvent;
import com.google.common.collect.ImmutableMap;
import com.kamth.zeldamod.item.ModItems;
import com.kamth.zeldamod.item.custom.ModArmorMaterials;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.Event;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class Flippers extends ArmorItem {
    private static final AttributeModifier FLIPPERS = new AttributeModifier(UUID.fromString("90998b0e-4188-403a-b3e5-1987d251d350"), "zeldamod:flipper", 1.5, AttributeModifier.Operation.ADDITION);


    public Flippers(ArmorMaterial material, EquipmentSlot slot, Properties settings) {
        super(material, slot, settings);

        MinecraftForge.EVENT_BUS.addListener(this::onPlayerTick);
    }
    private void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.START) {
            return;}
        AttributeInstance flipper = event.player.getAttribute(ForgeMod.SWIM_SPEED.get());
        if (event.player.isSprinting() && event.player.getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.FLIPPERS.get()) {}
        if (!flipper.hasModifier(FLIPPERS) && event.player instanceof Player && event.player.getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.FLIPPERS.get()) {
            flipper.addTransientModifier(FLIPPERS);}


        else {

            if (flipper.hasModifier(FLIPPERS)) {
                flipper.removeModifier(FLIPPERS);}}}

}



