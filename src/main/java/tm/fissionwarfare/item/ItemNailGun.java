package tm.fissionwarfare.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import tm.fissionwarfare.FissionWarfare;
import tm.fissionwarfare.block.BlockConcrete;
import tm.fissionwarfare.config.FWConfig;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.init.InitItems;
import tm.fissionwarfare.packet.ClientPacketHandler;
import tm.fissionwarfare.sounds.FWSound;
import tm.fissionwarfare.util.ChatUtil;

public class ItemNailGun extends ItemBase {

	public ItemNailGun() {
		super("nail_gun");
		setFull3D();
	}
	
	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float float1, float float2, float float3) {

		int meta = world.getBlockMetadata(x, y, z);

		if (FWConfig.enableNailGun) {
			
			if (world.getBlock(x, y, z) == InitBlocks.concrete) {

				if (player.inventory.hasItemStack(new ItemStack(InitItems.nail_gun_magazine)) || player.capabilities.isCreativeMode) {

					if (world.getBlockMetadata(x, y, z) < 14) {
						
						if (!player.capabilities.isCreativeMode) player.inventory.consumeInventoryItem(InitItems.nail_gun_magazine);
						world.setBlockMetadataWithNotify(x, y, z, meta + ((meta == BlockConcrete.metaTiers[0] || meta == BlockConcrete.metaTiers[1]) ? 5 : 1), 2);
						world.playSound((double)x, (double)y, (double)z, "random.anvil_land", 0.05F, 1.0F, false);
					}
				}
			}
		}
		
		else if (!world.isRemote) ChatUtil.printFWMessage(EnumChatFormatting.RED, "This function was disabled by config!", player);
		
		return true;
	}
}
