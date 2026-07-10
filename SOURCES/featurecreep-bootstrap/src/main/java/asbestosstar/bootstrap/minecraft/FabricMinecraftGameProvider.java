package asbestosstar.bootstrap.minecraft;

import featurecreep.loader.ExecutionSide;

/**
 * Fabric-aware provider without depending on Fabric's Mixin bootstrap.
 */
public final class FabricMinecraftGameProvider extends MinecraftGameProvider {

	@Override
	public ExecutionSide getExecutionSide() {
		try {
			Class<?> environmentType = Class.forName("net.fabricmc.api.EnvType", false,
					Thread.currentThread().getContextClassLoader());
			Class<?> loaderClass = Class.forName("net.fabricmc.loader.api.FabricLoader", false,
					Thread.currentThread().getContextClassLoader());

			Object loader = loaderClass.getMethod("getInstance").invoke(null);
			Object type = loaderClass.getMethod("getEnvironmentType").invoke(loader);

			return type == Enum.valueOf((Class<? extends Enum>) environmentType.asSubclass(Enum.class), "CLIENT")
					? ExecutionSide.CLIENT
					: ExecutionSide.SERVER;
		} catch (ReflectiveOperationException ignored) {
			return super.getExecutionSide();
		}
	}
}
