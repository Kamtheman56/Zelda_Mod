package com.kamth.zeldamod;


import com.kamth.zeldamod.block.ModBlocks;
import com.kamth.zeldamod.effect.ModEffects;
import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.item.ModCreativeModeTab;
import com.kamth.zeldamod.item.ModItems;
import com.kamth.zeldamod.item.custom.util.ModItemProperties;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ZeldaMod.MOD_ID)
public class ZeldaMod
{
    public static final String MOD_ID = "zeldamod";

    private static final Logger LOGGER = LogUtils.getLogger();


    public ZeldaMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModEntityTypes.register(modEventBus);
        ModBlocks.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        ModEffects.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }
private void clientSetup(final FMLClientSetupEvent event){
       ModItemProperties.addCustomItemProperties();



}


    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }
private void addCreative(CreativeModeTabEvent.BuildContents event){
        if (event.getTab() == ModCreativeModeTab.ZELDA_TAB){
            event.accept(ModItems.RED_EMERALD);
        }
}
    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }

    }
}
