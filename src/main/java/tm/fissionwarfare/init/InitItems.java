package tm.fissionwarfare.init;

import net.minecraft.item.Item;
import tm.fissionwarfare.item.ItemBase;
import tm.fissionwarfare.item.ItemCement;

public class InitItems {

	public static Item quartz;
	public static Item steel_ingot;
	
	public static Item cement;
	public static Item frame_iron;
	public static Item frame_steel;
	public static Item frame_invar;
	
	public static void init() {
		
		quartz = new ItemBase("quartz");
		steel_ingot = new ItemBase("steel_ingot");
		
		cement = new ItemCement("cement");
		
		frame_iron = new ItemBase("frame_iron");
		frame_steel = new ItemBase("frame_steel");
		frame_invar = new ItemBase("frame_invar");
	}
}
