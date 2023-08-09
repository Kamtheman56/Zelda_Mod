package com.kamth.zeldamod.item.armors;

import com.kamth.zeldamod.effect.ModEffects;
import com.kamth.zeldamod.item.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

//Todo set a timer for hover effect!


public class HoverBoots extends ArmorItem {


    public HoverBoots(ArmorMaterial p_40386_, EquipmentSlot p_40387_, Properties p_40388_) {
        super(p_40386_, p_40387_, p_40388_);


    }


    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {

        if (player.isSprinting() && player.getDeltaMovement().y > 0  && !player.isOnGround() && player.hasEffect(ModEffects.HOVER.get())) {
            player.fallDistance = -3;
            player.setNoGravity(true);
            Vec3 vec3 = player.getDeltaMovement();
            player.setDeltaMovement(vec3.x, 0.0, vec3.z);
            player.canSpawnSprintParticle();
            world.addParticle(ParticleTypes.CLOUD,player.getX(), player.getY() + 0.1D, player.getZ(), vec3.x, 0, vec3.z * -4.0D);

            world.playSound(null,player.getX(),player.getY(),player.getZ(), SoundEvents.WOOL_STEP, SoundSource.NEUTRAL, 1F, 0.2F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        }
        if (!player.hasEffect(ModEffects.HOVER.get())){
            player.setNoGravity(false);
            player.resetFallDistance();
        }

        if (!player.isOnGround() && !player.isSprinting() && player.getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.HOVER_BOOTS.get()) {
            player.setNoGravity(false);
            player.fallDistance = -3;
        }
        if (player.isOnGround() && player.getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.HOVER_BOOTS.get()) {
            player.addEffect(((new MobEffectInstance(ModEffects.HOVER.get(), 90, 0, true, false))));
        }
    }
   @Override
   public boolean canWalkOnPowderedSnow(ItemStack stack, LivingEntity wearer)
    {
        return stack.getItem() == ModItems.HOVER_BOOTS.get();
    }
}





