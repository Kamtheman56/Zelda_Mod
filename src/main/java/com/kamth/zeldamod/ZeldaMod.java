package com.kamth.zeldamod;


import com.kamth.zeldamod.block.ZeldaBlocks;
import com.kamth.zeldamod.block.entity.ZeldaBlockEntities;
import com.kamth.zeldamod.custom.ModItemProperties;
import com.kamth.zeldamod.effect.ModEffects;
import com.kamth.zeldamod.entity.ModEntityTypes;
import com.kamth.zeldamod.entity.mobs.KeeseEntity;
import com.kamth.zeldamod.item.ZeldaCreativeTab;
import com.kamth.zeldamod.item.ModCreativeModeTab;
import com.kamth.zeldamod.item.ZeldaItems;
import com.kamth.zeldamod.loot.ModLootModifiers;
import com.kamth.zeldamod.networking.ZeldaNetworking;
import com.kamth.zeldamod.networking.packets.SwordSwingPacket;
import com.kamth.zeldamod.painting.ModPaintings;
import com.kamth.zeldamod.particle.ModParticles;
import com.kamth.zeldamod.sound.ModSounds;
import com.kamth.zeldamod.villager.ModVillagers;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(ZeldaMod.MOD_ID)
public class ZeldaMod {
    public static final String MOD_ID = "zeldamod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ZeldaMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModeTab.register(modEventBus);
        ZeldaItems.register(modEventBus);
        ModEntityTypes.register(modEventBus);
        ZeldaBlocks.register(modEventBus);
        ModSounds.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        ModVillagers.register(modEventBus);
        ModEffects.register(modEventBus);
        ModParticles.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        ModPaintings.register(modEventBus);
        ZeldaBlockEntities.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(ZeldaCreativeTab::addCreativeTabItems);

    }
    private void clientSetup(final FMLClientSetupEvent event){
        ModItemProperties.addCustomItemProperties();
    }

    private void commonSetup(final FMLCommonSetupEvent event){
        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntityTypes.KOROK.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,   Animal::checkAnimalSpawnRules);
            SpawnPlacements.register(ModEntityTypes.CHUCHU.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,  Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(ModEntityTypes.CHUCHU_FIRE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,  Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(ModEntityTypes.CHUCHU_ICE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,  Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(ModEntityTypes.CHUCHU_ELECTRIC.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,  Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(ModEntityTypes.SKULLTULA.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,  Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(ModEntityTypes.DEKU.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,  Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(ModEntityTypes.DEKU_MAD.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,  Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(ModEntityTypes.KEESE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, KeeseEntity::checkKeeseSpawnRules);



            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ZeldaBlocks.NIGHTSHADE.getId(), ZeldaBlocks.POTTED_NIGHTSHADE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ZeldaBlocks.SUNDELION.getId(), ZeldaBlocks.POTTED_SUNDELION);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ZeldaBlocks.HEART_FLOWER.getId(), ZeldaBlocks.POTTED_HEART_FLOWER);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ZeldaBlocks.PRIMO_FLOWER.getId(), ZeldaBlocks.POTTED_PRIMO_FLOWER);
        });

        ZeldaNetworking.registerPackets();
    }

    @Mod.EventBusSubscriber(modid = ZeldaMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class tickEventShit {
        public static boolean attackKeyWasPressed = false;

        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            Minecraft client = Minecraft.getInstance();

            if (event.phase == TickEvent.Phase.END) {
                if (client.options.keyAttack.isDown()) {
                    if (!attackKeyWasPressed) {
                        ZeldaNetworking.sendToServer(new SwordSwingPacket());
                        attackKeyWasPressed = true;

                    }
                }
            }
            else if (attackKeyWasPressed && !client.options.keyAttack.isDown()) {
                attackKeyWasPressed = false;
            }
        }
    }
}
