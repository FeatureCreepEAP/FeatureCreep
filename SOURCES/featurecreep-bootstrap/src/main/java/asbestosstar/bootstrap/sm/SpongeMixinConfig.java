package asbestosstar.bootstrap.sm;

import java.util.List;
import java.util.Set;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

public class SpongeMixinConfig implements IMixinConfigPlugin {

	static {
		
		//SpongeMixinUtils.injectSpongeMixins();

		String featureConfig = "featurecreepimpl.mixins.json";

		// Check if the resource exists in the current classloader has this config. this config is not part of the bootstrap but of the version specific projects
		if (SpongeMixinConfig.class.getClassLoader().getResource(featureConfig) != null) {
			try {
				Mixins.addConfiguration(featureConfig);
			} catch (Exception e) {
				System.err.println("Failed to register " + featureConfig);
				e.printStackTrace();
			}
		}

	}

	@Override
	public void onLoad(String mixinPackage) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getRefMapperConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> getMixins() {
		// TODO Auto-generated method stub
		return List.of();// TODO
	}

	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
		// TODO Auto-generated method stub

	}

}
