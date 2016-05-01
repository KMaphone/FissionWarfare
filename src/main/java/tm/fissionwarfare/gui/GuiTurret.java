package tm.fissionwarfare.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import tm.fissionwarfare.inventory.ContainerTurret;
import tm.fissionwarfare.tileentity.base.TileEntityInventoryBase;

public class GuiTurret extends GuiContainerBase {

	public GuiTurret(EntityPlayer player, TileEntityInventoryBase tileEntity) {
		super(new ContainerTurret(player, tileEntity), player, tileEntity);
	}

	@Override
	public String getGuiTextures() {
		return "turret";
	}

	@Override
	public String getGuiTitle() {
		return "Turret";
	}

	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		
	}

	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {
		
	}
}
