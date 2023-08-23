package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.entity.custom.projectile.BoomerangProjectile;
import com.kamth.zeldamod.entity.custom.projectile.GustProjectile;
import com.kamth.zeldamod.item.ModItems;
import com.kamth.zeldamod.item.custom.util.ModTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;

public class MirrorShieldItem extends ShieldItem {
    public MirrorShieldItem(Properties pProperties) {
        super(pProperties);
        MinecraftForge.EVENT_BUS.addListener(this::ShieldBlockEvent);

        MinecraftForge.EVENT_BUS.addListener(this::reflector);
    }


    public void ShieldBlockEvent(ShieldBlockEvent event){
        if (event.getEntity().getItemBySlot(EquipmentSlot.MAINHAND).getItem() == ModItems.MIRROR_SHIELD.get()  && !event.getDamageSource().is(DamageTypeTags.IS_PROJECTILE)) {
      event.getEntity().getUseItem().hurtAndBreak(12, event.getEntity(), (p_43276_) -> p_43276_.broadcastBreakEvent(InteractionHand.MAIN_HAND));
        }



    }



    public void reflector(ProjectileImpactEvent event) {
        if (!event.getEntity().level().isClientSide()
                && event.getEntity() instanceof Projectile projectile
                && event.getRayTraceResult() instanceof EntityHitResult entityHitResult
                && entityHitResult.getEntity() instanceof LivingEntity livingEntity
                && tryReflect(projectile, livingEntity, !(projectile instanceof FishingHook))
        ) {
            event.setCanceled(true);
        }
    }

    private static <T extends Projectile> boolean tryReflect(T projectile, LivingEntity entityBlocking, boolean takeOwnership) {
        if (!projectile.getType().is(ModTags.Entities.MIRROR) && entityBlocking.isBlocking()) {
            ItemStack itemUsed = entityBlocking.getUseItem();
            if (itemUsed.is(ModItems.MIRROR_SHIELD.get())){
                return parryProjectile(projectile, entityBlocking, takeOwnership);
            }
        }

        return false;
    }
    private static <T extends Projectile> boolean parryProjectile(T projectile, LivingEntity entityBlocking, boolean takeOwnership) {
        if (takeOwnership) transferOwnership(projectile, entityBlocking);

        Vec3 reboundAngle = entityBlocking.getLookAngle();

        projectile.shoot(reboundAngle.x, reboundAngle.y, reboundAngle.z, 1.1F, 0.1F);  // reflect faster and more accurately

        if (projectile instanceof AbstractHurtingProjectile damagingProjectile) {
            damagingProjectile.xPower = reboundAngle.x * 0.1D;
            damagingProjectile.yPower = reboundAngle.y * 0.1D;
            damagingProjectile.zPower = reboundAngle.z * 0.1D;
        }
        projectile.hurtMarked = true;

        return true;
    }
    private static <T extends Projectile> void transferOwnership(T projectile, LivingEntity entityBlocking) {
        if (projectile instanceof AbstractArrow arrow) {
            // AbstractArrow overrides setOwner, override changes state for its pickup based on owner
            AbstractArrow.Pickup priorPickupState = arrow.pickup;

            arrow.setOwner(entityBlocking);


            // Forge's event for rebounding projectiles has an issue that causes the server-side to stall if piercing arrows are involved
            // Though this is a reasonable long-term nerf anyway: Loss of 1 pierce level upon rebound
            byte pierceLevel = arrow.getPierceLevel();
            if (pierceLevel > 0) {
                return;
            }

            // Re-set the pre-fetched value
            arrow.pickup = priorPickupState;
        } else {
            projectile.setOwner(entityBlocking);
        }
    }

}
