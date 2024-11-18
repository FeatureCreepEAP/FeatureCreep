package featurecreep.api.bg.mapping_converter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.GZIPInputStream;

import com.asbestosstar.assistremapper.Mappings;
import com.asbestosstar.assistremapper.mappings.PDMEMappings;

import featurecreep.FeatureCreep;
import featurecreep.loader.filesystem.PhilKatzZip;

public class MappingConverter {

	public MappingConverter() {

		try {
			JarFile fcjar = null;
			Enumeration<URL> resources = MappingConverter.class.getClassLoader().getResources("fci/");
			Map<String, InputStream> pairs = new HashMap<String, InputStream>();

			if (resources.hasMoreElements()) {

				while (resources.hasMoreElements()) {
					URL url = resources.nextElement();
					if (url.getProtocol().equals("jar")) {
						String jarPath = url.getPath().substring(0, url.getPath().indexOf('!')); // 去除 jar:file: 和 !
																									// 后的部分
						
							try {
								PhilKatzZip jar = new PhilKatzZip(new URI(jarPath).toURL());
								for (String fil:jar.getFilenames("fci")) {
									if (fil.endsWith(".pdme.gz")) {
										// 使用 classLoader 来获取输入流，以便正确处理缓存等
										InputStream inputStream = MappingConverter.class.getClassLoader()
												.getResourceAsStream(fil);
										if (inputStream != null) {
											pairs.put(fil, inputStream);
										}
									}
								}
							} catch (URISyntaxException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						

					} else {
						fcjar = new JarFile(FeatureCreep.loader.getFeatureCreepJar());
						System.out.println("FC JAR "+fcjar.getName());
						searchJarForMappings(pairs, fcjar);
					}

				}

			} else {
				fcjar = new JarFile(FeatureCreep.loader.getFeatureCreepJar());
				searchJarForMappings(pairs, fcjar);

			}

			for (Map.Entry<String, InputStream> entry : pairs.entrySet()) {
				System.out.println(entry.getKey());
				GZIPInputStream gzipInputStream = new GZIPInputStream(entry.getValue());
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len;
				while ((len = gzipInputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, len);
				}
				byte[] uncompressed = outputStream.toByteArray();
				Mappings maps = new PDMEMappings(new ByteArrayInputStream(uncompressed));

				if (entry.getKey().contains("yarn")) {
					ActiveMapping.YARN.setMappings(maps);
				} else if (entry.getKey().contains("parchsrg")) {
					ActiveMapping.PARCHSRG.setMappings(maps);
				} else if (entry.getKey().contains("srg")) {
					ActiveMapping.SRG.setMappings(maps);
				} else if (entry.getKey().contains("hashed-mojmap")) {
					ActiveMapping.HASHED_MOJMAP.setMappings(maps);
				} else if (entry.getKey().contains("fabric-intermediary")) {
					ActiveMapping.FABRICMC_INTERMEDIARY.setMappings(maps);
				} else if (entry.getKey().contains("sugarcane")) {
					ActiveMapping.PARCHMENT.setMappings(maps);
				} else if (entry.getKey().contains("obf")) {
					ActiveMapping.OBF.setMappings(maps);
				} // need to do others
			}

			if (fcjar != null) {
				fcjar.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void searchJarForMappings(Map<String, InputStream> map_to_append, JarFile fcjar) {
		for (JarEntry entry : Collections.list(fcjar.entries())) {
			if (entry.getName().startsWith("fci") && !entry.isDirectory() && entry.getName().endsWith(".pdme.gz")) {
				try {
					InputStream mapstream = fcjar.getInputStream(entry);
					if(!map_to_append.containsKey(entry.getName())) {
					map_to_append.put(entry.getName(), mapstream);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	// full_class_name should be the full class name in FeatureCreep intermediary.
	// Should be Spanish in the future, for now just use English method
	// mappings seperated with . for package and $ for subclass
	public static String getUnMappedClass(String full_class_name) {
		if (!FeatureCreep.mappings.hasMappings()) {
			System.out.println("No Mappings");
			return full_class_name;
		}

		return FeatureCreep.mappings.getMappings().getClassUnMappedName(full_class_name);
	}

	// full_class_name should be the full class name in FeatureCreep intermediary
	// English
	// mappings seperated with . for package and $ for subclass
	public static String getUnMappedClassEnglish(String full_class_name) {
		return getUnMappedClass(full_class_name);// This will be changed when we have Spanish FCI
	}

	// full_class_name should be the full class name in FeatureCreep intermediary
	// Should be Spanish in the future, for now just use English method
	// mappings seperated with . for package and $ for subclass
	public static String getUnMappedClass(String full_class_name, ActiveMapping mappings) {
		if (!mappings.hasMappings()) {
			System.out.println("No Mappings");
			return full_class_name;
		}

		return mappings.getMappings().getClassUnMappedName(full_class_name);
	}

	// full_class_name should be the full class name in FeatureCreep intermediary
	// Should be English
	// mappings seperated with . for package and $ for subclass
	public static String getUnMappedClassEnglish(String full_class_name, ActiveMapping mappings) {
		return getUnMappedClassEnglish(full_class_name, mappings); // Will be changed when FCIs are in Spanish
	}

	// full_class_name should be the full class name in preferred format and it
	// Should be Spanish in the future, for now just use English method
	// returns featurecreep intermediary mappings seperated with . for package and $
	// for subclass
	public static String getMappedClass(String full_class_name, ActiveMapping mappings) {
		if (!mappings.hasMappings()) {
			System.out.println("No Mappings");
			return full_class_name;
		}

		return mappings.getMappings().getClassMappedName(full_class_name);
	}

	// full_class_name should be the full class name in preferred format and it
	// Should be English
	// returns featurecreep intermediary mappings seperated with . for package and $
	// for subclass
	public static String getMappedClassEnglish(String full_class_name, ActiveMapping mappings) {
		return getMappedClass(full_class_name, mappings);// escribir otra vez cuando FCIs son en español
	}

	// full_class_name should be the full class name in default format and it Should
	// be Spanish in the future, for now just use English method
	// returns featurecreep intermediary mappings seperated with . for package and $
	// for subclass
	public static String getMappedClass(String full_class_name) {
		if (!FeatureCreep.mappings.hasMappings()) {
			System.out.println("No Mappings");
			return full_class_name;
		}

		return FeatureCreep.mappings.getMappings().getClassMappedName(full_class_name);
	}

	// full_class_name should be the full class name in default format and it Should
	// be English
	// returns featurecreep intermediary mappings seperated with . for package and $
	// for subclass
	public static String getMappedClassEnglish(String full_class_name) {
		return getMappedClass(full_class_name);// escribir otra vez cuando FCIs son en español
	}

}


