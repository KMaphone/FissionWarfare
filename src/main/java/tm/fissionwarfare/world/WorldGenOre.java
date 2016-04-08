package tm.fissionwarfare.world;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import tm.fissionwarfare.init.InitBlocks;

public class WorldGenOre implements IWorldGenerator {
	
	private OreGenOption uraniumGen = new OreGenOption(10, 50, 5, 1, InitBlocks.uranium_ore);
	private OreGenOption sulfurGen = new OreGenOption(0, 20, 20, 8, InitBlocks.sulfur_ore);
	
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		if (world.provider.dimensionId == 0) {
			generateSurface(world, rand, chunkX * 16, chunkZ * 16);
		}
	}

	private void generateSurface(World world, Random rand, int chunkX, int chunkZ) {
		
		uraniumGen.generate(world, rand, chunkX, chunkZ);
		sulfurGen.generate(world, rand, chunkX, chunkZ);
	}
}
