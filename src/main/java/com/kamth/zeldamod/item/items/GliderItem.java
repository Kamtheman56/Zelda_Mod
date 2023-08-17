package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.entity.custom.projectile.GustProjectile;
import com.kamth.zeldamod.item.ModItems;
import com.kamth.zeldamod.item.custom.util.ModTags;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class GliderItem extends Item {
    public GliderItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        player.awardStat(Stats.ITEM_USED.get(this));
        if  (!player.onGround() && !player.onClimbable()) {
            player.startUsingItem(InteractionHand.MAIN_HAND);
            player.startUsingItem(InteractionHand.OFF_HAND);
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.WOOL_PLACE, SoundSource.PLAYERS, 1.2F, 2F / (world.getRandom().nextFloat() * 0.4F + 0.8F));

        }
        return super.use(world, player, hand);

    }


    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.SPEAR;
    }
    @Override
    public void onUseTick(Level pLevel, LivingEntity livingEntity, ItemStack pStack, int pRemainingUseDuration) {
        Player player = (Player) livingEntity;
        Vec3 vec3 = player.getDeltaMovement();

      player.resetFallDistance();
      player.setDeltaMovement(vec3.x, -0.06, vec3.z);
        player.setDeltaMovement(player.getDeltaMovement().add(player.getDeltaMovement().multiply(.09D, 0.0D, .09D)));
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




