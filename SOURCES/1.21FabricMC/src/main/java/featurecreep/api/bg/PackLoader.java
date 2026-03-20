package featurecreep.api.bg;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.jboss.modules.Module;

import featurecreep.api.FCLoaderObtainer;
import featurecreep.api.bg.datapacks.DataPackLoader;
import featurecreep.api.bg.mc.FeatureCreepMC;
import featurecreep.api.bg.resource_packs.ClausewitzModResourcePack;
import featurecreep.api.bg.resource_packs.FCPackMCMeta;
import featurecreep.api.bg.resource_packs.ModuleVainillaResourcePack;
import featurecreep.api.bg.resource_packs.VainillaResourcePack;
import featurecreep.api.clausewitz.mod.Mod;
import featurecreep.api.io.BasicIO;

/**
 * The FC4 compat parts will be removed in version 13 but loadPacks will stay
 */
@Deprecated(forRemoval = true, since = "13")
public class PackLoader implements VainillaResourcePack {

	public static Map<String, byte[]> entries = new HashMap<String, byte[]>();// For static entries
	public static Map<String, VainillaResourcePack> packs = new HashMap<String, VainillaResourcePack>();
	public static int pack_version = 10;
	public static String pack_name = "fcpack_" + pack_version;
	public static String fc_pack_location = pack_name; // This used to point to a physical directory but not anymore
	public static VainillaResourcePack INSTANCE = new PackLoader();// TEMPORARILY

	public static void loadPacks() {
		DataPackLoader.onInitialise();
		packs.put(pack_name, INSTANCE);
		for (Mod mod : FeatureCreepMC.getClausewitzMods()) {
			ClausewitzModResourcePack pack = new ClausewitzModResourcePack(mod);
			packs.put(pack.getPackName(), pack);
		}
		for (Module mod : FCLoaderObtainer.getFCLoaderBasic(PackLoader.class).getModules()) {
			packs.put(mod.getName(), new ModuleVainillaResourcePack(mod));
		}
	}

	public static boolean packLoaderFCHasPack(String name) {
		return packs.containsKey(name);
	}

	@Override
	public Supplier<InputStream> getStream(String location) {
		// TODO Auto-generated method stub
		byte[] get = entries.get(location);
		if (get != null) {
			return BasicIO.inputStreamSupplierFromBytes(get);
		}

		return null;// I may make this globbed in the future

	}

	@Override
	public Supplier<InputStream> getPackPng() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> getEntries(String prefix) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return new FCPackMCMeta(pack_version, "Paquete de FeatureCreep genderado automaticomente");
	}

	@Override
	public String getPackName() {
		// TODO Auto-generated method stub
		return pack_name;
	}

	@Override
	public void closeStreams() {
		// TODO Auto-generated method stub

	}

}
