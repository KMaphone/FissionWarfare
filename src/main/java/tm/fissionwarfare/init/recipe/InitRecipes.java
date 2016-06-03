package tm.fissionwarfare.init.recipe;

import cofh.thermalexpansion.block.TEBlocks;
import cofh.thermalexpansion.item.TEItems;
import cofh.thermalfoundation.item.TFItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.init.InitItems;

public class InitRecipes {

	public static void init() {
		
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.quartz, 1), new Object[] { InitItems.quartz_chunk, InitItems.quartz_chunk, InitItems.quartz_chunk, InitItems.quartz_chunk});
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.quartz_sand), new Object[] { Blocks.sand, InitItems.quartz });
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.cement), new Object[] { Blocks.sand, Blocks.gravel });
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.shell, 8), new Object[] {
			" C ", "C C", "C C", 'C', TFItems.ingotCopper
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.bullet, 16), new Object[] {
			" S ", "SLS", "SLS", 'S', InitItems.shell, 'L', TFItems.ingotLead
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.lightning_rod, 2), new Object[] {
			" L ", "LBL", " L ", 'B', Items.blaze_rod, 'L', TFItems.ingotLumium
		}));
					
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.iron_frame, 16), new Object[] {
			"I I", " I ", "I I", 'I', "ingotIron"
		}));
	
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.gunpowder, 1), new Object[] {
			"SSS", "SCS", "SSS", 'S', TFItems.dustSulfur, 'C', Items.coal
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.gunpowder, 1), new Object[] {
			"SSS", "SCS", "SSS", 'S', TFItems.dustSulfur, 'C', new ItemStack(Items.coal, 1, 1)
		}));
		
		/////////////////////////////////////////////////////////CIRCUIT\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.circuit, 1), new Object[] {
			"GGG", "ESE", "GGG", 'G', InitBlocks.reinforced_glass, 'E', TFItems.ingotElectrum, 'S', TEItems.powerCoilSilver
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.circuit, 1, 1), new Object[] {
			"HHH", "SCS", "HHH", 'H', TEBlocks.blockGlass, 'S', TFItems.ingotSignalum, 'C', InitItems.circuit
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.circuit, 1, 2), new Object[] {
			"LLL", "DCD", "LLL", 'L', new ItemStack (TEBlocks.blockGlass, 1, 1), 'D', Items.diamond, 'C', new ItemStack(InitItems.circuit, 1, 1)
		}));
		
		/////////////////////////////////////////////////////////CANISTER\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.fuel_canister, 1, 0), new Object[] {
			" S ", "SBS", "SBS", 'S', InitItems.steel_ingot, 'B', TFItems.bucketCoal,
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.fuel_canister, 1, 1), new Object[] {
			" T ", "TBT", "TBT", 'T', TFItems.ingotBronze, 'B', TFItems.bucketPyrotheum,
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.fuel_canister, 1, 2), new Object[] {
			" T ", "TBT", "TBT", 'T', TFItems.ingotSilver, 'B', TFItems.bucketCryotheum,
		}));
		
		/////////////////////////////////////////////////////////CAPSULE\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.fiery_capsule, 1), new Object[] {
			"LBL", "BMB", "LBL", 'L', Items.lava_bucket, 'B', Items.blaze_powder, 'M', Items.magma_cream
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.poison_capsule, 1), new Object[] {
			"RRR", "FPF", "RRR", 'R', Items.rotten_flesh, 'F', Items.fermented_spider_eye, 'P', new ItemStack(Items.fish, 1, 3)
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.shrapnel_capsule, 1), new Object[] {
			"AAA", "AIA", "AAA", 'I', Items.iron_axe, 'A', Items.arrow,
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.nuclear_capsule, 1), new Object[] {
			"EEE", "UUU", "EEE", 'U', InitItems.uranium_pellet, 'E', TFItems.ingotEnderium,
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.electromagnetic_capsule, 1), new Object[] {
			"SSS", "SLS", "CPC", 'L', new ItemStack(InitItems.lightning_rod), 'C', TEItems.capacitorHardened, 'P', TEItems.powerCoilElectrum, 'S', new ItemStack(InitItems.steel_plate)
		}));
		
		////////////////////////////////////////////////////////EXPLOSIVES\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.atomicExplosive, 1), new Object[] {
			"SRS", "BNB", "SRS", 'R', TFItems.bucketEnder, 'N', InitItems.nuclear_capsule, 'B', new ItemStack(InitBlocks.basicExplosive, 1), 'S', new ItemStack(InitItems.steel_plate)
		}));
		
		/*GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.empExplosive, 1), new Object[] {
			"ICI", "BEB", "ICI", 'C', new ItemStack(InitItems.circuit, 1, 1), 'E', InitItems.electromagnetic_capsule, 'B', new ItemStack(InitBlocks.basicExplosive, 1), 'I', new ItemStack(InitItems.frame, 1, 2)
		}));*/
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.pyroExplosive, 1), new Object[] {
			"SPS", "PFP", "SPS", 'P', TFItems.bucketPyrotheum, 'F', InitItems.fiery_capsule, 'B', new ItemStack(InitBlocks.basicExplosive, 1), 'S', new ItemStack(InitItems.steel_plate)
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.chemicalExplosive, 1), new Object[] {
			"SBS", "EPE", "SBS", 'B', TFItems.bucketPetrotheum, 'P', InitItems.poison_capsule, 'E', new ItemStack(InitBlocks.basicExplosive, 1), 'S', new ItemStack(InitItems.steel_plate)
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.basicExplosive, 1), new Object[] {
			"SES", "RRR", "SES", 'S', InitItems.steel_plate, 'E', Blocks.tnt, 'R', TEItems.powerCoilSilver
		}));
		
		/////////////////////////////////////////////////////////MACHINES\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.sentry_turret, 1), new Object[] {
			"R  ", "MCD", "MGM", 'M', new ItemStack(TEBlocks.blockFrame, 1, 2), 'C', new ItemStack(InitItems.circuit, 1, 2), 'G', TFItems.gearGold, 'D', Blocks.dispenser, 'R', Blocks.redstone_torch
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.missile_factory, 1), new Object[] {
			"MGM", "DCD", "MGM", 'M', new ItemStack(TEBlocks.blockFrame, 1, 1), 'C', new ItemStack(InitItems.circuit, 1, 0), 'G', TFItems.gearGold, 'D', new ItemStack(TEBlocks.blockWorkbench, 1, 1)
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.launch_pad, 1), new Object[] {
			"GGG", "MPM", "MCM", 'M', new ItemStack(TEBlocks.blockFrame, 1, 2), 'C', new ItemStack(InitItems.circuit, 1, 1), 'G', new ItemStack(InitItems.steel_plate), 'P', Blocks.piston
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.support_frame, 1), new Object[] {
			" BB", " BB", " BB", 'B', new ItemStack(InitBlocks.steel_block, 1)
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.steel_block, 1), new Object[] {
			"BBB", "BBB", "BBB", 'B', new ItemStack(InitItems.steel_ingot, 1)
		}));
	}
}
