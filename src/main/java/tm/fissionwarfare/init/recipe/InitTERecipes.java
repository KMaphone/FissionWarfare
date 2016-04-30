package tm.fissionwarfare.init.recipe;

import cofh.api.modhelpers.ThermalExpansionHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.init.InitFluids;
import tm.fissionwarfare.init.InitItems;

public class InitTERecipes {

	public static void init() {
		
		ThermalExpansionHelper.addCrucibleRecipe(8000, new ItemStack(InitItems.cement, 1, 1),	new FluidStack(InitFluids.concrete_mix, 1000));

		ThermalExpansionHelper.addTransposerFill(1000, new ItemStack(InitItems.iron_frame), new ItemStack(InitBlocks.concrete, 1, 4), new FluidStack(InitFluids.concrete_mix, 1000), false);

		ThermalExpansionHelper.addTransposerFill(2000, new ItemStack(InitItems.steel_frame), new ItemStack(InitBlocks.concrete, 1, 9), new FluidStack(InitFluids.concrete_mix, 2000), false);

		ThermalExpansionHelper.addTransposerFill(4000, new ItemStack(InitItems.invar_frame), new ItemStack(InitBlocks.concrete, 1, 14), new FluidStack(InitFluids.concrete_mix, 4000), false);
	}
}