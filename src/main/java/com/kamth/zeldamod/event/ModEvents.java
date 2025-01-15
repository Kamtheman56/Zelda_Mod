package com.kamth.zeldamod.event;


import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.block.ModBlocks;
import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.effect.ModEffects;
import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.entity.mobs.KorokEntity;
import com.kamth.zeldamod.entity.mobs.variants.KorokVariants;
import com.kamth.zeldamod.event.events.EntityMaskGoal;
import com.kamth.zeldamod.event.events.PlayerInteractionEvents;
import com.kamth.zeldamod.event.events.ZeldaPlayerHealth;
import com.kamth.zeldamod.event.events.ZeldaTrades;
import com.kamth.zeldamod.item.ModItems;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
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
import static com.kamth.zeldamod.item.items.z_sort_through_later.LensItem.LOOKING;


@Mod.EventBusSubscriber(modid = ZeldaMod.MOD_ID)
public class ModEvents {

    private static final EquipmentSlot HEAD = EquipmentSlot.HEAD;

    @SubscribeEvent
    public static void onPlayerEntityInteract(PlayerInteractEvent.EntityInteract event) {
        if (PlayerInteractionEvents.usingMask(event, ModItems.ROMANI_MASK.get())) {
            if (event.getTarget() instanceof Cow) {
                ItemStack itemstack = event.getEntity().getItemInHand(InteractionHand.MAIN_HAND);
                if (itemstack.is(Items.GLASS_BOTTLE)) {
                    itemstack.shrink(1);
                    event.getEntity().addItem(ModItems.MILK_BOTTLE.get().getDefaultInstance());
                    event.getTarget().playSound(SoundEvents.COW_MILK, 1, 1.8f);
                    event.getEntity().getItemBySlot(EquipmentSlot.HEAD).hurtAndBreak(4, event.getEntity(), ((p_43296_) ->
                            p_43296_.broadcastBreakEvent(EquipmentSlot.HEAD)));
                } else if (itemstack.is(Items.AIR)) {
                    event.getEntity().sendSystemMessage(Component.literal(event.getEntity().getName().getString() + " Knowing you're a friend to all cows, I'll show you how to put my milk into a bottle."));
                }
            }
        }
        if (PlayerInteractionEvents.usingMask(event, ModItems.GERO_MASK.get())) {
            if (event.getTarget() instanceof Frog) {
                event.getEntity().sendSystemMessage(Component.literal(event.getEntity().getName().getString() + "Try eating some Magma Cubes!"));
                event.getTarget().playSound(SoundEvents.FROG_AMBIENT, 1, 2.6f);
            }
        }
        if (PlayerInteractionEvents.usingMaskNotCreative(event, ModItems.COUPLES_MASK.get())) {
            if (event.getTarget() instanceof LivingEntity) {
                event.getEntity().hurt(event.getEntity().damageSources().magic(), 2);
                event.getTarget().playSound(ModSounds.HEAL.get(), 1, 1);
                ((LivingEntity) event.getTarget()).heal(2);
            }
        }
        if (PlayerInteractionEvents.usingMask(event, ModItems.COUPLES_MASK.get())) {
            if (event.getTarget() instanceof LivingEntity) {
                event.getLevel().addParticle(ParticleTypes.HEART, true, event.getTarget().getX() + 0, event.getTarget().getY() + .6, event.getTarget().getZ() + 0, 0, 0, 0);
            }
        }


        //This is how to obtain the Magic Boomerang
        if (!event.getLevel().isClientSide() && event.getHand() == InteractionHand.MAIN_HAND && !event.getEntity().getAbilities().instabuild && event.getEntity().getItemBySlot(EquipmentSlot.MAINHAND).getItem() == ModItems.BOOMERANG.get()) {
            if (event.getTarget() instanceof Allay) {
                event.getEntity().getUseItem().shrink(1);
                event.getTarget().discard();
                event.getTarget().spawnAtLocation(ModItems.MAGIC_BOOMERANG.get());
                event.getTarget().playSound(SoundEvents.ALLAY_AMBIENT_WITH_ITEM, 1, 2);
            }
        }
        // Ocarina Effects
        if (!event.getLevel().isClientSide() && event.getHand() == InteractionHand.MAIN_HAND && event.getEntity().getItemBySlot(EquipmentSlot.MAINHAND).getItem() == ModItems.OCARINA.get()) {
            if (event.getTarget() instanceof Horse) {
                ItemStack itemstack = event.getEntity().getItemInHand(InteractionHand.MAIN_HAND);
                event.getEntity().getCooldowns().addCooldown(ModItems.OCARINA.get(), 500);
                event.getTarget().playSound(ModSounds.SONG_EPONA.get(), 1, 1f);
                ((Horse) event.getTarget()).addEffect(new MobEffectInstance(MobEffects.REGENERATION, 80, 0));
            }
        }

        //Methods for obtaining Masks
        // Romani's Mask
        if (!event.getLevel().isClientSide() && event.getHand() == InteractionHand.MAIN_HAND && event.getEntity().getItemBySlot(EquipmentSlot.MAINHAND).getItem() == Items.GOLDEN_APPLE) {
            if (event.getTarget() instanceof Cow) {
                ItemStack itemstack = event.getEntity().getItemInHand(InteractionHand.MAIN_HAND);
                itemstack.shrink(1);
                event.getTarget().spawnAtLocation(ModItems.ROMANI_MASK.get());
                event.getTarget().playSound(SoundEvents.COW_AMBIENT, 1, 1.9f);
                event.getTarget().playSound(SoundEvents.HORSE_EAT, 1, 1.9f);
                ((Cow) event.getTarget()).addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 80));
            }}
        // Fairy Mask
        if (!event.getLevel().isClientSide() && event.getHand() == InteractionHand.MAIN_HAND && event.getEntity().getItemBySlot(EquipmentSlot.MAINHAND).getItem() == Items.GOLDEN_APPLE) {
            if (event.getTarget() instanceof Allay && event.getEntity().isCrouching()) {
                ItemStack itemstack = event.getEntity().getItemInHand(InteractionHand.MAIN_HAND);
                itemstack.shrink(1);
                event.getTarget().spawnAtLocation(ModItems.FAIRY_MASK.get());
                event.getTarget().playSound(SoundEvents.ALLAY_AMBIENT_WITH_ITEM, 1, 1.4f);
                event.getTarget().playSound(SoundEvents.HORSE_EAT, 1, 1.9f);
                event.getTarget().discard();
            }}
        // Bunny Hood
        if (!event.getLevel().isClientSide() && event.getHand() == InteractionHand.MAIN_HAND && event.getEntity().getItemBySlot(EquipmentSlot.MAINHAND).getItem() == Items.GOLDEN_CARROT) {
            if (event.getTarget() instanceof Rabbit && ((Rabbit) event.getTarget()).hasEffect(MobEffects.MOVEMENT_SPEED)) {
                ItemStack itemstack = event.getEntity().getItemInHand(InteractionHand.MAIN_HAND);
                itemstack.shrink(1);
                event.getTarget().spawnAtLocation(ModItems.BUNNY_MASK.get());
                event.getTarget().playSound(SoundEvents.ITEM_FRAME_REMOVE_ITEM, 1, 1.4f);
                event.getTarget().playSound(SoundEvents.HORSE_EAT, 1, 1.2f);
                event.getTarget().discard();
            }}
        // Gibdo Mask
        if (!event.getLevel().isClientSide() && event.getHand() == InteractionHand.MAIN_HAND && event.getEntity().getItemBySlot(EquipmentSlot.MAINHAND).getItem() == ModItems.OCARINA.get()) {
            if (event.getTarget() instanceof Husk && ((Husk) event.getTarget()).hasEffect(MobEffects.WEAKNESS)) {
                event.getTarget().spawnAtLocation(ModItems.GIBDO_MASK.get());
                event.getTarget().playSound(SoundEvents.ITEM_FRAME_REMOVE_ITEM, 1, 1.4f);
                event.getTarget().playSound(ModSounds.SONG_HEALING.get(), 1, 1f);
                event.getTarget().discard();
            }}
        if (!event.getLevel().isClientSide() && event.getHand() == InteractionHand.MAIN_HAND && event.getEntity().getItemBySlot(EquipmentSlot.MAINHAND).getItem() instanceof RecordItem) {
            if (event.getTarget() instanceof Husk && ((Husk) event.getTarget()).hasEffect(MobEffects.WEAKNESS)) {
                ItemStack itemstack = event.getEntity().getItemInHand(InteractionHand.MAIN_HAND);
                itemstack.shrink(1);
                event.getTarget().spawnAtLocation(ModItems.GIBDO_MASK.get());
                event.getTarget().playSound(SoundEvents.ITEM_FRAME_REMOVE_ITEM, 1, 1.4f);
                event.getTarget().playSound(ModSounds.SONG_HEALING.get(), 1, 1.3f);
                event.getTarget().discard();
            }}
        if (!event.getLevel().isClientSide() && event.getHand() == InteractionHand.MAIN_HAND && event.getEntity().getItemBySlot(EquipmentSlot.MAINHAND).getItem() == Items.GOAT_HORN) {
            if (event.getTarget() instanceof Husk && ((Husk) event.getTarget()).hasEffect(MobEffects.WEAKNESS)) {
                ItemStack itemstack = event.getEntity().getItemInHand(InteractionHand.MAIN_HAND);
                itemstack.shrink(1);
                event.getTarget().spawnAtLocation(ModItems.GIBDO_MASK.get());
                event.getTarget().playSound(SoundEvents.ITEM_FRAME_REMOVE_ITEM, 1, 1.4f);
                event.getTarget().playSound(SoundEvents.GOAT_HORN_PLAY, 1, 1.2f);
                event.getTarget().discard();
            }}
        // Captain's Mask
        if (!event.getLevel().isClientSide() && event.getHand() == InteractionHand.MAIN_HAND && event.getEntity().getItemBySlot(EquipmentSlot.MAINHAND).getItem() == Items.WITHER_SKELETON_SKULL) {
            if (event.getTarget() instanceof WitherSkeleton) {
                ItemStack itemstack = event.getEntity().getItemInHand(InteractionHand.MAIN_HAND);
                itemstack.shrink(1);
                event.getTarget().spawnAtLocation(ModItems.CAPTAIN_MASK.get());
                event.getTarget().playSound(SoundEvents.ITEM_FRAME_REMOVE_ITEM, 1, 1.4f);
                event.getTarget().playSound(SoundEvents.WITHER_SKELETON_DEATH, 1, 1.2f);
                event.getTarget().discard();
            }}
        // Korok Mask
        if (!event.getLevel().isClientSide() && event.getHand() == InteractionHand.MAIN_HAND && event.getEntity().getItemBySlot(EquipmentSlot.MAINHAND).getItem() == ModItems.OCARINA.get()) {
            if (event.getTarget() instanceof KorokEntity && ((KorokEntity) event.getTarget()).hasEffect(MobEffects.REGENERATION)) {
                event.getTarget().spawnAtLocation(ModItems.KOROK_MASK.get());
                event.getTarget().playSound(ModSounds.KOROK_LIKES.get(), 1, 1.4f);
                event.getTarget().playSound(ModSounds.SONG_SARIA.get(), 1, 1f);
                event.getTarget().discard();

            }
        }
        if (PlayerInteractionEvents.usingMask(event, ModItems.GERO_MASK.get())) {
            if (event.getTarget() instanceof Slime) {

                event.getTarget().discard();
                event.getTarget().playSound(SoundEvents.FROG_EAT, 1, 1);
                event.getTarget().playSound(SoundEvents.PLAYER_BURP, 1, 1);

                if (event.getTarget() instanceof MagmaCube) {
                    event.getTarget().spawnAtLocation(ModBlocks.CARMINE_FROGLIGHT.get());
                }
            }
        }

        //These are the Majoras Mask Effects
        if (PlayerInteractionEvents.usingMask(event, ModItems.MAJORA_MASK.get())) {
            if (event.getTarget() instanceof Pig) {
                event.getTarget().discard();
                event.getTarget().playSound(SoundEvents.PIG_HURT, 1, -4);
                event.getTarget().playSound(SoundEvents.AMBIENT_CAVE.get(), 1.2f, 0);
                event.getTarget().spawnAtLocation(ModBlocks.PORK_BLOCK.get());
            }
            if (!event.getLevel().isClientSide && event.getTarget() instanceof Creeper) {
                event.getTarget().setDeltaMovement(0, 1.5f, 0);
                ((Creeper) event.getTarget()).setHealth(1);
                event.getTarget().playSound(SoundEvents.FIREWORK_ROCKET_LAUNCH, 1, 1.5F);
                ((Creeper) event.getTarget()).ignite();
            }
            if (!event.getLevel().isClientSide && event.getTarget() instanceof Rabbit) {
                event.getTarget().discard();
                event.getTarget().playSound(SoundEvents.ITEM_FRAME_REMOVE_ITEM, 1.4f, 1.5F);
            }
            if (!event.getLevel().isClientSide && event.getTarget() instanceof Villager) {
                ((Villager) event.getTarget()).setBaby(true);
                event.getTarget().playSound(SoundEvents.AMBIENT_CAVE.get(), 1.2f, 1.2f);}

            if (event.getTarget() instanceof SnowGolem) {
                event.getTarget().discard();
                event.getTarget().playSound(ModSounds.OLD_HURT.get(), 1, 1);
                event.getTarget().playSound(SoundEvents.SNOW_BREAK, 1.2f, 0);
                event.getTarget().spawnAtLocation(ModBlocks.CLAY_PUMPKIN.get());
            } else {
                event.getTarget().setSecondsOnFire(400);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
            if(event.getSource().getEntity() instanceof Player player) {
                if(player.getMainHandItem().is(ModTags.Items.GLOOM_WEAPONS)) {
               player.hurt(player.damageSources().wither(),2);
                }
            }
        }
    @SubscribeEvent
    public static void HealMode(LivingHealEvent event){
        if (event.getEntity().getItemBySlot(EquipmentSlot.HEAD).is(ModItems.FAIRY_MASK.get()) && !event.getEntity().hasEffect(MobEffects.REGENERATION)){
            event.setAmount(event.getAmount() + 2);
        }
        if (event.getEntity().hasEffect(ModEffects.GLOOM.get())){
 event.setCanceled(true);
        }
    }


    private static boolean equipped(LivingEntity player, EquipmentSlot slot, Item item) {
        return item == player.getItemBySlot(slot).getItem();
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void TickEvents(TickEvent.PlayerTickEvent event){
        Item mask = ModItems.MAJORA_MASK.get();
        Player player = event.player;
        CompoundTag tag = player.getPersistentData();
        boolean wasFlying = tag.getBoolean("pastflight");
        if(!player.isCreative() && !player.isSpectator()){
            if((player.hasEffect(ModEffects.MAJORA.get()) || equipped(player, HEAD, mask))){
                if(!player.getAbilities().mayfly){
                    player.getAbilities().mayfly = true;
                    tag.putBoolean("pastflight", true);
                    player.onUpdateAbilities();
                }
              if((player.onGround() || player.isInWaterOrBubble() && equipped(player, HEAD, mask))){
                    player.getAbilities().flying = false;
                    player.onUpdateAbilities();
                }
            } else if(wasFlying && (!player.hasEffect(ModEffects.MAJORA.get()) || !equipped(player, HEAD, mask))) {
                player.getAbilities().mayfly = false;
                player.getAbilities().flying = false;
                tag.putBoolean("pastflight", false);
                player.onUpdateAbilities();}}
    }
    @SubscribeEvent
    public static void addNBTData(PlayerEvent.PlayerLoggedInEvent event){
        Player player = event.getEntity();
        CompoundTag tag = player.getPersistentData();
        if (tag.get("pastflight") == null) {
            tag.putBoolean("pastflight", false);
        }
        if (tag.get("activeflight") == null) {
            tag.putInt("activeflight", 0);
        }
    }
    @SubscribeEvent
    public static void onStruckByLightning(EntityStruckByLightningEvent event) {
        if (event.getEntity().getType() == ModEntityTypes.KOROK.get() && !event.getEntity().level().isClientSide) {
            ServerLevel level = (ServerLevel) event.getEntity().level();
            event.setCanceled(true);
            KorokEntity korok = ModEntityTypes.KOROK.get().create(event.getEntity().level());
            korok.moveTo(event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity().getYRot(), event.getEntity().getXRot());
            korok.finalizeSpawn(level, level.getCurrentDifficultyAt(korok.blockPosition()), MobSpawnType.CONVERSION, null, null);
            if (event.getEntity().hasCustomName()) {
                korok.setCustomName(event.getEntity().getCustomName());
                korok.setCustomNameVisible(event.getEntity().isCustomNameVisible());
            }
            korok.setVariant(KorokVariants.MUSHROOM);
            korok.setPersistenceRequired();
            level.addFreshEntityWithPassengers(korok);
            event.getEntity().discard();
        }
    }
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onLivingPreRender(RenderLivingEvent.Pre<LivingEntity, EntityModel<LivingEntity>> event) {
        if (event.getEntity().isInvisible()) {
            Minecraft client = Minecraft.getInstance();
            Player player = client.player;
            boolean LensMode = player.isUsingItem() && player.getItemInHand(player.getUsedItemHand()).getItem() == ModItems.LENS_OF_TRUTH.get() || player.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.TRUTH_MASK.get());
            if (LensMode) {
                removeEntityInvisibility(event.getEntity());}}
    }
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onLivingPostRender(RenderLivingEvent.Post<LivingEntity, EntityModel<LivingEntity>> event) {
        if (LOOKING.contains(event.getEntity())) {
            restoreEntityInvisibility(event.getEntity());
        }
    }

    @OnlyIn(Dist.CLIENT)
    private static void restoreEntityInvisibility(LivingEntity livingEntity) {
        LOOKING.remove(livingEntity);
        livingEntity.setInvisible(true);
    }

    @OnlyIn(Dist.CLIENT)
    private static void removeEntityInvisibility(LivingEntity livingEntity) {
        livingEntity.setInvisible(false);
        LOOKING.add(livingEntity);
    }

    //Controls Zoom in for Hawkeye and bows
    @SubscribeEvent
    public static void onFovUpdate(ComputeFovModifierEvent event) {
        LivingEntity player = event.getPlayer();
        Item item = player.getUseItem().getItem();
        if (event.getPlayer().getUseItem().getItem() instanceof BowItem && event.getPlayer().getItemBySlot(EquipmentSlot.HEAD).is(ModItems.HAWK_MASK.get()) && Minecraft.getInstance().options.getCameraType().isFirstPerson()) {
            float FOVModifier = player.getTicksUsingItem() / (float) BowItem.MAX_DRAW_DURATION;
            event.setNewFovModifier(event.getFovModifier() * (1.0f - FOVModifier * 1.3f));
        }
        if (event.getPlayer().getItemBySlot(EquipmentSlot.HEAD).is(ModItems.GORON_MASK.get()) && Minecraft.getInstance().options.getCameraType().isFirstPerson()) {
            event.setNewFovModifier(event.getFovModifier() * (0.9f - player.getSpeed() * 1.1f));
        }
        if(event.getPlayer().isUsingItem() && event.getPlayer().getUseItem().is(ModTags.Items.BOW_WEAPONS) && !event.getPlayer().getItemBySlot(EquipmentSlot.HEAD).is(ModItems.HAWK_MASK.get()) ){
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












