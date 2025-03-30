package com.kamth.zeldamod.util.interfaces.entity;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

// Code contributed by Deadlydiamond98 (c) 2024 under the MIT License.
// Added here with explicit permission by the original owner.

public interface ISurfaceSticker extends IRaycast {

    enum FloorAttachState {
        FLOOR("Floor", Direction.DOWN),
        CEILING("Ceiling", Direction.UP),
        NORTH("North", Direction.NORTH),
        EAST("East", Direction.EAST),
        SOUTH("South", Direction.SOUTH),
        WEST("West", Direction.WEST),
        DETACHED("Detached", Direction.DOWN),
        THROWN("Thrown", Direction.DOWN);

        private final String name;
        private final Direction direction;

        FloorAttachState(String name, @Nullable Direction direction) {
            this.name = name;
            this.direction = direction;
        }

        public boolean canApplyGravity() {
            return is(DETACHED) || is(THROWN);
        }

        public boolean ceiling() {
            return is(CEILING);
        }

        public boolean isWall() {
            return is(NORTH) || is(SOUTH) || is(EAST) || is(WEST);
        }

        public FloorAttachState getByName(String name) {
            for (FloorAttachState state : values()) {
                if (state.name.equals(name)) {
                    return state;
                }
            }
            return null;
        }

        public String getName() {
            return this.name;
        }

        public Direction getDirection() {
            return this.direction;
        }

        private boolean is(FloorAttachState state) {
            return this == state;
        }

        @Nullable
        public static FloorAttachState getFromDirection(Direction direction) {
            for (FloorAttachState state : values()) {
                if (state.direction == direction) {
                    return state;
                }
            }
            return null;
        }

    }

    default FloorAttachState changeSide(Direction direction) {
        return switch (direction) {
            case DOWN -> FloorAttachState.FLOOR;
            case UP -> FloorAttachState.CEILING;
            case NORTH -> FloorAttachState.NORTH;
            case SOUTH -> FloorAttachState.SOUTH;
            case EAST -> FloorAttachState.EAST;
            case WEST -> FloorAttachState.WEST;
        };
    }

    default Vec3 updateVelocityDirection(FloorAttachState floor, float yaw, float pitch, double speed) {
        double radYaw = Math.toRadians(yaw);
        double radPitch = Math.toRadians(pitch);

        double sinYaw = Math.sin(radYaw);
        double cosYaw = Math.cos(radYaw);
        double sinPitch = Math.sin(radPitch);
        double cosPitch = Math.cos(radPitch);

        Vec3 velocity;

        switch (floor) {
            case CEILING -> velocity = new Vec3(
                    -sinYaw * cosPitch,
                    sinPitch,
                    cosYaw * cosPitch
            );
            case NORTH, SOUTH -> velocity = new Vec3(
                    -sinYaw,
                    -sinPitch,
                    0
            );
            case EAST, WEST -> velocity = new Vec3(
                    0,
                    -sinPitch,
                    cosYaw
            );
            default -> velocity = new Vec3(
                    -sinYaw * cosPitch,
                    -sinPitch,
                    cosYaw * cosPitch
            );
        }
        return velocity.multiply(speed, speed, speed);
    }

    default int rotateWallToWall(FloorAttachState oldFace, FloorAttachState newFace) {
        FloorAttachState[] directions = {
                FloorAttachState.NORTH,
                FloorAttachState.EAST,
                FloorAttachState.SOUTH,
                FloorAttachState.WEST
        };

        int oldIndex = -1;
        int newIndex = -1;

        for (int i = 0; i < directions.length; i++) {
            if (directions[i] == oldFace) oldIndex = i;
            if (directions[i] == newFace) newIndex = i;
        }

        if (oldIndex == -1 || newIndex == -1) return 0;

        int diff = (newIndex - oldIndex + 4) % 4;

        return switch (diff) {
            case 1 -> -90;
            case 3 -> 90;
            default -> 0;
        };
    }
}
