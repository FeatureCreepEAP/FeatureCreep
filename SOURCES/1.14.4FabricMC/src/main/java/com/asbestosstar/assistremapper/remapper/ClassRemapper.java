package com.asbestosstar.assistremapper.remapper;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.asbestosstar.assistremapper.ExprScan;
import com.asbestosstar.assistremapper.GenericExtendsFinder;
import com.asbestosstar.assistremapper.Mappings;
import com.asbestosstar.assistremapper.RemapperInstance;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.AccessFlag;
import javassist.bytecode.BootstrapMethodsAttribute;
import javassist.bytecode.BootstrapMethodsAttribute.BootstrapMethod;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.EnclosingMethodAttribute;
import javassist.bytecode.FieldInfo;
import javassist.bytecode.InnerClassesAttribute;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.SourceFileAttribute;

public class ClassRemapper implements RemapperInstance {

	public Mappings mappings;
	public ClassPool classpool;
	public ClassFile file;
	public String export_location;
	public GenericExtendsFinder gef = new GenericExtendsFinder(this);
	public boolean rename_source_files = true;
	public boolean debug_mode = true;
	public boolean clean_classpools = true;
	public boolean disallowDupes = true;
	public ArrayList<ClassFile> clazzes = new ArrayList<ClassFile>(); // For Jar Remapping or multiclass files
	public ArrayList<ClassFile> not_remapped_classes = new ArrayList<ClassFile>();
	public int threads;
	public boolean dont_export = false;
	public ArrayList<String> vardefnewnamesogdefs = new ArrayList<String>();
	public boolean useResourceInputStream = false;
	public ClassLoader resourceInputStreamLoader = RemapperInstance.class.getClassLoader();
	public boolean load_class_from_pool = true;
	
	
	public ClassRemapper(Mappings mappings, ClassPool pool, ClassFile file, String export_location, int threads) {
		this.mappings = mappings;
		this.classpool = pool;
		this.file = file;
		this.export_location = export_location;
		this.threads = threads;
	}

	public ClassFile remap_class() {

		System.out.println("Getting Depths");
		updateIncludes(false);
		checkInhertitedMaps();

		System.out.println("Remapping Bodies");

		try {
			remapMethodBodies();
		} catch (CannotCompileException e) {
			// TODO Auto-generated catch block
			if (debug_mode) {
				e.printStackTrace();

			}
		}

		System.out.println("renaming");

		rename_all();

		System.out.println("Renaming Classes");

		reNameAllClasses();// Maybe make this use a classCall lib? though it may have the same
		// issues as the method and field versions

		if (rename_source_files) {
			renameSourceFile();
		}

		if (clean_classpools) {
			cleanClassPool();
		}

//						return null;
		// });
		// executorService.shutdown();

		if (!dont_export) {
			System.out.println("Saving Classes");
			ExecutorService executorService = Executors.newFixedThreadPool(threads);

			writeClassToLocation();

			executorService.shutdown();

		}

		System.out.println("Done if not blinking");

		return file;

	}

	public void renameSourceFile() {
		// TODO Auto-generated method stub
		SourceFileAttribute sf = (SourceFileAttribute) file.getAttribute(SourceFileAttribute.tag);
		String[] og_arr = file.getName().split("\\.");
		String sf_name = og_arr[og_arr.length - 1].split("\\$")[0] + ".java";
		SourceFileAttribute nuevo = new SourceFileAttribute(file.getConstPool(), sf_name);
		if (sf != null) {
			sf.set(nuevo.get());
		} else {
			System.out.println("SF is null for " + file.getName());
		}
	}

	public void writeClassToLocation() {

		String name = export_location + "/" + file.getName().replace(".", "/") + ".class";
		File clazz = new File(name);
		File directory = new File(name.replace(RemapperInstance.extractClassName(file.getName()) + ".class", ""));

		directory.mkdirs();

		DataOutputStream out;
		try {
			out = new DataOutputStream(new FileOutputStream(clazz));
			file.write(out);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			if (debug_mode) {
				e1.printStackTrace();
			}
		}

	}


	public void reNameAllClasses() {

		InnerClassesAttribute inners = (InnerClassesAttribute) file.getAttribute(InnerClassesAttribute.tag);
		if (inners != null) {
			int rec = 0;
			while (rec < inners.tableLength()) {
				String og = inners.innerClass(rec);

				String full = mappings.getClassMappedName(og);
				String sub;

				if (full.contains("$")) {
					String[] split = full.split("\\$");
					sub = split[split.length - 1];
				} else {
					sub = full;
				}

				inners.setInnerNameIndex(rec, file.getConstPool().addUtf8Info(sub));

				rec++;
			}
		}

		file.renameClass(file.getName(), mappings.getClassMappedName(file.getName()));
		file.setName(mappings.getClassMappedName(file.getName()));

		if (mappings.getClasses().containsKey(file.getSuperclass())) {
			try {
				file.setSuperclass(mappings.getClassMappedName(file.getSuperclass()));
			} catch (CannotCompileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullPointerException e1) {
				e1.printStackTrace();
			}
		}

		file.renameClass(mappings.getJVMClasses());// Not Sure if calling the method is fastest or if making a field
													// would
		// be faster but eh its ok for now as for field we would need a way
		// to regen if classes were later added to mappings, we can see
		// later though
		// }
	}

	public void rename_all() {
		// TODO Auto-generated method stub

		// Params soon
		for (MethodInfo def : file.getMethods()) {
			putMethod(def);
		}

		for (FieldInfo var : file.getFields()) { // I soon need to find a way to do undeclared
			putField(var);
		}

	}

	// Perhaps move this with someof the very similar ExprScan code? . We also need
	// to try in the future to avoid potential dupes if they exist
	public void renameBootStrapMethods() {
		// TODO Auto-generated method stub

		BootstrapMethodsAttribute bm = (BootstrapMethodsAttribute) file.getAttribute(BootstrapMethodsAttribute.tag);
		if (bm != null) {

			for (BootstrapMethod def : bm.getMethods()) {

				int handle = def.methodRef;
				int index = file.getConstPool().getMethodHandleIndex(handle);
				int tipoynombre = file.getConstPool().getMethodrefNameAndType(index);
				int nombre = file.getConstPool().getNameAndTypeName(tipoynombre);
				int desc = file.getConstPool().getNameAndTypeDescriptor(tipoynombre);
				String nombre_string = file.getConstPool().getUtf8Info(nombre);
				String desc_string = file.getConstPool().getUtf8Info(desc);
				String clazz = file.getConstPool().getMethodrefClassName(index);
//			int handle =	def.methodRef;

				if (file.getConstPool().getTag(handle) == ConstPool.CONST_MethodHandle) {

//					if (clazz.equals("java.lang.runtime.ObjectMethods") && nombre_string.equals("bootstrap")
//							&& desc_string.equals(
//									"(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/TypeDescriptor;Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/invoke/MethodHandle;)Ljava/lang/Object;")) {
//						
//						recordcaller(def);
//					}
//					 else {

					for (int arg : def.arguments) {
						int tag = file.getConstPool().getTag(arg);
						if (tag == 15) { // MethodHandleInfo.tag

							int ref = file.getConstPool().getMethodHandleKind(arg);
							int refered = file.getConstPool().getMethodHandleIndex(arg);
							int argtipoynombre = file.getConstPool().getMethodrefNameAndType(refered);
							int existing_nombre = file.getConstPool().getNameAndTypeName(argtipoynombre);
							String existing_nombre_string = file.getConstPool().getUtf8Info(existing_nombre);

							int existing_desc = file.getConstPool().getNameAndTypeDescriptor(argtipoynombre);
							String argdesc_string = file.getConstPool().getUtf8Info(existing_desc);
							ArrayList<String> recu = new ArrayList<String>();
							String oldest;
							String argclazz = file.getConstPool().getMethodrefClassName(refered);

							if (ref == ConstPool.REF_getField // || ref == ConstPool.CONST_Fieldref reenable if needed
																// but it conflicts with the interfaceref
									|| ref == ConstPool.REF_putField) {
								oldest = getOldestFieldName(getClassFromName(argclazz), existing_nombre_string,
										argdesc_string, recu);
							} else {// Method and field classname may be similar but we just add both in case
								oldest = getOldestMethodName(getClassFromName(argclazz), existing_nombre_string,
										argdesc_string, recu);
							}

							if (!existing_nombre_string.equals(oldest)) {

								try {
									Method additem = ConstPool.class.getDeclaredMethod("getItem", int.class);
									additem.setAccessible(true);
									Object inf = additem.invoke(file.getConstPool(), argtipoynombre);
									// Should make this check for instance 1st
									Class<?> ref_info = Class.forName(
											ConstPool.class.getPackage().getName() + ".NameAndTypeInfo", false,
											file.getConstPool().getClass().getClassLoader());
									Field nati = ref_info.getDeclaredField("memberName");
									nati.setAccessible(true);
									nati.setInt(inf, file.getConstPool().addUtf8Info(oldest));
								} catch (NoSuchMethodException | SecurityException | IllegalAccessException
										| IllegalArgumentException | InvocationTargetException | ClassNotFoundException
										| NoSuchFieldException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}

						}

					}
				}
			}

		}
	}
	// }

//TODO maybe remove clazz and use arg[0]
	public void recordcaller(BootstrapMethod def) {
		// TODO Auto-generated method stub
		int[] args = def.arguments;
		String clazz = file.getConstPool().getClassInfo(args[0]);
		String existing = file.getConstPool().getStringInfo(args[1]);
		String[] split = existing.split(";");
		List<String> to_join = new ArrayList<String>();
		if (args.length > 2) {// There is likely a better way to do this but i am still not too farmilar with
								// records so for now this will do
			int i = 2;

			for (String nombre : split) {
				int arg = args[i];

				int ref = file.getConstPool().getMethodHandleKind(arg);
				int refered = file.getConstPool().getMethodHandleIndex(arg);
				int tipoynombre = file.getConstPool().getMethodrefNameAndType(refered);
				int existing_nombre = file.getConstPool().getNameAndTypeName(tipoynombre);
				String existing_nombre_string = file.getConstPool().getUtf8Info(existing_nombre);
				int existing_desc = file.getConstPool().getNameAndTypeDescriptor(tipoynombre);
				String desc_string = file.getConstPool().getUtf8Info(existing_desc);

				ArrayList<String> recu = new ArrayList<String>();
				String oldest;

				if (ref == ConstPool.REF_getField || ref == ConstPool.CONST_Fieldref || ref == ConstPool.REF_putField) {
					oldest = getOldestFieldName(getClassFromName(clazz.replace("/", ".")), existing_nombre_string,
							desc_string, recu);
				} else {
					oldest = getOldestMethodName(getClassFromName(clazz.replace("/", ".")), existing_nombre_string,
							desc_string, recu);
				}
				to_join.add(oldest);

				if (!existing_nombre_string.equals(oldest)) {

					try {
						Method additem = ConstPool.class.getDeclaredMethod("getItem", int.class);
						additem.setAccessible(true);
						Object inf = additem.invoke(file.getConstPool(), tipoynombre);
						// Should make this check for instance 1st
						Class<?> ref_info = Class.forName(ConstPool.class.getPackage().getName() + ".NameAndTypeInfo",
								false, file.getConstPool().getClass().getClassLoader());
						Field nati = ref_info.getDeclaredField("memberName");
						nati.setAccessible(true);
						nati.setInt(inf, file.getConstPool().addUtf8Info(oldest));
					} catch (NoSuchMethodException | SecurityException | IllegalAccessException
							| IllegalArgumentException | InvocationTargetException | ClassNotFoundException
							| NoSuchFieldException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

				i++;
			}
			if (to_join.size() > 0) {
				String arg1 = String.join(";", to_join);
				if (existing != arg1) {
					try {
						Method additem = ConstPool.class.getDeclaredMethod("getItem", int.class);
						additem.setAccessible(true);
						Object inf = additem.invoke(file.getConstPool(), args[1]);
						// Should make this check for instance 1st
						Class<?> ref_info = Class.forName(ConstPool.class.getPackage().getName() + ".StringInfo", false,
								file.getConstPool().getClass().getClassLoader());
						Field nati = ref_info.getDeclaredField("string");
						nati.setAccessible(true);
						nati.setInt(inf, file.getConstPool().addUtf8Info(arg1));
					} catch (NoSuchMethodException | SecurityException | IllegalAccessException
							| IllegalArgumentException | InvocationTargetException | ClassNotFoundException
							| NoSuchFieldException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}

	}

	public void putMethod(MethodInfo def) {

		ArrayList<String> recersive_names = new ArrayList<String>();

		String old_nombre = def.getName();
		String nombre = null;
		String key = file.getName().replace("/", ".") + "." + def.getName() + def.getDescriptor();
		if (!mappings.getDefs().containsKey(key)) {
			if (!key.contains("<")) {
				nombre = getOldestMethodName(file, old_nombre, def.getDescriptor(), recersive_names);
				recersive_names.clear();
			}

		} else {
			nombre = mappings.getDefMappedName(key);
		}

		if (nombre != null) {
			String anti_dupe_lleve = nombre+def.getDescriptor();
			if (this.classFileHasMethod(file.getName(), nombre, def.getDescriptor()) // This should be changed to account for when there are dupes which a duped name is duped with a non remapped, other wise its basically useless 
					&& !old_nombre.equals(nombre)
					&& disallowDupes && this.vardefnewnamesogdefs.contains(anti_dupe_lleve)) {
				System.out.println("Dupe" + file.getName() + nombre + def.getDescriptor());
			} else if (!old_nombre.equals(nombre)) {
				def.setName(nombre);
				this.vardefnewnamesogdefs.add(anti_dupe_lleve);
			}

		}

	}

	public boolean checkIfMethodExists(String name, String desc) {
		for (MethodInfo def : file.getMethods()) {
			if (def.getName().equals(name) && def.getDescriptor().equals(desc)) {
				return true;
			}
		}
		return false;
	}

	public void putField(FieldInfo var) {

		ArrayList<String> recersive_names = new ArrayList<String>();

		String old_nombre = var.getName();
		String nombre = mappings
				.getVarMappedName(file.getName().replace("/", ".") + "." + var.getName() + ":" + var.getDescriptor());

		if (nombre.equals(old_nombre)) {
			nombre = getOldestFieldName(file, old_nombre, var.getDescriptor(), recersive_names);
			recersive_names.clear();
		}
		
		String anti_dupe_lleve = nombre+":"+var.getDescriptor();
		if (this.classFileHasField(file.getName(), nombre, var.getDescriptor()) // This should be changed to account for when there are dupes which a duped name is duped with a non remapped, other wise its basically useless 
				&& !old_nombre.equals(nombre)
				&& disallowDupes && this.vardefnewnamesogdefs.contains(anti_dupe_lleve)) {
			System.out.println("Dupe" + file.getName() + nombre + var.getDescriptor());
		} else if (!nombre.equals(old_nombre)) {
			var.setName(nombre);
			this.vardefnewnamesogdefs.add(anti_dupe_lleve);
		}

	}
	
	
	
	public boolean checkIfFieldExists(String name, String desc) {
		for (FieldInfo var : file.getFields()) {
			if (var.getName().equals(name) && var.getDescriptor().equals(desc)) {
				return true;
			}
		}
		return false;
	}

	public void remapMethodBodies() throws CannotCompileException {

		// ExecutorService executorService = Executors.newFixedThreadPool(threads);

		try {
			CtClass ct = this.getClassFromPool(file.getName());
			ct.instrument(new ExprScan(this, false));

		} catch (CannotCompileException e) {
			// TODO Auto-generated catch block
			if (debug_mode) {
				e.printStackTrace();
			}
		}

		renameBootStrapMethods();
		remapInvokeDyamics();
		renameEnclosedMethods();

		// executorService.shutdown();

		// TODO Auto-generated method stub

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

	public void checkInhertitedMaps() {

		ExecutorService executorService = Executors.newFixedThreadPool(threads);

		for (CtMethod def : this.getClassFromPool(file.getName()).getMethods()) {
			executorService.submit(() -> {
				ArrayList<String> recersive_names = new ArrayList<String>();
				getOldestMethodName(file, def.getName(), def.getSignature(), recersive_names);
				recersive_names.clear();

				return null;
			});

		}

		for (CtField var : this.getClassFromPool(file.getName()).getFields()) {
			executorService.submit(() -> {
				ArrayList<String> recersive_names = new ArrayList<String>();
				getOldestFieldName(file, var.getName(), var.getSignature(), recersive_names);
				recersive_names.clear();
			});
		}

		if (!mappings.getClasses().containsKey(file.getName()) && file.getName().contains("$")) {
			mappings.getClasses().put(file.getName(), mappings.parseSubClass(file.getName()));
		}

		executorService.shutdown();
	}

	

	@Override
	public ClassPool getClassPool() {
		// TODO Auto-generated method stub
		return classpool;
	}

	public void cleanClassPool() {
		if (!file.getSuperclass().replace("/", ".").equals("java.lang.Record")) { // At this time compact does not work
																					// well with attribute record
			file.compact();
		}
	}

	// If include already exist it will not try to find more
	public void updateIncludes(boolean skipIfExists) {
		// TODO Auto-generated method stub

		String nombre = file.getName();
		boolean contains = mappings.getIncludes().containsKey(nombre);
		if (skipIfExists && contains) {
			return;
		}

		List<String> sups = new ArrayList<String>();
		if (contains) {
			for (String sup : mappings.getIncludes().get(nombre)) {
				sups.add(sup);
			}
		}

		String supe = file.getSuperclass();
		if (supe != null && !supe.equals("java.lang.Object")) {
			sups.add(supe);
		}

		for (String inter : file.getInterfaces()) {
			sups.add(inter);
		}

		if (sups.size() > 0) {
			String[] incls = new String[sups.size()];
			int i = 0;
			for (String res : sups) {
				incls[i] = res;
				i++;
			}
			mappings.getIncludes().put(nombre, incls);
		}

	}

	public void remapInvokeDyamics() {
		ConstPool pool = file.getConstPool();
		for (int i = 1; i < pool.getSize(); i++) {
			if (pool.getTag(i) == 18) {
				int nameandtype = pool.getInvokeDynamicNameAndType(i);
				String old_name = pool.getUtf8Info(pool.getNameAndTypeName(nameandtype));
				String old_desc = pool.getUtf8Info(pool.getNameAndTypeDescriptor(nameandtype));

				// Try with return as class
				if (old_desc.contains(")L")) {
					String returning_class = old_desc.split("\\)L")[1].replace(";", "").replace("/", ".");
					// THIS IS VERY TEMPORARY AND ONLY WORKS IN SOME CASES AND RELYS ON FUNCTIONAL
					// INTERFACES ONLY HAVING 1 METHOD ESPECIALLY OF THAT NAME
					// String oldest = getOldestMethodName(getClassFromName(returning_class),
					// old_name, "()Ljava/lang/Object;", new ArrayList<String>());
					String oldest = null;
					for (Map.Entry<String, String> clazz : mappings.getDefs().entrySet()) {
						if (clazz.getKey().startsWith(returning_class + "." + old_name + "(")) {
							oldest = clazz.getValue();
						}

					}

					if (oldest != null) {

						if (!old_name.equals(oldest)) {

							try {
								Method additem = ConstPool.class.getDeclaredMethod("getItem", int.class);
								additem.setAccessible(true);
								Object inf = additem.invoke(pool, nameandtype);
								// Should make this check for instance 1st
								Class<?> ref_info = Class.forName(
										ConstPool.class.getPackage().getName() + ".NameAndTypeInfo", false,
										pool.getClass().getClassLoader());
								Field nati = ref_info.getDeclaredField("memberName");
								nati.setAccessible(true);
								nati.setInt(inf, pool.addUtf8Info(oldest));
							} catch (NoSuchMethodException | SecurityException | IllegalAccessException
									| IllegalArgumentException | InvocationTargetException | ClassNotFoundException
									| NoSuchFieldException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}

					}

				}
			}

		}

	}

	public void renameEnclosedMethods() {
		// TODO Auto-generated method stub

		// Not sure if there can be more than 1
		EnclosingMethodAttribute em = (EnclosingMethodAttribute) file.getAttribute(EnclosingMethodAttribute.tag);
		if (em != null && em.methodIndex() != 0) {
			String oldest = getOldestMethodName(getClassFromName(em.className().replace("/", ".")), em.methodName(),
					em.methodDescriptor(), new ArrayList<String>());

			if (!oldest.equals(em.methodName())) {
				file.removeAttribute(EnclosingMethodAttribute.tag); // Perhaps replacing nameandtype would be better
				file.addAttribute(new EnclosingMethodAttribute(file.getConstPool(), em.className(), oldest,
						em.methodDescriptor()));
			}
		}

	}
	
	
	
	
	
	@Override
	public void setDebugMode(boolean bool) {
		// TODO Auto-generated method stub
		this.debug_mode=bool;
	}

	@Override
	public boolean debugMode() {
		// TODO Auto-generated method stub
		return this.debug_mode;
	}

	@Override
	public ArrayList<ClassFile> getRemapClassFiles() {
		// TODO Auto-generated method stub
		return this.clazzes;
	}

	@Override
	public ArrayList<ClassFile> getUnRemappedClassFiles() {
		// TODO Auto-generated method stub
		return this.not_remapped_classes;
	}

	@Override
	public boolean useResourceInputStream() {
		// TODO Auto-generated method stub
		return this.useResourceInputStream;
	}

	@Override
	public void setUseResourceInputStream(boolean bool) {
		// TODO Auto-generated method stub
		this.useResourceInputStream=bool;
	}

	@Override
	public ClassLoader loaderForResourceInputStream() {
		// TODO Auto-generated method stub
		return this.loaderForResourceInputStream();
	}

	@Override
	public void setLoaderForResourceInputStream(ClassLoader loader) {
		// TODO Auto-generated method stub
		this.resourceInputStreamLoader=loader;
	}

	@Override
	public boolean loadFromClassPool() {
		// TODO Auto-generated method stub
		return this.load_class_from_pool;
	}

	@Override
	public void setLoadFromClassPool(boolean bool) {
		// TODO Auto-generated method stub
		load_class_from_pool=bool;
	}

	@Override
	public Mappings getMappings() {
		// TODO Auto-generated method stub
		return this.mappings;
	}

	@Override
	public GenericExtendsFinder getGenericExtendsFinder() {
		// TODO Auto-generated method stub
		return this.gef;
	}
	
	
	

}

