package com.kamth.zeldamod.item.items.movement;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;

public class AscendItem extends Item {
    public AscendItem(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
    //    pLevel.clip(new ClipContext(new Vec3(0,2,0),new Vec3(0,10,0), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, pPlayer)).getType().equals(HitResult.Type.BLOCK);



 if (pLevel.clip(new ClipContext(new Vec3(0,2,0),new Vec3(0,10,0), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, pPlayer)).getType().equals(HitResult.Type.BLOCK) ){
     if (pLevel.getBlockState(pPlayer.getOnPos().above(12)).is(Blocks.AIR)){
         pPlayer.startUsingItem(pHand);
     }}
else {
 }
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        return super.use(pLevel, pPlayer, pHand);
    }
    @Override
    public void onUseTick(Level pLevel, LivingEntity livingEntity, ItemStack pStack, int pRemainingUseDuration) {
        Player player = (Player) livingEntity;
    if (pLevel.isClientSide){
        double particleX = player.getX();
        double particleY = player.getY() +2;
        double particleZ = player.getZ();
        player.level().addParticle(ParticleTypes.HAPPY_VILLAGER, particleX, particleY, particleZ, 0, 0, 0);
    }

    }

    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity livingEntity, int pTimeLeft) {
        Player pPlayer = (Player) livingEntity;

        Vec3 lookAngle = pPlayer.getLookAngle();
        int radius = 12;
        pPlayer.teleportTo(pPlayer.getX(), pPlayer.getY()+10, pPlayer.getZ());


    }


    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }


    @Override
    public int getUseDuration(ItemStack itemStack)
    {
        return 72000;
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.literal("Ascend through the floor").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.ITALIC));
        }
        super.appendHoverText(stack, level, components, flag);
    }
   }






