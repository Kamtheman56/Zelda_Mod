package com.kamth.zeldamod.block.custom.pedastal;

import com.kamth.zeldamod.block.entity.SwordPedestalEntity;
import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.enchantments.ZeldaEnchantments;
import com.kamth.zeldamod.item.ZeldaItems;
import com.kamth.zeldamod.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class UnlockedSwordPedestalBlock extends SwordPedestalBlock {


    public UnlockedSwordPedestalBlock(Properties pProperties) {
        super(pProperties);
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public InteractionResult  use(BlockState pState, Level pLevel, BlockPos pPos,
                                  Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack stackInHand = pPlayer.getItemInHand(pHand);

        BlockEntity te = pLevel.getBlockEntity(pPos);
        SwordPedestalEntity pedestal = (SwordPedestalEntity) pLevel.getBlockEntity(pPos);

        if (te instanceof SwordPedestalEntity) {

            // Checks if Master Sword_Golden is enchanted if so gives us the True Master Sword

            if (stackInHand.is(ZeldaItems.MASTER_SWORD_GOLDEN.get()) && pedestal.getSword().isEmpty()) {
                if (stackInHand.getAllEnchantments().containsKey(Enchantments.SMITE) && stackInHand.getAllEnchantments().containsKey(ZeldaEnchantments.SWORD_SPIN.get()) &&
                stackInHand.getAllEnchantments().containsKey(Enchantments.UNBREAKING)) {

                    pedestal.setSword(ZeldaItems.MASTER_SWORD_TRUE.get().getDefaultInstance());
                    pedestal.getSword().enchant(Enchantments.SMITE,3);
                    pedestal.getSword().enchant(Enchantments.SWEEPING_EDGE,3);
                    pedestal.getSword().enchant(Enchantments.MOB_LOOTING,3);
                    pedestal.getSword().enchant(ZeldaEnchantments.SWORD_SPIN.get(), 3);

                    EnchantSound(pLevel,pPlayer,pPos);

                    pPlayer.setItemInHand(pHand, ItemStack.EMPTY);
                    pLevel.updateNeighborsAt(pPos,this);
                    return InteractionResult.SUCCESS;
                }
            }

            if (stackInHand.is(ModTags.Items.BROKEN_SWORDS) && pPlayer.getMaxHealth() >= 26 && pedestal.getSword().isEmpty()) {

                pedestal.setSword(ZeldaItems.MASTER_SWORD.get().getDefaultInstance());
                EnchantSound(pLevel,pPlayer,pPos);
                pPlayer.setItemInHand(pHand, ItemStack.EMPTY);
                pLevel.updateNeighborsAt(pPos,this);
                return InteractionResult.SUCCESS;
            }
        }

            if (stackInHand.isEmpty() && !pedestal.getSword().isEmpty())
            {
                pLevel.updateNeighborsAt(pPos,this);
                pPlayer.setItemInHand(pHand, pedestal.getSword());
                pedestal.setSword(stackInHand);
                pLevel.playSound(pPlayer,pPos, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS);
                return InteractionResult.SUCCESS;
            }
            else  if (stackInHand.getItem() instanceof SwordItem && pedestal.getSword().isEmpty())
        {
            pedestal.setSword(stackInHand);
            pLevel.playSound(pPlayer,pPos, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS);
            pPlayer.setItemInHand(pHand, ItemStack.EMPTY);
            pLevel.updateNeighborsAt(pPos,this);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }


    private void EnchantSound(Level pLevel, Player player, BlockPos pos) {
        pLevel.playSound(player, pos, ModSounds.SWORD_ENCHANT.get(), SoundSource.BLOCKS);
    }


}
