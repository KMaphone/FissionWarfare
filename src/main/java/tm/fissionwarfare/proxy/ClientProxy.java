package tm.fissionwarfare.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import tm.fissionwarfare.entity.EntityExplosive;
import tm.fissionwarfare.entity.EntityMissile;
import tm.fissionwarfare.event.TierTooltipEvent;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.init.InitItems;
import tm.fissionwarfare.key.KeyInputHandler;
import tm.fissionwarfare.render.RenderExplosive;
import tm.fissionwarfare.render.RenderLaunchPad;
import tm.fissionwarfare.render.RenderMissile;
import tm.fissionwarfare.render.RenderTurret;
import tm.fissionwarfare.render.item.ItemRenderLaunchPad;
import tm.fissionwarfare.render.item.ItemRenderMissile;
import tm.fissionwarfare.render.item.ItemRenderTurret;
import tm.fissionwarfare.tileentity.machine.TileEntityLaunchPad;
import tm.fissionwarfare.tileentity.machine.TileEntityTurretSentry;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerRenders() {
		
		MinecraftForge.EVENT_BUS.register(new TierTooltipEvent());
		FMLCommonHandler.instance().bus().register(new KeyInputHandler());
		
		RenderingRegistry.registerEntityRenderingHandler(EntityExplosive.class, new RenderExplosive());
		RenderingRegistry.registerEntityRenderingHandler(EntityMissile.class, new RenderMissile());
		
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(InitBlocks.turret), new ItemRenderTurret());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(InitBlocks.launch_pad), new ItemRenderLaunchPad());
		MinecraftForgeClient.registerItemRenderer(InitItems.missile, new ItemRenderMissile());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTurretSentry.class, new RenderTurret());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLaunchPad.class, new RenderLaunchPad());
	}
}