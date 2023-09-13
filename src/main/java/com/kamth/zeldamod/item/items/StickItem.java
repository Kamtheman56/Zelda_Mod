package com.kamth.zeldamod.item.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.kamth.zeldamod.item.ModItems;
import com.kamth.zeldamod.item.custom.util.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class StickItem extends Item {

    public StickItem(Properties pProperties) {
        super(pProperties);
    }




    public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        return !pPlayer.isCreative();
    }
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if (pAttacker.getItemBySlot(EquipmentSlot.MAINHAND).is(ModItems.DEKU_STICK_LIT.get())){
            pTarget.hurt(pTarget.damageSources().magic(), 5);
            pTarget.setRemainingFireTicks(40);
            pAttacker.level().playSound( pAttacker, pTarget.getOnPos(), SoundEvents.ITEM_BREAK, SoundSource.BLOCKS, 1.0F, pAttacker.level().getRandom().nextFloat() * 0.4F + 0.8F);
            pStack.shrink(1);

        }
   else     pTarget.hurt(pTarget.damageSources().magic(), 5);
        pAttacker.playSound(SoundEvents.ITEM_BREAK);
        pAttacker.level().playSound( pAttacker, pTarget.getOnPos(), SoundEvents.ITEM_BREAK, SoundSource.BLOCKS, 1.0F, pAttacker.level().getRandom().nextFloat() * 0.4F + 0.8F);
        pStack.shrink(1);
        return true;

    }


    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);
        ItemStack itemstack = pContext.getItemInHand();
        if (blockstate.is(ModTags.Blocks.FLAME)  && pContext.getPlayer().getMainHandItem().is(ModItems.DEKU_STICK.get())) {
            level.playSound( pContext.getPlayer(), blockpos, SoundEvents.ITEM_BREAK, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
            itemstack.shrink(1);
           pContext.getPlayer().addItem(ModItems.DEKU_STICK_LIT.get().getDefaultInstance());
            level.addParticle(ParticleTypes.FLAME, blockpos.getX() , blockpos.getY() + .5f, blockpos.getZ(), 0, 0, 0);
            level.addParticle(ParticleTypes.FLAME, blockpos.getX(), blockpos.getY() , blockpos.getZ() , 0, 0, 0);
            return InteractionResult.SUCCESS;
        }

        if (blockstate.is(ModTags.Blocks.BURN) && pContext.getPlayer().getMainHandItem().is(ModItems.DEKU_STICK_LIT.get())) {
          level.destroyBlock(blockpos,false, pContext.getPlayer());
            level.addParticle(ParticleTypes.FLAME, blockpos.getX() , blockpos.getY() + .5f, blockpos.getZ(), 0, 0, 0);
            level.addParticle(ParticleTypes.FLAME, blockpos.getX(), blockpos.getY() , blockpos.getZ() , 0, 0, 0);
            itemstack.shrink(1);
            return InteractionResult.SUCCESS;
        }

        if (!CampfireBlock.canLight(blockstate) && !CandleBlock.canLight(blockstate) && !CandleCakeBlock.canLight(blockstate) && pContext.getPlayer().getMainHandItem().is(ModItems.DEKU_STICK_LIT.get())) {
            BlockPos blockpos1 = blockpos.relative(pContext.getClickedFace());
            if (BaseFireBlock.canBePlacedAt(level, blockpos1, pContext.getHorizontalDirection())) {
                level.playSound( pContext.getPlayer(), blockpos1, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                BlockState blockstate1 = BaseFireBlock.getState(level, blockpos1);
                level.setBlock(blockpos1, blockstate1, 11);
                level.gameEvent( pContext.getPlayer(), GameEvent.BLOCK_PLACE, blockpos);

                if ( pContext.getPlayer() instanceof ServerPlayer) {
                    CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer) pContext.getPlayer(), blockpos1, itemstack);
                    pContext.getPlayer().playSound(SoundEvents.ITEM_BREAK);
                  itemstack.shrink(1);}
                return InteractionResult.sidedSuccess(level.isClientSide());
            }}
        else {
        }
        return InteractionResult.FAIL;}


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.literal("This seems flammable").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
    else {
            components.add(Component.literal("5 Attack Damage").withStyle(ChatFormatting.DARK_GREEN));
            components.add(Component.literal("1.6 Attack Speed").withStyle(ChatFormatting.DARK_GREEN));
        }

    }
}