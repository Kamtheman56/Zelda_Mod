package com.kamth.zeldamod.item.items.weapons.swords;

import com.kamth.zeldamod.block.ZeldaBlocks;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class GoldenMasterSwordItem extends MasterSwordItem {
    public GoldenMasterSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    // Grants enchantments upon clicking matching Flame Block
    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);


        if (blockstate.is(ZeldaBlocks.COURAGE_FLAME.get())) {
            if (!(pContext.getItemInHand().getAllEnchantments().containsKey(Enchantments.SWEEPING_EDGE))){
         pContext.getItemInHand().enchant(Enchantments.SWEEPING_EDGE,1);
         createsound(pContext.getPlayer());
        }
            return InteractionResult.SUCCESS;
        }

     if (blockstate.is(ZeldaBlocks.WISDOM_FLAME.get())) {
        if (!(pContext.getItemInHand().getAllEnchantments().containsKey(Enchantments.UNBREAKING))){
            pContext.getItemInHand().enchant(Enchantments.UNBREAKING,2);
            createsound(pContext.getPlayer());
        }
        return InteractionResult.SUCCESS;
     }

        if (blockstate.is(ZeldaBlocks.POWER_FLAME.get())) {
            if (!(pContext.getItemInHand().getAllEnchantments().containsKey(Enchantments.SMITE))){
                pContext.getItemInHand().enchant(Enchantments.SMITE,3);
                createsound(pContext.getPlayer());
            }
            return InteractionResult.SUCCESS;
        }
     else return InteractionResult.PASS;
    }

    private void createsound(Player player) {
        player.level().playSound(player, player.getOnPos(), ModSounds.SWORD_ENCHANT.get(),SoundSource.BLOCKS, 1,1);
    }

}

