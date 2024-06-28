package com.kamth.zeldamod.item.armors;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class RocCape extends ArmorItem {
    public RocCape(ArmorMaterial p_40386_, Type type, Properties p_40388_) {
        super(p_40386_, type, p_40388_);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.JUMP, 10, 1, true, false));
        if (player.isFallFlying()){
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 10, 0, true, false));}
player.resetFallDistance();
    }

    @Override
    public boolean canElytraFly(ItemStack stack, net.minecraft.world.entity.LivingEntity entity) {
        return ElytraItem.isFlyEnabled(stack);
    }
    @Override
    public boolean elytraFlightTick(ItemStack stack, net.minecraft.world.entity.LivingEntity entity, int flightTicks) {
        if (!entity.level().isClientSide) {
            int nextFlightTick = flightTicks + 1;
            if (nextFlightTick % 10 == 0) {
                if (nextFlightTick % 30 == 0) {
                    stack.hurtAndBreak(2, entity, e -> e.broadcastBreakEvent(net.minecraft.world.entity.EquipmentSlot.CHEST));
                }
                entity.gameEvent(net.minecraft.world.level.gameevent.GameEvent.ELYTRA_GLIDE);
            }
            if (nextFlightTick == 70){
                return false;
            }
        }
        return true;
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.translatable("armor.roc_cape.description").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));
        }

        super.appendHoverText(stack, level, components, flag);
    }

}