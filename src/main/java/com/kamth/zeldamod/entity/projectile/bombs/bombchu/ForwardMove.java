

package com.kamth.zeldamod.entity.projectile.bombs.bombchu;

// Code contributed by Deadlydiamond98 (c) 2024 under the MIT License.
// Added here with explicit permission by the original owner.

public enum ForwardMove {
    LEFT(-1),
    NORMAL(0),
    RIGHT(1);

    private final int direction;

    ForwardMove(int direction) {
        this.direction = direction;
    }

    public float getRotation(float frontDist) {
        if (frontDist == 0) {
            return 10 * this.direction;
        }

        return (6 * (1 / frontDist)) * this.direction;
    }
}
