package tm.fissionwarfare.missile;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import tm.fissionwarfare.explosion.type.EnumExplosionType;

public class MissileData {

	private int accuracy = 0;
	private int speed = 0;
	private EnumExplosionType explosionType;

	public void readFromNBT(NBTTagCompound nbt) {

		accuracy = nbt.getInteger("accuracy");
		speed = nbt.getInteger("speed");

		if (nbt.hasKey("explosionType")) {
			explosionType = EnumExplosionType.valueOf(nbt.getString("explosionType"));
		}
	}
	
	public int returnBlockRange(){
		return getSpeed() * 500;
	}

	public void writeToNBT(NBTTagCompound nbt) {

		nbt.setInteger("accuracy", accuracy);
		nbt.setInteger("speed", speed);

		if (explosionType != null) {
			nbt.setString("explosionType", explosionType.name());
		}
	}

	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setExplosionType(EnumExplosionType explosionType) {
		this.explosionType = explosionType;
	}

	public int getAccuracy() {
		return accuracy;
	}

	public int getSpeed() {
		return speed;
	}

	public EnumExplosionType getExplosionType() {
		return explosionType;
	}

	public static MissileData getDataFromItem(ItemStack stack) {

		NBTTagCompound nbt = stack.getTagCompound();

		if (nbt == null) {
			nbt = new NBTTagCompound();
		}
		
		MissileData data = new MissileData();

		data.readFromNBT(nbt);

		return data;
	}

	public static ItemStack setDataToItem(ItemStack stack, MissileData data) {

		NBTTagCompound nbt = stack.getTagCompound();
		
		if (nbt == null) {
			nbt = new NBTTagCompound();
		}
		
		data.writeToNBT(nbt);
		
		stack.setTagCompound(nbt);
		
		return stack;
	}
}
