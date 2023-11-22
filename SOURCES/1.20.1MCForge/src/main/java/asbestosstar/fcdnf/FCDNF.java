package asbestosstar.fcdnf;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.asbestosstar.dnfjava.DnfJava;

import featurecreep.FeatureCreep;
import featurecreep.loader.FCLoaderBasic;

public class FCDNF extends DnfJava{

	public FCDNF() {
		super();
		this.setBaseLocation(FeatureCreep.gamepath.toString(), true);
for(File mod: FeatureCreep.loader.getCombinedFiles()) {
	if(FCLoaderBasic.isFilePKZipCompatible(mod)) {
		try {
			JarFile jar = new JarFile(mod);
			for (JarEntry entry : Collections.list(jar.entries())) {
				if (entry.getName().endsWith(".repo") && entry.getName().startsWith("etc/yum.repos.d/")) {
					extractRepoFile(jar.getInputStream(entry), entry.getName());
			    }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	this.addRepos();
}

		
		
}
	
	
public void extractRepoFile(InputStream stream,String name)	{
	Path rep = new File(FeatureCreep.gamepath.toString()+"/"+name).toPath();
	rep.toFile().mkdirs();
        try {
			Files.copy(stream, rep, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
}
	
	
	
}
