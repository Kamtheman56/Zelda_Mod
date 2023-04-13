package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.entity.custom.projectile.GustProjectile;
import com.kamth.zeldamod.item.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class TornadoRodItem extends Item {
    public TornadoRodItem(Properties pProperties) {
        super(pProperties);
    }



    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (player.isOnGround() && !player.isCrouching()) {
            Vec3 vec3 = player.getDeltaMovement();
            player.resetFallDistance();
            player.setDeltaMovement(vec3.x, 1.3, vec3.z);
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PARROT_FLY, SoundSource.NEUTRAL, 1F, 2F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
            player.getCooldowns().addCooldown(this, 18);
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 20, 0,true,false));
            ItemStack stack = player.getItemInHand(hand);
            stack.hurtAndBreak(2, player, (p_43296_) -> {
                p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                p_43296_.broadcastBreakEvent(EquipmentSlot.OFFHAND);
            });
        }
        player.awardStat(Stats.ITEM_USED.get(this));
        if  (!player.isOnGround()) {
            player.startUsingItem(InteractionHand.MAIN_HAND);
            player.startUsingItem(InteractionHand.OFF_HAND);
        }
        if (player.isOnGround() && player.isCrouching()){
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

    public void onUsingTick(ItemStack stack, LivingEntity livingEntity, int count)
    {
        Player player = (Player) livingEntity;
        Vec3 vec3 = player.getDeltaMovement();
player.resetFallDistance();
       player.setDeltaMovement(vec3.x, -0.27, vec3.z);
     //   stack.hurtAndBreak(1, player, (p_43296_) -> {
     //       p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
     //       p_43296_.broadcastBreakEvent(EquipmentSlot.OFFHAND);
    //    });
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

}


//   player.startUsingItem(InteractionHand.MAIN_HAND);




