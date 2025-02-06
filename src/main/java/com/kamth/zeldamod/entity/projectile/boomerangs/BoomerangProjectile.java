package com.kamth.zeldamod.entity.projectile.boomerangs;


import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BoomerangProjectile extends AbstractBoomerang {
    public BoomerangProjectile(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public BoomerangProjectile(EntityType<? extends Projectile> pEntityType, Level pLevel, Player player, int returnSlot) {
        super(pEntityType, pLevel, player, returnSlot);
    }

    @Override
    protected float getDamage() {
        return 2;
    }

    @Override
    protected float getLifetimeLimit() {
        return 15;
    }

    @Override
    public ItemStack getItem() {
        return ZeldaItems.BOOMERANG.get().getDefaultInstance();
    }
}