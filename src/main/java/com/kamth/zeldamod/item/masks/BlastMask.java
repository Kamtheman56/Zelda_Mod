package com.kamth.zeldamod.item.masks;

import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;

public class BlastMask extends ArmorItem {
    public BlastMask(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }
    @Override
    public void onArmorTick (ItemStack stack, Level world, Player player) {

        if (player.getCooldowns().isOnCooldown(ZeldaItems.BLAST_MASK.get()))
        {
            return;
        }
            if (player.isCrouching() && !player.isBlocking() ){
        Vec3 explosionPos = player.getEyePosition(1.0F).add(player.getLookAngle().multiply(.5D, .5D, .5D));
                player.getItemBySlot(EquipmentSlot.HEAD).hurtAndBreak(7, player, (p_43296_) -> {
                    p_43296_.broadcastBreakEvent(EquipmentSlot.HEAD);});
        player.level().explode(player, explosionPos.x, explosionPos.y, explosionPos.z, 4F, Level.ExplosionInteraction.NONE);
                player.getCooldowns().addCooldown(ZeldaItems.BLAST_MASK.get(),350);
       player.hurt(player.damageSources().magic(), 10);
            }
            else if (player.isCrouching() && player.isBlocking()){
            Vec3 explosionPos = player.getEyePosition(1.0F).add(player.getLookAngle().multiply(.5D, .5D, .5D));
            player.level().explode(player, explosionPos.x, explosionPos.y, explosionPos.z, 4F, Level.ExplosionInteraction.NONE);
            player.getCooldowns().addCooldown(ZeldaItems.BLAST_MASK.get(),400);
                player.hurt(player.damageSources().magic(), 0);
                player.getItemBySlot(EquipmentSlot.HEAD).hurtAndBreak(3, player, (p_43296_) -> {
                    p_43296_.broadcastBreakEvent(EquipmentSlot.HEAD);});
}}
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.translatable("item.blast_mask.description").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));
}}
