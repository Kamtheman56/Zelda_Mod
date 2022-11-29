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

   // @Override
   // public void onArmorTick(ItemStack stack, Level world, Player player) {
      //  if (!world.isClientSide()) {
        //    if (hasFullSuitOfArmorOn(player)) {
           //     evaluateArmorEffects(player);
           // }
      //  }
   // }

   // private void evaluateArmorEffects(Player player) {
     //   for (Map.Entry<ArmorMaterial, MobEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
        //    ArmorMaterial mapArmorMaterial = entry.getKey();
        //    MobEffectInstance mapStatusEffect = entry.getValue();

       //     if (hasCorrectArmorOn(mapArmorMaterial, player)) {
            //    addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);
      //      }
     //   }
   // }

   // private void addStatusEffectForMaterial(Player player, ArmorMaterial mapArmorMaterial,
                                //            MobEffectInstance mapStatusEffect)

   // {
        //boolean hasPlayerEffect = player.hasEffect(mapStatusEffect.getEffect());

        // if (hasCorrectArmorOn(mapArmorMaterial, player) && !hasPlayerEffect) {
        // player.addEffect(new MobEffectInstance(mapStatusEffect.getEffect(),
        //    mapStatusEffect.getDuration(), mapStatusEffect.getAmplifier()));

        //if(new Random().nextFloat() > 0.6f) { // 40% of damaging the armor! Possibly!
        //    player.getInventory().hurtArmor(DamageSource.MAGIC, 1f, new int[]{0, 1, 2, 3});
        //}
        // }
        // }

        // private boolean hasFullSuitOfArmorOn(Player player) {
        //  ItemStack boots = player.getInventory().getArmor(0);


        //   return !boots.isEmpty();
        // }

        // private boolean hasCorrectArmorOn(ArmorMaterial material, Player player) {
        //   ArmorItem boots = ((ArmorItem) player.getInventory().getArmor(0).getItem());


        //   return boots.getMaterial() == material;
        //}

        public void onPlayerSwim (PlayerSwimEvent event){

        if (event.getEntity().getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.HEAVY_BOOTS.get()) {

            event.setResult(Event.Result.DENY);


        }
    }

        public void LivingFallEvent (LivingFallEvent event){
        if (event.getEntity().getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.HEAVY_BOOTS.get()) {
            if (event.getEntity().isEyeInFluidType(ForgeMod.WATER_TYPE.get())) {
                event.setCanceled(true);

            }


        }

    }
    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {

        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, 1, true, false));
        //  if (player.isUnderWater()){
        //   player.removeEffect(MobEffect.byId(MobEffect.getId(MobEffects.MOVEMENT_SLOWDOWN)));
        //  }

        {
            Level level = world;

            if (player.isCrouching()){

            }

            else if (level.getBlockState(player.getOnPos()).getBlock() == Blocks.GLASS) {
                level.destroyBlock(player.getOnPos(), false);
                {

                }


            }
            if (player.isCrouching()){

            }

            else if (level.getBlockState(player.getOnPos()).getBlock() == Blocks.ICE) {
                level.destroyBlock(player.getOnPos(), true);
                return;


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



