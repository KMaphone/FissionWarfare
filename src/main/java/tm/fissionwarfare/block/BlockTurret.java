package tm.fissionwarfare.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tm.fissionwarfare.tileentity.machine.TileEntityTurret;

public class BlockTurret extends BlockContainerBase {

	public BlockTurret() {
		super("steel_casing", 2, Material.iron, 1, 1, Block.soundTypeMetal);
		setBounds(1.5F, 0, 1.5F, 14.5F, 8, 14.5F);
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

		return new TileEntityTurret();
	}	
}
