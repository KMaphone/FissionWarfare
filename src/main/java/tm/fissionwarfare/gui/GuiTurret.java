package tm.fissionwarfare.gui;

import net.minecraft.entity.player.EntityPlayer;
import tm.fissionwarfare.gui.base.GuiButtonRect;
import tm.fissionwarfare.gui.base.GuiEnergyContainerBase;
import tm.fissionwarfare.inventory.ContainerTurret;
import tm.fissionwarfare.tileentity.base.TileEntityEnergyBase;

public class GuiTurret extends GuiEnergyContainerBase {

	private GuiButtonRect toggleButton;
	
	public GuiTurret(EntityPlayer player, TileEntityEnergyBase tileEntity) {
		super(new ContainerTurret(player, tileEntity), player, tileEntity);
	}

	@Override
	public String getGuiTextureName() {
		return "turret";
	}

	@Override
	public String getGuiTitle() {
		return "Turret";
	}
	
	@Override
	public void initGui() {
		super.initGui();
		
		toggleButton = new GuiButtonRect(0, getScreenX() + (getGuiSize() / 2) - 17, getScreenY() + (getGuiSize() / 2) - 24, 32, "Off");
		toggleButton.enabled = true;
		buttonList.add(toggleButton);
	}

	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		
	}

	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {
		
	}
}
