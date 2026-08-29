package com.kamth.zeldamod.item.items.weapons.swords;

import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.item.items.weapons.swords.master.SwordBeamSwordItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.state.BlockState;

public class GloomBreakingSword extends SwordBeamSwordItem {

    private final int gloomDestroySpeed;

    public GloomBreakingSword(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties, int gloomDestroySpeed) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        this.gloomDestroySpeed = gloomDestroySpeed;
    }

    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    @Override
    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        if (pState.is(ModTags.Blocks.DEMON)) {
            return this.gloomDestroySpeed;
        }
        return 1;
    }
}
