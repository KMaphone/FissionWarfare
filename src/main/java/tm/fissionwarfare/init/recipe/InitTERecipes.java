package tm.fissionwarfare.init.recipe;

import cofh.api.modhelpers.ThermalExpansionHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.init.InitFluids;
import tm.fissionwarfare.init.InitItems;

public class InitTERecipes {

	public static void init() {
		
		ThermalExpansionHelper.addCrucibleRecipe(8000, new ItemStack(InitItems.cement, 1, 3), new FluidStack(InitFluids.concrete_mix_T1, 1000));		
		ThermalExpansionHelper.addCrucibleRecipe(8000, new ItemStack(InitItems.cement, 1, 4), new FluidStack(InitFluids.concrete_mix_T2, 1000));		
		ThermalExpansionHelper.addCrucibleRecipe(8000, new ItemStack(InitItems.cement, 1, 5), new FluidStack(InitFluids.concrete_mix_T3, 1000));

		ThermalExpansionHelper.addCrucibleRecipe(8000, new ItemStack(InitItems.quartz_sand), new FluidStack(InitFluids.liquid_glass, 1000));
		
		ThermalExpansionHelper.addTransposerFill(1000, new ItemStack(InitItems.cement),	new ItemStack(InitItems.cement, 1, 3), new FluidStack(FluidRegistry.WATER, 1000), false);
		ThermalExpansionHelper.addTransposerFill(1000, new ItemStack(InitItems.cement, 1, 1), new ItemStack(InitItems.cement, 1, 4), new FluidStack(FluidRegistry.WATER, 1000), false);
		ThermalExpansionHelper.addTransposerFill(1000, new ItemStack(InitItems.cement, 1, 2), new ItemStack(InitItems.cement, 1, 5), new FluidStack(FluidRegistry.WATER, 1000), false);
		
		ThermalExpansionHelper.addTransposerFill(1000, new ItemStack(InitItems.frame), new ItemStack(InitBlocks.concrete, 1, 4), new FluidStack(InitFluids.concrete_mix_T1, 1000), false);
		ThermalExpansionHelper.addTransposerFill(2000, new ItemStack(InitItems.frame, 1, 1), new ItemStack(InitBlocks.concrete, 1, 9), new FluidStack(InitFluids.concrete_mix_T2, 2000), false);
		ThermalExpansionHelper.addTransposerFill(4000, new ItemStack(InitItems.frame, 1, 2), new ItemStack(InitBlocks.concrete, 1, 14), new FluidStack(InitFluids.concrete_mix_T3, 4000), false);
		
		ThermalExpansionHelper.addTransposerFill(1000, new ItemStack(InitItems.frame), new ItemStack(InitBlocks.reinforced_glass, 1, 4), new FluidStack(InitFluids.liquid_glass, 1000), false);
	}
}
