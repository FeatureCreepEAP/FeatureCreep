package featurecreep.api.clausewitz.mod;

import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import featurecreep.api.annotations.Internal;

public interface ClausewitzModLoader<T> {

	public List<Mod> getMods();

	/**
	 * Gets Name for the 1st time so it does not search again especially after
	 * change in filesystem
	 * 
	 * @return
	 */
	@Internal
	public default String setupName(String defaul, ModFile fil) {
		if (fil != null) {
			String name = fil.getName();
			if (name != null && !name.isEmpty()) {
				return name;
			}
			return defaul;
		} else {
			return defaul;
		}
	}

	/**
	 * gets path and string of each modfile
	 * 
	 * @return
	 */
	@Internal
	public Map<String, ModFile> setupModFile(T search);

	public static String getFileName(String filePath) {
		return Paths.get(filePath).getFileName().toString();
	}

	public static String getFolderName(String filePath) {
		// Get the parent directory and then the last part of that
		return Paths.get(filePath).getParent().getFileName().toString();
	}

	public default String getName(ModFile file, String path) {
		// TODO Auto-generated method stub
		if (file.getName() != null) {
			return file.getName();
		} else {
			return getFileName(path);
		}
	}

}
