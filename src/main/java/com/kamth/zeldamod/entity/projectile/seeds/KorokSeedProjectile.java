package com.kamth.zeldamod.entity.projectile.seeds;

import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class KorokSeedProjectile extends AbstractSeedProjectile {

    public KorokSeedProjectile(EntityType<? extends AbstractSeedProjectile> pEntityType, Level level) {
        super(pEntityType, level);
    }

    protected KorokSeedProjectile(LivingEntity pShooter, Level pLevel) {
        super(ModEntityTypes.KOROK_SEED.get(), pShooter, pLevel);
    }

    @Override
    protected double getDamage() {
        return 5.0;
    }

    @Override
    public ItemStack getItem() {
        return ZeldaItems.KOROK_SEED.get().getDefaultInstance();
    }

    @Override
    protected int insectDamage(Entity entity) {
        return entity.getType().is(ModTags.Entities.BUGS)? 5 : 0;
    }
}
