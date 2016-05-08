package tm.fissionwarfare.init.recipe;

import cofh.api.modhelpers.ThermalExpansionHelper;
import cofh.thermalexpansion.block.TEBlocks;
import cofh.thermalexpansion.core.TEAchievements;
import cofh.thermalexpansion.item.TEItems;
import cofh.thermalfoundation.item.TFItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.init.InitItems;

public class InitRecipes {

	public static void init() {
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.gunpowder, 2), new Object[] { new ItemStack(Items.coal, 1), TFItems.dustSulfur, TFItems.dustSulfur, TFItems.dustSulfur  });
		GameRegistry.addShapelessRecipe(new ItemStack(Items.gunpowder, 2), new Object[] { Items.coal, TFItems.dustSulfur, TFItems.dustSulfur, TFItems.dustSulfur });
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.quartz_sand), new Object[] { Blocks.sand, InitItems.quartz });
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.cement), new Object[] { Blocks.sand, Blocks.gravel });
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.cementT2), new Object[] { Items.clay_ball, InitItems.limestone_chunk });
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.cementT3), new Object[] { InitItems.quartz_sand, TEItems.slagRich });
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.cementT3), new Object[] { InitItems.quartz_sand, TEItems.slag });
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.basicExplosive, 1), new Object[] {
				"FEF", "RRR", "FEF", 'F', InitItems.frame, 'E', Blocks.tnt, 'R', TEItems.powerCoilSilver
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.circuit, 1), new Object[] {
				"GGG", "ESE", "GGG", 'G', Blocks.glass, 'E', TFItems.ingotElectrum, 'S', TEItems.powerCoilSilver
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.circuit_T2, 1), new Object[] {
				"HHH", "SCS", "HHH", 'H', TEBlocks.blockGlass, 'S', TFItems.ingotSignalum, 'C', InitItems.circuit
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.circuit_T3, 1), new Object[] {
				"LLL", "DCD", "LLL", 'L', new ItemStack (TEBlocks.blockGlass, 1, 1), 'D', Items.diamond, 'C', InitItems.circuit_T2
			}));



		addFrameRecipe(InitItems.frame, "ingotIron");
		addFrameRecipe(InitItems.frame_T2, "ingotSteel");
		addFrameRecipe(InitItems.frame_T3, "ingotInvar");
	}

	private static void addFrameRecipe(Item output, String oreIngot) {

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(output, 16), new Object[] {"I I", " I ", "I I", 'I', oreIngot}));
	}
}
