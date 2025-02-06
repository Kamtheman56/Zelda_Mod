package com.kamth.zeldamod.item.items.weapons.swords.master;

import com.kamth.zeldamod.block.ZeldaBlocks;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class GoldenMasterSwordItem extends MasterSwordItem {
    public GoldenMasterSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @NotNull
    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);

        if (blockstate.is(ZeldaBlocks.COURAGE_FLAME.get())) {
            return empowerSword(pContext, 1, Enchantments.SWEEPING_EDGE);
        }

        if (blockstate.is(ZeldaBlocks.WISDOM_FLAME.get())) {
            return empowerSword(pContext, 2, Enchantments.UNBREAKING);
        }

        if (blockstate.is(ZeldaBlocks.POWER_FLAME.get())) {
            return empowerSword(pContext, 3, Enchantments.SMITE);
        }
        return InteractionResult.PASS;
    }

    private InteractionResult empowerSword(UseOnContext pContext, int lvl, Enchantment enchantment) {
        if (!(pContext.getItemInHand().getAllEnchantments().containsKey(enchantment))) {
            Player player = pContext.getPlayer();
            pContext.getItemInHand().enchant(enchantment, lvl);
            player.level().playSound(player, player.getOnPos(), ModSounds.SWORD_ENCHANT.get(), SoundSource.BLOCKS, 1, 1);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

}

