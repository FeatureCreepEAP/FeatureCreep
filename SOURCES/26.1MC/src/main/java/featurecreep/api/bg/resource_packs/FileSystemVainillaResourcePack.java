package featurecreep.api.bg.resource_packs;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.function.Supplier;

import featurecreep.api.dmr.ModelNode;
import featurecreep.loader.filesystem.FileSystem;

public class FileSystemVainillaResourcePack implements VainillaResourcePack {

	String name;
	public FileSystem fs;
	String overlay;// Does not do anything

	public FileSystemVainillaResourcePack(String name, FileSystem fs, String overlay) {
		this.name = name;
		this.overlay = overlay;
		this.fs = fs;
	}

	public FileSystemVainillaResourcePack(String name, FileSystem fs) {
		this(name, fs, "");
	}

	@Override
	public Supplier<InputStream> getStream(String location) {
		InputStream stream;
		try {
			stream = fs.getStream(location);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return null;
		}

		return () -> stream;
	}

	@Override
	public Collection<String> getEntries(String prefix) {
		return fs.getFilenames(prefix);
	}

	@Override
	public FCPackMCMeta getPackMCMetaInfo() {
		try {
			return FCPackMCMeta.fromModelNode(ModelNode.fromJSONStream(this.getStream("pack.mcmeta").get()));
		} catch (IOException e) {
			// e.printStackTrace();
			return new FCPackMCMeta(41, getPackName());
		}
	}

	@Override
	public String getPackName() {
		return this.name;
	}

	@Override
	public void closeStreams() {
		// No need to close anything here as everything is already in memory
	}

	@Override
	public String getOverlay() {
		return this.overlay;
	}

	@Override
	public VainillaResourcePack getVainillaResourcePack(String overlay) {
		if (overlay.equals(this.getOverlay())) {
			return this;
		} else {
			return new FileSystemVainillaResourcePack(this.getPackName(), fs, overlay);
		}
	}

	@Override
	public Supplier<InputStream> getPackPng() {
		// TODO Auto-generated method stub
		return getStream("pack.png");
	}

}
