package com.kamth.mixin;

import java.util.Random;
import java.util.UUID;

import com.kamth.zeldamod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Unique;


@Mixin(MushroomBlock.class)
public abstract class MixinMushroomBlock extends BushBlock
{
    // This constructor is fake and never used
    protected MixinMushroomBlock()
    {
        super(null);
    }


    @Unique
    @OnlyIn(value = Dist.CLIENT)
    public void animateTick(BlockState state, Level world, BlockPos pos, Random random)
    {
        Minecraft client = Minecraft.getInstance();



        if (client.player != null && client.player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.SCENT_MASK.get())

        {
            double radius = 20;

            if (client.player.distanceToSqr(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5) < radius * radius)
            {
                double particleX = pos.getX() + 0.5 + world.random.nextDouble() * 0.4 - 0.2;
                double particleY = pos.getY() + 0.2 + world.random.nextDouble() * 0.4 - 0.1;
                double particleZ = pos.getZ() + 0.5 + world.random.nextDouble() * 0.4 - 0.2;
                double particleMotionY = world.random.nextDouble() * 0.1 + 0.1;
                world.addParticle(ParticleTypes.MYCELIUM, particleX, particleY, particleZ, 0, particleMotionY, 0);
                world.addParticle(ParticleTypes.MYCELIUM, particleX, particleY, particleZ, 0, particleMotionY, 0);
                world.addParticle(ParticleTypes.MYCELIUM, particleX, particleY, particleZ, 0, particleMotionY, 1);
                world.addParticle(ParticleTypes.MYCELIUM, particleX, particleY, particleZ, 0, particleMotionY, 1);
                world.addParticle(ParticleTypes.MYCELIUM, particleX, particleY, particleZ, 0, particleMotionY, 2);
                world.addParticle(ParticleTypes.MYCELIUM, particleX, particleY, particleZ, 0, particleMotionY, 2);
                world.addParticle(ParticleTypes.MYCELIUM, particleX, particleY, particleZ, 0, particleMotionY, 3);
                world.addParticle(ParticleTypes.MYCELIUM, particleX, particleY, particleZ, 0, particleMotionY, 3);
            }
        }
    }
}


















