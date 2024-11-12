package featurecreep.api.clausewitz.mod;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import featurecreep.loader.filesystem.FileSystem;
import javassist.NotFoundException;

public class FileSystemMod implements Mod {

	public ModFile modfile;
	public FileSystem filesystem;
	public String name;

	/**
	 * Creates a new File Syste Mod
	 * 
	 * @param fs      File System for the mod
	 * @param modfile The modfile. If the name is null then you should set the name
	 *                after
	 */
	public FileSystemMod(FileSystem fs, ModFile modfile) {
		this.filesystem = fs;
		this.modfile = modfile;
		if (modfile != null) {
			this.name = modfile.getName();
		}
	}

	public FileSystemMod(FileSystem fs) {
		this(fs, null);
	}

	public FileSystem getFileSystem() {
		return filesystem;
	}

	public void setFileSystem(FileSystem fs) {
		this.filesystem = fs;
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
	public byte[] get(String name) throws NotFoundException, IOException {
		// TODO Auto-generated method stub
		if (filesystem.has(name)) {
			return filesystem.get(name);
		} else {
			throw new NotFoundException(name);
		}

	}

	@Override
	public InputStream getStream(String name) throws NotFoundException, IOException {
		// TODO Auto-generated method stub
		if (filesystem.has(name)) {
			return filesystem.getStream(name);
		} else {
			throw new NotFoundException(name);
		}

	}

	@Override
	public List<String> getEntries() {
		// TODO Auto-generated method stub
		ArrayList<String> strs = new ArrayList<String>();
		strs.addAll(filesystem.getFilenames(""));
		return strs;
	}

}
