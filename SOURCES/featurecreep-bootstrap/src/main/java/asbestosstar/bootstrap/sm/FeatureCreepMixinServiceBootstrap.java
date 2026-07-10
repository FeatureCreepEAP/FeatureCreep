package asbestosstar.bootstrap.sm;

import org.spongepowered.asm.service.IMixinServiceBootstrap;

public final class FeatureCreepMixinServiceBootstrap implements IMixinServiceBootstrap {

	@Override
	public void bootstrap() {
		System.out.println("[FeatureCreep/Mixin] Service bootstrap.");
	}

	@Override
	public String getServiceClassName() {
		return FeatureCreepMixinService.class.getName();
	}

	@Override
	public String getName() {
		return "featurecreep-minecraft";
	}
}
