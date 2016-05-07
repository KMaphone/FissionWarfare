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
		
		ThermalExpansionHelper.addCrucibleRecipe(8000, new ItemStack(InitItems.cementT2, 1, 1),	new FluidStack(InitFluids.concrete_mixT2, 1000));
		
		ThermalExpansionHelper.addCrucibleRecipe(8000, new ItemStack(InitItems.cementT3, 1, 1),	new FluidStack(InitFluids.concrete_mixT3, 1000));

		ThermalExpansionHelper.addTransposerFill(1000, new ItemStack(InitItems.frame), new ItemStack(InitBlocks.concrete, 1, 4), new FluidStack(InitFluids.concrete_mix, 1000), false);

		ThermalExpansionHelper.addTransposerFill(2000, new ItemStack(InitItems.frame_T2), new ItemStack(InitBlocks.concrete, 1, 9), new FluidStack(InitFluids.concrete_mixT2, 2000), false);

		ThermalExpansionHelper.addTransposerFill(4000, new ItemStack(InitItems.frame_T3), new ItemStack(InitBlocks.concrete, 1, 14), new FluidStack(InitFluids.concrete_mixT3, 4000), false);
	}
}
