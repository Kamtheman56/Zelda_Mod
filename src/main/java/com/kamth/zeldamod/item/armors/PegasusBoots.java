package com.kamth.zeldamod.item.armors;

import com.kamth.zeldamod.item.ModItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;

import java.util.UUID;


@SuppressWarnings("ReassignedVariable")
public class PegasusBoots extends ArmorItem {
    private static final AttributeModifier STEP_HEIGHT_BONUS = new AttributeModifier(UUID.fromString("a3ec65b9-710d-4ab9-b2d9-16bc07743a78"), "zeldamod:pegasusboots", 0.5, AttributeModifier.Operation.ADDITION);

    public PegasusBoots(ArmorMaterial p_40386_, Type type, Properties p_40388_) {
        super(p_40386_, type, p_40388_);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerTick);

    }


    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {

        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 10, 2, true, false));}
    private void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.START) {
            return;
        }
        AttributeInstance stepHeight;
        stepHeight = event.player.getAttribute(ForgeMod.STEP_HEIGHT_ADDITION.get());

        if (event.player.isSprinting() && event.player.getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.PEGASUS_BOOTS.get()) {
        }
        if (!stepHeight.hasModifier(STEP_HEIGHT_BONUS) && event.player instanceof Player && event.player.getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.PEGASUS_BOOTS.get()) {
            stepHeight.addTransientModifier(STEP_HEIGHT_BONUS);
        }
        else {
            if (stepHeight.hasModifier(STEP_HEIGHT_BONUS)) {
                stepHeight.removeModifier(STEP_HEIGHT_BONUS);
            }}}
    }





