package featurecreep.api.bg.resource_packs;

public class FCResourceType {

	public static FCResourceType CLIENT_RESOURCES = new FCResourceType("assets");
	public static FCResourceType SERVER_DATA = new FCResourceType("data");

	public String dir;

	/**
	 * 
	 * @param dir Directory within Resource Pack
	 */
	public FCResourceType(String dir) {
		this.dir = dir;
	}

	/**
	 * Directory within the resource Pack
	 * 
	 * @return
	 */
	public String getDirectory() {
		return dir;
	}

	/**
	 * Checks the built in ResourceTypes and if it finds none it makes a new one
	 * 
	 * @param dir Directory within Resource Pack
	 * @return
	 */
	public static FCResourceType checkBuiltIn(String dir) {
		if (dir.equals("assets")) {
			return CLIENT_RESOURCES;
		} else if (dir.equals("data")) {
			return SERVER_DATA;
		} else {
			return new FCResourceType(dir);
		}

	}

}
