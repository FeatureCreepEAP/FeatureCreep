package com.asbestosstar.assistremapper.mappings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.asbestosstar.assistremapper.Mappings;

public class Proguard implements Mappings{

	
	public Map<String, String> classes = new HashMap<String, String>();
	public Map<String, String> jvm_classes = new HashMap<String, String>();
	public Map<String, String> defs = new HashMap<String, String>();
	public Map<String, String> vars = new HashMap<String, String>();
	public Map<String, String> params = new HashMap<String, String>();
	public Map<String, String[]> includes = new HashMap<String, String[]>();

	public Mappings reverse;
	
	
	private static final String NAME = "[a-zA-Z0-9_\\-.$<>]+";
	
	private static final String TYPE = NAME + "(?:\\[])*";
	
	private static final String TYPE_LIST = "|(?:(?:" + TYPE + ",)*" + TYPE + ")";
	
	private static final Pattern CLASS = Pattern.compile("(" + NAME + ") -> (" + NAME + "):");
	
	private static final Pattern FIELD = Pattern.compile(" {4}(" + TYPE + ") (" + NAME + ") -> (" + NAME + ")");
	
	private static final Pattern METHOD = Pattern.compile(" {4}(?:[0-9]+:[0-9]+:)?(" + TYPE + ") (" + NAME + ")\\((" + TYPE_LIST + ")\\) -> (" + NAME + ")");
	
	
	
	/*
	 * Keep in mind that this returns a null reverse.
	 */
	public Proguard() {

	}
	
	public Proguard(InputStream map_file) {
		System.out.println("Parsing Mappings");
		BufferedReader reader = new BufferedReader(new InputStreamReader(map_file));
		String line;
		String currentClass = null;
		try {
			while ((line = reader.readLine()) != null) {				
				if (line.startsWith("#") || line.isEmpty()) {
					
					continue;
		
				}
		
	 
		
				Matcher classMatcher = CLASS.matcher(line);
		
				Matcher fieldMatcher = FIELD.matcher(line);
		
				Matcher methodMatcher = METHOD.matcher(line);
		
	 
		
				if (classMatcher.matches()) {
		
					String name = classMatcher.group(1);
		
					String targetName = classMatcher.group(2);
		
	 
		
				currentClass = name;
		
				addClass(currentClass, targetName);
		
				} else if (fieldMatcher.matches()) {
		
					String type = fieldMatcher.group(1);
		
					String name = fieldMatcher.group(2);
		
					String targetName = fieldMatcher.group(3);

		
					vars.put(currentClass+"."+name+":"+ this.getDescriptor(type), targetName);
		
				} else if (methodMatcher.matches()) {
		
					String returnType = methodMatcher.group(1);
		
					String name = methodMatcher.group(2);
		
					String[] parameterTypes = methodMatcher.group(3).isEmpty() ? new String[0] : methodMatcher.group(3).split(",");
		
					String targetName = methodMatcher.group(4);
	 
		
					defs.put(currentClass+"."+name+this.getDescriptor(returnType, parameterTypes), targetName);
		
				}
				
				
			}
				
				
				
				
				
				
				
				
			
			
			
			// Close the BufferedReader
			reader.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		parseSubClasses();
		System.out.println("Getting reverse mappings");
		this.refreshReverse();

	}
	

	public String getDescriptor(String type) {
		
		StringBuilder descriptor = new StringBuilder();
	
 
	
		while (type.endsWith("[]")) {
	
			descriptor.append("[");
	
			type = type.substring(0, type.length() - 2);
	
		}
	
 
	
		if ("byte".equals(type)) {  

		    return descriptor.append("B").toString();  

		} else if ("char".equals(type)) {  

		    return descriptor.append("C").toString();  

		} else if ("short".equals(type)) {  

		    return descriptor.append("S").toString();  

		} else if ("int".equals(type)) {  

		    return descriptor.append("I").toString();  

		} else if ("long".equals(type)) {  

		    return descriptor.append("J").toString();  

		} else if ("float".equals(type)) {  

		    return descriptor.append("F").toString();  

		} else if ("double".equals(type)) {  

		    return descriptor.append("D").toString();  

		} else if ("boolean".equals(type)) {  

		    return descriptor.append("Z").toString();  

		} else if ("void".equals(type)) {  

		    return descriptor.append("V").toString();  

		} 
	
		
	
 
	
		descriptor.append("L");
	
		descriptor.append(type.replace('.', '/'));
	
		descriptor.append(";");
	
 
	
		return descriptor.toString();
	
	}
	
 
	
	public String getDescriptor(String returnType, String[] parameterTypes) {
	
		StringBuilder descriptor = new StringBuilder();
	
		descriptor.append('(');
	
 
	
		for (String parameterType : parameterTypes) {
	
			descriptor.append(this.getDescriptor(parameterType));
	
		}
	
 
	
		descriptor.append(')');
	
		descriptor.append(this.getDescriptor(returnType));
	
 
	
		return descriptor.toString();
	
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
		return new Proguard();
	}
	
	@Override
	public Map<String, String> getJVMClasses() {
		// TODO Auto-generated method stub
		return jvm_classes;
	}
	
}
