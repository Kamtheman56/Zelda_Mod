package com.kamth.zeldamod.item.items.grapples;

import com.kamth.zeldamod.entity.custom.projectile.Hookshot;
import com.kamth.zeldamod.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;


public class HookshotItem extends Item {
    public HookshotItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand pHand) {
        ItemStack itemstack = player.getItemInHand(pHand);
            Hookshot projectile = new Hookshot(pLevel,player);
        player.getCooldowns().addCooldown(this, 40);
        pLevel.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.CROSSBOW_SHOOT, SoundSource.PLAYERS, 1F, -4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
        projectile.setOwner(player);
       itemstack.hurtAndBreak(3, player, (p_43296_) -> {
            p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
            projectile.shootFromRotation(player, player.xRotO, player.yRotO, 1F, 1.6f,0f);
            if (itemstack.is(ModItems.HOOKSHOT.get())){
                projectile.Properties(itemstack, 15, 10, player.getXRot(), player.getYRot(), 0f, 1.5f * (float) (10 / 10));
            }
        if (itemstack.is(ModItems.LONGSHOT.get())){
            projectile.Properties(itemstack, 30, 10, player.getXRot(), player.getYRot(), 0f, 1.5f * (float) (10 / 10));
        }
            pLevel.addFreshEntity(projectile);
        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.translatable("item.hookshot.description_advanced").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));
        } else {
            components.add(Component.translatable("item.hookshot.description_basic").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));
        }}

}