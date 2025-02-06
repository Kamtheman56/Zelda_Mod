package com.kamth.zeldamod.item.items.rods;

import com.kamth.zeldamod.block.ZeldaBlocks;
import com.kamth.zeldamod.entity.projectile.magic.GustProjectile;
import com.kamth.zeldamod.item.items.TooltipItem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;

public class TornadoRodItem extends TooltipItem {
    public TornadoRodItem(Properties pProperties) {
        super(pProperties,  "igreen", true);
    }



    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (player.onGround() && !player.isCrouching()) {
            Vec3 vec3 = player.getDeltaMovement();
            player.resetFallDistance();
            player.setDeltaMovement(vec3.x, 1.3, vec3.z);
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PARROT_FLY, SoundSource.NEUTRAL, 1F, 2F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
            player.getCooldowns().addCooldown(this, 18);
            double particleX = player.getX() + (player.getRandom().nextBoolean() ? 0.1D : 0);
            double particleY = player.getY() + player.getRandom().nextFloat() * 0 + 1.2D;
            double particleZ = player.getZ() + (player.getRandom().nextBoolean() ? +0.8D : 0);
            player.level().addParticle(ParticleTypes.CLOUD, particleX, particleY, particleZ, 0, 0, 0);
            player.level().addParticle(ParticleTypes.CLOUD, particleX+.2, particleY, particleZ, 0, 0, 0);
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 20, 0,true,false));
            ItemStack stack = player.getItemInHand(hand);
            stack.hurtAndBreak(2, player, (p_43296_) -> {
                p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                p_43296_.broadcastBreakEvent(EquipmentSlot.OFFHAND);
            });
        }
        player.awardStat(Stats.ITEM_USED.get(this));
        if  (!player.onGround()) {
            player.startUsingItem(InteractionHand.MAIN_HAND);
            player.startUsingItem(InteractionHand.OFF_HAND);
        }
        if (player.onGround() && player.isCrouching()){
            ItemStack stack = player.getItemInHand(hand);
            stack.setDamageValue(stack.getDamageValue() + 4);
            if (stack.getDamageValue() >= stack.getMaxDamage()) stack.setCount(0);
            GustProjectile bombEntity = new GustProjectile(world,player);
            bombEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 1, .5F, 0.7F);
            world.addFreshEntity(bombEntity);
            player.getCooldowns().addCooldown(this, 25);
        }
        return super.use(world, player, hand);
    }
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BLOCK;
    }
    @Override
    public void onUseTick(Level pLevel, LivingEntity livingEntity, ItemStack pStack, int pRemainingUseDuration) {
        Player player = (Player) livingEntity;
        Vec3 vec3 = player.getDeltaMovement();
player.resetFallDistance();
       player.setDeltaMovement(vec3.x, -0.27, vec3.z);
        }

    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pStack.hurtAndBreak(6, pAttacker, (p_43296_) -> {
            p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        return true;
    }
    @Override
    public int getUseDuration(ItemStack itemStack)
    {
        return 72000;
    }
    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);

        if (blockstate.is(ZeldaBlocks.COURAGE_FLAME.get())) {
            if (!(pContext.getItemInHand().getAllEnchantments().containsKey(Enchantments.UNBREAKING))){
                pContext.getItemInHand().enchant(Enchantments.MENDING,1);
                pContext.getLevel().playSound(pContext.getPlayer(),blockpos,SoundEvents.AMETHYST_BLOCK_RESONATE,SoundSource.BLOCKS, 1,1);
            }  return InteractionResult.SUCCESS;}
        else return InteractionResult.PASS;
    }
}


//   player.startUsingItem(InteractionHand.MAIN_HAND);




