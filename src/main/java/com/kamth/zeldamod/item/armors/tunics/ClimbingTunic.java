package com.kamth.zeldamod.item.armors.tunics;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;

public class ClimbingTunic extends ArmorItem {
    public ClimbingTunic(ArmorMaterial p_40386_, Type type, Properties p_40388_) {
        super(p_40386_, type, p_40388_);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {


        if (!player.isSpectator() && player.horizontalCollision && player.zza > 0 && player.isCrouching() && !world.isRaining()){

            Vec3 initialVec = player.getDeltaMovement();
            Vec3 climbVec = new Vec3(initialVec.x, 0.09D, initialVec.z);
            player.setDeltaMovement(climbVec.x * 0.91D,
                    climbVec.y * 0.98D, climbVec.z * 0.91D);
            player.onClimbable();
            player.causeFoodExhaustion(-1f);

            double particleX = player.getX() + (player.getRandom().nextBoolean() ? -0.1D : 0);
            double particleY = player.getY() + player.getRandom().nextFloat() * 0 - 0.7D;
            double particleZ = player.getZ() + (player.getRandom().nextBoolean() ? -0.5D : 0);
            player.level().addParticle(ParticleTypes.POOF, particleX, particleY, particleZ, 0, 0, 0);
        }
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.translatable("armor.climbing_tunic.description").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));
        }
        super.appendHoverText(stack, level, components, flag);
    }
}