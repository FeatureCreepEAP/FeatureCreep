package featurecreep.api.bg.resource_packs;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import co.phoenixlab.dds.Dds;
import co.phoenixlab.dds.DdsImageDecoder;
import featurecreep.api.bg.PackLoader;
import featurecreep.api.bg.orespawn.OrespawnBasicFeatureParser;
import featurecreep.api.clausewitz.mod.Mod;
import featurecreep.api.io.BasicIO;
import featurecreep.api.parsers.ParseDMRItem;
import javassist.NotFoundException;

public class ClausewitzModResourcePack implements VainillaResourcePack {

	public Mod mod;
	public Map<String, byte[]> entries = new HashMap<String, byte[]>();

	public ClausewitzModResourcePack(Mod mod) {
		this.mod = mod;
		refresh();
	}

	@Override
	public Supplier<InputStream> getStream(String location) {
		// TODO Auto-generated method stub
		byte[] get = entries.get(location);
		if (get != null) {
			return BasicIO.inputStreamSupplierFromBytes(get);
		}

		return null;
	}

	/**
	 * Converts the files from the Clausewitz and FeatureCreep formats to Minecraft
	 * ones
	 */
	public void refresh() {
		// TODO allow for custom item locations
		for (String entry : mod.getTechnologiesInterfaceGFXLocations()) {
			String name = getFileName(entry);
			if (isFileNameBGType(name) && name.endsWith(".dds")) {
				String ns = getNameSpaceFromDDSFileName(name);
				String rl = getNameSpaceFromDDSFileName(name);
				putDDSEntryAsPNG("assets/" + ns + "/textures/item/" + rl + ".png", entry);// iirc older versions it may
																							// be items rather than
																							// item?
			}
		}
		for (String entry : mod.getTerrainsInterfaceGFXLocations()) {
			String name = getFileName(entry);
			if (isFileNameBGType(name) && name.endsWith(".dds")) {
				String ns = getNameSpaceFromDDSFileName(name);
				String rl = getNameSpaceFromDDSFileName(name);
				putDDSEntryAsPNG("assets/" + ns + "/textures/block/" + rl + ".png", entry);// iirc older versions it may
																							// be blocks rather than
																							// block?
			}
		}

		for (String entry : mod.getArmourModelsGFXLocations()) {
			String name = getFileName(entry);
			if (name.endsWith(".png")) {
				String ns = getNameSpaceFromDDSFileName(name);
				String rl = getNameSpaceFromDDSFileName(name);
				entries.put("assets/" + ns + "/textures/models/armor/" + rl + ".png", getModBytes(entry));// iirc older
																											// versions
																											// it may be
																											// blocks
																											// rather
																											// than
																											// block?
			}
		}

		for (String entry : mod.getEntries("datafiedcontents/items/")) { // Part of FC BG
			if (entry.endsWith(".dmr") || entry.endsWith(".json")) {// DMR Items
				InputStream stream = this.getModStream(entry).get();
				ParseDMRItem.registerFromStream(stream);
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		for (String entry : mod.getEntries("orespawn/config/")) { // Part of FC BG
			if (entry.endsWith(".dmr") || entry.endsWith(".json")) {
				InputStream stream = this.getModStream(entry).get();
				OrespawnBasicFeatureParser.registerFromStream(stream);
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * Puts an entry
	 */
	public void putDDSEntryAsPNG(String vainilla_path, String dds_entry) {
		try {
			entries.put(vainilla_path, convertDDSToPNG(mod.get(dds_entry)));
		} catch (NotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static byte[] convertDDSToPNG(byte[] dds_bytes) {
		Dds dds = new Dds();
		byte[] ret = null;
		try {
			dds.read(dds_bytes);
			DdsImageDecoder decoder = new DdsImageDecoder();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			decoder.convertToPNG(dds, outputStream);
			ret = outputStream.toByteArray();
			outputStream.flush();
			outputStream.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * Similar to getStream on vanilla but the normal getStream here does the
	 * conversion
	 * 
	 * @param location
	 * @return
	 */
	public byte[] getModBytes(String location) {
		byte[] stream;
		try {
			stream = mod.get(location);
		} catch (NotFoundException | IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return null;
		}

		return stream;
	}

	/**
	 * Similar to getStream on vanilla but the normal getStream here does the
	 * conversion
	 * 
	 * @param location
	 * @return
	 */
	public Supplier<InputStream> getModStream(String location) {
		InputStream stream;
		try {
			stream = mod.getStream(location);
		} catch (NotFoundException | IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return null;
		}

		return () -> stream;
	}

	/**
	 * Similar to getStream on vanilla but the normal getStream here does the
	 * conversion
	 * 
	 * @param location
	 * @return
	 */
	public InputStream getUnsuppliedModStream(String location) {
		InputStream stream;
		try {
			stream = mod.getStream(location);
		} catch (NotFoundException | IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return null;
		}

		return stream;
	}

	@Override
	public Supplier<InputStream> getPackPng() {
		// TODO Auto-generated method stub
		if (mod.hasModFile()) {
			if (mod.getModFile().getPicture() != null) {
				return getStream(mod.getModFile().picture);
			}

		}
		return null;
	}

	@Override
	public Collection<String> getEntries(String prefix) {
		// TODO Auto-generated method stub
		ArrayList<String> strs = new ArrayList<String>();
		for(String str:entries.keySet()) {
			if(str.startsWith(prefix)) {strs.add(str);}
		}
		return strs;
	}

	@Override
	public FCPackMCMeta getPackMCMetaInfo() {
		// TODO Auto-generated method stub
		return new FCPackMCMeta(PackLoader.pack_version, "");
	}

	@Override
	public String getPackName() {
		// TODO Auto-generated method stub
		String name = mod.getName();
		if (PackLoader.packLoaderFCHasPack(name)) {
			name = name + "_clausewitz";
		}
		return name;
	}

	@Override
	public void closeStreams() {
		// TODO Auto-generated method stub

	}

	public static boolean isItemGFX(String location) {
		// TODO Auto-generated method stub
		if (location.startsWith("assets/")) {
			String[] dirs = location.split("/");
			if (dirs.length == 4) {
				if (dirs[2].equals("textures") && dirs[3].equals("item")) {
					return true;
				} // Isnt it items in some versions? Need to make that a costant somewhere
			}
		}

		return false;
	}

	public static boolean isBlockGFX(String location) {
		// TODO Auto-generated method stub
		if (location.startsWith("assets/")) {
			String[] dirs = location.split("/");
			if (dirs.length == 4) {
				if (dirs[2].equals("textures") && dirs[3].equals("block")) {
					return true;
				} // Isnt it blocks in some versions? Need to make that a costant somewhere
			}
		}

		return false;
	}

	public static boolean isArmourGFX(String location) {
		// TODO Auto-generated method stub
		if (location.startsWith("assets/")) {
			String[] dirs = location.split("/");
			if (dirs.length == 5) {
				if (dirs[2].equals("textures") && dirs[3].equals("models") && dirs[3].equals("armor")) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Gets only the file name and removes the folder name
	 * 
	 * @param full_path the full path with the folder name
	 * @return
	 */
	public static String getFileName(String full_path) {
		if (full_path.endsWith("/")) {
			return "";
		}
		File fil = new File(full_path);
		return fil.getName();
	}

	/**
	 * Checks if the file name contains the Unicode characters for "!" or "§".
	 * 
	 * @param file_name the file name to check
	 * @return true if the file name contains either "!" (Unicode \u0021) or "§"
	 *         (Unicode \u00A7), false otherwise
	 */
	public static boolean isFileNameBGType(String file_name) {
		// Check for the presence of "!" (Unicode \u0021) or "§" (Unicode \u00A7)
		return file_name.contains("\u0021") || file_name.contains("\u00A7");
	}

	/**
	 * gets the name space and resource location from the place file name of dds
	 * items divided by § or !
	 * 
	 * @param file_name
	 * @return 0 is the namespace and 1 is the location
	 */
	public static String[] getNameSpaceAndLocationFromDDSFileName(String file_name) {
		// Remove the dds file extension
		String result = file_name.substring(0, file_name.length() - 4);

		// Check if the string contains either "!" or "§" and split accordingly
		if (result.contains("\u0021")) { // Unicode for "!"
			return result.split("\u0021");
		} else {
			return result.split("\u00A7");// Unicode for "§"
		}
	}

	/**
	 * gets the name space from the place file name of dds items divided by § or !
	 * 
	 * @param file_name
	 * @return
	 */
	public static String getNameSpaceFromDDSFileName(String file_name) {
		return getNameSpaceAndLocationFromDDSFileName(file_name)[0];
	}

	/**
	 * gets the name space from the place file name of dds items divided by § or !
	 * 
	 * @param file_name
	 * @return
	 */
	public static String getLocationFromDDSFileName(String file_name) {
		return getNameSpaceAndLocationFromDDSFileName(file_name)[1];
	}

}
