package com.kamth.zeldamod.entity.custom.projectile;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class BombProjectile extends ThrowableProjectile {


    public BombProjectile(Object entityType, Level level) {
        super((EntityType<? extends ThrowableProjectile>) entityType,level);

    }
    public BombProjectile(Level world, LivingEntity owner) {
        super(ModEntityTypes.BOMB.get(), owner, world); // null will be changed later
    }
    public BombProjectile(Level world, double x, double y, double z) {
        super(ModEntityTypes.BOMB.get(), x, y, z, world); // null will be changed later
    }

   protected Item getDefaultItem() {
        return ModItems.BOMB.get();
    }
    @Override
    protected void onHitEntity(EntityHitResult ray) {
        super.onHitEntity(ray);
        // this, x, y, z, explosionStrength, setsFires, breakMode
        this.level.explode(this, this.getX(), this.getY(), this.getZ(), 4.0f, false, Explosion.BlockInteraction.NONE);
    }
    /**
     *
     */
    @Override
    protected void defineSynchedData() {

    }
}





