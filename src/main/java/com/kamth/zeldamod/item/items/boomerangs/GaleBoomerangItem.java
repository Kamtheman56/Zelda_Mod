package com.kamth.zeldamod.item.items.boomerangs;

import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.entity.custom.projectile.GaleBoomerangProjectile;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class GaleBoomerangItem extends Item {
    public GaleBoomerangItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand pHand) {
        ItemStack itemstack = player.getItemInHand(pHand);
        pLevel.playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.BOOMERANG_TOSS.get(), SoundSource.PLAYERS, 1F, 0.2F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!pLevel.isClientSide) {
            player.getCooldowns().addCooldown(this, 35);
            GaleBoomerangProjectile boomerang = new GaleBoomerangProjectile(ModEntityTypes.GALE_BOOMERANG.get(), pLevel, player);
            boomerang.shootFromRotation(player, player.getXRot(), player.getYRot(), 1, 1.6F, 0.9F);
            boomerang.setThrowData(pHand.ordinal(), itemstack);
            pLevel.addFreshEntity(boomerang);
        }
        player.awardStat(Stats.ITEM_USED.get(this));
        itemstack.shrink(1);
        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.translatable("item.gale_boomerang.description").withStyle(ChatFormatting.GREEN).withStyle(ChatFormatting.ITALIC));
        }
        super.appendHoverText(stack, level, components, flag);
    }
}



