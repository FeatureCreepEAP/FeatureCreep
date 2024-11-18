package featurecreep.api.clausewitz.mod;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import featurecreep.api.io.BasicIO;
import featurecreep.loader.filesystem.FileSystem;
import featurecreep.loader.filesystem.PhilKatzZip;

public class FileSystemClausewitzModLoader implements ClausewitzModLoader<FileSystem> {

	public ArrayList<Mod> mods = new ArrayList<Mod>();

	/**
	 * A new Clausewitz Mod
	 * 
	 * @param fs The Filesystem to search for the .mod files and load the mods
	 */
	public void search(FileSystem searchfs) {
		Map<String, ModFile> mfs = setupModFile(searchfs);
		for (Map.Entry<String, ModFile> entry : mfs.entrySet()) {
			String path = entry.getKey();
			ModFile file = entry.getValue();
			FileSystem fs = getFileSystem(searchfs ,path, file);
			String name = getName(file,path);
		if(fs!=null) {
			Mod mod = new FileSystemMod(fs,file);
			mod.setName(name);
			mods.add(mod);
		}
		
		}

	}



	@Override
	public List<Mod> getMods() {
		// TODO Auto-generated method stub
		return mods;
	}

	@Override
	public Map<String, ModFile> setupModFile(FileSystem search) {
		// TODO Auto-generated method stub
		Map<String, ModFile> files = new HashMap<String, ModFile>();
		for (String name : search.getFilenames("")) {
			if (name.endsWith(".mod")) {
				try {
					files.put(name, ModFile.parseModFile(BasicIO.byteArrayToString(search.get(name))));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return files;

	}

	public FileSystem getFileSystem(FileSystem searchfs, String path, ModFile file) {
		String folder = ClausewitzModLoader.getFolderName(path);
		String modpath = file.getPath();
		String archive = file.getArchive();
		if (modpath != null) {
			if (!modpath.endsWith("/") && !modpath.isEmpty()) {
				modpath = modpath + "/";
			}

			String without_mod;
			if (modpath.startsWith("mod/")) {
				without_mod = modpath.substring(4);
			} else {
				without_mod = modpath;
			}

			String dir = folder + without_mod;
			if (searchfs.has(dir)) { // Prioritise relative
				return searchfs.createSubFileSystem(dir);
			} else if (searchfs.has(folder + modpath)) {
				return searchfs.createSubFileSystem(folder + modpath);
			} else if (searchfs.has(without_mod)) {// Search the root folder now
				return searchfs.createSubFileSystem(without_mod);
			} else if (searchfs.has(modpath)) {
				return searchfs.createSubFileSystem(modpath);
			}

		} else if (archive != null) {// Prioritise path over archive

			if (searchfs.has(folder + archive)) {// Prioritise relative
				URL url = searchfs.getURLForFile(folder + archive);
				URI uri = null;
				if (url != null) {
					try {
						uri = url.toURI();
					} catch (URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				try {
					return new PhilKatzZip(searchfs.getStream(folder + archive), uri);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
			} else if (searchfs.has(archive)) {

				URL url = searchfs.getURLForFile(archive);
				URI uri = null;
				if (url != null) {
					try {
						uri = url.toURI();
					} catch (URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				try {
					return new PhilKatzZip(searchfs.getStream(archive), uri);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}

			}

		}
		return null;

	}

}
