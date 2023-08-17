package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.entity.custom.projectile.IceProjectile;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class IceRodItem extends Item {
    public IceRodItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {

    player.getCooldowns().addCooldown(this, 38);

        ItemStack stack = player.getItemInHand(hand);
        stack.setDamageValue(stack.getDamageValue() + 3);
        if (stack.getDamageValue() >= stack.getMaxDamage()) stack.setCount(0);
    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_HURT_FREEZE, SoundSource.NEUTRAL, 1F, -2F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        IceProjectile projectile = new IceProjectile(world, player);
    projectile.shootFromRotation(player, player.xRotO, player.yRotO, 0.0F, 1.3f, .9f);
    world.addFreshEntity(projectile);
    player.awardStat(Stats.ITEM_USED.get(this));
    return super.use(world, player, hand);
    }
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BLOCK;
    }
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pStack.hurtAndBreak(11, pAttacker, (p_43296_) -> {
            p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        return true;
    }
    @Override
    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        if (pState.is(BlockTags.SNOW)) {
            return 6;
        }
       else return 1;
    }}






