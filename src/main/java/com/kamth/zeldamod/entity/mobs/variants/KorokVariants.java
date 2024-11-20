package com.kamth.zeldamod.entity.mobs.variants;

import java.util.Arrays;
import java.util.Comparator;

public enum KorokVariants {
    DEFAULT(0),
    BIRCH(1),
    DARK(2),
    CHERRY(3);

    private static final KorokVariants[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(KorokVariants::getId)).toArray(KorokVariants[]::new);
    private final int id;

    KorokVariants(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static KorokVariants byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}