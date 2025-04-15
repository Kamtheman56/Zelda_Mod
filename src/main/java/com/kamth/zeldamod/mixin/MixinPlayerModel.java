package com.kamth.zeldamod.mixin;



import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.enchantments.ZeldaEnchantments;
import com.kamth.zeldamod.item.items.grapples.ClawshotItem;
import com.kamth.zeldamod.item.items.movement.AscendItem;
import com.kamth.zeldamod.item.items.movement.DekuLeafItem;
import com.kamth.zeldamod.item.items.movement.GliderItem;
import com.kamth.zeldamod.item.masks.transformation.ZoraMask;
import com.kamth.zeldamod.util.interfaces.mixin.SwordSpinPlayerData;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerModel.class)
public abstract class MixinPlayerModel extends HumanoidModel<LivingEntity> {
    @Unique
    private static final float ARM_ROTATION = (float) (Math.PI * 2 - 2.9);

    public MixinPlayerModel(ModelPart part){
        super(part);
    }

    @Inject(
            method = "setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/model/HumanoidModel;setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V",
                    shift = At.Shift.AFTER
            )
    )
    public void onSetRotationAngles(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
                                    float netHeadYaw, float headPitch, CallbackInfo ci) {

        ItemStack head = entity.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack mainHand = entity.getMainHandItem();
        ItemStack offHand = entity.getOffhandItem();

        if (head.getItem() instanceof ZoraMask && entity.isSwimming()) {
            leftArm.xRot = ARM_ROTATION;
            leftArm.zRot = 34.5f;
            rightArm.xRot = ARM_ROTATION;
            rightArm.zRot = -34.5f;
        }

        if (mainHand.getItem() instanceof AscendItem && entity.isUsingItem()) {
            rightArm.xRot = ARM_ROTATION;
            rightArm.zRot = 0;
        }

        if ((mainHand.is(ModTags.Items.GLIDERS) || offHand.is(ModTags.Items.GLIDERS)) && entity.isUsingItem()) {
            leftArm.xRot = ARM_ROTATION;
            leftArm.zRot = 0;
            rightArm.xRot = ARM_ROTATION;
            rightArm.zRot = 0;
        }


        // Grapple

        boolean holdingClawMain = mainHand.getItem() instanceof ClawshotItem;
        boolean holdingClawOff = offHand.getItem() instanceof ClawshotItem;

        if (!entity.isVisuallySwimming() && (holdingClawMain || holdingClawOff)) {

            ModelPart arm = holdingClawMain ? this.rightArm : this.leftArm;
            int side = holdingClawMain ? -1 : 1;

            arm.yRot = (0.1f * side) + this.head.yRot;
            arm.xRot = (-(float) Math.PI / 2) + this.head.xRot;
        }


        // Sword Spinning Animation

        boolean hasSwordSpin = EnchantmentHelper.getItemEnchantmentLevel(ZeldaEnchantments.SWORD_SPIN.get(), mainHand) > 0;

        if (entity instanceof Player player && hasSwordSpin) {

            SwordSpinPlayerData playerData = (SwordSpinPlayerData) player;

            if (entity.isUsingItem()) {
                this.rightArm.xRot = this.rightArm.xRot * 0.5F - 0.9424779F;
                this.rightArm.yRot = -0.5235988F;
            }

            if (playerData.legendaryArmory$isSwordSpinActive()) {
                this.rightArm.yRot = (-(float) Math.PI / 2) + this.body.yRot + 90;
                this.rightArm.xRot = (-(float) Math.PI / 2) + this.head.xRot;
            }
        }
    }
}