package tm.fissionwarfare.init;

import cofh.thermalfoundation.item.TFItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import tm.fissionwarfare.block.BlockBase;
import tm.fissionwarfare.block.BlockConcrete;
import tm.fissionwarfare.block.BlockExplosive;
import tm.fissionwarfare.block.BlockOreBase;
import tm.fissionwarfare.block.BlockReinforcedGlass;
import tm.fissionwarfare.block.BlockTest;
import tm.fissionwarfare.block.BlockTurret;
import tm.fissionwarfare.explosion.type.BasicExplosion;
import tm.fissionwarfare.explosion.type.FireExplosion;
import tm.fissionwarfare.explosion.type.ShrapnelExplosion;

public class InitBlocks {

	public static Block quartz_ore, sulfur_ore, limestone_ore, uranium_ore;
	
	
	public static Block basicExplosive;
	public static Block fireExplosive;
	public static Block shrapnelExplosive;
	
	public static Block concrete;
	public static Block reinforced_glass;
	
	public static Block turret;	
	public static Block test_block;
	
	public static void init() {
		
		limestone_ore = new BlockOreBase("limestone").setDroppedItem(new ItemStack(InitItems.limestone_chunk), 1, 2, 1, 2);
		quartz_ore = new BlockOreBase("quartz").setDroppedItem(new ItemStack(InitItems.quartz), 1, 2, 1, 2);
		sulfur_ore = new BlockOreBase("sulfur").setDroppedItem(TFItems.dustSulfur, 1, 2, 2, 4);		
		uranium_ore = new BlockOreBase("uranium").setLightLevel(0.3F);
		
		
		basicExplosive = new BlockExplosive("basic", new BasicExplosion());
		fireExplosive = new BlockExplosive("fire", new FireExplosion());
		shrapnelExplosive = new BlockExplosive("shrapnel", new ShrapnelExplosion());
		
		concrete = new BlockConcrete();		
		reinforced_glass = new BlockReinforcedGlass();
		
		turret = new BlockTurret();
		test_block = new BlockTest();
	}	
}