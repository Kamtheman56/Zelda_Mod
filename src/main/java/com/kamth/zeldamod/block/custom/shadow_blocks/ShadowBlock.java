package com.kamth.zeldamod.block.custom.shadow_blocks;
import com.kamth.zeldamod.block.entity.shadow_blocks.ShadowBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.Random;

public class ShadowBlock extends Block implements EntityBlock {
    public ShadowBlock(Properties properties) {
        super(properties);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ShadowBlockEntity(pos, state);
    }

    @Override
    public boolean addLandingEffects(BlockState state1, ServerLevel level, BlockPos pos, BlockState state2, LivingEntity entity, int numberOfParticles) {
        getBlockEntity(level, pos).ifPresent(t -> {
            level.sendParticles(getBlockParticles(t), entity.getX(), entity.getY(), entity.getZ(), numberOfParticles, 0.0D, 0.0D, 0.0D, 0.15F);
        });
        return true;
    }

    @Override
    public boolean addRunningEffects(BlockState state, Level level, BlockPos pos, Entity entity) {
        getBlockEntity(level, pos).ifPresent(t -> {
            Vec3 vector3d = entity.getDeltaMovement();
            Random random = new Random();
            level.addParticle(getBlockParticles(t),
                    entity.getX() + (random.nextDouble() - 0.5D) * entity.getBbWidth(),
                    entity.getY() + 0.1D,
                    entity.getZ() + (random.nextDouble() - 0.5D) * entity.getBbWidth(),
                    vector3d.x * -4.0D, 1.5D, vector3d.z * -4.0D);
        });
        return true;
    }

    private BlockParticleOption getBlockParticles(ShadowBlockEntity blockEntity) {
        return new BlockParticleOption(ParticleTypes.BLOCK, blockEntity.getAppearance());
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        BlockState disguise = ShadowBlockItem.loadDisguiseFromStack(stack);
        if (disguise != null && level.getBlockEntity(pos) instanceof ShadowBlockEntity shadowEntity) {
            shadowEntity.setDisguise(disguise);
        }
    }

    @Override
    public BlockState getAppearance(BlockState state, BlockAndTintGetter level, BlockPos pos, Direction side, @Nullable BlockState queryState, @Nullable BlockPos queryPos) {
        if (level.getBlockEntity(pos) instanceof ShadowBlockEntity shadowEntity) {
            BlockState disguise = shadowEntity.getDisguise();
            return disguise != null && !(disguise.getBlock() instanceof ShadowBlock) ? disguise : state;
        }
        return state;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (level.getBlockEntity(pos) instanceof ShadowBlockEntity shadowEntity) {
            BlockState disguise = shadowEntity.getDisguise();
            if (disguise != null && !(disguise.getBlock() instanceof ShadowBlock)) {
                return disguise.getShape(level, pos, context);
            }
        }
        return super.getShape(state, level, pos, context);
    }

    private Optional<ShadowBlockEntity> getBlockEntity(Level level, BlockPos pos) {
        return Optional.ofNullable(level.getBlockEntity(pos))
                .map(t -> (ShadowBlockEntity) t);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (level.getBlockEntity(pos) instanceof ShadowBlockEntity shadowEntity) {
            BlockState disguise = shadowEntity.getDisguise();
            if (disguise != null) {
                return disguise.getCollisionShape(level, pos, context);
            }
        }
        return super.getCollisionShape(state, level, pos, context);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        if (level.getBlockEntity(pos) instanceof ShadowBlockEntity shadowEntity) {
            BlockState disguise = shadowEntity.getDisguise();
            if (disguise != null && !(disguise.getBlock() instanceof ShadowBlock)) {
                return disguise.propagatesSkylightDown(level, pos);
            }
        }
        return super.propagatesSkylightDown(state, level, pos);
    }

    @Override
    public float getShadeBrightness(BlockState state, BlockGetter level, BlockPos pos) {
        if (level.getBlockEntity(pos) instanceof ShadowBlockEntity shadowEntity) {
            BlockState disguise = shadowEntity.getDisguise();
            if (disguise != null && !(disguise.getBlock() instanceof ShadowBlock)) {
                return disguise.getShadeBrightness(level, pos);
            }
        }
        return super.getShadeBrightness(state, level, pos);
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }
}