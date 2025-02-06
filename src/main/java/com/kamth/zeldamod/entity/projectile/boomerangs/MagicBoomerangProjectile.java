package com.kamth.zeldamod.entity.projectile.boomerangs;

import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


public class MagicBoomerangProjectile extends AbstractBoomerang {

    public MagicBoomerangProjectile(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public MagicBoomerangProjectile(EntityType<? extends Projectile> pEntityType, Level pLevel, Player player, int returnSlot) {
        super(pEntityType, pLevel, player, returnSlot);
    }

    @Override
    protected float getDamage() {
        return 5;
    }

    @Override
    protected float getLifetimeLimit() {
        return 18;
    }

    @Override
    public ItemStack getItem() {
        return ZeldaItems.MAGIC_BOOMERANG.get().getDefaultInstance();
    }

    @Override
    public void tick() {
        int particlesDensity = 3;
        float particlesSpeed = 0.1F;
        float particlesSpread = 0.6F;

        for (int i = 0; i < particlesDensity; i++) {
            double particleX = getX() + (random.nextFloat() * 2 - 1) * particlesSpread;
            double particleY = getY() + (random.nextFloat() * 2 - 1) * particlesSpread;
            double particleZ = getZ() + (random.nextFloat() * 3 - 1) * particlesSpread;
            double particleMotionX = (random.nextFloat() * 2 - 1) * particlesSpeed;
            double particleMotionY = (random.nextFloat() * 2 - 1) * particlesSpeed;
            double particleMotionZ = (random.nextFloat() * 2 - 1) * particlesSpeed;
            this.level().addParticle(ParticleTypes.ENCHANTED_HIT, particleX, particleY, particleZ, particleMotionX, particleMotionY, particleMotionZ);
        }
        super.tick();
    }
}

