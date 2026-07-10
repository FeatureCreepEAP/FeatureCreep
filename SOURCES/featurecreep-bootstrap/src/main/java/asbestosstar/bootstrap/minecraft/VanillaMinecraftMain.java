package asbestosstar.bootstrap.minecraft;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import asbestosstar.bootstrap.BootstrapCommon;
import featurecreep.loader.GameProvider;

/**
 * TLauncher main class for plain Minecraft.
 */
public final class VanillaMinecraftMain {
	private static final String CLIENT_MAIN = "net.minecraft.client.main.Main";

	private VanillaMinecraftMain() {
	}

	public static void main(String[] args) throws Throwable {
		GameProvider provider = MinecraftProviderSelector.detect();

		BootstrapCommon.initializeGame(provider);
		BootstrapCommon.runMods();

		invokeMain(CLIENT_MAIN, args);
	}

	private static void invokeMain(String className, String[] args) throws Throwable {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		if (loader == null) {
			loader = ClassLoader.getSystemClassLoader();
		}

		Class<?> type = Class.forName(className, true, loader);
		Method method = type.getMethod("main", String[].class);

		try {
			method.invoke(null, (Object) args);
		} catch (InvocationTargetException exception) {
			throw exception.getCause();
		}
	}
}
