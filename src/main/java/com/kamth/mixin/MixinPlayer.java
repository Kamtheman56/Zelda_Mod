package com.kamth.mixin;

import com.kamth.zeldamod.api.LegendaryArmoryPlayerData;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin (Player.class)
public abstract class MixinPlayer implements LegendaryArmoryPlayerData {

        @Unique
        private boolean arrowRemoved;

        @Override
        public boolean hasArrowBeenRemoved() {
            return this.arrowRemoved;
        }

        @Override
        public void setArrowRemoved(boolean removed) {
            this.arrowRemoved = removed;
        }
    }


