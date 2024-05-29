package com.asbestosstar.assistremapper;

import java.util.ArrayList;
import java.util.List;

import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.ClassFile;
import javassist.bytecode.Descriptor;

public class GenericExtendsFinder {

	public RemapperInstance remapper;

	public GenericExtendsFinder(RemapperInstance remapper) {
		// TODO Auto-generated constructor stub
		this.remapper = remapper;
	}

	public List<CtMethod> getMethodsFromNameAlone(CtClass clazz, String name, boolean declared_only) {
		List<CtMethod> defs = new ArrayList<CtMethod>();
		CtMethod[] from_clazz;
		if (declared_only) {// Should really be making a method for ClassFile for this but in reality it is
							// likely unneeded
			from_clazz = clazz.getDeclaredMethods();
		} else {
			from_clazz = clazz.getMethods();
		}
		for (CtMethod def : from_clazz) {
			if (def.getName().equals(name)) {
				defs.add(def);
			}
		}

		return defs;
	}

	public List<CtField> getFieldsFromNameAlone(CtClass clazz, String name, boolean declared_only) {
		List<CtField> vars = new ArrayList<CtField>();
		CtField[] from_clazz;
		if (declared_only) {// Should really be making a method for ClassFile for this but in reality it is
							// likely unneeded
			from_clazz = clazz.getDeclaredFields();
		} else {
			from_clazz = clazz.getFields();
		}
		for (CtField var : from_clazz) {
			if (var.getName().equals(name)) {
				vars.add(var);
			}
		}

		return vars;
	}

	public String getPotentialGenericExtendingField(ClassFile clazz, String fieldName, String desc,
			ArrayList<String> recersive_names) {
		String name = fieldName;
		CtClass ct = remapper.getClassFromPool(clazz.getName());// Ct is easier as it goes down
		List<CtField> vars = getFieldsFromNameAlone(ct, fieldName, false);
		try {
			CtClass return_type = Descriptor.getReturnType(desc, remapper.classpool);

			for (CtField var : vars) {
				CtClass var_ret = Descriptor.getReturnType(var.getSignature(), remapper.classpool);
				if (return_type != null && var_ret != null) {
					if (return_type.subtypeOf(var_ret) && !var.getSignature().equals(desc)) {
						String oldest = remapper.getOldestFieldName(var.getDeclaringClass().getClassFile(), fieldName,
								var.getSignature(), new ArrayList<String>());
						if (oldest != fieldName) {
							remapper.mappings.vars.put(clazz.getName() + "." + fieldName + ":" + desc, oldest);
							name = oldest;
						}
					}
				}
			}

		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			if (remapper.debug_mode) {
				e.printStackTrace();
			}
			remapper.getClassFromPool(e.getMessage());
			return (getPotentialGenericExtendingField(clazz, fieldName, desc, recersive_names));

		}

		return name;
	}

	public String getPotentialGenericExtendingMethod(ClassFile clazz, String methodName, String desc,
			ArrayList<String> recersive_names) {
		String name = methodName;
		CtClass ct = remapper.getClassFromPool(clazz.getName());// Ct is easier as it goes down
		List<CtMethod> defs = getMethodsFromNameAlone(ct, methodName, false);
		try {
			CtClass return_type = Descriptor.getReturnType(desc, remapper.classpool);

			for (CtMethod def : defs) {
				CtClass var_ret = Descriptor.getReturnType(def.getSignature(), remapper.classpool);
				if (return_type != null && var_ret != null) {

					if (return_type.subtypeOf(var_ret) && !def.getSignature().equals(desc)
							&& isCompatible(Descriptor.getParameterTypes(desc, remapper.classpool),
									Descriptor.getParameterTypes(def.getSignature(), remapper.classpool))) {

						String oldest = remapper.getOldestMethodName(def.getDeclaringClass().getClassFile(), methodName,
								def.getSignature(), new ArrayList<String>());
						if (oldest != methodName) {
							remapper.mappings.defs.put(clazz.getName() + "." + methodName + ":" + desc, oldest);
							name = oldest;
						}
					}
				}
			}

		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			if (remapper.debug_mode) {
				e.printStackTrace();
			}
			remapper.getClassFromPool(e.getMessage());
			return (getPotentialGenericExtendingMethod(clazz, methodName, desc, recersive_names));

		}

		return name;
	}

	public boolean isCompatible(CtClass[] targetTypes, CtClass[] candidateTypes) {
		if (targetTypes.length != candidateTypes.length) {
			return false;
		}

		try {
			for (int i = 0; i < targetTypes.length; i++) {
				CtClass targetType = targetTypes[i];
				CtClass candidateType = candidateTypes[i];

				if (!targetType.subtypeOf(candidateType)) {
					return false;
				}
			}
		} catch (NotFoundException e) {
			// Class not found in the ClassPool, consider it incompatible
			return false;
		}

		return true;
	}

}
