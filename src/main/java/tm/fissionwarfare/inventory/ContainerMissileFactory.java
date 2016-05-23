package tm.fissionwarfare.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import tm.fissionwarfare.tileentity.base.TileEntityEnergyBase;

public class ContainerMissileFactory extends ContainerEnergyBase {

	public ContainerMissileFactory(EntityPlayer player, TileEntityEnergyBase tileEntity) {
		super(player, tileEntity);
		
		addSlotToContainer(new Slot(tileEntity, 0, 48, 21));
		addSlotToContainer(new Slot(tileEntity, 1, 48, 43));
		addSlotToContainer(new Slot(tileEntity, 2, 48, 65));
		
		addSlotToContainer(new Slot(tileEntity, 3, 80, 43));
		addSlotToContainer(new Slot(tileEntity, 4, 112, 43));		
	}
}
