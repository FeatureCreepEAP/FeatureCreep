package com.asbestosstar.assistremapper.mappings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.asbestosstar.assistremapper.Mappings;

//For the newer format in newer MCPs
public class MCPCSVNewer implements Mappings{
	
	
	
	public Map<String, String> classes = new HashMap<String, String>();
	public Map<String, String> jvm_classes = new HashMap<String, String>();
	public Map<String, String> defs = new HashMap<String, String>();
	public Map<String, String> vars = new HashMap<String, String>();
	public Map<String, String> params = new HashMap<String, String>();
	public Map<String, String[]> includes = new HashMap<String, String[]>();

	public Mappings reverse;

//	public ClassPool pool = new ClassPool(); will be needed later for full parsing without existing SRG Mapping object

	/*
	 * Keep in mind that this returns a null reverse.
	 */
	public MCPCSVNewer() {

	}
	

	//ATM We only support from existing SRG mappings
	public MCPCSVNewer(Mappings srgs, InputStream fields, InputStream methods, InputStream params) {
		System.out.println("Parsing Mappings");
		BufferedReader fields_reader = new BufferedReader(new InputStreamReader(fields));
		BufferedReader methods_reader = new BufferedReader(new InputStreamReader(methods));
		BufferedReader params_reader = new BufferedReader(new InputStreamReader(params));

		String line;
		try {
			while ((line = fields_reader.readLine()) != null) {
				if(!line.startsWith("searge")) {
					String[] split = line.split(",");
				if(srgs.getVars().containsValue(split[0]))
				{
				
					String key = getKeyFromValue(srgs.getVars(), split[0]);
					if(key!=null) {
						vars.put(key, split[1]);
					}
					
					
				}
					
				
				
				
				}

			}

			// Close the BufferedReader
			fields_reader.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		try {
			while ((line = methods_reader.readLine()) != null) {

				
				if(!line.startsWith("searge")) {
					String[] split = line.split(",");
				if(srgs.getDefs().containsValue(split[0]))
				{
				
					String key = getKeyFromValue(srgs.getDefs(), split[0]);
					if(key!=null) {
						defs.put(key, split[1]);
					}
					
					
				}
					
				
				
				
				}
				
				
				
				

			}

			// Close the BufferedReader
			methods_reader.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		try {
			while ((line = params_reader.readLine()) != null) {

				
				if(!line.startsWith("searge")) {
					String[] split = line.split(",");
				if(srgs.getParams().containsValue(split[0]))
				{
				
					String key = getKeyFromValue(srgs.getParams(), split[0]);
					if(key!=null) {
						this.getParams().put(key, split[1]);
					}
					
					
				}
					
				
				
				
				}
				
				
				

			}

			// Close the BufferedReader
			params_reader.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.includes.putAll(srgs.getIncludes());
		this.classes.putAll(srgs.getClasses());//Classes are not included in the newer spec, only the older ones have it
		this.jvm_classes.putAll(srgs.getJVMClasses());

		parseSubClasses();
		System.out.println("Getting reverse mappings");
		this.refreshReverse();


	}


	public String getKeyFromValue(Map<String, String> map, String string) {
		// TODO Auto-generated method stub
		
		for (Map.Entry<String, String> include : map.entrySet()) {
			
			if(include.getValue().equals(string)) {
				return include.getKey();
			}
			
			
		}
		
		
		
		
		return null;
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
	public Map<String, String[]> getIncludes() {
		// TODO Auto-generated method stub
		return includes;
	}


	@Override
	public Mappings getReverse() {
		// TODO Auto-generated method stub
		return reverse;
	}


	@Override
	public void setReverse(Mappings rev) {
		// TODO Auto-generated method stub
		this.reverse=rev;
	}


	@Override
	public Mappings getPlainMappings() {
		// TODO Auto-generated method stub
		return new MCPCSVNewer();
	}


	//Not now no
	@Override
	public void write(OutputStream stream) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public Map<String, String> getJVMClasses() {
		// TODO Auto-generated method stub
		return jvm_classes;
	}
	
	
	
}
