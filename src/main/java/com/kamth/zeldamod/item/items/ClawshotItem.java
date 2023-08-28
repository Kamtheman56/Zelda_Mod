package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.entity.custom.projectile.Clawshot;
import com.kamth.zeldamod.entity.custom.projectile.Hookshot;
import com.kamth.zeldamod.item.ModItems;
import net.minecraft.core.BlockPos;
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
import net.minecraftforge.event.ForgeEventFactory;

import static net.minecraft.world.item.BowItem.getPowerForTime;


public class ClawshotItem extends Item {
    public ClawshotItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);


        boolean flag = false;
        InteractionResultHolder<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, pLevel, pPlayer, pHand, flag);
        if (ret != null) return ret;
            pPlayer.startUsingItem(pHand);
            return InteractionResultHolder.consume(itemstack);
        }

    @Override
    public void releaseUsing(ItemStack stack, Level world, LivingEntity entity, int timeLeft) {
        Player player = (Player) entity;
        ItemStack itemstack = entity.getItemInHand(entity.getUsedItemHand());
        BlockPos currentPos = entity.blockPosition();
        world.playSound(null, player.getX(),player.getY(), player.getZ(), SoundEvents.CROSSBOW_SHOOT, SoundSource.PLAYERS, 1F, -4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!world.isClientSide) {
                Clawshot projectile = new Clawshot(world, (Player) entity);
                projectile.setOwner(player);
                projectile.shootFromRotation(player, player.xRotO, player.yRotO, 0.0F, 1.6f, 0f);
                projectile.Properties(itemstack, 20, 12, player.getXRot(), player.getYRot(), 0f, 1.5f * (float) (10 / 10));
                world.addFreshEntity(projectile);
            }
        if (itemstack.is(ModItems.CLAWSHOT.get())) {
            player.getCooldowns().addCooldown(this, 40);
        }
        if (itemstack.is(ModItems.CLAWSHOT_GODDESS.get())) {
            player.getCooldowns().addCooldown(this, 40);
        }

        }




    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }



}