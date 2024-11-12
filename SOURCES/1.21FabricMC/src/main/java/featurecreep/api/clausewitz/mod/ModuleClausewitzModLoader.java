package featurecreep.api.clausewitz.mod;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.modules.Module;
import org.jboss.modules.ModuleLoadException;
import org.jboss.modules.ModuleLoader;

import featurecreep.api.io.BasicIO;

public class ModuleClausewitzModLoader implements ClausewitzModLoader<Module> {

	public ArrayList<Mod> mods = new ArrayList<Mod>();
	public ModuleLoader loader;

	/**
	 * A new Clausewitz Mod
	 * 
	 * @param mod The module to search for .mod files and load all the mods. ATM
	 *            only does directories because the modloader should get pkzips
	 */
	public void search(Module module) {
		Map<String, ModFile> mfs = setupModFile(module);
		for (Map.Entry<String, ModFile> entry : mfs.entrySet()) {
			String path = entry.getKey();
			ModFile file = entry.getValue();
			if (file.getPath() != null) {
				String relative = getRelativeDir(module, path, file);
				String name = getName(file, path);
				Mod mod = new ModuleMod(module,file,relative);
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
						files.put(res.getName(), fil);

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

	public String getRelativeDir(Module mod, String path, ModFile file) {
		String folder = ClausewitzModLoader.getFolderName(path);
		String modpath = file.getPath();
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
		if (has(mod, dir)) { // Prioritise relative
			return dir;
		} else if (has(mod, folder + modpath)) {
			return folder + modpath;
		} else if (has(mod, without_mod)) {// Search the root folder now
			return without_mod;
		} else if (has(mod, modpath)) {
			return modpath;
		}

		return null;

	}

	public boolean has(Module mod, String path) {
	ArrayList<String> names = new ArrayList<String>();
			try {
				mod.globResources("").forEachRemaining((res) -> {
					if(res.getName().equals(path)) {
						names.add(res.getName());
					}
					
				}

						
						);
			} catch (ModuleLoadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return names.contains(path);
	}

}
