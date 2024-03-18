package com.asbestosstar.assistremapper;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javassist.ByteArrayClassPath;
import javassist.CannotCompileException;
import javassist.ClassMap;
import javassist.ClassPool;
import javassist.CodeConverter;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.AccessFlag;
import javassist.bytecode.ClassFile;
import javassist.bytecode.FieldInfo;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.SourceFileAttribute;

public class RemapperInstance {

	public Mappings mappings;
	public ClassPool classpool;
	public int method_collect = 0;
	public ArrayList<ClassFile> clazzes = new ArrayList<ClassFile>();
	public ArrayList<CtClass> ct_clazzes = new ArrayList<CtClass>();
	public CodeConverter converter = new CodeConverter();
	public String export_location;
	public GenericExtendsFinder gef = new GenericExtendsFinder(this);
	public Map<String, String> slash_classes;
	public int threads = 4;
	public boolean rename_source_files = true;
	public boolean debug_mode = true;
	public boolean clean_classpools = true;
	public boolean disallowDupes = true;

	public RemapperInstance(Mappings mappings, String export_location) {
		this(mappings, ClassPool.getDefault(), export_location);
	}

	public RemapperInstance(Mappings mappings, ClassPool classpool, String export_location) {
		this.mappings = mappings;
		this.classpool = classpool;
		this.export_location = export_location;
	}

	public void addToClasspathJar(JarFile file) {
		try {
			classpool.appendClassPath(file.getName());
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			if (debug_mode) {
				e.printStackTrace();
			}
		}
	}

	public void remapJar(JarFile jarFile) {
		try {
			classpool.appendClassPath(jarFile.getName());
		} catch (NotFoundException e1) {
			// TODO Auto-generated catch block
			if (debug_mode) {
				e1.printStackTrace();
			}
		}

		try {
			Enumeration<JarEntry> entries = jarFile.entries();
			while (entries.hasMoreElements()) {
				JarEntry entry = entries.nextElement();
				if (entry.getName().endsWith(".class")) {

					// InputStream inputStream = jarFile.getInputStream(entry);
					// DataInputStream dataInputStream = new DataInputStream(inputStream);

					CtClass ct = getClassFromPool(entry.getName().replace(".class", "").replace("/", "."));
					ct_clazzes.add(ct);
					ct.defrost();
					clazzes.add(ct.getClassFile());
				}
			}
			jarFile.close();
		} catch (IOException e) {
			if (debug_mode) {
				e.printStackTrace();
			}
		}

		if (clazzes != null) {
			try {

				System.out.println("Getting Depths");
				for (ClassFile item : clazzes) {
					checkInhertitedMaps(item);
				}

				System.out.println("renaming");

				for (ClassFile item : clazzes) {

					rename_all(item);

				}

				System.out.println("Remapping Bodies");
				for (ClassFile item : clazzes) {
					try {
						remap_class(item);
					} catch (CannotCompileException e) {
						// TODO Auto-generated catch block
						if (debug_mode) {
							e.printStackTrace();
						}
					}

				}
				this.slash_classes = getClassesWithSlashes();
				System.out.println("Renaming Classes");
				for (ClassFile item : clazzes) {

					// ExecutorService executorService = Executors.newFixedThreadPool(threads);

					// executorService.submit(() -> {
//						if(clean_classpools) {
//							item.compact();
//						}

					item.renameClass(item.getName(), mappings.getClassMappedName(item.getName()));
					item.setName(mappings.getClassMappedName(item.getName()));

					try {
						item.setSuperclass(mappings.getClassMappedName(item.getSuperclass()));
					} catch (CannotCompileException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NullPointerException e1) {
						e1.printStackTrace();
					}
					reNameAllClasses(item);// Maybe make this use a classCall lib? though it may have the same
					// issues as the method and field versions

					if (rename_source_files) {
						renameSourceFile(item);
					}

					if (clean_classpools) {
						item.compact();
					}

//						return null;
					// });
					// executorService.shutdown();
				}

				System.out.println("Saving Classes");
				for (ClassFile item : clazzes) {
					ExecutorService executorService = Executors.newFixedThreadPool(threads);

//					executorService.submit(() -> {

						writeClassToLocation(item);
		//				return null;
//					});
					executorService.shutdown();

				}

			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				if (debug_mode) {
					e.printStackTrace();
				}
			}
			clazzes.clear();
			ct_clazzes.clear();
			System.out.println("Done if not blinking");

		} else {
			System.out.println("Jar does not appear to have classes");
		}

	}

	public void renameSourceFile(ClassFile item) {
		// TODO Auto-generated method stub
		SourceFileAttribute sf = (SourceFileAttribute) item.getAttribute(SourceFileAttribute.tag);
		String[] og_arr = item.getName().split("\\.");
		String sf_name = og_arr[og_arr.length - 1].split("\\$")[0] + ".java";
		SourceFileAttribute nuevo = new SourceFileAttribute(item.getConstPool(), sf_name);
		if (sf != null) {
			sf.set(nuevo.get());
		} else {
			System.out.println("SF is null for " + item.getName());
		}
	}

	public static String extractClassName(String fullyQualifiedName) {
		int lastDotIndex = fullyQualifiedName.lastIndexOf('.');
		if (lastDotIndex > 0 && lastDotIndex < fullyQualifiedName.length() - 1) {
			return fullyQualifiedName.substring(lastDotIndex + 1);
		}
		return fullyQualifiedName;
	}

	public void writeClassToLocation(ClassFile fil) {

		String name = export_location + "/" + fil.getName().replace(".", "/") + ".class";
		File clazz = new File(name);
		File directory = new File(name.replace(extractClassName(fil.getName()) + ".class", ""));

		directory.mkdirs();

		DataOutputStream out;
		try {
			out = new DataOutputStream(new FileOutputStream(clazz));
			fil.write(out);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			if (debug_mode) {
				e1.printStackTrace();
			}
		}

	}

	public void addClassFromBytesAndName(byte[] bytes, String name) {
		classpool.appendClassPath(new ByteArrayClassPath(name, bytes));
		try {
			classpool.get(name);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			if (debug_mode) {
				e.printStackTrace();
			}
		}
	}

	public Map<String, String> getClassesWithSlashes() {
		HashMap<String, String> map = new HashMap<String, String>();
		for (Map.Entry<String, String> entry : mappings.classes.entrySet()) {
			map.put(entry.getKey().replace(".", "/"), entry.getValue().replace(".", "/"));
		}
		return map;
	}

	public void reNameAllClasses(ClassFile fil) {

		// for (Map.Entry<String, String> entry : mappings.classes.entrySet()) {
//fil.renameClass(entry.getKey(),entry.getValue());

//		for (MethodInfo def : fil.getMethods()) {
//			String ol = mappings.renameClassesInMethodDescriptor(def.getDescriptor());
//			def.setDescriptor(ol);
//
//		}
//
//		for (FieldInfo var : fil.getFields()) {
//			var.setDescriptor(mappings.renameClassesInFieldDescriptor(var.getDescriptor()));
//		}

		//ExecutorService executorService = Executors.newFixedThreadPool(threads);

//		try {
//			CtClass ct = this.getClassFromPool(fil.getName());
//			ct.instrument(new ExprScan(this, true));
//		} catch (CannotCompileException e) {
//			// TODO Auto-generated catch block
//			if (debug_mode) {
//				e.printStackTrace();
//			}
//		}
	//	executorService.shutdown();

		fil.renameClass(slash_classes);// Not Sure if calling the method is fastest or if making a field would
										// be faster but eh its ok for now as for field we would need a way
										// to regen if classes were later added to mappings, we can see
										// later though

		// }

	}

	public void rename_all(ClassFile item) {
		// TODO Auto-generated method stub

		// Params soon
		for (MethodInfo def : item.getMethods()) {
			putMethod(item, def);
		}

		for (FieldInfo var : item.getFields()) { // I soon need to find a way to do undeclared
			putField(item, var);
		}

	}

	public void putMethod(ClassFile item, MethodInfo def) {

		ArrayList<String> recersive_names = new ArrayList<String>();

		String old_nombre = def.getName();
		String nombre = null;
		String key = item.getName().replace("/", ".") + "." + def.getName() + def.getDescriptor();
		if (!mappings.defs.containsKey(key)) {
			if (!key.contains("<")) {
				nombre = getOldestMethodName(item, old_nombre, def.getDescriptor(), recersive_names);
				recersive_names.clear();
			}

		} else {
			nombre = mappings.getDefMappedName(key);
		}

		if (nombre != null) {
			if(this.classFileHasMethod(item.getName(), nombre, def.getDescriptor()) && !old_nombre.equals(nombre) && disallowDupes) {
				System.out.println("Dupe" + item.getName()+nombre+def.getDescriptor());
			}else {
				def.setName(nombre);
			}
			
		}

	}

	public boolean checkIfMethodExists(ClassFile item, String name, String desc) {
		for (MethodInfo def : item.getMethods()) {
			if (def.getName().equals(name) && def.getDescriptor().equals(desc)) {
				return true;
			}
		}
		return false;
	}

	public void putField(ClassFile item, FieldInfo var) {

		ArrayList<String> recersive_names = new ArrayList<String>();

		String old_nombre = var.getName();
		String nombre = mappings
				.getVarMappedName(item.getName().replace("/", ".") + "." + var.getName() + ":" + var.getDescriptor());

		if (nombre.equals(old_nombre)) {
			nombre = getOldestFieldName(item, old_nombre, var.getDescriptor(), recersive_names);
			recersive_names.clear();
		}
		if(this.classFileHasField(item.getName(), nombre, var.getDescriptor())&& !old_nombre.equals(nombre) && disallowDupes) {
			System.out.println("Dupe" + item.getName()+nombre+var.getDescriptor());
		}else {
			var.setName(nombre);
		}
		

	}

	public boolean checkIfFieldExists(ClassFile item, String name, String desc) {
		for (FieldInfo var : item.getFields()) {
			if (var.getName().equals(name) && var.getDescriptor().equals(desc)) {
				return true;
			}
		}
		return false;
	}

	public void remap_class(ClassFile clazz) throws CannotCompileException {

	//	ExecutorService executorService = Executors.newFixedThreadPool(threads);

		try {
			CtClass ct = this.getClassFromPool(clazz.getName());
			ct.instrument(new ExprScan(this, false));

		} catch (CannotCompileException e) {
			// TODO Auto-generated catch block
			if (debug_mode) {
				e.printStackTrace();
			}
		}
//		executorService.shutdown();

		// TODO Auto-generated method stub

	}

	public CtClass getClassFromPool(String name) {
		return getClassFromPool(name, false);
	}

	public CtClass getClassFromPool(String name, boolean is_interface) {
		try {// I also need a delete classes array
			return classpool.get(name);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			if (is_interface) {
				return classpool.makeInterface(name);
			} else {
				return classpool.makeClass(name);
			}
		}
	}

	public CtMethod getMethodFromClass(String name, String desc, CtClass clazz) {
		return getMethodFromClass(name, desc, AccessFlag.PUBLIC, clazz);
	}

	public CtMethod getMethodFromClass(String name, String desc, int accessFlagsmodifiers, CtClass clazz) {
		try {
			return clazz.getMethod(name, desc);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			CtMethod ret = new CtMethod(clazz, name, null, clazz);
			ret.getMethodInfo().setDescriptor(desc);
			ret.setModifiers(accessFlagsmodifiers);
			return ret;
		}
	}

	public CtField getFieldFromClass(String name, String desc, CtClass clazz) {
		try {
			return clazz.getField(name, desc);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();

			try {
				CtField diff = clazz.getField(name);
				return diff;
			} catch (NotFoundException e2) {
				// TODO Auto-generated catch block

				CtField ret = null;
				try {
					ret = new CtField(clazz, name, clazz);
					clazz.addField(ret);
				} catch (CannotCompileException e1) {
					// TODO Auto-generated catch block
					if (debug_mode) {
						e1.printStackTrace();
					}
				}
				ret.getFieldInfo().setDescriptor(desc);
				return ret;
			}

		}
	}

	public void checkInhertitedMaps(ClassFile clazz) {

		ExecutorService executorService = Executors.newFixedThreadPool(4);

		for (CtMethod def : this.getClassFromPool(clazz.getName()).getMethods()) {
			executorService.submit(() -> {
				ArrayList<String> recersive_names = new ArrayList<String>();
				getOldestMethodName(clazz, def.getName(), def.getSignature(), recersive_names);
				recersive_names.clear();


				return null;
			});

		}

		for (CtField var : this.getClassFromPool(clazz.getName()).getFields()) {
			executorService.submit(() -> {
				ArrayList<String> recersive_names = new ArrayList<String>();
				getOldestFieldName(clazz, var.getName(), var.getSignature(), recersive_names);
				recersive_names.clear();
			});
		}

		if (!mappings.classes.containsKey(clazz.getName()) && clazz.getName().contains("$")) {
			mappings.classes.put(clazz.getName(), mappings.parseSubClass(clazz.getName()));
		}


		executorService.shutdown();
	}

	public static boolean hasField(ClassFile cf, String fieldName, String descriptor) {
		for (FieldInfo fieldInfo : cf.getFields()) {
			if (Objects.equals(fieldInfo.getName(), fieldName)
					&& Objects.equals(fieldInfo.getDescriptor(), descriptor)) {
				return true;
			}
		}
		return false;
	}

	public ClassFile getClassFromName(String name) {

		for (ClassFile clazz : clazzes) {
			if (clazz.getName() == name) {
				return clazz;
			}
		}
		return getClassFromPool(name).getClassFile();

	}

	public String getOldestMethodName(ClassFile clazz, String methodName, String desc,
			ArrayList<String> recersive_names) {
		// The idea of searching the mappings for the mapping reference 1st was copied
		// from
		// https://github.com/pocolifo/jar-remapper/blob/main/com.pocolifo.jarremapper/src/main/java/com/pocolifo/jarremapper/
		String className = clazz.getName();

		if (!recersive_names.contains(className)) {

			if (mappings.defs.containsKey(className + "." + methodName + desc)) {
				return mappings.defs.get(className + "." + methodName + desc);
			} else {

				recersive_names.add(className);
				if (this.classFileHasMethod(className, methodName, desc)) { // Soon need to account for descriptorsss

					ArrayList<ClassFile> supers = new ArrayList<ClassFile>();
					if (clazz.getSuperclass() != null) {
						if (!clazz.getSuperclass().equals("java.lang.Object")) {
							supers.add(getClassFromName(clazz.getSuperclass()));
						}
					}
					for (String interface_name : clazz.getInterfaces()) {
						supers.add(getClassFromName(interface_name));
					}

					for (ClassFile super_clazz : supers) {
						String old = getOldestMethodName(super_clazz, methodName, desc, recersive_names);
						if (!old.equals(methodName)) {
							mappings.defs.put(className + "." + methodName + desc, old);
							return old;
						}
					}
				}
			}
		}

		return gef.getPotentialGenericExtendingMethod(clazz, methodName, desc, recersive_names);

	}

	public String getOldestFieldName(ClassFile clazz, String fieldName, String desc,
			ArrayList<String> recersive_names) {
		// The idea of searching the mappings for the mapping reference rather than
		// searching form the oldest from
		// https://github.com/pocolifo/jar-remapper/blob/main/com.pocolifo.jarremapper/src/main/java/com/pocolifo/jarremapper/
		String className = clazz.getName();

		if (!recersive_names.contains(className)) {
			if (mappings.vars.containsKey(className + "." + fieldName + ":" + desc)) {
				return mappings.vars.get(className + "." + fieldName + ":" + desc);
			} else {
				recersive_names.add(className);
				if (this.classFileHasField(className, fieldName, desc)) { // Soon need to account for descriptorsss

					ArrayList<ClassFile> supers = new ArrayList<ClassFile>();
					if (clazz.getSuperclass() != null) {
						if (!clazz.getSuperclass().equals("java.lang.Object")) {
							supers.add(getClassFromName(clazz.getSuperclass()));
						}
					}
					for (String interface_name : clazz.getInterfaces()) {
						supers.add(getClassFromName(interface_name));
					}

					for (ClassFile super_clazz : supers) {
						String old = getOldestFieldName(super_clazz, fieldName, desc, recersive_names);
						if (!old.equals(fieldName)) {
							mappings.vars.put(className + "." + fieldName + ":" + desc, old);
							return old;
						}
					}
				}
			}
		}

		return gef.getPotentialGenericExtendingField(clazz, fieldName, desc, recersive_names);

	}

	public boolean classFileHasField(String clazz, String field_name, String desc) {

		for (CtField field : this.getClassFromPool(clazz).getFields()) {
			if (field.getName().equals(field_name) && field.getSignature().equals(desc)) {
				return true;
			}

		}

		return false;
	}

	public boolean classFileHasMethod(String clazz, String method_name, String desc) {

		for (CtMethod method : this.getClassFromPool(clazz).getMethods()) {
			if (method.getName().equals(method_name) && method.getSignature().equals(desc)) {
				return true;
			}

		}

		return false;
	}

}
