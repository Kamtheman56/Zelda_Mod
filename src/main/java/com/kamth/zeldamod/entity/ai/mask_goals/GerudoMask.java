package com.kamth.zeldamod.entity.ai.mask_goals;


import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.EnumSet;

public class GerudoMask extends Goal
    {
        protected final Villager mob;
        private final double speedModifier;
        protected Player player;
        private int calmDown;



        public GerudoMask(Entity mob, double speed)
        {
            this.mob = (Villager) mob;
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
                this.player = this.mob.level().getNearestPlayer(TargetingConditions.DEFAULT,3D,2D, 3D);
                if (this.player == null)
                {
                    return false;
                }
                else
                {
                    return Follow(player);
                }
            }
        }

        private <L> boolean Follow(Player player)
        {
            ItemStack stack0 = player.getItemBySlot(EquipmentSlot.HEAD);
            boolean l =  (this.mob.distanceTo(this.player) < 8.25D);
            if ((!stack0.isEmpty() && l))
                return stack0.getItem() == ZeldaItems.BREMEN_MASK.get();
            return false;
        }



        public void tick()
        {
            this.mob.getLookControl().setLookAt(this.player, (float) (this.mob.getMaxHeadYRot() + 20), (float) this.mob.getMaxHeadXRot());
                this.mob.getNavigation().moveTo(this.player, this.speedModifier);


        }

    }