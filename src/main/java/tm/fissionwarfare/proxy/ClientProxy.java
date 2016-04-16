package tm.fissionwarfare.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import tm.fissionwarfare.entity.EntityExplosive;
import tm.fissionwarfare.render.RenderExplosive;
import tm.fissionwarfare.render.RenderScaffold;
import tm.fissionwarfare.tileentity.TileEntityScaffold;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenders() {
		RenderingRegistry.registerEntityRenderingHandler(EntityExplosive.class, new RenderExplosive());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityScaffold.class, new RenderScaffold());
	}
}