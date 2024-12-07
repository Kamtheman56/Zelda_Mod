package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.entity.custom.projectile.BombSeedProjectile;
import com.kamth.zeldamod.entity.custom.projectile.KorokSeedProjectile;
import com.kamth.zeldamod.entity.custom.projectile.SeedProjectile;
import com.kamth.zeldamod.item.ModItems;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
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
                    world.playSound((Player) entityLiving, player.getX(), player.getY(), player.getZ(), ModSounds.SLINGSHOT_RELEASE.get(), SoundSource.PLAYERS, .5F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F)  * 0.5F);

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
        Item bullet = itemStack.getItem();
        if (bullet == ModItems.BOMB_SEEDS.get()) {
            return new BombSeedProjectile(level);}
        if (bullet == ModItems.KOROK_SEED.get()) {
            return new KorokSeedProjectile(level);}
        else  return new SeedProjectile(level);
    }

    public static float getPowerForTime(int timeInUse) {
        float power = (float) timeInUse / 14.0F;
        power = (power * power + power * 2.0F) / 3.0F;

        if (power > 1.0F) {
            power = 1.0F;
        }

        return power;
    }

     @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        boolean flag = !pPlayer.getProjectile(itemstack).isEmpty();

        InteractionResultHolder<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, pLevel, pPlayer, pHand, flag);
        if (ret != null) return ret;

        if (!pPlayer.getAbilities().instabuild && !flag) {
            return InteractionResultHolder.fail(itemstack);
        } else {
            pPlayer.startUsingItem(pHand);
            pLevel.playSound(pPlayer, pPlayer.getOnPos(), ModSounds.SLINGSHOT_PULL.get(),SoundSource.PLAYERS);
            return InteractionResultHolder.consume(itemstack);
        }
    }




    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.translatable("item.slingshot.description_advanced").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));
        }
        else components.add(Component.translatable("item.slingshot.description_basic").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));

        super.appendHoverText(stack, level, components, flag);
    }
}
