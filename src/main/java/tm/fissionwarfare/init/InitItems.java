package tm.fissionwarfare.init;

import net.minecraft.item.Item;
import tm.fissionwarfare.item.ItemBase;

public class InitItems {

	public static Item copper_ingot, tin_ingot;
	public static Item quartz, sulfur_dust;
	
	public static void init() {
		
		copper_ingot = new ItemBase("copper_ingot");
		tin_ingot = new ItemBase("tin_ingot");
		
		quartz = new ItemBase("quartz");
		sulfur_dust = new ItemBase("sulfur_dust");
	}	
}
