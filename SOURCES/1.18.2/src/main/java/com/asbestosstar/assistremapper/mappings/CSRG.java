package com.asbestosstar.assistremapper.mappings;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import com.asbestosstar.assistremapper.Mappings;

import javassist.ClassPool;
import javassist.NotFoundException;
import javassist.bytecode.ClassFile;

public class CSRG implements Mappings{

	
	public Map<String, String> classes = new HashMap<String, String>();
	public Map<String, String> jvm_classes = new HashMap<String, String>();
	public Map<String, String> defs = new HashMap<String, String>();
	public Map<String, String> vars = new HashMap<String, String>();
	public Map<String, String> params = new HashMap<String, String>();
	public Map<String, String[]> includes = new HashMap<String, String[]>();

	public Mappings reverse;

	
	/*
	 * Keep in mind that this returns a null reverse.
	 */
	public CSRG() {

	}
	
	public ClassPool pool = new ClassPool();
	
	
	public CSRG(Mappings classes_csrg, InputStream members_map_file, InputStream jar) {
		System.out.println("Parsing Mappings");
		BufferedReader reader = new BufferedReader(new InputStreamReader(members_map_file));
		addToClasspathJarFromStream(jar);
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				String[] args = line.trim().split(" ");
				if (!line.startsWith("#") && !line.isEmpty()) {
					
					
					String clazz = classes_csrg.getClassUnMappedName(args[0].replace("/", "."));
					Mappings rev = classes_csrg.getReverse();
					if(line.contains("(")) {//Method
					if(args.length>3) { //Apparently there are some with fewer
					String name = args[1];
					String old_desc = args[2];
					String out = args[3];
						String desc = rev.renameClassesInMethodDescriptor(old_desc);
						defs.put(clazz+"."+name+desc, out);
					}
					}else { //field
						String name = args[1];
						//String old_desc = args[2];
						try {
							String desc = pool.get(clazz).getField(name).getSignature(); //Need to make this use bytecode but i guess its fine, for now

							String out = args[2];
							//String desc = rev.renameClassesInFieldDescriptor(old_desc);
							vars.put(clazz+"."+name+":"+desc, out);
						} catch (NotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				
					
					
					
				}

			}

			// Close the BufferedReader
			reader.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		classes.putAll(classes_csrg.getClasses());
		jvm_classes.putAll(classes_csrg.getJVMClasses());
		
		parseSubClasses();
		System.out.println("Getting reverse mappings");
		this.refreshReverse();


	}
	
	
	
	
	
	public void addToClasspathJarFromStream(InputStream jarInputStream) {
	    try (JarInputStream jarInput = new JarInputStream(new BufferedInputStream(jarInputStream))) {
	        JarEntry entry;
	        while ((entry = jarInput.getNextJarEntry()) != null) {
	            if (entry.getName().endsWith(".class")) {
	                // Read the class file from the JAR input stream
	                ByteArrayOutputStream bos = new ByteArrayOutputStream();
	                byte[] buffer = new byte[1024];
	                int bytesRead;
	                while ((bytesRead = jarInput.read(buffer)) != -1) {
	                    bos.write(buffer, 0, bytesRead);
	                }
	                byte[] entryBytes = bos.toByteArray();

	                try {
	                    ClassFile clazz_fil = new ClassFile(new DataInputStream(new ByteArrayInputStream(entryBytes)));
	                    // Convert the class bytes to a CtClass
	                    pool.makeClass(clazz_fil, false);

	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	
	
	@Override
	public Map<String, String> getClasses() {
		// TODO Auto-generated method stub
		return classes;
	}

	@Override
	public Map<String, String> getDefs() {
		// TODO Auto-generated method stub
		return defs;
	}

	@Override
	public Map<String, String> getVars() {
		// TODO Auto-generated method stub
		return vars;
	}

	@Override
	public Map<String, String> getParams() {
		// TODO Auto-generated method stub
		return params;
	}

	@Override
	public Mappings getReverse() {
		// TODO Auto-generated method stub
		return reverse;
	}

	@Override
	public Map<String, String[]> getIncludes() {
		// TODO Auto-generated method stub
		return includes;
	}

	/*
	 * UNSUPPORTED AT THIS TIME
	 */
	@Override
	public void write(OutputStream stream) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void setReverse(Mappings rev) {
		// TODO Auto-generated method stub
		this.reverse=rev;
	}

	@Override
	public Mappings getPlainMappings() {
		// TODO Auto-generated method stub
		return new CSRG();
	}
	
	@Override
	public Map<String, String> getJVMClasses() {
		// TODO Auto-generated method stub
		return jvm_classes;
	}
	
	
}
