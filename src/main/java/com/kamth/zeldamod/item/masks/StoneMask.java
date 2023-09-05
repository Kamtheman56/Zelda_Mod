package com.kamth.zeldamod.item.masks;

import com.kamth.zeldamod.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class StoneMask extends ArmorItem {


    public StoneMask(ArmorMaterial p_40386_, Type type, Properties p_40388_) {
        super(p_40386_, type, p_40388_);


    }
    @Override
    public boolean isEnderMask(ItemStack stack, Player player, EnderMan endermanEntity) {
        return stack.getItem() == ModItems.STONE_MASK.get();
    }
    @Override
    public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer){
        return stack.getItem() == ModItems.STONE_MASK.get();
    }


    //this adds effects that do not create particles.
    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
if (player.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.STONE_MASK.get())) {
    player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 10, 0, true, false));
}
        if (player.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.GIBDO_MASK.get())) {
        player.removeEffect(MobEffects.HUNGER);
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if (stack.is(ModItems.STONE_MASK.get())){
            components.add(Component.literal("Become as plain as stone").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
        if (stack.is(ModItems.GIBDO_MASK.get())){
            components.add(Component.literal("Hunger immune").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
    }
}