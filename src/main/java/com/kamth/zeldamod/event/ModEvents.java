package com.kamth.zeldamod.event;


import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.effect.ModEffects;
import com.kamth.zeldamod.entity.mobs.KorokEntity;
import com.kamth.zeldamod.event.events.*;
import com.kamth.zeldamod.item.ZeldaItems;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = ZeldaMod.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onPlayerEntityInteract(PlayerInteractEvent.EntityInteract event) {
        Player user = event.getEntity();
        Entity target = event.getTarget();

        PlayerInteractionEvents.maskInteractionEvents(event, user, target);

        PlayerInteractionEvents.interactionCraftEvents(event, user, target);

        // Ocarina Effects
        if (PlayerInteractionEvents.craftWithItemInteraction(event, ZeldaItems.OCARINA.get())) {
            if (target instanceof Horse horse) {
                user.getCooldowns().addCooldown(ZeldaItems.OCARINA.get(), 500);
                target.playSound(ModSounds.SONG_EPONA.get(), 1, 1f);
                horse.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 80, 0));
            }
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (player.getMainHandItem().is(ModTags.Items.GLOOM_WEAPONS)) {
                player.hurt(player.damageSources().wither(),2);
            }
        }
    }
    @SubscribeEvent
    public static void HealMode(LivingHealEvent event){
        if (event.getEntity().getItemBySlot(EquipmentSlot.HEAD).is(ZeldaItems.FAIRY_MASK.get()) && !event.getEntity().hasEffect(MobEffects.REGENERATION)){
            event.setAmount(event.getAmount() + 2);
        }
        if (event.getEntity().hasEffect(ModEffects.GLOOM.get())){
            event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void TickEvents(TickEvent.PlayerTickEvent event){
        MajoraMaskFlight.tickFlight(event);
    }

    @SubscribeEvent
    public static void addNBTData (PlayerEvent.PlayerLoggedInEvent event) {
        MajoraMaskFlight.addFlightNBT(event);
    }

    @SubscribeEvent
    public static void onStruckByLightning(EntityStruckByLightningEvent event) {
        KorokEntity.convertFromLightning(event);
    }



    // TODO: Change FOV UPDATE
    @SubscribeEvent
    public static void onFovUpdate(ComputeFovModifierEvent event) {
        LivingEntity player = event.getPlayer();
        Item item = player.getUseItem().getItem();
        if (event.getPlayer().getUseItem().getItem() instanceof BowItem && event.getPlayer().getItemBySlot(EquipmentSlot.HEAD).is(ZeldaItems.HAWKEYE_MASK.get()) && Minecraft.getInstance().options.getCameraType().isFirstPerson()) {
            float FOVModifier = player.getTicksUsingItem() / (float) BowItem.MAX_DRAW_DURATION;
            event.setNewFovModifier(event.getFovModifier() * (1.0f - FOVModifier * 1.3f));
        }
        if (event.getPlayer().getItemBySlot(EquipmentSlot.HEAD).is(ZeldaItems.GORON_MASK.get()) && Minecraft.getInstance().options.getCameraType().isFirstPerson()) {
            event.setNewFovModifier(event.getFovModifier() * (0.9f - player.getSpeed() * 1.1f));
        }
        if (event.getPlayer().isUsingItem() && event.getPlayer().getUseItem().is(ModTags.Items.BOW_WEAPONS) && !event.getPlayer().getItemBySlot(EquipmentSlot.HEAD).is(ZeldaItems.HAWKEYE_MASK.get()) ){
            float fovModifier = 1f;
            int ticksUsingItem = event.getPlayer().getTicksUsingItem();
            float deltaTicks = (float)ticksUsingItem / 20f;
            if(deltaTicks > 1f) {
                deltaTicks = 1f;
            } else {
                deltaTicks *= deltaTicks;
            }
            fovModifier *= 1f - deltaTicks * 0.15f;
            event.setNewFovModifier(fovModifier);
        }
    }

    @SubscribeEvent
    public static void joinLevelEvent(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Player player) {
            ZeldaPlayerHealth.adjustBaseHealth(player);
        }

        if (event.getEntity() instanceof Mob mob) {
            EntityMaskGoal.assignMaskGoals(mob);
        }
    }

    @SubscribeEvent
    public static void reapplyHealthModifiers(PlayerEvent.Clone event) {
        ZeldaPlayerHealth.reapplyHealthModifiers(event);
    }

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        ZeldaTrades.addRegularTrades(event);
    }

    @SubscribeEvent
    public static void addCustomWanderingTrades(WandererTradesEvent event) {
        ZeldaTrades.addWanderingTrades(event);
    }
}