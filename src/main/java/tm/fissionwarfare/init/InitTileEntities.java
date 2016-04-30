package tm.fissionwarfare.init;

import cpw.mods.fml.common.registry.GameRegistry;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.tileentity.TileEntityTurret;

public class InitTileEntities {

	public static void init() {
		
		GameRegistry.registerTileEntity(TileEntityTurret.class, Reference.MOD_ID + ":tileEntityTurret");
	}
}
