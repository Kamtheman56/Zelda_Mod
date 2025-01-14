package com.kamth.zeldamod.mixin;

import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.item.items.BombBagItem;
import com.kamth.zeldamod.item.items.CustomBundleItem;
import com.kamth.zeldamod.item.items.QuiverItem;
import com.kamth.zeldamod.item.items.WalletItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin (Inventory.class)
public class MixinInventory {

    @Inject(method = "add(Lnet/minecraft/world/item/ItemStack;)Z", at = @At("HEAD"), cancellable = true)
    public void addStack(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        Player player = ((Inventory) (Object) this).player;
        if (stack.is(ModTags.Items.BOW_AMMO)) {
            if (addItemToBag(player, stack, QuiverItem.class, cir)) {
                cir.setReturnValue(true);
                cir.cancel();
            }
        }
        if (stack.is(ModTags.Items.GEMS)) {
            if (addItemToBag(player, stack, WalletItem.class, cir)) {
                cir.setReturnValue(true);
                cir.cancel();
            }
        }
        else if (stack.is(ModTags.Items.BOMBS)) {
            if (addItemToBag(player, stack, BombBagItem.class, cir)) {
                cir.setReturnValue(true);
                cir.cancel();
            }
        }
    }


    @Unique
    private <T extends CustomBundleItem> boolean addItemToBag(Player player, ItemStack itemStack,
                                                          Class<T> itemClass, CallbackInfo cir) {

        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (itemClass.isInstance(stack.getItem())) {
                T customBundle = (T) stack.getItem();
                if (customBundle.getBarWidth(customBundle.getDefaultInstance()) < 13) {
                    int added = customBundle.addToBundle(stack, itemStack);
                    if (added > 0) {
                        itemStack.shrink(added);
                        if (itemStack.isEmpty()) {
                            player.awardStat(Stats.ITEM_PICKED_UP.get(itemStack.getItem()), added);
                            player.playSound(SoundEvents.BUNDLE_INSERT, 0.8F,
                                    0.8F + player.level().getRandom().nextFloat() * 0.4F);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
