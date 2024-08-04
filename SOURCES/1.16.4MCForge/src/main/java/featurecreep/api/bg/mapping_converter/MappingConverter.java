package featurecreep.api.bg.mapping_converter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.GZIPInputStream;

import com.asbestosstar.assistremapper.Mappings;
import com.asbestosstar.assistremapper.mappings.PDMEMappings;

import featurecreep.FeatureCreep;

public class MappingConverter {

	public MappingConverter() {
		try {
			JarFile fcjar = new JarFile(FeatureCreep.loader.getFeatureCreepJar());
			for (JarEntry entry : Collections.list(fcjar.entries())) {
				if (entry.getName().startsWith("fci") && entry.getName().endsWith(".gz")) {
					InputStream mapstream = fcjar.getInputStream(entry);
					GZIPInputStream gzipInputStream = new GZIPInputStream(mapstream);
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
					byte[] buffer = new byte[1024];
					int len;
					while ((len = gzipInputStream.read(buffer)) != -1) {
						outputStream.write(buffer, 0, len);
					}
					byte[] uncompressed = outputStream.toByteArray();

					Mappings maps = new PDMEMappings(new ByteArrayInputStream(uncompressed));

					if (entry.getName().contains("yarn")) {
						ActiveMapping.YARN.setMappings(maps);
					} else if (entry.getName().contains("parchsrg")) {
						ActiveMapping.PARCHSRG.setMappings(maps);
					} else if (entry.getName().contains("srg")) {
						ActiveMapping.SRG.setMappings(maps);
					} else if (entry.getName().contains("hashed-mojmap")) {
						ActiveMapping.HASHED_MOJMAP.setMappings(maps);
					} else if (entry.getName().contains("fabric-intermediary")) {
						ActiveMapping.FABRICMC_INTERMEDIARY.setMappings(maps);
					} else if (entry.getName().contains("sugarcane")) {
						ActiveMapping.PARCHMENT.setMappings(maps);
					} else if (entry.getName().contains("obf")) {
						ActiveMapping.OBF.setMappings(maps);
					} // need to do others

				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// full_class_name should be the full class name in FeatureCreep intermediary. Should be Spanish in the future, for now just use English method
	// mappings seperated with . for package and $ for subclass
	public static String getUnMappedClass(String full_class_name) {
		if (!FeatureCreep.mappings.hasMappings()) {
			System.out.println("No Mappings");
			return full_class_name;
		}

		return FeatureCreep.mappings.getMappings().getClassUnMappedName(full_class_name);
	}
	
	// full_class_name should be the full class name in FeatureCreep intermediary English
	// mappings seperated with . for package and $ for subclass
	public static String getUnMappedClassEnglish(String full_class_name) {
		return getUnMappedClass(full_class_name);//This will be changed when we have Spanish FCI
	}
	

	// full_class_name should be the full class name in FeatureCreep intermediary Should be Spanish in the future, for now just use English method
	// mappings seperated with . for package and $ for subclass
	public static String getUnMappedClass(String full_class_name, ActiveMapping mappings) {
		if (!mappings.hasMappings()) {
			System.out.println("No Mappings");
			return full_class_name;
		}

		return mappings.getMappings().getClassUnMappedName(full_class_name);
	}

	
	// full_class_name should be the full class name in FeatureCreep intermediary Should be English
	// mappings seperated with . for package and $ for subclass
	public static String getUnMappedClassEnglish(String full_class_name, ActiveMapping mappings) {
			return getUnMappedClassEnglish(full_class_name,mappings); //Will be changed when FCIs are in Spanish
	}
	
	
	// full_class_name should be the full class name in preferred format and it Should be Spanish in the future, for now just use English method
	// returns featurecreep intermediary mappings seperated with . for package and $
	// for subclass
	public static String getMappedClass(String full_class_name, ActiveMapping mappings) {
		if (!mappings.hasMappings()) {
			System.out.println("No Mappings");
			return full_class_name;
		}

		return mappings.getMappings().getClassMappedName(full_class_name);
	}
	
	
	// full_class_name should be the full class name in preferred format and it Should be English
	// returns featurecreep intermediary mappings seperated with . for package and $
	// for subclass
	public static String getMappedClassEnglish(String full_class_name, ActiveMapping mappings) {
return getMappedClass(full_class_name,mappings);//escribir otra vez cuando FCIs son en español
	}
	

	// full_class_name should be the full class name in default format and it Should be Spanish in the future, for now just use English method
	// returns featurecreep intermediary mappings seperated with . for package and $
	// for subclass
	public static String getMappedClass(String full_class_name) {
		if (!FeatureCreep.mappings.hasMappings()) {
			System.out.println("No Mappings");
			return full_class_name;
		}

		return FeatureCreep.mappings.getMappings().getClassMappedName(full_class_name);
	}

	
	
	// full_class_name should be the full class name in default format and it Should be English
		// returns featurecreep intermediary mappings seperated with . for package and $
		// for subclass
		public static String getMappedClassEnglish(String full_class_name) {
			return getMappedClass(full_class_name);//escribir otra vez cuando FCIs son en español
		}
	
	
}


