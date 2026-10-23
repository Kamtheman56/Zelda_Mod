package com.kamth.zeldamod.item.items.weapons;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class SwordBreakingItem extends Item {
    private final Item swordChild;
    public SwordBreakingItem(Properties properties, Item swordChild) {
        super(properties);
        this.swordChild = swordChild;
    }


    @Override
    public void hurtEnemy(ItemStack itemStack, LivingEntity mob, LivingEntity attacker) {
        damageSword(itemStack, attacker, mob.level(), 1);
    }

    @Override
    public boolean mineBlock(ItemStack itemStack, Level level, BlockState state, BlockPos pos, LivingEntity owner) {
        damageSword(itemStack, owner, level, 3);
        return true;
    }

    public void damageSword(ItemStack itemStack, LivingEntity user, Level level, int damage) {
        itemStack.hurtAndBreak(1, user, EquipmentSlot.MAINHAND);


        if (!(itemStack.getDamageValue() < itemStack.getMaxDamage() - 3)){
              ItemStack newItemStack = new ItemStack(this.swordChild);
                user.setItemSlot(EquipmentSlot.MAINHAND, newItemStack);
            }

    }
}
