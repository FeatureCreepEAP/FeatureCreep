package com.asbestosstar.assistremapper.mappings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.asbestosstar.assistremapper.Mappings;

public class M3LMappings implements Mappings{

	
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
	public M3LMappings() {

	}
	
	
	public M3LMappings(InputStream mappings) {

		System.out.println("Parsing Mappings");
		BufferedReader reader = new BufferedReader(new InputStreamReader(mappings));
		String line;
		String currentClass = null;
		String currentDef = null;
		try {
			while ((line = reader.readLine()) != null) {
				//Modified from Recaf's parser
				String lineStrTrim = line.trim();
				String[] args = lineStrTrim.split(" ");//theres gotta be a better way of spliting space
				String type = args[0];
				
				try {
					// A note on the "intermediate" values... I have seen cases of the format where this column
					// does not exist... so the fix here will be to check for the number of columns. If there are
					// enough, we assume it contains the intermediate in the middle. Otherwise, there is none.
					switch(type) {
						case "CLASS":
							currentClass=args[1].replace("none/", "").replace("/", ".");
							if(args.length >= 3) { //Need to reconsider lambda classes with just numbers in their name for the whole remapper
								addClass(currentClass,args[2].replace("/", ".")); //The removing of the none package is not the best way to do this but since this format is uncommon its ok 
							}
							break;
						case "FIELD":
							if(args.length >= 4) {
							vars.put(currentClass + "." + args[1]+":"+args[3].replace("none/", ""), args[2]);
							}
							break;
						case "METHOD":
							
							if(args.length >= 4) {
							currentDef = currentClass + "." + args[1] + args[3].replace("none/", "");
							defs.put(currentDef, args[2]);
							}else {
								currentDef = currentClass + "." + args[1] + args[2].replace("none/", "");
							}
							break;
						case "ARG": //Soon TM
							params.put(currentDef + "@" + args[1], args[2]);
							
						
					}
				} catch(IndexOutOfBoundsException ex) {
					throw new IllegalArgumentException("failed parsing line " + line, ex);
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
		return new M3LMappings();
	}	
	
	@Override
	public Map<String, String> getJVMClasses() {
		// TODO Auto-generated method stub
		return jvm_classes;
	}
 
	
}
