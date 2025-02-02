package com.kamth.zeldamod.item.items.weapons.swords;

import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.entity.projectile.magic.SwordBeam;
import com.kamth.zeldamod.item.items.SwingActionItem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class TrueMasterSwordItem extends SwordItem implements SwingActionItem {
    public TrueMasterSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }
    @Override
    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        if (pState.is(ModTags.Blocks.DEMON)) {   return 28.0F;
        }
        else return 1;
    }

    public boolean isFoil(ItemStack pStack) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.translatable("item.zeldamod.master_sword_true.description_advanced").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
        } else {
            components.add(Component.translatable("item.zeldamod.master_sword_true.description_basic_1").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            components.add(Component.translatable("item.zeldamod.master_sword_true.description_basic_2").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
        }
    }

    @Override
    public void swingSword(Level world, Player player) {
            if (!(player.getCooldowns().isOnCooldown(this))) {
                player.getCooldowns().addCooldown(this, 15);
                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, .8F, 5F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
                SwordBeam projectile = new SwordBeam(world, player);
                projectile.setOwner(player);
                projectile.setPos(player.getEyePosition(1F).add(0, -0.1, 0));
                projectile.shootFromRotation(player, player.xRotO, player.yRotO, 0.0F, 1.6f, 0f);
                world.addFreshEntity(projectile);
            }
        }
    }


