package com.asbestosstar.mixerlogger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.spongepowered.asm.mixin.Mixin;

import javassist.CannotCompileException;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.ArrayMemberValue;
import javassist.bytecode.annotation.ClassMemberValue;
import javassist.bytecode.annotation.MemberValue;

public class MixerLoggerMain {

	public static boolean done = false;
	public static ClassPool pool = new ClassPool();

	public static void doit() {
		if (!done) {
			System.out.println(
					"MixerLogger! This should be removed in a production enviornment and only be used for testing crashes or else it may cause new ones!");
			String currentPath = System.getProperty("user.dir");
			// Concatenate the current path with "/mods/"
			File modsFolder = new File(currentPath + File.separator + "mods");
			File log = new File(currentPath + File.separator + "logs/MixerLogger.log");
			log.getParentFile().mkdirs();

			pool.appendClassPath(new ClassClassPath(Mixin.class));

			List<ClassInfo> mixerClasses = findMixerClasses(modsFolder);

			try {
				FileWriter fileWriter = new FileWriter(log, true);
				PrintWriter printWriter = new PrintWriter(fileWriter);

				for (ClassInfo classInfo : mixerClasses) {
					String className = "Class Name: " + classInfo.getCtClass().getName();
					String targets = "Targets: " + classInfo.getTargets();
					String classRef = "ClassRef: " + classInfo.getRefClasses();
					// Instrument the class (assuming this method is defined)
					classInfo.instrumentExpr();
					String methodRef = "MethodRef: " + classInfo.getRefMethods();
					String fieldRef = "FieldRef: " + classInfo.getRefFields();
					String originatingJar = "Originating JAR: " + classInfo.getJarFile();

					// Print to the console
					System.out.println(className);
					System.out.println(targets);
					System.out.println(classRef);
					System.out.println(methodRef);
					System.out.println(fieldRef);
					System.out.println(originatingJar);
					System.out.println("--------------------");

					// Append to the log file
					printWriter.println(className);
					printWriter.println(targets);
					printWriter.println(classRef);
					printWriter.println(methodRef);
					printWriter.println(fieldRef);
					printWriter.println(originatingJar);
					printWriter.println("--------------------");
				}
				printWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		done = true;
	}

	public static List<ClassInfo> findMixerClasses(File folder) {
		List<ClassInfo> mixinClasses = new ArrayList<>();
		Set<String> visitedJars = new HashSet<>();

		for (File file : folder.listFiles()) {
			if (file.isDirectory()) {
				mixinClasses.addAll(findMixerClasses(file));
			} else if (file.getName().endsWith(".jar")) {
				findMixerClassesInJar(file, mixinClasses, visitedJars);
			}
		}

		return mixinClasses;
	}

	public static void findMixerClassesInJar(File jarFile, List<ClassInfo> mixinClasses, Set<String> visitedJars) {
		try (ZipFile zipFile = new ZipFile(jarFile)) {
			findMixerClassesInJar(zipFile, jarFile.getName(), mixinClasses, visitedJars);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void findMixerClassesInJar(ZipFile zipFile, String jarPath, List<ClassInfo> mixinClasses,
			Set<String> visitedJars) {
		Enumeration<? extends ZipEntry> entries = zipFile.entries();

		while (entries.hasMoreElements()) {
			ZipEntry entry = entries.nextElement();
			String name = entry.getName();
			if (name.endsWith(".jar") && !visitedJars.contains(jarPath + "!" + name)) {
				visitedJars.add(jarPath + "!" + name);
				try (InputStream nestedJarStream = zipFile.getInputStream(entry)) {
					findMixerClassesInNestedJar(nestedJarStream, mixinClasses, visitedJars, jarPath);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (name.endsWith(".class")) {
				String className = name.replace('/', '.').substring(0, name.length() - 6);
				try (InputStream classStream = zipFile.getInputStream(entry)) {
					ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
					byte[] buffer = new byte[1024];
					int bytesRead;
					while ((bytesRead = classStream.read(buffer)) != -1) {
						byteArrayOutputStream.write(buffer, 0, bytesRead);
					}
					byte[] classBytes = byteArrayOutputStream.toByteArray();
					if (isMixerClass(className, classBytes)) {
						try {
							mixinClasses.add(new ClassInfo(pool.get(className), jarPath, false));
						} catch (NotFoundException e) {
							e.printStackTrace();
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void findMixerClassesInNestedJar(InputStream nestedJarStream, List<ClassInfo> mixinClasses,
			Set<String> visitedJars, String original_jar) {
		try (ZipInputStream zipInputStream = new ZipInputStream(nestedJarStream)) {
			ZipEntry entry;
			while ((entry = zipInputStream.getNextEntry()) != null) {
				String name = entry.getName();
				if (name.endsWith(".class")) {
					String className = name.replace('/', '.').substring(0, name.length() - 6);
					byte[] classBytes = new byte[1024];
					int length;
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					while ((length = zipInputStream.read(classBytes)) > 0) {
						baos.write(classBytes, 0, length);
					}
					byte[] fullClassBytes = baos.toByteArray();
					if (isMixerClass(className, fullClassBytes)) {
						// Here you need to construct the ClassInfo object with the appropriate values
						// Since we don't have the jar file path for nested classes, you might need to
						// adjust this part
						// For demonstration, I'm assuming you have a way to handle this (e.g., by
						// passing a generic path or adjusting the ClassInfo constructor)
						try {
							mixinClasses.add(new ClassInfo(pool.get(className), original_jar, false));
						} catch (NotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} // TODO recursive

				zipInputStream.closeEntry();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean isMixerClass(String className, byte[] classBytes) {

		try {
			// pool.insertClassPath(new ByteArrayClassPath(className.replace("/", "."),
			// classBytes));
			// CtClass ctClass = pool.get(className.replace(".", "/"));
			ClassFile clazz_fil = new ClassFile(new DataInputStream(new ByteArrayInputStream(classBytes)));
			CtClass ctClass = pool.makeClass(clazz_fil, false);
			if (ctClass.hasAnnotation(Mixin.class)) {
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public static class ClassInfo {
		public CtClass ct;
		public String jarFile;
		public boolean nestedJar;
		public ExprMixerScanner expr = new ExprMixerScanner(this);
		public List<String> method_refs = new ArrayList<String>();
		public List<String> field_refs = new ArrayList<String>();

		public ClassInfo(CtClass classfile, String jarFile, boolean nestedJar) {
			this.ct = classfile;
			this.jarFile = jarFile;
			this.nestedJar = nestedJar;
		}

		public CtClass getCtClass() {
			return ct;
		}

		public String getJarFile() {
			return jarFile;
		}

		public boolean isNestedJar() {
			return nestedJar;
		}

		// This method is a placeholder and would need to be implemented to extract the
		// actual targets.
		public List<String> getTargets() {
			try {
				Mixin sm = (Mixin) ct.getAnnotation(Mixin.class);
				List<String> ends = new ArrayList<String>();
				ends.addAll(Arrays.asList(sm.targets()));

				// for (Class clazz:sm.value()) {
				// ends.add(clazz.getCanonicalName());
				// }

				// ClassFilePrinter.print(ct.getClassFile());

				AnnotationsAttribute attr = (AnnotationsAttribute) ct.getClassFile()
						.getAttribute(AnnotationsAttribute.invisibleTag);// Seems to be only invisible for SMs

				Annotation ann = attr.getAnnotation(Mixin.class.getCanonicalName());
				if (ann != null) {
					ArrayMemberValue ann_arr = (ArrayMemberValue) ann.getMemberValue("value");
					if (ann_arr != null) {
						for (MemberValue mem : ann_arr.getValue()) {
							ClassMemberValue classmem = (ClassMemberValue) mem;
							ends.add(classmem.getValue());
						}
					}
				}

//ends.addAll(attr.getConstPool().getClassNames());

				return ends;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return new ArrayList<>();
		}

		public List<String> getRefClasses() {
			List<String> clazzes = new ArrayList<String>();
			clazzes.addAll(ct.getRefClasses());
			return clazzes;
		}

		public void instrumentExpr() {
			try {
				ct.instrument(expr);
			} catch (CannotCompileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public List<String> getRefMethods() {
			// TODO Auto-generated method stub
			return method_refs;
		}

		public List<String> getRefFields() {
			// TODO Auto-generated method stub
			return field_refs;
		}

	}

}
