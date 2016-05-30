package tm.fissionwarfare.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;

public class BlockLaunchPad extends BlockContainerBase{

	public BlockLaunchPad() {
		super("launch_pad", 2, Material.iron, 2.0F, 2.0F, Block.soundTypeMetal);
	}

	@Override
	public TileEntity getTileEntity(int meta) {
		return null;
	}

}
