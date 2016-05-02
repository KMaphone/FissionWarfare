package tm.fissionwarfare.packet;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import tm.fissionwarfare.inventory.ContainerEnergyBase;

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
			
			return null;
		}
	}
}