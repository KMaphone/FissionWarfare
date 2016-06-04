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
	
	private GuiNumberFieldRect xField, yField, zField;
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
		
		xField = new GuiNumberFieldRect(fontRendererObj, getScreenX() + 8, getScreenY() + 21, 70, 10);
		yField = new GuiNumberFieldRect(fontRendererObj, getScreenX() + 8, getScreenY() + 42, 70, 10);
		zField = new GuiNumberFieldRect(fontRendererObj, getScreenX() + 8, getScreenY() + 63, 70, 10);
		
		xField.setText("" + tileEntity.targetCoords[0]);
		yField.setText("" + tileEntity.targetCoords[1]);
		zField.setText("" + tileEntity.targetCoords[2]);
		
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
			
			tileEntity.toggleLaunch();
			FissionWarfare.network.sendToServer(new ServerPacketHandler("toggle.launch%" + tileEntity.xCoord + "%" + tileEntity.yCoord + "%" + tileEntity.zCoord));
		}
	}
	
	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		
		xField.drawTextBox();
		yField.drawTextBox();
		zField.drawTextBox();
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
		yField.textboxKeyTyped(c, i);
		zField.textboxKeyTyped(c, i);
		
		setCoord(xField, 0);
		setCoord(yField, 1);
		setCoord(zField, 2);
    }

	@Override
	protected void mouseClicked(int x, int y, int i) {
	    super.mouseClicked(x, y, i);
	    xField.mouseClicked(x, y, i);
	    yField.mouseClicked(x, y, i);
	    zField.mouseClicked(x, y, i);
	}
}
