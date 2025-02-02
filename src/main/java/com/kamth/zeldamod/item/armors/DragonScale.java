package com.kamth.zeldamod.item.armors;

import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;

import javax.annotation.Nullable;
import java.util.List;

public class DragonScale extends ArmorItem {
    public DragonScale(ArmorMaterial p_40386_, Type type, Properties p_40388_) {
        super(p_40386_, type, p_40388_);
    }
    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if (player.getCooldowns().isOnCooldown(this))
        {
            return;
        }
            Level level = world;
            if (player.isShiftKeyDown() && player.isEyeInFluidType(ForgeMod.WATER_TYPE.get()) && player.getAirSupply() > 10){
                float f7 = player.getYRot();
                float f = player.getXRot();
                float f1 = -Mth.sin(f7 * ((float) Math.PI / 180F)) * Mth.cos(f * ((float) Math.PI / 180F));
                float f2 = -Mth.sin(f * ((float) Math.PI / 180F));
                float f3 = Mth.cos(f7 * ((float) Math.PI / 180F)) * Mth.cos(f * ((float) Math.PI / 180F));
                float f4 = Mth.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
                float f5 = 5.0F * ((1.0F + (float) 1) / 4.0F);
                f1 *= f5 / f4;
                f2 *= f5 / f4;
                f3 *= f5 / f4;
                player.push(f1, f2, f3);
                player.startAutoSpinAttack(30);
                player.getCooldowns().addCooldown(ZeldaItems.DRAGON_SCALE.get(),15);
                if (!player.hasEffect(MobEffects.WATER_BREATHING)){
                    player.setAirSupply(player.getAirSupply()-20);
                }
            }

    }
    public boolean isFoil(ItemStack pStack) {
        return true;
    }
@Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {

   if(Screen.hasShiftDown()) {
        components.add(Component.translatable("armor.dragon_scale.description_advanced").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.ITALIC));
    }
   else components.add(Component.translatable("armor.dragon_scale.description_basic").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.ITALIC));
}}
