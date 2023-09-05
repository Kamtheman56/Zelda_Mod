package com.kamth.zeldamod.event;


import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.item.ModItems;


import com.kamth.zeldamod.villager.ModVillagers;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.frog.Frog;

import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;

import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;


import net.minecraftforge.event.village.VillagerTradesEvent;

import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fml.common.Mod;


import java.util.List;



import static com.kamth.zeldamod.item.items.LensItem.IN_SIGHT;


@Mod.EventBusSubscriber(modid = ZeldaMod.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void onPlayerEntityInteract(PlayerInteractEvent.EntityInteract event)
    {
        if( !event.getLevel().isClientSide() && event.getHand() == InteractionHand.MAIN_HAND && event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.ROMANI_MASK.get()) {
            if (event.getTarget() instanceof Cow)
            {
                ItemStack itemstack = event.getEntity().getItemInHand(InteractionHand.MAIN_HAND);
                if (itemstack.is(Items.GLASS_BOTTLE)){
                    itemstack.shrink(1);
                    event.getEntity().addItem(ModItems.MILK_BOTTLE1.get().getDefaultInstance());
                    event.getTarget().playSound(SoundEvents.COW_MILK, 1, 1.8f);
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
                event.getTarget().playSound(SoundEvents.VILLAGER_CELEBRATE, 1, 2.6f);
                ((LivingEntity) event.getTarget()).heal(2);
            }}
        if(event.getLevel().isClientSide() && event.getHand() == InteractionHand.MAIN_HAND && event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.COUPLES_MASK.get()) {
            if (event.getTarget() instanceof LivingEntity)
            {
                event.getLevel().addParticle(ParticleTypes.HEART, true, event.getTarget().getX() +0, event.getTarget().getY() +.6, event.getTarget().getZ() +0, 0, 0, 0);
            }}

    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onLivingPreRender(RenderLivingEvent.Pre<LivingEntity, EntityModel<LivingEntity>> event) {
        if (event.getEntity().isInvisible()) {
            Minecraft client = Minecraft.getInstance();
            Player player = client.player;
            boolean LensMode = player.isUsingItem() && player.getItemInHand(player.getUsedItemHand()).getItem() == ModItems.LENS_OF_TRUTH.get() || player.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.TRUTH_MASK.get());

            if (LensMode) {
                removeEntityInvisibility(event.getEntity());
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onLivingPostRender(RenderLivingEvent.Post<LivingEntity, EntityModel<LivingEntity>> event) {
        if (IN_SIGHT.contains(event.getEntity())) {
            restoreEntityInvisibility(event.getEntity());
        }
    }

    @OnlyIn(Dist.CLIENT)
    private static void restoreEntityInvisibility(LivingEntity livingEntity) {
        IN_SIGHT.remove(livingEntity);
        livingEntity.setInvisible(true);
    }

    @OnlyIn(Dist.CLIENT)
    private static void removeEntityInvisibility(LivingEntity livingEntity) {
        livingEntity.setInvisible(false);
        IN_SIGHT.add(livingEntity);
    }
    @SubscribeEvent
    public static void onFovUpdate(ComputeFovModifierEvent event) {
        LivingEntity player = event.getPlayer();
        Item item = player.getUseItem().getItem();
        if (event.getPlayer().getUseItem().getItem() instanceof BowItem && event.getPlayer().getItemBySlot(EquipmentSlot.HEAD).is(ModItems.HAWK_MASK.get()) && Minecraft.getInstance().options.getCameraType().isFirstPerson()) {
            float FOVModifier = player.getTicksUsingItem() / (float) BowItem.MAX_DRAW_DURATION;
            event.setNewFovModifier(event.getFovModifier() * (1.0f - FOVModifier * 1.4f));
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
                    stack,1,5,0.02F));
        }

    }





}







