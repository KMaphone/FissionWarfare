package tm.fissionwarfare.init;

import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
import tm.fissionwarfare.item.ItemBase;
import tm.fissionwarfare.item.ItemCement;

public class InitItems {

	public static Item quartz;
	public static Item quartz_sand;
	public static Item steel_ingot;
	public static Item limestone_chunk;
	
	public static Item cement;
	public static Item cementT2;
	public static Item cementT3;
	public static Item frame;
	public static Item frame_T2;
	public static Item frame_T3;
	
	public static Item circuit;
	public static Item circuit_T2;
	public static Item circuit_T3;
	
	
	public static void init() {
		
		quartz = new ItemBase("quartz");
		quartz_sand = new ItemBase("quartz_sand");
		steel_ingot = new ItemBase("steel_ingot");
		limestone_chunk = new ItemBase("limestone_chunk");
		
		cement = new ItemCement("cement");
		cementT2 = new ItemCement("cementT2");
		cementT3 = new ItemCement("cementT3");
		
		frame = new ItemBase("frame");
		frame_T2 = new ItemBase("frame_T2");
		frame_T3 = new ItemBase("frame_T3");
		
		circuit = new ItemBase("circuit");
		circuit_T2 = new ItemBase("circuit_T2");
		circuit_T3 = new ItemBase("circuit_T3");
		
		OreDictionary.registerOre("ingotSteel", steel_ingot);
	}
}
