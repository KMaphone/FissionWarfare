package tm.fissionwarfare.init.recipe;

import cofh.api.modhelpers.ThermalExpansionHelper;
import cofh.thermalexpansion.core.TEAchievements;
import cofh.thermalexpansion.item.TEItems;
import cofh.thermalfoundation.item.TFItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.init.InitItems;

public class InitRecipes {

	public static void init() {
		
		
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.cement), new Object[] { Blocks.sand, Blocks.gravel });
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.cementT2), new Object[] { Items.clay_ball, TFItems.dustIron });
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.cementT3), new Object[] { InitBlocks.limestone, TEItems.slagRich });
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.cementT3), new Object[] { InitBlocks.limestone, TEItems.slag });

		addFrameRecipe(InitItems.iron_frame, "ingotIron");
		addFrameRecipe(InitItems.steel_frame, "ingotSteel");
		addFrameRecipe(InitItems.invar_frame, "ingotInvar");
	}

	private static void addFrameRecipe(Item output, String oreIngot) {

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(output, 16), new Object[] {"I I", " I ", "I I", 'I', oreIngot}));
	}
}
