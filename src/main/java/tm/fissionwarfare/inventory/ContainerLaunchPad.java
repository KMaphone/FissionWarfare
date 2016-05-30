package tm.fissionwarfare.inventory;

import net.minecraft.entity.player.EntityPlayer;
import tm.fissionwarfare.init.InitItems;
import tm.fissionwarfare.tileentity.base.TileEntityEnergyBase;

public class ContainerLaunchPad extends ContainerEnergyBase {

	public ContainerLaunchPad(EntityPlayer player, TileEntityEnergyBase tileEntity) {
		super(player, tileEntity);

		addSlotToContainer(new SlotFilter(tileEntityEnergy, tileEntity, 0, 80, 39, InitItems.missile));
	}
}
