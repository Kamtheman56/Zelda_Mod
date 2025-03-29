package com.kamth.zeldamod.item.items.movement;

import com.kamth.zeldamod.item.ZeldaItems;
import com.kamth.zeldamod.item.items.TooltipItem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

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
                entity.resetFallDistance();
            }
            if (entity instanceof Player && ((Player) entity).getOffhandItem().getItem() instanceof FeatherItem && ((Player) entity).getItemBySlot(EquipmentSlot.FEET).getItem() == ZeldaItems.PEGASUS_BOOTS.get() || entity instanceof Player && ((Player) entity).getMainHandItem().getItem() instanceof FeatherItem && ((Player) entity).getItemBySlot(EquipmentSlot.FEET).getItem() == ZeldaItems.PEGASUS_BOOTS.get()) {
                ((Player) entity).addEffect(new MobEffectInstance(MobEffects.JUMP, 10, 3, true, false));
                entity.resetFallDistance();
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.translatable("item.zeldamod.feather.description_advanced").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));
        }
        else  components.add(Component.translatable("item.zeldamod.feather.description_basic").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));
        super.appendHoverText(stack, level, components, flag);
    }


}
