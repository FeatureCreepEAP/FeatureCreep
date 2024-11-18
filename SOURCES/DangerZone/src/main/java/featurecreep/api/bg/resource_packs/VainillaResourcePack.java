package featurecreep.api.bg.resource_packs;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.function.Supplier;

import featurecreep.api.annotations.Internal;

public interface VainillaResourcePack {

	public static String METADATA_PATH_SUFFIX = ".mcmeta";
	public static String PACK_METADATA_NAME = "pack.mcmeta";

	/**
	 * Returns a Supplier for an inputstream
	 * 
	 * @param location Location of the file in the pack
	 * @return
	 */
	public Supplier<InputStream> getStream(String location);

	/**
	 * Gets the pack.png for the resource pack. It should be in png format. It null
	 * if none found
	 * 
	 * @return
	 */
	public Supplier<InputStream> getPackPng();


	@Internal
	public static String toPath(FCResourceType arg, String namespace, String path) {
		return String.format(Locale.ROOT, "%s/%s/%s", arg.getDirectory(), namespace, path);
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

	public FCPackMCMeta getPackMCMetaInfo();

	/**
	 * Resource Pack Name
	 * 
	 * @return
	 */
	public String getPackName();

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

	public default boolean isEmpty() {
		return getEntries("assets/").isEmpty()&&getEntries("data/").isEmpty();
	}
	
}
