package tm.fissionwarfare.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.item.ItemBlockMeta;

public class BlockSupportFrame extends BlockMetaBase {

	private IIcon top_icon;
	
	public BlockSupportFrame() {
		super("support_frame", Material.iron, 2, 2.0F, 2.0F, Block.soundTypeMetal, ItemBlockMeta.class);
	}
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tabs, List list) {
		list.add(new ItemStack(item, 1, 1));
	}
		
	private void setMetaBounds(int meta, int x, int y, int z) {		
		if (meta == 0) setBounds(5, 0, 0, 11, 16, 16);
		else setBounds(0, 0, 5, 16, 16, 11);
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		setMetaBounds(world.getBlockMetadata(x, y, z), x, y, z);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
		setMetaBounds(world.getBlockMetadata(x, y, z), x, y, z);
		return AxisAlignedBB.getBoundingBox(x + minX, y + minY, z + minZ, x + maxX, y + maxY, z + maxZ);
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		setMetaBounds(world.getBlockMetadata(x, y, z), x, y, z);
		return AxisAlignedBB.getBoundingBox(x + minX, y + minY, z + minZ, x + maxX, y + maxY, z + maxZ);
	}
	
	public void setBlockBoundsForItemRender() {      
		setBounds(0, 0, 5, 16, 16, 11);
    }

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		
		int meta = world.getBlockMetadata(x, y, z);
		
		for (int xzOffset = -1; x < 2; x++) {
			
			for (int yOffset= 0; y < 8; y++) {
				
				if (!super.canPlaceBlockAt(world, x + (meta == 0 ? xzOffset : 0), y + yOffset, z + (meta == 1 ? xzOffset : 0))) {
					return false;
				}
			}
		}
		
		return super.canPlaceBlockAt(world, x, y, z);
	}
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack is) {
		
		int l = MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        int i1 = world.getBlockMetadata(x, y, z) >> 2;
        ++l;
        l %= 4;

        if (l == 0) world.setBlockMetadataWithNotify(x, y, z, 0 | i1 << 2, 2);
        if (l == 1) world.setBlockMetadataWithNotify(x, y, z, 1 | i1 << 2, 2);
        if (l == 2) world.setBlockMetadataWithNotify(x, y, z, 0 | i1 << 2, 2);        
        if (l == 3) world.setBlockMetadataWithNotify(x, y, z, 1 | i1 << 2, 2);
    }
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(this, 1, 1);		
	}
	
	@Override
	public int damageDropped(int meta) {
		return 1;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {		
		Block block = world.getBlock(x, y, z);
		return block == this ? false : super.shouldSideBeRendered(world, x, y, z, side);
	}

	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 1;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		
		if (side < 2 || side == 2 + (meta * 2) || side == 3 + (meta * 2)) {
			return top_icon;
		}
		
		return blockIcon;		
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconreg) {
		
		blockIcon = iconreg.registerIcon(Reference.MOD_ID + ":support_frame_side");
		top_icon = iconreg.registerIcon(Reference.MOD_ID + ":support_frame_top");
	}
}
