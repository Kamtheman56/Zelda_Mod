package com.kamth.zeldamod.item.armors;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ZoraTunic extends ArmorItem {
    public ZoraTunic(ArmorMaterial p_40386_, EquipmentSlot p_40387_, Properties p_40388_) {
        super(p_40386_, p_40387_, p_40388_);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {


        player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 10, 0, true, false));
   //Randomly damage the tunic if underwater
     //   if(player.isUnderWater()) {
          //  if(new Random().nextFloat() > 0.6f) {
           //     player.getInventory().hurtArmor(DamageSource.MAGIC, 1f, new int[]{0, 1, 2});


   // }
//}
    }

}