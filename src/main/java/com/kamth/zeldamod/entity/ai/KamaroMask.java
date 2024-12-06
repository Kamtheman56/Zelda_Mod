package com.kamth.zeldamod.entity.ai;


import com.kamth.zeldamod.item.ModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.EnumSet;

public class KamaroMask extends Goal
    {
        protected final Piglin mob;

        protected Player player;
        private int calmDown;



        public KamaroMask(Entity mob)
        {
            this.mob = (Piglin) mob;
            this.setFlags(EnumSet.of(Flag.TARGET, Flag.JUMP));
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
            if ((!stack0.isEmpty()))
                return stack0.getItem() == ModItems.KAMARO_MASK.get();
            return false;
        }



        public void tick()
        {

            this.mob.getLookControl().setLookAt(this.player, (float) (this.mob.getMaxHeadYRot() + 20), (float) this.mob.getMaxHeadXRot());
            this.mob.setDancing(true);

        }

    }