package com.kamth.zeldamod.entity.projectile.bombs;

import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class WaterBombEntity extends AbstractBombEntity {
    public WaterBombEntity(EntityType<WaterBombEntity> bombProjectileEntityType, Level level) {
        super(bombProjectileEntityType, level);
    }

    public WaterBombEntity(LivingEntity pShooter, Level pLevel, boolean bowled) {
        super(ModEntityTypes.WATER_BOMB.get(), pShooter, pLevel, 65, 1, true, bowled);
    }

    @Override
    public ItemStack getItem() {
        return ZeldaItems.WATER_BOMB.get().getDefaultInstance();
    }

    @Override
    protected float getGravity() {
        return 0.15f;
    }
}






