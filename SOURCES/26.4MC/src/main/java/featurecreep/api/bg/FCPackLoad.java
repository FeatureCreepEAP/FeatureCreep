package featurecreep.api.bg;

import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Consumer;

import com.google.common.collect.ImmutableSet;

import featurecreep.api.anti_encapsulation.GoogleCommonsImmutableMutaliser;
import featurecreep.api.bg.resource_packs.VainillaResourcePack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.Pack.Position;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.repository.RepositorySource;

public class FCPackLoad implements RepositorySource {

	public static RepositorySource INSTANCE = new FCPackLoad();

	@Override
	public void loadPacks(Consumer<Pack> consumer) {
		// TODO Auto-generated method stub

		for (Entry<String, VainillaResourcePack> entry : PackLoader.packs.entrySet()) {
			VainillaResourcePack pack = entry.getValue();
			if (!isNative(pack) && !pack.isEmpty()) {

				PackLocationInfo loc = new PackLocationInfo(pack.getPackName(), (Component) null, PackSource.BUILT_IN,
						java.util.Optional.empty());
				Pack packInfo = Pack.readMetaAndCreate(loc, pack.getLoader(), PackType.SERVER_DATA, // No longer
						// need to
						// worry
						// about
						// pack
						// version
						// and its
						// fast
						// moving
						// eh!
						new PackSelectionConfig(true, Position.TOP.opposite(), true));// I need to check that this
																						// also does client stuff

				PackLocationInfo clientLoc = new PackLocationInfo(pack.getPackName(), (Component) null,
						PackSource.BUILT_IN, java.util.Optional.empty());
				Pack clientInfo = Pack.readMetaAndCreate(clientLoc, pack.getLoader(), PackType.CLIENT_RESOURCES, // No
																													// longer
						// need to
						// worry
						// about
						// pack
						// version
						// and its
						// fast
						// moving
						// eh!
						new PackSelectionConfig(true, Position.TOP.opposite(), true));// I need to check that this
																						// also does client stuff

				if (packInfo != null) {
					System.out.println("Adding FCDatapack " + pack.getPackName());
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

		if (pack.hasResource("fabric.mod.json") || pack.hasResource("META-INF/neoforge.mods.toml")
				|| pack.hasResource("META-INF/mods.toml") || pack.hasResource("flintmodule.json")
				|| pack.hasResource("modid.nilmod.css")) {
			return true;
		}

		return false;
	}

	public static void updateProviders(Set<RepositorySource> providers) {
		if (providers instanceof ImmutableSet) {// I doubt it will be anything but regularimmutbleset
			GoogleCommonsImmutableMutaliser.addToRegularImmutableSet(INSTANCE, providers);
		} else {// Sometimes,like with FabricAPI, the type is changed, such as by fabric api,
				// lets hope its not immutable
			providers.add(INSTANCE);
		}

	}

}