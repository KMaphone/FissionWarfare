package tm.fissionwarfare.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import tm.fissionwarfare.tileentity.base.TileEntityInventoryBase;

public abstract class ContainerBase extends Container {

	public TileEntityInventoryBase tileEntity;
	public EntityPlayer player;
	
	public ContainerBase(EntityPlayer player, TileEntityInventoryBase tileEntity) {
		this.tileEntity = tileEntity;
		this.player = player;
		addPlayerInv(8, 94);
		addPlayerHotbar(8, 152);
	}
	
	public abstract int getNewSlotAmount();
	
	public void addPlayerInv(int x, int y) {
				
		for (int i = 0; i < 3; i++) {
			
			for (int j = 0; j < 9; j++) {		
				
				this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, x + (j * 18), y + (i * 18)));
			}
		}
	}

	public void addPlayerHotbar(int x, int y) {
		
		for (int i = 0; i < 9; i++) {
			
			this.addSlotToContainer(new Slot(player.inventory, i, x + i * 18, y));
		}
	}

	public ItemStack transferStackInSlot(EntityPlayer player, int slotId) {
		
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(slotId);
			
		if (slot != null && slot.getHasStack()) {
			
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
				
			if (getNewSlotAmount() > 0) {
				
				if (!slot.isItemValid(itemstack1)) {
					
					if (!mergeInvHotbarIfPossable(slot, itemstack1, slotId)) {
						return null;
					}
				}
				
				if (slotId <= 35) {
					
					if (!mergeIfPossable(slot, itemstack1, 36, 36 + getNewSlotAmount())) {
						
						if (!mergeInvHotbarIfPossable(slot, itemstack1, slotId)) {
							return null;
						}
					}
				}	
				
				else {
										
					if (!mergeIfPossable(slot, itemstack1, 0, 35)) {
						
						if (!mergeInvHotbarIfPossable(slot, itemstack1, slotId)) {
							return null;
						}
					}
				}
			}
			
			else {
				
				if (!mergeInvHotbarIfPossable(slot, itemstack1, slotId)) {
					return null;
				}
			}			
			
			if (itemstack1.stackSize == 0) {				
				slot.putStack((ItemStack)null);
			}
			
			else {				
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(player, itemstack1);
		}
		
		return itemstack;
	}
	
	public boolean mergeIfPossable(Slot slot, ItemStack is, int id, int maxId) {
		
		if (!this.mergeItemStack(is, id, maxId, false)) {
			return false;
		}

		slot.onSlotChange(is, is);
		return true;
	}
	
	public boolean mergeInvHotbarIfPossable(Slot slot, ItemStack is, int id) {
		
		if (id < 27) {
			
			if (!mergeIfPossable(slot, is, 27, 35)) {
				return false;
			}

			slot.onSlotChange(is, is);
			
		}
		
		else {
			
			if (!mergeIfPossable(slot, is, 0, 26)) {
				return false;
			}	
			
			slot.onSlotChange(is, is);
		}
		
		return true;
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}
}
