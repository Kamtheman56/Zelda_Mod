package com.kamth.zeldamod;


import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ZeldaMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();


// BOMB GRIEFING
    public static final ForgeConfigSpec.BooleanValue BOMB_GRIEFING = BUILDER.comment("Do bombs destroy blocks?").define("bomb_griefing", false);
    public static final ForgeConfigSpec.BooleanValue BOMB_ARROW_GRIEFING = BUILDER.comment("Do bomb arrows destroy blocks?").define("bomb_arrow_griefing", false);
    public static final ForgeConfigSpec.BooleanValue BOMB_FLOWER_GRIEFING = BUILDER.comment("Do bomb flowers destroy blocks?").define("bomb_flower_griefing", true);


// PROJECTILE GRIEFING
    public static final ForgeConfigSpec.BooleanValue GUST_GRIEFING = BUILDER.comment("Do gusts of wind destroy plant blocks and extinguish fires").define("wind_griefing", true);
    public static final ForgeConfigSpec.BooleanValue SWORD_BEAM_GRIEFING = BUILDER.comment("Do sword beams destroy blocks by default").define("sword_beam_griefing", true);


    // ARROW CONFIG
    public static final ForgeConfigSpec.BooleanValue FIRE_ARROW_MELTING = BUILDER.comment("Do Fire Arrows melt frozen blocks?").define("fire_arrow_melting", true);
    public static final ForgeConfigSpec.BooleanValue ICE_ARROW_FREEZING = BUILDER.comment("Do Ice Arrows freeze water?").define("ice_arrow_freezing", true);
    public static final ForgeConfigSpec.BooleanValue SHOCK_ARROW_LIGHTNING = BUILDER.comment("Do Shock Arrows summon lightning when it's raining?").define("shock_arrow_lightning", true);

 // HOOKSHOT CLAWS
    public static final ForgeConfigSpec.BooleanValue HOOKSHOT_GRABBING = BUILDER.comment("Can the Hookshot grapple onto any block type?").define("hookshot_grabbing", false);
    public static final ForgeConfigSpec.BooleanValue CLAWSHOT_GRABBING = BUILDER.comment("Can the Clawshot grapple onto any block type?").define("clawshot_grabbing", false);
    public static ForgeConfigSpec.IntValue HOOKSHOT_RANGE = BUILDER.comment("The total range of the hookshot. Default is 15.").defineInRange("hookshot_range", 15,0,60);
    public static ForgeConfigSpec.IntValue CLAWSHOT_RANGE = BUILDER.comment("The total range of the clawshot. Default is 25.").defineInRange("clawshot_range", 25,0,60);


    // SWORD BEAMS
    public static final ForgeConfigSpec.BooleanValue SWORD_BEAMS = BUILDER.comment("Can the player shoot sword beams by Left Clicking when available?").define("sword_beams", true);
    public static final ForgeConfigSpec.BooleanValue ALT_SWORD_BEAMS = BUILDER.comment("Reverts Sword Beams to being fired with shift + Right Click").define("alternative_sword_beams", false);
    public static final ForgeConfigSpec.BooleanValue REFORGED_SWORD_BEAMS = BUILDER.comment("Can the Reforged Master Sword fire Sword Beams?").define("reforged_sword_beams", true);
    public static final ForgeConfigSpec.BooleanValue REFORGED_SWORD_LIGHTNING = BUILDER.comment("Can the Reforged Master Sword Beams summon lightning?").define("reforged_sword_lightning", true);


    // Do Ocarina songs have effects?
    public static ForgeConfigSpec.BooleanValue SONG_OF_STORMS = BUILDER.comment("Does the Song of Storms affect the weather?").define("song_of_storms", true);
    public static final ForgeConfigSpec.BooleanValue SUN_SONG = BUILDER.comment("Does the Sun's Song affect the time of day?").define("suns_song", true);


    // ENTITY CONFIG

    public static final ForgeConfigSpec.BooleanValue DEKU_SPAWNING = BUILDER.comment("Can Deku Scrubs and their variants spawn?").define("deku_spawning", true);
    public static final ForgeConfigSpec.BooleanValue KEESE_SPAWNING = BUILDER.comment("Can Keese and their variants spawn?").define("keese_spawning", true);
    public static final ForgeConfigSpec.BooleanValue CHUCHU_SPAWNING = BUILDER.comment("Can Chuchus and their variants spawn?").define("chuchu_spawning", true);
    public static final ForgeConfigSpec.BooleanValue SKULLTULA_SPAWNING = BUILDER.comment("Can Skulltula spawn?").define("skulltula_spawning", true);


    public static final ForgeConfigSpec.BooleanValue KOROK_SPAWNING = BUILDER.comment("Can Koroks and their variants spawn?").define("korok_spawning", true);


    public static ForgeConfigSpec.IntValue HEART_CONTAINER_HEARTS = BUILDER.comment("The total amount of hearts the player may obtain with Heart Containers. By default this is one additional row. (40)").defineInRange("heart_container_maximum", 40,0,80);
    public static ForgeConfigSpec.IntValue PRIMO_CONTAINER_HEARTS = BUILDER.comment("The total amount of hearts the player may obtain with Primo Heart Containers. By default this is two additional rows. (60)").defineInRange("primo_container_maximum", 60,0,200);


    static final ForgeConfigSpec SPEC = BUILDER.build();

    private static boolean isLoaded = false;


// OCARINA RELATED
    private static boolean song_of_storms;
    private static boolean sun_song;

    // GRIEFING RELATED
    private static boolean bomb_griefing;
    private static boolean bomb_arrow_griefing;
    private static boolean bomb_flower_griefing;
    private static boolean gust_griefing;

    // ARROW RELATED
    private static boolean fire_arrow_melting;
    private static boolean ice_arrow_freezing;
    private static boolean shock_arrow_lightning;

    //HOOKSHOTS
    private static boolean hookshot_grabbing;
    private static boolean clawshot_grabbing;
    public static ForgeConfigSpec.IntValue hookshot_range;
    public static ForgeConfigSpec.IntValue clawshot_range;

    // SWORD BEAMS
    private static boolean sword_beams;
    private static boolean alternative_sword_beams;
    private static boolean reforged_sword_beams;
    private static boolean reforged_sword_lightning;

    // MISC
    public static ForgeConfigSpec.IntValue heart_container_maximum;
    public static ForgeConfigSpec.IntValue primo_container_maximum;

    //MOB CONFIG
    private static boolean deku_spawning;
    private static boolean keese_spawning;
    private static boolean chuchu_spawning;
    private static boolean bokoblin_spawning;
    private static boolean moblin_spawning;
    private static boolean yiga_spawning;
    private static boolean korok_spawning;
    private static boolean skulltula_spawning;


    @SubscribeEvent
    static void load(ModConfigEvent event) {
        if (event.getConfig().getSpec() == SPEC) {

            // GRIEFING
            bomb_griefing = BOMB_GRIEFING.get();
            bomb_arrow_griefing = BOMB_ARROW_GRIEFING.get();
            bomb_flower_griefing = BOMB_FLOWER_GRIEFING.get();
            gust_griefing = GUST_GRIEFING.get();

            // ARROWS
            fire_arrow_melting = FIRE_ARROW_MELTING.get();
            ice_arrow_freezing = ICE_ARROW_FREEZING.get();
            shock_arrow_lightning = SHOCK_ARROW_LIGHTNING.get();

            //HOOKSHOT
            hookshot_grabbing = HOOKSHOT_GRABBING.get();
            clawshot_grabbing = CLAWSHOT_GRABBING.get();
            hookshot_range = HOOKSHOT_RANGE;
            clawshot_range = CLAWSHOT_RANGE;

            // OCARINA RELATED
            song_of_storms = SONG_OF_STORMS.get();
            sun_song = SUN_SONG.get();

            // SWORD BEAMS
            sword_beams = SWORD_BEAMS.get();
            alternative_sword_beams = ALT_SWORD_BEAMS.get();
            reforged_sword_beams = REFORGED_SWORD_BEAMS.get();
            reforged_sword_lightning= REFORGED_SWORD_LIGHTNING.get();

            // MOB CONFIG
            deku_spawning = DEKU_SPAWNING.get();
            keese_spawning = KEESE_SPAWNING.get();
            chuchu_spawning = CHUCHU_SPAWNING.get();


            korok_spawning = KOROK_SPAWNING.get();
            skulltula_spawning = SKULLTULA_SPAWNING.get();


            // Additional Config options
            heart_container_maximum = HEART_CONTAINER_HEARTS;
            primo_container_maximum = PRIMO_CONTAINER_HEARTS;

            isLoaded = true;
        }
    }


    //OCARINA EFFECTS
    public static boolean song_of_storms_effect() {
        return isLoaded ? song_of_storms : SONG_OF_STORMS.get();
    }
    public static boolean sun_song_effect() {
        return isLoaded ? sun_song : SUN_SONG.get();
    }

   // GRIEFING
    public static boolean bomb_griefing()   {
        return isLoaded ? bomb_griefing : BOMB_GRIEFING.get();
    }

    public static boolean bomb_arrow_griefing()   {
        return isLoaded ? bomb_arrow_griefing : BOMB_ARROW_GRIEFING.get();
    }

    public static boolean bomb_flower_griefing()   {
        return isLoaded ? bomb_flower_griefing : BOMB_FLOWER_GRIEFING.get();
    }

    public static boolean gust_griefing()   {
        return isLoaded ? gust_griefing : GUST_GRIEFING.get();
    }

    // ARROW CONFIG

    public static boolean fire_arrow_melting()   {
        return isLoaded ? fire_arrow_melting : FIRE_ARROW_MELTING.get();
    }
    public static boolean ice_arrow_freezing()   {
        return isLoaded ? ice_arrow_freezing : ICE_ARROW_FREEZING.get();
    }
    public static boolean shock_arrow_lightning()   {
        return isLoaded ? shock_arrow_lightning : SHOCK_ARROW_LIGHTNING.get();
    }

    //HOOKSHOT CONFIG
    public static boolean hookshot_grabbing()   {
        return isLoaded ? hookshot_grabbing : HOOKSHOT_GRABBING.get();
    }
    public static boolean clawshot_grabbing()   {
        return isLoaded ? clawshot_grabbing : CLAWSHOT_GRABBING.get();
    }



    //SWORD BEAMS
    public static boolean sword_beams() {
        return isLoaded ? sword_beams : SWORD_BEAMS.get();
    }

    public static boolean alternative_sword_beams() {
        return isLoaded ? alternative_sword_beams : ALT_SWORD_BEAMS.get();
    }

    public static boolean reforged_sword_beams() {
        return isLoaded ? reforged_sword_beams : REFORGED_SWORD_BEAMS.get();
    }
    public static boolean reforged_sword_lightning() {
        return isLoaded ? reforged_sword_lightning : REFORGED_SWORD_LIGHTNING.get();
    }

    // MOB CONFIG
    public static boolean deku_spawning() {
        return isLoaded ? deku_spawning : DEKU_SPAWNING.get();
    }
    public static boolean keese_spawning() {
        return isLoaded ? keese_spawning : KEESE_SPAWNING.get();
    }
    public static boolean chuchu_spawning() {
        return isLoaded ? chuchu_spawning : CHUCHU_SPAWNING.get();
    }


    public static boolean korok_spawning() {
        return isLoaded ? korok_spawning : KOROK_SPAWNING.get();
    }
    public static boolean skulltula_spawning() {
        return isLoaded ? skulltula_spawning : SKULLTULA_SPAWNING.get();
    }



}
