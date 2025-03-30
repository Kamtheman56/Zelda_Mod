package com.kamth.zeldamod.mixin.swordspin;

import com.kamth.zeldamod.enchantments.ZeldaEnchantments;
import com.kamth.zeldamod.util.interfaces.mixin.SwordSpinPlayerData;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Code contributed by Deadlydiamond98 (c) 2024 under the MIT License.
// Added here with explicit permission by the original owner.

@Mixin(ItemRenderer.class)
public class MixinItemRenderer {

    @Inject(method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/ItemRenderer;renderModelLists(Lnet/minecraft/client/resources/model/BakedModel;Lnet/minecraft/world/item/ItemStack;IILcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;)V",
                    shift = At.Shift.BEFORE
            )
    )
    private void onRenderBefore(ItemStack pItemStack, ItemDisplayContext pDisplayContext, boolean pLeftHand, PoseStack pPoseStack, MultiBufferSource pBuffer, int pCombinedLight, int pCombinedOverlay, BakedModel pModel, CallbackInfo ci) {
        Item item = pItemStack.getItem();
        Player player = Minecraft.getInstance().player;
        SwordSpinPlayerData playerData = ((SwordSpinPlayerData) player);

        boolean hasSwordSpin = EnchantmentHelper.getItemEnchantmentLevel(ZeldaEnchantments.SWORD_SPIN.get(), pItemStack) > 0;

        if (item instanceof SwordItem && hasSwordSpin) {
            pPoseStack.translate(0.5, 0.5, 0.5);

            if (player != null && pDisplayContext.firstPerson()) {

                float yRot = !player.isUsingItem() ? 90 : 0;

                if (player.isUsingItem() || playerData.legendaryArmory$isSwordSpinActive()) {
                    pPoseStack.mulPose(Axis.ZP.rotationDegrees(90));
                    pPoseStack.mulPose(Axis.YP.rotationDegrees(yRot));
                    pPoseStack.mulPose(Axis.XP.rotationDegrees(45));
                }
            }

            pPoseStack.translate(-0.5, -0.5, -0.5);
        }
    }

}
