package tm.fissionwarfare.tileentity;

import com.sun.javafx.geom.Vec3d;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import tm.fissionwarfare.math.Angle2d;
import tm.fissionwarfare.math.MathUtil;
import tm.fissionwarfare.math.Vector3d;

public class TileEntityTurret extends TileEntity {
	
	public static final int RANGE = 10;
	
	public Angle2d angle = new Angle2d(0, 0);
	
	public EntityPlayer target;
	
	@Override
	public void updateEntity() {
		
		if (target == null) {
			
			angle.pitch += MathUtil.approach(angle.pitch, 0, 6);
			angle.yaw += 0.5F;
			
			findTarget();
		}
		
		else {
			
			Angle2d targetAngle = Vector3d.getAngleFromVectors(getVector(), new Vector3d(target));
			
			angle.pitch += MathUtil.approach(angle.pitch, targetAngle.pitch, 6);
			angle.yaw = MathUtil.approachRotation(angle.yaw, targetAngle.yaw, 6);			
			
			if (canHit(target)) {
				System.out.println("Pew Pew Pew");
			}
			
			if (!isTargetInRange(target) || target.capabilities.isCreativeMode) {
				target = null;
			}			
		}
	}
	
	public boolean canHit(Entity e) {
		
		Vec3 tileVec = Vec3.createVectorHelper(xCoord + 0.5D, yCoord + 1, zCoord + 0.5D);
		Vec3 entityVec = Vec3.createVectorHelper(e.posX, e.posY + 1, e.posZ);
		
		MovingObjectPosition blockMop = worldObj.rayTraceBlocks(tileVec, entityVec);
		
		
		if (blockMop == null && e.getCollisionBox(e) != null) {
			
			MovingObjectPosition playerMop = e.getCollisionBox(e).calculateIntercept(tileVec, entityVec);
			
			if (playerMop != null) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isTargetInRange(Entity e) {
		return e.getDistance(xCoord + 0.5F, yCoord + 0.5F, zCoord + 0.5F) <= RANGE;
	}
	
	public Vector3d getVector() {
		return new Vector3d(xCoord + 0.5F, yCoord + 1, zCoord + 0.5F);
	}
	
	public void findTarget() {
		
		for (Object o : worldObj.loadedEntityList) {
			
			if (o instanceof EntityPlayer) {
				
				EntityPlayer player = (EntityPlayer)o;
				
				if (isTargetInRange(player) && !player.capabilities.isCreativeMode) {
					
					target = player;
					return;
				}				
			}
		}
	}
}
