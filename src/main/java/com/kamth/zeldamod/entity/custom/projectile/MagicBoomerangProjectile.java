package com.kamth.zeldamod.entity.custom.projectile;

import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.item.ModItems;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class MagicBoomerangProjectile extends BoomerangProjectile{
    public MagicBoomerangProjectile(EntityType<? extends AbstractArrow> pEntityType, LivingEntity pShooter, Level pLevel) {
        super(pEntityType, pShooter, pLevel);
    }
    private static final double BASE_DAMAGE = 5.0D;
    private final ItemStack boomerangItem = new ItemStack(ModItems.MAGIC_BOOMERANG.get());
    public MagicBoomerangProjectile(EntityType<MagicBoomerangProjectile> magicBoomerangProjectileEntityType, Level level) {
        super(ModEntityTypes.MAGIC_BOOMERANG.get(), level);
    }

    public MagicBoomerangProjectile(Level pLevel, Player player) {
        super(ModEntityTypes.MAGIC_BOOMERANG.get(), player, pLevel);
    }
    @Override
    protected ItemStack getPickupItem() {

        return boomerangItem;
    }

    public void tick() {
        super.tick();

            this.setNoGravity(true);
            if (this.tickCount > 30) {
                this.dealtDamage = true;

    }}
    public void onAddedToWorld() {
        super.onAddedToWorld();
        setBaseDamage(BASE_DAMAGE);
    }
    protected float getWaterInertia() {
        return 0.99F;
    }
    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
