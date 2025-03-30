package com.kamth.zeldamod.enchantments;

import com.kamth.zeldamod.ZeldaMod;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ZeldaEnchantments {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, ZeldaMod.MOD_ID);

    public static final RegistryObject<Enchantment> SWORD_SPIN = ENCHANTMENTS.register("swordspin", SwordSpin::new);



    public static void register(IEventBus eventbus) {
        ENCHANTMENTS.register(eventbus);
    }
}
