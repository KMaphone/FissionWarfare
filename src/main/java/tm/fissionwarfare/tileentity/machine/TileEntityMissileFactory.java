package tm.fissionwarfare.tileentity.machine;

import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;
import tm.fissionwarfare.block.BlockExplosive;
import tm.fissionwarfare.gui.GuiMissileFactory;
import tm.fissionwarfare.init.InitItems;
import tm.fissionwarfare.inventory.ContainerMissileFactory;
import tm.fissionwarfare.item.ItemTiered;
import tm.fissionwarfare.missile.MissileData;
import tm.fissionwarfare.tileentity.base.TileEntityEnergyBase;

public class TileEntityMissileFactory extends TileEntityEnergyBase {
	
	public static final int ENERGY_COST = 25000;
	
	public TileEntityMissileFactory() {
		setInputSlots(0, 1, 2);
		setSideInputSlots(3);
		setExtractSlots(4);
	}
	
	@Override
	public int getMaxEnergy() {
		return 100000;
	}

	@Override
	public int getMaxReceive() {
		return 25000;
	}

	@Override
	public int getMaxExtract() {
		return 25000;
	}

	@Override
	public int getMaxProgress() {
		return 20 * 5;
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		if (isReady()) {
			progress++;
		}
		
		else progress = 0;
		
		if (slots[0] != null) System.out.println(((BlockExplosive)Block.getBlockFromItem(slots[0].getItem())).getExplosion().getName());
		
		if (isDoneAndReset()) {
			
			System.out.println("done");
			
			storage.extractEnergy(ENERGY_COST, false);
			
			ItemStack stack = new ItemStack(InitItems.missile);
			
			MissileData data = new MissileData();
			
			data.setExplosionType(((BlockExplosive)Block.getBlockFromItem(slots[0].getItem())).getExplosion());
			data.setAccuracy(slots[1].getItemDamage());
			data.setSpeed(slots[2].getItemDamage());
			
			MissileData.setDataToItem(stack, data);
			
			setInventorySlotContents(4, stack);
			
			for (int i = 0; i < 3; i++) {
				slots[0].stackSize--;
			}
			
			slots[4].stackSize -= 16;		
		}
	}
	
	public boolean isReady() {
		
		for (int i = 0; i < 4; i++) {
			
			if (slots[i] == null) {
				return false;
			}
		}
		
		return slots[3].stackSize >= 16 && slots[4] == null && canExtractEnergy(ENERGY_COST);
	}
	
	@Override
	public boolean canConnectEnergy(ForgeDirection dir) {
		return true;
	}

	@Override
	public int getSizeInventory() {
		return 5;
	}

	@Override
	public Container getTileContainer(EntityPlayer player) {
		return new ContainerMissileFactory(player, this);
	}

	@Override
	public GuiContainer getTileGuiContainer(EntityPlayer player) {
		return new GuiMissileFactory(getTileContainer(player), player, this);
	}
}
