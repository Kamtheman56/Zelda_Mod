package com.kamth.zeldamod.DataGen;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.item.ModArmorMaterials;
import com.mojang.serialization.Codec;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModEquipmentAssetProvider implements DataProvider {

    private final PackOutput.PathProvider pathProvider;

    public ModEquipmentAssetProvider (PackOutput packOutput){
        this.pathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "equipment");
    }

    private static void bootstrap(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> output){
        output.accept(ModArmorMaterials.KOKIRI_ARMOR_KEY, EquipmentClientInfo.builder()
                .addHumanoidLayers(Identifier.fromNamespaceAndPath(ZeldaMod.MOD_ID,"kokiri"),false).build());



        // Masks
        output.accept(ModArmorMaterials.DEKU_MASK_KEY, EquipmentClientInfo.builder()
                .addHumanoidLayers(Identifier.fromNamespaceAndPath(ZeldaMod.MOD_ID,"deku_mask"),false).build());

        output.accept(ModArmorMaterials.GIANTS_MASK_KEY, EquipmentClientInfo.builder()
                .addHumanoidLayers(Identifier.fromNamespaceAndPath(ZeldaMod.MOD_ID,"giants_mask"),false).build());




    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        Map<ResourceKey<EquipmentAsset>, EquipmentClientInfo> equipmentAssets = new HashMap();
        bootstrap((id, asset) -> {
            if (equipmentAssets.putIfAbsent(id, asset) != null) {
                throw new IllegalStateException("Tried to register equipment asset twice for id: " + String.valueOf(id));
            }
        });
        Codec var10001 = EquipmentClientInfo.CODEC;
        PackOutput.PathProvider var10002 = this.pathProvider;
        Objects.requireNonNull(var10002);
        return DataProvider.saveAll(cache, var10001, var10002::json, equipmentAssets);
    }


    @Override
    public String getName() {
        return "Zeldamod Equipment Definitions";
    }
}
