package featurecreep.api.bg.mc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jboss.modules.Module;

import asbestosstar.bootstrap.BootstrapCommon;
import asbestosstar.bootstrap.minecraft.MinecraftCommonStartup;
import featurecreep.api.bg.PackLoader;
import featurecreep.api.bg.mc.registry.DeferredMainRegistrar;
import featurecreep.api.clausewitz.mod.FileSystemClausewitzModLoader;
import featurecreep.api.clausewitz.mod.Mod;
import featurecreep.api.clausewitz.mod.ModuleClausewitzModLoader;
import featurecreep.api.clausewitz.mod.WithoutModFileFileSystemClausewitzModLoader;
import featurecreep.api.clausewitz.mod.WithoutModFileModuleClausewitzModLoader;
import featurecreep.loader.filesystem.DirectoryReader;

public class FeatureCreepMC {

    public static ModuleClausewitzModLoader clausewitz_module_modloader = new ModuleClausewitzModLoader();
    public static FileSystemClausewitzModLoader clausewitz_filesystem_modloader = new FileSystemClausewitzModLoader();
    public static WithoutModFileModuleClausewitzModLoader clausewitz_module_modloader_no_modfile = new WithoutModFileModuleClausewitzModLoader();
    public static WithoutModFileFileSystemClausewitzModLoader clausewitz_filesystem_modloader_no_modfile = new WithoutModFileFileSystemClausewitzModLoader();

    private static boolean initialized = false;

    public static void init() {
        if (initialized) return;
        initialized = true;

        System.out.println("FC Init");
        MinecraftCommonStartup.start();

        try {
            clausewitz_filesystem_modloader.search(new DirectoryReader(new File(System.getProperty("user.dir"))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Module mod : BootstrapCommon.loader.getModules()) {
            clausewitz_module_modloader.search(mod);
            clausewitz_module_modloader_no_modfile.search(mod);
        }

        BootstrapCommon.loader.runMods();
        BootstrapCommon.flatloader.runMods();

        DeferredMainRegistrar.commit();

        PackLoader.loadPacks();
    }

    public static List<Mod> getClausewitzMods() {
        ArrayList<Mod> list = new ArrayList<>();
        list.addAll(clausewitz_module_modloader.getMods());
        list.addAll(clausewitz_filesystem_modloader.getMods());
        list.addAll(clausewitz_module_modloader_no_modfile.getMods());
        list.addAll(clausewitz_filesystem_modloader_no_modfile.getMods());
        return list;
    }
}