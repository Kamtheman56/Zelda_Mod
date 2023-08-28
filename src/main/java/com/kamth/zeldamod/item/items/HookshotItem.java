package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.entity.custom.projectile.Hookshot;
import com.kamth.zeldamod.entity.custom.projectile.SwordBeam;
import com.kamth.zeldamod.item.ModItems;
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
        player.getCooldowns().addCooldown(this, 40);
        pLevel.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.CROSSBOW_SHOOT, SoundSource.PLAYERS, 1F, -4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
        projectile.setOwner(player);
            projectile.shootFromRotation(player, player.xRotO, player.yRotO, 1F, 1.6f,0f);
            if (itemstack.is(ModItems.HOOKSHOT.get())){
                projectile.Properties(itemstack, 15, 10, player.getXRot(), player.getYRot(), 0f, 1.5f * (float) (10 / 10));
            }
        if (itemstack.is(ModItems.LONGSHOT.get())){
            projectile.Properties(itemstack, 30, 10, player.getXRot(), player.getYRot(), 0f, 1.5f * (float) (10 / 10));
        }
            pLevel.addFreshEntity(projectile);
        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }




}