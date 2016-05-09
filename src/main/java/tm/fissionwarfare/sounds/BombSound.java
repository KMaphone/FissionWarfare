package tm.fissionwarfare.sounds;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.util.ResourceLocation;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.entity.EntityExplosive;

@SideOnly(Side.CLIENT)
public class BombSound extends MovingSound {

	private static ResourceLocation location = new ResourceLocation(Reference.MOD_ID + ":beep");
	
	private EntityExplosive entity;
	
	public BombSound(EntityExplosive entity) {
		super(location);
		this.entity = entity;
		
		repeat = true;
		volume = 0.5F;
	}

	@Override
	public void update() {
		xPosF = (float) entity.posX;
		yPosF = (float) entity.posY;
		zPosF = (float) entity.posZ;
		
		field_147663_c += 0.001F;
		
		donePlaying = entity.isDead;
	}
}
