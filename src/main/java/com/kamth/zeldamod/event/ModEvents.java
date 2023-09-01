package com.kamth.zeldamod.event;


import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.item.ModItems;


import it.unimi.dsi.fastutil.ints.Int2ObjectMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.frog.Frog;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;


import net.minecraftforge.event.village.VillagerTradesEvent;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fml.common.Mod;


import java.util.List;


import static com.kamth.zeldamod.item.items.LensItem.AFFECTED_ENTITIES;


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
            else if (itemstack.is(Items.AIR)){
                    event.getEntity().sendSystemMessage(Component.literal(event.getEntity().getName().getString() + " Knowing you're a friend to all cows, I'll show you how to put my milk into a bottle."));

    }
            }}
        if( event.getLevel().isClientSide() && event.getHand() == InteractionHand.MAIN_HAND && event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.GERO_MASK.get()) {
            if (event.getTarget() instanceof Frog)
            {
                event.getEntity().sendSystemMessage(Component.literal(event.getEntity().getName().getString() + " Please bring me to some magma cubes!"));
                    event.getTarget().playSound(SoundEvents.FROG_AMBIENT, 1, 2.6f);
                }}}

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onLivingPreRender(RenderLivingEvent.Pre<LivingEntity, EntityModel<LivingEntity>> event) {
        if (event.getEntity().isInvisible()) {
            Minecraft client = Minecraft.getInstance();
            Player player = client.player;
            boolean isLocalPlayerUsingLens = player.isUsingItem() && player.getItemInHand(player.getUsedItemHand()).getItem() == ModItems.LENS_OF_TRUTH.get();

            if (isLocalPlayerUsingLens) {
                removeEntityInvisibility(event.getEntity());
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onLivingPostRender(RenderLivingEvent.Post<LivingEntity, EntityModel<LivingEntity>> event) {
        if (AFFECTED_ENTITIES.contains(event.getEntity())) {
            restoreEntityInvisibility(event.getEntity());
        }
    }

    @OnlyIn(Dist.CLIENT)
    private static void restoreEntityInvisibility(LivingEntity livingEntity) {
        AFFECTED_ENTITIES.remove(livingEntity);
        livingEntity.setInvisible(true);
    }

    @OnlyIn(Dist.CLIENT)
    private static void removeEntityInvisibility(LivingEntity livingEntity) {
        livingEntity.setInvisible(false);
        AFFECTED_ENTITIES.add(livingEntity);
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






    }


}







