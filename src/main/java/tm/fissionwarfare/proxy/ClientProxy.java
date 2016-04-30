package tm.fissionwarfare.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import tm.fissionwarfare.entity.EntityExplosive;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.render.RenderExplosive;
import tm.fissionwarfare.render.RenderTurret;
import tm.fissionwarfare.render.item.ItemRenderTurret;
import tm.fissionwarfare.tileentity.TileEntityTurret;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerRenders() {
		
		RenderingRegistry.registerEntityRenderingHandler(EntityExplosive.class, new RenderExplosive());
		
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(InitBlocks.turret), new ItemRenderTurret());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTurret.class, new RenderTurret());
	}
}