package tm.fissionwarfare.tileentity;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.TileFluidHandler;
import tm.fissionwarfare.block.BlockScaffold;
import tm.fissionwarfare.init.InitFluids;

public class TileEntityScaffold extends TileFluidHandler {
	
	private ForgeDirection[] dirs = {ForgeDirection.DOWN, ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST};
	
	public int ticks, maxTicks = 20 * 10;
	
	public TileEntityScaffold(int meta) {
		tank.setCapacity((meta * 4000) + (meta == 0 ? 1000 : 0));
	}
	
	public TileEntityScaffold() {}
	
	public FluidTank getTank() {
		return tank;
	}
	
	public boolean isFull() {
		return getTank().getFluidAmount() >= getTank().getCapacity();
	}
	
	@Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return fluid == InitFluids.concrete_mix;
    }
	
	@Override
	public void updateEntity() {
		
		markDirty();
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		
		ticks++;
		
		checkForNeighbors();
		
		if (isFull() && ticks >= maxTicks) {
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.stone);
		}		
	}
	
	public void checkForNeighbors() {
				
		for (ForgeDirection dir : dirs) {
			
			TileEntity tileEntity = worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
			
			if (tileEntity instanceof TileEntityScaffold) {			
				
				TileEntityScaffold scaffold = (TileEntityScaffold)tileEntity;
				
				tryToFill(scaffold);
			}
		}		
	}
	
	public void tryToFill(TileEntityScaffold tileEntity) {
		
		int dif = getTank().getFluidAmount() - tileEntity.getTank().getFluidAmount();
		
		if (dif > 200) {
						
			int filled = tileEntity.getTank().fill(new FluidStack(getTank().getFluid(), getTank().drain(150, true).amount), true);
			
			if (filled < 50) {
				
				int returnedValue = 50 - filled;
				
				getTank().fill(new FluidStack(getTank().getFluid(), returnedValue), true);				
			}
			
			ticks = 0;
		}	
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound syncData = new NBTTagCompound();
	
		tank.writeToNBT(syncData);

		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {		
		tank.readFromNBT(pkt.func_148857_g());
	}		
}
