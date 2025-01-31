package com.kamth.zeldamod.block.custom.redstone_related;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;


//A block that activates when hit with a projectile only
public class TimedEyeSwitchBlock extends EyeSwitchBlock {

    public final int ticksToStayPressed;
    private final boolean arrowsCanPress;

    public TimedEyeSwitchBlock(Properties pProperties, int ticksToStayPressed, boolean arrowsCanPress) {
        super(pProperties);
        this.ticksToStayPressed = ticksToStayPressed;
        this.arrowsCanPress = arrowsCanPress;
    }


    @Override
    public void press(BlockState pState, Level pLevel, BlockPos pPos) {
        pLevel.setBlock(pPos, pState.setValue(POWERED, Boolean.valueOf(true)), 3);
        this.updateNeighbours(pState, pLevel, pPos);
        pLevel.scheduleTick(pPos, this, this.ticksToStayPressed);
    }

    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pState.getValue(POWERED)) {
            this.checkPressed(pState, pLevel, pPos);
        }
    }

    protected void checkPressed(BlockState pState, Level pLevel, BlockPos pPos) {
        AbstractArrow abstractarrow = this.arrowsCanPress ? pLevel.getEntitiesOfClass(AbstractArrow.class, pState.getShape(pLevel, pPos).bounds().move(pPos)).stream().findFirst().orElse((AbstractArrow)null) : null;
        boolean flag = abstractarrow != null;
        boolean flag1 = pState.getValue(POWERED);
        if (flag != flag1) {
            pLevel.setBlock(pPos, pState.setValue(POWERED, Boolean.valueOf(flag)), 3);
            this.updateNeighbours(pState, pLevel, pPos);
            pLevel.gameEvent(abstractarrow, flag ? GameEvent.BLOCK_ACTIVATE : GameEvent.BLOCK_DEACTIVATE, pPos);
        }
        if (flag) {
            pLevel.scheduleTick(new BlockPos(pPos), this, this.ticksToStayPressed);
        }
    }
}
