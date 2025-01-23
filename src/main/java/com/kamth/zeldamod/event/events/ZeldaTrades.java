package com.kamth.zeldamod.event.events;

import com.kamth.zeldamod.villager.ModVillagers;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;

import java.util.List;

import static com.kamth.zeldamod.item.ZeldaItems.*;
import static net.minecraft.world.item.Items.*;

public class ZeldaTrades {

    public static void addRegularTrades(VillagerTradesEvent event) {
        Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

        // Vanilla

        if (event.getType() == VillagerProfession.WEAPONSMITH) {

            // 1
            addTrade(trades, 1,
                    new ItemStack(EMERALD, 4), // Cost
                    new ItemStack(KOKIRI_SWORD_OOT.get()), 3, 6, 0.02f
            );

            // 2
            addTrade(trades, 2,
                    new ItemStack(EMERALD, 15), new ItemStack(KOKIRI_SWORD_OOT.get()), // Cost
                    new ItemStack(RAZOR_SWORD.get()), 2, 30, 0.02f
            );

            // 5
            addTrade(trades, 5,
                    new ItemStack(GOLD_DUST.get()), new ItemStack(RAZOR_SWORD.get()), // Cost
                    new ItemStack(GILDED_SWORD.get()), 2, 65, 0.02f
            );

        }

        if (event.getType() == VillagerProfession.FLETCHER) {

            // 2
            addTrade(trades, 2,
                    new ItemStack(Items.EMERALD, 6), // Cost
                    new ItemStack(SLINGSHOT.get()), 3, 18, 0.02f
            );

            // 3
            addTrade(trades, 3,
                    new ItemStack(Items.EMERALD, 24), // Cost
                    new ItemStack(BOMB_ARROW.get(), 5), 3, 18, 0.08f
            );
            addTrade(trades, 3,
                    new ItemStack(Items.EMERALD, 8), // Cost
                    new ItemStack(FIRE_ARROW.get(), 5), 5, 18, 0.02f
            );

            // 4
            addTrade(trades, 4,
                    new ItemStack(Items.EMERALD, 8), // Cost
                    new ItemStack(ICE_ARROW.get(), 5), 5, 38, 0.04f
            );

            // 5
            addTrade(trades, 5,
                    new ItemStack(Items.EMERALD, 24), // Cost
                    new ItemStack(LIGHT_ARROW.get(), 5), 2, 60, 0.03f
            );
            addTrade(trades, 5,
                    new ItemStack(Items.EMERALD, 24), // Cost
                    new ItemStack(HAWKEYE.get()), 1, 18, 30.0f
            );

        }

        if (event.getType() == VillagerProfession.ARMORER) {

            // 1
            addTrade(trades, 1,
                    new ItemStack(Items.EMERALD, 6), // Cost
                    new ItemStack(BOMB.get(), 3), 8, 2, 0.02f
            );

        }

        // Modded

        if (event.getType() == ModVillagers.MASK_TRADER.get()) {

            // 1
            addTrade(trades, 1,
                    new ItemStack(EMERALD, 6), // Cost
                    new ItemStack(KEATON_MASK.get()), 2, 6, 0.02f
            );
            addTrade(trades, 1,
                    new ItemStack(EMERALD, 6), // Cost
                    new ItemStack(GERO_MASK.get()), 2, 6, 0.02f
            );
            addTrade(trades, 1,
                    new ItemStack(EMERALD, 6), // Cost
                    new ItemStack(GERUDO_MASK.get()), 2, 5, 0.02f
            );
            addTrade(trades, 1,
                    new ItemStack(EMERALD, 4), // Cost
                    new ItemStack(SPOOKY_MASK.get()), 2, 4, 0.02f
            );
            addTrade(trades, 1,
                    new ItemStack(EMERALD, 6), // Cost
                    new ItemStack(SKULL_MASK.get()), 2, 6, 0.02f
            );

            // 2
            addTrade(trades, 2,
                    new ItemStack(EMERALD, 8), // Cost
                    new ItemStack(COUPLES_MASK.get()), 2, 35, 0.02f
            );
            addTrade(trades, 2,
                    new ItemStack(EMERALD, 6), // Cost
                    new ItemStack(KAMARO_MASK.get()), 2, 35, 0.05f
            );
            addTrade(trades, 2,
                    new ItemStack(EMERALD, 7), // Cost
                    new ItemStack(SCENT_MASK.get()), 2, 35, 0.02f
            );
            addTrade(trades, 2,
                    new ItemStack(EMERALD, 3), // Cost
                    new ItemStack(TROUPE_MASK.get()), 2, 35, 0.02f
            );

            // 3
            addTrade(trades, 3,
                    new ItemStack(EMERALD, 14), // Cost
                    new ItemStack(BLAST_MASK.get()), 2, 75, 0.02f
            );
            addTrade(trades, 3,
                    new ItemStack(EMERALD, 14), // Cost
                    new ItemStack(BREMEN_MASK.get()), 2, 75, 0.02f
            );
            addTrade(trades, 3,
                    new ItemStack(EMERALD, 20), // Cost
                    new ItemStack(BUNNY_HOOD.get()), 2, 75, 0.04f
            );

            // 4
            addTrade(trades, 4,
                    new ItemStack(EMERALD, 18), // Cost
                    new ItemStack(KAFEI_MASK.get()), 1, 125, 0.03f
            );
            addTrade(trades, 4,
                    new ItemStack(EMERALD, 18), // Cost
                    new ItemStack(TRUTH_MASK.get()), 1, 125, 0.03f
            );
            addTrade(trades, 4,
                    new ItemStack(EMERALD, 20), // Cost
                    new ItemStack(DEKU_MASK.get()), 1, 125, 0.06f
            );

            // 5
            addTrade(trades, 5,
                    new ItemStack(EMERALD, 22), // Cost
                    new ItemStack(GORON_MASK.get()), 1, 250, 0.06f
            );
            addTrade(trades, 5,
                    new ItemStack(EMERALD, 28), // Cost
                    new ItemStack(ZORA_MASK.get()), 1, 250, 0.07f
            );
            addTrade(trades, 5,
                    new ItemStack(EMERALD, 35), // Cost
                    new ItemStack(POSTMAN_MASK.get()), 1, 250, 0.07f
            );


        }

        if (event.getType() == ModVillagers.MORSHU.get()) {

            // 1
            addTrade(trades, 1,
                    new ItemStack(KOROK_SEED.get(), 8), // Cost
                    new ItemStack(BLUE_EMERALD.get()), 10, 6, 0.02f
            );
            addTrade(trades, 1,
                    new ItemStack(BLUE_EMERALD.get(), 2), // Cost
                    new ItemStack(BOMB.get(), 8), 5, 6, 0.02f
            );

            // 2
            addTrade(trades, 2,
                    new ItemStack(BLUE_EMERALD.get(), 2), // Cost
                    new ItemStack(WALLET.get()), 5, 6, 0.02f
            );
            addTrade(trades, 2,
                    new ItemStack(RED_EMERALD.get(), 2), // Cost
                    new ItemStack(BOMB_BAG.get()), 2, 8, 0.03f
            );
            addTrade(trades, 2,
                    new ItemStack(RED_EMERALD.get(), 2), // Cost
                    new ItemStack(QUIVER_SMALL.get()), 2, 8, 0.03f
            );

            // 3
            addTrade(trades, 3,
                    new ItemStack(PURPLE_EMERALD.get(), 4), // Cost
                    new ItemStack(QUIVER_MEDIUM.get()), 2, 8, 0.03f
            );
            addTrade(trades, 3,
                    new ItemStack(PURPLE_EMERALD.get(), 3), // Cost
                    new ItemStack(BOMB_BAG_MEDIUM.get()), 2, 8, 0.03f
            );
            addTrade(trades, 3,
                    new ItemStack(SILVER_EMERALD.get()), // Cost
                    new ItemStack(WALLET_GIANT.get()), 2, 10, 0.03f
            );

            // 4
            addTrade(trades, 4,
                    new ItemStack(GOLD_EMERALD.get()), // Cost
                    new ItemStack(WALLET_TYCOON.get()), 2, 10, 0.03f
            );
            addTrade(trades, 4,
                    new ItemStack(GOLD_EMERALD.get()), // Cost
                    new ItemStack(BOMB_BAG_LARGE.get()), 2, 12, 0.03f
            );
            addTrade(trades, 4,
                    new ItemStack(GOLD_EMERALD.get()), // Cost
                    new ItemStack(QUIVER_BIG.get()), 2, 16, 0.03f
            );

            // 5
            addTrade(trades, 5,
                    new ItemStack(GOLD_EMERALD.get(), 4), // Cost
                    new ItemStack(KOROK_SEED_GIFT.get()), 1, 40, 0.03f
            );

        }
    }

    public static void addWanderingTrades(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades(); // Unused, but left here for future use
        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();

        addWanderingTrade(rareTrades, 12, new ItemStack(GERUDO_MASK.get()), 2, 12, 0.15f);
        addWanderingTrade(rareTrades, 13, new ItemStack(SKULL_MASK.get()), 2, 12, 0.15f);
        addWanderingTrade(rareTrades, 16, new ItemStack(SPOOKY_MASK.get()), 2, 12, 0.15f);
    }

    private static void addTrade(Int2ObjectMap<List<VillagerTrades.ItemListing>> trades, int lvl, ItemStack cost, ItemStack product,
                                 int maxUse, int xp, float priceMult) {
        addTrade(trades, lvl, cost, ItemStack.EMPTY, product, maxUse, xp, priceMult);
    }

    private static void addTrade(Int2ObjectMap<List<VillagerTrades.ItemListing>> trades, int lvl, ItemStack cost, ItemStack cost2, ItemStack product,
                                 int maxUse, int xp, float priceMult) {
        trades.get(lvl).add((trader, rand) -> new MerchantOffer(cost, cost2, product, maxUse, xp, priceMult));
    }

    private static void addWanderingTrade(List<VillagerTrades.ItemListing> trades, int cost, ItemStack product,
                                          int maxUse, int xp, float priceMult) {
        trades.add((trader, rand) -> new MerchantOffer(new ItemStack(EMERALD, cost), product, maxUse, xp, priceMult));
    }
}
