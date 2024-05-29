package com.asbestosstar.assistremapper;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.jar.JarFile;

public class AssistRemapper {

	public static void main(String[] args) throws Exception {
		if (args.length == 3) {
			mapJar(new JarFile(args[0]), new FileInputStream(args[1]), args[2]);
		} else {
			System.out.println("Nessesary Format. Path-To/File.jar Path-To/Mappings.pdme Path-To/Output.jar");
		}
	}

	@Deprecated
	public static void mapJar(JarFile file, InputStream mappings, String output_path) throws Exception {
		mapJar(file, mappings, output_path, new String[] {});
	}

	public static void mapJar(JarFile file, InputStream mappings, String output_path, String[] args) throws Exception {
		Mappings maps = new Mappings(mappings);
		RemapperInstance remapper = new RemapperInstance(maps, output_path);
		parseArgs(args, remapper);
		remapper.remapJar(file);
	}

	public static void parseArgs(String[] args, RemapperInstance remapper) {
		for (String arg : args) {// switch to i based loop soon
			if (arg.equals("--keepSourceFile") || arg.equals("--conservarArchivoFuente")) {
				remapper.rename_source_files = false;
			}
			if (arg.equals("--dontCleanConstantPool") || arg.equals("--noLimpiarPiscinaConstante")) {
				remapper.clean_classpools = true;
			}
		}

	}

}
