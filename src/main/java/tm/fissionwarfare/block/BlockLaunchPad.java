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
		
		TileEntityInventoryBase tileEntity = (TileEntityInventoryBase)w.getTileEntity(x, y, z);
		
		if (p.getCurrentEquippedItem() != null && p.getCurrentEquippedItem().getItem() instanceof ItemMissile) {
			
			if (tileEntity.slots[0] == null) {
				
				tileEntity.setInventorySlotContents(0, p.getCurrentEquippedItem());
				p.inventory.decrStackSize(p.inventory.currentItem, 1);
				tileEntity.update();
				
				w.playSound(x, y, z, "random.click", 1, 0, false);
				return true;
			}			
		}
		
		else if (p.getCurrentEquippedItem() == null) {
			
			if (tileEntity.slots[0] != null) {
				
				p.inventory.setInventorySlotContents(p.inventory.currentItem, tileEntity.slots[0]);
				tileEntity.decrStackSize(0, 1);
				//((Slot)tileEntity.getTileContainer(p).inventorySlots.get(0)).onSlotChanged();
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
