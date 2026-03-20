package com.asbestosstar.assistremapper.mappings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.asbestosstar.assistremapper.Mappings;

public class Tiny implements Mappings{

	
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
	public Tiny() {

	}
	
	public Tiny(InputStream map_file) {
		System.out.println("Parsing Mappings");
		BufferedReader reader = new BufferedReader(new InputStreamReader(map_file));
		String line;
		try {
			while ((line = reader.readLine()) != null) {						 
				
				if (line.trim().startsWith("#")) {
		
					continue;
		
				}
					this.parseLine(line);
				
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
	
	
	
	
	public void parseLine(String line) {  
	    String[] tokens = line.split("\t");  
	    String key = tokens[0];  
	      
	    if ("CLASS".equals(key)) {  
	        this.parseClass(tokens);  
	    } else if ("FIELD".equals(key)) {  
	        this.parseField(tokens);  
	    } else if ("METHOD".equals(key)) {  
	        this.parseMethod(tokens);  
	    } else if ("MTH-ARG".equals(key)) {  
	        this.parseArgument(tokens);  
	    } //else {  
	       // throw new RuntimeException("Unknown token '" + key + "'!");  
	    //}  
	}
	
 
	
	public void parseClass(String[] tokens) {
	
		String obfuscatedEntry = tokens[1];
	
		String mapping = tokens[2];
	
		if (mapping.indexOf('$') > 0) {
	
			// inner classes should map to only the final part
	
			mapping = mapping.substring(mapping.lastIndexOf('$') + 1);
	
		}
	
 
	
		addClass(obfuscatedEntry.replace("/", "."), mapping.replace("/", "."));
	
	}
	
 
	
	public void parseField(String[] tokens) {
	
		String ownerClass = tokens[1].replace("/", ".");
	
		String descriptor = tokens[2];
	
 
	
		String obfuscatedEntry = ownerClass + "." + tokens[3] + ":" + descriptor;
	
		String mapping = tokens[4];
	
vars.put(obfuscatedEntry, mapping);
	
	}
	
 
	
	public void parseMethod(String[] tokens) {
	
		String ownerClass = tokens[1].replace("/", ".");
	
		String descriptor = tokens[2];
	
 
	
		String obfuscatedEntry = new String(ownerClass + "." + tokens[3] + descriptor);
	
		String mapping = tokens[4];
	
defs.put(obfuscatedEntry, mapping);

	}
	
 
	
	public void parseArgument(String[] tokens) {
	
		String ownerClass = tokens[1].replace("/", ".");
		
		String descriptor = tokens[2];
	
		String obfuscatedEntry = new String(ownerClass + "." + tokens[3] + descriptor);
	
		int variableIndex = Integer.parseInt(tokens[4]);
	
 
	
		String mapping = tokens[5];
	
	
		params.put(obfuscatedEntry + "@" + variableIndex, mapping);
	
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
		return new Tiny();
	}
	
	@Override
	public Map<String, String> getJVMClasses() {
		// TODO Auto-generated method stub
		return jvm_classes;
	}
	
	
}
