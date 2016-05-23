package tm.fissionwarfare.explosion.type;

import tm.fissionwarfare.api.IExplosionType;

public enum EnumExplosionType {

	BASIC(new BasicExplosion(), 80),
	EMP(new EMPExplosion(), 100),
	ATOMIC(new AtomicExplosion(), 100),
	CHEMICAL(new ChemicalExplosion(), 100),
	PYRO(new PyroExplosion(), 100);

	private IExplosionType explosionType;
	private int fuseTime;

	private EnumExplosionType(IExplosionType explosionType, int fuseTime) {
		this.explosionType = explosionType;
		this.fuseTime = fuseTime;
	}
	
	public IExplosionType getExplosionType() {
		return explosionType;
	}
	
	public int getFuseTime() {
		return fuseTime;
	}
}
