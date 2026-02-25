package com.kamth.zeldamod.client.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ZeldaModConfig {
    public static final ZeldaModConfig INSTANCE;
    public static final ForgeConfigSpec SPEC;

    static {
        final Pair<ZeldaModConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ZeldaModConfig::new);
        INSTANCE = specPair.getLeft();
        SPEC = specPair.getRight();
    }
    // General
    private final ForgeConfigSpec.DoubleValue playerMaxHealth;
    private final ForgeConfigSpec.BooleanValue bombGriefing;
    private final ForgeConfigSpec.BooleanValue explosivegriefing;
    private final ForgeConfigSpec.BooleanValue canShootSwordBeams;
    private final ForgeConfigSpec.BooleanValue alternativeSwordBeams;
    private final ForgeConfigSpec.BooleanValue reforgedSwordBeams;


    private ZeldaModConfig(ForgeConfigSpec.Builder builder) {
        builder.push("General");

        playerMaxHealth = builder
                .comment("Players health they will start with. One Minecraft heart is 2, and a full bar is 20. Its Recommended you only use Even numbers.")
                .defineInRange("playerMaxHealth", 20D, 1D, 40D);



        bombGriefing = builder
                .comment("Do bombs destroy terrain?")
                .define("bomb_griefing", false);


        explosivegriefing = builder
                .comment("Whether bombs & bomb arrows will cause block destruction")
                .define("explosivegriefing", false);

        alternativeSwordBeams = builder
                .comment("Revert Sword Beams to being fired with shift + Right Click")
                .define("alternate_sword_beams", false);


        reforgedSwordBeams = builder
                .comment("Can the Reforged Master Sword fire Sword Beams?")
                .define("reforged_sword_beams", true);

        canShootSwordBeams = builder
                .comment("Whether the player can shoot sword beams with compatible swords at full health with Left Click")
                .define("sword_beams", true);



        builder.pop();





    }

    public static ZeldaModConfig getInstance() {
        return INSTANCE;
    }

    // Query Operations



    public void save() {
        SPEC.save();
    }
}