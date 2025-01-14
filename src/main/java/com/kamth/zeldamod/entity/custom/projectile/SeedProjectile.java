package com.kamth.zeldamod.entity.custom.projectile;

import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class SeedProjectile extends AbstractArrow {
    private static final double BASE_DAMAGE = 4.0D;
    private Item referenceItem;
    public SeedProjectile(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.referenceItem = Items.WHEAT_SEEDS;
    }
    public SeedProjectile(Level worldIn) {
        super(ModEntityTypes.SEED_PROJECTILE.get(), worldIn);
    }
    public  SeedProjectile(Level pLevel, LivingEntity pShooter) {
        super(ModEntityTypes.SEED_PROJECTILE.get(), pShooter, pLevel);

    }

    @Override
    public ItemStack getPickupItem() {
        return new ItemStack(this.referenceItem);
    }


    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        setBaseDamage(BASE_DAMAGE);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        int z = entity.getType().is(ModTags.Entities.BUGS)? 4 : 0;
        entity.hurt(damageSources().generic(), (float)z);
        this.discard();

        if (pResult.getEntity() instanceof LivingEntity) {
            LivingEntity target = (LivingEntity) pResult.getEntity();
                target.setArrowCount(target.getArrowCount() - 1);
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult ray) {
        super.onHitBlock(ray);
        this.discard();
        if (this.level() instanceof ServerLevel) {
            ((ServerLevel)this.level()).sendParticles(ParticleTypes.CLOUD, this.getX() , this.getY(), this.getZ() , 2, 0, 0.0D, 0, 0.0D);
        }
    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return ModSounds.SEED_BREAKS.get();
    }
    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
