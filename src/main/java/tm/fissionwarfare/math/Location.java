package tm.fissionwarfare.math;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class Location {

	public World world;
	public int x, y, z;

	public Location(World world, int x, int y, int z) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Location(World world, Vector3d vector) {
		this.world = world;
		this.x = MathHelper.floor_double(vector.x);
		this.y = MathHelper.floor_double(vector.y);
		this.z = MathHelper.floor_double(vector.z);
	}

	public double getDistance(Location location) {
		
		int dx = x - location.x;
		int dy = y - location.y;
		int dz = z - location.z;
		
		return Math.sqrt((dx * dx) + (dy * dy) + (dz * dz));
	}
	
	public Block getBlock() {
		return world.getBlock(x, y, z);
	}

	public int getMetadata() {
		return world.getBlockMetadata(x, y, z);
	}

	public float getBlockResistance(double explosionX, double explosionY, double explosionZ) {
		return getBlock().getExplosionResistance(null, world, x, y, z, explosionX, explosionY, explosionZ);
	}
	
	public void setBlockMetadata(int meta) {
		world.setBlockMetadataWithNotify(x, y, z, meta, 3);
	}
	
	public void setBlock(Block block) {
		world.setBlock(x, y, z, block);
	}
	
	public void setBlockToAir() {
		setBlock(Blocks.air);
	}
	
	public boolean compare(Block block) {
		return getBlock() == block;
	}
	
	public boolean compare(Block... blocks) {
		for (Block block : blocks) {
			if (compare(block)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean matches(Location loc) {
		return loc.x == x && loc.y == y && loc.z == z && loc.world.provider.dimensionId == world.provider.dimensionId;
	}
}
