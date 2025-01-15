package com.kamth.zeldamod.item.masks;

import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class GaroMask extends ArmorItem {
    public GaroMask(ArmorMaterial pMaterial, Type type, Properties pProperties) {
        super(pMaterial, type, pProperties);
    }
    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {

    if (player.getCooldowns().isOnCooldown(ZeldaItems.GARO_MASK.get()))
    {
        return;
    }
   if (world.isNight() && player.level().getBiome(player.blockPosition()).is(Biomes.BADLANDS) && !world.isClientSide){
       if(new Random().nextFloat() > .9f) {
           Pillager pillager = EntityType.PILLAGER.create(player.level());
           player.level().addFreshEntity(pillager);
           pillager.setItemInHand(InteractionHand.MAIN_HAND, Items.CROSSBOW.getDefaultInstance());
     pillager.moveTo(player.getX()+2, player.getY() , player.getZ() +1, player.getYRot(), player.getXRot());
           player.getCooldowns().addCooldown(ZeldaItems.GARO_MASK.get(),500);
       }}}


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.translatable("item.garo_mask.description_advanced").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));}
  else   components.add(Component.translatable("item.garo_mask.description_basic").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));

    }

}
