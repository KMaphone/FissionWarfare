package tm.fissionwarfare.gui;

import net.minecraft.entity.player.EntityPlayer;
import tm.fissionwarfare.gui.base.GuiScreenBase;

public class GuiTeamManager extends GuiScreenBase {

	private EntityPlayer player;
	
	public GuiTeamManager(EntityPlayer player) {
		this.player = player;
	}
	
	@Override
	public String getGuiTextureName() {
		return "team_manager";
	}

	@Override
	public int getGuiSizeX() {
		return 256;
	}

	@Override
	public int getGuiSizeY() {
		return 209;
	}

	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		
	}

	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {
		
	}

	@Override
	public boolean canCloseWithInvKey() {
		return true;
	}
}
