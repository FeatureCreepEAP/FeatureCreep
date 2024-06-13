package com.asbestosstar.assistremapper.mappings;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.asbestosstar.assistremapper.Mappings;

public class RecafMappings implements Mappings{

	public Map<String, String> classes = new HashMap<String, String>();
	public Map<String, String> jvm_classes = new HashMap<String, String>();
	public Map<String, String> defs = new HashMap<String, String>();
	public Map<String, String> vars = new HashMap<String, String>();
	public Map<String, String> params = new HashMap<String, String>();
	public Map<String, String[]> includes = new HashMap<String, String[]>();

	public Mappings reverse;
	
		
	private static final Pattern METHOD_PATTERN = Pattern.compile("(.*?)\\.(.*?)(\\(.*?) (.*)");
	
	//private static final Pattern FIELD_PATTERN = Pattern.compile("(.*?)\\.(.*?) (.*?) (.*)");
	
	private static final Pattern CLASS_PATTERN = Pattern.compile("(.*?) (.*)");
	
	
	
	
	/*
	 * Keep in mind that this returns a null reverse.
	 */
	public RecafMappings() {

	}

	public RecafMappings(InputStream map_file) {
				
		System.out.println("Parsing Mappings");
		BufferedReader reader = new BufferedReader(new InputStreamReader(map_file));
		String line;
		try {
			while ((line = reader.readLine()) != null) {

				
				
				Matcher methodMatcher = METHOD_PATTERN.matcher(line);
				//Matcher fieldMatcher = FIELD_PATTERN.matcher(line);
				Matcher classMatcher = CLASS_PATTERN.matcher(line);

				
				if (line.contains("(")) {
					String owner = methodMatcher.group(1).replace("/", ".");
					String name = methodMatcher.group(2);
					String desc = methodMatcher.group(3);
					String result = methodMatcher.group(4);
					defs.put(owner+"."+name+desc, result);			
				}else if(line.contains(":")) {
					String owner = methodMatcher.group(1).replace("/", ".");
					String name = methodMatcher.group(2);
					String desc = methodMatcher.group(3);
					String result = methodMatcher.group(4);
					vars.put(owner+"."+name+":"+desc, result);		
				}else {
					addClass(classMatcher.group(1).replace("/", "."), classMatcher.group(2).replace("/", "."));
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

	@Override
	public void write(OutputStream stream) {
		// TODO Auto-generated method stub
				
				List<String> lines = new ArrayList<String>();
				for (Map.Entry<String, String> clazz : getClasses().entrySet()) {
					String out_class;
					if(clazz.getValue().contains("$")) {
						String[] clz = clazz.getValue().split("\\$");
						out_class = clz[clz.length-1];
					}else {
						out_class = clazz.getValue();
					}
					
					String line = clazz.getKey().replace(".", "/") + " " + out_class.replace(".", "/");
					lines.add(line);
				}
				for (Map.Entry<String, String> def : getDefs().entrySet()) {
					String line = def.getKey().replace(".", "/") + " " + def.getValue().replace(".", "/");
					lines.add(line);
				}
				for (Map.Entry<String, String> var : getVars().entrySet()) {
					String line = var.getKey().replace(".", "/") + " " + var.getValue().replace(".", "/");
					lines.add(line);
				}

				try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream, StandardCharsets.UTF_8))) {

					for (String line : lines) {

						writer.write(line);

						writer.newLine(); // 写入换行符，这样每个字符串都会成为输出流中的一行

					}

					writer.flush(); // 确保所有内容都被写入输出流

				} catch (IOException e) {

					e.printStackTrace(); // 处理异常，这里只是简单地打印堆栈跟踪

				}

				// 关闭输出流（如果它没有被自动关闭，例如在try-with-resources语句中）

				try {

					stream.close();

				} catch (IOException e) {

					e.printStackTrace();

				}

		
		
	}
	
	@Override
	public void setReverse(Mappings rev) {
		// TODO Auto-generated method stub
		this.reverse=rev;
	}

	@Override
	public Mappings getPlainMappings() {
		// TODO Auto-generated method stub
		return new RecafMappings();
	}
	
	@Override
	public Map<String, String> getJVMClasses() {
		// TODO Auto-generated method stub
		return jvm_classes;
	}
	
	
}
