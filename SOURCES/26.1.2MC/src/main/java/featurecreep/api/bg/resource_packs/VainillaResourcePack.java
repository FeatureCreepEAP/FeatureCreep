package featurecreep.api.bg.resource_packs;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mojang.serialization.JsonOps;

import featurecreep.api.annotations.Internal;
import featurecreep.api.annotations.Vainilla;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.OverlayedPackResources;
import net.minecraft.server.packs.PackMetadataResources;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.MetadataSectionType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.Pack.Metadata;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.resources.IoSupplier;

public interface VainillaResourcePack extends PackResources {

	public static String METADATA_PATH_SUFFIX = ".mcmeta";
	public static String PACK_METADATA_NAME = "pack.mcmeta";

	/**
	 * Returns a Supplier for an inputstream
	 * 
	 * @param location Location of the file in the pack
	 * @return
	 */
	public Supplier<InputStream> getStream(String location);

	@Override
	@Vainilla
	public default IoSupplier<InputStream> getRootResource(String... var1) {
		String name = String.join("/", var1);
		if (name.equals("pack.png")) {
			Supplier<InputStream> sup = getPackPng();
			if (sup != null) {
				return desdeSupplierNormal(sup);
			} else {
				return null;
			}
		}

		return desdeSupplierNormal(this.getStream(name));

	}

	/**
	 * Gets the pack.png for the resource pack. It should be in png format. It null
	 * if none found
	 * 
	 * @return
	 */
	public Supplier<InputStream> getPackPng();

	@Internal
	public static IoSupplier<InputStream> desdeSupplierNormal(Supplier<InputStream> supplier) {
		if (supplier == null) {
			return null;
		}

		// IoSupplier represents an operation that opens the resource. Do not eagerly
		// open and capture one InputStream: Minecraft may ask for the same resource
		// more than once and closes streams after reading them.
		return supplier::get;
	}

	@Override
	@Vainilla
	public default IoSupplier<InputStream> getResource(PackType var1, Identifier var2) {
		FCResourceType type = FCResourceType.checkBuiltIn(var1.getDirectory());
		String path = toPath(type, var2.getNamespace(), var2.getPath());
		return desdeSupplierNormal(getStream(this.appendOverlayPrefix(path)));
	}

	@Internal
	public static String toPath(FCResourceType arg, String namespace, String path) {
		return String.format(Locale.ROOT, "%s/%s/%s", arg.getDirectory(), namespace, path);
	}

	@Override
	@Vainilla
	public default void listResources(PackType dir, String namespace, String object_type, ResourceOutput output) {

		String namespaceLocation = this.appendOverlayPrefix(dir.getDirectory() + "/" + namespace + "/");
		String searchPath = namespaceLocation + object_type + "/";
		for (String entry : getEntries(searchPath)) {
			if (!entry.endsWith("/")) {
				String fileNameWithinNamespace = entry.substring(namespaceLocation.length());
				Identifier location = Identifier.fromNamespaceAndPath(namespace, fileNameWithinNamespace);
				output.accept(location, desdeSupplierNormal(getStream(entry)));
			}
		}

	}

	/**
	 * Returns a collection of all the paths. Folders should end with /
	 * 
	 * @param prefix based on the MC Resource Pack Structure the location to include
	 *               Files/Directories from
	 * @return
	 */
	public Collection<String> getEntries(String prefix);

	/**
	 * The namespaces/modids/pack prefixes. e.g. assets/prefix/textures/postfix.png
	 * 
	 * @return A set of unique prefixes (namespaces).
	 */
	public default Set<String> getPackPrefixes() {
		Set<String> prefixes = new HashSet<>();

		// Get all entries with an empty prefix to retrieve the root-level entries
		Collection<String> entries = getEntries("");

		for (String entry : entries) {
			// Check if the entry starts with "assets/" or "data/"
			if (entry.startsWith("assets/") || entry.startsWith("data/")) {
				// Remove "assets/" or "data/" prefix
				String withoutPrefix = entry.startsWith("assets/") ? entry.substring(7) : entry.substring(5);
				// Extract the prefix, which is the segment before the next '/'
				int slashIndex = withoutPrefix.indexOf('/');
				if (slashIndex != -1) {
					String prefix = withoutPrefix.substring(0, slashIndex);
					prefixes.add(prefix); // Add the unique prefix to the set
				} else {
					// If there's no additional '/', the entire string is the prefix
					prefixes.add(withoutPrefix);
				}
			}
		}

		return prefixes;
	}

	@Override
	@Vainilla
	public default Set<String> getNamespaces(PackType var1) {
		return getPackPrefixes();
	}

	@Override
	@Vainilla
	public default <T> T getMetadataSection(MetadataSectionType<T> type) throws IOException {
		Gson gson = new Gson();
		JsonObject metadata = gson.getAdapter(JsonObject.class).fromJson(getPackMCMetaInfo().asJSON());
		JsonElement rawSection = metadata.get(type.name());
		if (rawSection == null) {
			return null;
		}

		return type.codec().parse(JsonOps.INSTANCE, rawSection).getOrThrow(JsonParseException::new);
	}

	public FCPackMCMeta getPackMCMetaInfo();

	@Override
	@Vainilla
	public default PackLocationInfo location() {
		return new PackLocationInfo(getPackName(), Component.literal(getPackName()), PackSource.BUILT_IN,
				Optional.empty());
	}

	/**
	 * Resource Pack Name
	 * 
	 * @return
	 */
	public String getPackName();

	@Vainilla
	@Override
	public default String packId() {
		return getPackName();
	}

	@Vainilla
	@Override
	public default void close() {
		closeStreams();
	}

	/**
	 * Close the ResourcePack and all its streams
	 */
	public void closeStreams();

	/**
	 * For Minecraft 1.13+ the Resource Packs have a feature called overlays which
	 * are like subpacks, this is the prefix for those
	 * 
	 * @param string
	 * @return
	 */
	public default String getOverlay() {
		return "";
	}

	/**
	 * For Minecraft 1.13+ the Resource Packs have a feature called overlays which
	 * are like subpacks, this adds the prefix for those
	 * 
	 * @param string
	 * @return
	 */
	public default String appendOverlayPrefix(String string) {
		if (this.getOverlay().isEmpty()) {
			return string;
		}
		return this.getOverlay() + "/" + string;
	}

	/**
	 * Checks if a resource Exists
	 * 
	 * @param resource
	 * @return
	 */
	public default boolean hasResource(String resource) {
		return this.getEntries(resource).contains(resource);
	}

	public default VainillaResourcePack getVainillaResourcePack(String overley) {
		return this;
	}

	@Internal
	public default Loader getLoader() {
		return (overley) -> {
			return getVainillaResourcePack(overley);
		};
	}

	public default boolean isEmpty() {
		return getEntries("assets/").isEmpty() && getEntries("data/").isEmpty();
	}

	@Internal
	@FunctionalInterface
	public static interface Loader extends Pack.ResourcesSupplier {

		public VainillaResourcePack getPack(String overlay);

		@Override
		public default PackMetadataResources openMetadata(PackLocationInfo location) {
			return getPack("");
		}

		@Override
		public default Stream<PackResources> openResources(PackLocationInfo location, Metadata metadata) {
			VainillaResourcePack primary = getPack("");
			List<String> overlays = metadata.overlays();
			if (overlays.isEmpty()) {
				return Stream.of(primary);
			}

			ArrayList<PackResources> overlayResources = new ArrayList<>(overlays.size());
			for (String overlay : overlays) {
				overlayResources.add(getPack(overlay));
			}

			return Stream.of(new OverlayedPackResources(primary, overlayResources));
		}

	}
}
