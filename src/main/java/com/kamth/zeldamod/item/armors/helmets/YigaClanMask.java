package com.kamth.zeldamod.item.armors.helmets;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.custom.ModArmorMaterials;
import com.kamth.zeldamod.item.armors.render.BokoblinMaskModel;
import com.kamth.zeldamod.item.armors.render.ModModelLayers;
import com.kamth.zeldamod.item.armors.render.YigaClanMaskModel;
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

public class YigaClanMask extends ArmorItem {

    private static final String YIGA_MASK = new ResourceLocation(ZeldaMod.MOD_ID, "textures/models/armor/yiga_layer_0.png").toString();
    public YigaClanMask(ModArmorMaterials pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return YIGA_MASK;
    }




    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private YigaClanMaskModel model;

            @Nullable
            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
                if (null == model) {
                    model = new YigaClanMaskModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.YIGA_MASK));
                }
                return model;
            }
        });
    }
}