package tm.fissionwarfare.itemblock;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.tileentity.machine.TileEntityLaunchPad;
import tm.fissionwarfare.util.math.Location;

public class ItemControlPanel extends ItemBlockBase {

	public ItemControlPanel() {
		super("control_panel");
	}

	@Override
	public boolean placeBlocks(World world, int hitX, int hitY, int hitZ, int x, int y, int z) {
		
		Block block = InitBlocks.control_panel;
		
		Location hitLoc = new Location(world, hitX, hitY, hitZ);
		
		if (hitLoc.getBlock() != null && hitLoc.hasTileEntity() && hitLoc.getTileEntity() instanceof TileEntityLaunchPad) {
						
			Location loc = hitLoc.add(hitLoc.getMetadata(), false);
			
			if (block.canPlaceBlockAt(loc.world, loc.x, loc.y, loc.z)) {
				
				loc.setBlock(block);
				loc.setBlockMetadata(hitLoc.getMetadata());
				playSound(world, loc.x, loc.y, loc.z, block);
				return true;
			}			
		}
		
		return false;
	}
}
