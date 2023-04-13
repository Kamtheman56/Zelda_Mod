package com.kamth.zeldamod.entity.custom.projectile;

import com.kamth.zeldamod.item.ModItems;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class IceArrow extends AbstractArrow {
    private static final double BASE_DAMAGE = 3.0D;
    public IceArrow(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public IceArrow(EntityType<? extends AbstractArrow> pEntityType, double pX, double pY, double pZ, Level pLevel) {
        super(pEntityType, pX, pY, pZ, pLevel);
    }

    public IceArrow(EntityType<? extends AbstractArrow> pEntityType, LivingEntity pShooter, Level pLevel) {
        super(pEntityType, pShooter, pLevel);
    }

    /**
     * @return
     */
    @Override
    protected ItemStack getPickupItem() {
        return ModItems.ICE_ARROW.get().getDefaultInstance();
    }
    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity owner = this.getOwner();
        Entity entity = pResult.getEntity();
      if (pResult.getEntity() != owner){
          entity.setTicksFrozen(250);
      }}



    @Override
    public void tick() {
        super.tick();
        if (this.isInWater()){
           level.setBlockAndUpdate(this.blockPosition(), Blocks.FROSTED_ICE.defaultBlockState());

            this.discard();}
        if (this.isInLava()){
            level.setBlockAndUpdate(this.blockPosition(), Blocks.COBBLESTONE.defaultBlockState());
            this.discard();}
        }
    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        setBaseDamage(BASE_DAMAGE);


    }
    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.SNOW_BREAK;
    }
    @Override
    protected void onHitBlock(@NotNull BlockHitResult ray) {
        super.onHitBlock(ray);

        BlockState blockHit = level.getBlockState(ray.getBlockPos());
        if (blockHit == Blocks.FIRE.defaultBlockState()) {
            level.destroyBlock(ray.getBlockPos(), false);
        }

        this.discard();

}
    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
