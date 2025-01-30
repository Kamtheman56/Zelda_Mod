package com.kamth.zeldamod.item.armors;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.custom.ModArmorMaterials;
import com.kamth.zeldamod.item.armors.render.DarknutHelmetModel;
import com.kamth.zeldamod.item.armors.render.ModModelLayers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import javax.annotation.Nullable;
import java.util.function.Consumer;

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