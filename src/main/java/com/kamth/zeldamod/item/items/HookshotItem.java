package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.entity.custom.projectile.Hookshot;
import com.kamth.zeldamod.entity.custom.projectile.SwordBeam;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


public class HookshotItem extends Item {
    public HookshotItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand pHand) {
        ItemStack itemstack = player.getItemInHand(pHand);
            Hookshot projectile = new Hookshot(pLevel,player);

        pLevel.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.DISPENSER_LAUNCH, SoundSource.PLAYERS, 1F, -3F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
        projectile.setOwner(player);
            projectile.shootFromRotation(player, player.xRotO, player.yRotO, 0.0F, 1.6f,0f);
        projectile.Properties(itemstack, 20, 10, player.getXRot(), player.getYRot(), 0f, 1.5f * (float) (10 / 10));
            pLevel.addFreshEntity(projectile);
        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }




    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }



}