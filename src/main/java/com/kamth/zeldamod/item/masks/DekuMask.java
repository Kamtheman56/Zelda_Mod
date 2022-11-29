package com.kamth.zeldamod.item.masks;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import net.minecraft.world.level.block.Blocks;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Tilt;
import net.minecraftforge.common.MinecraftForge;

import net.minecraftforge.eventbus.api.Event;

import javax.annotation.Nullable;

import static net.minecraft.world.level.block.BigDripleafBlock.*;


public class DekuMask extends ArmorItem {
    public DekuMask(ArmorMaterial p_40386_, EquipmentSlot p_40387_, Properties p_40388_) {
        super(p_40386_, p_40387_, p_40388_);
        MinecraftForge.EVENT_BUS.addListener(this::setNewSize);
    }

    private void setNewSize(Event event) {
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {

        player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 10, 1, true, false));
        player.addEffect(new MobEffectInstance(MobEffects.JUMP, 10, 2, true, false));
        {
            //Simulate the jumping flowers from MM. Replace with modded flower later
            Level level = world;
            if (level.getBlockState(player.getOnPos()).getBlock() == Blocks.HAY_BLOCK) {
                if (player.isCrouching()) {
                    player.addEffect(new MobEffectInstance(MobEffects.JUMP, 10, 6, true, false));


                }

            }
            if (level.getBlockState(player.getOnPos()).getBlock() == Blocks.REDSTONE_LAMP) {
                if (player.isCrouching()) {



                }

            }


        }


    }




    }


