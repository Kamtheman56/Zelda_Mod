package com.kamth.zeldamod.item.items;

import com.google.common.collect.ImmutableMap;
import com.kamth.zeldamod.item.ModItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SandBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Map;
import java.util.Random;

public class SandWandItem extends Item {
    public SandWandItem(Properties pProperties) {
        super(pProperties);
    }


    @Override

    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        BlockHitResult ray = raytrace1(world, player, ClipContext.Fluid.NONE);
        BlockPos lookPos = ray.getBlockPos().relative(ray.getDirection());

        ItemStack stack = player.getItemInHand(hand);
        stack.setDamageValue(stack.getDamageValue() + 2);
        if (stack.getDamageValue() >= stack.getMaxDamage()) stack.setCount(0);
        world.setBlockAndUpdate(lookPos, Blocks.SAND.defaultBlockState());
        world.addParticle(ParticleTypes.CLOUD, lookPos.getX() , lookPos.getY() + 1.5, lookPos.getZ() + .3,  0.0D, 0.0D, 0.0D);
        world.addParticle(ParticleTypes.CLOUD, lookPos.getX(), lookPos.getY() + 1.7, lookPos.getZ() + .5, 0.0D, 0.0D, 0.0D);
        player.getCooldowns().addCooldown(this, 20);
        world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.SAND_PLACE, SoundSource.PLAYERS, 1.0F, 1.0F);
        return super.use(world, player, hand);
    }


    protected static BlockHitResult raytrace1(Level pLevel, Player pPlayer, ClipContext.Fluid pFluidMode) {
        double range = 2;
        float f = pPlayer.getXRot();
        float f1 = pPlayer.getYRot();
        Vec3 vec3 = pPlayer.getEyePosition(1.0f);
        float f2 = Mth.cos(-f1 * ((float) Math.PI / 180F) - (float) Math.PI);
        float f3 = Mth.sin(-f1 * ((float) Math.PI / 180F) - (float) Math.PI);
        float f4 = -Mth.cos(-f * ((float) Math.PI / 180F));
        float f5 = Mth.sin(-f * ((float) Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d0 = pPlayer.getReachDistance();
        Vec3 vec31 = vec3.add((double) f6 * d0, (double) f5 * d0, (double) f7 * range);
        return pLevel.clip(new ClipContext(vec3, vec31, ClipContext.Block.OUTLINE, pFluidMode, pPlayer));
    }

    @Override
    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        if (pState.is(BlockTags.SAND)) {
            return 10.0F;
        }
       else return  1f;
    }}





 //  if (raytraceresult.getType() == BlockHitResult.Type.BLOCK && blockstate.getBlock() instanceof SandBlock && !player.level.isClientSide) {

      //     worldIn.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.CHICKEN_EGG, SoundSource.PLAYERS, 1.0F, 1.0F);
       //    worldIn.setBlockAndUpdate(blockpos, Blocks.AIR.defaultBlockState());
       //    return InteractionResult.sidedSuccess(context.getLevel().isClientSide());
       //    }






