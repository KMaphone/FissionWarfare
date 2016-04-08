package tm.fissionwarfare.init;

import net.minecraft.item.Item;
import tm.fissionwarfare.item.ItemBase;

public class InitItems {

	public static Item quartz;
	
	public static void init() {
		
		quartz = new ItemBase("quartz");
	}
}
