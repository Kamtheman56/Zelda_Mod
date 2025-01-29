package com.kamth.zeldamod.item.items.tools;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.kamth.zeldamod.custom.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;



public class DiggingMittsItem extends DiggerItem {




    public DiggingMittsItem(int pAttackDamageModifier, float pAttackSpeedModifier, Tier pTier, TagKey<Block> pBlocks, Properties pProperties) {
        super((float)pAttackDamageModifier, pAttackSpeedModifier, pTier, ModTags.Blocks.MITTS, pProperties);
    }
    protected static final Map<Block, BlockState> FLATTENABLES = Maps.newHashMap((new ImmutableMap.Builder()).put(Blocks.GRASS_BLOCK, Blocks.DIRT_PATH.defaultBlockState()).put(Blocks.DIRT, Blocks.DIRT_PATH.defaultBlockState()).put(Blocks.PODZOL, Blocks.DIRT_PATH.defaultBlockState()).put(Blocks.COARSE_DIRT, Blocks.DIRT_PATH.defaultBlockState()).put(Blocks.MYCELIUM, Blocks.DIRT_PATH.defaultBlockState()).put(Blocks.ROOTED_DIRT, Blocks.DIRT_PATH.defaultBlockState()).build());

@Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        return  ToolActions.DEFAULT_SHOVEL_ACTIONS.contains(toolAction);
    }
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);



  if (pContext.getClickedFace() == Direction.DOWN) {
            return InteractionResult.PASS;
        } else {
            Player player = pContext.getPlayer();
            BlockState blockstate1 = blockstate.getToolModifiedState(pContext, net.minecraftforge.common.ToolActions.SHOVEL_FLATTEN, false);
            BlockState blockstate2 = null;
            if (blockstate1 != null && level.isEmptyBlock(blockpos.above())) {
                level.playSound(player, blockpos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                blockstate2 = blockstate1;
            } else if (blockstate.getBlock() instanceof CampfireBlock && blockstate.getValue(CampfireBlock.LIT)) {
                if (!level.isClientSide()) {
                    level.levelEvent((Player)null, 1009, blockpos, 0);
                }

                CampfireBlock.dowse(pContext.getPlayer(), level, blockpos, blockstate);
                blockstate2 = blockstate.setValue(CampfireBlock.LIT, Boolean.valueOf(false));
            }
            if (blockstate2 != null) {
                if (!level.isClientSide) {
                    level.setBlock(blockpos, blockstate2, 11);
                    level.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(player, blockstate2));
                    if (player != null) {
                        pContext.getItemInHand().hurtAndBreak(1, player, (p_43122_) -> {
                            p_43122_.broadcastBreakEvent(pContext.getHand());
                        });
                    }
                }
                return InteractionResult.sidedSuccess(level.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        }
    }


    public static BlockState getShovelPathingState(BlockState originalState) {
        return FLATTENABLES.get(originalState.getBlock());
    }
    @Override
   public float getDestroySpeed(ItemStack itemstack, BlockState blockstate) {return 8f;}
    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState blockstate) {
        int tier = 2;
        if (tier < 3 && blockstate.is(BlockTags.NEEDS_DIAMOND_TOOL)) {
            return false;
        } else if (tier < 2 && blockstate.is(BlockTags.NEEDS_IRON_TOOL)) {
            return false;
        } else {
            return tier < 1 && blockstate.is(BlockTags.NEEDS_STONE_TOOL)
                    ? false
                    : (blockstate.is(BlockTags.MINEABLE_WITH_AXE) || blockstate.is(BlockTags.MINEABLE_WITH_HOE)
                    || blockstate.is(BlockTags.MINEABLE_WITH_PICKAXE) || blockstate.is(BlockTags.MINEABLE_WITH_SHOVEL));
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.translatable("item.zeldamod.mitts.description_basic1").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));
            components.add(Component.translatable("item.zeldamod.mitts.description_basic2").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));
        }}
}


