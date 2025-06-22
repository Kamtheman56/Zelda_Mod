package com.kamth.zeldamod.mixin.swordspin;

import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.enchantments.ZeldaEnchantments;
import com.kamth.zeldamod.util.interfaces.mixin.SwordSpinPlayerData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Code contributed by Deadlydiamond98 (c) 2024 under the MIT License.
// Added here with explicit permission by the original owner.

@Mixin(SwordItem.class)
public class MixinSwordItem extends MixinItem {

    // The Context of which override is used here is safe

    @Override
    protected void useSword(Level pLevel, Player pPlayer, InteractionHand pUsedHand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        boolean hasSwordSpin = EnchantmentHelper.getItemEnchantmentLevel(ZeldaEnchantments.SWORD_SPIN.get(), pPlayer.getItemInHand(pUsedHand)) > 0;
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        pPlayer.startUsingItem(pUsedHand);


 if (hasSwordSpin && pUsedHand == InteractionHand.MAIN_HAND
                && !((SwordSpinPlayerData) pPlayer).legendaryArmory$isSwordSpinActive() ||
                pUsedHand == InteractionHand.MAIN_HAND && pPlayer.getUseItem().is(ModTags.Items.SPIN_ATTACK_SWORDS)
                        && !((SwordSpinPlayerData) pPlayer).legendaryArmory$isSwordSpinActive()) {

            if (!pLevel.isClientSide() && pPlayer.isCrouching()) {
                pPlayer.playNotifySound(SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1, 1);
            }
            cir.setReturnValue(InteractionResultHolder.consume(itemstack));
        }
        else cir.setReturnValue(InteractionResultHolder.pass(itemstack));
    }

    @Override
    protected void useDuration(ItemStack pStack, CallbackInfoReturnable<Integer> cir) {
        boolean hasSwordSpin = EnchantmentHelper.getItemEnchantmentLevel(ZeldaEnchantments.SWORD_SPIN.get(), pStack) > 0;

        if (hasSwordSpin || pStack.is(ModTags.Items.SPIN_ATTACK_SWORDS)) {
            cir.setReturnValue(72000);
        }
    }
}
