package com.kamth.zeldamod.item.items.weapons.swords.goddess;

import com.kamth.zeldamod.block.ZeldaBlocks;
import com.kamth.zeldamod.entity.projectile.magic.SwordBeam;
import com.kamth.zeldamod.item.ZeldaItems;
import com.kamth.zeldamod.item.items.weapons.swords.GloomBreakingSword;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class AbstractGoddessSwordItem extends GloomBreakingSword  {

    public final int swordChargeDuration;
    public final Item  swordUpgrade;
    public final Block upgradeBlock;

    public AbstractGoddessSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties, int swordChargeDuration, Item swordUpgrade, Block upgradeBlock) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties, 2);
        this.swordChargeDuration = swordChargeDuration;
        this.swordUpgrade = swordUpgrade;
        this.upgradeBlock = upgradeBlock;
    }




    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }

    /**
     * Returns the action that specifies what animation to play when the item is being used.
     */
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BLOCK;
    }


    @Override
    public void onUseTick(Level pLevel, LivingEntity pEntityLiving, ItemStack pStack, int pRemainingUseDuration) {
        if (pEntityLiving instanceof Player player) {
            int i = this.getUseDuration(pStack) - pRemainingUseDuration;
            if (i == swordChargeDuration && !pLevel.isClientSide){
            player.playNotifySound(ModSounds.FINISH_CHARGING.get(), SoundSource.PLAYERS, 1.5f, 1);}
        }
    }


    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand pHand) {
        ItemStack itemstack = player.getItemInHand(pHand);

        if (!player.isCrouching()){
            player.startUsingItem(pHand);

            world.playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.SWORD_CHARGE_START.get(), SoundSource.PLAYERS, 0.4f, 1 / (world.getRandom().nextFloat() * 0.4f + 0.8f));

            return InteractionResultHolder.consume(itemstack);
        }

       else return InteractionResultHolder.pass(itemstack);

    }



    public void releaseUsing(ItemStack pStack, Level world, LivingEntity pEntityLiving, int pTimeLeft) {
        if (pEntityLiving instanceof Player player) {
            int i = this.getUseDuration(pStack) - pTimeLeft;

            if (i < swordChargeDuration) return;


            player.getCooldowns().addCooldown((Item) this, swordChargeDuration);
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, 0.8f, 5 / (world.getRandom().nextFloat() * 0.4f + 0.8f));
            SwordBeam projectile = new SwordBeam(world, player);
            projectile.setOwner(player);
            projectile.setPos(player.getEyePosition().add(0, -0.1, 0));
            projectile.shootFromRotation(player, player.xRotO, player.yRotO, 0, 1.6f, 0);
            world.addFreshEntity(projectile);

            player.awardStat(Stats.ITEM_USED.get(this));
        }
    }


    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);
        ItemStack Sword = new ItemStack(this.swordUpgrade);
        Player player = pContext.getPlayer();

        if (blockstate.is(upgradeBlock)) {
            level.playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.SWORD_ENCHANT.get(), SoundSource.PLAYERS,
                    0.8f, 1 / (level.getRandom().nextFloat() * 0.4f + 0.8f));
            pContext.getItemInHand().shrink(1);
            pContext.getPlayer().addItem(Sword);
        }

        return InteractionResult.PASS;
    }


}

