package asbestosstar.bootstrap;

import java.io.File;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;

import javax.annotation.Nullable;

import asbestosstar.bootstrap.sm.SpongeMixinRuntime;
import featurecreep.loader.FCLoaderBasic;
import featurecreep.loader.FCLoaderBasicR9;
import featurecreep.loader.GameProvider;
import featurecreep.loader.flat.FCLoaderFlat;
import featurecreep.loader.flat.FCLoaderFlatR1;

/**
 * Shared bootstrap for all supported game providers.
 *
 * <p>
 * This class now owns the complete loader lifecycle. There is no separate
 * MinecraftCommonStartup class.
 * </p>
 */
public final class BootstrapCommon {
	public static volatile boolean agent_activated;
	public static volatile Instrumentation instrument;
	public static volatile FCLoaderBasic loader;
	public static volatile FCLoaderFlat flatloader;

	private static volatile GameProvider gameProvider;
	private static volatile boolean loadersInitialized;
	private static volatile boolean modsRun;

	private BootstrapCommon() {
	}

	/**
	 * Initializes FeatureCreep for a game provider, discovers mods, initializes
	 * Sponge Mixin, and registers each mod's own Mixin configuration.
	 *
	 * <p>
	 * This method does not launch the game and does not require command-line Mixin
	 * configuration arguments.
	 * </p>
	 */
	public static synchronized void initializeGame(GameProvider provider) {
		if (loadersInitialized) {
			if (gameProvider != provider && !gameProvider.getClass().equals(provider.getClass())) {
				throw new IllegalStateException(
						"FeatureCreep was already initialized with " + gameProvider.getClass().getName()
								+ ", cannot replace it with " + provider.getClass().getName());
			}
			return;
		}

		gameProvider = provider;

		if (instrument != null) {
			provider.setInstrumentation(instrument);
		}

		loader = new FCLoaderBasicR9(provider, 8);
		flatloader = new FCLoaderFlatR1(provider);

		System.out.println("[FeatureCreep] Loading module mods.");
		loader.loadMods();

		System.out.println("[FeatureCreep] Loading flat mods.");
		flatloader.loadMods();

		if (loader.isHotswapNeeded()) {
			if (instrument == null && !initDefault()) {
				throw new IllegalStateException(
						"Module mods require Instrumentation, but the FeatureCreep " + "agent could not be activated.");
			}

			provider.setInstrumentation(instrument);
			loader.setupInstrumentation();
			loader.PremainAgents();
		}

		/*
		 * Mixin must initialize only after module discovery, because the mods own their
		 * configurations and classloaders.
		 */
		SpongeMixinRuntime.initialize(provider, loader, flatloader);
		SpongeMixinRuntime.registerDiscoveredModConfigurations();

		loadersInitialized = true;
	}

	/**
	 * Runs module and flat mod entrypoints after all transformation systems have
	 * been initialized.
	 */
	public static synchronized void runMods() {
		if (!loadersInitialized) {
			throw new IllegalStateException("BootstrapCommon.initializeGame(provider) must run first.");
		}
		if (modsRun) {
			return;
		}

		loader.runMods();
		flatloader.runMods();
		modsRun = true;
	}

	public static GameProvider getGameProvider() {
		return gameProvider;
	}

	public static boolean isGameInitialized() {
		return loadersInitialized;
	}

	public static boolean initDefault() {
		if (instrument != null && agent_activated) {
			return true;
		}

		activateAgent(getJar());

		if (instrument != null && agent_activated) {
			return true;
		}

		try {
			ClassLoader sys = ClassLoader.getSystemClassLoader();
			Class<?> agentClass = Class.forName("asbestosstar.bootstrap.FeatureCreepAgent", false, sys);

			Method method = agentClass.getMethod("getInstrumentation");
			Object obtained = method.invoke(null);

			if (obtained instanceof Instrumentation) {
				instrument = (Instrumentation) obtained;
				agent_activated = true;
				return true;
			}
		} catch (Throwable t) {
			System.err.println("[BootstrapCommon] Could not recover Instrumentation: " + t);
			t.printStackTrace(System.err);
		}

		return false;
	}

	public static @Nullable Instrumentation activateAgent(String pathToAgent) {
		if (instrument != null) {
			return instrument;
		}
		if (pathToAgent == null) {
			return null;
		}

		if (hasJdkAttach()) {
			try {
				if (jdkAttachSelf(pathToAgent, "")) {
					return instrument;
				}
			} catch (Throwable t) {
				System.err.println("[BootstrapCommon] JDK attach failed; trying fallback: " + t);
			}
		}

		if (classExists("featurecreep.attach.Attach")) {
			try {
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				Class<?> attachClass = Class.forName("featurecreep.attach.Attach", true, classLoader);
				Method attachMethod = attachClass.getMethod("attach", String.class, String.class);
				attachMethod.invoke(null, pathToAgent, "");
			} catch (Throwable t) {
				System.err.println("[BootstrapCommon] FeatureCreep attach failed: " + t);
				t.printStackTrace(System.err);
			}
		}

		return instrument;
	}

	private static boolean jdkAttachSelf(String agentPath, String args) throws Exception {
		Class<?> vmClass = Class.forName("com.sun.tools.attach.VirtualMachine");
		Class<?> attachExceptionClass = Class.forName("com.sun.tools.attach.AttachNotSupportedException");

		Method attach = vmClass.getMethod("attach", String.class);
		Method loadAgent = vmClass.getMethod("loadAgent", String.class, String.class);
		Method detach = vmClass.getMethod("detach");

		String pid = Long.toString(ProcessHandle.current().pid());
		Object vm = null;

		try {
			try {
				vm = attach.invoke(null, pid);
			} catch (InvocationTargetException exception) {
				Throwable cause = exception.getCause();
				if (cause != null && attachExceptionClass.isInstance(cause)) {
					return false;
				}
				throw exception;
			}

			loadAgent.invoke(vm, agentPath, args == null ? "" : args);
			return true;
		} finally {
			if (vm != null) {
				try {
					detach.invoke(vm);
				} catch (Throwable ignored) {
				}
			}
		}
	}

	private static boolean hasJdkAttach() {
		try {
			Class.forName("com.sun.tools.attach.VirtualMachine", false, BootstrapCommon.class.getClassLoader());
			return true;
		} catch (ClassNotFoundException exception) {
			return false;
		}
	}

	public static boolean classExists(String name) {
		try {
			Class.forName(name, false, BootstrapCommon.class.getClassLoader());
			return true;
		} catch (ClassNotFoundException exception) {
			return false;
		}
	}

	public static @Nullable String getJar() {
		try {
			URI source = BootstrapCommon.class.getProtectionDomain().getCodeSource().getLocation().toURI();

			String value = source.toString();
			if (value.startsWith("union:")) {
				value = value.replace("union:", "file://");
			}
			if (value.startsWith("jar:")) {
				value = value.substring(4);
			}

			String path = new URI(value).getPath();
			return new File(path).getAbsolutePath().split("\\.jar")[0] + ".jar";
		} catch (Exception exception) {
			System.err.println("[BootstrapCommon] Could not locate bootstrap jar.");
			exception.printStackTrace(System.err);
			return null;
		}
	}
}
