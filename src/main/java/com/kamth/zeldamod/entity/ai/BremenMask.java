package com.kamth.zeldamod.entity.ai;


import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.EnumSet;

public class BremenMask  extends Goal
    {
        protected final Animal mob;
        private final double speedModifier;
        protected Player player;
        private int calmDown;



        public BremenMask(Entity mob, double speed)
        {
            this.mob = (Animal) mob;
            this.speedModifier = speed;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
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
                this.player = this.mob.level().getNearestPlayer(TargetingConditions.DEFAULT,2D,2D, 1D);
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
        private boolean Follow(Player player)
        {
            ItemStack stack0 = player.getItemBySlot(EquipmentSlot.HEAD);
            boolean l =  (this.mob.distanceTo(this.player) < 12.5D);
            if ((!stack0.isEmpty() && l))
                return stack0.getItem() == ZeldaItems.BREMEN_MASK.get();
            return false;
        }



        public void tick()
        {
            this.mob.getLookControl().setLookAt(this.player, (float) (this.mob.getMaxHeadYRot() + 20), (float) this.mob.getMaxHeadXRot());
                this.mob.getNavigation().moveTo(this.player, this.speedModifier);}
    }