package com.kamth.zeldamod.entity.custom.projectile;

import com.kamth.zeldamod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
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
//TODO fix hitting yourself with arrow???
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
        Entity entity = pResult.getEntity();
    }

    @Override
    protected void doPostHurtEffects(LivingEntity entity)
    {
        super.doPostHurtEffects(entity);
 entity.setTicksFrozen(40);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isInWater()){
            BlockState blockstate = Blocks.FROSTED_ICE.defaultBlockState();
            float f = (float)Math.min(1, 1);


            for(BlockPos blockpos : BlockPos.betweenClosed(this.blockPosition().offset((double) (-f), +1.0D, (double)(-f)), this.blockPosition().offset((double)f, +1.0D, (double)f))) {

                   {
                        BlockState blockstate2 = level.getBlockState(blockpos);
                        boolean isFull = blockstate2.getBlock() == Blocks.WATER && blockstate2.getValue(LiquidBlock.LEVEL) == 0; //TODO: Forge, modded waters?
                        if (blockstate2.getMaterial() == Material.WATER && isFull && blockstate.canSurvive(level, blockpos) && level.isUnobstructed(blockstate, blockpos, CollisionContext.empty()) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(this, net.minecraftforge.common.util.BlockSnapshot.create(level.dimension(), level, blockpos), net.minecraft.core.Direction.UP)) {
                            level.setBlockAndUpdate(blockpos, blockstate);
                            level.scheduleTick(blockpos, Blocks.FROSTED_ICE, Mth.nextInt(this.random, 60, 150));
                            this.discard();
                        }
                    }
                }
            }

        }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        setBaseDamage(BASE_DAMAGE);


    }
    public static void onEntityMoved(LivingEntity pLiving, Level pLevel, BlockPos pPos, int pLevelConflicting) {
        if (pLiving.isOnGround()) {
            BlockState blockstate = Blocks.FROSTED_ICE.defaultBlockState();
            float f = (float)Math.min(16, 2 + pLevelConflicting);
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for(BlockPos blockpos : BlockPos.betweenClosed(pPos.offset((double)(-f), -1.0D, (double)(-f)), pPos.offset((double)f, -1.0D, (double)f))) {
                if (blockpos.closerToCenterThan(pLiving.position(), (double)f)) {
                    blockpos$mutableblockpos.set(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
                    BlockState blockstate1 = pLevel.getBlockState(blockpos$mutableblockpos);
                    if (blockstate1.isAir()) {
                        BlockState blockstate2 = pLevel.getBlockState(blockpos);
                        boolean isFull = blockstate2.getBlock() == Blocks.WATER && blockstate2.getValue(LiquidBlock.LEVEL) == 0; //TODO: Forge, modded waters?
                        if (blockstate2.getMaterial() == Material.WATER && isFull && blockstate.canSurvive(pLevel, blockpos) && pLevel.isUnobstructed(blockstate, blockpos, CollisionContext.empty()) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(pLiving, net.minecraftforge.common.util.BlockSnapshot.create(pLevel.dimension(), pLevel, blockpos), net.minecraft.core.Direction.UP)) {
                            pLevel.setBlockAndUpdate(blockpos, blockstate);
                            pLevel.scheduleTick(blockpos, Blocks.FROSTED_ICE, Mth.nextInt(pLiving.getRandom(), 60, 120));
                        }
                    }
                }
            }

        }
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
