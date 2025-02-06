package com.kamth.zeldamod.entity.projectile.boomerangs;

import com.kamth.zeldamod.entity.projectile.bombs.BombEntity;
import com.kamth.zeldamod.item.ZeldaItems;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCandleBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.LeverBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;


public class GaleBoomerangProjectile extends AbstractBoomerang {
    public GaleBoomerangProjectile(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public GaleBoomerangProjectile(EntityType<? extends Projectile> pEntityType, Level pLevel, Player player, int returnSlot) {
        super(pEntityType, pLevel, player, returnSlot);
    }

    @Override
    protected float getDamage() {
        return 4;
    }

    @Override
    protected float getLifetimeLimit() {
        return 18;
    }

    @Override
    public ItemStack getItem() {
        return ZeldaItems.GALE_BOOMERANG.get().getDefaultInstance();
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
            this.level().addParticle(ParticleTypes.CLOUD, particleX, particleY, particleZ, particleMotionX, particleMotionY, particleMotionZ);
        }
        super.tick();
    }
}