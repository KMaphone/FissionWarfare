package tm.fissionwarfare.init;

import cofh.thermalfoundation.item.TFItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import tm.fissionwarfare.block.BlockBase;
import tm.fissionwarfare.block.BlockConcrete;
import tm.fissionwarfare.block.BlockExplosive;
import tm.fissionwarfare.block.BlockOreBase;
import tm.fissionwarfare.explosion.type.BasicExplosion;
import tm.fissionwarfare.explosion.type.FireExplosion;
import tm.fissionwarfare.explosion.type.ShrapnelExplosion;

public class InitBlocks {

	public static Block quartz_ore, sulfur_ore, uranium_ore;
	
	public static Block basicExplosive;
	public static Block fireExplosive;
	public static Block shrapnelExplosive;
	
	public static Block concrete;
	public static Block limestone;
	
	public static void init() {
			
		quartz_ore = new BlockOreBase("quartz").setDroppedItem(new ItemStack(InitItems.quartz), 1, 2, 1, 2);
		sulfur_ore = new BlockOreBase("sulfur").setDroppedItem(TFItems.dustSulfur, 1, 2, 2, 4);		
		uranium_ore = new BlockOreBase("uranium").setLightLevel(0.3F);
		
		limestone = new BlockBase("limestone" , Material.rock, 1, 3.0F, 3.0F, Block.soundTypeStone);
		
		basicExplosive = new BlockExplosive("basic", new BasicExplosion());
		fireExplosive = new BlockExplosive("fire", new FireExplosion());
		shrapnelExplosive = new BlockExplosive("shrapnel", new ShrapnelExplosion());
		
		concrete = new BlockConcrete();		
	}	
}