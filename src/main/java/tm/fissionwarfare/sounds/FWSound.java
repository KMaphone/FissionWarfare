package tm.fissionwarfare.sounds;

import net.minecraft.world.World;
import tm.fissionwarfare.Reference;

public class FWSound {

	public static final FWSound small_blast = new FWSound("blast_1");
	public static final FWSound turret_blast1 = new FWSound("turret_blast1");
	public static final FWSound rumbling = new FWSound("rumbling");
	public static final FWSound cave_in = new FWSound("cave_in");
	public static final FWSound rumbling_cave = new FWSound("rumbling_cave");
	
	private String sound;

	public FWSound(String sound) {
		this.sound = sound;
	}
	
	public String getSoundPath() {
		return Reference.MOD_ID + ":" + sound;
	}
	
	public void play(World world, double x, double y, double z, float gain, float pitch) {
		world.playSound(x, y, z, getSoundPath(), gain, pitch, false);
	}
}
