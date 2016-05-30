package tm.fissionwarfare.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import tm.fissionwarfare.gui.base.GuiContainerBase;
import tm.fissionwarfare.tileentity.base.TileEntityInventoryBase;

public class GuiLaunchPad extends GuiContainerBase {

	public GuiLaunchPad(Container container, EntityPlayer player, TileEntityInventoryBase tileEntity) {
		super(container, player, tileEntity);
	}

	@Override
	public String getGuiTextureName() {
		return "launch_pad";
	}

	@Override
	public String getGuiTitle() {
		return "Launch Pad";
	}

	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		
	}

	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {
		
	}	
}
