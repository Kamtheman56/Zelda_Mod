package com.kamth.zeldamod.item.armors;

import com.kamth.zeldamod.effect.ModEffects;
import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class ZoraArmor extends ArmorItem {
    public ZoraArmor(ArmorMaterial p_40386_, Type type, Properties p_40388_) {
        super(p_40386_, type, p_40388_);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerTick);
    }

    private static final AttributeModifier ZORA_ARMOR = new AttributeModifier(UUID.fromString("90998b0e-4188-403a-b3e5-1487d341d350"), "zeldamod:zora_armor", 1.2, AttributeModifier.Operation.ADDITION);



    private void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;


        if (event.phase != TickEvent.Phase.START) {
            return;}
        AttributeInstance zora_armor = player.getAttribute(ForgeMod.SWIM_SPEED.get());

        if (!zora_armor.hasModifier(ZORA_ARMOR) && player instanceof Player
                && player.getItemBySlot(EquipmentSlot.CHEST).getItem() == ZeldaItems.ZORA_CHESTPLATE.get())
        {
            zora_armor.addTransientModifier(ZORA_ARMOR);}
        else {
            if (zora_armor.hasModifier(ZORA_ARMOR)) {
                zora_armor.removeModifier(ZORA_ARMOR);}
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.translatable("armor.zora_chestplate_description").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.ITALIC));
        }
        super.appendHoverText(stack, level, components, flag);
    }
}