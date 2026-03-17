package com.kamth.zeldamod.entity.projectile.arrows;

import com.kamth.zeldamod.Config;
import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class BombArrow extends AbstractArrow {
    private static final double BASE_DAMAGE = 1.0D;
    private int explosionPower = 3;
    public BombArrow(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public BombArrow(EntityType<? extends AbstractArrow> pEntityType, double pX, double pY, double pZ, Level pLevel) {
        super(pEntityType, pX, pY, pZ, pLevel);
    }

    public BombArrow(EntityType<? extends AbstractArrow> pEntityType, LivingEntity pShooter, Level pLevel) {
        super(pEntityType, pShooter, pLevel);
    }

    /**
     * @return
     */
    @Override
    protected ItemStack getPickupItem() {
        return  ZeldaItems.BOMB_ARROW.get().getDefaultInstance();}
    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        explode();

    }
    @Override
    public void tick() {
        super.tick();
        if (this.isInWater()){    this.playSound(SoundEvents.FIRE_EXTINGUISH);
            this.discard();}
    }
    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        setBaseDamage(BASE_DAMAGE);

    }
    @Override
    protected void onHitBlock(@NotNull BlockHitResult ray) {
        super.onHitBlock(ray);
        explode();
        this.discard();

}
    private void explode() {

        if (Config.bomb_arrow_griefing()) {
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), this.explosionPower, Level.ExplosionInteraction.TNT);
        } else {
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), this.explosionPower, Level.ExplosionInteraction.NONE);



            BlockPos explosionPos = this.blockPosition();

            int radius = (int) Math.ceil(this.explosionPower);

            for (BlockPos pos : BlockPos.betweenClosed(explosionPos.offset(-radius, -radius, -radius), explosionPos.offset(radius, radius, radius))) {

                BlockState blockState = this.level().getBlockState(pos).getBlock().defaultBlockState();

                if (blockState.is(ModTags.Blocks.BOMB)) {
                    this.level().destroyBlock(pos, false);
                }

                if (blockState.is(ModTags.Blocks.BOMB_FLOWER_BLOCKS)) {
                    this.level().destroyBlock(pos, false);
                    this.level().explode(this, this.getX(), this.getY(), this.getZ(), this.explosionPower, Level.ExplosionInteraction.MOB);
                }
            }
        }
        this.discard();
    }

@Override
protected SoundEvent getDefaultHitGroundSoundEvent() {
    return SoundEvents.ARROW_HIT;
}
    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
