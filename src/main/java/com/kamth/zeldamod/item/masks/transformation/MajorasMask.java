package com.kamth.zeldamod.item.masks.transformation;

import com.kamth.zeldamod.block.ZeldaBlocks;
import com.kamth.zeldamod.effect.ModEffects;
import com.kamth.zeldamod.item.ZeldaItems;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.npc.Villager;
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

public class MajorasMask extends ArmorItem {
    public MajorasMask(ArmorMaterial pMaterial, Type type, Properties pProperties) {
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
        if (!stepHeight.hasModifier(STEP_HEIGHT_BONUS) && event.player instanceof Player  && event.player.hasEffect(ModEffects.MAJORA.get()) && event.player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ZeldaItems.MAJORA_MASK.get()) {
            stepHeight.addTransientModifier(STEP_HEIGHT_BONUS);
            event.player.getAbilities().setFlyingSpeed(.1f);
        }
//sends the player downward and removes the increased flight speed
        else if (event.player.getItemBySlot(EquipmentSlot.HEAD).getItem() != ZeldaItems.MAJORA_MASK.get()) {
            if (stepHeight.hasModifier(STEP_HEIGHT_BONUS)) {
                event.player.removeEffect(ModEffects.MAJORA.get());
                stepHeight.removeModifier(STEP_HEIGHT_BONUS);
                event.player.setDeltaMovement(0,-200,0);
                event.player.getAbilities().setFlyingSpeed(.05f);
                event.player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 20, 0));
                event.player.causeFoodExhaustion(12);
            }
        }}


    public void onLivingHurtEvent(LivingHurtEvent event){
        if (event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ZeldaItems.MAJORA_MASK.get()) {
            if (event.getSource().is(DamageTypes.MAGIC)) {
                event.setAmount(event.getAmount() * 4);
            }
            if (event.getSource().is(DamageTypes.WITHER)) {
                event.setAmount(event.getAmount() * 2);
            }
        }
    }

    public static void onInteract(Player user, Entity target) {

        if (target instanceof Pig) {

            target.discard();
            target.playSound(SoundEvents.PIG_HURT, 1, -4);
            target.playSound(SoundEvents.AMBIENT_CAVE.get(), 1.2f, 0);
            target.spawnAtLocation(ZeldaBlocks.PORK_BLOCK.get());

        }

        if (!target.level().isClientSide && target instanceof Creeper creeper) {

            target.setDeltaMovement(0, 1.5f, 0);
            target.playSound(SoundEvents.FIREWORK_ROCKET_LAUNCH, 1, 1.5f);
            creeper.setHealth(1);
            creeper.ignite();

        }

        if (!target.level().isClientSide && target instanceof Rabbit) {

            target.discard();
            target.playSound(SoundEvents.ITEM_FRAME_REMOVE_ITEM, 1.4f, 1.5F);

        }

        if (!target.level().isClientSide && target instanceof Villager villager) {

            villager.setBaby(true);
            villager.playSound(SoundEvents.AMBIENT_CAVE.get(), 1.2f, 1.2f);

        }

        if (target instanceof SnowGolem) {

            target.discard();
            target.playSound(ModSounds.OLD_HURT.get(), 1, 1);
            target.playSound(SoundEvents.SNOW_BREAK, 1.2f, 0);
            target.spawnAtLocation(ZeldaBlocks.CLAY_PUMPKIN.get());

        } else {

            target.setSecondsOnFire(400);

        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.translatable("item.zeldamod.majora_mask.description").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.UNDERLINE));

        if (Screen.hasShiftDown()) {
            components.add(Component.translatable("item.zeldamod.majora_mask.description_advanced").withStyle(ChatFormatting.DARK_RED));
            components.add(Component.translatable("item.zeldamod.majora_mask.description_advanced2").withStyle(ChatFormatting.DARK_RED));
        }
    }

}
