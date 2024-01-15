package com.asbestosstar.assistremapper;

import java.lang.reflect.Modifier;
import java.util.ArrayList;

import javassist.CannotCompileException;
import javassist.CodeConverter;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;
import javassist.expr.MethodCall;

public class ExprScan extends ExprEditor{

	public RemapperInstance remapper;
	public CodeConverter converter;
	
	public ExprScan(RemapperInstance remapper, CodeConverter converter) {
		this.remapper=remapper;
		this.converter=converter;
	}
	
	@Override
	public void edit(MethodCall m) {
        ArrayList<String> recersive_names = new ArrayList<String>();

   
		CtClass clazz =remapper.getClassFromPool(m.getClassName());
		
		String oldest = remapper.getOldestMethodName(clazz,m.getMethodName(), m.getSignature(),recersive_names);
		CtMethod neww= (remapper.getMethodFromClass(oldest, m.getSignature(), remapper.getClassFromPool(m.getClassName())));

		if(m.getEnclosingClass().getName().contains("featurecreep")) {
     //  		System.out.println(oldest);
		if(clazz.getName().contains("minecraft.")||clazz.getName().contains("game")) {
		//System.out.println(m.getClassName());
			if(m.getMethodName().contains("RL")) {
			System.out.println(m.getEnclosingClass().getName()+" "+clazz.getName()+"."+m.getMethodName()+m.getSignature());
			System.out.println(m.getEnclosingClass().getName()+" "+clazz.getName()+"."+neww.getName()+neww.getSignature());
			}
		}	
		}
		
		

		//converter.redirectMethodCall(m.getMethodName(), neww);
	//	if(remapper.mappings.defs.containsKey(m.getClassName().replace("/", ".")+"."+m.getMethodName()+m.getSignature())) {
//            	m.replace("$_= "+m.getClassName().replace("/", ".")+"."+oldest+"($$);");   
    	

            			try {
						if(!oldest.equals(m.getMethodName())&&oldest !=null)
            				converter.redirectMethodCall(m.getMethodName(), neww);
            			} catch (CannotCompileException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	// 	}

		
		recersive_names.clear();
}
	@Override
    public void edit(FieldAccess f) throws CannotCompileException {
    	ArrayList<String> recersive_names = new ArrayList<String>();
	remapper.getClassFromPool(f.getClassName()).defrost();
	String oldest_field = remapper.getOldestFieldName(remapper.getClassFromPool(f.getClassName()).getClassFile(), f.getFieldName(), f.getSignature(),recersive_names);
	//System.out.println(oldest_field);


	
	//System.out.println(f.getClassName().replace("/", ".")+"."+f.getFieldName()+":"+f.getSignature());

    if(remapper.mappings.vars.containsKey(f.getClassName().replace("/", ".")+"."+f.getFieldName()+":"+f.getSignature())) {


    if(f.getClassName().equals(f.getEnclosingClass().getName()) && !f.isStatic()) {
    	f.replace("$_ =$0."+oldest_field+";");//maybe $class would be better?
    }	else {
 //   	System.out.println(f.getClassName().replace("/", "."));
 //   	System.out.println(oldest_field);
    	f.replace("$_ ="+remapper.mappings.getClassMappedName(f.getClassName()).replace("/", ".")+"."+oldest_field+";");
    }
    	
    	//converter.redirectFieldAccess(remapper.getFieldFromClass(f.getFieldName(), f.getSignature(), remapper.getClassFromPool(f.getClassName())), remapper.getClassFromPool(f.getClassName()), oldest_field);

    
    }
	//f.replace("System.out.println(\"\");");

    	//converter.redirectFieldAccess(remapper.getFieldFromClass(f.getFieldName(), f.getSignature(), remapper.getClassFromPool(f.getClassName())), remapper.getClassFromPool(f.getClassName()), remapper.getOldestFieldName(remapper.getClassFromPool(f.getClassName()).getClassFile(), f.getFieldName(), f.getSignature(),recersive_names));
		recersive_names.clear();		
	
	}



}
