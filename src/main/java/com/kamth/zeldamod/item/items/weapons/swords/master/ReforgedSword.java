package com.kamth.zeldamod.item.items.weapons.swords.master;

import com.kamth.zeldamod.block.ZeldaBlocks;
import com.kamth.zeldamod.entity.projectile.magic.SwordBeam_Evil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class ReforgedSword extends TrueMasterSwordItem {

    public ReforgedSword(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties, 10);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);

        if (blockstate.is(ZeldaBlocks.COURAGE_FLAME.get())) {
            if (!(pContext.getItemInHand().getAllEnchantments().containsKey(Enchantments.KNOCKBACK))) {
                pContext.getItemInHand().enchant(Enchantments.KNOCKBACK, 3);
                pContext.getLevel().playSound(pContext.getPlayer(), blockpos, SoundEvents.AMETHYST_BLOCK_RESONATE, SoundSource.BLOCKS, 1, 1);
            }
            return InteractionResult.SUCCESS;
        }

        if (blockstate.is(ZeldaBlocks.WISDOM_FLAME.get())) {
            if (!(pContext.getItemInHand().getAllEnchantments().containsKey(Enchantments.FIRE_ASPECT))) {
                pContext.getItemInHand().enchant(Enchantments.FIRE_ASPECT, 3);
                pContext.getLevel().playSound(pContext.getPlayer(), blockpos, SoundEvents.AMETHYST_BLOCK_RESONATE, SoundSource.BLOCKS, 1, 1.4f);
            }
            return InteractionResult.SUCCESS;
        }

        if (blockstate.is(ZeldaBlocks.POWER_FLAME.get())) {
            if (!(pContext.getItemInHand().getAllEnchantments().containsKey(Enchantments.SHARPNESS))) {
                pContext.getItemInHand().enchant(Enchantments.SHARPNESS, 3);
                pContext.getLevel().playSound(pContext.getPlayer(), blockpos, SoundEvents.AMETHYST_BLOCK_RESONATE, SoundSource.BLOCKS, 1, 2f);
            }
            return InteractionResult.SUCCESS;
        } else return InteractionResult.FAIL;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            components.add(Component.translatable("item.zeldamod.reforged_sword.description_advanced").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            components.add(Component.translatable("item.zeldamod.master_sword.description_basic_2").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));

        } else {
            components.add(Component.translatable("item.zeldamod.reforged_sword.description_basic_1").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            components.add(Component.translatable("item.zeldamod.reforged_sword.description_basic_2").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
        }

    }

    @Override
    public void swingSword(Level world, Player player) {
        if (player.getHealth() >= player.getMaxHealth()) {
            if (!(player.getCooldowns().isOnCooldown(this))) {
                player.getCooldowns().addCooldown(this, 30);
                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, .8F, 5F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
                SwordBeam_Evil projectile = new SwordBeam_Evil(world, player);
                projectile.setOwner(player);
                projectile.setPos(player.getEyePosition(1F).add(0, -0.1, 0));
                projectile.shootFromRotation(player, player.xRotO, player.yRotO, 0.0F, 1.6f, 0f);
                world.addFreshEntity(projectile);
            }
        }
    }

}

