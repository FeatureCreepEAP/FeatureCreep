package featurecreep.api.bg.resource_packs;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import co.phoenixlab.dds.Dds;
import co.phoenixlab.dds.DdsImageDecoder;
import featurecreep.api.bg.PackLoader;
import featurecreep.api.bg.VersionDependentContstants;
import featurecreep.api.clausewitz.mod.Mod;
import featurecreep.api.hashing.Md5;
import featurecreep.api.io.BasicIO;

public class ClausewitzModResourcePack implements VainillaResourcePack {

	public static File pngcaches = new File(System.getProperty("user.dir") + "/cachesfc/dds2png/");

	public Mod mod;
	// Use ConcurrentHashMap for thread safety
	public Map<String, byte[]> entries = new ConcurrentHashMap<>();

	// Map for per-file locks to prevent race conditions
	private final ConcurrentMap<String, Object> fileLocks = new ConcurrentHashMap<>();

	public ClausewitzModResourcePack(Mod mod) {
		this.mod = mod;
		refresh();
	}

	@Override
	public Supplier<InputStream> getStream(String location) {
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
		pngcaches.mkdirs();
		ExecutorService executor = Executors.newFixedThreadPool(4);

		for (String entry : mod.getTechnologiesInterfaceGFXLocations()) {
			final String entryFinal = entry;
			executor.submit(() -> {
				String name = getFileName(entryFinal);
				if (isFileNameBGType(name) && name.endsWith(".dds")) {
					String ns = getNameSpaceFromDDSFileName(name);
					String rl = getLocationFromDDSFileName(name);
					putDDSEntryAsPNG("assets/" + ns + "/textures/" + VersionDependentContstants.ITEM_TEXTURE_LOCATION
							+ "/" + rl + ".png", entryFinal);
				}
			});
		}

		for (String entry : mod.getTerrainsInterfaceGFXLocations()) {
			final String entryFinal = entry;
			executor.submit(() -> {
				String name = getFileName(entryFinal);
				if (isFileNameBGType(name) && name.endsWith(".dds")) {
					String ns = getNameSpaceFromDDSFileName(name);
					String rl = getLocationFromDDSFileName(name);
					putDDSEntryAsPNG("assets/" + ns + "/textures/" + VersionDependentContstants.BLOCK_TEXTURE_LOCATION
							+ "/" + rl + ".png", entryFinal);
				}
			});
		}

		for (String entry : mod.getArmourModelsGFXLocations()) {
			final String entryFinal = entry;
			executor.submit(() -> {
				String name = getFileName(entryFinal);
				if (name.endsWith(".png")) {
					String ns = getNameSpaceFromDDSFileName(name);
					String rl = getLocationFromDDSFileName(name);
					entries.put("assets/" + ns + "/textures/models/armor/" + rl + ".png", getModBytes(entryFinal));
				}
			});
		}

		executor.shutdown();
		try {
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Puts an entry
	 */
	public void putDDSEntryAsPNG(String vainilla_path, String dds_entry) {
		try {
			byte[] dds = mod.get(dds_entry);
			String hashString = Md5.getHashFromBytesAsString(dds);
			File png = new File(pngcaches.getCanonicalPath() + "/" + hashString + ".png");

			Object lock = fileLocks.computeIfAbsent(hashString, k -> new Object());

			synchronized (lock) {
				if (!png.exists()) {
					byte[] png_bytes = convertDDSToPNG(dds);
					entries.put(vainilla_path, png_bytes);
					System.out.println(
							"Convertando DDS a PNG. Las cargas futuras serán más rápidas " + png.getCanonicalPath());
					try (FileOutputStream fos = new FileOutputStream(png.getCanonicalPath())) {
						fos.write(png_bytes);
					}
				} else {
					entries.put(vainilla_path, BasicIO.convertInputStreamToByteArray(new FileInputStream(png)));
				}
			}

		} catch (IOException | NoSuchAlgorithmException e) {
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
		} catch (IOException e) {
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
		try {
			return mod.get(location);
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * Similar to getStream on vanilla but the normal getStream here does the
	 * conversion
	 * 
	 * @param location
	 * @return
	 */
	public Supplier<InputStream> getModStream(String location) {
		try {
			InputStream stream = mod.getStream(location);
			return () -> stream;
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * Similar to getStream on vanilla but the normal getStream here does the
	 * conversion
	 * 
	 * @param location
	 * @return
	 */
	public InputStream getUnsuppliedModStream(String location) {
		try {
			return mod.getStream(location);
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public Supplier<InputStream> getPackPng() {
		if (mod.hasModFile()) {
			if (mod.getModFile().getPicture() != null) {
				return getStream(mod.getModFile().picture);
			}
		}
		return null;
	}

	@Override
	public Collection<String> getEntries(String prefix) {
		ArrayList<String> strs = new ArrayList<>();
		for (String str : entries.keySet()) {
			if (str.startsWith(prefix)) {
				strs.add(str);
			}
		}
		return strs;
	}

	@Override
	public FCPackMCMeta getPackMCMetaInfo() {
		return new FCPackMCMeta(PackLoader.pack_version, "");
	}

	@Override
	public String getPackName() {
		String name = mod.getName();
		if (PackLoader.packLoaderFCHasPack(name)) {
			name = name + "_clausewitz";
		}
		return name;
	}

	@Override
	public void closeStreams() {
		// No streams to close in this implementation
	}

	public static boolean isItemGFX(String location) {
		if (location.startsWith("assets/")) {
			String[] dirs = location.split("/");
			if (dirs.length == 4) {
				return dirs[2].equals("textures") && dirs[3].equals("item");
			}
		}
		return false;
	}

	public static boolean isBlockGFX(String location) {
		if (location.startsWith("assets/")) {
			String[] dirs = location.split("/");
			if (dirs.length == 4) {
				return dirs[2].equals("textures") && dirs[3].equals("block");
			}
		}
		return false;
	}

	public static boolean isArmourGFX(String location) {
		if (location.startsWith("assets/")) {
			String[] dirs = location.split("/");
			if (dirs.length == 5) {
				return dirs[2].equals("textures") && dirs[3].equals("models") && dirs[4].equals("armor");
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
		return file_name.contains("\u0021") || file_name.contains("\u00A7");
	}

	/**
	 * Gets the namespace and resource location from the DDS file name divided by §
	 * or !
	 * 
	 * @param file_name
	 * @return String array where index 0 is the namespace and index 1 is the
	 *         location
	 */
	public static String[] getNameSpaceAndLocationFromDDSFileName(String file_name) {
		String result = file_name.substring(0, file_name.length() - 4);
		if (result.contains("\u0021")) { // Unicode for "!"
			return result.split("\u0021");
		} else {
			return result.split("\u00A7"); // Unicode for "§"
		}
	}

	/**
	 * Gets the namespace from the DDS file name divided by § or !
	 * 
	 * @param file_name
	 * @return
	 */
	public static String getNameSpaceFromDDSFileName(String file_name) {
		return getNameSpaceAndLocationFromDDSFileName(file_name)[0];
	}

	/**
	 * Gets the location from the DDS file name divided by § or !
	 * 
	 * @param file_name
	 * @return
	 */
	public static String getLocationFromDDSFileName(String file_name) {
		return getNameSpaceAndLocationFromDDSFileName(file_name)[1];
	}

//TODO. make it so orespawn and datafied items are able to be removed in a pack and make an isEmptyOverride to check for that\

}
