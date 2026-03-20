package com.asbestosstar.assistremapper;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.bytecode.ClassFile;
import javassist.bytecode.FieldInfo;
import javassist.bytecode.MethodInfo;

public interface RemapperInstance {

	public void setDebugMode(boolean bool);

	public boolean debugMode();

	public ClassPool getClassPool();

	public ArrayList<ClassFile> getRemapClassFiles();

	public ArrayList<ClassFile> getUnRemappedClassFiles();
	
	public boolean loadFromClassPool();
	
	public void setLoadFromClassPool(boolean bool);
	
	public Mappings getMappings();
	
	public GenericExtendsFinder getGenericExtendsFinder();

	public default ArrayList<ClassFile> getClassFilePool() {
		ArrayList<ClassFile> combinado = new ArrayList<ClassFile>();
		combinado.addAll(getUnRemappedClassFiles());
		for (ClassFile arc : getRemapClassFiles()) {
			if (!combinado.contains(arc)) {
				combinado.add(arc);
			}
		}

		return combinado;
	}

	public default CtClass getClassFromPool(String name) {
		return getClassFromPool(name, false);
	}

	public default CtClass getClassFromPool(String name, boolean is_interface) {
		try {// I also need a delete classes array
			return getClassPool().get(name);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			if (is_interface) {
				return getClassPool().makeInterface(name);
			} else {
				return getClassPool().makeClass(name);
			}
		}
	}

	public boolean useResourceInputStream();

	public void setUseResourceInputStream(boolean bool);

	public ClassLoader loaderForResourceInputStream();

	public void setLoaderForResourceInputStream(ClassLoader loader);

	public default void addToClasspathJarFromStream(InputStream jarInputStream, boolean to_remap) {
		try (JarInputStream jarInput = new JarInputStream(new BufferedInputStream(jarInputStream))) {
			JarEntry entry;
			while ((entry = jarInput.getNextJarEntry()) != null) {
				if (entry.getName().endsWith(".class")) {
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					byte[] buffer = new byte[1024];
					int bytesRead;
					while ((bytesRead = jarInput.read(buffer)) != -1) {
						bos.write(buffer, 0, bytesRead);
					}
					byte[] entryBytes = bos.toByteArray();
					ClassFile clazz_fil = new ClassFile(new DataInputStream(new ByteArrayInputStream(entryBytes)));
					// Convert the class bytes to a CtClass
					CtClass ctClass = this.getClassPool().makeClass(clazz_fil, false);
					// If to_remap is true, add the CtClass to some collection or process it

					// TODO: Add the CtClass to a collection or perform some other operation
					// For example:

					try {
						ctClass.defrost();
						getUnRemappedClassFiles().add(ctClass.getClassFile());
						if (to_remap) {
							getRemapClassFiles().add(ctClass.getClassFile());
						}

					} catch (Exception e) {
						if (this.debugMode()) {
							e.printStackTrace();
							// this is not likely to go well
						}
					}

					// Defrost the CtClass if needed
					// ctClass.defrost();
				}
			}
		} catch (IOException e) {
			if (this.debugMode()) {
				e.printStackTrace();
			}
		}
	}

	public default void addToClasspathJar(JarFile file, boolean to_remap) {
		try {
			this.getClassPool().appendClassPath(file.getName());
			Enumeration<JarEntry> entries = file.entries();
			while (entries.hasMoreElements()) {
				JarEntry entry = entries.nextElement();
				if (entry.getName().endsWith(".class")) {

					InputStream inputStream = file.getInputStream(entry);
					DataInputStream dataInputStream = new DataInputStream(inputStream);

					ClassFile archivo = new ClassFile(dataInputStream);
					CtClass ct = this.getClassPool().makeClass(archivo);
					this.getUnRemappedClassFiles().add(ct.getClassFile());
					if (to_remap) {
						this.getRemapClassFiles().add(ct.getClassFile());
					}

				}
			}
			file.close();

		} catch (NotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			if (this.debugMode()) {
				e1.printStackTrace();
			}
		}

	}

	
	
	public static String extractClassName(String fullyQualifiedName) {
		int lastDotIndex = fullyQualifiedName.lastIndexOf('.');
		if (lastDotIndex > 0 && lastDotIndex < fullyQualifiedName.length() - 1) {
			return fullyQualifiedName.substring(lastDotIndex + 1);
		}
		return fullyQualifiedName;
	}
	
	
	
	public default void addClassFromBytesAndName(byte[] bytes, String name) {
		try {
			ClassFile clazz_fil = new ClassFile(new DataInputStream(new ByteArrayInputStream(bytes)));
			CtClass ct = this.getClassPool().makeClass(clazz_fil);
			this.getUnRemappedClassFiles().add(ct.getClassFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			if (this.debugMode()) {
				e.printStackTrace();
			}
		}
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

	public default ClassFile getClassFromName(String proto_name) {
		String name = proto_name.replace("/", ".");
		for (ClassFile clazz : this.getClassFilePool()) {
			if (clazz.getName() == name) {
				return clazz;
			}
		}
		if(this.loadFromClassPool()) {
		return getClassFromPool(name).getClassFile();
		}
		
		if(this.useResourceInputStream()) {
			InputStream stream = this.loaderForResourceInputStream().getResourceAsStream(name.replace(".", "/")+".class");
			try {
				ClassFile clazz_fil = new ClassFile(new DataInputStream(stream));
				CtClass ct = this.getClassPool().makeClass(clazz_fil);
				return ct.getClassFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
			if(this.debugMode()) {
				e.printStackTrace();
			}
			}
		}
		return new ClassFile(false,//nesesito cambio para interfaces
				name, "java/lang/Object"); //necesito buscar por una método mejor
	}

	public default String getOldestMethodName(ClassFile clazz, String methodName, String desc,
			ArrayList<String> recersive_names) {
		// The idea of searching the mappings for the mapping reference 1st was copied
		// from
		// https://github.com/pocolifo/jar-remapper/blob/main/com.pocolifo.jarremapper/src/main/java/com/pocolifo/jarremapper/
		String className = clazz.getName();

		if (!recersive_names.contains(className)) {

			if (this.getMappings().getDefs().containsKey(className + "." + methodName + desc)) {
				String ret = getMappings().getDefs().get(className + "." + methodName + desc);
				return ret;
			} else {
				recersive_names.add(className);
				String protomapped = getMappings().getDefMappedName(className + "." + methodName + desc);
				if (protomapped != methodName) {
					return protomapped;
				}

				if (this.classFileHasMethod(className, methodName, desc)) { // Soon need to account for descriptorsss
					// This section is likely going to be removed in the future

					ArrayList<ClassFile> supers = new ArrayList<ClassFile>();

					String[] sups = getMappings().getIncludes().get(className);
					if (sups != null) {
						for (String interface_name : sups) {
							supers.add(getClassFromName(interface_name));
						}

						for (ClassFile super_clazz : supers) {
							String old = getOldestMethodName(super_clazz, methodName, desc, recersive_names);
							if (!old.equals(methodName)) {
								getMappings().getDefs().put(className + "." + methodName + desc, old);
								return old;
							}
						}
					}
				}

			}
		}

		return getGenericExtendsFinder().getPotentialGenericExtendingMethod(clazz, methodName, desc, recersive_names);

	}

	public default String getOldestFieldName(ClassFile clazz, String fieldName, String desc,
			ArrayList<String> recersive_names) {
		// The idea of searching the mappings for the mapping reference rather than
		// searching form the oldest from
		// https://github.com/pocolifo/jar-remapper/blob/main/com.pocolifo.jarremapper/src/main/java/com/pocolifo/jarremapper/
		String className = clazz.getName();

		if (!recersive_names.contains(className)) {
			if (getMappings().getVars().containsKey(className + "." + fieldName + ":" + desc)) {
				return getMappings().getVars().get(className + "." + fieldName + ":" + desc);
			} else {
				recersive_names.add(className);

				String protomapped = getMappings().getVarMappedName(className + "." + fieldName + ":" + desc);
				if (protomapped != fieldName) {
					return protomapped;
				}

				if (this.classFileHasField(className, fieldName, desc)) { // Soon need to account for descriptorsss
					// This section will likely be removed in the future
					ArrayList<ClassFile> supers = new ArrayList<ClassFile>();

					String[] sups = getMappings().getIncludes().get(className);
					for (String interface_name : sups) {
						supers.add(getClassFromName(interface_name));
					}

					for (ClassFile super_clazz : supers) {
						String old = getOldestFieldName(super_clazz, fieldName, desc, recersive_names);
						if (!old.equals(fieldName)) {
							getMappings().getVars().put(className + "." + fieldName + ":" + desc, old);
							return old;
						}
					}
				}
			}
		}

		return getGenericExtendsFinder().getPotentialGenericExtendingField(clazz, fieldName, desc, recersive_names);

	}

	public default boolean classFileHasField(String clazz, String field_name, String desc) {

		ClassFile archivo = this.getClassFromName(clazz);
		for (FieldInfo field : archivo.getFields()) {
			if (field.getName().equals(field_name) && field.getDescriptor().equals(desc)) {
				return true;
			}
		}

		for(String sup:getSupers(archivo)) {
			if(classFileHasField(sup,field_name,desc)) {
				return true;
			}
		}
		
		
		return false;
	}

	public default boolean classFileHasMethod(String clazz, String method_name, String desc) {

		ClassFile archivo = this.getClassFromName(clazz);
		for (MethodInfo method : archivo.getMethods()) {
			if (method.getName().equals(method_name) && method.getDescriptor().equals(desc)) {
				return true;
			}
		}

		for(String sup:getSupers(archivo)) {
			if(classFileHasMethod(sup,method_name,desc)) {
				return true;
			}
		}
		
		
		return false;
	}
	
	
	public static List<String> getSupers (ClassFile archivo){
		List<String> res = new ArrayList<String>(); 
		for(String interf:archivo.getInterfaces()) {
			res.add(interf.replace("/", "."));
		}
		if(archivo.getSuperclass()!=null && !archivo.getSuperclass().replace("/", ".").equals("java.lang.Object")) {
			res.add(archivo.getSuperclass().replace("/", "."));
		}
		
		return res;
	}
	
	
}

