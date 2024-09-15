package com.happysg.levelcraft.gui;

import com.happysg.levelcraft.block.ResearchTableBlockEntity;
import com.happysg.levelcraft.registry.ModBlocks;
import com.happysg.levelcraft.registry.ModMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;

public class ResearchMenu extends AbstractContainerMenu {

    private final ResearchTableBlockEntity researchTable;

    public ResearchMenu(int pContainerId, Inventory pPlayerInventory, BlockPos pos) {
        super(ModMenus.RESEARCH_MENU.get(), pContainerId);
        researchTable = (ResearchTableBlockEntity) pPlayerInventory.player.level().getBlockEntity(pos);

    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(researchTable.getLevel(), researchTable.getBlockPos()), pPlayer, ModBlocks.RESEARCH_TABLE.get());
    }
}
