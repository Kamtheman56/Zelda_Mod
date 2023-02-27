package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.item.custom.util.ModTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

public class HeroBowItem extends BowItem {
    public HeroBowItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return stack -> stack.is(ModTags.Items.BOW_AMMO);


    }
}
