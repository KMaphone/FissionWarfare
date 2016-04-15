package tm.fissionwarfare.explosion;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import tm.fissionwarfare.math.Angle2d;
import tm.fissionwarfare.math.Vector3d;

public class PlayerExplosionUtil {

	private static List<EntityLivingBase> getLivingEntities(World world) {

		List<EntityLivingBase> list = new ArrayList<EntityLivingBase>();

		for (Object object : world.loadedEntityList) {

			if (object instanceof EntityLivingBase) {

				list.add((EntityLivingBase) object);
			}
		}

		return list;
	}

	public static void doLivingDamage(World world, Vector3d vector, double range, double maxDamage) {

		for (EntityLivingBase living : getLivingEntities(world)) {

			double distance = living.getDistance(vector.x, vector.y, vector.z);

			int damage = (int) (range - ((maxDamage / range) * distance));

			if (damage > 0) {
				living.attackEntityFrom(DamageSource.generic, damage);
			}
		}
	}
}
