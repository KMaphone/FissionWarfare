package tm.fissionwarfare.init;

import cofh.api.modhelpers.ThermalExpansionHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class InitRecipes {
	
	public static void init() {
		
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.cement), new Object[]{Blocks.sand, Blocks.gravel});
		
		ThermalExpansionHelper.addTransposerFill(1000, new ItemStack(InitItems.cement), new ItemStack(InitItems.wet_cement), new FluidStack(FluidRegistry.WATER, 1000), false);
		ThermalExpansionHelper.addCrucibleRecipe(8000, new ItemStack(InitItems.wet_cement), new FluidStack(InitFluids.concrete_mix, 1000));
		
		ThermalExpansionHelper.addTransposerFill(1000,
				new ItemStack(InitItems.frame_iron),
				new ItemStack(Blocks.stone),
				new FluidStack(InitFluids.concrete_mix, 1000),
				false);
		
		ThermalExpansionHelper.addTransposerFill(2000,
				new ItemStack(InitItems.frame_steel),
				new ItemStack(Blocks.stone),
				new FluidStack(InitFluids.concrete_mix, 2000),
				false);
		
		ThermalExpansionHelper.addTransposerFill(4000,
				new ItemStack(InitItems.frame_invar),
				new ItemStack(Blocks.stone),
				new FluidStack(InitFluids.concrete_mix, 4000),
				false);
	}
}
