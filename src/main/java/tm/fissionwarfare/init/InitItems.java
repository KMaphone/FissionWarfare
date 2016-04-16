package tm.fissionwarfare.init;

import net.minecraft.item.Item;
import tm.fissionwarfare.item.ItemBase;

public class InitItems {

	public static Item quartz;
	public static Item cement;
	public static Item wet_cement;
	
	public static void init() {
		
		quartz = new ItemBase("quartz");
		
		cement = new ItemBase("cement");
		wet_cement = new ItemBase("wet_cement");
	}
}
