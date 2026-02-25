package com.kamth.zeldamod.item.items.weapons.swords.master;

import com.kamth.zeldamod.Config;
import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.enchantments.ZeldaEnchantments;
import com.kamth.zeldamod.entity.projectile.magic.SwordBeam;
import com.kamth.zeldamod.item.ZeldaItems;
import com.kamth.zeldamod.item.ModTiers;
import com.kamth.zeldamod.item.items.weapons.swords.GloomBreakingSword;
import com.kamth.zeldamod.util.interfaces.item.IBeamShootAction;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class MasterSwordItem extends GloomBreakingSword implements IBeamShootAction {

    public MasterSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties, 15);
    }

    @Override
    public boolean healthRequirement() {
        return true;
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if (pStack.getDamageValue() < pStack.getMaxDamage() - 1) {
            super.hurtEnemy(pStack, pTarget, pAttacker);
        } else {
            ItemStack newItemStack = new ItemStack(ZeldaItems.MASTER_SWORD_INJURED.get());
            pAttacker.setItemSlot(EquipmentSlot.MAINHAND, newItemStack);
            pAttacker.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        }
        return true;
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        if (pState.is(ModTags.Blocks.DEMON) && this.getTier() != ModTiers.MASTER_TRUE) {
            pStack.hurtAndBreak(3, pEntityLiving, (p_43276_) -> p_43276_.broadcastBreakEvent(EquipmentSlot.MAINHAND));
            if (!(pStack.getDamageValue() < pStack.getMaxDamage() - 2)) {
                ItemStack newItemStack = new ItemStack(ZeldaItems.MASTER_SWORD_INJURED.get());
                pEntityLiving.setItemSlot(EquipmentSlot.MAINHAND, newItemStack);
                pEntityLiving.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            }
        }
        return true;
    }


    //Only enabled if Alternative Sword Beams are allowed
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (player.isCrouching() && Config.alternative_sword_beams() && !Config.sword_beams() && !player.getCooldowns().isOnCooldown(this)){
            player.getCooldowns().addCooldown((Item) this, swingCooldownDuration());
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, 0.8f, 5 / (world.getRandom().nextFloat() * 0.4f + 0.8f));
            SwordBeam projectile = new SwordBeam(world, player);
            projectile.setOwner(player);
            projectile.setPos(player.getEyePosition().add(0, -0.1, 0));
            projectile.shootFromRotation(player, player.xRotO, player.yRotO, 0, 1.6f, 0);
            world.addFreshEntity(projectile);

            return InteractionResultHolder.consume(itemstack);
        }
           return InteractionResultHolder.pass(itemstack);
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            components.add(Component.translatable("item.zeldamod.master_sword.description_advanced").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
        }
        else {
            components.add(Component.translatable("item.zeldamod.master_sword.description_basic").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
        }

    }
}

