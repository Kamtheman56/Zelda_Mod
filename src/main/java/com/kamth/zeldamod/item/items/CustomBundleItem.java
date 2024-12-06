package com.kamth.zeldamod.item.items;

import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.BundleTooltip;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


//Credits to DeadlyDiamond98 for this code!

public class CustomBundleItem extends Item {
    public CustomBundleItem(Properties pProperties, int maxStorage, List<TagKey> itemTags) {
        super(pProperties);
        this.maxStorage = maxStorage;
        this.itemTags = List.copyOf(itemTags);
    }
    private static final String ITEMS_KEY = "Items";
    private static final int ITEM_BAR_COLOR = Mth.color(0.4F, 0.4F, 1.0F);
    private final int maxStorage;
    private final List<TagKey> itemTags;



    private boolean canInsertItem(Item item) {
        for (int i = 0; i < this.itemTags.size(); i++) {
            if (item.getDefaultInstance().is(itemTags.get(i))) {
                return true;
            }
        }
        return false;
    }
    public class BundledItemData {

        //Purpose of this is to allow more items to be stored in my custom bundles, original bundle dislikes numbers >127

        private final String itemId;
        private final CompoundTag itemNbt;
        private int count;

        public BundledItemData(ItemStack stack, int count) {
            this.itemId = ForgeRegistries.ITEMS.getKey(stack.getItem()).toString();
            this.itemNbt = stack.getTag() != null ? stack.getTag().copy() : new CompoundTag();
            this.count = count;
        }

        public BundledItemData(CompoundTag nbt) {

            this.itemId = nbt.getString("id");
            this.count = nbt.getInt("Count");
            this.itemNbt = nbt.contains("tag") ? nbt.getCompound("tag") : new CompoundTag();
        }

            public CompoundTag toNbt() {
                CompoundTag nbt = new CompoundTag();
                nbt.putString("id", itemId);
                nbt.putInt("Count", count);
                if (!itemNbt.isEmpty()) {
                    nbt.put("tag", itemNbt);
                }
                return nbt;
            }

        public String getItemId() {
            return itemId;
        }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public Item getItem() {
                return ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemId));
            }


            public CompoundTag getItemNbt() {
                return itemNbt;
            }}

    @Override
    public boolean overrideStackedOnOther(ItemStack stack, Slot slot, ClickAction pAction, Player player) {
        if (pAction !=  ClickAction.SECONDARY) {
            return false;
        } else {
            ItemStack itemStack = slot.getItem();
            if (itemStack.isEmpty()) {
                this.playRemoveOneSound(player);
                removeFirstStack(stack).ifPresent((removedStack) -> {
                    addToBundle(stack, slot.safeInsert(removedStack));
                });
            } else if (canInsertItem(itemStack.getItem()) && itemStack.getItem().canFitInsideContainerItems()) {
                int maxItemsToAdd = (maxStorage - getBundleOccupancy(stack));
                int itemsAdded = addToBundle(stack, slot.safeTake(itemStack.getCount(), maxItemsToAdd, player));
                if (itemsAdded > 0) {
                    this.playInsertSound(player);
                }
            }

            return true;
        }
    }


    @Override
    public boolean overrideOtherStackedOnMe(ItemStack pStack, ItemStack pOther, Slot pSlot, ClickAction pAction, Player pPlayer, SlotAccess pAccess) {
        if (pAction == ClickAction.SECONDARY && pSlot.allowModification(pPlayer)) {
            if (pOther.isEmpty()) {
                removeFirstStack(pStack).ifPresent((itemStack) -> {
                    this.playRemoveOneSound(pPlayer);
                    pAccess.set(itemStack);
                });
            } else if (canInsertItem(pOther.getItem()) && pOther.getItem().canFitInsideContainerItems()) {
                int itemsAdded = addToBundle(pStack, pOther);
                if (itemsAdded > 0) {
                    this.playInsertSound(pPlayer);
                    pOther.shrink(itemsAdded);
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public boolean isBarVisible(ItemStack pStack) {
        return getBundleOccupancy(pStack) > 0;
    }

    public int getBarWidth(ItemStack pStack) {
        return Math.min(1 + 12 * getBundleOccupancy(pStack) / maxStorage, 13);
    }

    public int getBarColor(ItemStack pStack) {
        return ITEM_BAR_COLOR;
    }

    public int addToBundle(ItemStack bundle, ItemStack stack) {
        if (!stack.isEmpty() && canInsertItem(stack.getItem()) && stack.getItem().canFitInsideContainerItems()) {
            CompoundTag nbtCompound = bundle.getOrCreateTag();
            if (!nbtCompound.contains(ITEMS_KEY)) {
                nbtCompound.put(ITEMS_KEY, new ListTag());
            }

            int currentOccupancy = getBundleOccupancy(bundle);
            int itemsToAdd = Math.min(stack.getCount(), (maxStorage - currentOccupancy));

            ListTag nbtList = nbtCompound.getList(ITEMS_KEY, 10);
            Optional<Integer> existingItemIndex = findBundledItemDataIndex(nbtList, stack);
            if (existingItemIndex.isPresent()) {
                int index = existingItemIndex.get();
                BundledItemData bundledItemData = new BundledItemData(nbtList.getCompound(index));
                int newCount = bundledItemData.getCount() + itemsToAdd;
                bundledItemData.setCount(newCount);
                nbtList.set(index, bundledItemData.toNbt());
            } else {
                BundledItemData newItem = new BundledItemData(stack, itemsToAdd);
                nbtList.add(newItem.toNbt());
            }

            nbtCompound.put(ITEMS_KEY, nbtList);
            return itemsToAdd;
        }
        return 0;
    }

    private Optional<Integer> findBundledItemDataIndex(ListTag items, ItemStack stack) {
        for (int i = 0; i < items.size(); i++) {
            CompoundTag nbt = items.getCompound(i);
            BundledItemData bundledItemData = new BundledItemData(nbt);
            ItemStack bundledStack = new ItemStack(bundledItemData.getItem());
            if (bundledItemData.getItem().equals(stack.getItem()) && ItemStack.isSameItemSameTags(bundledStack, stack)) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }

    protected int getBundleOccupancy(ItemStack stack) {
        CompoundTag nbtCompound = stack.getTag();
        if (nbtCompound == null) {
            return 0;
        }

        ListTag nbtList = nbtCompound.getList(ITEMS_KEY, 10);
        int occupancy = 0;
        for (int i = 0; i < nbtList.size(); i++) {
            CompoundTag nbt = nbtList.getCompound(i);
            BundledItemData bundledItemData = new BundledItemData(nbt);
            occupancy += bundledItemData.getCount();
        }
        return occupancy;
    }

    protected Optional<ItemStack> removeFirstStack(ItemStack stack) {
        CompoundTag nbtCompound = stack.getOrCreateTag();
        if (!nbtCompound.contains(ITEMS_KEY)) {
            return Optional.empty();
        }

        ListTag nbtList = nbtCompound.getList(ITEMS_KEY, 10);
        if (nbtList.isEmpty()) {
            return Optional.empty();
        }

        CompoundTag firstItemNbt = nbtList.getCompound(0);
        BundledItemData bundledItemData = new BundledItemData(firstItemNbt);
        ItemStack itemStack = new ItemStack(bundledItemData.getItem(), bundledItemData.getCount());
        if (firstItemNbt.contains("tag")) {
            itemStack.setTag(firstItemNbt.getCompound("tag"));
        }

        if (itemStack.getCount() >= bundledItemData.getCount()) {
            nbtList.remove(0);
        }
        else {
            bundledItemData.setCount(bundledItemData.getCount() - itemStack.getCount());
            nbtList.set(0, bundledItemData.toNbt());
            itemStack.setCount(itemStack.getCount());
        }

        if (nbtList.isEmpty()) {
            stack.removeTagKey(ITEMS_KEY);
        } else {
            nbtCompound.put(ITEMS_KEY, nbtList);
        }

        return Optional.of(itemStack);
    }

    public Optional<ItemStack> cycleStack(ItemStack stack) {
        CompoundTag nbtCompound = stack.getOrCreateTag();
        if (!nbtCompound.contains(ITEMS_KEY)) {
            return Optional.empty();
        }

        ListTag nbtList = nbtCompound.getList(ITEMS_KEY, 10);
        if (nbtList.size() <= 1) {
            return Optional.empty();
        }

        CompoundTag firstItemNbt = nbtList.getCompound(0);
        nbtList.remove(0);

        nbtList.add(firstItemNbt);
        nbtCompound.put(ITEMS_KEY, nbtList);

        BundledItemData bundledItemData = new BundledItemData(firstItemNbt);
        ItemStack movedItemStack = new ItemStack(bundledItemData.getItem(), bundledItemData.getCount());
        if (firstItemNbt.contains("tag")) {
            movedItemStack.setTag(firstItemNbt.getCompound("tag"));
        }
        return Optional.of(movedItemStack);
    }

    public Optional<ItemStack> removeOneItem(ItemStack stack, Item item) {
        CompoundTag nbtCompound = stack.getOrCreateTag();
        if (!nbtCompound.contains(ITEMS_KEY)) {
            return Optional.empty();
        }

        ListTag nbtList = nbtCompound.getList(ITEMS_KEY, 10);
        for (int i = 0; i < nbtList.size(); i++) {
            CompoundTag nbt = nbtList.getCompound(i);
            BundledItemData bundledItemData = new BundledItemData(nbt);
            if (bundledItemData.getItem().equals(item)) {
                int newCount = bundledItemData.getCount() - 1;
                if (newCount > 0) {
                    bundledItemData.setCount(newCount);
                    nbtList.set(i, bundledItemData.toNbt());
                } else {
                    nbtList.remove(i);
                }

                if (nbtList.isEmpty()) {
                    stack.removeTagKey(ITEMS_KEY);
                } else {
                    nbtCompound.put(ITEMS_KEY, nbtList);
                }

                ItemStack result = new ItemStack(item, 1);
                   if (nbt.contains("tag")) {
                   result.setTag(nbt.getCompound("tag"));
               }
                return Optional.of(result);
            }
        }

        return Optional.empty();
    }







    public Optional<ItemStack> getFirstItem(ItemStack stack) {
        CompoundTag nbtCompound = stack.getOrCreateTag();
        if (!nbtCompound.contains(ITEMS_KEY)) {
            return Optional.empty();
        }

        ListTag nbtList = nbtCompound.getList(ITEMS_KEY, 10);
        if (nbtList.isEmpty()) {
            return Optional.empty();
        }

        CompoundTag firstItemNbt = nbtList.getCompound(0);
        BundledItemData bundledItemData = new BundledItemData(firstItemNbt);
        ItemStack itemStack = new ItemStack(bundledItemData.getItem(), bundledItemData.getCount());
        if (firstItemNbt.contains("tag")) {
            itemStack.setTag(firstItemNbt.getCompound("tag"));
        }

        return Optional.of(itemStack);
    }

    private Stream<ItemStack> getBundledStacks(ItemStack stack) {
       CompoundTag nbtCompound = stack.getTag();
        if (nbtCompound == null) {
            return Stream.empty();
        }

        ListTag nbtList = nbtCompound.getList(ITEMS_KEY, 10);
        return nbtList.stream()
                .filter(CompoundTag.class::isInstance)
                .map(CompoundTag.class::cast)
                .map(nbt -> {
                    BundledItemData bundledItemData = new BundledItemData(nbt);
                    ItemStack itemStack = new ItemStack(bundledItemData.getItem(), bundledItemData.getCount());
                    if (nbt.contains("tag")) {
                        itemStack.setTag(nbt.getCompound("tag"));
                    }
                    return itemStack;
                });
    }

@Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack pStack) {
        NonNullList<ItemStack> nonnulllist = NonNullList.create();
        getBundledStacks(pStack).forEach(nonnulllist::add);
        return Optional.of(new BundleTooltip(nonnulllist, getBundleOccupancy(pStack)));
    }

    @Override
    public void appendHoverText(ItemStack pStack, Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("item.minecraft.bundle.fullness", getBundleOccupancy(pStack), maxStorage).withStyle(ChatFormatting.GRAY));
    }

    @Override
    public void onDestroyed(ItemEntity pItemEntity) {
        ItemUtils.onContainerDestroyed(pItemEntity, getBundledStacks(pItemEntity.getItem()));
    }


    private void playRemoveOneSound(Entity pEntity) {
        pEntity.playSound(SoundEvents.BUNDLE_REMOVE_ONE, 0.8F, 0.8F + pEntity.level().getRandom().nextFloat() * 0.4F);
    }

    private void playInsertSound(Entity pEntity) {
        pEntity.playSound(SoundEvents.BUNDLE_INSERT, 0.8F, 0.8F + pEntity.level().getRandom().nextFloat() * 0.4F);
    }

    private void playDropContentsSound(Entity pEntity) {
        pEntity.playSound(SoundEvents.BUNDLE_DROP_CONTENTS, 0.8F, 0.8F + pEntity.level().getRandom().nextFloat() * 0.4F);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (player.isCrouching()) {
            this.cycleStack(player.getItemInHand(hand));
            playInsertSound(player);
            return InteractionResultHolder.success(itemstack);

        }
        return super.use(world, player, hand);
    }





}


