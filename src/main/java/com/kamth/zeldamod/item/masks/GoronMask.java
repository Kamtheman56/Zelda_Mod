package com.kamth.zeldamod.item.masks;

import be.florens.expandability.api.forge.PlayerSwimEvent;
import com.kamth.zeldamod.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
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
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fluids.FluidType;

import javax.annotation.Nullable;
import java.util.List;

public class GoronMask extends ArmorItem {
    public GoronMask(ArmorMaterial p_40386_, EquipmentSlot p_40387_, Properties p_40388_) {
        super(p_40386_, p_40387_, p_40388_);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerSwim);
        MinecraftForge.EVENT_BUS.addListener(this::onLivingHurtEvent);
    }
//Mask that gives combat prowess and nether exploration
public void onLivingHurtEvent(LivingHurtEvent event){
    if (event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.GORON_MASK.get()) {
        if (event.getSource() == DamageSource.FALLING_STALACTITE) {
            event.setAmount(event.getAmount() * 0);}
        if (event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.GORON_MASK.get()){
            if (event.getSource() == DamageSource.DROWN) {
                event.setAmount(event.getAmount() * 2);}
        }}}

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {

        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 10, 0, true, false));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 10,1,true,false));
        player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 10,0,true,false));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 10,1,true,false));
      //  player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10,0,true,false));

            if (player.isOnFire()){
                player.setRemainingFireTicks(0);}



//Maybe add ice physics when running
// when springing have thorns armor effect


            Level level = world;



            if (level.getBlockState(player.getOnPos()).getMaterial() == Material.GLASS && !player.isCrouching()) {
                level.destroyBlock(player.getOnPos(), false);}

            if (level.getBlockState(player.getOnPos()).getBlock() == Blocks.ICE && !player.isCrouching()) {
                level.destroyBlock(player.getOnPos(), false);}
            if (player.isCrouching() && !player.isOnGround()){
                player.setDeltaMovement(0,-1,0);
            }
            if (player.isSprinting() && !player.isEyeInFluidType(ForgeMod.WATER_TYPE.get())) {
                player.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 25,5,true,false));}


    }

    public void onPlayerSwim(PlayerSwimEvent event) {

        if (event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.GORON_MASK.get()) {
            event.setResult(Event.Result.DENY);



        }
    }
@Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.literal("Contains the spirit of a Goron").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));

}}
