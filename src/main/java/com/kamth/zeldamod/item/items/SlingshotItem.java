package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.entity.custom.projectile.SeedProjectile;
import com.kamth.zeldamod.item.ModItems;
import com.kamth.zeldamod.item.custom.util.ModTags;
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
        return stack -> stack.is(ModTags.Items.SLING_AMMO);

}

    @Override
    public void releaseUsing(ItemStack stack, Level world, LivingEntity entityLiving, int timeLeft) {

        if (entityLiving instanceof Player) {
            Player player = (Player) entityLiving;
            boolean infiniteAmmo = player.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, stack) > 0;
            ItemStack itemStack = player.getProjectile(stack);
            int i = getUseDuration(stack) - timeLeft;
            i = ForgeEventFactory.onArrowLoose(stack, world, player, i, !itemStack.isEmpty() || infiniteAmmo);

            if (itemStack.getItem() == Items.ARROW) {
                itemStack = new ItemStack(Items.WHEAT_SEEDS);
            }

            if (i < 0) {
                return;
            }

            if (!itemStack.isEmpty() || infiniteAmmo) {
                float shotPower = getPowerForTime(i) * 0.5f;

                if (shotPower >= 0.1D) {
                    if (!world.isClientSide) {
                       SeedProjectile projectile = createAmmoEntity(world, itemStack);
                        projectile.setOwner(player);
                        projectile.setPos(player.getEyePosition(1F));
                        projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, shotPower * 3.0F, 1.0F);
                        world.addFreshEntity(projectile);
                    }
                    itemStack.hurtAndBreak(1, player, (p_40665_) -> p_40665_.broadcastBreakEvent(player.getUsedItemHand()));
                    world.playSound((Player) entityLiving, player.getX(), player.getY(), player.getZ(), SoundEvents.EGG_THROW, SoundSource.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F)  * 0.5F);

                    if (!infiniteAmmo && !player.getAbilities().instabuild) {
                        itemStack.shrink(1);

                        if (itemStack.isEmpty()) {
                            player.getInventory().removeItem(itemStack);
                        }
                    }
                    stack.hurtAndBreak(1, player, (p_40665_) -> {
                        p_40665_.broadcastBreakEvent(player.getUsedItemHand());
                    });

                    player.awardStat(Stats.ITEM_USED.get(this));

                }}}}
    @Nonnull
    private SeedProjectile createAmmoEntity(Level level, ItemStack itemStack) {
        return new SeedProjectile(level);
    }

    public static float getPowerForTime(int timeInUse) {
        float power = (float) timeInUse / 14.0F;
        power = (power * power + power * 2.0F) / 3.0F;

        if (power > 1.0F) {
            power = 1.0F;
        }

        return power;
    }
}
