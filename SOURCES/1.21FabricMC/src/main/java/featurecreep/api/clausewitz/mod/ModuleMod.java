package featurecreep.api.clausewitz.mod;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.jboss.modules.Module;
import org.jboss.modules.ModuleLoadException;

import featurecreep.api.io.BasicIO;
import javassist.NotFoundException;

public class ModuleMod implements Mod {

	public ModFile modfile;
	public Module mod;
	public String name;
	public String relative_path;

	/**
	 * Creates a Clausewitz mod from a Module from JBoss Modules
	 * 
	 * @param mod           a module
	 * @param modfile       the .mod file
	 * @param relative_path a relative path to look in. can be "" if blank
	 */
	public ModuleMod(Module mod, ModFile modfile, String relative_path) {
		this.mod = mod;
		this.modfile = modfile;
		if (modfile != null) {
			this.name = modfile.getName();
		} else {
			this.name = mod.getName().replace("/", "_") + relative_path;
		}
	}

	public ModuleMod(Module fs, String relative_path) {
		this(fs, null, relative_path);
	}

	public Module getFileSystem() {
		return mod;
	}

	public void setModule(Module mod) {
		this.mod = mod;
	}

	@Override
	public ModFile getModFile() {
		// TODO Auto-generated method stub
		return modfile;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}

	@Override
	public byte[] get(String name) throws IOException, NotFoundException {
		// TODO Auto-generated method stub
		InputStream stream = getStream(name);
		if (name != null) {

			try {
				return BasicIO.convertInputStreamToByteArray(stream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return null;

	}

	@Override
	public InputStream getStream(String name) throws IOException, NotFoundException {
		// TODO Auto-generated method stub

		List<InputStream> str = new ArrayList<InputStream>();

		try {
			mod.globResources(name).forEachRemaining((res) -> {
				if (res.getName().equals(name)) {
					try {
						str.add(res.openStream());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						str.add(null);
						e.printStackTrace();
					}
				}

			});
		} catch (ModuleLoadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IOException();
		}

		if (str.isEmpty()) {
			throw new NotFoundException(name);
		} else {
			InputStream stream = str.get(0);// There should only be one
			if (stream != null) {
				return stream;
			} else {
				throw new IOException();
			}
		}
	}

	@Override
	public List<String> getEntries() {
		// TODO Auto-generated method stub
		List<String> str = new ArrayList<String>();

		try {
			mod.globResources(name).forEachRemaining((res) -> {
				if (res.getName().equals(name)) {
					str.add(res.getName());
				}

			});
		} catch (ModuleLoadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}

}
