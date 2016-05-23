package tm.fissionwarfare.packet;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import tm.fissionwarfare.tileentity.base.TileEntityEnergyBase;

public class ServerPacketHandler implements IMessage {

	private String text;

	public ServerPacketHandler() {
	}

	public ServerPacketHandler(String text) {
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

	public static class Handler implements IMessageHandler<ServerPacketHandler, IMessage> {

		@Override
		public IMessage onMessage(ServerPacketHandler message, MessageContext ctx) {
			
			String[] data = message.text.split("%");
			
			EntityPlayer player = ctx.getServerHandler().playerEntity;
			
			if (data[0].equalsIgnoreCase("toggle.tileEntity")) {
				
				int x = Integer.parseInt(data[1]);
				int y = Integer.parseInt(data[2]);
				int z = Integer.parseInt(data[3]);
				
				((TileEntityEnergyBase)player.worldObj.getTileEntity(x, y, z)).enabled = !((TileEntityEnergyBase)player.worldObj.getTileEntity(x, y, z)).enabled;
			}
			
			return null;
		}
	}
}