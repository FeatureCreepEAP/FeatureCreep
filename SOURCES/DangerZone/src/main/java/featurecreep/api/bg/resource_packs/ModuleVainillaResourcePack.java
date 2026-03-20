package featurecreep.api.bg.resource_packs;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

import org.jboss.modules.IterableResourceLoader;
import org.jboss.modules.Module;
import org.jboss.modules.ResourceLoader;

import featurecreep.api.dmr.ModelNode;
import featurecreep.loader.utils.ResourceLoaderObtainer;

// Only works with LOCAL files, not non-local ones. The ResourceLoader needs to be an IteratableResourceLoader
public class ModuleVainillaResourcePack implements VainillaResourcePack {

	public Module mod;
	String overlay;// Does not do anything

	public ModuleVainillaResourcePack(Module mod, String overlay) {
		this.overlay = overlay;
		this.mod = mod;
		ResourceLoaderObtainer.getResourceLoaders(mod);
	}

	public ModuleVainillaResourcePack(Module mod) {
		this(mod, "");
	}

	@Override
	public Supplier<InputStream> getStream(String location) {
		List<InputStream> str = new ArrayList<InputStream>();

		for (ResourceLoader rl : ResourceLoaderObtainer.getResourceLoaders(mod)) {
			if (rl instanceof IterableResourceLoader) {
				IterableResourceLoader iter = (IterableResourceLoader) rl;
				iter.iterateResources("", true).forEachRemaining((res) -> {
					if (res.getName().equals(location)) {
						try {
							str.add(res.openStream());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							str.add(null);
							e.printStackTrace();
						}
					}

				});

			}
		}

		if (str.isEmpty()) {
			return null;
		} else {
			InputStream stream = str.get(0);// There should only be one
			if (stream != null) {
				return () -> stream;
			} else {
				return null;
			}
		}
	}

	@Override
	public Collection<String> getEntries(String prefix) {
		List<String> str = new ArrayList<String>();

		for (ResourceLoader rl : ResourceLoaderObtainer.getResourceLoaders(mod)) {
			if (rl instanceof IterableResourceLoader) {
				IterableResourceLoader iter = (IterableResourceLoader) rl;
				iter.iterateResources("", true).forEachRemaining((res) -> {
					if (res.getName().startsWith(prefix)) {
						str.add(res.getName());

					}
				});

			}
		}

		return str;
	}

	@Override
	public FCPackMCMeta getPackMCMetaInfo() {
		Supplier<InputStream> stream = getStream("pack.mcmeta");
		if (stream != null) {
			InputStream gotten = stream.get();
			if (gotten != null) {
				try {
					return FCPackMCMeta.fromModelNode(ModelNode.fromJSONStream(gotten));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return new FCPackMCMeta(41, getPackName());

	}

	@Override
	public String getPackName() {
		return this.mod.getName();
	}

	@Override
	public void closeStreams() {
		// No need to close anything here as everything is already in memory
	}

	@Override
	public String getOverlay() {
		return this.overlay;
	}

	@Override
	public VainillaResourcePack getVainillaResourcePack(String overlay) {
		if (overlay.equals(this.getOverlay())) {
			return this;
		} else {
			return new ModuleVainillaResourcePack(mod, overlay);
		}
	}

	@Override
	public Supplier<InputStream> getPackPng() {
		// TODO Auto-generated method stub
		return getStream("pack.png");
	}

}
