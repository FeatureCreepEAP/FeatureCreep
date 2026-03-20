package com.asbestosstar.assistremapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import com.asbestosstar.assistremapper.remapper.ClassRemapper;

import javassist.CannotCompileException;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.ConstPool;
import javassist.expr.Expr;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;
import javassist.expr.MethodCall;

public class ExprScan extends ExprEditor {

	public ClassRemapper remapper;
	public boolean dess;

	public ExprScan(ClassRemapper remapper, boolean dess) {
		this.remapper = remapper;
		this.dess = dess;
	}

	@Override
	public void edit(MethodCall m) {

		if (dess) {
			editNameAndTypeDes(m, remapper.mappings.renameClassesInMethodDescriptor(m.getSignature()));
		} else {
			ArrayList<String> recersive_names = new ArrayList<String>();
			if (!m.getClassName().contains("[]")) {
				
				String oldest = remapper.getOldestMethodName(
						remapper.getClassFromName(m.getClassName().replace("/", ".")), m.getMethodName(),
						m.getSignature(), recersive_names);
				
				if (!oldest.equals(m.getMethodName()) && oldest != null) {					
					editNameAndType(m, oldest);
				}
			}
			recersive_names.clear();

		}

	}

	@Override
	public void edit(FieldAccess f) throws CannotCompileException {
		if (dess) {
			editNameAndTypeDes(f, remapper.mappings.renameClassesInFieldDescriptor(f.getSignature()));
		} else {

			ArrayList<String> recersive_names = new ArrayList<String>();
			String oldest = remapper.getOldestFieldName(remapper.getClassFromName(f.getClassName().replace("/", ".")),
					f.getFieldName(), f.getSignature(), recersive_names);

			
			if (!oldest.equals(f.getFieldName()) && oldest != null) {			
				editNameAndType(f, oldest);
			}

			recersive_names.clear();

		}

	}

	public static void editNameAndType(Expr expr, String new_name) {
		CodeIterator iterator = expr.where().getMethodInfo().getCodeAttribute().iterator();
		ConstPool cons = expr.getEnclosingClass().getClassFile().getConstPool();
		int pos = expr.indexOfBytecode();
		int u16u = iterator.u16bitAt(pos + 1);
		int tipoynombre;
		if (expr instanceof MethodCall) { // How different are field from Method ref Name and Type?
			tipoynombre = cons.getMethodrefNameAndType(u16u);
		} else {
			tipoynombre = cons.getFieldrefNameAndType(u16u);
		}
		int nombre = cons.addUtf8Info(new_name);
		if(new_name.contains("/")) {
		System.out.println(new_name);
		}
		try {
			Method additem = ConstPool.class.getDeclaredMethod("getItem", int.class);
			additem.setAccessible(true);
			Object inf = additem.invoke(cons, tipoynombre);
//Should make this check for instance 1st
			Class<?> ref_info = Class.forName(ConstPool.class.getPackage().getName() + ".NameAndTypeInfo", false,
					cons.getClass().getClassLoader());
			Field nati = ref_info.getDeclaredField("memberName");
			nati.setAccessible(true);
			nati.setInt(inf, nombre);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | ClassNotFoundException | NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void editNameAndTypeDes(Expr expr, String desc) {
		CodeIterator iterator = expr.where().getMethodInfo().getCodeAttribute().iterator();
		ConstPool cons = expr.getEnclosingClass().getClassFile().getConstPool();
		int pos = expr.indexOfBytecode();
		int u16u = iterator.u16bitAt(pos + 1);
		int tipoynombre;
		if (expr instanceof MethodCall) { // How different are field from Method ref Name and Type?
			tipoynombre = cons.getMethodrefNameAndType(u16u);
		} else {
			tipoynombre = cons.getFieldrefNameAndType(u16u);
		}
		int des = cons.addUtf8Info(desc);// We **could** overwrite with a little work but not really worth the hassle
											// when it can be redirected more easily and safely
		try {
			Method additem = ConstPool.class.getDeclaredMethod("getItem", int.class);
			additem.setAccessible(true);
			Object inf = additem.invoke(cons, tipoynombre);
//Should make this check for instance 1st
			Class<?> ref_info = Class.forName(ConstPool.class.getPackage().getName() + ".NameAndTypeInfo", false,
					cons.getClass().getClassLoader());
			Field dsti = ref_info.getDeclaredField("typeDescriptor");
			dsti.setAccessible(true);
			dsti.setInt(inf, des);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | ClassNotFoundException | NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}