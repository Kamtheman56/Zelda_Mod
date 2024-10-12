package com.kamth.zeldamod.event;


import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.block.ModBlocks;
import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.effect.ModEffects;
import com.kamth.zeldamod.entity.ai.*;
import com.kamth.zeldamod.item.ModItems;
import com.kamth.zeldamod.sound.ModSounds;
import com.kamth.zeldamod.villager.ModVillagers;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.kamth.zeldamod.event.ModEvents.PlayerHealthEvents.HEARTS_MODIFIER;
import static com.kamth.zeldamod.event.ModEvents.PlayerHealthEvents.getMaxHealthAttribute;
import static com.kamth.zeldamod.item.items.LensItem.LOOKING;


@Mod.EventBusSubscriber(modid = ZeldaMod.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onPlayerEntityInteract(PlayerInteractEvent.EntityInteract event) {
        if (!event.getLevel().isClientSide() && event.getHand() == InteractionHand.MAIN_HAND && event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.ROMANI_MASK.get()) {
            if (event.getTarget() instanceof Cow) {
                ItemStack itemstack = event.getEntity().getItemInHand(InteractionHand.MAIN_HAND);
                if (itemstack.is(Items.GLASS_BOTTLE)) {
                    itemstack.shrink(1);
                    event.getEntity().addItem(ModItems.MILK_BOTTLE1.get().getDefaultInstance());
                    event.getTarget().playSound(SoundEvents.COW_MILK, 1, 1.8f);
                    event.getEntity().getItemBySlot(EquipmentSlot.HEAD).hurtAndBreak(4, event.getEntity(), ((p_43296_) ->
                            p_43296_.broadcastBreakEvent(EquipmentSlot.HEAD)));
                } else if (itemstack.is(Items.AIR)) {
                    event.getEntity().sendSystemMessage(Component.literal(event.getEntity().getName().getString() + " Knowing you're a friend to all cows, I'll show you how to put my milk into a bottle."));
                }
            }
        }
        if (event.getLevel().isClientSide() && event.getHand() == InteractionHand.MAIN_HAND && event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.GERO_MASK.get()) {
            if (event.getTarget() instanceof Frog) {
                event.getEntity().sendSystemMessage(Component.literal(event.getEntity().getName().getString() + "Try eating some Magma Cubes!"));
                event.getTarget().playSound(SoundEvents.FROG_AMBIENT, 1, 2.6f);
            }
        }
        if (!event.getLevel().isClientSide() && event.getHand() == InteractionHand.MAIN_HAND && !event.getEntity().getAbilities().instabuild && event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.COUPLES_MASK.get()) {
            if (event.getTarget() instanceof LivingEntity) {
                event.getEntity().hurt(event.getEntity().damageSources().magic(), 2);
                event.getTarget().playSound(ModSounds.HEAL.get(), 1, 1f);
                ((LivingEntity) event.getTarget()).heal(2);
            }
        }
        if (event.getLevel().isClientSide() && event.getHand() == InteractionHand.MAIN_HAND && event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.COUPLES_MASK.get()) {
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
            }}


        //Methods for obtaining Masks
        if (!event.getLevel().isClientSide() && event.getHand() == InteractionHand.MAIN_HAND && event.getEntity().getItemBySlot(EquipmentSlot.MAINHAND).getItem() == Items.GOLDEN_APPLE) {
            if (event.getTarget() instanceof Cow) {
                ItemStack itemstack = event.getEntity().getItemInHand(InteractionHand.MAIN_HAND);
                itemstack.shrink(1);
                event.getTarget().spawnAtLocation(ModItems.ROMANI_MASK.get());
                event.getTarget().playSound(SoundEvents.COW_AMBIENT, 1, 1.9f);
                event.getTarget().playSound(SoundEvents.HORSE_EAT, 1, 1.9f);
                ((Cow) event.getTarget()).addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 80));
            }}
        if (!event.getLevel().isClientSide() && event.getHand() == InteractionHand.MAIN_HAND && event.getEntity().getItemBySlot(EquipmentSlot.MAINHAND).getItem() == Items.GOLDEN_APPLE) {
            if (event.getTarget() instanceof Allay && event.getEntity().isCrouching()) {
                ItemStack itemstack = event.getEntity().getItemInHand(InteractionHand.MAIN_HAND);
                itemstack.shrink(1);
                event.getTarget().spawnAtLocation(ModItems.FAIRY_MASK.get());
                event.getTarget().playSound(SoundEvents.ALLAY_AMBIENT_WITH_ITEM, 1, 1.4f);
                event.getTarget().playSound(SoundEvents.HORSE_EAT, 1, 1.9f);
                event.getTarget().discard();
            }}
        if (!event.getLevel().isClientSide() && event.getHand() == InteractionHand.MAIN_HAND && event.getEntity().getItemBySlot(EquipmentSlot.MAINHAND).getItem() == Items.GOLDEN_CARROT) {
            if (event.getTarget() instanceof Rabbit && ((Rabbit) event.getTarget()).hasEffect(MobEffects.MOVEMENT_SPEED)) {
                ItemStack itemstack = event.getEntity().getItemInHand(InteractionHand.MAIN_HAND);
                itemstack.shrink(1);
                event.getTarget().spawnAtLocation(ModItems.BUNNY_MASK.get());
                event.getTarget().playSound(SoundEvents.ITEM_FRAME_REMOVE_ITEM, 1, 1.4f);
                event.getTarget().playSound(SoundEvents.HORSE_EAT, 1, 1.2f);
                event.getTarget().discard();
            }}
            if (!event.getLevel().isClientSide() && event.getHand() == InteractionHand.MAIN_HAND && event.getEntity().getItemBySlot(EquipmentSlot.MAINHAND).getItem() == ModItems.OCARINA.get()) {
                if (event.getTarget() instanceof Husk && ((Husk) event.getTarget()).hasEffect(MobEffects.WEAKNESS)) {
                    ItemStack itemstack = event.getEntity().getItemInHand(InteractionHand.MAIN_HAND);
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
        if (!event.getLevel().isClientSide() && event.getHand() == InteractionHand.MAIN_HAND && event.getEntity().getItemBySlot(EquipmentSlot.MAINHAND).getItem() == Items.WITHER_SKELETON_SKULL) {
            if (event.getTarget() instanceof WitherSkeleton) {
                ItemStack itemstack = event.getEntity().getItemInHand(InteractionHand.MAIN_HAND);
                itemstack.shrink(1);
                event.getTarget().spawnAtLocation(ModItems.CAPTAIN_MASK.get());
                event.getTarget().playSound(SoundEvents.ITEM_FRAME_REMOVE_ITEM, 1, 1.4f);
                event.getTarget().playSound(SoundEvents.WITHER_SKELETON_DEATH, 1, 1.2f);
                event.getTarget().discard();
            }}
        if (!event.getLevel().isClientSide && event.getHand() == InteractionHand.MAIN_HAND && event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.GERO_MASK.get()) {
            if (event.getTarget() instanceof MagmaCube) {
                event.getTarget().discard();
                event.getTarget().playSound(SoundEvents.FROG_EAT, 1, 1);
                event.getTarget().playSound(SoundEvents.PLAYER_BURP, 1, 1);
                event.getTarget().spawnAtLocation(ModBlocks.CARMINE_FROGLIGHT.get());
            }}
        if (!event.getLevel().isClientSide && event.getHand() == InteractionHand.MAIN_HAND && event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.GERO_MASK.get()) {
            if (event.getTarget() instanceof Slime) {
                event.getTarget().discard();
                event.getTarget().playSound(SoundEvents.FROG_EAT, 1, 1);
                event.getTarget().playSound(SoundEvents.PLAYER_BURP, 1, 1);
            }}

            //These are the Majoras Mask Effects
            if (!event.getLevel().isClientSide && event.getHand() == InteractionHand.MAIN_HAND && event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.MAJORA_MASK.get()) {
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
                }


            else event.getTarget().setSecondsOnFire(400);
            }}

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
    private static final EquipmentSlot head = EquipmentSlot.HEAD;


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
            if((player.hasEffect(ModEffects.MAJORA.get()) || equipped(player, head, mask))){
                if(!player.getAbilities().mayfly){
                    player.getAbilities().mayfly = true;
                    tag.putBoolean("pastflight", true);
                    player.onUpdateAbilities();
                }
              if((player.onGround() || player.isInWaterOrBubble() && equipped(player, head, mask))){
                    player.getAbilities().flying = false;
                    player.onUpdateAbilities();
                }
            } else if(wasFlying && (!player.hasEffect(ModEffects.MAJORA.get()) || !equipped(player, head, mask))) {
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
    @SubscribeEvent
    public static void restoreHealthModifiers(PlayerEvent.Clone event) {
        if (!event.isWasDeath()) return;
        AttributeInstance originalMaxHealth = getMaxHealthAttribute(event.getOriginal());
        AttributeModifier modifier = originalMaxHealth.getModifier(HEARTS_MODIFIER);
        if (modifier != null) {
            AttributeInstance cloneMaxHealth = getMaxHealthAttribute(event.getEntity());
            cloneMaxHealth.addPermanentModifier(modifier);
            // Also updates current health
            event.getEntity().setHealth(event.getEntity().getMaxHealth());
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
        }


    public class PlayerHealthEvents {
        public static final UUID BASE_HEALTH_MODIFIER = UUID.fromString("6ed6de9f-a743-4bee-8e59-8a56d54bb054");
        public static final UUID HEARTS_MODIFIER = UUID.fromString("3dc4214d-14eb-455c-9700-a2ab1433dfcc");

        @SubscribeEvent
        public static void adjustBaseHealth(EntityJoinLevelEvent event) {
            // Only change health of players
            if (!(event.getEntity() instanceof Player player)) return;
            AttributeInstance maxHealth = player.getAttribute(Attributes.MAX_HEALTH);
            AttributeModifier baseModifier = new AttributeModifier(BASE_HEALTH_MODIFIER, "Base", 20 - 18, AttributeModifier.Operation.ADDITION);
            // Add base modifier only if not added yet
            Objects.requireNonNull(maxHealth);
            if (!maxHealth.hasModifier(baseModifier)) {
                maxHealth.addPermanentModifier(baseModifier);
            }

            else {
                AttributeModifier oldModifier = maxHealth.getModifier(BASE_HEALTH_MODIFIER);
                Objects.requireNonNull(oldModifier);
                if (oldModifier.getAmount() != baseModifier.getAmount()) {
                    // Remove old instance and apply new one
                    maxHealth.removeModifier(BASE_HEALTH_MODIFIER);
                    maxHealth.addPermanentModifier(baseModifier);
                }
            }
            // Fixing health being higher then max health
            if (player.getHealth() > player.getMaxHealth()) {
                player.setHealth(player.getMaxHealth());
            }
        }
        @SubscribeEvent
        public static void reapplyHealthModifiers(PlayerEvent.Clone event) {
            if (!event.isWasDeath()) return;
            AttributeInstance originalMaxHealth = getMaxHealthAttribute(event.getOriginal());
            AttributeModifier modifier = originalMaxHealth.getModifier(HEARTS_MODIFIER);
            if (modifier != null) {
                AttributeInstance cloneMaxHealth = getMaxHealthAttribute(event.getEntity());
                cloneMaxHealth.addPermanentModifier(modifier);
                // Also updates current health
                event.getEntity().setHealth(event.getEntity().getMaxHealth());
            }
        }


        public static double getBaseHealth(Player player) {
            AttributeInstance maxHealth = getMaxHealthAttribute(player);
            double baseHealth = maxHealth.getBaseValue();
            AttributeModifier heartsModifier = maxHealth.getModifier(HEARTS_MODIFIER);
            if (heartsModifier != null) baseHealth += heartsModifier.getAmount();
            AttributeModifier baseModifier = maxHealth.getModifier(PlayerHealthEvents.BASE_HEALTH_MODIFIER);
            if (baseModifier != null) baseHealth += baseModifier.getAmount();
            return baseHealth;
        }


        public static void addBaseHealthModifier(Player player, float amount) {
            AttributeInstance maxHealth = getMaxHealthAttribute(player);
            AttributeModifier modifier = maxHealth.getModifier(HEARTS_MODIFIER);
            if (modifier == null) {
                modifier = new AttributeModifier(HEARTS_MODIFIER, "Hearts", amount, AttributeModifier.Operation.ADDITION);
            } else {
                maxHealth.removeModifier(modifier);
                modifier = new AttributeModifier(HEARTS_MODIFIER, "Hearts", modifier.getAmount() + amount, AttributeModifier.Operation.ADDITION);
            }
            maxHealth.addPermanentModifier(modifier);
            if (amount > 0) {
                player.heal(amount);
            } else if (player.getHealth() > player.getMaxHealth()) {
                player.setHealth(player.getMaxHealth());
            }
        }

        public static boolean canIncreaseGoldHealth(Player player) {
            return getBaseHealth(player) < 60;
        }
        @NotNull
        static AttributeInstance getMaxHealthAttribute(Player player) {
            AttributeInstance attribute = player.getAttribute(Attributes.MAX_HEALTH);
            return Objects.requireNonNull(attribute);
        }

        public static boolean canIncreaseBaseHealth(Player player) {
            return getBaseHealth(player) < 40;
        }

        public static boolean canDecreaseBaseHealth(Player player) {
            return getBaseHealth(player) > 2;
        }
    }





    @SubscribeEvent
    public static void onEntityConstructing(EntityJoinLevelEvent event)
    {
        if (event.getEntity() instanceof Animal chicken)
        {
            chicken.goalSelector.addGoal(3, new BremenMask(chicken, 1.3D));
        }
        if (event.getEntity() instanceof Allay allay)
        {
            allay.goalSelector.addGoal(3, new FairyMask(allay, 1.2D));
        }
        if (event.getEntity() instanceof Husk allay)
        {
            allay.goalSelector.addGoal(1, new GibdoMask(allay));
        }
        if (event.getEntity() instanceof Skeleton allay)
        {
            allay.goalSelector.addGoal(1, new CaptainMask(allay));
        }
        if (event.getEntity() instanceof Piglin allay)
        {
            allay.goalSelector.addGoal(1, new KamaroMask(allay));
        }
    }



    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {

        if(event.getType() == VillagerProfession.WEAPONSMITH) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.KOKIRI_SWORD.get(), 1);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    stack,3,6,0.02F));
        }

        if(event.getType() == VillagerProfession.WEAPONSMITH) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.RAZOR_SWORD.get(), 1);
            int villagerLevel = 2;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 15), new ItemStack(ModItems.KOKIRI_SWORD.get()),
                    stack,2,30,0.02F));
        }
        if(event.getType() == VillagerProfession.WEAPONSMITH) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.GILDED_SWORD.get(), 1);
            int villagerLevel = 5;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(ModItems.RAZOR_SWORD.get(), 1), new ItemStack(ModItems.GOLD_DUST.get()),
                    stack,2,65,0.02F));
        }
        if(event.getType() == VillagerProfession.FLETCHER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.FIRE_ARROW.get(), 5);
            int villagerLevel = 3;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 8),
                    stack,5,18,0.02F));
        }
        if(event.getType() == VillagerProfession.FLETCHER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.ICE_ARROW.get(), 5);
            int villagerLevel = 4;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 8),
                    stack,5,38,0.04F));
        }
        if(event.getType() == VillagerProfession.FLETCHER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
        ItemStack stack = new ItemStack(ModItems.LIGHT_ARROW.get(), 5);
            int villagerLevel = 5;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 24),
                    stack,2,60,0.03F));
        }
        if(event.getType() == VillagerProfession.FLETCHER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.BOMB_ARROW.get(), 5);
            int villagerLevel = 3;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 24),
                    stack,3,18,0.08F));
        }
        if(event.getType() == VillagerProfession.FLETCHER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.HAWK_MASK.get(), 1);
            int villagerLevel = 5;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 24),
                    stack,1,18,30F));
        }
        if(event.getType() == VillagerProfession.FLETCHER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.SLINGSHOT.get(), 1);
            int villagerLevel = 2;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    stack,3,18,0.02F));
        }
        if(event.getType() == VillagerProfession.ARMORER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.BOMB.get(), 3);
            int villagerLevel = 1;


            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    stack,8,2,0.02F));
        }

        //Mask Trader Trades
        if(event.getType() == ModVillagers.MASK_TRADER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.KEATON_MASK.get(), 1);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    stack,2,6,0.02F));
        }
        if(event.getType() == ModVillagers.MASK_TRADER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.GERO_MASK.get(), 1);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    stack,2,6,0.02F));
        }
        if(event.getType() == ModVillagers.MASK_TRADER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.GERUDO_MASK.get(), 1);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    stack,2,5,0.02F));
        }
        if(event.getType() == ModVillagers.MASK_TRADER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.SPOOKY_MASK.get(), 1);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    stack,2,4,0.02F));
        }
        if(event.getType() == ModVillagers.MASK_TRADER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.SKULL_MASK.get(), 1);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    stack,2,6,0.02F));
        }
        if(event.getType() == ModVillagers.MASK_TRADER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.COUPLES_MASK.get(), 1);
            int villagerLevel = 2;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 8),
                    stack,2,35,0.02F));
        }
        if(event.getType() == ModVillagers.MASK_TRADER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.KAMARO_MASK.get(), 1);
            int villagerLevel = 2;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    stack,2,35,0.05F));
        }
        if(event.getType() == ModVillagers.MASK_TRADER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.SCENT_MASK.get(), 1);
            int villagerLevel = 2;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 7),
                    stack,2,35,0.02F));
        }
        if(event.getType() == ModVillagers.MASK_TRADER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.TROUPE_MASK.get(), 1);
            int villagerLevel = 2;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    stack,2,35,0.02F));
        }
        if(event.getType() == ModVillagers.MASK_TRADER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.BLAST_MASK.get(), 1);
            int villagerLevel = 3;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 14),
                    stack,2,75,0.02F));
        }
        if(event.getType() == ModVillagers.MASK_TRADER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.BREMEN_MASK.get(), 1);
            int villagerLevel = 3;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 14),
                    stack,2,75,0.02F));
        }
        if(event.getType() == ModVillagers.MASK_TRADER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.BUNNY_MASK.get(), 1);
            int villagerLevel = 3;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    stack,2,75,0.04F));
        }
        if(event.getType() == ModVillagers.MASK_TRADER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.KAFEI_MASK.get(), 1);
            int villagerLevel = 4;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 18),
                    stack,1,125,0.03F));
        }

        if(event.getType() == ModVillagers.MASK_TRADER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.TRUTH_MASK.get(), 1);
            int villagerLevel = 4;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 18),
                    stack,1,125,0.03F));
        }
        if(event.getType() == ModVillagers.MASK_TRADER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.DEKU_MASK.get(), 1);
            int villagerLevel = 4;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    stack,1,125,0.06F));
        }
        if(event.getType() == ModVillagers.MASK_TRADER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.GORON_MASK.get(), 1);
            int villagerLevel = 5;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 22),
                    stack,1,250,0.06F));
        }
        if(event.getType() == ModVillagers.MASK_TRADER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.ZORA_MASK.get(), 1);
            int villagerLevel = 5;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 28),
                    stack,1,250,0.07F));
        }
        if(event.getType() == ModVillagers.MASK_TRADER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.POSTMAN_MASK.get(), 1);
            int villagerLevel = 5;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 35),
                    stack,1,250,0.07F));
        }
//make more trades dude

    }
    @SubscribeEvent
    public static void addCustomWanderingTrades(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();

        rareTrades.add((pTrader, pRandom) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 16),
                new ItemStack(ModItems.SPOOKY_MASK.get(), 1),
                2, 12, 0.15f));
        rareTrades.add((pTrader, pRandom) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 12),
                new ItemStack(ModItems.GERUDO_MASK.get(), 1),
                2, 12, 0.15f));
        rareTrades.add((pTrader, pRandom) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 13),
                new ItemStack(ModItems.SKULL_MASK.get(), 1),
                2, 12, 0.15f));
    }
    }












