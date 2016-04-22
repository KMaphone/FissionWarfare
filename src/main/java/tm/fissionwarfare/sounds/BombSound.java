package tm.fissionwarfare.sounds;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import tm.fissionwarfare.Reference;

@SideOnly(Side.CLIENT)
public class BombSound extends MovingSound {

	private static ResourceLocation location = new ResourceLocation(Reference.MOD_ID + ":beep");
	
	private Entity entity;
	
	public BombSound(Entity entity) {
		super(location);
		this.entity = entity;
		this.repeat = true;
	}

	@Override
	public void update() {
		xPosF = (float) entity.posX;
		yPosF = (float) entity.posY;
		zPosF = (float) entity.posZ;
		
		donePlaying = entity.isDead;
	}
}
