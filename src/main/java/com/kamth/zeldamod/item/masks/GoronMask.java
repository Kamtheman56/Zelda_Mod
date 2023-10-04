package com.kamth.zeldamod.item.masks;

import be.florens.expandability.api.forge.PlayerSwimEvent;
import com.kamth.zeldamod.effect.ModEffects;
import com.kamth.zeldamod.item.ModItems;
import net.minecraft.ChatFormatting;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageTypes;
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
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.Event;

import javax.annotation.Nullable;
import java.util.List;

public class GoronMask extends ArmorItem {
    public GoronMask(ArmorMaterial p_40386_, Type type, Properties p_40388_) {
        super(p_40386_, type, p_40388_);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerSwim);
        MinecraftForge.EVENT_BUS.addListener(this::onLivingHurtEvent);
    }
//Mask that gives combat prowess and nether exploration
public void onLivingHurtEvent(LivingHurtEvent event){
    if (event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.GORON_MASK.get()) {
        if (!event.getSource().is(DamageTypeTags.BYPASSES_ARMOR)) {
            event.setAmount(event.getAmount() / 2);
        }
        if (event.getSource().is(DamageTypes.DROWN)) {
            event.setAmount(event.getAmount() * 2);
        }
        if (event.getSource().is(DamageTypes.FALL)) {
            event.setAmount(event.getAmount() / 2F);
        }
    }}

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {

        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 10, 0, true, false));
        player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 10,0,true,false));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 10,1,true,false));

        if (!player.isSprinting()){
            player.addEffect(new MobEffectInstance(ModEffects.GORON.get(), 60,0,true,true));
        }
            if (player.isOnFire()){
                player.setRemainingFireTicks(0);}

            if (world.getBlockState(player.getOnPos()).is(Tags.Blocks.GLASS)) {
                world.destroyBlock(player.getOnPos(), false);}

            if (world.getBlockState(player.getOnPos()).getBlock() == Blocks.ICE ) {
                world.destroyBlock(player.getOnPos(), false);}
            if (player.isCrouching() && !player.onGround()){
                player.setDeltaMovement(0,-1,0);
            }
            if (player.isSprinting() && !player.isEyeInFluidType(ForgeMod.WATER_TYPE.get()) && !player.hasEffect(ModEffects.GORON.get())) {
                    player.causeFoodExhaustion(.15f);
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 25,6,true,false));}
    }

    public void onPlayerSwim(PlayerSwimEvent event) {

        if (event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.GORON_MASK.get()) {
            event.setResult(Event.Result.DENY);}
    }
@Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
    if (Screen.hasShiftDown()) {
        components.add(Component.literal("Boost, stomp, and punch!").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
    } else {
        components.add(Component.literal("Become grounded!").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
}}}
