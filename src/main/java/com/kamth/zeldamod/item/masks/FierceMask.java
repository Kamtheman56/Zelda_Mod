package com.kamth.zeldamod.item.masks;

import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class FierceMask extends ArmorItem {
    private static final AttributeModifier STEP_HEIGHT_BONUS = new AttributeModifier(UUID.fromString("4a312f09-78e0-4f3a-95c2-07ed63212472"), "zeldamod:deitymask", 2, AttributeModifier.Operation.ADDITION);
    public FierceMask(ArmorMaterial pMaterial, Type type, Properties pProperties) {
        super(pMaterial, type, pProperties);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerTick);
        MinecraftForge.EVENT_BUS.addListener(this::onLivingHurtEvent);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {

        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 10, 5, true, false));
        if (player.getItemBySlot(EquipmentSlot.MAINHAND).is(ZeldaItems.FIERCE_SWORD.get())){
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 10, 10, true, false));
        }
        player.removeEffect(MobEffects.DARKNESS);
        player.removeEffect(MobEffects.WITHER);
        player.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
        player.removeEffect(MobEffects.WEAKNESS);
        player.removeEffect(MobEffects.HUNGER);
        player.removeEffect(MobEffects.BLINDNESS);

    }
    public void onLivingHurtEvent(LivingHurtEvent event){
        if (event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ZeldaItems.FIERCE_MASK.get()) {
            if (event.getSource().is(DamageTypes.MAGIC)) {
                event.setAmount(event.getAmount() - 2);
            }
            if (event.getSource().is(DamageTypes.WITHER)) {
                event.setAmount(event.getAmount() -4);
            }}}
    private void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.START) {
            return;
        }
        AttributeInstance stepHeight;
        stepHeight = event.player.getAttribute(ForgeMod.ENTITY_REACH.get());
        if (!stepHeight.hasModifier(STEP_HEIGHT_BONUS) && event.player instanceof Player && event.player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ZeldaItems.FIERCE_MASK.get()) {
            stepHeight.addTransientModifier(STEP_HEIGHT_BONUS);
        }
        else {
            if (stepHeight.hasModifier(STEP_HEIGHT_BONUS)) {
                stepHeight.removeModifier(STEP_HEIGHT_BONUS);
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
            components.add(Component.translatable("item.fierce_mask.description").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.ITALIC));
    }

}