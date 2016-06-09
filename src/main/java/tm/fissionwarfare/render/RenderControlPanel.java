package tm.fissionwarfare.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;

public class RenderControlPanel extends RenderTileEntityBase {

	public RenderControlPanel() {
		super("control_panel");
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {
		
		GL11.glPushMatrix();
		
		GL11.glTranslated(x + 0.5D, y, z + 0.5D);
		GL11.glScaled(0.85D, 0.85D, 0.85D);
		
		GL11.glRotatef(-90 * tileEntity.getBlockMetadata(), 0, 1, 0);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		model.renderAll();	
		
		GL11.glPopMatrix();		
	}
}
