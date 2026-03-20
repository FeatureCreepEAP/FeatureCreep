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

public class TSRG2 implements Mappings {

	public Map<String, String> classes = new HashMap<String, String>();
	public Map<String, String> jvm_classes = new HashMap<String, String>();
	public Map<String, String> defs = new HashMap<String, String>();
	public Map<String, String> vars = new HashMap<String, String>();
	public Map<String, String> params = new HashMap<String, String>();
	public Map<String, String[]> includes = new HashMap<String, String[]>();

	public Mappings reverse;

	public ClassPool pool = new ClassPool();

	/*
	 * Keep in mind that this returns a null reverse.
	 */
	public TSRG2() {

	}

	public TSRG2(InputStream map_file, InputStream jar) {
		System.out.println("Parsing Mappings");
		addToClasspathJarFromStream(jar);
		BufferedReader reader = new BufferedReader(new InputStreamReader(map_file));
		String line;
		String obfOwner = null;
		String current_def = null;
		try {
			while ((line = reader.readLine()) != null) {

				
				  String[] args = line.trim().split(" ");
	
		                // Fields and Methods start with a tab
		                // class/Name new/Name
		                //      fieldName newFieldName
		                //      methodName methodDesc newMethodName
		                if (!line.startsWith("\t")) {
		                    obfOwner = args[0].replace("/", ".");;
		                    String renamedClass = args[1].replace("/", ".");
		                    addClass(obfOwner, renamedClass);
		                } else {
		                    if (args.length == 3) { // Field
		                        String obfName = args[0];
		                        String renamedName = args[1];
		    					try {
		    						String desc = pool.get(obfOwner).getField(obfName).getSignature(); //Need to make this use bytecode but i guess its fine, for now
		    						vars.put(obfOwner + "." + obfName + ":" + desc, renamedName);
		    					} catch (NotFoundException e) {
		    						// TODO Auto-generated catch block
		    						e.printStackTrace();
		    					}
		                    } else if (args.length == 4) { //Others exist like one that says static but are not important at our stage of development 
		                    
		                    	if(line.contains("(")) {// Method
		                    	String obfName = args[0];
		                        String obfDesc = args[1];
		                        String renamedName = args[2];
		                        String full = obfOwner + "." + obfName + obfDesc;
		                        defs.put(full, renamedName);
		                        current_def = full;
		                    	}else if(current_def!=null) { //Param
		                    		String spot = args[0];
		                    		String answer = args[2];
		                    		params.put(current_def+"@"+spot, answer);
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
		System.out.println("Many classes will not be found on Miencraft's Obfuscated jars sadly, it will need to be fixed at a later time");
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
		return new TSRG2();
	}

	@Override
	public Map<String, String> getJVMClasses() {
		// TODO Auto-generated method stub
		return jvm_classes;
	}
	
	
	
}
