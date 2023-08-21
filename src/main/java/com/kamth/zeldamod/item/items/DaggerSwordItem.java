package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.item.ModItems;
import com.kamth.zeldamod.item.custom.util.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class DaggerSwordItem extends SwordItem {
    public DaggerSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
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
        if (!pLevel.isClientSide && player.isCrouching() && player.getHealth() >= 20 ) {
        player.getCooldowns().addCooldown(this, 60);
        pLevel.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 1F, -2F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
            player.displayClientMessage(Component.literal("You're unable to do that").withStyle(ChatFormatting.BLUE).withStyle(ChatFormatting.BOLD), true);
        }
        else {
            return InteractionResultHolder.pass(itemstack);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        {
            components.add(Component.literal("It's been fully").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));
            components.add(Component.literal("corrupted by Gloom").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));
        }

    }

}

