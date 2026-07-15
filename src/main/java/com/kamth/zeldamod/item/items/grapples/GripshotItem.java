package com.kamth.zeldamod.item.items.grapples;

import com.kamth.zeldamod.entity.projectile.grapples.Clawshot;
import com.kamth.zeldamod.entity.projectile.grapples.GripShot;
import com.kamth.zeldamod.entity.projectile.grapples.Hookshot;
import com.kamth.zeldamod.item.ZeldaItems;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
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
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;


public class GripshotItem extends ClawshotItem {
    public GripshotItem(Properties pProperties) {
        super(pProperties);
    }



    @Override
    public void releaseUsing(ItemStack stack, Level world, LivingEntity entity, int timeLeft) {
        Player player = (Player) entity;
        ItemStack itemstack = entity.getItemInHand(entity.getUsedItemHand());
        world.playSound(null, player.getX(),player.getY(), player.getZ(), SoundEvents.DISPENSER_LAUNCH, SoundSource.PLAYERS, 1F, -4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!world.isClientSide) {
            GripShot projectile = new GripShot(world, (Player) entity);
            projectile.setOwner(player);
            projectile.setBaseDamage(4);
            projectile.shootFromRotation(player, player.xRotO, player.yRotO, 0.0F, 1.6f, 0f);


                projectile.Properties(itemstack, 45, 14, player.getXRot(), player.getYRot(), 0f, 1.5f * (float) (10 / 10));
                player.getCooldowns().addCooldown(this, 40);

            world.addFreshEntity(projectile);
            itemstack.hurtAndBreak(3, player, (p_43296_) -> {
                p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.translatable("item.zeldamod.gripshot.description_advanced").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.ITALIC));
        } else {
            components.add(Component.translatable("item.zeldamod.gripshot.description_basic").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.ITALIC));
        }
    }

}