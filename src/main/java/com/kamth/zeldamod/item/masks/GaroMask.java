package com.kamth.zeldamod.item.masks;

import com.kamth.zeldamod.entity.custom.projectile.SeedProjectile;
import com.kamth.zeldamod.item.ModItems;
import com.kamth.zeldamod.item.custom.util.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class GaroMask extends ArmorItem {
    public GaroMask(ArmorMaterial pMaterial, Type type, Properties pProperties) {
        super(pMaterial, type, pProperties);
    }
    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {

    if (player.getCooldowns().isOnCooldown(ModItems.GARO_MASK.get()))
    {
        return;
    }
   if (world.isNight() && player.level().getBiome(player.blockPosition()).is(Biomes.BADLANDS) && !world.isClientSide){
       if(new Random().nextFloat() > .9f) {
           Pillager pillager = EntityType.PILLAGER.create(player.level());
           player.level().addFreshEntity(pillager);
           pillager.setItemInHand(InteractionHand.MAIN_HAND, Items.CROSSBOW.getDefaultInstance());
     pillager.moveTo(player.getX()+2, player.getY() , player.getZ() +1, player.getYRot(), player.getXRot());
           player.getCooldowns().addCooldown(ModItems.GARO_MASK.get(),350);
       }}}


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.literal("Attracts unwanted attention").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));

    }

}
