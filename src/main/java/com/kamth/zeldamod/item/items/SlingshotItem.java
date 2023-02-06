package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.entity.custom.projectile.SeedProjectile;
import com.kamth.zeldamod.item.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nonnull;
import java.util.function.Predicate;

public class SlingshotItem extends BowItem {
    public SlingshotItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return stack -> stack.is(Items.WHEAT_SEEDS);

}

    @Override
    public void releaseUsing(ItemStack stack, Level world, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof Player) {
            Player player = (Player) entityLiving;
            boolean infiniteAmmo = player.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, stack) > 0;
            ItemStack ammoStack = player.getProjectile(stack);
            int i = getUseDuration(stack) - timeLeft;
            i = ForgeEventFactory.onArrowLoose(stack, world, player, i, !ammoStack.isEmpty() || infiniteAmmo);

            if (ammoStack.getItem() == Items.ARROW) {
                ammoStack = new ItemStack(Items.WHEAT_SEEDS);
            }

            if (i < 0) {
                return;
            }

            if (!ammoStack.isEmpty() || infiniteAmmo) {
                float shotPower = getPowerForTime(i) * 0.5f;

                if (shotPower >= 0.1D) {
                    if (!world.isClientSide) {
                       SeedProjectile projectile = createAmmoEntity(world, ammoStack);
                        projectile.setOwner(player);
                        projectile.setPos(player.getEyePosition(1F).add(0, -0.1, 0));
                        projectile.shoot(player.getLookAngle(), shotPower * 3F, 0F);
                        world.addFreshEntity(projectile);
                    }



                    if (!infiniteAmmo && !player.getAbilities().instabuild) {
                        ammoStack.shrink(1);

                        if (ammoStack.isEmpty()) {
                            player.getInventory().removeItem(ammoStack);
                        }
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }
    @Nonnull
    private SeedProjectile createAmmoEntity(Level level, ItemStack ammoStack) {
        Item ammoItem = ammoStack.getItem();

        if (ammoItem == Items.BEETROOT_SEEDS) {
            return new SeedProjectile(level);
        } else if (ammoItem == Items.WHEAT_SEEDS) {
            return new SeedProjectile(level);
        } else if (ammoItem == Items.MELON_SEEDS) {
            return new SeedProjectile(level);
        } else if (ammoItem == Items.PUMPKIN_SEEDS) {
            return new SeedProjectile(level);
        } else if (ammoItem == Items.COCOA_BEANS) {
            return new SeedProjectile(level);
        }

        return new SeedProjectile(level);
    }
    public static float getPowerForTime(int timeInUse) {
        float power = (float) timeInUse / 20.0F;
        power = (power * power + power * 2.0F) / 3.0F;

        if (power > 1.0F) {
            power = 1.0F;
        }

        return power;
    }
}
