package tm.fissionwarfare.init;

import cofh.core.util.fluid.BucketHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import tm.fissionwarfare.block.BlockFluidBase;
import tm.fissionwarfare.item.ItemBase;

public class InitFluids {

	public static Fluid concrete_mix;
	public static Fluid concrete_mixT2;
	public static Block block_concrete_mix;
	
	public static void init() {
		
		concrete_mix = new Fluid("concrete_mix").setDensity(5000).setViscosity(8000);
		concrete_mixT2 = new Fluid("concrete_mixT2").setDensity(5000).setViscosity(8000);
		FluidRegistry.registerFluid(concrete_mix);
		FluidRegistry.registerFluid(concrete_mixT2);
		
		block_concrete_mix = new BlockFluidBase(concrete_mix, Material.water);
	}
}
