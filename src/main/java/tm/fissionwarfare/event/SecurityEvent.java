package tm.fissionwarfare.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;
import tm.fissionwarfare.api.ISecurity;
import tm.fissionwarfare.util.ChatUtil;

public class SecurityEvent {

	@SubscribeEvent
	public void onBlockPlace(PlaceEvent event) {
		
		if (!event.world.isRemote) {
			
			TileEntity tileEntity = event.world.getTileEntity(event.x, event.y, event.z);
			
			if (tileEntity instanceof ISecurity) {
				
				ISecurity security = (ISecurity) tileEntity;
				
				if (event.player.getTeam() == null) {
					
					ChatUtil.printFWMessage(EnumChatFormatting.WHITE, "You are not on a team. No security will be added!", event.player);
				} 
				
				else security.getSecurityProfile().setTeamName(event.player.getTeam());
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent event) {
		
		if (!event.world.isRemote && event.action == Action.RIGHT_CLICK_BLOCK) {
			
			TileEntity tileEntity = event.world.getTileEntity(event.x, event.y, event.z);
			
			if (tileEntity instanceof ISecurity) {
				
				ISecurity security = (ISecurity) tileEntity;
								
				if (!security.getSecurityProfile().isSameTeam(event.entityPlayer)) {
					
					ChatUtil.printFWMessage(EnumChatFormatting.RED, "This unit doesn't belong to you!", event.entityPlayer);
					event.setCanceled(true);
				}
			}
		}
	}
}
