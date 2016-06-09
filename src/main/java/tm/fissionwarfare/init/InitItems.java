package tm.fissionwarfare.init;

import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
import tm.fissionwarfare.item.ItemBase;
import tm.fissionwarfare.item.ItemCompressor;
import tm.fissionwarfare.item.ItemGun;
import tm.fissionwarfare.item.ItemLocationLinker;
import tm.fissionwarfare.item.ItemMissile;
import tm.fissionwarfare.item.ItemNailGun;
import tm.fissionwarfare.item.ItemTiered;
import tm.fissionwarfare.util.ToolSet;

public class InitItems {

	public static Item quartz_sand;
	public static Item limestone_chunk;
	public static Item radioactive_chunk;
	public static Item uranium_pellet;
	public static Item quartz_chunk;
	
	public static Item steel_ingot;
	public static Item steel_plate;
	
	public static Item poison_capsule;
	public static Item fiery_capsule;
	public static Item shrapnel_capsule;
	public static Item nuclear_capsule;
	public static Item electromagnetic_capsule;
	
	public static Item cement;
	public static Item wet_cement;
	
	public static Item lightning_rod;
	public static Item iron_frame;
	public static Item invar_frame;
	public static Item circuit;	
	public static Item fuel_canister;
	public static Item motor;
	
	public static Item missile;
	public static Item nail_gun;
	public static Item compressor;
	
	public static Item nail;
	public static Item shell;
	public static Item bullet;
	public static Item nail_gun_magazine;
	
	public static Item location_linker;
	
	public static Item shotgun;
	
	public static ToolSet steel;
	
	public static void init() {
		
		quartz_sand = new ItemBase("quartz_sand");
		limestone_chunk = new ItemBase("limestone_chunk");
		radioactive_chunk = new ItemBase("radioactive_chunk");
		uranium_pellet = new ItemBase("uranium_pellet");
		quartz_chunk = new ItemBase("quartz_chunk");
		
		poison_capsule = new ItemBase("poison_capsule");
		fiery_capsule = new ItemBase("fiery_capsule");
		shrapnel_capsule = new ItemBase("shrapnel_capsule");
		nuclear_capsule = new ItemBase("nuclear_capsule");
		electromagnetic_capsule = new ItemBase("electromagnetic_capsule");
		
		steel_ingot = new ItemBase("steel_ingot");
		steel_plate = new ItemBase("steel_plate");
		
		cement = new ItemBase("cement");
		wet_cement = new ItemBase("wet_cement");
		
		lightning_rod = new ItemBase("lightning_rod");
		iron_frame = new ItemBase("iron_frame");
		invar_frame = new ItemBase("invar_frame");
		circuit = new ItemTiered("circuit", 3);
		fuel_canister = new ItemTiered("fuel_canister", 3);
		motor = new ItemBase("motor");
		
		missile = new ItemMissile();
		nail_gun = new ItemNailGun();
		compressor = new ItemCompressor();
		
		nail = new ItemBase("nail");
		shell = new ItemBase("shell");
		bullet = new ItemBase("bullet");
		nail_gun_magazine = new ItemBase("nail_gun_magazine");
		
		location_linker = new ItemLocationLinker();
		
		shotgun = new ItemGun("shotgun");
		
		steel = new ToolSet("steel", InitToolMaterials.toolMaterialSteel, steel_ingot, true);
		
		OreDictionary.registerOre("ingotSteel", steel_ingot);
	}
}
