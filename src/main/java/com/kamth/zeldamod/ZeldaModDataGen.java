package com.kamth.zeldamod;


import com.kamth.zeldamod.DataGen.ModModelProvider;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = ZeldaMod.MOD_ID)
public class ZeldaModDataGen {
    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event){
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        generator.addProvider(true, new ModModelProvider(packOutput));
    }
}
