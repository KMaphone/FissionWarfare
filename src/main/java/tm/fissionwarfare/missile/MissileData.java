package tm.fissionwarfare.missile;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import tm.fissionwarfare.explosion.type.EnumExplosionType;

public class MissileData {

	private float accuracy = 0;
	private float speed = 0;
	private EnumExplosionType explosionType;

	public void readFromNBT(NBTTagCompound nbt) {

		accuracy = nbt.getFloat("accuracy");
		speed = nbt.getFloat("speed");

		if (nbt.hasKey("explosionType")) {
			explosionType = EnumExplosionType.valueOf(nbt.getString("explosionType"));
		}
	}

	public void writeFromNBT(NBTTagCompound nbt) {

		nbt.setFloat("accuracy", accuracy);
		nbt.setFloat("speed", speed);

		if (explosionType != null) {
			nbt.setString("explosionType", explosionType.name());
		}
	}

	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public void setExplosionType(EnumExplosionType explosionType) {
		this.explosionType = explosionType;
	}

	public float getAccuracy() {
		return accuracy;
	}

	public float getSpeed() {
		return speed;
	}

	public EnumExplosionType getExplosionType() {
		return explosionType;
	}

	public static MissileData getDataFromItem(ItemStack stack) {

		NBTTagCompound nbt = stack.getTagCompound();

		nbt = new NBTTagCompound();		
		
		MissileData data = new MissileData();

		data.readFromNBT(nbt);

		return data;
	}

	public static ItemStack setDataToItem(ItemStack stack, MissileData data) {

		NBTTagCompound nbt = stack.getTagCompound();

		nbt = new NBTTagCompound();
		
		data.writeFromNBT(nbt);
		
		stack.setTagCompound(nbt);
		
		return stack;
	}
}
