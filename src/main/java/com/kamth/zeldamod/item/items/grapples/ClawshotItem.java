package com.kamth.zeldamod.item.items.grapples;

import com.kamth.zeldamod.entity.custom.projectile.Clawshot;
import com.kamth.zeldamod.item.ZeldaItems;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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

import javax.annotation.Nullable;
import java.util.List;




public class ClawshotItem extends Item {
    public ClawshotItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        pPlayer.playSound(ModSounds.CLAWSHOT.get());
            pPlayer.startUsingItem(pHand);
            return InteractionResultHolder.consume(itemstack);
        }

    @Override
    public void releaseUsing(ItemStack stack, Level world, LivingEntity entity, int timeLeft) {
        Player player = (Player) entity;
        ItemStack itemstack = entity.getItemInHand(entity.getUsedItemHand());
        world.playSound(null, player.getX(),player.getY(), player.getZ(), SoundEvents.CROSSBOW_SHOOT, SoundSource.PLAYERS, 1F, -4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!world.isClientSide) {
                Clawshot projectile = new Clawshot(world, (Player) entity);
                projectile.setOwner(player);
                projectile.setBaseDamage(4);
                projectile.shootFromRotation(player, player.xRotO, player.yRotO, 0.0F, 1.6f, 0f);
            if (itemstack.is(ZeldaItems.CLAWSHOT.get())){
                projectile.Properties(itemstack, 25, 12, player.getXRot(), player.getYRot(), 0f, 1.5f * (float) (10 / 10));
                player.getCooldowns().addCooldown(this, 40);
            }
            if (itemstack.is(ZeldaItems.CLAWSHOT_GODDESS.get())){
                projectile.Properties(itemstack, 40, 12, player.getXRot(), player.getYRot(), 0f, 1.5f * (float) (10 / 10));
                player.getCooldowns().addCooldown(this, 40);
            }
                world.addFreshEntity(projectile);
            itemstack.hurtAndBreak(3, player, (p_43296_) -> {
                p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
            }
            }


    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BLOCK;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.translatable("item.clawshot.description_advanced").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
        } else {
            components.add(Component.translatable("item.clawshot.description_basic").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
        }}

}