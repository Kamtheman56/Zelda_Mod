package com.kamth.zeldamod.item.masks;

import com.kamth.zeldamod.item.ModItems;
import com.kamth.zeldamod.item.custom.util.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ScentMask extends ArmorItem {
    public ScentMask(ArmorMaterial pMaterial, Type type, Properties pProperties) {
        super(pMaterial, type, pProperties);
    }
    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if (player.getCooldowns().isOnCooldown(ModItems.SCENT_MASK.get()))
        {
            return;
        }
        if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.SCENT_MASK.get() && level.getBlockState(player.getOnPos()).is(ModTags.Blocks.SCENT) ) {
            player.setSwimming(true);
            player.hasPose(Pose.SWIMMING);
            if (level.isRaining()){
                if(new Random().nextFloat() > .9f) {
                    player.playSound(SoundEvents.PIGLIN_ADMIRING_ITEM);
                    player.spawnAtLocation(Items.BROWN_MUSHROOM);
                    player.getCooldowns().addCooldown(ModItems.SCENT_MASK.get(),350);
                }}
        }
     }
    @Override
    public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer){
        return stack.getItem() == ModItems.SCENT_MASK.get();
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.literal("Smells like mushrooms").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));

    }

}
