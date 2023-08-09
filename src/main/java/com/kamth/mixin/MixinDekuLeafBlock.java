package com.kamth.mixin;

import com.kamth.zeldamod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BigDripleafBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BigDripleafBlock.class)
public abstract class MixinDekuLeafBlock extends Block {


    public MixinDekuLeafBlock(Properties pProperties) {
        super(pProperties);
    }

    @Inject(at = @At("HEAD"), method = "entityInside(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/Entity;)V", cancellable = true)
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity, CallbackInfo ci) {


        Entity Player = pEntity;
        Entity entity = Player;

        if (entity instanceof LivingEntity) {
            if (((LivingEntity) entity).getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.DEKU_MASK.get()) {
                ci.cancel();
            }


        }
    }
}













