package com.kamth.zeldamod.item.masks;

import com.kamth.zeldamod.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class TruthMask extends ArmorItem {
    public TruthMask(ArmorMaterial pMaterial, Type type, Properties pProperties) {
        super(pMaterial,type, pProperties);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerEntityInteract);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerTick);
    }
    private static final AttributeModifier TRUTH = new AttributeModifier(UUID.fromString("dcd170af-4e0f-4307-9df9-8039f79f39c9"), "zeldamod:truthmask", 3, AttributeModifier.Operation.ADDITION);


    public void onPlayerEntityInteract(PlayerInteractEvent.EntityInteract event)
    {
        if( event.getLevel().isClientSide && event.getHand() == InteractionHand.MAIN_HAND && event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.TRUTH_MASK.get()) {
            if (event.getTarget() instanceof Wolf)
            {
                event.getEntity().sendSystemMessage(Component.literal(event.getEntity().getName().getString() + " Thank you for downloading the mod!"));}}}
    private void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.START) {return;}
        AttributeInstance truth = event.player.getAttribute(Attributes.LUCK);
        if (!truth.hasModifier(TRUTH) && event.player instanceof Player && event.player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.TRUTH_MASK.get()) {
            truth.addTransientModifier(TRUTH);}
        else {if (truth.hasModifier(TRUTH)) {
                truth.removeModifier(TRUTH);}}}
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if (stack.is(ModItems.TRUTH_MASK.get())) {
            components.add(Component.translatable("item.truth_mask.description").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }
    }

}
