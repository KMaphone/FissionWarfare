package tm.fissionwarfare.explosion;

import java.util.List;

import net.minecraft.block.Block;
import tm.fissionwarfare.api.IConcreteBlock;
import tm.fissionwarfare.math.Location;
import tm.fissionwarfare.math.ShapeUtil;
import tm.fissionwarfare.sounds.FWSound;

public class ConcreteUtil {

	public static void generateShockwave(Location location, int r, int damage) {
		
		List<Location> list = ShapeUtil.getSphere(location, r);
		
		for (Location loc : list) {
			breakConcrete(loc, damage);
		}
	}
	
	public static void breakConcrete(Location location, int damage) {
		
		Block block = location.getBlock();
		int currentMeta = location.getMetadata();
		int newMeta = currentMeta - damage;
		
		if (block instanceof IConcreteBlock) {
			
			IConcreteBlock concrete = (IConcreteBlock) block;
			
			if (newMeta < 0) {
				
				location.setBlockToAir();
			}
			
			location.setBlockMetadata(newMeta);
		}
	}
}
