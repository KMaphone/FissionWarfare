package tm.fissionwarfare.fluid;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidConcrete extends Fluid {

	public int tier;
	
	public FluidConcrete(int tier) {
		super("concrete_mix_T" + tier);
		this.tier = tier;
		FluidRegistry.registerFluid(this);
		setDensity(5000);
		setViscosity(8000);
	}
}
