package com.asbestosstar.assistremapper;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.jar.JarFile;

public class AssistRemapper {

	public static void main(String[] args) throws Exception {
		if (args.length == 3) {
				 mapJar(new JarFile(args[0]), new FileInputStream(args[1]), args[2]);		
		}else {
			System.out.println("Nessesary Format. Path-To/File.jar Path-To/Mappings.pdme Path-To/Output.jar");
		}
	}
	
	public static void mapJar(JarFile file, InputStream mappings, String output_path) throws Exception {
		Mappings maps = new Mappings(mappings);
		RemapperInstance remapper = new RemapperInstance(maps, output_path);
	remapper.remapJar(file);
	}
	
	
}
