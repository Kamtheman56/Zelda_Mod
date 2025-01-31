package com.kamth.zeldamod.entity.ai.mask_goals;


import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.EnumSet;

public class GibdoMask extends Goal
    {
        protected final Husk mob;

        protected Player player;




        public GibdoMask(Entity mob)
        {
            this.mob = (Husk) mob;

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
            boolean l =  (this.mob.distanceTo(this.player) < 5.25D);
            if ((!stack0.isEmpty() && l))
                return stack0.getItem() == ZeldaItems.GIBDO_MASK.get();
            return false;
        }

        public void tick(){
            this.mob.getLookControl().setLookAt(this.player, (float) (this.mob.getMaxHeadYRot() + 20), (float) this.mob.getMaxHeadXRot());
       this.mob.setAggressive(false);
                this.mob.setTarget(null);

        }
        }

