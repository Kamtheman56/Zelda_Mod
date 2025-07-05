package com.kamth.zeldamod.item.items.movement;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.List;

public class WarpItem extends Item {
    public WarpItem(Properties pProperties) {
        super(pProperties);
    }


    public InteractionResult useOn(UseOnContext pContext) {

        Level level = pContext.getLevel();
        Player player = pContext.getPlayer();

        if (player == null) return InteractionResult.PASS;

        ItemStack stack = pContext.getItemInHand();
        BlockPos belowPlayer = player.blockPosition().below();

        if (player.isShiftKeyDown() && level.getBlockState(belowPlayer).getBlock() != Blocks.AIR) {
            BlockPos pos = player.blockPosition();

            if (getPosition(stack) == null) {
                setPosition(stack, level, pos, player);
                player.displayClientMessage(Component.translatable("Location set!").withStyle(ChatFormatting.GREEN), true);
                level.playSound(null, pos, SoundEvents.EVOKER_CAST_SPELL, SoundSource.PLAYERS, 1f, 1f);
                if (!player.isCreative()) {
                    player.getCooldowns().addCooldown(this, 60);
                }
                return InteractionResult.SUCCESS;
            }

            if (getPosition(stack) != null) {
                player.displayClientMessage(Component.translatable("Location already set.").withStyle(ChatFormatting.GREEN), true);
                level.playSound(null, pos, SoundEvents.DISPENSER_FAIL, SoundSource.PLAYERS, 1f, 1f);
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);


        if (getPosition(stack) != null && !player.isShiftKeyDown()) {
            RandomSource rand = level.random;
            for (int i = 0; i < 45; i++) {
                level.addParticle(ParticleTypes.CLOUD,
                        player.getX() + (rand.nextBoolean() ? -1 : 1) * rand.nextDouble() * 2,
                        player.getY() + rand.nextDouble() * 3 - 2,
                        player.getZ() + (rand.nextBoolean() ? -1 : 1) * rand.nextDouble() * 2,
                        0.3, 0.105D, 0.3);
            }


            teleport(player, level, stack);
            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
            if (!player.isCreative()) {
                player.getCooldowns().addCooldown(this, 120);
            }
            return InteractionResultHolder.success(stack);
        }

        if (getPosition(stack) != null && player.isShiftKeyDown()) {
            setPosition(stack, level, null, player);
            player.displayClientMessage(Component.translatable("Location cleared!").withStyle(ChatFormatting.GREEN), true);
            return InteractionResultHolder.success(stack);
        }

        return InteractionResultHolder.pass(stack);
    }

    public static BlockPos getPosition(ItemStack stack)
    {
        if (!stack.hasTag()) {
            return null;
        }

        CompoundTag tags = stack.getTag();

        if (tags != null && tags.contains("pos")) {
            return NbtUtils.readBlockPos((CompoundTag) tags.get("pos"));
        }
        return null;
    }

    public static void setPosition(ItemStack stack, Level world, BlockPos pos, Player player) {
        if(world.isClientSide) {
            return;
        }

        CompoundTag tags;

        if (!stack.hasTag()) {
            tags = new CompoundTag();
        }
        else {
            tags = stack.getTag();
        }

        if (tags != null && pos == null) {
            tags.remove("pos");
            tags.remove("Dim");
        }
        else {
            if (tags != null) {
                tags.put("pos", NbtUtils.writeBlockPos(pos));
                if (world.dimension().equals(Level.OVERWORLD)) tags.putInt("Dim", 0); //OVERWORLD
                if (world.dimension().equals(Level.NETHER)) tags.putInt("Dim", -1); //NETHER
                if (world.dimension().equals(Level.END)) tags.putInt("Dim", 1); //END
            }
        }
        stack.setTag(tags);
    }

    public static int getDimension(ItemStack stack) {
        if (!stack.hasTag()) {
            return Integer.MAX_VALUE;
        }

        CompoundTag tags = stack.getTag();

        if (tags != null && tags.contains("Dim")) {
            return tags.getInt("Dim");
        }

        return Integer.MAX_VALUE;
    }

    public void teleport(Player player, Level world, ItemStack stack) {
        if(world.isClientSide) {
            return;
        }
        int Dim = getDimension(stack);
        BlockPos pos = getPosition(stack);
        if (pos != null) {
            if (world.dimension().equals(Level.OVERWORLD) && Dim == 0) {
                player.teleportTo(pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F);
            } else if (world.dimension().equals(Level.NETHER) && Dim == -1) {
                player.teleportTo(pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F);
            } else if (world.dimension().equals(Level.END) && Dim == 1) {
                player.teleportTo(pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F);
            } else {
                player.displayClientMessage(
                        Component.translatable("You are not currently in the stored dimension").withStyle(ChatFormatting.DARK_GREEN)
                        ,true);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(@Nonnull ItemStack stack, Level world, @Nonnull List<Component> list, @Nonnull TooltipFlag flag)
    {
        String dimName = switch (getDimension(stack)) {
            case 1 -> "End";
            case 0 -> "OverWorld";
            case -1 -> "Nether";
            default -> "Unknown";
        };

        super.appendHoverText(stack, world, list, flag);

        list.add(Component.literal("Allows you to teleport to a saved location on Right-click").withStyle(ChatFormatting.GREEN));
        list.add(Component.literal("Does not work across dimensions").withStyle(ChatFormatting.GREEN));
        list.add(Component.literal("Uses Magic on use").withStyle(ChatFormatting.GRAY));
        list.add(Component.literal("Set: ").withStyle(ChatFormatting.WHITE)
                .append(Component.literal("Point at a block and sneak + Right-click").withStyle(ChatFormatting.AQUA)));
        list.add(Component.literal("Clear: ").withStyle(ChatFormatting.WHITE)
                .append(Component.literal("Point in the air and sneak + Right-click").withStyle(ChatFormatting.AQUA)));

        BlockPos pos = getPosition(stack);
        if (pos != null) {
            list.add(Component.literal("Location Stored:").withStyle(ChatFormatting.GOLD)); // Gold
            list.add(Component.literal("Dim: " + dimName + "  X: " + pos.getX() + "  Y: " + pos.getY() + "  Z: " + pos.getZ())
                    .withStyle(ChatFormatting.YELLOW)); // Yellow
        }
    }
}



