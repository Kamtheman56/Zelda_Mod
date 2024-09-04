package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.block.ModBlocks;
import com.kamth.zeldamod.item.ModItems;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LogicalSidedProvider;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class OcarinaItem extends Item {
    public OcarinaItem(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);

            world.playSound(pPlayer, pPlayer.getOnPos(), ModSounds.OCARINA.get(), SoundSource.PLAYERS, .5f, 1.2f / (world.getRandom().nextFloat() * 0.4F + 0.8F));

        return InteractionResultHolder.consume(itemstack);
    }
    @SubscribeEvent
    public static void onWorldTick(TickEvent.ServerTickEvent event) {
        boolean was_played = false;
        if (was_played) {
            MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
            ServerLevel serverWorld = server.getLevel(Level.OVERWORLD);
            boolean shouldContinue = serverWorld.isNight();

            if (shouldContinue) {

            } else {
                was_played = false;
            }
        }
    }
    //if this is good enough for Toby Fox I can use all the if statements I want
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);
        //the effects of the song of warping
       if (blockstate.is(ModBlocks.OWL_STATUE.get())){
           pContext.getPlayer().startUsingItem(pContext.getHand());
           level.playSound(pContext.getPlayer(),pContext.getPlayer().getOnPos(), ModSounds.SONG_SOARING.get(),SoundSource.PLAYERS, .4f, 1);
           pContext.getPlayer().getCooldowns().addCooldown(this, 120);
           pContext.getPlayer().addEffect(new MobEffectInstance(MobEffects.CONFUSION,180, 2,true, false));
           return InteractionResult.SUCCESS;
       }
       //calls the effects of the song of time
        if (blockstate.is(ModBlocks.TIME_BLOCK.get())){
            pContext.getPlayer().getCooldowns().addCooldown(this, 210);
            level.playSound(pContext.getPlayer(),pContext.getPlayer().getOnPos(), ModSounds.SONG_TIME.get(),SoundSource.PLAYERS, .4f, 1);
            level.removeBlock(blockpos,false);
          level.setBlockAndUpdate(blockpos, ModBlocks.TIME_BLOCK_GHOST.get().defaultBlockState());
            return InteractionResult.SUCCESS;
        }
        if (blockstate.is(ModBlocks.TIME_BLOCK_GHOST.get())){
            level.playSound(pContext.getPlayer(),pContext.getPlayer().getOnPos(), ModSounds.SONG_TIME.get(),SoundSource.PLAYERS, .4f, 1.1f);
            pContext.getPlayer().getCooldowns().addCooldown(this, 220);
            level.removeBlock(blockpos,false);
            level.setBlockAndUpdate(blockpos, ModBlocks.TIME_BLOCK.get().defaultBlockState());
            return InteractionResult.SUCCESS;
        }
        //calls the effects of the sun song
        if (blockstate.is(ModBlocks.SUN_STONE.get())){
            level.playSound(pContext.getPlayer(),pContext.getPlayer().getOnPos(), ModSounds.SONG_SUN.get(),SoundSource.PLAYERS, .8f, 1f);
           pContext.getPlayer().getCooldowns().addCooldown(this, 600);
         if (level.isNight()){
                    MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
                    ServerLevel serverWorld = server.getLevel(Level.OVERWORLD);
             assert serverWorld != null;
             ( serverWorld).setDayTime(0);}
           else if (level.isDay()){
                MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
                ServerLevel serverWorld = server.getLevel(Level.OVERWORLD);
             assert serverWorld != null;
             ( serverWorld).setDayTime(13000);
            }
            return InteractionResult.SUCCESS;
        }
        //calls the effects of the song of storms
        if (blockstate.is(ModBlocks.NOTE_STONE.get()) && !level.isRaining()) {
            MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
            level.playSound(pContext.getPlayer(),pContext.getPlayer().getOnPos(), ModSounds.SONG_STORMS.get(),SoundSource.PLAYERS, .8f, 1f);
            ServerLevel serverWorld = server.getLevel(Level.OVERWORLD);
            serverWorld.setWeatherParameters(0, 3800, true, true);
            return InteractionResult.SUCCESS;
        }
        {
            if (blockstate.is(ModBlocks.NOTE_STONE.get()) && level.isRaining()) {
                MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
                level.playSound(pContext.getPlayer(),pContext.getPlayer().getOnPos(), ModSounds.SONG_STORMS.get(),SoundSource.PLAYERS, .8f, 1f);
                ServerLevel serverWorld = server.getLevel(Level.OVERWORLD);
                serverWorld.setWeatherParameters(0, 3800, false, false);
                return InteractionResult.SUCCESS;
            }
        }
        //song of healing for masks
        if (blockstate.is(ModBlocks.DEKU_GRAVE.get())) {
            pContext.getPlayer().getCooldowns().addCooldown(this, 210);
            level.playSound(pContext.getPlayer(),pContext.getPlayer().getOnPos(), ModSounds.SONG_HEALING.get(),SoundSource.PLAYERS, .5f, 1);
            level.removeBlock(blockpos,false);
            addItem(pContext.getPlayer(), ModItems.DEKU_MASK.get().asItem());
            return InteractionResult.SUCCESS;
        }
        if (blockstate.is(ModBlocks.GORON_GRAVE.get())) {
            pContext.getPlayer().getCooldowns().addCooldown(this, 210);
            level.playSound(pContext.getPlayer(),pContext.getPlayer().getOnPos(), ModSounds.SONG_HEALING.get(),SoundSource.PLAYERS, .5f, 1);
            level.removeBlock(blockpos,false);
            addItem(pContext.getPlayer(), ModItems.GORON_MASK.get().asItem());
            return InteractionResult.SUCCESS;
        }
        if (blockstate.is(ModBlocks.ZORA_GRAVE.get())) {
            pContext.getPlayer().getCooldowns().addCooldown(this, 210);
            level.playSound(pContext.getPlayer(),pContext.getPlayer().getOnPos(), ModSounds.SONG_HEALING.get(),SoundSource.PLAYERS, .5f, 1);
            level.removeBlock(blockpos,false);
       addItem(pContext.getPlayer(), ModItems.ZORA_MASK.get().asItem());
            return InteractionResult.SUCCESS;

        }
        else return InteractionResult.PASS;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level worldIn, LivingEntity entity, int pTimeLeft) {
        if (entity instanceof Player player) {
                if (!worldIn.isClientSide) {
                    ServerPlayer player2 = (ServerPlayer) entity;
                    BlockPos bedLocation = player2.getRespawnPosition(); // find bed in current dimension first
                    if (bedLocation == null) {
                        player2.displayClientMessage(Component.translatable("Ocarina").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.BOLD), true);
                        return;}
                    if(player.level().dimension() != player2.getRespawnDimension()) {
                        LogicalSidedProvider.WORKQUEUE.get(LogicalSide.SERVER);
                        ServerLevel transferWorld = ((ServerLevel)worldIn).getServer().getLevel(player2.getRespawnDimension());

                        player2.teleportTo(transferWorld, bedLocation.getX() + 0.5D, bedLocation.getY() + 0.6D, bedLocation.getZ() + 0.5D, player.getRotationVector().x, player.getRotationVector().y);
                    } else {
                        ((ServerPlayer) entity).teleportTo(bedLocation.getX() + 0.5D, bedLocation.getY() + 0.6D, bedLocation.getZ() + 0.5D);
                        entity.resetFallDistance();
                    }
                    entity.fallDistance = 0;
                }
                player.awardStat(Stats.ITEM_USED.get(this));
        }
    }
    private void addItem(Player player, Item itemSupplier)
    {
        if (!player.addItem(new ItemStack(itemSupplier)))
        {
            player.spawnAtLocation(itemSupplier);
        }
    }
    @Override
    public @NotNull UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.TOOT_HORN;
    }
    @Override
    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }

    
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.translatable("item.ocarina.description").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.ITALIC));
        }
        super.appendHoverText(stack, level, components, flag);
    }
   }






