package com.kamth.zeldamod.item.masks;

import com.kamth.zeldamod.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Explosion;
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

        if (player.getCooldowns().isOnCooldown(ModItems.BLAST_MASK.get()))
        {
            return;
        }
            if (player.isCrouching() && !player.isBlocking() ){
        Vec3 explosionPos = player.getEyePosition(1.0F).add(player.getLookAngle().multiply(.5D, .5D, .5D));
        player.level().explode(player, explosionPos.x, explosionPos.y, explosionPos.z, 4F, Level.ExplosionInteraction.NONE);
                player.getCooldowns().addCooldown(ModItems.BLAST_MASK.get(),350);
       player.hurt(DamageSource.class.cast(DamageTypes.MAGIC), 10);
            }
            else if (player.isCrouching() && player.isBlocking()){
            Vec3 explosionPos = player.getEyePosition(1.0F).add(player.getLookAngle().multiply(.5D, .5D, .5D));
            player.level().explode(player, explosionPos.x, explosionPos.y, explosionPos.z, 4F, Level.ExplosionInteraction.NONE);
            player.getCooldowns().addCooldown(ModItems.BLAST_MASK.get(),400);
            player.hurt(DamageSource.class.cast(DamageTypes.MAGIC), 0);
}}
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.literal("Crouch for a blast").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
}}
