package featurecreep.unsupported;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.List;

import net.minecraftforge.fml.loading.moddiscovery.AbstractModProvider;
import net.minecraftforge.forgespi.locating.IModLocator;

public class CPWLocator extends AbstractModProvider implements IModLocator {

	@Override
	public List<ModFileOrException> scanMods() {

		// Get the current ProtectionDomain
		ProtectionDomain protectionDomain = CPWLocator.class.getProtectionDomain();

		// Get the CodeSource for the ProtectionDomain
		CodeSource codeSource = protectionDomain.getCodeSource();

		// Get the URL of the JAR file
		URL jarUrl = codeSource.getLocation();

		try {
			// Construct the path to the file inside the JAR
			Path jarFilePath = Paths.get(jarUrl.toURI());
			Path filePathInsideJar = jarFilePath.resolve("META-INF/jarjar/mod.jar");

			// Print the path
			System.out.println("Path to file inside JAR: " + filePathInsideJar);

			// You can use the filePathInsideJar for further operations
			// such as reading the file content or creating a FileSystem for the JAR

			ModFileOrException mod = createMod(filePathInsideJar);

			return List.of(mod); // 1st time trying that lol though ill prolly replace it when i get FC mods
									// recognised as MC Forge ones

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "featurecreep";
	}

}
