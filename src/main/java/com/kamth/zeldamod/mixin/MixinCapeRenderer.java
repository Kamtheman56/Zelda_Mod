package com.kamth.zeldamod.mixin;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.item.ModItems;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayer.class)
public abstract class MixinCapeRenderer  {
    private static final ResourceLocation TEXTURE = new ResourceLocation(ZeldaMod.MOD_ID, "textures/models/armor/hylian_cape.png");
    private static final ResourceLocation STEVE = new ResourceLocation(ZeldaMod.MOD_ID, "textures/models/skins/steve_skin.png");
        /**
         * Sets the player as having a loaded cape if they have a cape accessory equipped and visible.
         * @param cir The {@link Boolean} {@link CallbackInfoReturnable} used for the method's return value.
         */
        @Inject(at = @At("HEAD"), method = "isCapeLoaded()Z", cancellable = true)
        private void isCapeLoaded(CallbackInfoReturnable<Boolean> cir) {
            AbstractClientPlayer player = (AbstractClientPlayer) (Object) this;
            if (player.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.HYLIAN_HOOD.get())) {
                cir.setReturnValue(true);
            }
        }


        @Inject(at = @At("HEAD"), method = "getCloakTextureLocation()Lnet/minecraft/resources/ResourceLocation;", cancellable = true)
        private void getCloakTextureLocation(CallbackInfoReturnable<ResourceLocation> cir) {
            AbstractClientPlayer player = (AbstractClientPlayer) (Object) this;
            if (player.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.HYLIAN_HOOD.get())) {
                ResourceLocation texture = TEXTURE;
                cir.setReturnValue(texture);

            }
        }

    @Inject(at = @At("HEAD"), method = "getSkinTextureLocation", cancellable = true)
    private void getSkinLocation(CallbackInfoReturnable<ResourceLocation> cir){
        AbstractClientPlayer player = (AbstractClientPlayer) (Object) this;
        if (player.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.STEVE_MASK.get())) {
            ResourceLocation texture = STEVE;
            cir.setReturnValue(texture);
        }
    }
    }








