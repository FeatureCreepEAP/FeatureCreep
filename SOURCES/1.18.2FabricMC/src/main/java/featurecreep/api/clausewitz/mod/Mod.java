package featurecreep.api.clausewitz.mod;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Clausewitz/HOI4 mod
 */
public interface Mod {

	public ModFile getModFile();

	public default boolean hasModFile() {
		return getModFile() != null;
	}

	public String getName();

	public void setName(String name);

	public byte[] get(String name) throws FileNotFoundException, IOException;

	public InputStream getStream(String name) throws FileNotFoundException, IOException;

	/**
	 * A list of all the files/directories. Directories should end in /
	 * 
	 * @return
	 */
	public List<String> getEntries();

	public default boolean hasEntry(String name) {
		for (String entry : getEntries()) {
			if (entry.equals(name)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * A list of all the files/directories beginning with the prefix. Directories
	 * should end in /
	 * 
	 * @return
	 */
	public default List<String> getEntries(String prefix) {
		ArrayList<String> entries = new ArrayList<String>();
		for (String entry : getEntries()) {
			if (entry.startsWith(prefix)) {
				entries.add(entry);
			}
		}
		return entries;
	}

	/**
	 * Returns everything from the default GFX folder Most Entries are .dds
	 * 
	 * @return
	 */
	public default List<String> getGFXLocations() {
		return getEntries("gfx/");
	}

	/**
	 * Returns everything from the default GFX Interface folder Most Entries are
	 * .dds
	 * 
	 * @return
	 */
	public default List<String> getInterfaceGFXLocations() {
		return getEntries("gfx/interface/");
	}

	/**
	 * Returns everything from the default GFX Models folder Most Entries are .dds
	 * 
	 * @return
	 */
	public default List<String> getModelsGFXLocations() {
		return getEntries("gfx/models/");
	}

	/**
	 * Returns everything from the default GFX interface technology folder. Most
	 * Entries are .dds
	 * 
	 * @return
	 */
	public default List<String> getTechnologiesInterfaceGFXLocations() {
		return getEntries("gfx/interface/technologies/");
	}

	/**
	 * Returns everything from the default GFX interface terrains folder Most
	 * Entries are .dds
	 * 
	 * @return
	 */
	public default List<String> getTerrainsInterfaceGFXLocations() {
		return getEntries("gfx/interface/terrains/");
	}

	/**
	 * Returns everything from the default GFX Armour Models folder. This is mainly
	 * for FeatureCreep itself. Most entries are .png
	 * 
	 * @return
	 */
	public default List<String> getArmourModelsGFXLocations() {
		return getEntries("gfx/models/");
	}

}
