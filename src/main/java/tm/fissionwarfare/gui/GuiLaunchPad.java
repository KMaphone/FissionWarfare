package tm.fissionwarfare.gui;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import tm.fissionwarfare.FissionWarfare;
import tm.fissionwarfare.gui.base.GuiButtonRect;
import tm.fissionwarfare.gui.base.GuiContainerBase;
import tm.fissionwarfare.gui.base.GuiEnergyContainerBase;
import tm.fissionwarfare.gui.base.GuiNumberFieldRect;
import tm.fissionwarfare.gui.base.GuiTextFieldRect;
import tm.fissionwarfare.gui.base.GuiUtil;
import tm.fissionwarfare.packet.ServerPacketHandler;
import tm.fissionwarfare.tileentity.base.TileEntityEnergyBase;
import tm.fissionwarfare.tileentity.base.TileEntityInventoryBase;
import tm.fissionwarfare.tileentity.machine.TileEntityLaunchPad;

public class GuiLaunchPad extends GuiEnergyContainerBase {

	TileEntityLaunchPad tileEntity;
	
	private GuiNumberFieldRect xField, zField;
	private GuiButtonRect launchButton;
	
	public GuiLaunchPad(Container container, EntityPlayer player, TileEntityEnergyBase tileEntity) {
		super(container, player, tileEntity);
		this.tileEntity = (TileEntityLaunchPad) tileEntity;
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
	public void initGui() {
		super.initGui();
		
		Keyboard.enableRepeatEvents(true);
		
		xField = new GuiNumberFieldRect(fontRendererObj, getScreenX() + 26, getScreenY() + 32, 70, 9);
		zField = new GuiNumberFieldRect(fontRendererObj, getScreenX() + 26, getScreenY() + 54, 70, 9);
		
		xField.setText("" + tileEntity.targetCoords[0]);
		zField.setText("" + tileEntity.targetCoords[1]);
		
		launchButton = new GuiButtonRect(0, getScreenX() + 97, getScreenY() + 60, 54, tileEntity.launching ? "Abort" : "Launch", buttonList);
	}
	
	@Override
	public void updateScreen() {
		super.updateScreen();
		
		launchButton.displayString = tileEntity.launching ? "Cancel" : "Launch";
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		
		if (button.id == launchButton.id) {
			
			tileEntity.toggleLaunch(player);
			FissionWarfare.network.sendToServer(new ServerPacketHandler("toggle.launch%" + tileEntity.xCoord + "%" + tileEntity.yCoord + "%" + tileEntity.zCoord));
		}
	}
	
	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		
		xField.drawTextBox();
		zField.drawTextBox();
		
		fontRendererObj.drawString("X:", getScreenX() + 14, getScreenY() + 34, 0xCCCCCC);
		fontRendererObj.drawString("Z:", getScreenX() + 14, getScreenY() + 56, 0xCCCCCC);
	}
	
	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {}
	
	private void setCoord(GuiTextField field, int index) {
			
		if (field.getText() != null && !field.getText().trim().isEmpty()) {

			int coord = parseInt(tileEntity.targetCoords[index], field.getText());
			
			tileEntity.targetCoords[index] = coord;
			FissionWarfare.network.sendToServer(new ServerPacketHandler("set.coords%" + tileEntity.xCoord + "%" + tileEntity.yCoord + "%" + tileEntity.zCoord + "%" + index + "%" + coord));
		}
	}
		
	private int parseInt(int i, String text) {
		
		try  {
			i = Integer.parseInt(text);
		}
		
		catch (NumberFormatException e) {
			
		}
		
		return i;
	}
	
	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
		Keyboard.enableRepeatEvents(false);
	}
	
	@Override
	protected void keyTyped(char c, int i) {
		super.keyTyped(c, i);
			
		xField.textboxKeyTyped(c, i);
		zField.textboxKeyTyped(c, i);
		
		setCoord(xField, 0);
		setCoord(zField, 1);
    }

	@Override
	protected void mouseClicked(int x, int y, int i) {
	    super.mouseClicked(x, y, i);
	    xField.mouseClicked(x, y, i);
	    zField.mouseClicked(x, y, i);
	}
}
