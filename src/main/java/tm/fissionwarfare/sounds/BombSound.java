package tm.fissionwarfare.sounds;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.util.ResourceLocation;
import scala.annotation.meta.field;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.entity.EntityExplosive;
import tm.fissionwarfare.tileentity.machine.TileEntityLaunchPad;

public class BombSound extends MovingSound {

	private static ResourceLocation location = new ResourceLocation(Reference.MOD_ID + ":launch");

	private EntityExplosive explosive;

	public BombSound(EntityExplosive explosive) {
		super(location);
		this.explosive = explosive;
		repeat = true;
	}

	@Override
	public void update() {
		xPosF = (float) explosive.posY;
		yPosF = (float) explosive.posY;
		zPosF = (float) explosive.posZ;
		
		field_147663_c += 0.001F;
		
		if (explosive.isDead) {
			donePlaying = true;
		}
	}

	public void play() {

		if (!FMLClientHandler.instance().getClient().getSoundHandler().isSoundPlaying(this)) {

			FMLClientHandler.instance().getClient().getSoundHandler().playSound(this);
		}
	}
}
