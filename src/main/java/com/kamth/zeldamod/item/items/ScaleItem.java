package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.entity.custom.projectile.BombProjectile;
import com.kamth.zeldamod.entity.custom.projectile.GustProjectile;
import com.kamth.zeldamod.item.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
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
import net.minecraftforge.common.ForgeMod;

public class ScaleItem extends Item {
    public ScaleItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand pHand) {
        ItemStack itemstack = player.getItemInHand(pHand);
        if ( player.isInWater() && itemstack.is(ModItems.SILVER_SCALE.get())) {
            Vec3 vec3 = player.getDeltaMovement();
           // player.startUsingItem(pHand);
            float f7 = player.getYRot();
            float f = player.getXRot();
            float f1 = -Mth.sin(f7 * ((float) Math.PI / 180F)) * Mth.cos(f * ((float) Math.PI / 180F));
            float f2 = -Mth.sin(f * ((float) Math.PI / 180F));
            float f3 = Mth.cos(f7 * ((float) Math.PI / 180F)) * Mth.cos(f * ((float) Math.PI / 180F));
            float f4 = Mth.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
            float f5 = 3.5F * ((1.0F + (float) 1) / 4.0F);
            f1 *= f5 / f4;
            f2 *= f5 / f4;
            f3 *= f5 / f4;
            player.push((double) f1, (double) f2, (double) f3);
            player.startAutoSpinAttack(20);
            return InteractionResultHolder.success(itemstack);
        }
        if ( player.isInWater() && itemstack.is(ModItems.GOLDEN_SCALE.get())) {
            Vec3 vec3 = player.getDeltaMovement();
            // player.startUsingItem(pHand);
            float f7 = player.getYRot();
            float f = player.getXRot();
            float f1 = -Mth.sin(f7 * ((float) Math.PI / 180F)) * Mth.cos(f * ((float) Math.PI / 180F));
            float f2 = -Mth.sin(f * ((float) Math.PI / 180F));
            float f3 = Mth.cos(f7 * ((float) Math.PI / 180F)) * Mth.cos(f * ((float) Math.PI / 180F));
            float f4 = Mth.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
            float f5 = 4.5F * ((1.0F + (float) 1) / 4.0F);
            f1 *= f5 / f4;
            f2 *= f5 / f4;
            f3 *= f5 / f4;
            player.push((double) f1, (double) f2, (double) f3);
            player.startAutoSpinAttack(30);
            return InteractionResultHolder.success(itemstack);
        }

        else return InteractionResultHolder.fail(itemstack);
    }
    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int pSlotId, boolean pIsSelected) {
        if (!world.isClientSide) {
            if (entity instanceof Player && ((Player) entity).getOffhandItem().getItem() == ModItems.SILVER_SCALE.get() && !entity.isEyeInFluidType(ForgeMod.WATER_TYPE.get()) ) {
                ((Player) entity).addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 90, 0, true, false));
            }
            if (entity instanceof Player && ((Player) entity).getMainHandItem().getItem() == ModItems.SILVER_SCALE.get() &&  !entity.isEyeInFluidType(ForgeMod.WATER_TYPE.get()) ) {
                ((Player) entity).addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 90, 0, true, false));
            }
            if (entity instanceof Player && ((Player) entity).getOffhandItem().getItem() == ModItems.GOLDEN_SCALE.get() && !entity.isEyeInFluidType(ForgeMod.WATER_TYPE.get()) ) {
                ((Player) entity).addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 185, 0, true, false));
            }
            if (entity instanceof Player && ((Player) entity).getMainHandItem().getItem() == ModItems.GOLDEN_SCALE.get() &&  !entity.isEyeInFluidType(ForgeMod.WATER_TYPE.get()) ) {
                ((Player) entity).addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 185, 0, true, false));
            }


        }

    }

}






