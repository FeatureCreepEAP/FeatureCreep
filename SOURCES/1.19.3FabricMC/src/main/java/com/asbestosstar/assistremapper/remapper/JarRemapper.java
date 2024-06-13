package com.asbestosstar.assistremapper.remapper;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;

import com.asbestosstar.assistremapper.Mappings;
import com.asbestosstar.assistremapper.RemapperInstance;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CodeConverter;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.bytecode.ClassFile;

public class JarRemapper implements RemapperInstance {

	public Mappings mappings;
	public ClassPool classpool;
	public int method_collect = 0;
	public ArrayList<ClassFile> clazzes = new ArrayList<ClassFile>();
	public ArrayList<CtClass> ct_clazzes = new ArrayList<CtClass>();
	public CodeConverter converter = new CodeConverter();
	public String export_location;
	public int threads = 4;
	public boolean rename_source_files = true;
	public boolean debug_mode = true;
	public boolean clean_classpools = true;
	public boolean disallowDupes = true;
	public ArrayList<ClassRemapper> class_remappers = new ArrayList<ClassRemapper>();

	public JarRemapper(Mappings mappings, String export_location) {
		this(mappings, ClassPool.getDefault(), export_location);
	}

	public JarRemapper(Mappings mappings, ClassPool classpool, String export_location) {
		this.mappings = mappings;
		this.classpool = classpool;
		this.export_location = export_location;
	}
	
	
	
	public void addToClasspathJarFromStream(InputStream jarInputStream, boolean to_remap) {  
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
	                CtClass ctClass = classpool.makeClass(clazz_fil, false);
	                  
	                // If to_remap is true, add the CtClass to some collection or process it  
	                if (to_remap) {  
	                    // TODO: Add the CtClass to a collection or perform some other operation  
	                    // For example:  
	                     ct_clazzes.add(ctClass);  
	                
	                     try {
	                     ctClass.defrost();
	                     clazzes.add(ctClass.getClassFile());
	                     }catch(Exception e) {
	                    	 if(debug_mode) {
	                    	 e.printStackTrace();
	                    	 //this is not likely to go well
	                    	 }
	                     }
	                }  
	                  
	                // Defrost the CtClass if needed  
	                // ctClass.defrost();  
	            }  
	        }  
	    } catch (IOException e) {  
	        if (debug_mode) {  
	            e.printStackTrace();  
	        }  
	    }  
	}
	

	public void addToClasspathJar(JarFile file, boolean to_remap) {
		try {
			classpool.appendClassPath(file.getName());
		} catch (NotFoundException e1) {
			// TODO Auto-generated catch block
			if (debug_mode) {
				e1.printStackTrace();
			}
		}

		if (to_remap) {
			try {
				Enumeration<JarEntry> entries = file.entries();
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
				file.close();
			} catch (IOException e) {
				if (debug_mode) {
					e.printStackTrace();
				}
			}
		}

	}

	public void remapJar(JarFile jarFile) {

		addToClasspathJar(jarFile, true);

		if (clazzes != null) {

			try {

				System.out.println("Getting Depths");
				for (ClassFile item : clazzes) {
					ClassRemapper clazz_remapper = new ClassRemapper(mappings, classpool, item, export_location,
							threads);
					clazz_remapper.clazzes = clazzes;
					clazz_remapper.debug_mode = true;
					clazz_remapper.disallowDupes = true;
					clazz_remapper.threads = threads;
					clazz_remapper.updateIncludes(false);
					clazz_remapper.checkInhertitedMaps();

					class_remappers.add(clazz_remapper);
				}


				System.out.println("Remapping Bodies");
				for (ClassRemapper item : class_remappers) {

					try {
						item.remapMethodBodies();
					} catch (CannotCompileException e) {
						// TODO Auto-generated catch block
						if (debug_mode) {
							e.printStackTrace();

						}
					}
				}
				
				System.out.println("renaming");

				for (ClassRemapper item : class_remappers) {

					item.rename_all();

				}

				System.out.println("Renaming Classes");
				for (ClassRemapper item : class_remappers) {

					item.reNameAllClasses();// Maybe make this use a classCall lib? though it may have the same
					// issues as the method and field versions

					if (rename_source_files) {
						item.renameSourceFile();
					}

					if (clean_classpools) {
						item.cleanClassPool();
					}

//						return null;
					// });
					// executorService.shutdown();
				}

				System.out.println("Saving Classes");
				for (ClassRemapper item : class_remappers) {
					ExecutorService executorService = Executors.newFixedThreadPool(threads);

//					executorService.submit(() -> {

					item.writeClassToLocation();
					// return null;
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

	@Override
	public ClassPool getClassPool() {
		// TODO Auto-generated method stub
		return classpool;
	}

}
