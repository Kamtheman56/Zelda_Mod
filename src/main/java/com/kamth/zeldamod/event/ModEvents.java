package com.kamth.zeldamod.event;


import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.block.ModBlocks;
import com.kamth.zeldamod.entity.ai.*;
import com.kamth.zeldamod.item.ModItems;
import com.kamth.zeldamod.sound.ModSounds;
import com.kamth.zeldamod.villager.ModVillagers;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import static com.kamth.zeldamod.item.items.LensItem.LOOKING;


@Mod.EventBusSubscriber(modid = ZeldaMod.MOD_ID)
public class ModEvents {
    boolean isPulling = false;

    @SubscribeEvent
    public static void onPlayerEntityInteract(PlayerInteractEvent.EntityInteract event)
    {
        if( !event.getLevel().isClientSide() && event.getHand() == InteractionHand.MAIN_HAND && event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.ROMANI_MASK.get()) {
            if (event.getTarget() instanceof Cow)
            {
                ItemStack itemstack = event.getEntity().getItemInHand(InteractionHand.MAIN_HAND);
                if (itemstack.is(Items.GLASS_BOTTLE)) {
                    itemstack.shrink(1);
                    event.getEntity().addItem(ModItems.MILK_BOTTLE1.get().getDefaultInstance());
                    event.getTarget().playSound(SoundEvents.COW_MILK, 1, 1.8f);
                    event.getEntity().getItemBySlot(EquipmentSlot.HEAD).hurtAndBreak(4, event.getEntity(), ((p_43296_) ->
                            p_43296_.broadcastBreakEvent(EquipmentSlot.HEAD)));
                }
            else if (itemstack.is(Items.AIR)){event.getEntity().sendSystemMessage(Component.literal(event.getEntity().getName().getString() + " Knowing you're a friend to all cows, I'll show you how to put my milk into a bottle."));}
            }}
        if( event.getLevel().isClientSide() && event.getHand() == InteractionHand.MAIN_HAND && event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.GERO_MASK.get()) {
            if (event.getTarget() instanceof Frog){
                event.getEntity().sendSystemMessage(Component.literal(event.getEntity().getName().getString() + " Please bring me to some magma cubes!"));
                    event.getTarget().playSound(SoundEvents.FROG_AMBIENT, 1, 2.6f);}}
        if(!event.getLevel().isClientSide() && event.getHand() == InteractionHand.MAIN_HAND && event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.COUPLES_MASK.get()) {
            if (event.getTarget() instanceof LivingEntity)
            {
                event.getEntity().hurt(event.getEntity().damageSources().magic(), 2);
                event.getTarget().playSound(ModSounds.HEAL.get(), 1, 1f);
                ((LivingEntity) event.getTarget()).heal(2);
            }}
        if(event.getLevel().isClientSide() && event.getHand() == InteractionHand.MAIN_HAND && event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.COUPLES_MASK.get()) {
            if (event.getTarget() instanceof LivingEntity)
            {
                event.getLevel().addParticle(ParticleTypes.HEART, true, event.getTarget().getX() +0, event.getTarget().getY() +.6, event.getTarget().getZ() +0, 0, 0, 0);
            }}

        //These are the Majoras Mask Effects
        if( !event.getLevel().isClientSide && event.getHand() == InteractionHand.MAIN_HAND && event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.MAJORA_MASK.get())  {
            if (event.getTarget() instanceof Pig)
            {
                event.getTarget().discard();
                event.getTarget().playSound(SoundEvents.PIG_HURT,1, -4);
                event.getTarget().playSound(SoundEvents.AMBIENT_CAVE.get(),1.2f, 0);
                event.getTarget().spawnAtLocation(ModBlocks.PORK_BLOCK.get());}

        if (!event.getLevel().isClientSide && event.getTarget() instanceof Creeper)
        {

            event.getTarget().setDeltaMovement(0,1,0);
            ((Creeper) event.getTarget()).setHealth(1);
            event.getTarget().playSound(SoundEvents.FIREWORK_ROCKET_LAUNCH,1, 1.5F);

        }
        if (!event.getLevel().isClientSide && event.getTarget() instanceof Villager)
        {
           ((Villager) event.getTarget()).setBaby(true);
            event.getTarget().playSound(SoundEvents.AMBIENT_CAVE.get(),1.2f, 1.2f);
        }
        }
    }



    @SubscribeEvent
    public static void HealMode(LivingHealEvent event){
        if (event.getEntity().getItemBySlot(EquipmentSlot.HEAD).is(ModItems.FAIRY_MASK.get()) && !event.getEntity().hasEffect(MobEffects.REGENERATION)){
            event.setAmount(event.getAmount() + 2);
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
    @SubscribeEvent
    public static void onFovUpdate(ComputeFovModifierEvent event) {
        LivingEntity player = event.getPlayer();
        Item item = player.getUseItem().getItem();
        if (event.getPlayer().getUseItem().getItem() instanceof BowItem && event.getPlayer().getItemBySlot(EquipmentSlot.HEAD).is(ModItems.HAWK_MASK.get()) && Minecraft.getInstance().options.getCameraType().isFirstPerson()) {
            float FOVModifier = player.getTicksUsingItem() / (float) BowItem.MAX_DRAW_DURATION;
            event.setNewFovModifier(event.getFovModifier() * (1.0f - FOVModifier * 1.4f));
        }
        if (event.getPlayer().getItemBySlot(EquipmentSlot.HEAD).is(ModItems.GORON_MASK.get()) && Minecraft.getInstance().options.getCameraType().isFirstPerson()) {
            event.setNewFovModifier(event.getFovModifier() * (0.9f - player.getSpeed() * 1.1f));
        }
        }


    @SubscribeEvent
    public static void onEntityConstructing(EntityJoinLevelEvent event)
    {
        if (event.getEntity() instanceof Animal animal)
        {
            animal.goalSelector.addGoal(3, new BremenMask(animal, 1.3D));
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
                    stack,3,1,0.02F));
        }
        if(event.getType() == VillagerProfession.WEAPONSMITH) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.RAZOR_SWORD.get(), 1);
            int villagerLevel = 2;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 15), new ItemStack(ModItems.KOKIRI_SWORD.get()),
                    stack,2,3,0.02F));
        }
        if(event.getType() == VillagerProfession.WEAPONSMITH) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.GILDED_SWORD.get(), 1);
            int villagerLevel = 5;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(ModItems.RAZOR_SWORD.get(), 1), new ItemStack(ModItems.GOLD_DUST.get()),
                    stack,2,5,0.02F));
        }
        if(event.getType() == VillagerProfession.FLETCHER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.FIRE_ARROW.get(), 5);
            int villagerLevel = 3;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 8),
                    stack,4,5,0.02F));
        }
        if(event.getType() == VillagerProfession.FLETCHER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.ICE_ARROW.get(), 5);
            int villagerLevel = 3;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 8),
                    stack,5,5,0.04F));
        }
        if(event.getType() == VillagerProfession.FLETCHER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
        ItemStack stack = new ItemStack(ModItems.LIGHT_ARROW.get(), 5);
            int villagerLevel = 5;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 24),
                    stack,5,5,0.03F));
        }
        if(event.getType() == VillagerProfession.FLETCHER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.BOMB_ARROW.get(), 5);
            int villagerLevel = 5;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 24),
                    stack,3,5,0.08F));
        }
        if(event.getType() == VillagerProfession.FLETCHER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.SLINGSHOT.get(), 1);
            int villagerLevel = 2;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 5),
                    stack,3,5,0.02F));
        }
        if(event.getType() == VillagerProfession.ARMORER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.BOMB.get(), 3);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    stack,8,5,0.02F));
        }
        if(event.getType() == ModVillagers.MASK_TRADER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.KEATON_MASK.get(), 1);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    stack,2,5,0.02F));
        }
        if(event.getType() == ModVillagers.MASK_TRADER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.KAFEI_MASK.get(), 1);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 8),
                    stack,2,6,0.02F));
        }
//make more trades dude

    }





}







