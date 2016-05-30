package tm.fissionwarfare.init;

import cpw.mods.fml.common.registry.GameRegistry;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.tileentity.machine.TileEntityMissileFactory;
import tm.fissionwarfare.tileentity.machine.TileEntityTurretSentry;

public class InitTileEntities {

	public static void init() {
		
		GameRegistry.registerTileEntity(TileEntityMissileFactory.class, Reference.MOD_ID + ":tileMissileFactory");
		GameRegistry.registerTileEntity(TileEntityTurretSentry.class, Reference.MOD_ID + ":tileEntityTurret");
	}
}
