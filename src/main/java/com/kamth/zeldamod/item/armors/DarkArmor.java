package com.kamth.zeldamod.item.armors;

import com.kamth.zeldamod.effect.ModEffects;
import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class DarkArmor extends ArmorItem {
    public DarkArmor(ArmorMaterial p_40386_, Type type, Properties p_40388_) {
        super(p_40386_, type, p_40388_);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {


        player.addEffect(new MobEffectInstance(ModEffects.GLOOM_RESIST.get(), 10, 0, true, false, true));

        if (player.getItemBySlot(EquipmentSlot.HEAD).is(ZeldaItems.DARK_HAT.get()) && player.getItemBySlot(EquipmentSlot.LEGS).is(ZeldaItems.DARK_PANTS.get())
         && player.getItemBySlot(EquipmentSlot.FEET).is(ZeldaItems.DARK_BOOTS.get()) && world.isNight()
        ){

            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 10, 2, true, false, false));
    }
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.translatable("armor.dark_tunic_description").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));
        }
        super.appendHoverText(stack, level, components, flag);
    }
}