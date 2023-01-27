package com.kamth.zeldamod.item.masks;


import be.florens.expandability.api.forge.LivingFluidCollisionEvent;
import com.kamth.zeldamod.effect.ModEffects;
import com.kamth.zeldamod.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
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
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.commons.lang3.tuple.ImmutableTriple;

import javax.annotation.Nullable;
import java.util.List;


public class DekuMask extends ArmorItem {
    public DekuMask(ArmorMaterial p_40386_, EquipmentSlot p_40387_, Properties p_40388_) {
        super(p_40386_, p_40387_, p_40388_);

        MinecraftForge.EVENT_BUS.addListener(this::onLivingFluidCollisionEvent);
        MinecraftForge.EVENT_BUS.addListener(this::onLivingHurtEvent);


    }
//Mask that gives better platforming abilities


    public int timer = 0;

    public void onLivingFluidCollisionEvent(LivingFluidCollisionEvent event) {
        if (event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.DEKU_MASK.get()) {
            if (event.getEntity().isSprinting()) {
    if (event.getEntity().hasEffect(ModEffects.DEKU.get()))
                    event.setResult(Event.Result.ALLOW);
    event.getEntity().shouldDiscardFriction();
                }}}


    public void onLivingHurtEvent(LivingHurtEvent event) {
        if (event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.DEKU_MASK.get()) {
            if (event.getSource() == DamageSource.LAVA) {
                event.setAmount(event.getAmount() * 4);
            }
            if (event.getSource() == DamageSource.ON_FIRE) {
                event.setAmount(event.getAmount() * 2);
            }
            if (event.getSource() == DamageSource.IN_FIRE) {
                event.setAmount(event.getAmount() * 2);
            }
            if (event.getSource() == DamageSource.SWEET_BERRY_BUSH) {
                event.setCanceled(true);
            }
            if (event.getSource() == DamageSource.CACTUS) {
                event.setCanceled(true);
            }
            if (event.getSource() == DamageSource.DROWN) {
                event.setAmount(event.getAmount() * 3);
            }
        }
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {



        player.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.SLOW_FALLING, 10, 1, true, false, false));
        if (player.isCrouching()) {
            player.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.JUMP, 10, 2, true, false));
        }


        {
            //Simulate the jumping flowers from MM. Replace with modded flower later
            Level level = world;


            if (level.getBlockState(player.getOnPos()).getBlock() == Blocks.HAY_BLOCK) {
                if (player.isCrouching()) {

                    player.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.JUMP, 10, 6, true, false));


                }

            }


            if (level.getBlockState(player.getOnPos()).getBlock() == Blocks.LILY_PAD) {
                player.addEffect((new MobEffectInstance(ModEffects.DEKU.get(), 40, 0, true, false)));

            }
            //if not standing on water or in water, get effect. Else get nothing
            if (player.isOnGround() && level.getBlockState(player.getOnPos().below(1)).getBlock() != Blocks.WATER) {

                player.addEffect(((new MobEffectInstance(ModEffects.DEKU.get(), 40, 0, true, false))));
            }
            else if (player.isSwimming()){
                player.removeEffect(ModEffects.DEKU.get());
            }


        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            components.add(Component.literal("Excel at platforming").withStyle(ChatFormatting.GREEN).withStyle(ChatFormatting.ITALIC));
        } else {
            components.add(Component.literal("Become one with nature!").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));

        }
    }}















