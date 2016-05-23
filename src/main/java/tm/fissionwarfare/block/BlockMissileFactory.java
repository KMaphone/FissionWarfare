package tm.fissionwarfare.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import tm.fissionwarfare.tileentity.machine.TileEntityMissileFactory;

public class BlockMissileFactory extends BlockContainerBase {

	public BlockMissileFactory() {
		super("missile_factory", 2, Material.iron, 2, 2, Block.soundTypeMetal);
	}

	@Override
	public TileEntity getTileEntity(int meta) {
		return new TileEntityMissileFactory();
	}
}
