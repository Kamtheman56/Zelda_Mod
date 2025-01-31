package com.kamth.zeldamod.entity.ai.mask_goals;


import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.EnumSet;

public class FairyMask extends Goal
    {
        protected final Allay mob;
        private final double speedModifier;
        protected Player player;
        private int calmDown;



        public FairyMask(Entity mob, double speed)
        {
            this.mob = (Allay) mob;
            this.speedModifier = speed;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }


        public boolean canUse()
        {
            if (this.calmDown > 0)
            {
                --this.calmDown;
                return false;
            }
            else
            {
                this.player = this.mob.level().getNearestPlayer(TargetingConditions.DEFAULT,2D,2D, 1);
                if (this.player == null)
                {
                    return false;
                }
                else
                {
                    return shouldFollow(player);
                }
            }
        }

        private <L> boolean shouldFollow(Player player)
        {
            ItemStack stack0 = player.getItemBySlot(EquipmentSlot.HEAD);
            boolean l =  (this.mob.distanceTo(this.player) < 12D);
            if ((!stack0.isEmpty() && l))
                return stack0.getItem() == ZeldaItems.FAIRY_MASK.get();
            return false;
        }



        public void tick()
        {

            this.mob.getLookControl().setLookAt(this.player, (float) (this.mob.getMaxHeadYRot() + 20), (float) this.mob.getMaxHeadXRot());
                this.mob.getNavigation().moveTo(this.player, this.speedModifier);


        }

    }