package com.kamth.zeldamod;


import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ZeldaMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();



    public static final ForgeConfigSpec.BooleanValue BOMB_GRIEFING = BUILDER.comment("Do bombs  destroy blocks").define("bomb_griefing", false);
    public static final ForgeConfigSpec.BooleanValue BOMB_ARROW_GRIEFING = BUILDER.comment("Do bomb arrows destroy blocks").define("bomb_arrow_griefing", false);



    public static final ForgeConfigSpec.BooleanValue WIND_GRIEFING = BUILDER.comment("Do gusts of wind destroy plant blocks and extinguish fires").define("wind_griefing", true);



    public static final ForgeConfigSpec.BooleanValue SWORD_BEAM_GRIEFING = BUILDER.comment("Do sword beams destroy blocks by default").define("sword_beam_griefing", true);


    // ARROW CONFIG
    public static final ForgeConfigSpec.BooleanValue FIRE_ARROW_MELTING = BUILDER.comment("Do Fire Arrows melt frozen blocks?").define("fire_arrow_melting", true);
    public static final ForgeConfigSpec.BooleanValue ICE_ARROW_FREEZING = BUILDER.comment("Do Ice Arrows freeze water?").define("ice_arrow_freezing", true);
    public static final ForgeConfigSpec.BooleanValue SHOCK_ARROW_LIGHTNING = BUILDER.comment("Do Shock Arrows summon lightning when it's raining?").define("shock_arrow_lightning", true);



    // SWORD BEAMS
    public static final ForgeConfigSpec.BooleanValue SWORD_BEAMS = BUILDER.comment("Can the player shoot sword beams by Left Clicking when available?").define("sword_beams", true);
    public static final ForgeConfigSpec.BooleanValue ALT_SWORD_BEAMS = BUILDER.comment("Revert Sword Beams to being fired with shift + Right Click").define("alternative_sword_beams", false);
    public static final ForgeConfigSpec.BooleanValue REFORGED_SWORD_BEAMS = BUILDER.comment("Can the Reforged Master Sword fire Sword Beams?").define("reforged_sword_beams", true);


    // Do Ocarina songs have effects?
    public static ForgeConfigSpec.BooleanValue SONG_OF_STORMS = BUILDER.comment("Does the Song of Storms affect the weather?").define("song_of_storms", true);
    public static final ForgeConfigSpec.BooleanValue SUN_SONG = BUILDER.comment("Does the Sun's Song affect the time of day?").define("suns_song", true);


    static final ForgeConfigSpec SPEC = BUILDER.build();

    private static boolean isLoaded = false;



    private static boolean song_of_storms;

    // GRIEFING RELATED
    private static boolean bomb_griefing;
    private static boolean bomb_arrow_griefing;


    // ARROW RELATED
    private static boolean fire_arrow_melting;
    private static boolean ice_arrow_freezing;
    private static boolean shock_arrow_lightning;


    // SWORD BEAMS
    private static boolean sword_beams;
    private static boolean alternative_sword_beams;
    private static boolean reforged_sword_beams;

    @SubscribeEvent
    static void load(ModConfigEvent event) {
        if (event.getConfig().getSpec() == SPEC) {

            // GRIEFING
            bomb_griefing = BOMB_GRIEFING.get();
            bomb_arrow_griefing = BOMB_ARROW_GRIEFING.get();

            // ARROWS
            fire_arrow_melting = FIRE_ARROW_MELTING.get();
            ice_arrow_freezing = ICE_ARROW_FREEZING.get();
            shock_arrow_lightning = SHOCK_ARROW_LIGHTNING.get();



            // OCARINA RELATED
            song_of_storms = SONG_OF_STORMS.get();


            // SWORD BEAMS
            sword_beams = SWORD_BEAMS.get();
            alternative_sword_beams = ALT_SWORD_BEAMS.get();
            reforged_sword_beams = REFORGED_SWORD_BEAMS.get();
            isLoaded = true;
        }
    }


    //OCARINA EFFECTS
    public static boolean SongofStormsEffect() {
        return isLoaded ? song_of_storms : SONG_OF_STORMS.get();
    }

   // GRIEFING
    public static boolean bomb_griefing()   {
        return isLoaded ? bomb_griefing : BOMB_GRIEFING.get();
    }

    public static boolean bomb_arrow_griefing()   {
        return isLoaded ? bomb_arrow_griefing : BOMB_ARROW_GRIEFING.get();
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



}
