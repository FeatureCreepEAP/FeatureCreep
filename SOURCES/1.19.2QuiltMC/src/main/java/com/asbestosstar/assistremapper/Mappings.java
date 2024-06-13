package com.asbestosstar.assistremapper;

import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author rhel
 * 
 */
public interface Mappings {
	// We soon need to add comments/javadocs as well

	public Map<String, String> getClasses(); //Do not put to, use addClass whenever possible

	public Map<String, String> getJVMClasses(); //Do not use or put to unless you cant do addClass, should be refreshed when needed with refreshJVMClasses
	
	public Map<String, String> getDefs();

	public Map<String, String> getVars();

	public Map<String, String> getParams();

	public Map<String, String[]> getIncludes();

	public Mappings getReverse();

	public void setReverse(Mappings rev);

//Does not yet support params		
	public default void reverse(Mappings rev) {
		// TODO Auto-generated method stub

//for every entry in def
		for (Map.Entry<String, String> def : getDefs().entrySet()) {
			// System.out.println(def.getKey());
			String[] old_class_arr = java.util.Arrays.copyOfRange(def.getKey().split("\\."), 0,
					def.getKey().split("\\.").length - 1);
			String old_classname = String.join(".", old_class_arr);

			String new_classname = getClassMappedName(old_classname);
			if (def.getKey().contains("(")) {// Turns out this is not uncommon to fail
				String des = renameClassesInMethodDescriptor("(" + def.getKey().split("\\(")[1]);
				
				String[] divided = def.getKey().split("\\.");

				rev.getDefs().put(new_classname + "." + def.getValue() + des,
						divided[divided.length - 1].split("\\(")[0]);
			}
			// rev.defs.add()

			
	
			
			
			
		}

		for (Map.Entry<String, String> var : getVars().entrySet()) {

			String old_classname = String.join(".",
					java.util.Arrays.copyOfRange(var.getKey().split("\\."), 0, var.getKey().split("\\.").length - 1));

			String new_classname = getClassMappedName(old_classname);
			String old_des = var.getKey().split(":")[1];
			String des = this.renameClassesInFieldDescriptor(old_des);

			String[] divided = var.getKey().split("\\.");
			rev.getVars().put(new_classname + "." + var.getValue() + ":" + des,
					divided[divided.length - 1].split(":")[0]);
			// System.out.println(new_classname+"."+def.getValue()+":"+des+"¶"+divided[divided.length-1].split(":")[0]);

			// rev.defs.add()

		}

		for (Map.Entry<String, String> clazz : getClasses().entrySet()) {
			String lleve = clazz.getKey();
			String result = clazz.getValue();
			rev.getClasses().put(result, lleve);
			rev.getJVMClasses().put(result.replace(".", "/"), lleve.replace(".", "/"));//We convert in case the jvm one has not been refreshed yet
		}

		for (Map.Entry<String, String[]> include : getIncludes().entrySet()) {
			String[] val = include.getValue();
			if(val.length>0) {
			String[] new_values = new String[val.length];
			int i = 0;
			for (String incl : val) {
				new_values[i] = getClassMappedName(incl.trim());
				i++;
			}

			rev.getIncludes().put(getClassMappedName(include.getKey()), new_values);
		}
		}

		rev.setReverse(this); // Not best way to do this but an ok way

	}

	//Copied from Javassist Descriptor.rename
	public default String renameClassesInMethodDescriptor(String methodDescriptor) {  

		 if (this.getClasses() == null)
	            return methodDescriptor;

	        StringBuilder newdesc = new StringBuilder();
	        int head = 0;
	        int i = 0;
	        for (;;) {
	            int j = methodDescriptor.indexOf('L', i);
	            if (j < 0)
	                break;

	            int k = methodDescriptor.indexOf(';', j);
	            if (k < 0)
	                break;

	            i = k + 1;
	            String name = methodDescriptor.substring(j + 1, k);
	            String name2 = getClasses().get(name.replace("/", "."));
	            if (name2 != null) {
	                newdesc.append(methodDescriptor.substring(head, j));
	                newdesc.append('L');
	                newdesc.append(name2.replace(".", "/"));
	                newdesc.append(';');
	                head = i;
	            }
	        }

	        if (head == 0)
	            return methodDescriptor;
	        int len = methodDescriptor.length();
	        if (head < len)
	            newdesc.append(methodDescriptor.substring(head, len));

	        return newdesc.toString();
	        
	    }  

	public default String updateClassName(String className) {

		return this.getClassMappedName(className.replace("/", ".")).replace(".", "/");
	}

	public default String getClassMappedName(String original) {
		if (getClasses().get(original) != null) {
			return getClasses().get(original);
		} else {
			return original;
		}

	}

	public default String getClassUnMappedName(String mapped) {
		for (Map.Entry<String, String> entry : getClasses().entrySet()) {
			if (mapped.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		return mapped; // Value not found
	}

	public default String getDefMappedName(String original) {

		// 检查当前类是否有方法定义
		if (this.getDefs().containsKey(original)) {
			return this.getDefs().get(original);
		}

		String defau = original.split("\\.")[original.split("\\.").length - 1].split("\\(")[0];
		String[] split = original.split("\\.");
		// 检查当前类是否有继承的父类
		String[] superclazzes = this.getIncludes().get(String.join(".", getAllButLast(split)));
		if (superclazzes != null) {
			// 分割父类名
			for (String superClass : superclazzes) {
				// 去除父类名两侧的空白字符
				superClass = superClass.trim();

				// 递归查找父类的方法定义
				String name_and_desc = split[split.length - 1];
				String definition = getDefMappedName(superClass + "." + name_and_desc);
				if (definition != null && !definition.equals(defau)) {
					return definition;
				}
			}
		}

		// 如果没有找到定义，返回null

		return defau;
	}

	public default String getVarMappedName(String original) {
		// 检查当前类是否有方法定义
		if (this.getVars().containsKey(original)) {
			return this.getVars().get(original);
		}

		String defau = original.split("\\.")[original.split("\\.").length - 1].split(":")[0];
		String[] split = original.split("\\.");
		// 检查当前类是否有继承的父类
		String[] superclazzes = this.getIncludes().get(String.join(".", getAllButLast(split)));
		if (superclazzes != null) {
			// 分割父类名
			for (String superClass : superclazzes) {
				// 去除父类名两侧的空白字符
				superClass = superClass.trim();

				// 递归查找父类的方法定义
				String name_and_desc = split[split.length - 1];
				String to_check = superClass + "." + name_and_desc;
				String definition = getVarMappedName(to_check);
				if (definition != null && !definition.equals(defau)) {		
					return definition;
				}
			}
		}

		// 如果没有找到定义，返回null

		return defau;
	}

	/**
	 * @param method_with_descriptor Obfuscated Method Name with Descriptor using .
	 *                               instead of /
	 * @param location               starting from 1 where in the method the param
	 *                               is
	 * @return name
	 */
	public default String getParamMappedName(String method_with_descriptor, int location) {
		if (this.getParams().get(method_with_descriptor + "@" + Integer.toString(location)) != null) {
			return getParams().get(method_with_descriptor + "@" + Integer.toString(location)); // TODO need to make
																								// recursive
		} else {
			return method_with_descriptor;
		}
	}

	public default void parseSubClasses() {
		for (Map.Entry<String, String> entry : this.getClasses().entrySet()) {
			if (entry.getKey().contains("$")) {
			String lleve = entry.getKey();
			String value = parseSubClass(lleve);
			entry.setValue(value);
			getJVMClasses().put(lleve.replace(".", "/"), value.replace(".", "/"));	
			}
		}
	}

	public default String parseSubClass(String original_classname) {
		String new_name = getClassMappedName(original_classname);
		if (this.getClasses().containsKey(original_classname)) {

			if (new_name.contains("$")) {
				return new_name;
			} else {
				String[] sub_arr = original_classname.split("\\$");

				String[] subarray = Arrays.copyOfRange(sub_arr, 0, sub_arr.length - 1).clone();
				// Join the subarray elements with "$"
				String subarrayjoin = String.join("$", subarray);
				String root_class;
				if (subarrayjoin.contains("$")) {
					root_class = parseSubClass(subarrayjoin);
				} else {
					root_class = getClassMappedName(subarrayjoin);
				}

				return root_class + "$" + new_name;
				// return original_classname;
			}

		}

		String[] sub_arr = original_classname.split("\\$");

		String[] subarray = Arrays.copyOfRange(sub_arr, 0, sub_arr.length - 1).clone();
		// Join the subarray elements with "$"
		String root_class = getClassMappedName(String.join("$", subarray));
		if (root_class.contains("$")) {
			root_class = (parseSubClass(root_class));
		}

		String sub_class = sub_arr[sub_arr.length - 1];

		return root_class + "$" + sub_class;
		// return original_classname;

	}

	public default String parseSubClassUnmapped(String unmapped) {
		String[] sub_arr = unmapped.split("\\$");

		String[] subarray = Arrays.copyOfRange(sub_arr, 0, sub_arr.length - 1).clone();
		// Join the subarray elements with "$"
		String root_class = getClassMappedName(String.join("$", subarray));
		String sub_class = getClassMappedName(unmapped);
		return root_class + "$" + sub_class;

	}

	public default void addClass(String original, String mapped) {
		this.getClasses().put(original, mapped);
		this.getJVMClasses().put(original.replace(".", "/"), mapped.replace(".", "/"));
	}

	public default void addDef(String original, String mapped) {
		this.getDefs().put(original, mapped);
	}

	public default void addVar(String original, String mapped) {
		this.getVars().put(original, mapped);
	}

	public default void addParam(String original, String mapped) {
		this.getParams().put(original, mapped);
	}

	public default String renameClassesInFieldDescriptor(String old_desc) {

		String copia = old_desc;
		// 递归处理数组

		while (copia.startsWith("[")) {

			int dimensions = 0;

			// 计算数组的维度

			while (copia.startsWith("[")) {

				dimensions++;

				copia = copia.substring(1); // 去掉开头的"["

			}

			// 递归处理数组元素类型

			String renamedElementTypeDesc = renameClassesInFieldDescriptor(copia);

			// 重新构建多维数组描述符

			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < dimensions; i++) {

				sb.append('[');

			}

			sb.append(renamedElementTypeDesc);

			return sb.toString();

		}

		// 处理类描述符

		if (old_desc.startsWith("L") && old_desc.endsWith(";")) {

			String className = old_desc.substring(1, old_desc.length() - 1);

			String old_clazz = className.replace('/', '.');

			String clazz = getClassMappedName(old_clazz).replace('.', '/');

			return "L" + clazz + ";";

		}

		// 对于非类描述符（可能是基本类型），直接返回原描述符

		return old_desc;

	}

	public static Set<String> getClassNamesFromMethodDescriptor(String methodDescriptor) {
		Set<String> classNames = new HashSet<>();
		if (methodDescriptor != null && !methodDescriptor.isEmpty() && methodDescriptor.startsWith("(")
				&& methodDescriptor.endsWith(")")) {
			// Remove parentheses from the method descriptor
			methodDescriptor = methodDescriptor.substring(1, methodDescriptor.length() - 1);
			// Split the descriptor by ';' to separate argument types
			String[] argumentTypes = methodDescriptor.split(";");
			for (String argumentType : argumentTypes) {
				if (argumentType.startsWith("L")) {
					// Object type argument
					classNames.add(argumentType.replace("/", "."));
				} else if (argumentType.length() > 0) {
					// Primitive type or array argument
					classNames.add(getPrimitiveClassName(argumentType));
				}
			}
		}
		return classNames;
	}

	public static Set<String> getClassNamesFromFieldDescriptor(String fieldDescriptor) {
		Set<String> classNames = new HashSet<>();
		if (fieldDescriptor != null && !fieldDescriptor.isEmpty()) {
			char typeChar = fieldDescriptor.charAt(0);
			if (typeChar == 'L') {
				// Object type field
				classNames.add(fieldDescriptor.substring(1, fieldDescriptor.length() - 1).replace("/", "."));
			} else {
				// Primitive type field
				classNames.add(getPrimitiveClassName(typeChar));
			}
		}
		return classNames;
	}

	public static String getPrimitiveClassName(char typeChar) {
		switch (typeChar) {
		case 'B':
			return "byte";
		case 'C':
			return "char";
		case 'D':
			return "double";
		case 'F':
			return "float";
		case 'I':
			return "int";
		case 'J':
			return "long";
		case 'S':
			return "short";
		case 'Z':
			return "boolean";
		default:
			return null;
		}
	}

	public static String getPrimitiveClassName(String typeChar) {
		switch (typeChar) {
		case "B":
			return "byte";
		case "C":
			return "char";
		case "D":
			return "double";
		case "F":
			return "float";
		case "I":
			return "int";
		case "J":
			return "long";
		case "S":
			return "short";
		case "Z":
			return "boolean";
		default:
			return null;
		}
	}

	public static String[] getAllButLast(String[] array) {

		if (array == null || array.length == 0) {

			return new String[0]; // Return an empty array if the input is null or empty

		}

		// Create a new array with one less element than the original

		String[] result = new String[array.length - 1];

		// Copy all elements except the last one to the new array

		System.arraycopy(array, 0, result, 0, result.length);

		// Return the new array

		return result;

	}

	public default void copyTo(Mappings maps, boolean reverse) {
		for (Map.Entry<String, String> clazz : getClasses().entrySet()) {
			maps.getClasses().put(clazz.getKey(), clazz.getValue());
		}
		for (Map.Entry<String, String> def : getDefs().entrySet()) {
			maps.getDefs().put(def.getKey(), def.getValue());
		}
		for (Map.Entry<String, String> var : getVars().entrySet()) {
			maps.getVars().put(var.getKey(), var.getValue());
		}
		for (Map.Entry<String, String> arg : getParams().entrySet()) {
			maps.getParams().put(arg.getKey(), arg.getValue());
		}
		for (Map.Entry<String, String[]> include : getIncludes().entrySet()) {
			maps.getIncludes().put(include.getKey(), include.getValue());
		}

		if (reverse) {
			maps.refreshReverse();
		}
	}

	public default void refreshReverse() {
		Mappings new_refs = getPlainMappings();
		this.reverse(new_refs);
		this.setReverse(new_refs);
	}

	public Mappings getPlainMappings();

	public void write(OutputStream stream);

	/*
	 * original is original obj to your mappings to_convert_target to the new
	 * mappings you want your mappings to convert from. needs to be original obf to
	 * new target output is the blank mappings that is converted new target to your
	 * mappings
	 */
	public static void convert(Mappings original, Mappings to_convert_target, Mappings output) {
		Mappings conv_rev = to_convert_target.getReverse();
		
		// for every entry in def
		for (Map.Entry<String, String> def : conv_rev.getDefs().entrySet()) {
			// System.out.println(def.getKey());
			
						String[] old_class_arr = java.util.Arrays.copyOfRange(def.getKey().split("\\."), 0,
								def.getKey().split("\\.").length - 1);
						String old_classname = String.join(".", old_class_arr);

						String og_classname = conv_rev.getClassMappedName(old_classname);

						String des = conv_rev.renameClassesInMethodDescriptor("(" + def.getKey().split("\\(")[1]);
			if(original.getDefs().containsKey(og_classname + "." + def.getValue() + des)) {//Make sure both have. Mainly for recursive or incomplete mappings should find a better solution later
			String out = original.getDefMappedName(og_classname + "." + def.getValue() + des);
			
			output.getDefs().put(def.getKey(), out);

			}
			
			// rev.defs.add()

		}

		for (Map.Entry<String, String> var : conv_rev.getVars().entrySet()) {
			String old_classname = String.join(".",
					java.util.Arrays.copyOfRange(var.getKey().split("\\."), 0, var.getKey().split("\\.").length - 1));

			String og_classname = conv_rev.getClassMappedName(old_classname);
			String old_des = var.getKey().split(":")[1];
			String des = conv_rev.renameClassesInFieldDescriptor(old_des);
			if(original.getVars().containsKey(og_classname + "." + var.getValue() + ":" + des)) {
			

			String out = original.getVarMappedName(og_classname + "." + var.getValue() + ":" + des);

			output.getVars().put(var.getKey(), out);

		}
		}
			
		for (Map.Entry<String, String> clazz : conv_rev.getClasses().entrySet()) {
			if(original.getClasses().containsKey(clazz.getValue())) {
				String mapped = original.getClassMappedName(clazz.getValue());
				String key = clazz.getKey();
			output.getClasses().put(key, mapped);
			output.getJVMClasses().put(key.replace(".", "/"), mapped.replace(".", "/")); //We do from original in case JVM has not been refreshed
			}
		}

		// for every entry in def
		for (Map.Entry<String, String> param : original.getParams().entrySet()) {
			// System.out.println(def.getKey());

			
			String[] split = param.getKey().split("@");
			String param_num = split[1];
			String og_def = split[0];

			String[] old_class_arr = java.util.Arrays.copyOfRange(og_def.split("\\."), 0,
					og_def.split("\\.").length - 1);
			String old_classname = String.join(".", old_class_arr);

			String new_classname = to_convert_target.getClassMappedName(old_classname);
			if (og_def.contains("(")) {
				String des = to_convert_target.renameClassesInMethodDescriptor("(" + og_def.split("\\(")[1]);
				String to_con = new_classname + "." + to_convert_target.getDefMappedName(og_def) + des;
				output.getParams().put(to_con + "@" + param_num, param.getValue());

			}
			// rev.defs.add()

		}

		for (Map.Entry<String, String[]> include : original.getIncludes().entrySet()) {

			String[] val = include.getValue();
			if(val.length>0) {
			String[] new_values = new String[val.length];
			int i = 0;
			for (String incl : val) {
				new_values[i] = to_convert_target.getClassMappedName(incl.trim());
				i++;
			}

			output.getIncludes().put(to_convert_target.getClassMappedName(include.getKey()), new_values);
		}


		}

		output.parseSubClasses();
		output.refreshReverse();
	}
	
	public default void refreshJVMClasses() {
		this.getJVMClasses().clear();
		for (Map.Entry<String, String> entry : getClasses().entrySet()) {
			this.getJVMClasses().put(entry.getKey().replace(".", "/"), entry.getValue().replace("/", "."));
		}
	}
	
	
	
}
