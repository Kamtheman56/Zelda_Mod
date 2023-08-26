package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.entity.custom.projectile.FireProjectile;
import com.kamth.zeldamod.entity.custom.projectile.SandProjectile;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class SandWandItem extends Item {
    public SandWandItem(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        player.getCooldowns().addCooldown(this, 38);
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SAND_FALL, SoundSource.NEUTRAL, 1F, -2F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        ItemStack stack = player.getItemInHand(hand);
        stack.setDamageValue(stack.getDamageValue() + 2);
        if (stack.getDamageValue() >= stack.getMaxDamage()) stack.setCount(0);
      SandProjectile projectile = new SandProjectile(world, player);
       projectile.shootFromRotation(player, player.xRotO, player.yRotO, 0.0F, 1.4f, 0f);
        world.addFreshEntity(projectile);
        player.awardStat(Stats.ITEM_USED.get(this));

        return super.use(world, player, hand);
    }


    @Override
    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        if (pState.is(BlockTags.SAND)) {
            return 12.0F;
        }
       else return  1f;
    }}










