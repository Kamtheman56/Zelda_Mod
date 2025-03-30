
package com.kamth.zeldamod.util.interfaces.entity;

// Code contributed by Deadlydiamond98 (c) 2024 under the MIT License.
// Added here with explicit permission by the original owner.

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;


public interface IRaycast {
    Level getRaycastWorld();
    Vec3 getRaycastPos();
    float getRaycastHeight();

    default HitResult doRaycast(Vec3 start, float yaw, float pitch, double length) {
        Vec3 direction = getDirectionVector(yaw, pitch);
        Vec3 end = start.add(direction.multiply(length, length, length));


        return getRaycastWorld().clip(new ClipContext(
                start,
                end,
                ClipContext.Block.COLLIDER,
                ClipContext.Fluid.NONE,
                (Entity) this
        ));
    }

    default HitResult doRaycast(Vec3 start, Vec3 velocity, double length) {
        if (velocity.lengthSqr() < 1.0e-8) {
            return getRaycastWorld().clip(new ClipContext(
                    start,
                    start,
                    ClipContext.Block.COLLIDER,
                    ClipContext.Fluid.NONE,
                    (Entity) this
            ));
        }


        Vec3 direction = velocity.normalize();
        Vec3 end = start.add(direction.multiply(length, length, length));

        return getRaycastWorld().clip(new ClipContext(
                start,
                end,
                ClipContext.Block.COLLIDER,
                ClipContext.Fluid.NONE,
                (Entity) this
        ));
    }

    default Vec3 getDirectionVector(float yaw, float pitch) {
        double yawRad = Math.toRadians(yaw);
        double pitchRad = Math.toRadians(pitch);

        double x = -Math.sin(yawRad) * Math.cos(pitchRad);
        double y = -Math.sin(pitchRad);
        double z = Math.cos(yawRad) * Math.cos(pitchRad);

        return new Vec3(x, y, z);
    }

    default Vec3 getPerpendicularOffset(float yaw, double halfWidth) {
        double yawRad = Math.toRadians(yaw);

        double x = Math.cos(yawRad) * halfWidth;
        double z = Math.sin(yawRad) * halfWidth;

        return new Vec3(x, 0, z);
    }

    default Vec3 getCenterPos() {
        return getRaycastPos().add(0, getRaycastHeight() / 2.0, 0);
    }

    private static Vec3 up() {
        return new Vec3(0, 1, 0);
    }
}
