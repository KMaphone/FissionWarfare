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
		GameRegistry.addShapelessRecipe(new ItemStack(Items.gunpowder, 2), new Object[] { new ItemStack(Items.coal, 1), TFItems.dustSulfur, TFItems.dustSulfur, TFItems.dustSulfur  });
		GameRegistry.addShapelessRecipe(new ItemStack(Items.gunpowder, 2), new Object[] { Items.coal, TFItems.dustSulfur, TFItems.dustSulfur, TFItems.dustSulfur });
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.quartz_sand), new Object[] { Blocks.sand, InitItems.quartz });
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.cement, 1, 0), new Object[] { Blocks.sand, Blocks.gravel });
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.cement, 1, 1), new Object[] { Items.clay_ball, InitItems.limestone_chunk });
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.cement, 1, 2), new Object[] { InitItems.quartz_sand, TEItems.slagRich });
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.cement, 1, 2), new Object[] { InitItems.quartz_sand, TEItems.slag });
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.shell, 8), new Object[] {
			" C ", "C C", "C C", 'C', TFItems.ingotCopper
		}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.bullet, 16), new Object[] {
			" S ", "SLS", "SLS", 'S', InitItems.shell, 'L', TFItems.ingotLead
		}));
		
		/////////////////////////////////////////////////////////CIRCUIT\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.circuit, 1), new Object[] {
			"GGG", "ESE", "GGG", 'G', Blocks.glass, 'E', TFItems.ingotElectrum, 'S', TEItems.powerCoilSilver
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.circuit, 1, 1), new Object[] {
			"HHH", "SCS", "HHH", 'H', TEBlocks.blockGlass, 'S', TFItems.ingotSignalum, 'C', InitItems.circuit
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.circuit, 1, 2), new Object[] {
			"LLL", "DCD", "LLL", 'L', new ItemStack (TEBlocks.blockGlass, 1, 1), 'D', Items.diamond, 'C', new ItemStack(InitItems.circuit, 1, 1)
		}));
		
		/////////////////////////////////////////////////////////CAPSULE\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.fiery_capsule, 1), new Object[] {
			"LBL", "BMB", "LBL", 'L', Items.lava_bucket, 'B', Items.blaze_powder, 'M', Items.magma_cream
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.poison_capsule, 1), new Object[] {
			"RRR", "FPF", "RRR", 'R', Items.rotten_flesh, 'F', Items.fermented_spider_eye, 'P', new ItemStack(Items.fish, 3)
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.shrapnel_capsule, 1), new Object[] {
			"AAA", "AIA", "AAA", 'I', Items.iron_axe, 'A', Items.arrow,
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.nuclear_capsule, 1), new Object[] {
			"EEE", "UUU", "EEE", 'U', InitItems.uranium_chunk, 'E', TFItems.ingotEnderium,
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.electromagnetic_capsule, 1), new Object[] {
			"RRR", "CEC", "RRR", 'E', new ItemStack (TEBlocks.blockFrame, 1, 7), 'C', TEItems.capacitorReinforced, 'R', TEItems.powerCoilElectrum
		}));
		
		////////////////////////////////////////////////////////EXPLOSIVES\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.atomicExplosive, 1), new Object[] {
			"IRI", "BNB", "IRI", 'R', TFItems.bucketEnder, 'N', InitItems.nuclear_capsule, 'B', new ItemStack(InitBlocks.basicExplosive, 1), 'I', new ItemStack(InitItems.frame, 1, 2)
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.empExplosive, 1), new Object[] {
			"ICI", "BEB", "ICI", 'C', new ItemStack(InitItems.circuit, 1, 1), 'E', InitItems.electromagnetic_capsule, 'B', new ItemStack(InitBlocks.basicExplosive, 1), 'I', new ItemStack(InitItems.frame, 1, 2)
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.pyroExplosive, 1), new Object[] {
			"IPI", "BFB", "IPI", 'P', TFItems.bucketPyrotheum, 'F', InitItems.fiery_capsule, 'B', new ItemStack(InitBlocks.basicExplosive, 1), 'I', new ItemStack(InitItems.frame, 1, 2)
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.chemicalExplosive, 1), new Object[] {
			"IBI", "EPE", "IBI", 'B', TFItems.bucketPetrotheum, 'P', InitItems.poison_capsule, 'E', new ItemStack(InitBlocks.basicExplosive, 1), 'I', new ItemStack(InitItems.frame, 1, 2)
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.basicExplosive, 1), new Object[] {
			"FEF", "RRR", "FEF", 'F', InitItems.frame, 'E', Blocks.tnt, 'R', TEItems.powerCoilSilver
		}));
		
		
		
		addFrameRecipe(new ItemStack(InitItems.frame, 0, 16), "ingotIron");
		addFrameRecipe(new ItemStack(InitItems.frame, 1, 16), "ingotSteel");
		addFrameRecipe(new ItemStack(InitItems.frame, 2, 16), "ingotInvar");
	}

	private static void addFrameRecipe(ItemStack output, String oreIngot) {
		GameRegistry.addRecipe(new ShapedOreRecipe(output, new Object[] {"I I", " I ", "I I", 'I', oreIngot}));
	}
}
