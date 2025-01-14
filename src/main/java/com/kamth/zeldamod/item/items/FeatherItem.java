package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class FeatherItem extends Item {
    public FeatherItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int pSlotId, boolean pIsSelected) {
        if (!world.isClientSide) {
            if (entity instanceof Player && ((Player) entity).getOffhandItem().getItem() instanceof FeatherItem || entity instanceof Player && ((Player) entity).getMainHandItem().getItem() instanceof FeatherItem) {
                ((Player) entity).addEffect(new MobEffectInstance(MobEffects.JUMP, 10, 1, true, false));

            }
            if (entity instanceof Player && ((Player) entity).getOffhandItem().getItem() instanceof FeatherItem && ((Player) entity).getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.PEGASUS_BOOTS.get() ||entity instanceof Player && ((Player) entity).getMainHandItem().getItem() instanceof FeatherItem && ((Player) entity).getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.PEGASUS_BOOTS.get() ) {
                ((Player) entity).addEffect(new MobEffectInstance(MobEffects.JUMP, 10, 3, true, false));

            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.translatable("item.feather.description_advanced").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));
        }
        else  components.add(Component.translatable("item.feather.description_basic").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));
        super.appendHoverText(stack, level, components, flag);
    }


}
