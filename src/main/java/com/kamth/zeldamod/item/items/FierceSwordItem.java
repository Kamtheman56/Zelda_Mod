package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.entity.custom.projectile.SwordBeam;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class FierceSwordItem extends SwordItem {
    public FierceSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }



    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand pHand) {
        ItemStack itemstack = player.getItemInHand(pHand);
        if (!pLevel.isClientSide ) {
      //  player.getCooldowns().addCooldown(this, 10);
        player.causeFoodExhaustion(5);
        pLevel.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, 1F, 5F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
            SwordBeam projectile = new SwordBeam(pLevel,player);
            projectile.setOwner(player);
            projectile.setPos(player.getEyePosition(1F).add(0, -0.1, 0));
            projectile.shootFromRotation(player, player.xRotO, player.yRotO, 0.0F, 1.6f,.7f);
            pLevel.addFreshEntity(projectile);
           itemstack.hurtAndBreak(6, player, (p_43296_) -> {
                p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);});
        }
        else {
            return InteractionResultHolder.pass(itemstack);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.literal("Shoots Beams").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
        } else {
            components.add(Component.literal("Destruction...").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }

    }

}

