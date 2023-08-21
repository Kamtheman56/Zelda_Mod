package com.kamth.zeldamod.item.masks;

import com.kamth.zeldamod.block.ModBlocks;
import com.kamth.zeldamod.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class RomaniMask extends ArmorItem {
    public RomaniMask(ArmorMaterial pMaterial, Type type, Properties pProperties) {
        super(pMaterial,type, pProperties);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerEntityInteract);
    }


    public void onPlayerEntityInteract(PlayerInteractEvent.EntityInteract event)
    {
        if( !event.getLevel().isClientSide && event.getHand() == InteractionHand.MAIN_HAND && event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.TRUTH_MASK.get()) {
            if (event.getTarget() instanceof Cow) {
                ItemStack itemstack = event.getEntity().getItemInHand(InteractionHand.MAIN_HAND);
                if (itemstack.is(Items.GLASS_BOTTLE)){
itemstack.shrink(1);
event.getEntity().addItem(ModItems.MILK_BOTTLE1.get().getDefaultInstance());
event.getTarget().playSound(SoundEvents.COW_MILK, 1, 1.8f);
                }


            }}}
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.literal("Moove").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));

    }

}
