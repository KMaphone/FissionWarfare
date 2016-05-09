package tm.fissionwarfare.init;

import net.minecraft.block.Block;
import tm.fissionwarfare.block.BlockFluidConcrete;
import tm.fissionwarfare.fluid.FluidConcrete;

public class InitFluids {

	public static FluidConcrete concrete_mix_T1;
	public static FluidConcrete concrete_mix_T2;
	public static FluidConcrete concrete_mix_T3;
	
	public static Block block_concrete_mix_T1;
	public static Block block_concrete_mix_T2;
	public static Block block_concrete_mix_T3;
	
	public static void init() {
		
		concrete_mix_T1 = new FluidConcrete(1);
		concrete_mix_T2 = new FluidConcrete(2);
		concrete_mix_T3 = new FluidConcrete(3);
		
		block_concrete_mix_T1 = new BlockFluidConcrete(concrete_mix_T1);
		block_concrete_mix_T2 = new BlockFluidConcrete(concrete_mix_T2);
		block_concrete_mix_T3 = new BlockFluidConcrete(concrete_mix_T3);
	}
}
