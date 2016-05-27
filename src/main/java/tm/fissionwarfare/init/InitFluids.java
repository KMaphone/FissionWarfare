package tm.fissionwarfare.init;

import net.minecraft.block.Block;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import tm.fissionwarfare.block.BlockFluidBase;
import tm.fissionwarfare.fluid.FluidConcrete;

public class InitFluids {

	public static Fluid concrete_mix_T1;
	public static Fluid concrete_mix_T2;
	public static Fluid concrete_mix_T3;
	
	public static Fluid liquid_glass;
	
	public static Block block_concrete_mix_T1;
	public static Block block_concrete_mix_T2;
	public static Block block_concrete_mix_T3;
	
	public static Block block_liquid_glass;
	
	public static void init() {
		
		concrete_mix_T1 = new FluidConcrete(1);
		concrete_mix_T2 = new FluidConcrete(2);
		concrete_mix_T3 = new FluidConcrete(3);
		
		liquid_glass = new Fluid("liquid_glass");
		FluidRegistry.registerFluid(liquid_glass);
		
		block_concrete_mix_T1 = new BlockFluidBase(concrete_mix_T1);
		block_concrete_mix_T2 = new BlockFluidBase(concrete_mix_T2);
		block_concrete_mix_T3 = new BlockFluidBase(concrete_mix_T3);
		
		block_liquid_glass = new BlockFluidBase(liquid_glass);
	}
}
