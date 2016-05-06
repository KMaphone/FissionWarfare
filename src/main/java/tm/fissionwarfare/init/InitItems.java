package tm.fissionwarfare.init;

import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
import tm.fissionwarfare.item.ItemBase;
import tm.fissionwarfare.item.ItemCement;

public class InitItems {

	public static Item quartz;
	public static Item steel_ingot;
	
	public static Item cement;
	public static Item cementT2;
	public static Item cementT3;
	public static Item iron_frame;
	public static Item steel_frame;
	public static Item invar_frame;
	
	public static void init() {
		
		quartz = new ItemBase("quartz");
		steel_ingot = new ItemBase("steel_ingot");
		
		cement = new ItemCement("cement");
		cementT2 = new ItemCement("cementT2");
		cementT3 = new ItemCement("cementT3");
		
		iron_frame = new ItemBase("iron_frame");
		steel_frame = new ItemBase("steel_frame");
		invar_frame = new ItemBase("invar_frame");
		
		OreDictionary.registerOre("ingotSteel", steel_ingot);
	}
}
