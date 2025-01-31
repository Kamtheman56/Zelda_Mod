package com.kamth.zeldamod.item.items.movement;

import com.kamth.zeldamod.entity.projectile.magic.GustProjectile;
import com.kamth.zeldamod.item.items.TooltipItem;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;

public class DekuLeafItem extends TooltipItem {
    public DekuLeafItem(Properties pProperties) {
        super(pProperties, Style.EMPTY.withColor(ChatFormatting.GREEN).withItalic(true));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (player.onGround()) {
            GustProjectile gust = new GustProjectile(world,player);
            gust.shootFromRotation(player, player.getXRot(), player.getYRot(), 1, 1.25F, 0.9F);
            world.addFreshEntity(gust);
            player.getCooldowns().addCooldown(this, 20);
            world.playSound(null,player.getOnPos(),ModSounds.LEAF.get(), SoundSource.PLAYERS, 1F, 1F / (world.getRandom().nextFloat() * 0.3F + 0.8F));
            return InteractionResultHolder.success(itemstack);
        }
        player.awardStat(Stats.ITEM_USED.get(this));
        if  (!player.onGround() && !player.onClimbable()) {
            player.startUsingItem(InteractionHand.MAIN_HAND);
            player.startUsingItem(InteractionHand.OFF_HAND);
        world.playSound(null,player.getOnPos(),ModSounds.LEAF.get(), SoundSource.PLAYERS, 1F, 2.5F / (world.getRandom().nextFloat() * 0.3F + 0.8F));
        }

        return super.use(world, player, hand);}
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BLOCK;
    }
    @Override
    public void onUseTick(Level pLevel, LivingEntity livingEntity, ItemStack pStack, int pRemainingUseDuration) {
        Player player = (Player) livingEntity;
        Vec3 vec3 = player.getDeltaMovement();
        player.resetFallDistance();
        player.setDeltaMovement(vec3.x, -0.05, vec3.z);
        player.setDeltaMovement(player.getDeltaMovement().add(player.getDeltaMovement().multiply(.07D, 0.0D, .07D)));
    }
    @Override
    public int getUseDuration(ItemStack itemStack)
    {
        return 72000;
    }
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pStack.hurtAndBreak(3, pAttacker, (p_43296_) -> {
            p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        return true;
    }

}






