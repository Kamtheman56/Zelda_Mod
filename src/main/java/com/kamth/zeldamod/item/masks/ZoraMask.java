package com.kamth.zeldamod.item.masks;

import be.florens.expandability.api.forge.PlayerSwimEvent;
import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
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
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.Event;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;


//Todo add a keybinding to start spin attack

public class ZoraMask extends ArmorItem {
    private static final AttributeModifier ZORA_SWIMSPEED = new AttributeModifier(UUID.fromString("4a312f09-78e0-4f3a-95c2-07ed63212472"), "zeldamod:zoramask", 2.6, AttributeModifier.Operation.ADDITION);



    public ZoraMask(ArmorMaterial p_40386_, Type type, Properties p_40388_) {
        super(p_40386_, type, p_40388_);
        MinecraftForge.EVENT_BUS.addListener(this::LivingFallEvent);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerSwim);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerTick);
        MinecraftForge.EVENT_BUS.addListener(this::onLivingHurtEvent);
    }
    //Mask that gives higher swim control

    //this adds effects that do not create particles.


    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {

        player.addEffect(new MobEffectInstance(MobEffects.CONDUIT_POWER, 10, 0, true, false));
        if (player.isSwimming()) {
            player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 10, 0, true, false, false));
               // player.startAutoSpinAttack(20);
            }
        if (player.getItemBySlot(EquipmentSlot.MAINHAND).getItem() == ItemStack.EMPTY.getItem() ){
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 10, 0, true, false));}
//disadvantage state
      if (player.isOnFire() && player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ZeldaItems.ZORA_MASK.get() ){
          player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 10, 2, true, false));
          player.removeEffect(MobEffects.DAMAGE_BOOST);
          player.setTicksFrozen(0);}
      if (world.isRaining()){
          player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 10, 1, true, false));}

//Logic for standing on blocks = effects
        Level level = world;
        if (level.getBlockState(player.getOnPos()).getBlock() == Blocks.ICE) {
player.setTicksFrozen(200);}
        if (level.getBlockState(player.getOnPos()).getBlock() == Blocks.SNOW_BLOCK) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 2, true, false, true));
            player.setTicksFrozen(80);}
        if (level.getBlockState(player.getOnPos()).getBlock() == Blocks.BLUE_ICE) {
            player.setTicksFrozen(450);}
        if (level.getBlockState(player.getOnPos()).getBlock() == Blocks.PACKED_ICE) {
            player.setTicksFrozen(310);}}
    public void LivingFallEvent(LivingFallEvent event) {
        if (event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ZeldaItems.ZORA_MASK.get()) {
            if (event.getEntity().isEyeInFluidType(ForgeMod.WATER_TYPE.get())) {
                event.setCanceled(true);}}}
    public void onLivingHurtEvent(LivingHurtEvent event){
        if (event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ZeldaItems.ZORA_MASK.get()) {
            if (event.getSource().is(DamageTypes.FREEZE)) {
                event.setAmount(event.getAmount() * 3);}
            if (event.getSource().is(DamageTypes.LIGHTNING_BOLT)) {
                event.setAmount(event.getAmount() * 4);}
            if (event.getSource().is(DamageTypes.ON_FIRE)) {
                event.setAmount(event.getAmount() * 2);}
        }
    }
    public void onPlayerSwim (PlayerSwimEvent event){
        if (event.getEntity().isUnderWater() && event.getEntity().isCrouching() && event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ZeldaItems.ZORA_MASK.get()) {
            event.setResult(Event.Result.DENY);}}

    private void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.START) {
            return;}
        AttributeInstance zoraswim = event.player.getAttribute(ForgeMod.SWIM_SPEED.get());
        if (event.player.isSprinting() && event.player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ZeldaItems.ZORA_MASK.get()) {}
            if (!zoraswim.hasModifier(ZORA_SWIMSPEED) && event.player instanceof Player && event.player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ZeldaItems.ZORA_MASK.get()) {
                zoraswim.addTransientModifier(ZORA_SWIMSPEED);}
         else {

                if (zoraswim.hasModifier(ZORA_SWIMSPEED)) {
                    zoraswim.removeModifier(ZORA_SWIMSPEED);}}}


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            components.add(Component.translatable("item.zora_mask.description_advanced").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.ITALIC));
        } else {
            components.add(Component.translatable("item.zora_mask.description_basic").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.ITALIC));
        }}}




