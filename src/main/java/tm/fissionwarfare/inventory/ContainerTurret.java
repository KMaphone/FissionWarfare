package tm.fissionwarfare.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.init.InitItems;
import tm.fissionwarfare.tileentity.base.TileEntityEnergyBase;

public class ContainerTurret extends ContainerEnergyBase {

	public ContainerTurret(EntityPlayer player, TileEntityEnergyBase tileEntity) {
		super(player, tileEntity);
		
		addSlotToContainer(new SlotFilter(tileEntityEnergy, 0, 80, 39, new ItemStack(InitItems.quartz), new ItemStack(InitBlocks.concrete)));
	}

	@Override
	public int getNewSlotAmount() {
		return 1;
	}
}
