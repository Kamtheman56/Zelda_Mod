package com.kamth.zeldamod;


import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ZeldaMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec.BooleanValue explosivegriefing =  BUILDER.comment("Whether bombs & bomb arrows will cause block destruction").define("explosivegriefing", false);

    public static final ForgeConfigSpec.BooleanValue BOMB_GRIEFING = BUILDER.comment("Do bombs and bomb arrows destroy blocks").define("bomb_griefing", false);
    public static final ForgeConfigSpec.BooleanValue WIND_GRIEFING = BUILDER.comment("Do gusts of wind destroy plant blocks and extinguish fires").define("wind_griefing", true);
    public static final ForgeConfigSpec.BooleanValue SWORD_BEAM_GRIEFING = BUILDER.comment("Do sword beams destroy blocks by default").define("sword_beam_griefing", true);


    // Do Ocarina songs have effects?
    public static ForgeConfigSpec.BooleanValue SONG_OF_STORMS = BUILDER.comment("Does the Song of Storms affect the weather?").define("song_of_storms", true);
    public static final ForgeConfigSpec.BooleanValue SUN_SONG = BUILDER.comment("Does the Sun's Song affect the time of day?").define("suns_song", true);


    static final ForgeConfigSpec SPEC = BUILDER.build();

    private static boolean isLoaded = false;


    private static boolean song_of_storms;
    private static boolean bomb_griefing;




    @SubscribeEvent
    static void load(ModConfigEvent event) {
        if (event.getConfig().getSpec() == SPEC) {
            bomb_griefing = BOMB_GRIEFING.get();
            song_of_storms = SONG_OF_STORMS.get();
            isLoaded = true;
        }
    }

    public static boolean SongofStormsEffect() {
        return isLoaded ? song_of_storms : SONG_OF_STORMS.get();
    }

    public static boolean explosivegriefing() { return explosivegriefing.get(); }
    public static boolean bomb_griefing()   {
        return isLoaded ? bomb_griefing : BOMB_GRIEFING.get();
    }
}
