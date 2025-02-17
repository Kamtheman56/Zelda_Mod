package com.kamth.zeldamod.block.custom.pedastal;

import com.kamth.zeldamod.block.entity.SwordPedestalEntity;
import com.kamth.zeldamod.custom.ModTags;
import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class AncientSwordPedestalBlock extends SwordPedestalBlock {

    public AncientSwordPedestalBlock(Properties pProperties) {
        super(pProperties);
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public InteractionResult  use(BlockState pState, Level pLevel, BlockPos pPos,
                                  Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack stackInHand = pPlayer.getItemInHand(pHand);
        BlockEntity te = pLevel.getBlockEntity(pPos);

        if (te instanceof SwordPedestalEntity)
        {
            SwordPedestalEntity pedestal = (SwordPedestalEntity) pLevel.getBlockEntity(pPos);


            if (stackInHand.is(ModTags.Items.TRANSFORMING_SWORDS) && pedestal.getSword().isEmpty())
            {
                pedestal.setSword(stackInHand);
                pLevel.playSound(pPlayer,pPos, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS);
                pPlayer.setItemInHand(pHand, ItemStack.EMPTY);
                pLevel.updateNeighborsAt(pPos,this);
                pedestal.setSword(ZeldaItems.HERO_SWORD.get().getDefaultInstance());
                return InteractionResult.SUCCESS;
            }
            if (stackInHand.is(ZeldaItems.HERO_SWORD.get()) && pPlayer.getMaxHealth() >= 30 && pedestal.getSword().isEmpty())
            {
                pedestal.setSword(stackInHand);
                pLevel.playSound(pPlayer,pPos, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS);
                pPlayer.setItemInHand(pHand, ItemStack.EMPTY);
                pLevel.updateNeighborsAt(pPos,this);
                pedestal.setSword(ZeldaItems.WHITE_SWORD.get().getDefaultInstance());
                return InteractionResult.SUCCESS;
            }
            if (stackInHand.is(ZeldaItems.WHITE_SWORD.get()) && pPlayer.getMaxHealth() <= 6 && pedestal.getSword().isEmpty())
            {
                pedestal.setSword(stackInHand);
                pLevel.playSound(pPlayer,pPos, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS);
                pPlayer.setItemInHand(pHand, ItemStack.EMPTY);
                pLevel.updateNeighborsAt(pPos,this);
                pedestal.setSword(ZeldaItems.MAGIC_SWORD.get().getDefaultInstance());
                return InteractionResult.SUCCESS;
            }
            if (stackInHand.is(ModTags.Items.GLOOM_WEAPONS) && pPlayer.getMaxHealth() <= 6 && pedestal.getSword().isEmpty())
            {
                pedestal.setSword(stackInHand);
                pLevel.playSound(pPlayer,pPos, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS);
                pPlayer.setItemInHand(pHand, ItemStack.EMPTY);
                pLevel.updateNeighborsAt(pPos,this);
                pedestal.getSword().enchant(Enchantments.MOB_LOOTING,6);
                return InteractionResult.SUCCESS;
            }
            if (stackInHand.getItem() instanceof SwordItem && !stackInHand.is(ModTags.Items.TRANSFORMING_SWORDS) && pedestal.getSword().isEmpty())
            {
                pedestal.setSword(stackInHand);
                pLevel.playSound(pPlayer,pPos, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS);
                pPlayer.setItemInHand(pHand, ItemStack.EMPTY);
                pLevel.updateNeighborsAt(pPos,this);
                return InteractionResult.SUCCESS;
            }
            else if (stackInHand.isEmpty() && !pedestal.getSword().isEmpty())
            {
                pLevel.updateNeighborsAt(pPos,this);
                pPlayer.setItemInHand(pHand, pedestal.getSword());
                pedestal.setSword(stackInHand);
                pLevel.playSound(pPlayer,pPos, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.FAIL;
    }


}
