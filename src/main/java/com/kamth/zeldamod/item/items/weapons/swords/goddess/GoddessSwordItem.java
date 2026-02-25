package com.kamth.zeldamod.item.items.weapons.swords.goddess;

import com.kamth.zeldamod.Config;
import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.entity.projectile.magic.SwordBeam;
import com.kamth.zeldamod.item.ModTiers;
import com.kamth.zeldamod.item.ZeldaItems;
import com.kamth.zeldamod.item.items.weapons.swords.GloomBreakingSword;
import com.kamth.zeldamod.util.interfaces.item.IBeamShootAction;
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
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class GoddessSwordItem extends GloomBreakingSword  {

    public GoddessSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties, 15);
    }





    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }

    /**
     * Returns the action that specifies what animation to play when the item is being used.
     */
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BLOCK;
    }


    @Override
    public void onUseTick(Level pLevel, LivingEntity pEntityLiving, ItemStack pStack, int pRemainingUseDuration) {
        if (pEntityLiving instanceof Player player) {
            int i = this.getUseDuration(pStack) - pRemainingUseDuration;
            if (i == 100 && !pLevel.isClientSide){
            player.playNotifySound(SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1, 1);}
        }
    }

    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand pHand) {
        ItemStack itemstack = player.getItemInHand(pHand);
        player.startUsingItem(pHand);
        return InteractionResultHolder.consume(itemstack);
    }



    public void releaseUsing(ItemStack pStack, Level world, LivingEntity pEntityLiving, int pTimeLeft) {
        if (pEntityLiving instanceof Player player) {



            int i = this.getUseDuration(pStack) - pTimeLeft;

            if (i < 80) return;


            player.getCooldowns().addCooldown((Item) this, 45);
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, 0.8f, 5 / (world.getRandom().nextFloat() * 0.4f + 0.8f));
            SwordBeam projectile = new SwordBeam(world, player);
            projectile.setOwner(player);
            projectile.setPos(player.getEyePosition().add(0, -0.1, 0));
            projectile.shootFromRotation(player, player.xRotO, player.yRotO, 0, 1.6f, 0);
            world.addFreshEntity(projectile);

            player.awardStat(Stats.ITEM_USED.get(this));
        }

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

