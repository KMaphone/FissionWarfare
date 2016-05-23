package tm.fissionwarfare.tileentity.machine;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraftforge.common.util.ForgeDirection;
import tm.fissionwarfare.gui.GuiMissileFactory;
import tm.fissionwarfare.inventory.ContainerMissileFactory;
import tm.fissionwarfare.tileentity.base.TileEntityEnergyBase;

public class TileEntityMissileFactory extends TileEntityEnergyBase {
	
	public TileEntityMissileFactory() {
		setInputSlots(0, 1, 2);
		setSideInputSlots(3);
		setExtractSlots(4);
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
		return 20 * 5;
	}
	
	@Override
	public boolean canConnectEnergy(ForgeDirection dir) {
		return true;
	}

	@Override
	public int getSizeInventory() {
		return 5;
	}

	@Override
	public Container getTileContainer(EntityPlayer player) {
		return new ContainerMissileFactory(player, this);
	}

	@Override
	public GuiContainer getTileGuiContainer(EntityPlayer player) {
		return new GuiMissileFactory(getTileContainer(player), player, this);
	}
}
