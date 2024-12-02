package com.kamth.zeldamod.item.items;

import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Optional;

public class BombBagItem extends CustomBundleItem {
    public BombBagItem(Properties pProperties, int maxStorage, List<TagKey> itemtags) {
        super(pProperties,maxStorage,itemtags);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand pHand) {
    if (player.isCrouching()){
        Optional<ItemStack> bombstack = this.getFirstItem(player.getItemInHand(pHand));
        if (bombstack.isPresent()){
            Optional<ItemStack> bomb = this.removeOneItem(player.getItemInHand(pHand), bombstack.get().getItem());
            if (bomb.isPresent()){
                return bomb.get().use(pLevel,player,pHand);
            }
            return InteractionResultHolder.fail(player.getItemInHand(pHand));
        }
    }

return super.use(pLevel,player,pHand);
    }
}

