package tm.fissionwarfare.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class OreGenProfileSingle extends OreGenProfile {

	public OreGenProfileSingle(int minY, int maxY, int amount, Block ore) {
		super(minY, maxY, amount, 1, ore);
	}

	@Override
	public void generate(World world, Random rand, int chunkX, int chunkZ) {
		
		for (int i = 0; i < amount; i++) {

			int randPosX = chunkX + rand.nextInt(16);
			int randPosY = rand.nextInt(maxY - minY) + minY;
			int randPosZ = chunkZ + rand.nextInt(16);

			if (world.getBlock(randPosX, randPosY, randPosZ) == Blocks.stone) world.setBlock(randPosX, randPosY, randPosZ, ore);
		}		
	}
}
