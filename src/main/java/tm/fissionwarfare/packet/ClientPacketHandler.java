package tm.fissionwarfare.packet;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.inventory.ContainerEnergyBase;
import tm.fissionwarfare.sounds.FWSound;
import tm.fissionwarfare.util.NBTUtil;

public class ClientPacketHandler implements IMessage {

	private String text;

	public ClientPacketHandler() {}
	
	public ClientPacketHandler(String text) {
		this.text = text;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		text = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, text);
	}

	public static class Handler implements IMessageHandler<ClientPacketHandler, IMessage> {

		@Override
		public IMessage onMessage(ClientPacketHandler message, MessageContext ctx) {

			String[] data = message.text.split("%");
			
			EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
						
			if (data[0].equalsIgnoreCase("sync.energy")) {
				
				int energy = Integer.parseInt(data[1]);
				
				if (player.openContainer instanceof ContainerEnergyBase) {				
					
					ContainerEnergyBase container = (ContainerEnergyBase)player.openContainer;	
					
					container.tileEntityEnergy.storage.setEnergyStored(energy);
				}
			}
			
			if (data[0].equalsIgnoreCase("set.energy")) {
				
				int slot = Integer.parseInt(data[1]);
				int energy = Integer.parseInt(data[2]);
				
				ItemStack stack = player.inventory.getStackInSlot(slot);
				
				NBTUtil.getNBT(stack).setInteger("energy", energy);
			}
			
			if (data[0].equalsIgnoreCase("consumeitem")) {
								
				Item item = Item.getItemById(Integer.parseInt(data[1]));							
				player.inventory.consumeInventoryItem(item);
			}
			
			if (data[0].equalsIgnoreCase("damageitem")) {
				
				int slot = Integer.parseInt(data[1]);
				
				ItemStack stack = player.inventory.getStackInSlot(slot);
								
				stack.damageItem(Integer.parseInt(data[2]), player);
				
				if (stack.getItemDamage() >= stack.getMaxDamage()) {
					player.inventory.setInventorySlotContents(slot, null);
				}
			}
			
			if (data[0].equalsIgnoreCase("playsound")) {
				
				String soundPath = data[1];
				
				int x = Integer.parseInt(data[2]);
				int y = Integer.parseInt(data[3]);
				int z = Integer.parseInt(data[4]);
				
				float gain = Float.parseFloat(data[5]);
				player.worldObj.playSound(x, y, z, soundPath, gain, 1, false);
			}
				
			return null;
		}
	}
}