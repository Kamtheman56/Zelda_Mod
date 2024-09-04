package com.kamth.zeldamod.item.armors;

import be.florens.expandability.api.forge.LivingFluidCollisionEvent;
import com.kamth.zeldamod.effect.ModEffects;
import com.kamth.zeldamod.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;

import javax.annotation.Nullable;
import java.util.List;

public class FireShield extends ArmorItem {
    public FireShield(ArmorMaterial p_40386_, Type type, Properties p_40388_) {
        super(p_40386_, type, p_40388_);
        MinecraftForge.EVENT_BUS.addListener(this::onLivingFluidCollisionEvent);
    }
    public void onLivingFluidCollisionEvent(LivingFluidCollisionEvent event) {

        if (event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.FIRE_SHIELD.get()) {
            if (event.getEntity().isSprinting()) {
                if (event.getEntity().hasEffect(ModEffects.FIRE.get()))
                    event.setResult(Event.Result.ALLOW);
                event.getEntity().shouldDiscardFriction();
            }

        }}
    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if (player.onGround() && world.getBlockState(player.getOnPos().below(0)).getBlock() != Blocks.LAVA) {
            player.addEffect(((new MobEffectInstance(ModEffects.FIRE.get(), 85, 0, true, true))));
        }
        if (player.getCooldowns().isOnCooldown(ModItems.FIRE_SHIELD.get()))
        {
            return;
        }

        if (player.getRemainingFireTicks() >= 30){
            player.setRemainingFireTicks(0);
            player.playSound(SoundEvents.FIRE_EXTINGUISH);
        }
            Level level = world;
            if (player.isShiftKeyDown() && player.isInLava()){
                float f7 = player.getYRot();
                float f = player.getXRot();
                float f1 = -Mth.sin(f7 * ((float) Math.PI / 180F)) * Mth.cos(f * ((float) Math.PI / 180F));
                float f2 = -Mth.sin(f * ((float) Math.PI / 180F));
                float f3 = Mth.cos(f7 * ((float) Math.PI / 180F)) * Mth.cos(f * ((float) Math.PI / 180F));
                float f4 = Mth.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
                float f5 = 4.5F * ((1.0F + (float) 1) / 4.0F);
                f1 *= f5 / f4;
                f2 *= f5 / f4;
                f3 *= f5 / f4;
                player.push(f1, f2, f3);
                player.startAutoSpinAttack(30);
                player.getCooldowns().addCooldown(this,30);
            }
        if (level.getBlockState(player.getOnPos().below(0)).getBlock() == Blocks.WATER) {
            player.removeEffect(ModEffects.FIRE.get());
        }


    }
    public boolean isFoil(ItemStack pStack) {
        return true;
    }
@Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
            components.add(Component.translatable("armor.fire_shield.description").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));

}}
