package com.kamth.zeldamod.entity.ai;


import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.EnumSet;

public class CaptainMask extends Goal
    {
        protected final Skeleton mob;

        protected Player player;




        public CaptainMask(Entity mob)
        {
            this.mob = (Skeleton) mob;

            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }


        public boolean canUse()
        {

                this.player = this.mob.level().getNearestPlayer(TargetingConditions.forCombat(),2D,2D, 1);
                if (this.player == null)
                {
                    return false;
                }
                else
                {
                    return Follow(player);
                }
            }


        private <L> boolean Follow(Player player)
        {
            ItemStack stack0 = player.getItemBySlot(EquipmentSlot.HEAD);
            boolean l =  (this.mob.distanceTo(this.player) < 10.25);
            if ((!stack0.isEmpty() && l))
                return stack0.getItem() == ZeldaItems.CAPTAIN_MASK.get();
            return false;
        }

        public void tick(){
            this.mob.getLookControl().setLookAt(this.player, (float) (this.mob.getMaxHeadYRot() + 20), (float) this.mob.getMaxHeadXRot());
       this.mob.setAggressive(false);
                this.mob.setTarget(null);
this.mob.isShaking();
        }
        }

