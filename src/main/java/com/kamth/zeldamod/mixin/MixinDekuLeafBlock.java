package com.kamth.zeldamod.mixin;

import com.kamth.zeldamod.item.ZeldaItems;
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

    @Inject(at = @At("HEAD"), method = "entityInside", cancellable = true)
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity, CallbackInfo ci) {
        Entity Player = pEntity;
        Entity entity = Player;

        if (entity instanceof LivingEntity) {
            if (((LivingEntity) entity).getItemBySlot(EquipmentSlot.HEAD).getItem() == ZeldaItems.DEKU_MASK.get()) {
                ci.cancel();
            }


        }
    }
}













