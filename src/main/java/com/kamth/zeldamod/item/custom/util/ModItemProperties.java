package com.kamth.zeldamod.item.custom.util;

import com.kamth.zeldamod.item.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShieldItem;

import static net.minecraft.core.Registry.register;

public class ModItemProperties {
    public static void addCustomItemProperties() {
        makeShield(ModItems.DEKU_SHIELD.get());
        makeShield(ModItems.HYLIAN_SHIELD.get());
    }

    private static void makeShield(Item item) {

        ItemProperties.register(item, new ResourceLocation("blocking"), (p_174590_, p_174591_, p_174592_, p_174593_) -> {
            return p_174592_ != null && p_174592_.isUsingItem() && p_174592_.getUseItem() == p_174590_ ? 1.0F : 0.0F;
        });
    
    
    
    }

}

