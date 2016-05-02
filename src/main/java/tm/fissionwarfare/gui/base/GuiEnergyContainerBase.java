package tm.fissionwarfare.gui.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.tileentity.base.TileEntityEnergyBase;
import tm.fissionwarfare.tileentity.base.TileEntityInventoryBase;

public abstract class GuiEnergyContainerBase extends GuiContainerBase {

	public TileEntityEnergyBase tileEntity;
	
	public GuiEnergyContainerBase(Container container, EntityPlayer player, TileEntityEnergyBase tileEntity) {
		super(container, player, tileEntity);
		this.tileEntity = tileEntity;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(f, mouseX, mouseY);
		
		GuiUtil.drawStatusPanel(tileEntity.storage.getEnergyStored(), tileEntity.storage.getMaxEnergyStored(), tileEntity.progress, tileEntity.getMaxProgress(), getScreenX() - 19, getScreenY() + ((getGuiSize() / 4) - 34), mouseX, mouseY);
	}		
}
