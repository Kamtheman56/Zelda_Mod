package com.kamth.zeldamod.item.armors;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.custom.ModArmorMaterials;
import com.kamth.zeldamod.item.armors.render.CapModel;
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

public class KokiriCap extends ArmorItem {

    private static final String LOC = new ResourceLocation(ZeldaMod.MOD_ID, "textures/models/armor/kokiri_cap.png").toString();
    public KokiriCap(ModArmorMaterials pMaterial, Type pType, Properties pProperties) {
        super(ModArmorMaterials.Archaic, pType, pProperties);
    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
                return LOC;}

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private CapModel model;

            @Nullable
            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
                if (null == model) {
                    model = new CapModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.CAP));
                }
                return model;
            }
        });
    }
}