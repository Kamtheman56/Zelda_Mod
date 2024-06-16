package com.kamth.zeldamod.item.items;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.LogicalSidedProvider;
import net.minecraftforge.fml.LogicalSide;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class FluteItem extends Item {
    public FluteItem(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        pPlayer.startUsingItem(pHand);
        world.addParticle(ParticleTypes.NOTE,true,0,0,0,0,0,0);
        world.playSound(pPlayer, pPlayer.getOnPos(), SoundEvents.NOTE_BLOCK_FLUTE.get(), SoundSource.PLAYERS, .5f, 1);
        return InteractionResultHolder.consume(itemstack);
    }


    @Override
    public void releaseUsing(ItemStack stack, Level worldIn, LivingEntity entity, int pTimeLeft) {
        if (entity instanceof Player player) {
                if (!worldIn.isClientSide) {
                    ServerPlayer player2 = (ServerPlayer) entity;
                    BlockPos bedLocation = player2.getRespawnPosition(); // find bed in current dimension first
                    if (bedLocation == null) {
                        player2.displayClientMessage(Component.translatable("Ocarina").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.BOLD), true);
                        return;
                    }     if(player.level().dimension() != player2.getRespawnDimension()) {
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
    @Override
    public @NotNull UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.TOOT_HORN;
    }


    @Override
    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }

    @Override
    public void onUseTick(Level pLevel, LivingEntity entity, ItemStack pStack, int pRemainingUseDuration) {
        int j = this.getUseDuration(pStack) - pRemainingUseDuration;
        if(entity.level().isClientSide) {
                RandomSource rand = entity.level().random;
                for (int i = 0; i < 60; i++) {
                    double particleX = entity.getX() + (entity.getRandom().nextBoolean() ? -0.1D : 0);
                    double particleY = entity.getY() + entity.getRandom().nextFloat() * 0 - -1.2D;
                    double particleZ = entity.getZ() + (entity.getRandom().nextBoolean() ? -0.1D : 0);
               //     entity.level().addParticle(ParticleTypes.NOTE, particleX, particleY, particleZ, 0, 0, 0);
                  //  entity.level().addParticle(ParticleTypes.NOTE, entity.blockPosition().getX() + (rand.nextBoolean() ? -1 : 1) * Math.pow(rand.nextFloat(), 2) * 3, entity.blockPosition().getY() + rand.nextFloat() * 4 - 2, entity.blockPosition().getZ() + (rand.nextBoolean() ? -1 : 1) * Math.pow(rand.nextFloat(), 2) * 3, 0, 0.2D, 0);
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.literal("Always return home with the wind").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.ITALIC));
        }
        super.appendHoverText(stack, level, components, flag);
    }
   }






