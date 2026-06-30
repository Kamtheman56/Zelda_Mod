package com.kamth.zeldamod.block.entity.shadow_blocks;

import com.kamth.zeldamod.block.ZeldaBlocks;
import com.kamth.zeldamod.block.entity.ZeldaBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.Shapes;

import javax.annotation.Nullable;

public class HiddenShadowBlockEntity extends ShadowBlockEntity {
    public HiddenShadowBlockEntity(BlockPos pos, BlockState state) {
        super(ZeldaBlockEntities.HIDDEN_SHADOW_BLOCK_ENTITY.get(), pos, state);
    }

    public static BlockEntityType<HiddenShadowBlockEntity> createHiddenShadowType() {
        return BlockEntityType.Builder.of(HiddenShadowBlockEntity::new, ZeldaBlocks.HIDDEN_SHADOW_BLOCK.get()).build(null);
    }
}
