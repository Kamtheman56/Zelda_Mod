package com.kamth.zeldamod.entity.custom.projectile;

import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.item.custom.util.ModTags;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class SwordBeam extends AbstractArrow {
    private static final double BASE_DAMAGE = 3.0D;
    public SwordBeam(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public SwordBeam(EntityType<? extends AbstractArrow> pEntityType, LivingEntity pShooter, Level pLevel) {
        super(pEntityType, pShooter, pLevel);
    }

    public SwordBeam(Level world, LivingEntity owner) {
        super(ModEntityTypes.SWORD_BEAM.get(), owner, world);
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
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        int z = entity.getType().is(ModTags.Entities.UNDEAD)? 2 : 0;
        entity.hurt(damageSources().generic(), (float)z);
        int w = entity.getType().is(ModTags.Entities.EVIL) ? 10 : 0;
        entity.hurt(damageSources().generic(), (float)w);
      this.discard();
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

        if (tickCount > 15)
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
