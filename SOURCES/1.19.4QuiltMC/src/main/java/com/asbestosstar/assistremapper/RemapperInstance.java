package com.asbestosstar.assistremapper;

import java.io.ByteArrayOutputStream;
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
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javassist.ByteArrayClassPath;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CodeConverter;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.ClassFile;
import javassist.bytecode.FieldInfo;
import javassist.bytecode.MethodInfo;

public class RemapperInstance {

  public Mappings mappings;
  public ClassPool classpool;
  int method_collect = 0;
  public ArrayList < ClassFile > clazzes = new ArrayList < ClassFile > ();
  private ArrayList < CtClass > ct_clazzes = new ArrayList < CtClass > ();
  public CodeConverter converter = new CodeConverter();
  public String export_location;
  public int threads = 4;

  public RemapperInstance(Mappings mappings, String export_location) {
    this(mappings, ClassPool.getDefault(), export_location);
  }
  public RemapperInstance(Mappings mappings, ClassPool classpool, String export_location) {
    this.mappings = mappings;
    this.classpool = classpool;
  this.export_location=export_location;
  }

  public void addToClasspathJar(JarFile file) {
    try {
      classpool.appendClassPath(file.getName());
    } catch (NotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void remapJar(JarFile jarFile) {
    try {
      classpool.appendClassPath(jarFile.getName());
    } catch (NotFoundException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

    try {
      Enumeration < JarEntry > entries = jarFile.entries();
      while (entries.hasMoreElements()) {
        JarEntry entry = entries.nextElement();
        if (entry.getName().endsWith(".class")) {


        	
        	//   InputStream inputStream = jarFile.getInputStream(entry);
          //   DataInputStream dataInputStream = new DataInputStream(inputStream);

          CtClass ct = getClassFromPool(entry.getName().replace(".class", "").replace("/", "."));
          ct_clazzes.add(ct);
          ct.defrost();
          clazzes.add(ct.getClassFile());
        }
      }
      jarFile.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (clazzes != null) {
      try {
        //  ArrayList<CtClass> renamed_clazzes = new ArrayList<CtClass>();
        /*  for (String item : clazzes) {
			        
			        	
			        	
			        	
			        	
			        if(item.contains("$"))	{
			        	CtClass class_renaming = getClassFromPool(item);
			        
			        
			        	
			        	class_renaming.setName(parseSubClass(item));
			        	
			        	renamed_clazzes.add(class_renaming);
			        	//renamed_clazzes.add(classpool.getAndRename(item, parseSubClass(item)));			        	
			        }else {
			        	CtClass class_renaming = getClassFromPool(item);
			      
			        	class_renaming.setName(mappings.getClassMappedName(item));
			        	for (CtMethod def : class_renaming.getMethods()) { 
			        //		System.out.println(def.getName());
			        	}  
			        	renamed_clazzes.add(class_renaming);
			        	//renamed_clazzes.add(classpool.getAndRename(item, mappings.getClassMappedName(item)));			        	
			        }
			        }*/
  
    	     
    	     
        System.out.println("Getting Depths");
        int clazzes_incrument = 0;
        //for (CtClass item : renamed_clazzes) {
        for (ClassFile item: clazzes) {
          //System.out.println(item.getName());
          checkInhertitedMaps(item);
        }

        System.out.println("renaming");

        for (ClassFile item: clazzes) {

          rename_all(item);

        }
        
        

        
        
        
        
        System.out.println("Remapping Bodies");
        		        for (ClassFile item : clazzes) {
        		        	try {
								remap_class(item);
							} catch (CannotCompileException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

        		        }
        
        
        		        System.out.println("Renaming Classes");
        		        for (ClassFile item: clazzes) {
        		            ExecutorService executorService = Executors.newFixedThreadPool(threads);

        		        	 executorService.submit(() -> {
        		        		 item.renameClass(item.getName(), mappings.getClassMappedName(item.getName()));
        		        		 reNameAllClasses(item);//Maybe make this use a classCall lib? though it may have the same issues as the method and field versions
        		        	        return null;
        		        	      });
        		             executorService.shutdown();

        		        }


        System.out.println("Saving Classes");
        for (ClassFile item: clazzes) {
            ExecutorService executorService = Executors.newFixedThreadPool(threads);

       	 executorService.submit(() -> {
            	writeClassToLocation(item);
       	        return null;
       	      });
         executorService.shutdown();

        }
        
        
      } catch (NullPointerException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      System.out.println("Done if not blinking");

    } else {
      System.out.println("Jar does not appear to have classes");
    }

  }
  

  

  public byte[] getByteArrayFromClassName(String clazz) {
	  ByteArrayOutputStream barray = new ByteArrayOutputStream();
      DataOutputStream out = new DataOutputStream(barray);
      try {
         try {
			this.getClassFromName(clazz).write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
      finally {
          try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }

      return barray.toByteArray();
  }

  
  public static String extractClassName(String fullyQualifiedName) {
      int lastDotIndex = fullyQualifiedName.lastIndexOf('.');
      if (lastDotIndex > 0 && lastDotIndex < fullyQualifiedName.length() - 1) {
          return fullyQualifiedName.substring(lastDotIndex + 1);
      }
      return fullyQualifiedName;
  }
  
  
  
  public void writeClassToLocation(ClassFile fil) {
     
	  
	  
	  String name = export_location+"/"+fil.getName().replace(".", "/")+".class";
	  File clazz = new File(name);
      File directory = new File(name.replace(extractClassName(fil.getName())+".class", ""));

      directory.mkdirs();
	  
	  DataOutputStream out;
	try {
		out = new DataOutputStream(new FileOutputStream(clazz));
		fil.write(out);

	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
      
  
	  
	  
  }
  
  
  public void addClassFromBytesAndName(byte[] bytes, String name) {
	  classpool.appendClassPath(new ByteArrayClassPath(name, bytes));
try {
	classpool.get(name);
} catch (NotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
  }
  
  
  
  public Map<String,String> getClassesWithSlashes(){
	  HashMap<String,String> map = new HashMap<String,String>();
	  for (Map.Entry<String, String> entry : mappings.classes.entrySet()) {
		map.put(entry.getKey().replace(".", "/"), entry.getValue().replace(".", "/"));  
	  }
	  return map;
  }
  
  
  
  public void reNameAllClasses(ClassFile fil) {

	  //for (Map.Entry<String, String> entry : mappings.classes.entrySet()) {
//fil.renameClass(entry.getKey(),entry.getValue());
	  fil.renameClass(getClassesWithSlashes());//Not Sure if calling the method is fastest or if making a field would be faster but eh its ok for now as for field we would need a way to regen if classes were later added  to mappings, we can see later though

	  //  }
  
  }

  public void rename_all(ClassFile item) {
    // TODO Auto-generated method stub
    CtClass ct = this.getClassFromPool(item.getName());
    ArrayList < CtMethod > meths = new ArrayList < CtMethod > ();
    //Params not supported in Java bytecode I dont think?
    for (MethodInfo def: item.getMethods()) {
      putMethod(item, def, meths);
    }

    for (CtMethod meth: meths) {
      try {
        ct.addMethod(meth);
      } catch (CannotCompileException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    ArrayList < CtField > fields = new ArrayList < CtField > ();

    for (FieldInfo
      var: item.getFields()) { //I soon need to find a way to do undeclared
      putField(item,
        var, fields);
    }

    for (CtField field: fields) {
      try {
        ArrayList < String > recersive_names = new ArrayList < String > ();

        ct.addField(field, this.getOldestFieldName(item, field.getName(), field.getSignature(), recersive_names));
        recersive_names.clear();
      } catch (CannotCompileException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

  }

  public void putMethod(ClassFile item, MethodInfo def, ArrayList < CtMethod > meths) {

    ArrayList < String > recersive_names = new ArrayList < String > ();

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

    
if(nombre!=null) {    
    def.setName(nombre);
}
    
    //	try {
    //		converter.redirectMethodCall(old_nombre, this.getMethodFromClass(nombre, def.getDescriptor(), getClassFromPool(item.getName())));
    //	} catch (CannotCompileException e) {
    // TODO Auto-generated catch block
    //		e.printStackTrace();
    //	}

    // Likely remove after getting method body remapping working

   /* if (!checkIfMethodExists(item, old_nombre, def.getDescriptor()) && !old_nombre.contains("<")) {
      CtClass ct = this.getClassFromPool(item.getName());
      CtMethod meth = new CtMethod(ct, old_nombre, null, ct);
      meth.getMethodInfo().setDescriptor(def.getDescriptor());
      try {
        meth.setModifiers(ct.getMethod(nombre, def.getDescriptor()).getModifiers());
      meth.setModifiers(AccessFlag.PUBLIC);
      } catch (NotFoundException e1) {
        // TODO Auto-generated catch block
        this.getClassFromPool(e1.getMessage());
        putMethod(item, def, meths);

        //e1.printStackTrace();
      }
      try {
        if (meth.getSignature().endsWith("V")) {

          if (meth.getParameterTypes().length > 0) {
            meth.setBody(nombre + "($$);");
          } else {
            meth.setBody(nombre + "();");
          }

        } else {
          if (meth.getParameterTypes().length > 0) {

            meth.setBody("return " + nombre + "($$);");
          } else {
          }

        }
        meths.add(meth);
      } catch (NotFoundException e) {
        // TODO Auto-generated catch block
        this.getClassFromPool(e.getMessage());

        putMethod(item, def, meths);
        //e.printStackTrace();

      } catch (CannotCompileException e) {
        // TODO Auto-generated catch block
          System.out.println(e.getMessage());
    	  //this.getClassFromPool(e.getMessage());

        e.printStackTrace();
      }

    }*/

  }

  public boolean checkIfMethodExists(ClassFile item, String name, String desc) {
    for (MethodInfo def: item.getMethods()) {
      if (def.getName().equals(name) && def.getDescriptor().equals(desc)) {
        return true;
      }
    }
    return false;
  }

  public void putField(ClassFile item, FieldInfo
    var, ArrayList < CtField > fields) {

    ArrayList < String > recersive_names = new ArrayList < String > ();

    String old_nombre =
      var.getName();
    String nombre = mappings.getVarMappedName(item.getName().replace("/", ".") + "." +
      var.getName() + ":" +
      var.getDescriptor());

    if (nombre.equals(old_nombre)) {
      nombre = getOldestFieldName(item, old_nombre,
        var.getDescriptor(), recersive_names);
      recersive_names.clear();
    }
//var.setAccessFlags(AccessFlag.setPublic(var.getAccessFlags()));//Should have known about setPublic instead of simpley doing .PUBLIC
    //converter.redirectFieldAccess(this.getFieldFromClass(var.getName(), var.getDescriptor(), ct), ct, nombre);

    var.setName(nombre);

    //System.out.println(var.getName());

    //	try {
    //		converter.redirectMethodCall(old_nombre, this.getMethodFromClass(nombre, def.getDescriptor(), getClassFromPool(item.getName())));
    //	} catch (CannotCompileException e) {
    // TODO Auto-generated catch block
    //		e.printStackTrace();
    //	}

    // Likely remove after getting method body remapping working

  /*  if (!checkIfFieldExists(item, old_nombre,
        var.getDescriptor()) && !old_nombre.contains("<")) {
      CtClass ct = this.getClassFromPool(item.getName());
      CtField field = null;
      try {
        field = new CtField(ct, old_nombre, ct);
      } catch (CannotCompileException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      field.getFieldInfo().setDescriptor(var.getDescriptor());
      try {
        field.setModifiers(ct.getField(nombre,
          var.getDescriptor()).getModifiers());
        field.setModifiers(AccessFlag.PUBLIC);

      } catch (NotFoundException e) {
        // TODO Auto-generated catch block
        this.getClassFromPool(e.getMessage());
        putField(item,
          var, fields);
        //e.printStackTrace();
      }

      fields.add(field);

    }*/

  }

  public boolean checkIfFieldExists(ClassFile item, String name, String desc) {
    for (FieldInfo
      var: item.getFields()) {
      if (var.getName().equals(name) &&
        var.getDescriptor().equals(desc)) {
        return true;
      }
    }
    return false;
  }

  public void remap_class(ClassFile clazz) throws CannotCompileException {

    //Params not supported in Java bytecode I dont think?
    //CodeConverter converter = new CodeConverter();

    // Get the target class

    ExecutorService executorService = Executors.newFixedThreadPool(threads);

    //    executorService.submit(() -> {

    //  executorService.submit(() -> {
    try {
      CodeConverter conv = new CodeConverter();
      CtClass ct = this.getClassFromPool(clazz.getName());
      ct.instrument(new ExprScan(this, conv));
     
      //if (ct.getSimpleName().contains("class_5")) {
      //ct.instrument(converter);
      //}
      ct.instrument(conv);
    
      
    } catch (CannotCompileException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    //   });

    //    });

    executorService.shutdown();

    // TODO Auto-generated method stub

  }



  public CtClass getClassFromPool(String name) {
    try {//I also need a delete classes array
      return classpool.get(name);
    } catch (NotFoundException e) {
      // TODO Auto-generated catch block
      //e.printStackTrace();
      return classpool.makeClass(name);
    }
  }

  public CtMethod getMethodFromClass(String name, String desc, CtClass clazz) {
    try {
      return clazz.getMethod(name, desc);
    } catch (NotFoundException e) {
      // TODO Auto-generated catch block
      //	e.printStackTrace();
      CtMethod ret = new CtMethod(clazz, name, null, clazz);
      ret.getMethodInfo().setDescriptor(desc);
      return ret;
    }
  }

  public CtField getFieldFromClass(String name, String desc, CtClass clazz) {
    try {
      return clazz.getField(name, desc);
    } catch (NotFoundException e) {
      // TODO Auto-generated catch block
      //	e.printStackTrace();
      CtField ret = null;
      try {
        ret = new CtField(clazz, name, clazz);
      } catch (CannotCompileException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      ret.setGenericSignature(desc);
      return ret;
    }
  }

  public void checkInhertitedMaps(ClassFile clazz) {

    ExecutorService executorService = Executors.newFixedThreadPool(4);

    for (CtMethod def: this.getClassFromPool(clazz.getName()).getMethods()) {
      executorService.submit(() -> {
        ArrayList < String > recersive_names = new ArrayList < String > ();
        getOldestMethodName(clazz, def.getName(), def.getSignature(), recersive_names);
        recersive_names.clear();
        return null;
      });
    }

    for (CtField
      var: this.getClassFromPool(clazz.getName()).getFields()) {
      executorService.submit(() -> {
        ArrayList < String > recersive_names = new ArrayList < String > ();
        getOldestFieldName(clazz,
          var.getName(),
          var.getSignature(), recersive_names);
        recersive_names.clear();
      });
    }

    executorService.shutdown();

    //	for (CtMethod def: this.getClassFromPool(clazz.getName()).getMethods()) {

    /*try {
    CtMethod old = this.findOldestMethod(clazz.getName(), def.getName(), def.getSignature());
    if (old != null) {	
    String decl=	old.getDeclaringClass().getName();
    if(!mappings.defs.containsKey(clazz.getName()+"."+def.getName()+def.getSignature())) {	
    	mappings.defs.put(clazz.getName()+"."+def.getName()+def.getSignature(), mappings.getDefMappedName(decl+"."+def.getName()+def.getSignature()));
    }
    }
    
    } catch (NotFoundException e) {
    	// TODO Auto-generated catch block
    	e.printStackTrace();
    }*/
    //				getOldestMethodName(clazz, def.getName(), def.getSignature());
    //this.recersive_names.clear();		

    //		}

    //		for (CtField var: this.getClassFromPool(clazz.getName()).getFields()) {
    /*	try {
		CtField old = this.findOldestField(clazz.getName(), var.getName(), var.getSignature());
		if (old != null) {	
		String decl=	old.getDeclaringClass().getName();
		if(!mappings.vars.containsKey(clazz.getName()+"."+var.getName()+var.getSignature())) {	
			mappings.vars.put(clazz.getName()+"."+var.getName()+var.getSignature(), mappings.getDefMappedName(decl+"."+var.getName()+var.getSignature()));
		}
		}
		
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

    //	System.out.println(clazz.getName()+"."+var.getName()+var.getSignature());
    //			getOldestFieldName(clazz, var.getName(), var.getSignature());
    //			this.recersive_names.clear();		

    //		}

    /*
		ArrayList<MethodInfo> methods = new ArrayList<MethodInfo>();
		ArrayList<FieldInfo> fields = new ArrayList<FieldInfo>();
System.out.println("Getting all undecleared Classes For Methods and Fields");
		for (ClassFile fil: getClassHierarchy(clazz)) {
		methods.addAll(fil.getMethods());
		fields.addAll(fil.getFields());
		}
		methods.addAll(clazz.getMethods());
		fields.addAll(clazz.getFields());
		
		
		System.out.println("Getting Undeclared Methods");
		
		for (MethodInfo methodInfo : methods) {
        
			if (!methodInfo.getName().contains("<") && !mappings.defs.containsKey(clazz.getName()+"."+methodInfo.getName()+methodInfo.getDescriptor()))
			try {
		
		
				if (!mappings.defs.containsKey(clazz.getName()+"."+methodInfo.getName()+methodInfo.getDescriptor())) {
				mappings.defs.put(clazz.getName()+"."+methodInfo.getName()+methodInfo.getDescriptor(), mappings.getDefMappedName(findOriginalDeclarationMethod(clazz, methodInfo.getName(), methodInfo.getDescriptor()).getName()+"."+methodInfo.getName()+methodInfo.getDescriptor()));
				}
				
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
		
		}
System.out.println("Getting Undeclared Fields");		
		
		for (FieldInfo fieldInfo : fields) {
	        
			if (!mappings.vars.containsKey(clazz.getName()+"."+fieldInfo.getName()+fieldInfo.getDescriptor()))
			try {
				if (mappings.vars.containsKey(clazz.getName()+"."+fieldInfo.getName()+fieldInfo.getDescriptor()))
				mappings.vars.put(clazz.getName()+"."+fieldInfo.getName()+fieldInfo.getDescriptor(), mappings.getVarMappedName(findOriginalDeclarationField(clazz, fieldInfo.getName(), fieldInfo.getDescriptor()).getName()+"."+fieldInfo.getName()+fieldInfo.getDescriptor()));
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
		
		}
		*/

  }

  public static boolean hasField(ClassFile cf, String fieldName, String descriptor) {
    for (FieldInfo fieldInfo: cf.getFields()) {
      if (Objects.equals(fieldInfo.getName(), fieldName) && Objects.equals(fieldInfo.getDescriptor(), descriptor)) {
        return true;
      }
    }
    return false;
  }

  public ClassFile getClassFromName(String name) {

    for (ClassFile clazz: clazzes) {
      if (clazz.getName() == name) {
        return clazz;
      }
    }
    return getClassFromPool(name).getClassFile();

  }

  public String getOldestMethodName(ClassFile clazz, String methodName, String desc, ArrayList < String > recersive_names) {
    //The idea of searching the mappings for the mapping reference 1st was copied from https://github.com/pocolifo/jar-remapper/blob/main/com.pocolifo.jarremapper/src/main/java/com/pocolifo/jarremapper/
    String className = clazz.getName();

    if (!recersive_names.contains(className)) {

      if (mappings.defs.containsKey(className + "." + methodName + desc)) {
        return mappings.defs.get(className + "." + methodName + desc);
      } else {

        recersive_names.add(className);
        if (this.classFileHasMethod(className, methodName, desc)) { // Soon need to account for descriptorsss

          ArrayList < ClassFile > supers = new ArrayList < ClassFile > ();
          if (clazz.getSuperclass() != null) {
            if (!clazz.getSuperclass().equals("java.lang.Object")) {
              supers.add(getClassFromName(clazz.getSuperclass()));
            }
          }
          for (String interface_name: clazz.getInterfaces()) {
            supers.add(getClassFromName(interface_name));
          }

          for (ClassFile super_clazz: supers) {
            String old = getOldestMethodName(super_clazz, methodName, desc, recersive_names);
            if (!old.equals(methodName)) {
              mappings.defs.put(className + "." + methodName + desc, old);
              return old;
            }
          }
        }
      }
    }

    return methodName;

  }

  public String getOldestFieldName(ClassFile clazz, String fieldName, String desc, ArrayList < String > recersive_names) {
    //The idea of searching the mappings for the mapping reference rather than searching form the oldest from  https://github.com/pocolifo/jar-remapper/blob/main/com.pocolifo.jarremapper/src/main/java/com/pocolifo/jarremapper/
    String className = clazz.getName();

    if (!recersive_names.contains(className)) {
      if (mappings.vars.containsKey(className + "." + fieldName + ":" + desc)) {
        return mappings.vars.get(className + "." + fieldName + ":" + desc);
      } else {
        recersive_names.add(className);
        if (this.classFileHasField(className, fieldName, desc)) { // Soon need to account for descriptorsss

          ArrayList < ClassFile > supers = new ArrayList < ClassFile > ();
          if (clazz.getSuperclass() != null) {
            if (!clazz.getSuperclass().equals("java.lang.Object")) {
              supers.add(getClassFromName(clazz.getSuperclass()));
            }
          }
          for (String interface_name: clazz.getInterfaces()) {
            supers.add(getClassFromName(interface_name));
          }

          for (ClassFile super_clazz: supers) {
            String old = getOldestFieldName(super_clazz, fieldName, desc, recersive_names);
            if (!old.equals(fieldName)) {
              mappings.vars.put(className + "." + fieldName + ":" + desc, old);
              return old;
            }
          }
        }
      }
    }

    return fieldName;

  }

  public String getOldestMethodName(CtClass clazz, String methodName, String desc, ArrayList < String > recersive_names) {
    //The idea of searching the mappings for the mapping reference 1st was copied from https://github.com/pocolifo/jar-remapper/blob/main/com.pocolifo.jarremapper/src/main/java/com/pocolifo/jarremapper/
    String className = clazz.getName();

    if (!recersive_names.contains(className)) {

      if (mappings.defs.containsKey(className + "." + methodName + desc)) {
        return mappings.defs.get(className + "." + methodName + desc);
      } else {

        recersive_names.add(className);
        if (this.classFileHasMethod(className, methodName, desc)) { // Soon need to account for descriptorsss

          ArrayList < ClassFile > supers = new ArrayList < ClassFile > ();
          try {
            if (clazz.getSuperclass() != null) {

              if (!clazz.getSuperclass().equals("java.lang.Object")) {
                supers.add(getClassFromName(clazz.getSuperclass().getName()));
              }
              for (CtClass interface_name: clazz.getInterfaces()) {
                supers.add(getClassFromName(interface_name.getName()));
              }

            }
          } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }

          for (ClassFile super_clazz: supers) {
            String old = getOldestMethodName(super_clazz, methodName, desc, recersive_names);
            if (!old.equals(methodName)) {
              mappings.defs.put(className + "." + methodName + desc, old);
              return old;
            }
          }
        }
      }
    }

    return methodName;

  }

  public String getOldestFieldName(CtClass clazz, String fieldName, String desc, ArrayList < String > recersive_names) {
    //The idea of searching the mappings for the mapping reference rather than searching form the oldest from  https://github.com/pocolifo/jar-remapper/blob/main/com.pocolifo.jarremapper/src/main/java/com/pocolifo/jarremapper/
    String className = clazz.getName();
    if (!recersive_names.contains(className)) {
      if (mappings.vars.containsKey(className + "." + fieldName + ":" + desc)) {
        return mappings.vars.get(className + "." + fieldName + ":" + desc);
      } else {
        recersive_names.add(className);
        if (this.classFileHasField(className, fieldName, desc)) { // Soon need to account for descriptorsss

          ArrayList < ClassFile > supers = new ArrayList < ClassFile > ();
          try {
            if (clazz.getSuperclass() != null) {

              if (!clazz.getSuperclass().equals("java.lang.Object")) {
                supers.add(getClassFromName(clazz.getSuperclass().getName()));
              }
              for (CtClass interface_name: clazz.getInterfaces()) {
                supers.add(getClassFromName(interface_name.getName()));
              }
            }
          } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }

          for (ClassFile super_clazz: supers) {
            String old = getOldestFieldName(super_clazz, fieldName, desc, recersive_names);
            if (!old.equals(fieldName)) {
              mappings.vars.put(className + "." + fieldName + ":" + desc, old);
              return old;
            }
          }
        }
      }
    }

    return fieldName;

  }

  public boolean classFileHasField(String clazz, String field_name, String desc) {

    for (CtField field: this.getClassFromPool(clazz).getFields()) {
      if (field.getName().equals(field_name) && field.getSignature().equals(desc)) {
        return true;
      }

    }

    return false;
  }

  public boolean classFileHasMethod(String clazz, String method_name, String desc) {

    for (CtMethod method: this.getClassFromPool(clazz).getMethods()) {
      if (method.getName().equals(method_name) && method.getSignature().equals(desc)) {
        return true;
      }

    }

    return false;
  }

  public CtMethod findOldestMethod(String className, String methodName, String desc) throws NotFoundException {

    CtClass cc = getClassFromPool(className);

    CtMethod method = findMethodInClassHierarchy(cc, methodName, desc);
    if (method != null) {
      return method;
    }

    //   throw new NotFoundException("Method " + methodName + " not found in the hierarchy of class " + className);
    return null;
  }

  public CtMethod findMethodInClassHierarchy(CtClass cc, String methodName, String desc) throws NotFoundException {
    while (cc != null) {
      CtMethod[] methods = cc.getDeclaredMethods();
      for (CtMethod method: methods) {
        if (method.getName().equals(methodName) && method.getSignature().equals(desc)) {
          return method;
        }
      }

      // Look in interfaces
      for (CtClass anInterface: cc.getInterfaces()) {
        CtMethod method = findMethodInInterfaceHierarchy(anInterface, methodName, desc);
        if (method != null) {
          return method;
        }
      }

      cc = cc.getSuperclass();
    }
    return null;
  }

  public CtMethod findMethodInInterfaceHierarchy(CtClass cc, String methodName, String desc) throws NotFoundException {
    if (cc != null) {
      CtMethod[] methods = cc.getDeclaredMethods();
      for (CtMethod method: methods) {
        if (method.getName().equals(methodName) && method.getSignature().equals(desc)) {
          return method;
        }
      }

      // look in parent interfaces
      for (CtClass parentInterface: cc.getInterfaces()) {
        CtMethod method = findMethodInInterfaceHierarchy(parentInterface, methodName, desc);
        if (method != null) {
          return method;
        }
      }
    }
    return null;
  }

  

  
  
}
