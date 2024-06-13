package com.asbestosstar.assistremapper;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

public interface RemapperInstance {

	public ClassPool getClassPool();

	public default CtClass getClassFromPool(String name) {
		return getClassFromPool(name, false);
	}

	public default CtClass getClassFromPool(String name, boolean is_interface) {
		try {// I also need a delete classes array
			return getClassPool().get(name);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			if (is_interface) {
				return getClassPool().makeInterface(name);
			} else {
				return getClassPool().makeClass(name);
			}
		}
	}

}
