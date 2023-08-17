package com.kamth.zeldamod.item;

import com.kamth.zeldamod.ZeldaMod;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ZeldaMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTab {
    public static  CreativeModeTab ZELDA_TAB;
@SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event){
        ZELDA_TAB = event.registerCreativeModeTab(new ResourceLocation(ZeldaMod.MOD_ID, "zelda_tab"),
                builder -> builder.icon(() -> new ItemStack(ModItems.BLUE_EMERALD.get())).title(Component.translatable("zelda_tab")).build());
    }



}




