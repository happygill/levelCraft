package com.happysg.levelcraft.block;

import com.happysg.levelcraft.gui.ResearchMenu;
import com.happysg.levelcraft.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ResearchTableBlockEntity extends BlockEntity implements MenuProvider {

    public ResearchTableBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.RESEARCH_TABLE.get(), pPos, pBlockState);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("menu.levelcraft.research");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new ResearchMenu(pContainerId, pPlayerInventory, getBlockPos());
    }
}
