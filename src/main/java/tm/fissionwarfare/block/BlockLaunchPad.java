package tm.fissionwarfare.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tm.fissionwarfare.item.ItemMissile;
import tm.fissionwarfare.tileentity.base.TileEntityInventoryBase;
import tm.fissionwarfare.tileentity.machine.TileEntityLaunchPad;

public class BlockLaunchPad extends BlockContainerBase {

	public BlockLaunchPad() {
		super("launch_pad", "steel_block", Material.iron, 2, 2.0F, 2.0F, Block.soundTypeMetal);
		setBounds(0, 0, 0, 16, 10.9F, 16);
	}
	
	public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase e, ItemStack is) {
		
		int l = MathHelper.floor_double((double) (e.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;		
		w.setBlockMetadataWithNotify(x, y, z, l, 2);
	}
	
	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int i, float f, float f2, float f3) {
		
		TileEntityLaunchPad tileEntity = (TileEntityLaunchPad)w.getTileEntity(x, y, z);
		
		if (p.getCurrentEquippedItem() != null && p.getCurrentEquippedItem().getItem() instanceof ItemMissile) {
			
			if (tileEntity.missile == null) {
				
				tileEntity.missile = p.getCurrentEquippedItem();
				p.inventory.decrStackSize(p.inventory.currentItem, 1);
				tileEntity.update();
				
				w.playSound(x, y, z, "random.click", 1, 0, false);
				return true;
			}			
		}
		
		else if (p.getCurrentEquippedItem() == null) {
			
			if (tileEntity.missile != null) {
				
				p.inventory.setInventorySlotContents(p.inventory.currentItem, tileEntity.missile);
				tileEntity.missile = null;
				tileEntity.update();
				
				w.playSound(x, y, z, "random.click", 1, 0, false);
				return true;
			}
		}
		
		return false;
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
