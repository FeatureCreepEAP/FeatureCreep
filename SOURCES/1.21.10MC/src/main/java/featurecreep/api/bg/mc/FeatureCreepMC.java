package featurecreep.api.bg.mc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jboss.modules.Module;

import asbestosstar.bootstrap.BootstrapCommon;
import featurecreep.api.FCLoaderObtainer;
import featurecreep.api.bg.PackLoader;
import featurecreep.api.clausewitz.mod.FileSystemClausewitzModLoader;
import featurecreep.api.clausewitz.mod.Mod;
import featurecreep.api.clausewitz.mod.ModuleClausewitzModLoader;
import featurecreep.api.clausewitz.mod.WithoutModFileFileSystemClausewitzModLoader;
import featurecreep.api.clausewitz.mod.WithoutModFileModuleClausewitzModLoader;
import featurecreep.content.FCItems;
import featurecreep.loader.filesystem.DirectoryReader;

public class FeatureCreepMC {

	public static ModuleClausewitzModLoader clausewitz_module_modloader = new ModuleClausewitzModLoader();
	public static FileSystemClausewitzModLoader clausewitz_filesystem_modloader = new FileSystemClausewitzModLoader();
	public static WithoutModFileModuleClausewitzModLoader clausewitz_module_modloader_no_modfile = new WithoutModFileModuleClausewitzModLoader();
	public static WithoutModFileFileSystemClausewitzModLoader clausewitz_filesystem_modloader_no_modfile = new WithoutModFileFileSystemClausewitzModLoader();

	public static void init() {
		System.out.println("FC Init");
		FCItems.onInitialise();
		try {
			clausewitz_filesystem_modloader.search(new DirectoryReader(new File(System.getProperty("user.dir"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Module mod : FCLoaderObtainer.getFCLoaderBasic(FeatureCreepMC.class).getModules()) {
			clausewitz_module_modloader.search(mod);
			clausewitz_module_modloader_no_modfile.search(mod);// Maybe try to make one 1 work in the future
		}

		BootstrapCommon.loader.runMods();
		PackLoader.loadPacks();

	}

	public static List<Mod> getClausewitzMods() {
		ArrayList<Mod> list = new ArrayList<Mod>();
		list.addAll(clausewitz_module_modloader.getMods());
		list.addAll(clausewitz_filesystem_modloader.getMods());
		list.addAll(clausewitz_module_modloader_no_modfile.getMods());
		list.addAll(clausewitz_filesystem_modloader_no_modfile.getMods());
		return list;
	}

}
