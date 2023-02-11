package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.entity.custom.projectile.SeedProjectile;
import com.kamth.zeldamod.item.custom.util.ModTags;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nonnull;
import java.util.function.Predicate;

public class ScattershotItem extends SlingshotItem {
    public ScattershotItem(Properties pProperties) {
        super(pProperties);
    }
    public float velocity = 2.95f;
    public float inaccuracy = 1f;
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
                        SeedProjectile projectile2 = createAmmoEntity(world, itemStack);
                        SeedProjectile projectile3 = createAmmoEntity(world, itemStack);

                        projectile.setOwner(player);
                        projectile2.setOwner(player);
                        projectile3.setOwner(player);
                        //set x to +.5 and -.5 for original logic
                        projectile.setPos(player.getEyePosition(1F).add(0, -0.1, 0));
                        projectile2.setPos(player.getEyePosition(1F).add(0, 0.1, 0));
                        projectile3.setPos(player.getEyePosition(1F).add(0, -0.1, 0));
                      //  projectile.shoot(player.getLookAngle(), shotPower * 4F, 0.5F);
                      //  projectile2.shoot(player.getLookAngle(), shotPower * 4.5F, 3F);
                      //  projectile3.shoot(player.getLookAngle(), shotPower * 3.8F, -2F);
                        projectile.shootFromRotation(player, player.xRotO, player.yRotO, 0.0F, shotPower * velocity,.5f);
                        projectile2.shootFromRotation(player, player.xRotO, player.yRotO + 7, 0.0F, shotPower * velocity,.5f);
                       projectile3.shootFromRotation(player, player.xRotO, player.yRotO - 7, 0.0F, shotPower * velocity,.5f);
                        world.addFreshEntity(projectile);
                        world.addFreshEntity(projectile2);
                        world.addFreshEntity(projectile3);
                      //  stack.hurtAndBreak(1, player, (p_220009_1_) -> p_220009_1_.broadcastBreakEvent(player.getUsedItemHand()));
                    }

                    world.playSound((Player) entityLiving, player.getX(), player.getY(), player.getZ(), SoundEvents.EGG_THROW, SoundSource.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F)  * 0.5F);

                    if (!infiniteAmmo && !player.getAbilities().instabuild) {
                        itemStack.shrink(1);

                        if (itemStack.isEmpty()) {
                            player.getInventory().removeItem(itemStack);
                        }
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));

                }}}}
    @Nonnull
    private SeedProjectile createAmmoEntity(Level level, ItemStack itemStack) {
        return new SeedProjectile(level);
    }
    public static float getPowerForTime(int timeInUse) {
        float power = (float) timeInUse / 20.0F;
        power = (power * power + power * 4.0F) / 5.0F;

        if (power > 1.0F) {
            power = 1.5F;
        }

        return power;
    }
}
