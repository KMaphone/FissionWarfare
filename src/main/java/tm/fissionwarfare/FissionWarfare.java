package tm.fissionwarfare;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.world.biome.BiomeGenBase.TempCategory;
import tm.fissionwarfare.gui.GuiHandler;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.init.InitEntities;
import tm.fissionwarfare.init.InitFluids;
import tm.fissionwarfare.init.InitItems;
import tm.fissionwarfare.init.InitTileEntities;
import tm.fissionwarfare.init.recipe.InitRecipes;
import tm.fissionwarfare.init.recipe.InitTERecipes;
import tm.fissionwarfare.proxy.IProxy;
import tm.fissionwarfare.world.WorldGenOre;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS, dependencies = Reference.DEPENDENCIES)
public class FissionWarfare {
	
	@Instance(Reference.MOD_ID)
	public static FissionWarfare instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;
	
	public static WorldGenOre worldGenOre;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		
		InitItems.init();
		InitBlocks.init();
		InitFluids.init();
		InitRecipes.init();
		InitTERecipes.init();
	}
		
	@EventHandler
	public void init(FMLInitializationEvent event) {
		
		proxy.registerRenders();
		
		InitEntities.init();
		InitTileEntities.init();
		
		worldGenOre = new WorldGenOre();
		GameRegistry.registerWorldGenerator(worldGenOre, 1);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {}
}