package tm.fissionwarfare.init;

import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
import tm.fissionwarfare.item.ItemBase;
import tm.fissionwarfare.item.ItemMissile;
import tm.fissionwarfare.item.ItemTiered;
import tm.fissionwarfare.util.ToolSet;

public class InitItems {

	public static Item quartz;
	public static Item quartz_sand;
	public static Item steel_ingot;
	public static Item limestone_chunk;
	public static Item radioactive_chunk;
	public static Item uranium_pellet;
	public static Item quartz_chunk;
	
	public static Item poison_capsule;
	public static Item fiery_capsule;
	public static Item shrapnel_capsule;
	public static Item nuclear_capsule;
	public static Item electromagnetic_capsule;
	
	public static Item cement;
	public static Item wet_cement;
	
	public static Item lightning_rod;
	public static Item iron_frame;
	public static Item circuit;	
	public static Item fuel_canister;
	
	public static Item missile;
	
	public static Item shell;
	public static Item bullet;
	
	public static ToolSet steel;
	
	public static void init() {
		
		quartz = new ItemBase("quartz");
		quartz_sand = new ItemBase("quartz_sand");
		steel_ingot = new ItemBase("steel_ingot");
		limestone_chunk = new ItemBase("limestone_chunk");
		radioactive_chunk = new ItemBase("radioactive_chunk");
		uranium_pellet = new ItemBase("uranium_pellet");
		quartz_chunk = new ItemBase("quartz_chunk");
		
		poison_capsule = new ItemBase("poison_capsule");
		fiery_capsule = new ItemBase("fiery_capsule");
		shrapnel_capsule = new ItemBase("shrapnel_capsule");
		nuclear_capsule = new ItemBase("nuclear_capsule");
		electromagnetic_capsule = new ItemBase("electromagnetic_capsule");
		
		cement = new ItemBase("cement");
		wet_cement = new ItemBase("wet_cement");
		
		lightning_rod = new ItemBase("lightning_rod");
		iron_frame = new ItemBase("iron_frame");
		circuit = new ItemTiered("circuit", 3);
		fuel_canister = new ItemTiered("fuel_canister", 3);
		
		missile = new ItemMissile();
		
		shell = new ItemBase("shell");
		bullet = new ItemBase("bullet");
		
		steel = new ToolSet("steel", InitToolMaterials.toolMaterialSteel, steel_ingot, true);
		
		OreDictionary.registerOre("ingotSteel", steel_ingot);
	}
}
