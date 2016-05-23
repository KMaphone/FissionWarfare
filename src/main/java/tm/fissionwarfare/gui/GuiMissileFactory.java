package tm.fissionwarfare.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import tm.fissionwarfare.gui.base.GuiContainerBase;
import tm.fissionwarfare.gui.base.GuiEnergyContainerBase;
import tm.fissionwarfare.tileentity.base.TileEntityEnergyBase;
import tm.fissionwarfare.tileentity.base.TileEntityInventoryBase;

public class GuiMissileFactory extends GuiEnergyContainerBase {
	
	public GuiMissileFactory(Container container, EntityPlayer player, TileEntityEnergyBase tileEntity) {
		super(container, player, tileEntity);
	}

	@Override
	public String getGuiTextureName() {
		return "missile_factory";
	}

	@Override
	public String getGuiTitle() {
		return "Missile Factory";
	}

	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {

	}

	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {

	}
}
