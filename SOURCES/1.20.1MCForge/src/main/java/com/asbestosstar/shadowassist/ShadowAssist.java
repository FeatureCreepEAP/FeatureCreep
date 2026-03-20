package com.asbestosstar.shadowassist;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

public class ShadowAssist {

	public JarFile jar;
	public File output_jar;
	public ClassPool pool;

	public static void main(String[] args) {
		try {
			new ShadowAssist(ClassPool.getDefault(), new JarFile(args[0]), new File(args[1]));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ShadowAssist(ClassPool classPool, JarFile jar, File file) {
		// TODO Auto-generated constructor stub
		this.jar = jar;
		this.output_jar = file;
		this.pool = classPool;
		File tmp = new File(file.getParent() + "/shadowassist/");
		tmp.mkdirs();
		try {
			pool.appendClassPath(jar.getName());
			Enumeration<JarEntry> entries = jar.entries();

			// 遍历原始JAR文件的条目并复制到新JAR文件中
			while (entries.hasMoreElements()) {
				JarEntry entry = entries.nextElement();
				ZipEntry zipEntry = new ZipEntry(entry.getName());
				if (entry.getName().endsWith(".class")) {
					String clazz_name = entry.getName().substring(0, entry.getName().length() - 6).replace("/", ".");
					//System.out.println(clazz_name);
					CtClass clazz = pool.get(clazz_name);
					ClassShadowAssist.shadowClass(clazz, tmp);
				}

			}
			zipDirectory(tmp, file.getAbsolutePath());
			deleteDirectory(tmp.toPath());
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void deleteDirectory(Path path) throws Exception {
		File folder = path.toFile();
		if (folder.exists()) {
// 遍历文件夹中的所有文件和子文件夹  
			File[] files = folder.listFiles();
			if (files != null) {
				for (File file : files) {
					if (file.isDirectory()) {
// 如果是子文件夹，则递归调用此方法  
						deleteDirectory(file.toPath());
					} else {
// 如果是文件，则直接删除  
						if (!file.delete()) {
							throw new Exception("Unable to delete file: " + file.getName());
						}
					}
				}
			}

// 删除空文件夹  
			if (!folder.delete()) {
				throw new Exception("Unable to delete folder: " + folder.getName());
			}
		}
	}

	public static void zipDirectory(File directory, String output) throws IOException {
		try {
			// Create a new ZIP file in the native mods folder
			File zipFile = new File(output);

			// Create a ZIP output stream
			try (FileOutputStream fos = new FileOutputStream(zipFile); ZipOutputStream zos = new ZipOutputStream(fos)) {

				// Recursively add files and directories to the ZIP output stream
				zipDirectory(directory, zos, "");

				System.out.println("ZIP file created successfully!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void zipDirectory(File directory, ZipOutputStream zos, String parentFolder) throws IOException {
		File[] files = directory.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					// If it's a directory, recursively call this method
					zipDirectory(file, zos, parentFolder + file.getName() + "/");
				} else {
					// If it's a file, add it to the ZIP output stream
					addToZip(zos, file, parentFolder + file.getName());
				}
			}
		}
	}

	public static void addToZip(ZipOutputStream zos, File file, String fileName) throws IOException {
		try (FileInputStream fis = new FileInputStream(file)) {
			// Create a new ZIP entry with the file's name
			ZipEntry zipEntry = new ZipEntry(fileName);
			zos.putNextEntry(zipEntry);

			// Copy the file's content to the ZIP output stream
			byte[] buffer = new byte[1024];
			int length;
			while ((length = fis.read(buffer)) > 0) {
				zos.write(buffer, 0, length);
			}

			// Close the current ZIP entry
			zos.closeEntry();
		}
	}

}
