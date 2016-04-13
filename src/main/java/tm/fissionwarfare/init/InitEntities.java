package tm.fissionwarfare.init;

import cpw.mods.fml.common.registry.EntityRegistry;
import tm.fissionwarfare.FissionWarfare;
import tm.fissionwarfare.entity.EntityExplosive;

public class InitEntities {

	private static int nextEntityId = 5;
	
	public static void init() {
		EntityRegistry.registerModEntity(EntityExplosive.class, "explosive", nextEntityId++, FissionWarfare.instance, 64, 1, true);
	}
}
