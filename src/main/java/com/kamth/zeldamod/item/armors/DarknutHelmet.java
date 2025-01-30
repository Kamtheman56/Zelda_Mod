package com.kamth.zeldamod.item.armors;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.custom.ModArmorMaterials;
import com.kamth.zeldamod.item.armors.render.ArchaicTunicModel;
import com.kamth.zeldamod.item.armors.render.DarknutHelmetModel;
import com.kamth.zeldamod.item.armors.render.ModModelLayers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Consumer;

public class DarknutHelmet extends ArmorItem {

    private static final String DARK_NUT = new ResourceLocation(ZeldaMod.MOD_ID, "textures/models/armor/darknut_helmet.png").toString();
    public DarknutHelmet(ModArmorMaterials pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return DARK_NUT;
    }




    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private DarknutHelmetModel model;

            @Nullable
            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
                if (null == model) {
                    model = new DarknutHelmetModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.DARK_NUT_HELMET));
                }
                return model;
            }
        });
    }
}