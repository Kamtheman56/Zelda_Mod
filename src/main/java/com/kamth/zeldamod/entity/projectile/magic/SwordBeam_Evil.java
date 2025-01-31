package com.kamth.zeldamod.entity.projectile.magic;

import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.entity.ModEntityTypes;
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

public class SwordBeam_Evil extends SwordBeam {
    private static final double BASE_DAMAGE = 5.0D;
    public SwordBeam_Evil(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public SwordBeam_Evil(EntityType<? extends AbstractArrow> pEntityType, LivingEntity pShooter, Level pLevel) {
        super(pEntityType, pShooter, pLevel);
    }

    public SwordBeam_Evil(Level world, LivingEntity owner) {
        super(ModEntityTypes.SWORD_BEAM2.get(), owner, world);
    }


    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        this.discard();
        if (this.level() instanceof ServerLevel) {
            BlockPos blockpos = entity.blockPosition();
            if (this.level().canSeeSky(blockpos)) {
                LightningBolt lightningbolt = EntityType.LIGHTNING_BOLT.create(this.level());
                lightningbolt.moveTo(Vec3.atBottomCenterOf(blockpos));
                this.level().addFreshEntity(lightningbolt);
            }
        }
    }
    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        setBaseDamage(BASE_DAMAGE);
    }
}
