package com.kamth.zeldamod.item.armors;

import be.florens.expandability.api.forge.PlayerSwimEvent;
import com.google.common.collect.ImmutableMap;
import com.google.gson.internal.bind.JsonTreeReader;
import com.kamth.zeldamod.item.ModItems;
import com.kamth.zeldamod.item.custom.ModArmorMaterials;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import net.minecraft.world.level.Level;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;



public class HeavyBoots extends ArmorItem {
    private static final Map<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
                    .put(ModArmorMaterials.ZELDAH,
                            new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, 2,false,false)).build();


    public HeavyBoots(ArmorMaterial material, EquipmentSlot slot, Properties settings) {
        super(material, slot, settings);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerSwim);
        MinecraftForge.EVENT_BUS.addListener(this::LivingFallEvent);

    }



        public void onPlayerSwim (PlayerSwimEvent event){
        if (event.getEntity().getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.HEAVY_BOOTS.get()) {
            event.setResult(Event.Result.DENY);}}

        public void LivingFallEvent (LivingFallEvent event){
        if (event.getEntity().getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.HEAVY_BOOTS.get()) {
            if (event.getEntity().isEyeInFluidType(ForgeMod.WATER_TYPE.get())) {
                event.setCanceled(true);}}}
    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {

        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, 1, true, false));
        player.removeEffect(MobEffects.LEVITATION);
        if (player.isEyeInFluidType(ForgeMod.WATER_TYPE.get())){
            player.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
        }
        {
            Level level = world;

            if (player.isCrouching()){
return;
            }

            else if (level.getBlockState(player.getOnPos()).getBlock() == Blocks.GLASS) {
                level.destroyBlock(player.getOnPos(), false);
            }
            if (player.isCrouching()){}
            else if (level.getBlockState(player.getOnPos()).getBlock() == Blocks.ICE) {
                level.destroyBlock(player.getOnPos(), true);
                return;}
        }
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.literal("These boots look impossible to swim with equipped!").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.ITALIC));
        } else {
            components.add(Component.literal("Press SHIFT for more info").withStyle(ChatFormatting.YELLOW));
        }

        super.appendHoverText(stack, level, components, flag);
    }


}



