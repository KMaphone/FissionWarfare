package tm.fissionwarfare.util;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.particle.EntityFX;

public class EffectUtil {

	public static void spawnEffect(EntityFX entity) {
		FMLClientHandler.instance().getClient().effectRenderer.addEffect(entity);
	}
}
