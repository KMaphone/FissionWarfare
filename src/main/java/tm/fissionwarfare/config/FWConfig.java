package tm.fissionwarfare.config;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;
import tm.fissionwarfare.FissionWarfare;
import tm.fissionwarfare.Reference;

public class FWConfig {

	public static Configuration config = FissionWarfare.config;
	
	public static boolean enableIgnitingPlacedExplosives;
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
				
		if (event.modID.equals(Reference.MOD_ID)) {
			syncConfig();
		}
	}
	
	public static void syncConfig() {	
		
		FMLCommonHandler.instance().bus().register(FissionWarfare.instance);
		
		final String GENERAL_OPTIONS = config.CATEGORY_GENERAL + config.CATEGORY_SPLITTER + "General Options";
		config.setCategoryLanguageKey(GENERAL_OPTIONS, "category.general_options.name");
		config.addCustomCategoryComment(GENERAL_OPTIONS, "General options for " + Reference.MOD_NAME + ".");
		
		enableIgnitingPlacedExplosives = config.getBoolean("Ignite Placed Explosives", GENERAL_OPTIONS, true, "Enables or disables the ability to ignite placed explosives.");
		
		final String GUI = config.CATEGORY_GENERAL + config.CATEGORY_SPLITTER + "GUI";
		config.setCategoryLanguageKey(GUI, "category.gui.name");
		config.addCustomCategoryComment(GUI, "Options for GUIs.");
		
		final String ORES = config.CATEGORY_GENERAL + config.CATEGORY_SPLITTER + "Ores";
		config.setCategoryLanguageKey(ORES, "category.ores.name");
		config.addCustomCategoryComment(ORES, "Options for ores.");
		
		if (config.hasChanged()) {
			config.save();
		}
	}
}
