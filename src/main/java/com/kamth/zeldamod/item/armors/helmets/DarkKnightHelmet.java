package com.kamth.zeldamod.item.armors.helmets;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.custom.ModArmorMaterials;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

public class DarkKnightHelmet extends DarknutHelmet {

    private static final String DARK_KNIGHT = new ResourceLocation(ZeldaMod.MOD_ID, "textures/models/armor/dark_knight_helmet.png").toString();
    public DarkKnightHelmet(ModArmorMaterials pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
                return DARK_KNIGHT;
    }


}