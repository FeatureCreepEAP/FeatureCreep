package featurecreep.api.bg.mc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jboss.modules.Module;

import asbestosstar.bootstrap.BootstrapCommon;
import asbestosstar.bootstrap.minecraft.MinecraftCommonStartup;
import featurecreep.api.bg.PackLoader;
import featurecreep.api.clausewitz.mod.FileSystemClausewitzModLoader;
import featurecreep.api.clausewitz.mod.Mod;
import featurecreep.api.clausewitz.mod.ModuleClausewitzModLoader;
import featurecreep.api.clausewitz.mod.WithoutModFileFileSystemClausewitzModLoader;
import featurecreep.api.clausewitz.mod.WithoutModFileModuleClausewitzModLoader;
import featurecreep.content.FCItems;
import featurecreep.loader.filesystem.DirectoryReader;

public class FeatureCreepMC {

    // Module Loaders
    public static ModuleClausewitzModLoader clausewitz_module_modloader = new ModuleClausewitzModLoader();
    public static FileSystemClausewitzModLoader clausewitz_filesystem_modloader = new FileSystemClausewitzModLoader();
    public static WithoutModFileModuleClausewitzModLoader clausewitz_module_modloader_no_modfile = new WithoutModFileModuleClausewitzModLoader();
    public static WithoutModFileFileSystemClausewitzModLoader clausewitz_filesystem_modloader_no_modfile = new WithoutModFileFileSystemClausewitzModLoader();

    public static void init() {
        System.out.println("FC Init");
        MinecraftCommonStartup.start();
        FCItems.onInitialise();
        
        // Search filesystem for Clausewitz mods
        try {
            clausewitz_filesystem_modloader.search(new DirectoryReader(new File(System.getProperty("user.dir"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Search JBoss Modules for Clausewitz mods
        for (Module mod : BootstrapCommon.loader.getModules()) {
            clausewitz_module_modloader.search(mod);
            clausewitz_module_modloader_no_modfile.search(mod);
        }

        // Run Module Mods
        BootstrapCommon.loader.runMods();
        
        // Run Flat Mods
        // Note: Flat mods are run here to ensure they execute after standard module setup
        // but PackLoader logic runs afterwards to bundle everything.
        BootstrapCommon.flatloader.runMods();
        
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
