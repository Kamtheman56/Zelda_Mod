package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.entity.custom.projectile.BombProjectile;
import com.kamth.zeldamod.entity.custom.projectile.GustProjectile;
import com.kamth.zeldamod.entity.custom.projectile.SwordBeam;
import com.kamth.zeldamod.item.ModItems;
import net.minecraft.core.Direction;
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
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class LeafItem extends Item {
    public LeafItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (player.isOnGround()) {
            GustProjectile bombEntity = new GustProjectile(world,player);
            bombEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 1, 1.25F, 0.9F);
            world.addFreshEntity(bombEntity);
            player.getCooldowns().addCooldown(this, 20);
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BIG_DRIPLEAF_TILT_DOWN, SoundSource.NEUTRAL, 1F, .2F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        }
        player.awardStat(Stats.ITEM_USED.get(this));
        if  (!player.isOnGround()) {
            player.startUsingItem(InteractionHand.MAIN_HAND);
            player.startUsingItem(InteractionHand.OFF_HAND);
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
       player.setDeltaMovement(vec3.x, -0.05, vec3.z);
        player.setDeltaMovement(player.getDeltaMovement().add(player.getDeltaMovement().multiply(.07D, 1D, .07D)));

    }
    @Override
    public int getUseDuration(ItemStack itemStack)
    {
        return 72000;
    }
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pStack.hurtAndBreak(3, pAttacker, (p_43296_) -> {
            p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        return true;
    }
}

//   player.startUsingItem(InteractionHand.MAIN_HAND);



