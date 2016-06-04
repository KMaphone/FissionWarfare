package tm.fissionwarfare.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tm.fissionwarfare.FissionWarfare;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.init.InitItems;
import tm.fissionwarfare.packet.ClientPacketHandler;
import tm.fissionwarfare.sounds.FWSound;

public class ItemNailGun extends ItemBase {

	public ItemNailGun() {
		super("nail_gun");
	}
	
	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int meta, float float1, float float2, float float3) {
		
		double xd = (double) x;
		double yd = (double) y;
		double zd = (double) z;
		
		if (world.getBlock(x, y, z) == InitBlocks.concrete){

			if (player.inventory.hasItemStack(new ItemStack(InitItems.nail)) && player.inventory.hasItemStack(new ItemStack(InitItems.invar_frame))){
				
				if (world.getBlockMetadata(x, y, z) < 14){
					
					player.inventory.consumeInventoryItem(InitItems.invar_frame);
					player.inventory.consumeInventoryItem(InitItems.nail);
					int get = world.getBlockMetadata(x, y, z);
					world.setBlockMetadataWithNotify(x, y, z, get + 5, 2);
					world.playSound(xd, yd, zd, "random.anvil_land", 1.0F, 1.0F, false);
				}
			}
		}
		
		else System.out.println("NOT CONCRETE");
		
		return true;
	}

}
