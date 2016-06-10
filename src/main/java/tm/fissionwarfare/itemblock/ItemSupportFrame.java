package tm.fissionwarfare.itemblock;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.tileentity.machine.TileEntityLaunchPad;
import tm.fissionwarfare.util.math.Location;

public class ItemSupportFrame extends ItemBlockBase {

	public ItemSupportFrame() {
		super("support_frame");
	}

	@Override
	public boolean placeBlocks(World world, int hitX, int hitY, int hitZ, int x, int y, int z) {
		
		Block block = InitBlocks.support_frame;
		
		Location hitLoc = new Location(world, hitX, hitY, hitZ);
		
		if (hitLoc.getBlock() != null && hitLoc.hasTileEntity() && hitLoc.getTileEntity() instanceof TileEntityLaunchPad) {
						
			Location loc = hitLoc.add(hitLoc.getMetadata(), true);
			
			if (canPlaceFrame(block, hitLoc.getMetadata(), loc)) {
				
				setFrames(block, hitLoc.getMetadata(), loc);
				playSound(world, loc.x, loc.y, loc.z, block);
				return true;
			}			
		}
		
		return false;
	}
	
	private boolean canPlaceFrame(Block block, int meta, Location loc) {
		
		for (int xzOffset = -1; xzOffset < 2; xzOffset++) {
			
			for (int yOffset = 0; yOffset < 7; yOffset++) {
				
				if (!block.canPlaceBlockAt(loc.world, loc.x + (shouldRotate(meta) ? xzOffset : 0), loc.y + yOffset, loc.z + (!shouldRotate(meta) ? xzOffset : 0))) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	private void setFrames(Block block, int meta, Location loc) {
				
		for (int xzOffset = -1; xzOffset < 2; xzOffset++) {
			
			for (int yOffset = 0; yOffset < 7; yOffset++) {
				
				int x = loc.x + (shouldRotate(meta) ? xzOffset : 0);
				int y = loc.y + yOffset;
				int z =loc.z + (!shouldRotate(meta) ? xzOffset : 0);
				
				loc.world.setBlock(x, y, z, block);
				loc.world.setBlockMetadataWithNotify(x, y, z, shouldRotate(meta) ? 1 : 0, 3);
			}
		}
	}
	
	public static boolean shouldRotate(int meta) {
		return (meta == 0 || meta == 2);
	}
}
