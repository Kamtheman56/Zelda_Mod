package com.kamth.zeldamod.enchantments;

import com.kamth.zeldamod.util.interfaces.mixin.SwordSpinPlayerData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

// Code contributed by Deadlydiamond98 (c) 2024 under the MIT License.
// Added here with explicit permission by the original owner.

public class SwordSpin extends Enchantment {

    public SwordSpin() {
        super(Rarity.UNCOMMON, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    public static int doSwordSpin(Player player, int swordspinTicks, boolean swordspinActive) {

        Level level = player.level();

        SwordSpinPlayerData swordSpinPlayer = ((SwordSpinPlayerData) player);

        boolean hasSwordSpin = EnchantmentHelper.getItemEnchantmentLevel(ZeldaEnchantments.SWORD_SPIN.get(), player.getMainHandItem()) > 0;

        if (player.getUseItem().getItem() instanceof SwordItem && hasSwordSpin && player.isUsingItem()) {

            if (swordspinTicks == 25) {
                if (!level.isClientSide) {
                    player.playNotifySound(SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1, 2);
                }
                swordspinTicks++;
            }
            else if (swordspinTicks < 25) {
                swordspinTicks++;
            }
        }
        else if ((swordspinTicks >= 25 && !player.isUsingItem()) || swordspinActive) {

            if (!swordspinActive) {
                if (!level.isClientSide) {
                    player.playNotifySound(SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, 1, 1);
                }
                swordSpinPlayer.legendaryArmory$setSwordSpinActive(true);
            }
            else {
                swordspinTicks++;
                player.setYRot(player.getYRot() + 36);
            }

            if (swordspinActive && swordspinTicks > 35) {
                swordSpinPlayer.legendaryArmory$setSwordSpinActive(false);
                swordspinTicks = 0;
            }

            hurtEntitiesInSpin(player, 60, 12);
        }
        else {
            swordspinTicks = 0;
            swordSpinPlayer.legendaryArmory$setSwordSpinActive(false);
        }

        return swordspinTicks;
    }

    private static void hurtEntitiesInSpin(Player player, float maxAngle, float distance) {
        Level world = player.level();
        Vec3 playerPos = player.position();
        float playerYaw = player.getYRot();

        Vec3 playerLookVec = Vec3.directionFromRotation(0, playerYaw);

        for (Entity entity : world.getEntitiesOfClass(Entity.class, player.getBoundingBox().inflate(3), entity -> entity != player)) {
            Vec3 entityPos = entity.position();
            Vec3 toEntityVec = entityPos.subtract(playerPos).normalize();

            double angle = Math.toDegrees(Math.acos(playerLookVec.dot(toEntityVec)));

            if (angle <= maxAngle && playerPos.distanceToSqr(entityPos) <= distance) {

                if (entity instanceof LivingEntity living) {

                    double attributeDamage = player.getAttributeValue(Attributes.ATTACK_DAMAGE);
                    float damage = EnchantmentHelper.getDamageBonus(player.getMainHandItem(), living.getMobType());

                    entity.hurt(entity.damageSources().playerAttack(player), (float) ((attributeDamage + damage) * 2));

                    living.knockback(0.75, player.getX() - living.getX(), player.getZ() - living.getZ());
                }
            }
        }
    }


    @Override
    public boolean canEnchant(ItemStack stack) {
        return stack.getItem() instanceof SwordItem;
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }
}