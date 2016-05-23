package tm.fissionwarfare.init;

import cofh.thermalfoundation.item.TFItems;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import tm.fissionwarfare.block.BlockConcrete;
import tm.fissionwarfare.block.BlockExplosive;
import tm.fissionwarfare.block.BlockMissileFactory;
import tm.fissionwarfare.block.BlockOreBase;
import tm.fissionwarfare.block.BlockReinforcedGlass;
import tm.fissionwarfare.block.BlockTest;
import tm.fissionwarfare.block.BlockTurret;
import tm.fissionwarfare.explosion.type.AtomicExplosion;
import tm.fissionwarfare.explosion.type.BasicExplosion;
import tm.fissionwarfare.explosion.type.ChemicalExplosion;
import tm.fissionwarfare.explosion.type.EMPExplosion;
import tm.fissionwarfare.explosion.type.EnumExplosionType;
import tm.fissionwarfare.explosion.type.PyroExplosion;

public class InitBlocks {

	public static Block quartz_ore, sulfur_ore, limestone_ore, uranium_ore;
	
	public static Block basicExplosive;
	//public static Block empExplosive;
	public static Block chemicalExplosive;
	public static Block atomicExplosive;
	public static Block pyroExplosive;
	
	public static Block concrete;
	public static Block reinforced_glass;
	
	public static Block missile_factory;
	
	public static Block turret;
	public static Block test_block;
	
	public static void init() {
		
		limestone_ore = new BlockOreBase("limestone").setDroppedItem(new ItemStack(InitItems.limestone_chunk), 1, 2, 1, 2);
		quartz_ore = new BlockOreBase("quartz").setDroppedItem(new ItemStack(InitItems.quartz_chunk), 1, 2, 1, 2);
		sulfur_ore = new BlockOreBase("sulfur").setDroppedItem(TFItems.dustSulfur, 1, 2, 2, 4).setRareDrop(TFItems.dustNiter);		
		uranium_ore = new BlockOreBase("uranium").setDroppedItem(new ItemStack(InitItems.uranium_chunk), 1, 2, 1, 2).setLightLevel(0.3F);
		
		basicExplosive = new BlockExplosive("basic", EnumExplosionType.BASIC);
		//empExplosive = new BlockExplosive("emp", EnumExplosionType.EMP);
		chemicalExplosive = new BlockExplosive("chemical", EnumExplosionType.CHEMICAL);
		atomicExplosive = new BlockExplosive("atomic", EnumExplosionType.ATOMIC);
		pyroExplosive = new BlockExplosive("pyro", EnumExplosionType.PYRO);
		
		missile_factory = new BlockMissileFactory();
		
		concrete = new BlockConcrete();		
		reinforced_glass = new BlockReinforcedGlass();
		
		turret = new BlockTurret();
		test_block = new BlockTest();
	}	
}