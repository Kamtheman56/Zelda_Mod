package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.entity.custom.projectile.BombSeedProjectile;
import com.kamth.zeldamod.entity.custom.projectile.SeedProjectile;
import com.kamth.zeldamod.item.ModItems;
import com.kamth.zeldamod.item.custom.ModTags;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nonnull;
import java.util.function.Predicate;

public class ScattershotItem extends SlingshotItem {
    public static final int MAX_DRAW_DURATION = 10;
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

                        projectile.setPos(player.getEyePosition(1F));
                        projectile2.setPos(player.getEyePosition(1F));
                        projectile3.setPos(player.getEyePosition(1F));

                        projectile.shootFromRotation(player, player.xRotO, player.yRotO, 0.0F, shotPower * velocity,.5f);
                        projectile2.shootFromRotation(player, player.xRotO, player.yRotO + 7, 0.0F, shotPower * velocity,.5f);
                       projectile3.shootFromRotation(player, player.xRotO, player.yRotO - 7, 0.0F, shotPower * velocity,.5f);
                        world.addFreshEntity(projectile);
                        world.addFreshEntity(projectile2);
                        world.addFreshEntity(projectile3);
                       stack.hurtAndBreak(1, player, (p_220009_1_) -> p_220009_1_.broadcastBreakEvent(player.getUsedItemHand()));
                    }

                    world.playSound((Player) entityLiving, player.getX(), player.getY(), player.getZ(), SoundEvents.EGG_THROW, SoundSource.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F)  * 0.5F);

                    if (!infiniteAmmo && !player.getAbilities().instabuild) {
                        itemStack.shrink(1);

                        if (itemStack.isEmpty()) {
                            player.getInventory().removeItem(itemStack);}}
                    player.awardStat(Stats.ITEM_USED.get(this));

                }}}}
    @Nonnull
    private SeedProjectile createAmmoEntity(Level level, ItemStack itemStack) {
        Item bullet = itemStack.getItem();
        if (bullet == ModItems.BOMB_SEEDS.get()) {
            return new BombSeedProjectile(level);}
        else  return new SeedProjectile(level);
    }
    public static float getPowerForTime(int timeInUse) {
        float power = (float) timeInUse / 20.0F;
        power = (power * power + power * 3.0F) / 4.0F;

        if (power > 1.0F) {
            power = 1.5F;
        }

        return power;
    }
}
