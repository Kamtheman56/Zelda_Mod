package com.kamth.zeldamod.item.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Optional;

public class QuiverItem extends CustomBundleItem {
    public QuiverItem(Properties pProperties, int maxStorage, List<TagKey> itemtags) {
        super(pProperties,maxStorage,itemtags);
    }


    @Override
    public boolean overrideOtherStackedOnMe(ItemStack pStack, ItemStack pOther, Slot pSlot, ClickAction pAction, Player pPlayer, SlotAccess pAccess) {
        boolean result = super.overrideOtherStackedOnMe(pStack,pOther,pSlot,pAction,pPlayer,pAccess);
        this.updateFilledStatus(pStack);
        return result;
    }

    @Override
    public boolean overrideStackedOnOther(ItemStack stack, Slot slot, ClickAction pAction, Player player) {
        boolean result = super.overrideStackedOnOther(stack,slot,pAction,player);
        this.updateFilledStatus(stack);
        return result;

    }

    public Optional<ItemStack> removeOneItem(ItemStack stack, Item item) {

        Optional<ItemStack> result = super.removeOneItem(stack,item);
        this.updateFilledStatus(stack);
        return result;
    }
    public int addToBundle(ItemStack bundle, ItemStack stack) {
        int result = super.addToBundle(bundle,stack);
        this.updateFilledStatus(stack);
        return result;
    }

    private void updateFilledStatus(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        int filled = getBundleOccupancy(stack) > 0 ? 1 : 0;
        nbt.putInt("filled", filled);
    }
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(stack, world, entity, pSlotId, pIsSelected);
        this.updateFilledStatus(stack);


    }
}

