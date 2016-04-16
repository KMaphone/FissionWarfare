package tm.fissionwarfare.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidTankInfo;
import tm.fissionwarfare.init.InitFluids;
import tm.fissionwarfare.tileentity.TileEntityScaffold;

public class RenderScaffold extends TileEntitySpecialRenderer {

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {
		
		TileEntityScaffold teScaffold = (TileEntityScaffold)tileEntity;
		
		float scale = (0.99F / teScaffold.getTank().getCapacity()) * (teScaffold.getTank().getFluid() != null ? teScaffold.getTank().getFluidAmount() : 0);
		
		if (scale > 0) {
			
			GL11.glPushMatrix();
		
			GL11.glDisable(GL11.GL_LIGHTING);
		
			bindTexture(TextureMap.locationBlocksTexture);
		
			GL11.glTranslated(x, y, z);
			CubeRenderer.render(0.01F, 0.01F, 0.01F, 0.99F, scale, 0.99F, InitFluids.block_concrete_mix.getIcon(0, 0));
		
			GL11.glEnable(GL11.GL_LIGHTING);
		
			GL11.glPopMatrix();
		}	
	}
}
