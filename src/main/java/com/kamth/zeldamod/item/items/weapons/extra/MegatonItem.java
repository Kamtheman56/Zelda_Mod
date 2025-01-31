package com.kamth.zeldamod.item.items.weapons.extra;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.kamth.zeldamod.block.ZeldaBlocks;
import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class MegatonItem extends TieredItem {
    private final float attackDamage;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

public MegatonItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
    super(pTier, pProperties);
    this.attackDamage = (float)pAttackDamageModifier + pTier.getAttackDamageBonus();
    ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
    builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", (double)this.attackDamage, AttributeModifier.Operation.ADDITION));
    builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", (double)pAttackSpeedModifier, AttributeModifier.Operation.ADDITION));
    this.defaultModifiers = builder.build();
}
    public float getDamage() {return this.attackDamage;
    }
    public  boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker)
    {
        return this instanceof MegatonItem;
    }

    public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        return !pPlayer.isCreative();
    }
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pStack.hurtAndBreak(1, pAttacker, (p_43296_) -> {
            p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        return true;
    }
    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pEquipmentSlot) {
        return pEquipmentSlot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(pEquipmentSlot);
    }

    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        Player player = pContext.getPlayer();;
        BlockState blockstate = level.getBlockState(blockpos);


        if (!player.getCooldowns().isOnCooldown(this)){
            level.playSound(player, blockpos, ModSounds.WOODEN_HAMMER.get(), SoundSource.PLAYERS,1,1);
            if (!level.isClientSide) {
                AABB box = AABB.ofSize(pContext.getClickedPos().getCenter(), 6, 0, 6);

                ((ServerLevel)level).sendParticles(ParticleTypes.CLOUD,  blockpos.getX(), blockpos.getY() + 2, blockpos.getZ(),
                        10, 3, 1, 3, 0);

                level.getEntitiesOfClass(LivingEntity.class, box.expandTowards(1,2,1),
                                livingEntity -> blockpos.closerThan(livingEntity.getOnPos(), 10) && livingEntity != player)
                        .forEach(livingEntity -> livingEntity.hurt(pContext.getLevel().damageSources().indirectMagic(player, player), 6.0F));
            }
            player.getCooldowns().addCooldown(this,100);
            return InteractionResult.SUCCESS;
        }
        else return InteractionResult.PASS;
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.translatable("item.zeldamod.megaton_hammer.description_advanced").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));
        }
        else {
            components.add(Component.translatable("item.zeldamod.megaton_hammer.description_basic").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));
        }
    }
}
