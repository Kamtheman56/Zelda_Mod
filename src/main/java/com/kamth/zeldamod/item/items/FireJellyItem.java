package com.kamth.zeldamod.item.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FireJellyItem extends JellyItem {

    public FireJellyItem(Properties pProperties) {
        super(pProperties);
    }

@Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        super.finishUsingItem(pStack, pLevel, pEntityLiving);
        pLevel.playSound(pEntityLiving,pEntityLiving.getOnPos(), SoundEvents.PLAYER_BURP, SoundSource.PLAYERS, .5f, 1.2f);
        if (pEntityLiving instanceof ServerPlayer serverplayer) {
            Player player = (Player)pEntityLiving;
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, pStack);
           player.setTicksFrozen(0);
            serverplayer.awardStat(Stats.ITEM_USED.get(this));
            if (!player.getAbilities().instabuild){
                player.getUseItem().shrink(1);}}
        return pStack;
    }


}
