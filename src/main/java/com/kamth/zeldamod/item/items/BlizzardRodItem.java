package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.entity.custom.projectile.IceProjectile;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class BlizzardRodItem extends IceRodItem {
    public BlizzardRodItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {

        player.getCooldowns().addCooldown(this, 45);

        ItemStack stack = player.getItemInHand(hand);
        stack.setDamageValue(stack.getDamageValue() + 3);
        if (stack.getDamageValue() >= stack.getMaxDamage()) stack.setCount(0);
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_HURT_FREEZE, SoundSource.NEUTRAL, 1F, -2F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        IceProjectile projectile = new IceProjectile(world, player);
        projectile.shootFromRotation(player, player.xRotO, player.yRotO, 0.0F, 1.8f, .9f);
        world.addFreshEntity(projectile);
        IceProjectile projectile2 = new IceProjectile(world, player);
        IceProjectile projectile3 = new IceProjectile(world, player);
        projectile2.shootFromRotation(player, player.xRotO, player.yRotO + 15, 0.0F, 1.8f,.9f);
        projectile3.shootFromRotation(player, player.xRotO, player.yRotO - 15, 0.0F, 1.8f,.9f);

        world.addFreshEntity(projectile2);
        world.addFreshEntity(projectile3);
        player.awardStat(Stats.ITEM_USED.get(this));
        return super.use(world, player, hand);
    }

    public boolean isFoil(ItemStack pStack) {
        return true;
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.translatable("item.blizzard_rod.description").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.ITALIC));
        }
        super.appendHoverText(stack, level, components, flag);
    }
    @Override
    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        if (pState.is(BlockTags.SNOW)) {
            return 8;
        }
        else return 1;
    }}








