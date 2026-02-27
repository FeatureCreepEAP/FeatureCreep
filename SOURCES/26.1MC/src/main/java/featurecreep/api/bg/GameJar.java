package featurecreep.api.bg;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.jar.JarFile;

import com.asbestosstar.assistremapper.remapper.JarRemapper;
import com.asbestosstar.shadowassist.ShadowAssist;

import featurecreep.FeatureCreep;
import featurecreep.api.PKZipUtils;
import featurecreep.loader.utils.ClassPathUtils;
import featurecreep.loader.utils.FileUtils;
import javassist.ClassPool;
import javassist.NotFoundException;

public class GameJar {

	public static ClassPool pool = new ClassPool(true);

	static {
		pool.childFirstLookup = true;
	}

	// Needs to be fixed
	public static JarFile getGameJar() {
		List<String> cp = ClassPathUtils.getClassPath();
		List<String> compat = ClassPathUtils.findJarFilesWithPackage(cp, "net.minecraft.client");

		try {
			return new JarFile(compat.get(0));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public static JarFile getShadow() {
		String target = FeatureCreep.gamepath + "/usr/share/.shadows/mc_forge_" + FeatureCreep.game_version + ".jar";
		if (new File(target).exists()) {
			try {
				return new JarFile(target);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} else {
			ShadowAssist assist = new ShadowAssist(pool, getGameJar(), new File(target));
			try {
				return new JarFile(target);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}

	}



}

