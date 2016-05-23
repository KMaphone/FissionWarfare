package tm.fissionwarfare.missile;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class MissileData {

	private float accuracy;
	private float speed;
	private int health;
	private Block explosive;

	public MissileData(float accuracy, float speed, int health, Block explosive) {
		this.accuracy = accuracy;
		this.speed = speed;
		this.health = health;
		this.explosive = explosive;
	}

	public MissileData(ItemStack stack) {

		NBTTagCompound nbt = stack.getTagCompound();

		if (nbt == null) {
			nbt = new NBTTagCompound();
			stack.setTagCompound(nbt);
		}

		readFromNBT(nbt);
	}

	public void readFromNBT(NBTTagCompound nbt) {

		accuracy = nbt.getFloat("accuracy");
		speed = nbt.getFloat("speed");

		health = nbt.getInteger("health");

		if (nbt.hasKey("explosive")) {
			explosive = Block.getBlockById(nbt.getInteger("exploive"));
		}
	}

	public void writeFromNBT(NBTTagCompound nbt) {

		nbt.setFloat("accuracy", accuracy);
		nbt.setFloat("speed", speed);

		nbt.setInteger("health", health);

		if (explosive != null) {
			nbt.setInteger("exploive", Block.getIdFromBlock(explosive));
		}
	}
	
	public void flush(ItemStack stack) {
		
		NBTTagCompound nbt = stack.getTagCompound();

		if (nbt == null) {
			nbt = new NBTTagCompound();
			stack.setTagCompound(nbt);
		}

		writeFromNBT(nbt);
	}
	
	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void setExplosive(Block explosive) {
		this.explosive = explosive;
	}

	public float getAccuracy() {
		return accuracy;
	}

	public float getSpeed() {
		return speed;
	}
	
	public int getHealth() {
		return health;
	}

	public Block getExplosive() {
		return explosive;
	}
}
