package tm.fissionwarfare.render.item;

import org.lwjgl.opengl.GL11;

public class ItemRenderControlPanel extends ItemRenderBase {

	public ItemRenderControlPanel() {
		super("control_panel");
	}

	@Override
	public void renderForAll() {
		GL11.glScalef(0.9F, 0.9F, 0.9F);
	}

	@Override
	public void renderInventory() {
		GL11.glTranslatef(0, -0.65F, 0);
	}

	@Override
	public void renderFloatingEntity() {
		
	}

	@Override
	public void renderFirstPerson() {
		
		GL11.glRotatef(-160, 0, 1, 0);
		GL11.glRotatef(-20, 0, 0, 1);
		
		GL11.glTranslatef(-1.3F, -0.5F, -1.3F);
	}

	@Override
	public void renderThirdPerson() {
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		
		GL11.glRotatef(-60, 1, 0, 0);
		GL11.glRotatef(35, 0, 1, 0);
		GL11.glRotatef(15, 0, 0, 1);
		
		GL11.glTranslatef(0.4F, -0F, 1F);
	}
}
