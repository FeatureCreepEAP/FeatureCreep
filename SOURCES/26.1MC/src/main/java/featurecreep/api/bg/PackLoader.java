package featurecreep.api.bg;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.jboss.modules.Module;

import asbestosstar.bootstrap.BootstrapCommon;
import featurecreep.api.bg.mc.FeatureCreepMC;
import featurecreep.api.bg.resource_packs.ClausewitzModResourcePack;
import featurecreep.api.bg.resource_packs.FCPackMCMeta;
import featurecreep.api.bg.resource_packs.FlatVainillaResourcePack;
import featurecreep.api.bg.resource_packs.ModuleVainillaResourcePack;
import featurecreep.api.bg.resource_packs.VainillaResourcePack;
import featurecreep.api.clausewitz.mod.Mod;
import featurecreep.api.io.BasicIO;
import featurecreep.loader.flat.FlatModMetadata;

/**
 * The FC4 compat parts will be removed in version 13 but loadPacks will stay
 */
@Deprecated(forRemoval = true, since = "13")
public class PackLoader implements VainillaResourcePack {

    public static Map<String, byte[]> entries = new HashMap<String, byte[]>();
    public static Map<String, VainillaResourcePack> packs = new HashMap<String, VainillaResourcePack>();
    public static int pack_version = 10;
    public static String pack_name = "fcpack_" + pack_version;
    public static String fc_pack_location = pack_name; 
    public static VainillaResourcePack INSTANCE = new PackLoader();

    public static void loadPacks() {
        packs.put(pack_name, INSTANCE);
        
        // Load Clausewitz Mods
        for (Mod mod : FeatureCreepMC.getClausewitzMods()) {
            ClausewitzModResourcePack pack = new ClausewitzModResourcePack(mod);
            packs.put(pack.getPackName(), pack);
        }
        
        // Load JBoss Module Mods
        for (Module mod : BootstrapCommon.loader.getModules()) {
            packs.put(mod.getName(), new ModuleVainillaResourcePack(mod));
        }
        
        // Load Flat Mods
        ClassLoader flatClassLoader = BootstrapCommon.flatloader.getFlatClassLoader();
        for (Map.Entry<String, FlatModMetadata> entry : BootstrapCommon.flatloader.getModMetadataMap().entrySet()) {
            FlatModMetadata meta = entry.getValue();
            FlatVainillaResourcePack pack = new FlatVainillaResourcePack(meta, flatClassLoader);
            packs.put(meta.getModId(), pack);
        }
    }

    public static boolean packLoaderFCHasPack(String name) {
        return packs.containsKey(name);
    }

    @Override
    public Supplier<InputStream> getStream(String location) {
        byte[] get = entries.get(location);
        if (get != null) {
            return BasicIO.inputStreamSupplierFromBytes(get);
        }
        return null;
    }

    @Override
    public Supplier<InputStream> getPackPng() {
        return null;
    }

    @Override
    public Collection<String> getEntries(String prefix) {
        ArrayList<String> strs = new ArrayList<String>();
        for (String str : entries.keySet()) {
            if (str.startsWith(prefix)) {
                strs.add(str);
            }
        }
        return strs;
    }

    @Override
    public FCPackMCMeta getPackMCMetaInfo() {
        return new FCPackMCMeta(pack_version, "Paquete de FeatureCreep genderado automaticomente");
    }

    @Override
    public String getPackName() {
        return pack_name;
    }

    @Override
    public void closeStreams() {
        // No-op
    }
}
