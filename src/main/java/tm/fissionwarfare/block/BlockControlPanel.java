package tm.fissionwarfare.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tm.fissionwarfare.tileentity.machine.TileEntityControlPanel;

public class BlockControlPanel extends BlockContainerBase {

	public BlockControlPanel() {
		super("control_panel", "steel_block", Material.iron, 2, 2.0F, 2.0F, Block.soundTypeMetal);
		setBounds(2.6F, 0, 2.6F, 13.4F, 16, 13.4F);
	}

	public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase e, ItemStack is) {
		
		int l = MathHelper.floor_double((double) (e.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;		
		w.setBlockMetadataWithNotify(x, y, z, l, 2);
		System.out.println(l);
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
		return new TileEntityControlPanel();
	}
}
