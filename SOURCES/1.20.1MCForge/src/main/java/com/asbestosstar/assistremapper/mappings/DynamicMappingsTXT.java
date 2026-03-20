package com.asbestosstar.assistremapper.mappings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.asbestosstar.assistremapper.Mappings;

public class DynamicMappingsTXT implements Mappings{

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
	public DynamicMappingsTXT() {

	}

	public DynamicMappingsTXT(InputStream map_file) {
				
		System.out.println("Parsing Mappings");
		BufferedReader reader = new BufferedReader(new InputStreamReader(map_file));
		String line;
		try {
			while ((line = reader.readLine()) != null) {

	               if (line.startsWith("CLASSES:")) {  

	                    parseClasses(classes, reader);  

	                } else if (line.startsWith("FIELDS:")) {  

	                    parseFields(vars, reader);  

	                } else if (line.startsWith("METHODS:")) {  

	                    parseMethods(defs, reader);  

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
		this.refreshJVMClasses();
		this.refreshReverse();		
	}
	
	
	
	
	public static void parseClasses(Map<String, String> classes, BufferedReader reader) throws IOException {  
	        String line;  
	        while ((line = reader.readLine()) != null && !line.isEmpty() && !line.startsWith("CLASSES:") && !line.startsWith("FIELDS:") && !line.startsWith("METHODS:")) {  
	            String[] parts = line.split(" -> ");  
	            if (parts.length == 2) {  
	                String originalClass = parts[0].replace('/', '.');  
	                String mappedClass = parts[1].replace('/', '.');  
	                classes.put(originalClass, mappedClass);  
	            }  
	        }  
	    }  
	  
	  public static void parseFields(Map<String, String> fields, BufferedReader reader) throws IOException {  
	        String line;  
	        while ((line = reader.readLine()) != null && !line.isEmpty() && !line.startsWith("FIELDS:") && !line.startsWith("METHODS:")) {  
	            String[] parts = line.split(" -> ");  
	            if (parts.length == 2) {  
	                String[] fieldParts = parts[0].split(" ");  
	                String originalClass = fieldParts[0].replace('/', '.');  
	                String fieldName = fieldParts[1];  
	                String fieldType = fieldParts[2];  
	                String fieldKey = originalClass + "." + fieldName + ":" + fieldType;  
	                String mappedField = parts[1].split(" ")[1];  
	                fields.put(fieldKey, mappedField);  
	            }  
	        }  
	    }  
	  
	    public static void parseMethods(Map<String, String> methods, BufferedReader reader) throws IOException {  
	        String line;  
	        while ((line = reader.readLine()) != null && !line.isEmpty() && !line.startsWith("METHODS:")) {  
	            String[] parts = line.split(" -> ");  
	            if (parts.length == 2) {  
	                String[] methodParts = parts[0].split(" ");  
	                String originalClass = methodParts[0].replace('/', '.');  
	                String methodName = methodParts[1];
	                String methodDesc = parts[0].substring(parts[0].lastIndexOf("(")).trim();  
	                String methodKey = originalClass + "." + methodName + methodDesc;  
	                String mappedMethod = parts[1].split(" ")[1];  
	                methods.put(methodKey, mappedMethod);  
	            }  
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
		return new DynamicMappingsTXT();
	}
	
	@Override
	public Map<String, String> getJVMClasses() {
		// TODO Auto-generated method stub
		return jvm_classes;
	}
	
}
