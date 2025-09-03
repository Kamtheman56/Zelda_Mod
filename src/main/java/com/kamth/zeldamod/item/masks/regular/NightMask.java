package com.kamth.zeldamod.item.masks.regular;

import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
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
    player.displayClientMessage(Component.translatable("mask.zeldamod.nightmask").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD), true);
    player.stopSleeping();

    if (player.getCooldowns().isOnCooldown(ZeldaItems.NIGHT_MASK.get()))
    {
        return;
    }
    Vec3 explosionPos = player.getEyePosition(1.0F).add(player.getLookAngle().multiply(0.5D, 0.5D, 0.5D));
    player.level().explode(player, explosionPos.x, explosionPos.y, explosionPos.z, .9F, Level.ExplosionInteraction.NONE);
    player.getCooldowns().addCooldown(ZeldaItems.NIGHT_MASK.get(),250);
    player.hurt(player.damageSources().magic(), 9);
}

player.resetStat(Stats.CUSTOM.get(Stats.TIME_SINCE_REST));
if (world.isNight()){
    player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 600, 0, true, false));}
        if (world.getMoonPhase() == 5 && world.isNight()){
            player.removeEffect(MobEffects.BLINDNESS);}
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.translatable("item.zeldamod.night_mask.description").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));

    }

}
