package featurecreep.api.clausewitz.mod;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.modules.Module;
import org.jboss.modules.ModuleLoadException;

import featurecreep.api.io.BasicIO;
import featurecreep.loader.filesystem.FileSystem;

public class WithoutModFileModuleClausewitzModLoader implements ClausewitzModLoader<Module> {

	public ArrayList<Mod> mods = new ArrayList<Mod>();

	/**
	 * A new Clausewitz Mod
	 * 
	 * @param fs The Filesystem to make a mod. the root of the filesystem is used
	 */
	public void search(Module module) {
		Map<String, ModFile> mfs = setupModFile(module);
		if (mfs.isEmpty()) { // Make sure its not read by another loader
			Mod mod = new ModuleMod(module, "");
			mods.add(mod);
		}

	}

	@Override
	public List<Mod> getMods() {
		// TODO Auto-generated method stub
		return mods;
	}

	@Override
	public Map<String, ModFile> setupModFile(Module search) {
		// TODO Auto-generated method stub
		Map<String, ModFile> files = new HashMap<String, ModFile>();

		try {
			search.globResources("").forEachRemaining((res) -> {

				if (res.getName().endsWith(".mod")) {
					InputStream stream;
					try {
						stream = res.getURL().openStream();

						ModFile fil = ModFile.parseModFile(BasicIO.inputstreamToString(stream));

						if (fil.getPath() != null) {
							if (fil.getPath().isEmpty() || fil.getPath().equals("/")) {
								files.put(res.getName(), fil);
							}
						} else if (fil.getArchive() == null) {
							fil.setPath("");
							files.put(res.getName(), fil);
						}

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}

			);
		} catch (ModuleLoadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
