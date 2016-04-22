package tm.fissionwarfare.init;

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

public class InitRecipes {

	public static void init() {

		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.cement), new Object[] { Blocks.sand, Blocks.gravel });

		ThermalExpansionHelper.addTransposerFill(1000, new ItemStack(InitItems.cement),	new ItemStack(InitItems.wet_cement), new FluidStack(FluidRegistry.WATER, 1000), false);
		ThermalExpansionHelper.addCrucibleRecipe(8000, new ItemStack(InitItems.wet_cement),	new FluidStack(InitFluids.concrete_mix, 1000));

		ThermalExpansionHelper.addTransposerFill(1000, new ItemStack(InitItems.frame_iron), new ItemStack(Blocks.stone), new FluidStack(InitFluids.concrete_mix, 1000), false);

		ThermalExpansionHelper.addTransposerFill(2000, new ItemStack(InitItems.frame_steel), new ItemStack(Blocks.stone), new FluidStack(InitFluids.concrete_mix, 2000), false);

		ThermalExpansionHelper.addTransposerFill(4000, new ItemStack(InitItems.frame_invar), new ItemStack(Blocks.stone), new FluidStack(InitFluids.concrete_mix, 4000), false);

		ThermalExpansionHelper.addSmelterRecipe(1000, new ItemStack(Items.iron_ingot), new ItemStack(Items.coal), new ItemStack(InitItems.steel_ingot));

		addFrameRecipe(InitItems.frame_iron, new ItemStack(Items.iron_ingot));
		addFrameRecipe(InitItems.frame_steel, new ItemStack(InitItems.steel_ingot));
		addFrameRecipe(InitItems.frame_invar, TFItems.ingotInvar);
	}

	private static void addFrameRecipe(Item output, ItemStack stack) {

		GameRegistry.addShapedRecipe(new ItemStack(output, 16), new Object[] { "I I", " I ", "I I", 'I', stack });
	}
}
