package com.kamth.zeldamod.item.masks;

import com.kamth.zeldamod.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class GiantMask extends ArmorItem {
    private static final AttributeModifier STEP_HEIGHT_BONUS = new AttributeModifier(UUID.fromString("f84f96a4-8c78-459a-9c2b-ce7586048c3a"), "zeldamod:giantmask", 3, AttributeModifier.Operation.ADDITION);
    private static final AttributeModifier KNOCKBACK = new AttributeModifier(UUID.fromString("c7018b95-0cce-4500-9794-319cbfd60941"), "zeldamod:giantknockback", 4, AttributeModifier.Operation.ADDITION);
    private static final AttributeModifier BIG_PUNCH = new AttributeModifier(UUID.fromString("f35f2ff2-5588-46cc-9017-2f926ad2aaa2"), "zeldamod:giantpunch", 10, AttributeModifier.Operation.ADDITION);
    public GiantMask(ArmorMaterial pMaterial, Type type, Properties pProperties) {
        super(pMaterial, type, pProperties);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerTick);
    }

    private void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.START) {
            return;
        }
        AttributeInstance stepHeight;
        stepHeight = event.player.getAttribute(ForgeMod.ENTITY_REACH.get());
        AttributeInstance punchmode;
        punchmode = event.player.getAttribute(Attributes.ATTACK_DAMAGE);
        AttributeInstance giantknockback;
        giantknockback = event.player.getAttribute(Attributes.ATTACK_KNOCKBACK);
        if (!stepHeight.hasModifier(STEP_HEIGHT_BONUS) && event.player instanceof Player && event.player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.GIANT_MASK.get()) {
            stepHeight.addTransientModifier(STEP_HEIGHT_BONUS);
        }
        else {
            if (stepHeight.hasModifier(STEP_HEIGHT_BONUS)) {
                stepHeight.removeModifier(STEP_HEIGHT_BONUS);
            }
        }
        if (!giantknockback.hasModifier(KNOCKBACK) && event.player instanceof Player && event.player.getItemBySlot(EquipmentSlot.MAINHAND).getItem() == Items.AIR  && event.player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.GIANT_MASK.get()) {
            giantknockback.addTransientModifier(KNOCKBACK);
        }
        else {
            if (giantknockback.hasModifier(KNOCKBACK)) {
                giantknockback.removeModifier(KNOCKBACK);
            }
        }
        if (!punchmode.hasModifier(BIG_PUNCH) && event.player instanceof Player && event.player.getItemBySlot(EquipmentSlot.MAINHAND).getItem() == Items.AIR && event.player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.GIANT_MASK.get()) {
            punchmode.addTransientModifier(BIG_PUNCH);
        }
        else {
            if (punchmode.hasModifier(BIG_PUNCH)) {
                punchmode.removeModifier(BIG_PUNCH);
            }
        }


    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.literal("Big punches for a big mask").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
}

}