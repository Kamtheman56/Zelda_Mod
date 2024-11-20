package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.entity.custom.projectile.SandProjectile;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class SandStormRodItem extends Item {
    public SandStormRodItem(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        player.getCooldowns().addCooldown(this, 40);
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SAND_FALL, SoundSource.NEUTRAL, 1F, -2F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        ItemStack stack = player.getItemInHand(hand);
        stack.setDamageValue(stack.getDamageValue() + 2);
        if (stack.getDamageValue() >= stack.getMaxDamage()) stack.setCount(0);
      SandProjectile projectile = new SandProjectile(world, player);
       projectile.shootFromRotation(player, player.xRotO, player.yRotO, 0.0F, 1.4f, 0f);
        world.addFreshEntity(projectile);
        SandProjectile projectile2 = new SandProjectile(world, player);
        projectile2.shootFromRotation(player, player.xRotO + 20, player.yRotO, 0.0F, 1.4f, 0f);
        world.addFreshEntity(projectile2);
        player.awardStat(Stats.ITEM_USED.get(this));

        return super.use(world, player, hand);
    }
    public boolean isFoil(ItemStack pStack) {
        return true;
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.translatable("item.sandstorm_rod.description").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));
        }
        super.appendHoverText(stack, level, components, flag);
    }

    @Override
    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        if (pState.is(BlockTags.SAND)) {
            return 15.0F;
        }
       else return  1f;
    }}










