package com.kamth.zeldamod.item.items.weapons.swords.master;

import com.kamth.zeldamod.Config;
import com.kamth.zeldamod.enchantments.ZeldaEnchantments;
import com.kamth.zeldamod.entity.projectile.magic.SwordBeam;
import com.kamth.zeldamod.util.interfaces.item.IBeamShootAction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

public class SwordBeamSwordItem extends SwordItem implements IBeamShootAction {
    public SwordBeamSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }


    public boolean healthRequirement() {
        return true;
    }

    //Only enabled if Alternative Sword Beams are allowed
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (player.isCrouching() && Config.alternative_sword_beams() && !Config.sword_beams() && !player.getCooldowns().isOnCooldown(this)){
            player.getCooldowns().addCooldown((Item) this, swingCooldownDuration());
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, 0.8f, 5 / (world.getRandom().nextFloat() * 0.4f + 0.8f));
            SwordBeam projectile = new SwordBeam(world, player);
            projectile.setOwner(player);
            projectile.setPos(player.getEyePosition().add(0, -0.1, 0));
            projectile.shootFromRotation(player, player.xRotO, player.yRotO, 0, 1.6f, 0);
            world.addFreshEntity(projectile);

            return InteractionResultHolder.consume(itemstack);
        }
        if (player.isCrouching() && itemstack.getAllEnchantments().containsKey(ZeldaEnchantments.SWORD_SPIN.get())){
            player.startUsingItem(hand);
            if (!world.isClientSide()) {
                player.playNotifySound(SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1, 1);
            }
            return InteractionResultHolder.consume(itemstack);
        }
        else   return InteractionResultHolder.pass(itemstack);
    }

}
