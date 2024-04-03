package com.kamth.zeldamod.item.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.kamth.zeldamod.block.ModBlocks;
import com.kamth.zeldamod.item.custom.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class HammerItem extends TieredItem {
    private final float attackDamage;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

public HammerItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Item.Properties pProperties) {
    super(pTier, pProperties);
    this.attackDamage = (float)pAttackDamageModifier + pTier.getAttackDamageBonus();
    ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
    builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", (double)this.attackDamage, AttributeModifier.Operation.ADDITION));
    builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", (double)pAttackSpeedModifier, AttributeModifier.Operation.ADDITION));
    this.defaultModifiers = builder.build();
}
    public float getDamage() {return this.attackDamage;
    }
    public  boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker)
    {
        return this instanceof HammerItem;
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

        if (blockstate.is(ModTags.Blocks.HAMMER)) {
            level.destroyBlock(blockpos,false, pContext.getPlayer());
            return InteractionResult.SUCCESS;
        }  if (blockstate.is(ModBlocks.HAMMER_PEG.get())) {
             if (!pContext.getPlayer().getAbilities().instabuild){
                ItemStack stack = pContext.getItemInHand();
                stack.setDamageValue(stack.getDamageValue() + 3);
                if (stack.getDamageValue() >= stack.getMaxDamage()) stack.setCount(0);}
            level.destroyBlock(blockpos,false, pContext.getPlayer());
            level.setBlockAndUpdate(blockpos, ModBlocks.HAMMERED_PEG.get().defaultBlockState());
            return InteractionResult.SUCCESS;
        }
        else return InteractionResult.FAIL;
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.literal("Smash through weak blocks").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            components.add(Component.literal("Pound down wooden pegs").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }}
}
