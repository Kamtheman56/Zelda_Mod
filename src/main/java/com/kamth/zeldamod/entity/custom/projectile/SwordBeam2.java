package com.kamth.zeldamod.entity.custom.projectile;

import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.item.custom.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class SwordBeam2 extends AbstractArrow {
    private static final double BASE_DAMAGE = 5.0D;
    public SwordBeam2(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public SwordBeam2(EntityType<? extends AbstractArrow> pEntityType, LivingEntity pShooter, Level pLevel) {
        super(pEntityType, pShooter, pLevel);
    }

    public SwordBeam2(Level world, LivingEntity owner) {
        super(ModEntityTypes.SWORD_BEAM2.get(), owner, world);
    }

    /**
     * @return
     */
    @Override
    protected ItemStack getPickupItem() {
        return  null;}
    @Override
    public boolean isNoGravity()
    {
        return true;
    }
    @Override
    protected void onHitEntity(EntityHitResult pResult) {

        Entity entity = pResult.getEntity();
        if (this.level() instanceof ServerLevel) {
            BlockPos blockpos = entity.blockPosition();
            if (this.level().canSeeSky(blockpos)) {
                LightningBolt lightningbolt = EntityType.LIGHTNING_BOLT.create(this.level());
                lightningbolt.moveTo(Vec3.atBottomCenterOf(blockpos));
                this.level().addFreshEntity(lightningbolt);
            }}

    }
    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        setBaseDamage(BASE_DAMAGE);
    }
    @Override
    protected void onHitBlock(@NotNull BlockHitResult ray) {
        super.onHitBlock(ray);
        this.discard();

}

    @Override
    public boolean canChangeDimensions() {
        return false;
    }



    @Override
    public void tick()
    {
        super.tick();

        if (tickCount > 20)
        {
            this.discard();
        }
        if (this.isInWater()){
            this.discard();
        }

    }
@Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.EMPTY;
    }



    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
