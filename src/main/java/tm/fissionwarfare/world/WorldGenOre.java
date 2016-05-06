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
	
	private OreGenProfile sulfurGen = new OreGenProfile(5, 20, 20, 8, InitBlocks.sulfur_ore);
	private OreGenProfile quartzGen = new OreGenProfile(20, 60, 20, 8, InitBlocks.quartz_ore);
	private OreGenProfile uraniumGen = new OreGenProfileSingle(5, 50, 5, InitBlocks.uranium_ore);
	private OreGenProfile limestoneGen = new OreGenProfile(40, 70, 15, 10, InitBlocks.limestone_ore);
	
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		if (world.provider.dimensionId == 0) {
			generateSurface(world, rand, chunkX * 16, chunkZ * 16);
		}
	}

	private void generateSurface(World world, Random rand, int chunkX, int chunkZ) {
				
		sulfurGen.generate(world, rand, chunkX, chunkZ);
		quartzGen.generate(world, rand, chunkX, chunkZ);
		limestoneGen.generate(world, rand, chunkX, chunkZ);
		uraniumGen.generate(world, rand, chunkX, chunkZ);
	}
}
