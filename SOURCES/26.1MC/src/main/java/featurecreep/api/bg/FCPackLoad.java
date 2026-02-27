package featurecreep.api.bg;

import featurecreep.FeatureCreep;
import featurecreep.api.anti_encapsulation.GoogleCommonsImmutableMutaliser;
import featurecreep.api.bg.resource_packs.VainillaResourcePack;
import featurecreep.api.platform.super_.SuperLoader;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.Pack.Position;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.repository.RepositorySource;
import net.minecraft.server.packs.PathPackResources.PathResourcesSupplier;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackSelectionConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.jar.Manifest;

import com.google.common.collect.ImmutableSet;

public class FCPackLoad implements RepositorySource {

	public static FCPackLoad INSTANCE = new FCPackLoad();
	
	
	@Override
	public void loadPacks(Consumer<Pack> consumer) {
		// TODO Auto-generated method stub

		for (Entry<String, VainillaResourcePack> entry : PackLoader.packs.entrySet()) {
			VainillaResourcePack pack = entry.getValue();
			if (!isNative(pack) && !pack.isEmpty()) {

				PackLocationInfo loc = new PackLocationInfo(pack.getPackName(), (Component)null, PackSource.BUILT_IN, java.util.Optional.empty());
				Pack packInfo = Pack.readMetaAndCreate(loc, pack.getLoader(),
						PackType.SERVER_DATA, // No longer
						// need to
						// worry
						// about
						// pack
						// version
						// and its
						// fast
						// moving
						// eh!
						new PackSelectionConfig(true,
								Position.TOP.opposite(), true));// I need to check that this
																							// also does client stuff
				
				
				
				PackLocationInfo clientLoc = new PackLocationInfo(pack.getPackName(), (Component)null, PackSource.BUILT_IN, java.util.Optional.empty());
				Pack clientInfo = Pack.readMetaAndCreate(clientLoc, pack.getLoader(),
						PackType.CLIENT_RESOURCES, // No longer
						// need to
						// worry
						// about
						// pack
						// version
						// and its
						// fast
						// moving
						// eh!
						new PackSelectionConfig(true,
								Position.TOP.opposite(), true));// I need to check that this
																							// also does client stuff
				
				
				if (packInfo != null) {
					System.out.println("Adding FCDatapack "+pack.getPackName());
					consumer.accept(packInfo);
					consumer.accept(clientInfo);
				}
			}

		}
	}

	/**
	 * Checks if a resourcepack file is native to the super loader
	 * 
	 * @param pack
	 * @return
	 */
	public boolean isNative(VainillaResourcePack pack) {
		// TODO Auto-generated method stub
		if (FeatureCreep.super_loader.equals(SuperLoader.CYAN)) {
			// TODO 
		} else if (FeatureCreep.super_loader.equals(SuperLoader.DANGERZONE_BUILTIN_LOADER)) {
			// DO Nothing
		} else if (FeatureCreep.super_loader.equals(SuperLoader.HEARTS_OF_IRON_IV_BUILTIN_LOADER)) {
			// DO Nothing
		} else if (FeatureCreep.super_loader.equals(SuperLoader.FABRICMC)) {
			return pack.hasResource("fabric.mod.json");
		} else if (FeatureCreep.super_loader.equals(SuperLoader.QUILTMC)) {
			return pack.hasResource("quilt.mod.json")||pack.hasResource("fabric.mod.json");
		} else if (FeatureCreep.super_loader.equals(SuperLoader.QUILTMC)) {
			return pack.hasResource("mcmod.info") || pack.hasResource("META-INF/mods.toml");// others exist but only for
																							// cpw services which are
																							// rarely mixed with
																							// resource packs or even
																							// used
		} else if (FeatureCreep.super_loader.equals(SuperLoader.LITELOADER)) {
			return pack.hasResource("litemod.json");
		} else if (FeatureCreep.super_loader.equals(SuperLoader.RIFT)) {
			return pack.hasResource("riftmod.json");
		} else if (FeatureCreep.super_loader.equals(SuperLoader.FLINTMC)) {
			return pack.hasResource("flintmodule.json");
		} else if (FeatureCreep.super_loader.equals(SuperLoader.LITLAUNCHMC)) {
			// Do nothing, these generally do not have vainilla packs in them and are often
			// loaded on top of another, though this may cause issues for when running on
			// top of another
		} else if (FeatureCreep.super_loader.equals(SuperLoader.LOADERCOMPLEX)) {
			// Do nothing, these generally do not have vainilla packs in them and are often
			// loaded on top of another, though this may cause issues for when running on
			// top of another
			Supplier<InputStream> sup = pack.getStream("META-INF/MANIFEST.MF");
			if (sup != null) {
				InputStream stream = sup.get();
				if (stream != null) {
					try {
						Manifest man = new Manifest(stream);
						return man.getEntries().containsKey("LoaderComplex-AddonId");// Only one is needed to check as
																						// it is required
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}else if(FeatureCreep.super_loader.equals(SuperLoader.M3L)) {
			//TODO but likely nothing
		}else if(FeatureCreep.super_loader.equals(SuperLoader.MCPATCHER)) {
			//do nothing
		}else if(FeatureCreep.super_loader.equals(SuperLoader.MEDDLE)) {
			//TODO
		}else if(FeatureCreep.super_loader.equals(SuperLoader.NEOFORGE)) {
			return pack.hasResource("META-INF/neoforge.mods.toml") || pack.hasResource("META-INF/mods.toml");// others exist but only for
			// cpw services which are
			// rarely mixed with
			// resource packs or even
			// used
			}
		else if(FeatureCreep.super_loader.equals(SuperLoader.NILLOADER)) {
			return pack.hasResource("modid.nilmod.css");// This loader is not very well documented so i dont know if the name is literal or dynamic
		}else if(FeatureCreep.super_loader.equals(SuperLoader.OPENMODLOADER)) {
			//TODO
		}else if(FeatureCreep.super_loader.equals(SuperLoader.PIDGEON)) {
			//TODO
		}else if(FeatureCreep.super_loader.equals(SuperLoader.RISUGAMIS_MODLOADER)) {
			//TODO
		}else if(FeatureCreep.super_loader.equals(SuperLoader.ROPEMC)) {
			//TODO
		}else if(FeatureCreep.super_loader.equals(SuperLoader.SILKPOWERED)) {
			//TODO
			pack.hasResource("fabric.mod.json");
		}else if(FeatureCreep.super_loader.equals(SuperLoader.VANILLA_MINECRAFT)) {
			//do nothing
		}

		return false;
	}
	
	
	public static void updateProviders(Set<RepositorySource> providers) {
		if(providers instanceof ImmutableSet) {//I doubt it will be anything but regularimmutbleset
			GoogleCommonsImmutableMutaliser.addToRegularImmutableSet(INSTANCE, providers);
		}else {//Sometimes,like with FabricAPI, the type is changed, such as by fabric api, lets hope its not immutable
			providers.add(INSTANCE);
		}
		
		
	}
	

}