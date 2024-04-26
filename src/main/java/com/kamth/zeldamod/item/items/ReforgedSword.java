package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.block.ModBlocks;
import com.kamth.zeldamod.entity.custom.projectile.SwordBeam2;
import com.kamth.zeldamod.item.custom.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class ReforgedSword extends SwordItem {
    public ReforgedSword(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }
    @Override
    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        if (pState.is(ModTags.Blocks.DEMON)) {   return 10.0F;
        }
        else return 1;
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand pHand) {
        ItemStack itemstack = player.getItemInHand(pHand);
        if (!pLevel.isClientSide && player.isCrouching()) {
        pLevel.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, 1F, 5F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
            SwordBeam2 projectile = new SwordBeam2(pLevel,player);
            projectile.setOwner(player);
            projectile.setPos(player.getEyePosition(1F).add(0, -0.1, 0));
            projectile.shootFromRotation(player, player.xRotO, player.yRotO, 0.0F, 1.6f,0f);
            pLevel.addFreshEntity(projectile);
        }
        else {
            return InteractionResultHolder.pass(itemstack);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }


    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);

        if (blockstate.is(ModBlocks.COURAGE_FLAME.get())) {
            if (!(pContext.getItemInHand().getAllEnchantments().containsKey(Enchantments.KNOCKBACK))){
                pContext.getItemInHand().enchant(Enchantments.KNOCKBACK,3);
                pContext.getLevel().playSound(pContext.getPlayer(),blockpos,SoundEvents.AMETHYST_BLOCK_RESONATE,SoundSource.BLOCKS, 1,1);
            }  return InteractionResult.SUCCESS;}

        if (blockstate.is(ModBlocks.WISDOM_FLAME.get())) {
            if (!(pContext.getItemInHand().getAllEnchantments().containsKey(Enchantments.FIRE_ASPECT))){
                pContext.getItemInHand().enchant(Enchantments.FIRE_ASPECT,3);
                pContext.getLevel().playSound(pContext.getPlayer(),blockpos,SoundEvents.AMETHYST_BLOCK_RESONATE,SoundSource.BLOCKS,1,1.4f);
            }  return InteractionResult.SUCCESS;}

        if (blockstate.is(ModBlocks.POWER_FLAME.get())) {
            if (!(pContext.getItemInHand().getAllEnchantments().containsKey(Enchantments.SHARPNESS))){
                pContext.getItemInHand().enchant(Enchantments.SHARPNESS,3);
                pContext.getLevel().playSound(pContext.getPlayer(),blockpos,SoundEvents.AMETHYST_BLOCK_RESONATE,SoundSource.BLOCKS,1,2f);
            }  return InteractionResult.SUCCESS;}
        else return InteractionResult.FAIL;
    }

    public boolean isFoil(ItemStack pStack) {
        return true;
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.literal("Shoots Beams...").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
        } else {
            components.add(Component.literal("The sword that").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            components.add(Component.literal("sealed the darkness ").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
        }

    }

}

