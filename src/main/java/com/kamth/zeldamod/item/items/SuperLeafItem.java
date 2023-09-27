package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.item.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SuperLeafItem extends Item {
    public SuperLeafItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        Vec3 vec3 = player.getDeltaMovement();
        ItemStack itemstack = player.getItemInHand(hand);
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.CHERRY_LEAVES_FALL, SoundSource.NEUTRAL, 1F, 0.2F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
            player.setDeltaMovement(vec3.x, 0.6, vec3.z);
        itemstack.hurtAndBreak(1, player, (p_41288_) -> {
            p_41288_.broadcastBreakEvent(hand);
        });


        return super.use(world, player, hand);
}}
