package tm.fissionwarfare.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import tm.fissionwarfare.tileentity.base.TileEntityEnergyBase;

public class ContainerTurret extends ContainerEnergyBase {

	public ContainerTurret(EntityPlayer player, TileEntityEnergyBase tileEntity) {
		super(player, tileEntity);
		
		addSlotToContainer(new Slot(tileEntityEnergy, 0, 80, 39));
	}

	@Override
	public int getNewSlotAmount() {
		return 1;
	}
}
