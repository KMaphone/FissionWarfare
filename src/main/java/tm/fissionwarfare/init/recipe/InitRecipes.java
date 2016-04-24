package tm.fissionwarfare.init.recipe;

import cofh.api.modhelpers.ThermalExpansionHelper;
import cofh.thermalfoundation.item.TFItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import tm.fissionwarfare.init.InitItems;

public class InitRecipes {

	public static void init() {

		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.cement), new Object[] { Blocks.sand, Blocks.gravel });

		addFrameRecipe(InitItems.iron_frame, new ItemStack(Items.iron_ingot));
		addFrameRecipe(InitItems.steel_frame, new ItemStack(InitItems.steel_ingot));
		addFrameRecipe(InitItems.invar_frame, TFItems.ingotInvar);
	}

	private static void addFrameRecipe(Item output, ItemStack stack) {

		GameRegistry.addShapedRecipe(new ItemStack(output, 16), new Object[] { "I I", " I ", "I I", 'I', stack });
	}
}
