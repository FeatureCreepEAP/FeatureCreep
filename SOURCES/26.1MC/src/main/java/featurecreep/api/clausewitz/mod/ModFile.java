package featurecreep.api.clausewitz.mod;

import java.util.ArrayList;
import java.util.List;

public class ModFile {

	public String name;
	public String path;
	public List<String> tags;
	public List<String> dependencies;
	public String supported_version;
	public String picture;
	public String remote_file_id;
	public String archive;

	public ModFile(String name, String archive, String path, List<String> tags, List<String> dependencies,
			String supported_version, String picture, String remote_file_id) {
		super();
		this.name = name;
		this.path = path;
		this.tags = tags;
		this.dependencies = dependencies;
		this.supported_version = supported_version;
		this.picture = picture;
		this.remote_file_id = remote_file_id;
		this.archive = archive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public List<String> getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<String> dependencies) {
		this.dependencies = dependencies;
	}

	public String getSupportedVersion() {
		return supported_version;
	}

	public void setSupportedVersion(String supported_version) {
		this.supported_version = supported_version;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String thumbnail) {
		this.picture = thumbnail;
	}

	public String getRemoteFileID() {
		return remote_file_id;
	}

	public int getRemoteFileIDInt() {
		return Integer.valueOf(getRemoteFileID());
	}

	public void setRemoteFileID(String remote_file_id) {
		this.remote_file_id = remote_file_id;
	}

	public String getArchive() {
		return archive;
	}

	public void setArchive(String archive) {
		this.archive = archive;
	}

	public static ModFile parseModFile(String fileContent) {
		String name = null;
		String path = null;
		String archive = null;
		List<String> tags = new ArrayList<>();
		List<String> dependencies = new ArrayList<>();
		String supported_version = null;
		String picture = null;
		String remote_file_id = null;
		boolean inTagsBlock = false;
		boolean inDepsBlock = false;

		// Simple parsing by splitting the content into lines and processing each line
		for (String line : fileContent.split("\\r?\\n")) {
			line = line.trim(); // Trim whitespace
			if (line.isEmpty() || line.startsWith("#")) {
				// Skip empty lines and comment lines
				continue;
			}

			String[] parts = line.split("=", 2); // Split into key and value, limiting to 2 parts
			if (parts.length != 2) {
				throw new IllegalArgumentException("Invalid line format: " + line);
			}

			if (line.startsWith("name=")) {
				name = line.substring("name=".length()).trim();
			} else if (line.startsWith("path=")) {
				path = line.substring("path=".length()).trim();
			} else if (line.startsWith("archive=")) {
				archive = line.substring("archive=".length()).trim();
			} else if (line.startsWith("picture=")) {
				picture = line.substring("picture=".length()).trim();
			} else if (line.startsWith("remote_file_id=")) {
				remote_file_id = line.substring("remote_file_id=".length()).trim();
			} else if (line.startsWith("tags=")) {
				inTagsBlock = true;
				continue; // Skip the opening brace line
			} else if (inTagsBlock && !line.isEmpty() && !line.startsWith("}")) {
				String tag = line.trim();
				if (tag.startsWith("\"") && tag.endsWith("\"")) {
					tag = tag.substring(1, tag.length() - 1); // Remove the first and last character (assuming they are
																// quotes)
				}
				tags.add(tag); // Add tag if not empty and not the closing brace

			} else if (line.startsWith("}")) {
				inTagsBlock = false;
			} else if (line.startsWith("dependencies=")) {
				inDepsBlock = true;
				continue; // Skip the opening brace line
			} else if (inDepsBlock && !line.isEmpty() && !line.startsWith("}")) {
				String dep = line.trim();
				if (dep.startsWith("\"") && dep.endsWith("\"")) {
					dep = dep.substring(1, dep.length() - 1); // Remove the first and last character (assuming they are
																// quotes)
				}
				dependencies.add(dep); // Add tag if not empty and not the closing brace

			} else if (line.startsWith("}")) {
				inDepsBlock = false;
			} else if (line.startsWith("supported_version=")) {
				supported_version = line.substring("supported_version=".length()).trim();
			}

		}

		// Validate required fields
		if (name == null || supported_version == null) {
			throw new IllegalArgumentException("Missing required fields: name, path/archive, supported_version");
		}
		// Validate required fields
		if (archive == null && path == null) {
			throw new IllegalArgumentException("Either archive or path must have a value");
		}

		// Create and return the ModFile instance
		return new ModFile(name, archive, path, tags, dependencies, supported_version, picture, remote_file_id);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		// Add the name
		sb.append("name=\"").append(name).append("\"\n");

		// Add the path
		sb.append("path=\"").append(path).append("\"\n");

		// Optionally add dependencies (if not null or empty)
		if (dependencies != null && !dependencies.isEmpty()) {
			sb.append("dependencies={\"").append(String.join("\"" + "\n", dependencies)).append("}\n");
		}

		// Optionally add tags (if not null or empty)
		if (tags != null && !tags.isEmpty()) {
			sb.append("tags={\"").append(String.join("\"" + "\n", tags)).append("}\n");
		}

		// Add the supported version
		sb.append("supported_version=\"").append(supported_version).append("\"\n");

		if (picture != null && !picture.isEmpty()) {
			sb.append("picture=\"").append(picture).append("\"\n");
		}

		if (remote_file_id != null && !remote_file_id.isEmpty()) {
			sb.append("remote_file_id=\"").append(remote_file_id).append("\"\n");
		}

		return sb.toString();
	}

}
