package tm.fissionwarfare.gui.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import tm.fissionwarfare.api.ISecurity;
import tm.fissionwarfare.api.SecurityProfile;
import tm.fissionwarfare.tileentity.base.TileEntityEnergyBase;

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
		
		if (tileEntity instanceof ISecurity) {
			
			SecurityProfile profile = ((ISecurity)tileEntity).getSecurityProfile();
						
			if (profile.hasTeam()) {				
				GuiUtil.drawBottomInfoBox(profile.getTeamName(), getScreenX() + getGuiSize() / 2, getScreenY() + getGuiSize(), 0xFFFFFF);
			}		
		}
	}		
}
