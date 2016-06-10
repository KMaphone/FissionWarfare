package tm.fissionwarfare.sounds;

import net.minecraft.client.audio.MovingSound;
import net.minecraft.util.ResourceLocation;
import scala.annotation.meta.field;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.tileentity.machine.TileEntityLaunchPad;

public class LaunchSound extends MovingSound {

	private static ResourceLocation location = new ResourceLocation(Reference.MOD_ID + ":launch");
	
	private TileEntityLaunchPad launchPad;
	
	public LaunchSound(TileEntityLaunchPad launchPad) {
		super(location);
		
		this.launchPad = launchPad;
		
		xPosF = launchPad.xCoord;
		yPosF = launchPad.yCoord;
		zPosF = launchPad.zCoord;
		
		repeat = true;
	}

	@Override
	public void update() {
		donePlaying = !launchPad.launching;
	}
}
