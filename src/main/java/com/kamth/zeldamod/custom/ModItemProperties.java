package com.kamth.zeldamod.custom;

import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ModItemProperties {
    public static void addCustomItemProperties() {
        makeShield(ZeldaItems.DEKU_SHIELD.get());
        makeShield(ZeldaItems.HYLIAN_SHIELD.get());
        makeShield(ZeldaItems.MIRROR_SHIELD.get());
        makeShield(ZeldaItems.BALANCED_MIRROR_SHIELD.get());
        makeBow(ZeldaItems.SLINGSHOT.get());
        makeBow(ZeldaItems.SCATTERSHOT.get());
        makeBow(ZeldaItems.HERO_BOW.get());
        makeBow(ZeldaItems.REINFORCED_BOW.get());
        makeShield(ZeldaItems.DEKU_LEAF.get());
        makeShield(ZeldaItems.PARAGLIDER.get());
        makeShield(ZeldaItems.LENS_OF_TRUTH.get());
        makeShield(ZeldaItems.CLAWSHOT.get());
        makeShield(ZeldaItems.CLAWSHOT_GODDESS.get());
        makeShield(ZeldaItems.FLUTE.get());
        makeShield(ZeldaItems.OCARINA.get());

    }


    private static void makeShield(Item item) {

        ItemProperties.register(item, new ResourceLocation("blocking"), (p_174590_, p_174591_, p_174592_, p_174593_) -> {
            return p_174592_ != null && p_174592_.isUsingItem() && p_174592_.getUseItem() == p_174590_ ? 1.0F : 0.0F;
        });
    }





    private static void makeBow(Item item) {
        ItemProperties.register(item, new ResourceLocation("pull"), (p_174635_, p_174636_, p_174637_, p_174638_) -> {
            if (p_174637_ == null) {
                return 0.0F;
            } else {
                return p_174637_.getUseItem() != p_174635_ ? 0.0F : (float)(p_174635_.getUseDuration() -
                        p_174637_.getUseItemRemainingTicks()) / 20.0F;
            }
        });

        ItemProperties.register(item, new ResourceLocation("pulling"), (p_174630_, p_174631_, p_174632_, p_174633_) -> {
            return p_174632_ != null && p_174632_.isUsingItem() && p_174632_.getUseItem() == p_174630_ ? 1.0F : 0.0F;
        });
    }
}

