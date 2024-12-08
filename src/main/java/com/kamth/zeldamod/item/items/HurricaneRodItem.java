package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.entity.custom.projectile.GustProjectile;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;

public class HurricaneRodItem extends TornadoRodItem {
    public HurricaneRodItem(Properties pProperties) {
        super(pProperties);
    }



    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (player.onGround() && !player.isCrouching()) {
            Vec3 vec3 = player.getDeltaMovement();
            player.resetFallDistance();
            player.setDeltaMovement(vec3.x, 1.7, vec3.z);
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PARROT_FLY, SoundSource.NEUTRAL, 1F, 2F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
            player.getCooldowns().addCooldown(this, 30);
            double particleX = player.getX() + (player.getRandom().nextBoolean() ? 0.1D : 0);
            double particleY = player.getY() + player.getRandom().nextFloat() * 0 + 1.2D;
            double particleZ = player.getZ() + (player.getRandom().nextBoolean() ? +0.8D : 0);
            player.level().addParticle(ParticleTypes.CLOUD, particleX, particleY, particleZ, 0, 0, 0);
            player.level().addParticle(ParticleTypes.CLOUD, particleX+.2, particleY, particleZ, 0, 0, 0);
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 30, 0,true,false));
            ItemStack stack = player.getItemInHand(hand);
            stack.hurtAndBreak(2, player, (p_43296_) -> {
                p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                p_43296_.broadcastBreakEvent(EquipmentSlot.OFFHAND);
            });
        }
        player.awardStat(Stats.ITEM_USED.get(this));
        if  (!player.onGround() && !player.onClimbable()) {
            player.startUsingItem(InteractionHand.MAIN_HAND);
            player.startUsingItem(InteractionHand.OFF_HAND);
        }
        if (player.onGround() && player.isCrouching()){
            ItemStack stack = player.getItemInHand(hand);
            stack.setDamageValue(stack.getDamageValue() + 4);
            if (stack.getDamageValue() >= stack.getMaxDamage()) stack.setCount(0);
            GustProjectile bombEntity = new GustProjectile(world,player);
            bombEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 1, .5F, 0.7F);
            world.addFreshEntity(bombEntity);
            player.getCooldowns().addCooldown(this, 25);
        }
        return super.use(world, player, hand);
    }


    public boolean isFoil(ItemStack pStack) {
        return true;
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.translatable("item.hurricane_rod.description").withStyle(ChatFormatting.GREEN).withStyle(ChatFormatting.ITALIC));
        }
        super.appendHoverText(stack, level, components, flag);
    }

}


//   player.startUsingItem(InteractionHand.MAIN_HAND);




