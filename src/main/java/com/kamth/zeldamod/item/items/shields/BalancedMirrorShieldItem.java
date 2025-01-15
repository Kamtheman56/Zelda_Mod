package com.kamth.zeldamod.item.items.shields;

import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.ProjectileImpactEvent;

import javax.annotation.Nullable;
import java.util.List;

public class BalancedMirrorShieldItem extends ShieldItem {
    public BalancedMirrorShieldItem(Properties pProperties) {
        super(pProperties);
        MinecraftForge.EVENT_BUS.addListener(this::reflector);
    }



    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
            components.add(Component.translatable("item.balanced_mirror_shield.description").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.ITALIC));
        super.appendHoverText(stack, level, components, flag);
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
        if (entityBlocking.isBlocking()) {
            ItemStack itemUsed = entityBlocking.getUseItem();
            if (itemUsed.is(ZeldaItems.BALANCED_MIRROR_SHIELD.get())){
                return ReflectProjectile(projectile, entityBlocking, takeOwnership);
            }
        }

        return false;
    }
    private static <T extends Projectile> boolean ReflectProjectile(T projectile, LivingEntity entityBlocking, boolean takeOwnership) {
        if (takeOwnership) transferOwnership(projectile, entityBlocking);

        Vec3 reflectAngle = entityBlocking.getLookAngle();

        projectile.shoot(reflectAngle.x, reflectAngle.y, reflectAngle.z, 1.1F, 0.2F);

        if (projectile instanceof AbstractHurtingProjectile damagingProjectile) {
            damagingProjectile.xPower = reflectAngle.x * 0.1D;
            damagingProjectile.yPower = reflectAngle.y * 0.1D;
            damagingProjectile.zPower = reflectAngle.z * 0.1D;
        }
        projectile.hurtMarked = true;

        return true;
    }
    private static <T extends Projectile> void transferOwnership(T projectile, LivingEntity entityBlocking) {
        if (projectile instanceof AbstractArrow arrow) {
            AbstractArrow.Pickup priorPickupState = arrow.pickup;
            arrow.setOwner(entityBlocking);
            byte pierceLevel = arrow.getPierceLevel();
            if (pierceLevel > 0) {
                return;
            }
            arrow.pickup = priorPickupState;
        } else {
            projectile.setOwner(entityBlocking);
        }
    }

}
