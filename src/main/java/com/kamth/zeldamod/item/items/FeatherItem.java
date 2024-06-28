package com.kamth.zeldamod.item.items;

import com.kamth.zeldamod.item.ModItems;
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
                if (entity instanceof Player && ((Player) entity).getOffhandItem().getItem() instanceof  FeatherItem) {
                    ((Player) entity).addEffect(new MobEffectInstance(MobEffects.JUMP, 10, 1, true, false));
                    entity.resetFallDistance();
                }
                if  (entity instanceof Player && ((Player) entity).getOffhandItem().getItem() instanceof  FeatherItem && ((Player) entity).getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.PEGASUS_BOOTS.get()) {
                    ((Player) entity).addEffect(new MobEffectInstance(MobEffects.JUMP, 10, 3, true, false));
                    entity.resetFallDistance();
            }
                if (entity instanceof Player && ((Player) entity).getMainHandItem().getItem() instanceof  FeatherItem && ((Player) entity).getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.PEGASUS_BOOTS.get()) {
                    ((Player) entity).addEffect(new MobEffectInstance(MobEffects.JUMP, 10, 3, true, false));
                    entity.resetFallDistance();
            }
                if (entity instanceof Player && ((Player) entity).getMainHandItem().getItem() instanceof  FeatherItem ) {
                    ((Player) entity).addEffect(new MobEffectInstance(MobEffects.JUMP, 10, 1, true, false));
                    entity.resetFallDistance();}
            }
        }

    @Override

    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        Vec3 vec3 = player.getDeltaMovement();

world.playSound(null,player.getX(),player.getY(),player.getZ(), SoundEvents.WOOL_FALL, SoundSource.NEUTRAL, 1F, 0.2F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        player.setDeltaMovement(vec3.x, 0.6, vec3.z);
        player.getCooldowns().addCooldown(this, 50);
        if (player.getItemBySlot(EquipmentSlot.MAINHAND).getItem() instanceof FeatherItem){
        player.getItemBySlot(EquipmentSlot.MAINHAND).hurtAndBreak(2, player, (p_43296_) -> {
            p_43296_.broadcastBreakEvent(InteractionHand.MAIN_HAND);
        });}
        if (player.getItemBySlot(EquipmentSlot.OFFHAND).getItem() instanceof FeatherItem){
            player.getItemBySlot(EquipmentSlot.OFFHAND).hurtAndBreak(2, player, (p_43296_) -> {
                p_43296_.broadcastBreakEvent(InteractionHand.OFF_HAND);
            });}
        return super.use(world, player, hand);
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
