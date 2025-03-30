package com.kamth.zeldamod.item.items.weapons.swords;

import com.kamth.zeldamod.entity.projectile.magic.SwordBeam;
import com.kamth.zeldamod.util.interfaces.item.ISwingActionItem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class FierceDeitySwordItem extends SwordItem implements ISwingActionItem {
    public FierceDeitySwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            components.add(Component.translatable("item.zeldamod.fierce_sword.description_advanceda").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));
            components.add(Component.translatable("item.zeldamod.fierce_sword.description_advancedb").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC));

        } else {
            components.add(Component.translatable("item.zeldamod.fierce_sword.description_basic").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));
        }
    }

    @Override
    public void swingSword(Level world, Player player) {
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS,
                0.8f, 5 / (world.getRandom().nextFloat() * 0.4f + 0.8f));
        SwordBeam projectile = new SwordBeam(world, player);
        projectile.setOwner(player);
        projectile.setPos(player.getEyePosition().add(0, -0.1, 0));
        projectile.shootFromRotation(player, player.xRotO, player.yRotO, 0, 1.6f, 1.8f);
        projectile.setBaseDamage(7);
        world.addFreshEntity(projectile);
    }

    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    public boolean isFoil(ItemStack pStack) {
        return true;
    }

    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return true;
    }
}

