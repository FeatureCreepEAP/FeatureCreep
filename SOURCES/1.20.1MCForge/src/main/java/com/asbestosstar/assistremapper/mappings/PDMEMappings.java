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

import com.asbestosstar.assistremapper.Mappings;

public class PDMEMappings implements Mappings {

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
	public PDMEMappings() {

	}

	public PDMEMappings(InputStream pdme) {
		System.out.println("Parsing Mappings");
		BufferedReader reader = new BufferedReader(new InputStreamReader(pdme));
		String line;
		int num = 0;
		try {
			while ((line = reader.readLine()) != null) {
				// Perform your action for each line stripped here
				String[] row_array;
				row_array = line.split("¶");
				if (row_array.length < 2) {
					row_array = line.split("\\u00B6");
				}

				if (num == 0) {
				} else if (row_array[0].equals("Class")) {
					addClass(row_array[1], row_array[2]);
				} else if (row_array[0].equals("Def")) {
					defs.put(row_array[1], row_array[2]);
				} else if (row_array[0].equals("Var")) {
					vars.put(row_array[1], row_array[2]);
				} else if (row_array[0].equals("Param")) {
					params.put(row_array[3] + "@" + row_array[4], row_array[2]);
				} else if (row_array[0].equals("Include") || row_array[0].equals("Incluir")) {
					includes.put(row_array[1], row_array[2].split(","));
				}
				num++;
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

		// Need to account for JavaDoc soon

		List<String> lines = new ArrayList<String>();
		lines.add("tipo¶original¶nuevo¶def¶pos¶desc");
		for (Map.Entry<String, String> clazz : getClasses().entrySet()) {
			String out_class;
			if(clazz.getValue().contains("$")) {
				String[] clz = clazz.getValue().split("\\$");
				out_class = clz[clz.length-1];
			}else {
				out_class = clazz.getValue();
			}
			
			String line = "Class¶" + clazz.getKey() + "¶" + out_class + "¶nil¶nil¶";
			lines.add(line);
		}
		for (Map.Entry<String, String> def : getDefs().entrySet()) {
			String line = "Def¶" + def.getKey() + "¶" + def.getValue() + "¶nil¶nil¶";
			lines.add(line);
		}
		for (Map.Entry<String, String> var : getVars().entrySet()) {
			String line = "Var¶" + var.getKey() + "¶" + var.getValue() + "¶nil¶nil¶";
			lines.add(line);
		}
		for (Map.Entry<String, String> arg : getParams().entrySet()) {
			String[] key = arg.getKey().split("@");
			String line = "Param¶nil¶" + arg.getValue() + "¶" + key[0] + "¶" + key[1] + "¶";
			lines.add(line);
		}
		for (Map.Entry<String, String[]> include : getIncludes().entrySet()) {
			String line = "Include¶" + include.getKey() + "¶" + String.join(",",include.getValue()) + "¶nil¶nil¶";
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
		return new PDMEMappings();
	}
	
	@Override
	public Map<String, String> getJVMClasses() {
		// TODO Auto-generated method stub
		return jvm_classes;
	}

}
