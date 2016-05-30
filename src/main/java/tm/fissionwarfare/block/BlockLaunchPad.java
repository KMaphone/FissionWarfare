package tm.fissionwarfare.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import tm.fissionwarfare.tileentity.machine.TileEntityLaunchPad;

public class BlockLaunchPad extends BlockContainerBase {

	public BlockLaunchPad() {
		super("launch_pad", 2, Material.iron, 2.0F, 2.0F, Block.soundTypeMetal);
		setBounds(0, 0, 0, 16, 10.9F, 16);
	}
	
	@Override
	public int getRenderType() {
		return -1;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public TileEntity getTileEntity(int meta) {
		return new TileEntityLaunchPad();
	}
}
