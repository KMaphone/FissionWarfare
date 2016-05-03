package tm.fissionwarfare.inventory;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotFilter extends Slot {
	
	public List<ItemStack> filters;
	
	public SlotFilter(IInventory inv, int id, int x, int y, ItemStack... filters) {
		super(inv, id, x, y);
		
		this.filters = new ArrayList<ItemStack>();
		
		for (ItemStack stack : filters) {
			this.filters.add(stack);
		}
	}
	
	public boolean isFilter(ItemStack stack) {
				
		for (ItemStack filter : this.filters) {
						
			if (filter.getItem() == stack.getItem()) return true; 
		}
		
		return false;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return isFilter(stack);
	}
}
