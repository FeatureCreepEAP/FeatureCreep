package com.asbestosstar.AssistMixer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.asbestosstar.AssistMixer.annotations.IgnoreImplements;

import javassist.CannotCompileException;
import javassist.ClassMap;
import javassist.CtBehavior;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.NotFoundException;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.ClassFile;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.CodeIterator;

public class AssistMixer {
	
	public static ArrayList<AssistMixerPlugin> plugins = new ArrayList<AssistMixerPlugin>();
	
	public static void main(String[] args) {
	System.out.println("Running AssistMixer");
	}
	
	public static void mix(CtClass receiver, CtClass donar) {
		receiver.defrost();
		donar.defrost();
	
		ClassFile receiverclass = receiver.getClassFile();
		ClassFile donarclass = donar.getClassFile();

	if (!donar.hasAnnotation("com.asbestosstar.AssistMixer.annotations.IgnoreExtends")) {
	if (donarclass.getSuperclass() != "java.lang.Object") {try {
		receiverclass.setSuperclass(donarclass.getSuperclass());
	} catch (CannotCompileException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
	}
	
	String[] ignored_implements = {};
	if (donar.hasAnnotation("com.asbestosstar.AssistMixer.annotations.IgnoreImplements")) {
		try {
			IgnoreImplements implement = (IgnoreImplements)donar.getAnnotation(IgnoreImplements.class);
			ignored_implements = implement.ignoring();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

	List<String> interfaces = new ArrayList<String>(Arrays.asList(donarclass.getInterfaces()));
	List<String> subtractinterfaces = new ArrayList<String>(Arrays.asList(ignored_implements));
	interfaces.removeAll(subtractinterfaces);
	String[] interfaces_to_add = interfaces.toArray(new String[subtractinterfaces.size()]);
	
	for (int i = 0; i < interfaces_to_add.length; i++) {
	if (interfaces_to_add[i] != null)
		receiverclass.addInterface(interfaces_to_add[i]);
	}
	
	
	
	
	
	
	List<CtField> accepted_fields = new ArrayList<>();

	for(CtField field : donar.getFields()){
	  if (!field.hasAnnotation("com.asbestosstar.AssistMixer.annotations.Ignore")) {accepted_fields.add(field);}			
	}

	for(CtField field : accepted_fields){
		try {
			CtField newField = new CtField(field, receiver);
			//	newField.setAttribute("fieldInitializer", field.getAttribute("fieldInitializer"));
		
		receiver.addField(newField);//We soon gotta do for Initalizer
		} catch (CannotCompileException e) {
			// TODO Auto-generated catch block
			if (field.hasAnnotation("com.asbestosstar.AssistMixer.annotations.Overwrite")) {
try {
	receiver.removeField(receiver.getField(field.getName()));
	CtField newField = new CtField(field, receiver);
	receiver.addField(newField);//We soon gotta do for Initalizer

} catch (NotFoundException | CannotCompileException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
				
				
			}else {
				System.out.println("Duplicate Field, if you want to overwrite a field use the @Overwrite annotation");
				e.printStackTrace();	
			}
					
		}		
	}
	
	
	
	
	
	//Constructors

	
		List<CtConstructor> accepted_constructors = new ArrayList<>();

		for(CtConstructor constructor : donar.getConstructors()){
		  if (!constructor.hasAnnotation("com.asbestosstar.AssistMixer.annotations.Ignore")) {accepted_constructors.add(constructor);}			
		}

		for(CtConstructor constructor : accepted_constructors){
			try {
				CtConstructor newConstructor = new CtConstructor(constructor, receiver, new ClassMap());
				//	newField.setAttribute("fieldInitializer", field.getAttribute("fieldInitializer"));
			
			receiver.addConstructor(newConstructor);//We soon gotta do for Initalizer
			} catch (CannotCompileException e) {
				// TODO Auto-generated catch block
				if (constructor.hasAnnotation("com.asbestosstar.AssistMixer.annotations.Overwrite")) {
	try {
		receiver.removeConstructor(receiver.getConstructor(constructor.getSignature()));		
		CtConstructor newConstructor = new CtConstructor(constructor, receiver, new ClassMap());
		receiver.addConstructor(newConstructor);//We soon gotta do for Initalizer

	} catch (NotFoundException | CannotCompileException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
					
					
				}if (constructor.hasAnnotation("com.asbestosstar.AssistMixer.annotations.InsertBefore")) {
					try {
						insertCtBehaviourBefore(receiver.getConstructor(constructor.getSignature()), new CtConstructor(constructor, receiver, new ClassMap()));
					} catch (NotFoundException | CannotCompileException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				
				
				
				
				
				
				
				}
				
				
				
				else {
					System.out.println("Duplicate Constructor, if you want to overwrite a field use the @Overwrite annotation");
					e.printStackTrace();	
				}
				
				
				
				
			}		
		}
		
		
		
		
		
		
			
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void insertCtBehaviourBefore(CtBehavior receiver, CtBehavior donar)
	{
		
	
		
     /*   CodeAttribute ca = receiver.getMethodInfo().getCodeAttribute();
		 CodeIterator iterator = ca.iterator();	
		
		  
		 int stack = ca.getMaxStack();
         int locals = ca.getMaxLocals();

         if (stack > ca.getMaxStack())
             ca.setMaxStack(stack);

         if (locals > ca.getMaxLocals())
             ca.setMaxLocals(locals);
		 
		int pos = 0;
		try {
			pos = iterator.insertEx(donar.getMethodInfo().getCodeAttribute().getCode());
		} catch (BadBytecode e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        iterator.insert(donar.getMethodInfo().getCodeAttribute().getExceptionTable(), pos);
        
        try {
			receiver.getMethodInfo().rebuildStackMapIf6(receiver.getDeclaringClass().getClassPool(), receiver.getDeclaringClass().getClassFile2());
		
        } catch (BadBytecode e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	
	
	
		
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

