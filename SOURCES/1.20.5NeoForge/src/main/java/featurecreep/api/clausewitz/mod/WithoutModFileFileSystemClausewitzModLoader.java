package featurecreep.api.clausewitz.mod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import featurecreep.api.io.BasicIO;
import featurecreep.loader.filesystem.FileSystem;

public class WithoutModFileFileSystemClausewitzModLoader implements ClausewitzModLoader<FileSystem> {

	public ArrayList<Mod> mods = new ArrayList<Mod>();

	/**
	 * A new Clausewitz Mod
	 * 
	 * @param fs The Filesystem to make a mod. the root of the filesystem is used
	 */
	public void search(FileSystem fs, String name) {
		Map<String, ModFile> mfs = setupModFile(fs);
		if(mfs.isEmpty()) { //Make sure its not read by another loader
			Mod mod = new FileSystemMod(fs);
			mod.setName(name);
			mods.add(mod);
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
			if (name.endsWith(".mod")&&!name.contains("/")) {
				ModFile fil = null;
				try {
					fil = ModFile.parseModFile(BasicIO.byteArrayToString(search.get(name)));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(fil.getPath()!=null) {
			if(fil.getPath().isEmpty()||fil.getPath().equals("/")) {
				files.put(name, fil);
			}
			}else if(fil.getArchive()==null){
				fil.setPath("");
				files.put(name, fil);
			}
			}
		}

		return files;

	}

	public FileSystem getFileSystem(FileSystem searchfs, String path, ModFile file) {
		String folder = ClausewitzModLoader.getFolderName(path);
		String modpath = file.getPath();
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

		

		}
		return null;

	}

}
