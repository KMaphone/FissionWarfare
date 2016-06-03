package tm.fissionwarfare.tileentity.machine;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import tm.fissionwarfare.gui.GuiLaunchPad;
import tm.fissionwarfare.inventory.ContainerLaunchPad;
import tm.fissionwarfare.tileentity.base.TileEntityEnergyBase;

public class TileEntityLaunchPad extends TileEntityEnergyBase {
	
	public TileEntityLaunchPad() {
		setSideInputSlots(0);
	}
	
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
		return 20 * 10;
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getBoundingBox(xCoord - 1F, yCoord, zCoord - 1F, xCoord + 2F, yCoord + 1F, zCoord + 2F);
	}
		
	@Override
	public int getSizeInventory() {
		return 1;
	}
	
	@Override
	public boolean canConnectEnergy(ForgeDirection dir) {
		return dir == ForgeDirection.DOWN;
	}

	@Override
	public Container getTileContainer(EntityPlayer player) {
		return new ContainerLaunchPad(player, this);
	}

	@Override
	public GuiContainer getTileGuiContainer(EntityPlayer player) {
		return new GuiLaunchPad(getTileContainer(player), player, this);
	}
}
