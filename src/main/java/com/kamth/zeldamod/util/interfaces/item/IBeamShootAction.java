package com.kamth.zeldamod.util.interfaces.item;

import com.kamth.zeldamod.entity.projectile.magic.SwordBeam;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public interface IBeamShootAction extends ISwingActionItem {
    @Override
    default void swingSword(Level world, Player player) {
        if (healthRequirement() && player.getHealth() < player.getMaxHealth()) {
            return;
        }

        if (!(player.getCooldowns().isOnCooldown((Item) this)) && !player.isCrouching()) {
            player.getCooldowns().addCooldown((Item) this, swingCooldownDuration());
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, 0.8f, 5 / (world.getRandom().nextFloat() * 0.4f + 0.8f));
            SwordBeam projectile = new SwordBeam(world, player);
            projectile.setOwner(player);
            projectile.setPos(player.getEyePosition().add(0, -0.1, 0));
            projectile.shootFromRotation(player, player.xRotO, player.yRotO, 0, 1.6f, 0);
            world.addFreshEntity(projectile);
        }
    }

    default int swingCooldownDuration() {
        return 30;
    }

    boolean healthRequirement();
}
