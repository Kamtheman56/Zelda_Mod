package com.kamth.zeldamod.item.masks;

import be.florens.expandability.api.forge.PlayerSwimEvent;
import com.google.common.eventbus.Subscribe;
import com.kamth.zeldamod.item.ModItems;
import net.minecraft.client.model.EntityModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.Event;
import org.apache.commons.logging.LogFactory;

import java.util.UUID;



public class ZoraMask extends ArmorItem {
    private static final AttributeModifier ZORA_SWIMSPEED = new AttributeModifier(UUID.fromString("4a312f09-78e0-4f3a-95c2-07ed63212472"), "zeldamod:zoramask", 2, AttributeModifier.Operation.ADDITION);



    public ZoraMask(ArmorMaterial p_40386_, EquipmentSlot p_40387_, Properties p_40388_) {
        super(p_40386_, p_40387_, p_40388_);
        MinecraftForge.EVENT_BUS.addListener(this::LivingFallEvent);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerSwim);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerTick);

    }

    //this adds effects that do not create particles.
    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {

        player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 10, 10, true, false));
        player.addEffect(new MobEffectInstance(MobEffects.CONDUIT_POWER, 10, 0, true, false));

        if (player.isSwimming()) {
            player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 10, 0, true, false, false));
        }

        {
            {
                


            }
        }

    }

    public void LivingFallEvent(LivingFallEvent event) {
        if (event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.ZORA_MASK.get()) {
            if (event.getEntity().isEyeInFluidType(ForgeMod.WATER_TYPE.get())) {
                event.setCanceled(true);

            }


        }


    }
  

    public void onPlayerSwim (PlayerSwimEvent event){

        if (event.getEntity().isUnderWater() && event.getEntity().isCrouching()) {

            event.setResult(Event.Result.DENY);


        }
    }
//This calls the swim speed attribute to be used! Gotta go fast
    private void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.START) {
            return;
        }





        AttributeInstance zoraswim = event.player.getAttribute(ForgeMod.SWIM_SPEED.get());




        if (event.player.isSprinting() && event.player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.ZORA_MASK.get()) {

            }
            if (!zoraswim.hasModifier(ZORA_SWIMSPEED) && event.player instanceof Player && event.player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.ZORA_MASK.get()) {
                zoraswim.addTransientModifier(ZORA_SWIMSPEED);
            }


         else {

            if (zoraswim.hasModifier(ZORA_SWIMSPEED)) {
                zoraswim.removeModifier(ZORA_SWIMSPEED);






        }
    }}

}
