package com.kamth.zeldamod.item.masks;

import be.florens.expandability.api.forge.PlayerSwimEvent;
import com.kamth.zeldamod.item.ModItems;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.Event;

import java.util.UUID;


//Todo add a keybinding to start spin attack

public class ZoraMask extends ArmorItem {
    private static final AttributeModifier ZORA_SWIMSPEED = new AttributeModifier(UUID.fromString("4a312f09-78e0-4f3a-95c2-07ed63212472"), "zeldamod:zoramask", 2, AttributeModifier.Operation.ADDITION);



    public ZoraMask(ArmorMaterial p_40386_, EquipmentSlot p_40387_, Properties p_40388_) {
        super(p_40386_, p_40387_, p_40388_);
        MinecraftForge.EVENT_BUS.addListener(this::LivingFallEvent);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerSwim);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerTick);

    }
    //Mask that gives higher swim control

    //this adds effects that do not create particles.


    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {

        player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 10, 10, true, false));
        player.addEffect(new MobEffectInstance(MobEffects.CONDUIT_POWER, 10, 0, true, false));

        if (player.isSwimming()) {
            player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 10, 0, true, false, false));

               // player.startAutoSpinAttack(20);
            }


        if (player.getItemBySlot(EquipmentSlot.MAINHAND).getItem() == ItemStack.EMPTY.getItem() ){
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 10, 0, true, false));
        }
//disadvantage state
      if (player.isOnFire()){
          player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 10, 2, true, false));
          player.removeEffect(MobEffects.DAMAGE_BOOST);
      }
//Logic for standing on blocks = effects
        Level level = world;
        if (level.getBlockState(player.getOnPos()).getBlock() == Blocks.ICE) {
player.setTicksFrozen(250);


        }
        if (level.getBlockState(player.getOnPos()).getBlock() == Blocks.SNOW_BLOCK) {

            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 2, true, false, true));
            player.setTicksFrozen(80);


        }
        if (level.getBlockState(player.getOnPos()).getBlock() == Blocks.BLUE_ICE) {
            player.setTicksFrozen(400);}}

    public void LivingFallEvent(LivingFallEvent event) {
        if (event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.ZORA_MASK.get()) {
            if (event.getEntity().isEyeInFluidType(ForgeMod.WATER_TYPE.get())) {
                event.setCanceled(true);

            }}}

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
