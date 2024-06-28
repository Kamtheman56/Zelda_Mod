package com.kamth.zeldamod.item.masks;

import com.kamth.zeldamod.effect.ModEffects;
import com.kamth.zeldamod.item.ModItems;
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

public class MajoraMask extends ArmorItem {
    public MajoraMask(ArmorMaterial pMaterial, Type type, Properties pProperties) {
        super(pMaterial, type, pProperties);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerTick);
        MinecraftForge.EVENT_BUS.addListener(this::onLivingHurtEvent);
    }
    private static final AttributeModifier STEP_HEIGHT_BONUS = new AttributeModifier(UUID.fromString("4a312f09-78e0-4f3a-95c2-07ed63212483"), "zeldamod:majora", 2, AttributeModifier.Operation.ADDITION);


    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        player.addEffect(new MobEffectInstance(ModEffects.MAJORA.get(), 10, 0, true, false));
if (player.isOnFire()){
    player.setRemainingFireTicks(0);}}

    private void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.START) {
            return;
        }

        AttributeInstance stepHeight;
        stepHeight = event.player.getAttribute(ForgeMod.ENTITY_REACH.get());
        if (!stepHeight.hasModifier(STEP_HEIGHT_BONUS) && event.player instanceof Player  && event.player.hasEffect(ModEffects.MAJORA.get()) && event.player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.MAJORA_MASK.get()) {
            stepHeight.addTransientModifier(STEP_HEIGHT_BONUS);
            event.player.getAbilities().setFlyingSpeed(.1f);
        }
//sends the player downward and removes the increased flight speed
        else if (event.player.getItemBySlot(EquipmentSlot.HEAD).getItem() != ModItems.MAJORA_MASK.get()) {
            if (stepHeight.hasModifier(STEP_HEIGHT_BONUS)) {
                event.player.removeEffect(ModEffects.MAJORA.get());
                stepHeight.removeModifier(STEP_HEIGHT_BONUS);
                event.player.setDeltaMovement(0,-200,0);
                event.player.getAbilities().setFlyingSpeed(.05f);
                event.player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 20, 0));
                event.player.causeFoodExhaustion(12);
            }
        }}
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
            components.add(Component.translatable("item.majora_mask.description").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.UNDERLINE));
    }
    public void onLivingHurtEvent(LivingHurtEvent event){
        if (event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.MAJORA_MASK.get()) {
            if (event.getSource().is(DamageTypes.MAGIC)) {
                event.setAmount(event.getAmount() * 4);
            }
            if (event.getSource().is(DamageTypes.WITHER)) {
                event.setAmount(event.getAmount() * 2);
            }}}

}
