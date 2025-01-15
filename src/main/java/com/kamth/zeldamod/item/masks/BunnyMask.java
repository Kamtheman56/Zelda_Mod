package com.kamth.zeldamod.item.masks;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.custom.ModArmorMaterials;
import com.kamth.zeldamod.item.ZeldaItems;
import com.kamth.zeldamod.item.armors.render.BunnyHoodModel;
import com.kamth.zeldamod.item.armors.render.ModModelLayers;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public class BunnyMask extends ArmorItem {
    private static final AttributeModifier STEP_HEIGHT_BONUS = new AttributeModifier(UUID.fromString("4a312f09-78e0-4f3a-95c2-07ed63212472"), "zeldamod:bunnymask", 0.5, AttributeModifier.Operation.ADDITION);
    public BunnyMask(ModArmorMaterials pMaterial, Type pType, Properties pProperties) {
        super(ModArmorMaterials.BUNNY, pType, pProperties);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerTick);
    }
    private static final String LOC = new ResourceLocation(ZeldaMod.MOD_ID, "textures/models/armor/bunny_hood.png").toString();
    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 10, 1, true, false));
        player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 10, 0, true, false));
    }
    private void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.START) {
            return;
        }
        AttributeInstance stepHeight;
        stepHeight = event.player.getAttribute(ForgeMod.STEP_HEIGHT_ADDITION.get());

        if (event.player.isSprinting() && event.player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ZeldaItems.BUNNY_MASK.get()) {
        }
        if (!stepHeight.hasModifier(STEP_HEIGHT_BONUS) && event.player instanceof Player && event.player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ZeldaItems.BUNNY_MASK.get()) {
            stepHeight.addTransientModifier(STEP_HEIGHT_BONUS);
        }
        else {
            if (stepHeight.hasModifier(STEP_HEIGHT_BONUS)) {
                stepHeight.removeModifier(STEP_HEIGHT_BONUS);
            }
        }
    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return LOC;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private BunnyHoodModel model;

            @Nullable
            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
                if (null == model) {
                    model = new BunnyHoodModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.BUNNY));
                }
                return model;
            }
        });
    }



    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.translatable("item.bunny_mask.description").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));

    }
}