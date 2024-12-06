package com.kamth.mixin;


import com.kamth.zeldamod.item.items.BombBagItem;
import com.kamth.zeldamod.item.items.BombItem;
import com.kamth.zeldamod.item.items.CustomBundleItem;
import com.kamth.zeldamod.item.items.QuiverItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class MixinPlayerInventory {

    @Inject(method = "addItem", at = @At("HEAD"), cancellable = true)
    public void addItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        Player player = ((Inventory) (Object) this).player;
        Item item = stack.getItem();
        if (item instanceof ArrowItem) {
            if (addItemToBag(player, stack, QuiverItem.class, cir)) {
                cir.setReturnValue(true);
                cir.cancel();
            }
        }
        if (item instanceof BombItem) {
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
