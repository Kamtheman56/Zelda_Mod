package com.kamth.zeldamod.item.masks;

import com.kamth.zeldamod.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;

public class NightMask extends ArmorItem {
    public NightMask(ArmorMaterial pMaterial, Type type, Properties pProperties) {
        super(pMaterial, type, pProperties);
    }
    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
if (player.isSleeping()){
    player.displayClientMessage(Component.translatable("Nightmask").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD), true);
    player.stopSleeping();
    if (player.getCooldowns().isOnCooldown(ModItems.NIGHT_MASK.get()))
    {
        return;
    }
    Vec3 explosionPos = player.getEyePosition(1.0F).add(player.getLookAngle().multiply(0.5D, 0.5D, 0.5D));
    player.level().explode(player, explosionPos.x, explosionPos.y, explosionPos.z, .9F, Level.ExplosionInteraction.NONE);
player.getCooldowns().addCooldown(ModItems.NIGHT_MASK.get(),250);
player.hurt(player.damageSources().magic(), 9);

}

if (world.isNight()){
    player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 600, 0, true, false));}

    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.literal("You shouldn't try to sleep").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));

    }

}
