package com.asbestosstar.assistremapper.remapper;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.jar.JarFile;

import com.asbestosstar.assistremapper.GenericExtendsFinder;
import com.asbestosstar.assistremapper.Mappings;
import com.asbestosstar.assistremapper.RemapperInstance;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CodeConverter;
import javassist.bytecode.ClassFile;

public class JarRemapper implements RemapperInstance {

	public Mappings mappings;
	public ClassPool classpool;
	public int method_collect = 0;
	public ArrayList<ClassFile> clazzes = new ArrayList<ClassFile>();
	public ArrayList<ClassFile> not_remapped_classes = new ArrayList<ClassFile>();
	public CodeConverter converter = new CodeConverter();
	public String export_location;
	public int threads = 4;
	public boolean rename_source_files = true;
	public boolean debug_mode = true;
	public boolean clean_classpools = true;
	public boolean disallowDupes = true;
	public ArrayList<ClassRemapper> class_remappers = new ArrayList<ClassRemapper>();
	public boolean useResourceInputStream = false;
	public ClassLoader resourceInputStreamLoader = RemapperInstance.class.getClassLoader();
	public boolean load_class_from_pool = true;
	public GenericExtendsFinder gef = new GenericExtendsFinder(this);

	public JarRemapper(Mappings mappings, String export_location) {
		this(mappings, ClassPool.getDefault(), export_location);
	}

	public JarRemapper(Mappings mappings, ClassPool classpool, String export_location) {
		this.mappings = mappings;
		this.classpool = classpool;
		this.export_location = export_location;
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

