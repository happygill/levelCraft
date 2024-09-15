package com.happysg.levelcraft.item;

import com.happysg.levelcraft.LevelCraft;
import com.happysg.levelcraft.block.ResearchTableBlock;
import com.happysg.levelcraft.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.BlockState;

public class ResearchTableBlockItem extends BlockItem {

    public ResearchTableBlockItem() {
        super(ModBlocks.RESEARCH_TABLE.get(), new Properties());
    }

    @Override
    protected boolean canPlace(BlockPlaceContext pContext, BlockState pState) {
        Direction direction = pContext.getHorizontalDirection();
        BlockPos neighborPos = pContext.getClickedPos().relative(direction.getClockWise());
        if (!pContext.getLevel().getBlockState(neighborPos).canBeReplaced()) {
            LevelCraft.LOGGER.debug("Can't place research table here");
            return false;
        }
        return super.canPlace(pContext, pState);
    }

    @Override
    protected boolean placeBlock(BlockPlaceContext pContext, BlockState pState) {
        boolean success = super.placeBlock(pContext, pState);
        if (success) {
            Direction direction = pContext.getHorizontalDirection();
            BlockPos neighborPos = pContext.getClickedPos().relative(direction.getClockWise());
            pContext.getLevel().setBlock(neighborPos, pState.setValue(ResearchTableBlock.LEFT, false), 3);
        }
        return success;
    }
}
