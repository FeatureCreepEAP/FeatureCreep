package featurecreep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class FeatureCreepMCInit implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Loading FeatureCreep Initialisation Class");

		FeatureCreep.onInitialise();
		// FeatureCreepMC.onInitialize();

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			FeatureCreep.registerFCDNF(dispatcher);
		});

	}
	
	
	
	
	
	
	//https://github.com/gudenau/minecraft-fcm/blob/master/src/main/java/net/gudenau/minecraft/fcm/Plugin.java Thanks for helping
//	  public void premain() {
//	        try {
//	            // Access the class loader and its delegate field
//	            ClassLoader classLoader = FeatureCreepMCInit.class.getClassLoader();
//	            Field delegateField = classLoader.getClass().getDeclaredField("delegate");
//	            delegateField.setAccessible(true); // Might require --add-opens for module access
//	            Object delegate = delegateField.get(classLoader);
//	            Class<?> knotClassDelegateClass = delegateField.getType();
//
//	            // Access the mixinTransformer field
//	            Field mixinTransformerField = knotClassDelegateClass.getDeclaredField("mixinTransformer");
//	            mixinTransformerField.setAccessible(true); // Might require --add-opens
//	            Object mixinTransformer = mixinTransformerField.get(delegate);
//
//	            // Generate proxy class
//	            byte[] proxyClassBytes = generateProxyClass();
//	            Class<?> mixinTransformerProxy = defineHiddenClass(proxyClassBytes);
//
//	            // Create proxy instance and set the parent
//	            Object proxy = mixinTransformerProxy.getDeclaredConstructor().newInstance();
//	            Field parentField = mixinTransformerProxy.getDeclaredField("parent");
//	            parentField.setAccessible(true); // Required to modify private fields
//	            parentField.set(proxy, mixinTransformer);
//
//	            mixinTransformerField.set(delegate, proxy);
//	        } catch (Exception e) {
//	        	e.printStackTrace();
//	        }
//	    }
//
//	    // Generates the proxy class
//	    private byte[] generateProxyClass() {
//	    	ClassFile fil = new ClassFile(false, "org/spongepowered/asm/mixin/transformer/MixinTransformerProxy", "org/spongepowered/asm/mixin/transformer/MixinTransformer");
//	    	
//	    	
//	        // Implementation of bytecode generation (provided in earlier example)
//	        return null; // Replace with actual bytecode logic
//	    }
//
//	    // Defines a hidden class using modern Java APIs
//	    private Class<?> defineHiddenClass(byte[] classBytes) throws Exception {
//	        // Using MethodHandles for hidden class definition
//	        MethodHandles.Lookup lookup = MethodHandles.lookup();
//	        return lookup.defineHiddenClass(classBytes, true, MethodHandles.Lookup.ClassOption.NESTMATE).lookupClass();
//	    }

}
