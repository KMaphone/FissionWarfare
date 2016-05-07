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
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.gunpowder, 2), new Object[] { (new ItemStack(Items.coal, 1, 1)), TFItems.dustSulfur });
		GameRegistry.addShapelessRecipe(new ItemStack(Items.gunpowder, 2), new Object[] { Items.coal, TFItems.dustSulfur });
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.quartz_sand), new Object[] { Blocks.sand, InitItems.quartz });
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.cement), new Object[] { Blocks.sand, Blocks.gravel });
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.cementT2), new Object[] { Items.clay_ball, InitItems.limestone_chunk });
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.cementT3), new Object[] { InitItems.quartz_sand, TEItems.slagRich });
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.cementT3), new Object[] { InitItems.quartz_sand, TEItems.slag });

		addFrameRecipe(InitItems.frame, "ingotIron");
		addFrameRecipe(InitItems.frame_T2, "ingotSteel");
		addFrameRecipe(InitItems.frame_T3, "ingotInvar");
	}

	private static void addFrameRecipe(Item output, String oreIngot) {

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(output, 16), new Object[] {"I I", " I ", "I I", 'I', oreIngot}));
	}
}
