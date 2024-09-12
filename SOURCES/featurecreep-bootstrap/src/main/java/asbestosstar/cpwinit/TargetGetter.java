package asbestosstar.cpwinit;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import net.minecraftforge.fml.loading.FMLLoader;

public class TargetGetter {

	static String path = obtainerUbicationDeJuego();
	
	public static List<String> obtainirTodos(){
	
		
		
		List<String> all_classes = new ArrayList<String>();
		for (String arc : getAllFilesFromDirectory(new  File (path +"/"+ "mods" ))) {
			List<String> archivos = getFilesFromJarWithoutSwitchingFromSlashToDot(new File(arc));
			if (archivos.contains("META-INF/mods.toml") || archivos.contains("META-INF/neoforge.mods.toml")
					|| archivos.contains("riftmod.json") || archivos.contains("litemod.json")
					|| archivos.contains("quilt.mod.json") || archivos.contains("fabric.mod.json")) { // Nesesito volver
																										// a ecribir
				for (String clazzz : archivos) {
					if (clazzz.endsWith(".class")) {
						all_classes.add(clazzz.replace("/", ".").substring(0, clazzz.length() - 6));
					}
				}
			}

		}


		List<String> unfiltered_classes = new ArrayList<String>();

		for (String entry : obtainerClasesDeJuego()) { // Cunado tenemos FCI vainilla
																						// nesesitemos cambiar este
			unfiltered_classes.add(entry);
		}

		List<String> completa = new ArrayList<String>();
		completa.addAll(unfiltered_classes);

		for (String clase : all_classes) {

			// TODO Auto-generated catch block
			if (!completa.contains(clase)) {
				completa.add(clase);

			}

		}
		return completa;
		
		
		
		
		
	}
	
	private static String obtainerUbicationDeJuego() {
		// TODO Auto-generated method stub
		
		if(claseExiste("net.minecraftforge.fml.loading.FMLLoader")) {
			return FMLLoader.getGamePath().toString();
		}else if (claseExiste("net.neoforged.fml.loading.FMLLoader")) {
			Path path;
			try {
				path = (Path)Class.forName("net.neoforged.fml.loading.FMLLoader").getDeclaredMethod("getGamePath", null).invoke(null, null);
				return path.toString();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//Mas
		
		return  Paths.get(System.getProperty("user.dir")).toString();
		
	}

	public static List<String> obtainerClasesDeJuego() {
		// TODO Auto-generated method stub
		List<String> strs = new ArrayList<String>();
		try {
			
			InputStream stream = new FileInputStream(CPWTransformer.mod_jar);
			  try (ZipInputStream zip = new ZipInputStream(stream)) {  
			        ZipEntry entry;  
			        while ((entry = zip.getNextEntry()) != null) {  
			            if (entry.getName().contains("sugarcane") || entry.getName().contains("parchsrg")) {  
			                // Found the gzipped file, now decompress it  
			                try (InputStream gzipStream = zip;  
			                     BufferedInputStream bis = new BufferedInputStream(new GZIPInputStream(gzipStream));  
			                     BufferedReader reader = new BufferedReader(new InputStreamReader(bis))) {  
  
			                    String line;  
			                    while ((line = reader.readLine()) != null) {  
			                    	String[] row_array;
			        				row_array = line.split("¶");
			        				if (row_array.length < 2) {
			        					row_array = line.split("\\u00B6");
			        				}
			        				if(row_array[0].equals("Class") || row_array[0].equals("Clase")) {
			                    	strs.add(row_array[1]);
			        				}
			                    }  
			                }  
			                break; // Assuming we only need one file, so break the loop  
			            }  
			            zip.closeEntry();  
			        }  
			    } catch (IOException e) {  
			        throw new RuntimeException("Error reading or decompressing file", e);  
			    }
			  
			  
			  
			  
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	  		
		
		
		
		
		
		return strs;
	}

	public static List<String> getAllFilesFromDirectory(File directory) {
		List<String> files = new ArrayList<>();
		File[] filesInDir = directory.listFiles();
		if (filesInDir != null) {
			for (File file : filesInDir) {
				if (file.isFile()) {
					files.add(file.getAbsolutePath());
				} else if (file.isDirectory()) {
					files.addAll(getAllFilesFromDirectory(file)); // Recursive call
				}
			}
		}
		return files;
	}
	
	
	public static List<String> getFilesFromJarWithoutSwitchingFromSlashToDot(File jarFile) {
		List<String> jarEntries = new ArrayList<>();
		try (JarFile jar = new JarFile(jarFile)) {
			Enumeration<JarEntry> entries = jar.entries();
			while (entries.hasMoreElements()) {
				JarEntry entry = entries.nextElement();
				jarEntries.add(entry.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jarEntries;
	}
	
	
	
	public static boolean claseExiste(String nombre) {
		try {
			Class clase_mcf = Class.forName(nombre);
			return true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
		return false;//	e.printStackTrace();
		}
	}
	
	
}
