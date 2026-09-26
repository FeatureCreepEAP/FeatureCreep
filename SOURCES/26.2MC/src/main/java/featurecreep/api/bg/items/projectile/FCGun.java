package featurecreep.api.bg.items.projectile;

import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;

@Deprecated(forRemoval = true, since = "13")

public class FCGun extends FCBow {

	public static final int TICKS_PER_SECOND = 500;
	public static final int RANGE = 15;

	public FCGun(int id, String modid, String name, UnifiedItemGroupGetter group) {
		super(id, modid, name, group);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getDefaultProjectileRange() {
		return 200;
	}

}
