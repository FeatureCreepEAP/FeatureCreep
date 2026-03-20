package com.asbestosstar.shadowassist;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtField;
import javassist.NotFoundException;
import javassist.bytecode.AccessFlag;
import javassist.bytecode.Bytecode;
import javassist.bytecode.DuplicateMemberException;
import javassist.bytecode.FieldInfo;
import javassist.bytecode.MethodInfo;

public class ClassShadowAssist {

	public static CtClass shadowClass(CtClass clazz, File output_path) {
		clazz.defrost();
		for (MethodInfo def : clazz.getClassFile().getMethods()) {
			if(!Modifier.isAbstract(def.getAccessFlags())) {
			def.setCodeAttribute(new Bytecode(clazz.getClassFile().getConstPool()).toCodeAttribute());
			}
		}
		for (CtField var : clazz.getDeclaredFields()) {
			String name = var.getName();
			String desc = var.getSignature();
			int flag = var.getFieldInfo().getAccessFlags();
			try {
				clazz.removeField(var);
				FieldInfo field = new FieldInfo(clazz.getClassFile().getConstPool(), name, desc);
				field.setAccessFlags(flag);
				clazz.getClassFile().addField(field);
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DuplicateMemberException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		output_path.mkdirs();
		try {
			clazz.getClassFile().compact();
			clazz.writeFile(output_path.getAbsolutePath());
		} catch (CannotCompileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clazz;
	}

}

