package tm.fissionwarfare.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import tm.fissionwarfare.entity.EntityExplosive;
import tm.fissionwarfare.render.RenderExplosive;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerRenders() {
		
		RenderingRegistry.registerEntityRenderingHandler(EntityExplosive.class, new RenderExplosive());
	}
}