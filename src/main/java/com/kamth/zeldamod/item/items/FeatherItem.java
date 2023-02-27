package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.item.ModItems;
import com.mojang.math.Vector3d;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import static com.kamth.zeldamod.item.items.SandWandItem.raytrace1;

public class FeatherItem extends Item {
    public FeatherItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int pSlotId, boolean pIsSelected) {
        if (!world.isClientSide) {
                if (entity instanceof Player && ((Player) entity).getOffhandItem().getItem() == ModItems.ROC_FEATHER.get()) {
                    ((Player) entity).addEffect(new MobEffectInstance(MobEffects.JUMP, 10, 1, true, false));
                    entity.resetFallDistance();

                }
                if  (entity instanceof Player && ((Player) entity).getOffhandItem().getItem() == ModItems.ROC_FEATHER.get() && ((Player) entity).getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.PEGASUS_BOOTS.get()) {
                    ((Player) entity).addEffect(new MobEffectInstance(MobEffects.JUMP, 10, 3, true, false));
                    entity.resetFallDistance();

            }
                if (entity instanceof Player && ((Player) entity).getMainHandItem().getItem() == ModItems.ROC_FEATHER.get() && ((Player) entity).getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.PEGASUS_BOOTS.get()) {
                    ((Player) entity).addEffect(new MobEffectInstance(MobEffects.JUMP, 10, 3, true, false));
                    entity.resetFallDistance();
            }
                if (entity instanceof Player && ((Player) entity).getMainHandItem().getItem() == ModItems.ROC_FEATHER.get()) {
                    ((Player) entity).addEffect(new MobEffectInstance(MobEffects.JUMP, 10, 1, true, false));
                    entity.resetFallDistance();}
            }
        }
    @Override

    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        BlockHitResult ray = raytrace1(world, player, ClipContext.Fluid.NONE);
        BlockPos lookPos = ray.getBlockPos().relative(ray.getDirection());
        Vec3 vec3 = player.getDeltaMovement();
        double d0;
        d0 = Math.min(0.7D, vec3.y + 0.06D);

        player.setDeltaMovement(vec3.x, 0.5, vec3.z);
        player.getCooldowns().addCooldown(this, 20);
        return super.use(world, player, hand);
}}
