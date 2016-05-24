package tm.fissionwarfare.init;

import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
import tm.fissionwarfare.item.ItemBase;
import tm.fissionwarfare.item.ItemCement;
import tm.fissionwarfare.item.ItemTiered;

public class InitItems {

	public static Item quartz;
	public static Item quartz_sand;
	public static Item steel_ingot;
	public static Item limestone_chunk;
	public static Item uranium_chunk;
	public static Item quartz_chunk;
	
	public static Item poison_capsule;
	public static Item fiery_capsule;
	public static Item shrapnel_capsule;
	public static Item nuclear_capsule;
	public static Item electromagnetic_capsule;
	
	public static Item liquid_coal_canister;
	public static Item pyrotheum_canister;
	public static Item cryotheum_canister;
	
	public static Item cement;
	
	public static Item frame;
	public static Item circuit;	
	
	public static Item shell;
	public static Item bullet;
	
	public static void init() {
		
		quartz = new ItemBase("quartz");
		quartz_sand = new ItemBase("quartz_sand");
		steel_ingot = new ItemBase("steel_ingot");
		limestone_chunk = new ItemBase("limestone_chunk");
		uranium_chunk = new ItemBase("uranium_chunk");
		quartz_chunk = new ItemBase("quartz_chunk");
		
		poison_capsule = new ItemBase("poison_capsule");
		fiery_capsule = new ItemBase("fiery_capsule");
		shrapnel_capsule = new ItemBase("shrapnel_capsule");
		nuclear_capsule = new ItemBase("nuclear_capsule");
		electromagnetic_capsule = new ItemBase("electromagnetic_capsule");
		
		liquid_coal_canister = new ItemBase("liquid_coal_canister");
		pyrotheum_canister = new ItemBase("pyrotheum_canister");
		cryotheum_canister = new ItemBase("cryotheum_canister");
		
		cement = new ItemCement();
		
		frame = new ItemTiered("frame", 3);
		circuit = new ItemTiered("circuit", 3);
		
		shell = new ItemBase("shell");
		bullet = new ItemBase("bullet");
		
		OreDictionary.registerOre("ingotSteel", steel_ingot);
	}
}
