package com.kamth.zeldamod.entity.mobs.variants;

import java.util.Arrays;
import java.util.Comparator;

public enum BokoblinVariants {
    RED(0),
    BLUE(1),
    BLACK(2);

    private static final BokoblinVariants[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(BokoblinVariants::getId)).toArray(BokoblinVariants[]::new);
    private final int id;

    BokoblinVariants(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static BokoblinVariants byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}