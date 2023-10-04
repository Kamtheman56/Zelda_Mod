package com.kamth.zeldamod.item.armors;

import be.florens.expandability.api.forge.PlayerSwimEvent;
import com.google.common.collect.ImmutableMap;
import com.kamth.zeldamod.item.ModItems;
import com.kamth.zeldamod.item.custom.ModArmorMaterials;
import com.kamth.zeldamod.item.custom.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.DamageTypeTags;
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
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.Event;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;



public class HeavyBoots extends ArmorItem {
    private static final Map<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
                    .put(ModArmorMaterials.ZELDAH,
                            new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, 2,false,false)).build();


    public HeavyBoots(ArmorMaterial material, Type type, Properties settings) {
        super(material, type, settings);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerSwim);
        MinecraftForge.EVENT_BUS.addListener(this::LivingFallEvent);
        MinecraftForge.EVENT_BUS.addListener(this::onLivingHurtEvent);
    }
    public void onLivingHurtEvent(LivingHurtEvent event){
        if (event.getEntity().getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.HEAVY_BOOTS.get()) {
            if (!event.getSource().is(DamageTypeTags.IS_LIGHTNING)) {
            event.getEntity().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 60, 10, true, true));
            }}}


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
        player.removeEffect(MobEffects.SLOW_FALLING);

        if (player.isEyeInFluidType(ForgeMod.WATER_TYPE.get())){
            player.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
        }
        {
            Level level = world;

             if (level.getBlockState(player.getOnPos()).is(Tags.Blocks.GLASS)  && !player.isCrouching()) {
                level.destroyBlock(player.getOnPos(), false);}

             if (level.getBlockState(player.getOnPos()).getBlock() == Blocks.ICE && !player.isCrouching()) {
                level.destroyBlock(player.getOnPos(), false);
              }
            if (level.getBlockState(player.getOnPos()).is(ModTags.Blocks.HEAVY)) {
                player.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 10, 1, true, false));
            }

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



