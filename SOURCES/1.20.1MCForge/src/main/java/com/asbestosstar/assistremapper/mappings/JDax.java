package com.asbestosstar.assistremapper.mappings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.asbestosstar.assistremapper.Mappings;

public class JDax implements Mappings {

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
	public JDax() {

	}

	public JDax(InputStream map_file, InputStream jar) {
		System.out.println("Parsing Mappings");
		BufferedReader reader = new BufferedReader(new InputStreamReader(map_file));
		String line;
		try {
			while ((line = reader.readLine()) != null) {

				
	
				
				
				String[] args = line.trim().split("[\\s=:]+");
				String type = args[0];
						if (type.equals("c")){
							// 1: class-name
							// 2: renameed class (does not include package)
							// Replace "." in class name
							String original = args[1];
							// The new value is always in the same package.
							// Only the class is renamed, not the package.
							addClass(original, original.substring(0, original.lastIndexOf('.') + 1) + args[2]);
						}
						else if (type.equals("f")){
							// 1: class-name.field-name
							// 2: field-type
							// 3: renamed
							// Replace all "." except last one
							vars.put(args[1]+":"+args[2], args[3]);
						}
						else if (type.equals("m")){
							// 1: class-name.method-name + method-desc
							// 2: renamed
							// Replace all "." except last one
							defs.put(args[1], args[2]);
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
		return new JDax();
	}
	
	@Override
	public Map<String, String> getJVMClasses() {
		// TODO Auto-generated method stub
		return jvm_classes;
	}
	
}
