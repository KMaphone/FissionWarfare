package tm.fissionwarfare.tileentity.machine;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import tm.fissionwarfare.tileentity.base.TileEntityEnergyBase;

public class TileEntityLaunchPad extends TileEntityEnergyBase {
	
	@Override
	public int getMaxEnergy() {
		return 100000;
	}

	@Override
	public int getMaxReceive() {
		return 10000;
	}

	@Override
	public int getMaxExtract() {
		return 10000;
	}

	@Override
	public int getMaxProgress() {
		return 0;
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getBoundingBox(xCoord - 1F, yCoord, yCoord - 1F, xCoord + 2F, yCoord, zCoord + 2F);
	}
		
	@Override
	public int getSizeInventory() {
		return 0;
	}
	
	@Override
	public boolean canConnectEnergy(ForgeDirection dir) {
		return dir == ForgeDirection.DOWN;
	}

	@Override
	public Container getTileContainer(EntityPlayer player) {
		return null;
	}

	@Override
	public GuiContainer getTileGuiContainer(EntityPlayer player) {
		return null;
	}
}
