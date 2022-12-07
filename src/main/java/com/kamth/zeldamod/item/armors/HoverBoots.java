package com.kamth.zeldamod.item.armors;

import com.kamth.zeldamod.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import org.lwjgl.system.linux.XMotionEvent;

import javax.swing.text.JTextComponent;
import java.util.UUID;

//Todo set a timer for hover effect!

@SuppressWarnings("ReassignedVariable")
public class HoverBoots extends ArmorItem {


    public HoverBoots(ArmorMaterial p_40386_, EquipmentSlot p_40387_, Properties p_40388_) {
        super(p_40386_, p_40387_, p_40388_);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerTick);

    }


    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        Level level = world;
        if (player.isSprinting() && player.getDeltaMovement().y > 0 && !player.isOnGround()) {
            player.fallDistance = -3;
            player.setNoGravity(true);
            player.setDeltaMovement(Vec3.fromRGB24(player.getMotionDirection().getStepY()).add(0, 0, 0));

        }

        if (!player.isOnGround() && !player.isSprinting() && player.getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.HOVER_BOOTS.get()) {
            player.setNoGravity(false);
            player.fallDistance = -3;
        }

    }

                //player.setDeltaMovement(player.getDeltaMovement().relative(Direction.UP, 2));





        private void onPlayerTick(TickEvent.PlayerTickEvent event) {
            if (event.phase != TickEvent.Phase.START) {

                return;
            }







    }}





