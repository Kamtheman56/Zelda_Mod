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
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class StickItem extends TieredItem {
    private final float attackDamage;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

public StickItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
    super(pTier, pProperties);
    this.attackDamage = (float)pAttackDamageModifier + pTier.getAttackDamageBonus();
    ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
    builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", (double)this.attackDamage, AttributeModifier.Operation.ADDITION));
    builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", (double)pAttackSpeedModifier, AttributeModifier.Operation.ADDITION));
    this.defaultModifiers = builder.build();
}
    public float getDamage() {
        return this.attackDamage;
    }

    public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        return !pPlayer.isCreative();
    }
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pStack.hurtAndBreak(1, pAttacker, (p_43296_) -> {
            p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        return true;
    }
    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pEquipmentSlot) {
        return pEquipmentSlot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(pEquipmentSlot);
    }

    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);

        if (blockstate.is(ModTags.Blocks.FLAME)  && pContext.getPlayer().getMainHandItem().is(ModItems.DEKU_STICK.get())) {
            ItemStack	newItemStack = new ItemStack(ModItems.DEKU_STICK_LIT.get());
            pContext.getPlayer().setItemSlot(EquipmentSlot.MAINHAND, newItemStack);
            pContext.getPlayer().broadcastBreakEvent(EquipmentSlot.MAINHAND);
            level.addParticle(ParticleTypes.FLAME, blockpos.getX() , blockpos.getY() + .5f, blockpos.getZ(), 0, 0, 0);
            level.addParticle(ParticleTypes.FLAME, blockpos.getX(), blockpos.getY() , blockpos.getZ() , 0, 0, 0);
            return InteractionResult.SUCCESS;
        }
        if (blockstate.is(ModTags.Blocks.BURN) && pContext.getPlayer().getMainHandItem().is(ModItems.DEKU_STICK_LIT.get())) {
          level.destroyBlock(blockpos,false, pContext.getPlayer());
            level.addParticle(ParticleTypes.FLAME, blockpos.getX() , blockpos.getY() + .5f, blockpos.getZ(), 0, 0, 0);
            level.addParticle(ParticleTypes.FLAME, blockpos.getX(), blockpos.getY() , blockpos.getZ() , 0, 0, 0);
            pContext.getPlayer().broadcastBreakEvent(EquipmentSlot.MAINHAND);
            return InteractionResult.SUCCESS;
        }
        if (!CampfireBlock.canLight(blockstate) && !CandleBlock.canLight(blockstate) && !CandleCakeBlock.canLight(blockstate) && pContext.getPlayer().getMainHandItem().is(ModItems.DEKU_STICK_LIT.get())) {
            BlockPos blockpos1 = blockpos.relative(pContext.getClickedFace());
            if (BaseFireBlock.canBePlacedAt(level, blockpos1, pContext.getHorizontalDirection())) {
                level.playSound( pContext.getPlayer(), blockpos1, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                BlockState blockstate1 = BaseFireBlock.getState(level, blockpos1);
                level.setBlock(blockpos1, blockstate1, 11);
                level.gameEvent( pContext.getPlayer(), GameEvent.BLOCK_PLACE, blockpos);
                ItemStack itemstack = pContext.getItemInHand();
                if ( pContext.getPlayer() instanceof ServerPlayer) {
                    CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer) pContext.getPlayer(), blockpos1, itemstack);
                    itemstack.hurtAndBreak(1,  pContext.getPlayer(), (p_41300_) -> {
                        p_41300_.broadcastBreakEvent(pContext.getHand());
                    });}
                return InteractionResult.sidedSuccess(level.isClientSide());
            }}
        else {
        }
        return InteractionResult.FAIL;}


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.literal("This seems flammable").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }}
}
