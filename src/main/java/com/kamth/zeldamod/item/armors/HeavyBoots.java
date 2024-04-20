package com.kamth.zeldamod.item.armors;

import be.florens.expandability.api.forge.PlayerSwimEvent;
import com.kamth.zeldamod.item.ModItems;
import com.kamth.zeldamod.item.custom.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.Event;

import javax.annotation.Nullable;
import java.util.List;



public class HeavyBoots extends ArmorItem {
    public HeavyBoots(ArmorMaterial material, Type type, Properties settings) {
        super(material, type, settings);
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
        player.removeEffect(MobEffects.SLOW_FALLING);
        player.removeEffect(MobEffects.JUMP);

        if (player.isEyeInFluidType(ForgeMod.WATER_TYPE.get())){
            player.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
        }
        {
            Level level = world;
            if (level.getBlockState(player.getOnPos()).is(ModTags.Blocks.HEAVY2)  && !player.isCrouching()) {
                level.destroyBlock(player.getOnPos(), false);}
            if (level.getBlockState(player.getOnPos()).is(ModTags.Blocks.HEAVY)) {
                player.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 10, 1, true, false));
            }}}
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.literal("These boots look impossible to swim with equipped!").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        } else {
            components.add(Component.literal("Extra Heavy").withStyle(ChatFormatting.GRAY));
        }

        super.appendHoverText(stack, level, components, flag);
    }


}



