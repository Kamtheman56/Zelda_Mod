package com.kamth.zeldamod.item.armors;

import com.kamth.zeldamod.item.ModItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;

import java.util.UUID;


public class Flippers extends ArmorItem {
    private static final AttributeModifier FLIPPERS = new AttributeModifier(UUID.fromString("90998b0e-4188-403a-b3e5-1987d251d350"), "zeldamod:flipper", 1.5, AttributeModifier.Operation.ADDITION);


    public Flippers(ArmorMaterial material, Type type, Properties settings) {
        super(material, type, settings);

        MinecraftForge.EVENT_BUS.addListener(this::onPlayerTick);
    }
    private void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.START) {
            return;}
        AttributeInstance flipper = event.player.getAttribute(ForgeMod.SWIM_SPEED.get());
        if (event.player.isSprinting() && event.player.getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.FLIPPERS.get()) {}
        if (!flipper.hasModifier(FLIPPERS) && event.player instanceof Player && event.player.getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.FLIPPERS.get()) {
            flipper.addTransientModifier(FLIPPERS);}


        else {

            if (flipper.hasModifier(FLIPPERS)) {
                flipper.removeModifier(FLIPPERS);}}}

}



