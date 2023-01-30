package featurecreep.pizzamixin;

import org.apache.commons.lang3.SerializationUtils;

import javassist.ByteArrayClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.pizzacrust.mixin.FieldInitalizer;
import net.pizzacrust.mixin.IgnoreMethod;
import net.pizzacrust.mixin.Inject;
import net.pizzacrust.mixin.MethodName;
import net.pizzacrust.mixin.MixinBridge;
import net.pizzacrust.mixin.MixinTransformer;

public class PizzaTransformer extends MixinTransformer{

	
	
	
	  public PizzaTransformer(String targetClass, CtClass mixinClass) throws Exception {
		super(targetClass, mixinClass);
		// TODO Auto-generated constructor stub
	}

	  

	    /**
	     * Retrieves if the method specified already exists in the target class.
	     *
	     * @param method  the method
	     * @param ctClass the class
	     * @return the bool
	     */
	    private static boolean doesMethodAlreadyExists(CtMethod method, CtClass ctClass) {
	        try {
	            if (method.hasAnnotation(MethodName.class)) {
	                MethodName methodName = (MethodName) method.getAnnotation(MethodName.class);
	                ctClass.getDeclaredMethod(methodName.value(), method.getParameterTypes());
	            } else {
	                ctClass.getDeclaredMethod(method.getName(), method.getParameterTypes());
	            }
	        } catch (Exception e) {
	            return false;
	        }
	        return true;
	    }
	  
	  
	  
	    private static boolean doesFieldAlreadyExists(CtField field, CtClass ctClass) throws Exception {
	        for (CtField field1 : ctClass.getDeclaredFields()) {
	        	if (field1.getType() == field.getType() && field1.getName().equals(field.getName())) { //Type part was having some issues
	            //if (field1.getName().equals(field.getName())) {
	                return true;
	            }
	        }
	        return false;
	    }
	    
	  
	  
	/**
     * Transforms the target class accordingly with the mixin class.
     */
    public static byte[] transform(String targetCTClass, CtClass mixinClass, byte[] bits) throws Exception {
    	ClassPool pool = ClassPool.getDefault();
    	pool.insertClassPath(new ByteArrayClassPath(targetCTClass, bits));

    //	String oldclasspath = System.getProperty("java.class.path");
    //	System.out.println(oldclasspath);
  //  	String[] cpobjects = oldclasspath.split(File.pathSeparator);
//URLLoaderPizza urls = new URLLoaderPizza();
//for (int i = 0; i < cpobjects.length; i++) {
	//String encoded = java.net.URLEncoder.encode("file://"+cpobjects[i], "UTF-8");
	//urls.addURL(new URL("file://"+cpobjects[i]));
//	pool.appendClassPath(cpobjects[i]);

//}

//pool.insertClassPath(new LoaderClassPath(urls));


    	
    	
    	CtClass targetClass = pool.getCtClass(targetCTClass);
    	System.out.println("Mixin -> Transforming " + targetClass.getName() + " with " + mixinClass.getName() + "...");
        System.out.println(targetClass.getName() + " -> Adding Mixin fields...");
        CtClass targetCtClass = ClassPool.getDefault().getCtClass(targetClass.getName());
        CtClass mixinCtClass = ClassPool.getDefault().getCtClass(mixinClass.getName());
        for (CtField field : mixinCtClass.getDeclaredFields()) {
            if (!field.hasAnnotation(MixinBridge.class)) {
                CtField ctField = new CtField(field, targetCtClass);
                if (doesFieldAlreadyExists(ctField, targetCtClass)) {
                    for (CtField field1 : targetCtClass.getDeclaredFields()) {
                        if (field1.getType() == field.getType() && field1.getName().equals(field.getName())) {
                            FieldInitalizer fieldInitalizer = (FieldInitalizer) field.getAnnotation(FieldInitalizer.class); // field init is now only used for overriding fields because of JASSIST-140
                            for (CtConstructor constructor : targetCtClass.getDeclaredConstructors()) {
                                constructor.insertAfter(field.getName() + " = " + fieldInitalizer.value() + ";");
                            }
                        }
                    }
                } else {
                    targetCtClass.addField(ctField);
                }
            }
        }
        System.out.println(targetClass.getName() + " -> Adding Mixin methods...");
        for (CtMethod method : mixinCtClass.getDeclaredMethods()) {
            if (method.hasAnnotation(Inject.class)) {
                if (doesMethodAlreadyExists(method, targetCtClass)) {
                    Inject annotation = (Inject) method.getAnnotation(Inject.class);
                    CtMethod methodInTargetClass;
                    if (method.hasAnnotation(MethodName.class)) {
                        MethodName methodName = (MethodName) method.getAnnotation(MethodName.class);
                        methodInTargetClass = targetCtClass.getDeclaredMethod(methodName.value(), method.getParameterTypes());
                    } else {
                       methodInTargetClass = targetCtClass.getDeclaredMethod(method.getName(), method.getParameterTypes());
                    }
                    switch (annotation.value()) {
                        case AFTER:
                            methodInTargetClass.insertAfter("new " + method.getDeclaringClass().getName() + "($0)." + method.getName() + "($$);");
                            break;
                        case BEFORE:
                            methodInTargetClass.insertBefore("new " + method.getDeclaringClass().getName() + "($0)." + method.getName() + "($$);");
                            break;
                        case CUSTOM:
                            int line = annotation.line();
                            methodInTargetClass.insertAt(line, "new " + method.getDeclaringClass().getName() + "($0)." + method.getName() + "($$);");
                            break;
                    }
                } else {
                    System.out.println("MixinTransformer -> Error -> Retrieved a invalid @Inject annotation on: " + method.getName());
                }
            } else {
                if (!method.hasAnnotation(IgnoreMethod.class) || !method.hasAnnotation(MixinBridge.class) || !method.hasAnnotation(Inject.class)) {
                    if (doesMethodAlreadyExists(method, targetCtClass)) {
                        CtMethod ctMethod;
                        if (method.hasAnnotation(MethodName.class)) {
                            MethodName methodName = (MethodName) method.getAnnotation(MethodName.class);
                            ctMethod = targetCtClass.getDeclaredMethod(methodName.value(), method.getParameterTypes());
                        } else {
                           ctMethod = targetCtClass.getDeclaredMethod(method.getName(), method.getParameterTypes());
                        }
                        targetCtClass.removeMethod(ctMethod);
                    }
                    //CtMethod newMethod = new CtMethod(method.getReturnType(), method.getName(), method.getParameterTypes(), targetCtClass);
                    //newMethod.setBody(methodBody.value());
                    CtMethod newMethod;
                    if (method.hasAnnotation(MethodName.class)) {
                        MethodName methodName = (MethodName) method.getAnnotation(MethodName.class);
                        newMethod = CtNewMethod.copy(method, methodName.value(), targetCtClass, null);
                    } else {
                        newMethod = CtNewMethod.copy(method, targetCtClass, null);
                    }
                    targetClass.addMethod(newMethod);
                }
            }
        }
        System.out.println(targetClass.getName() + " -> Modifying target hierarchy...");
        for (CtClass interfaceType : mixinCtClass.getInterfaces()) {
            boolean isAlreadyImplemented = false;
            for (CtClass targetInterface : targetCtClass.getInterfaces()) {
                if (targetInterface == interfaceType) {
                    isAlreadyImplemented = true;
                }
            }
            if (!isAlreadyImplemented) {
                targetCtClass.addInterface(interfaceType);
            }
        }
        System.out.println("Mixin -> Inserting " + targetClass.getName() + " into class loader...");
      return  targetCtClass.toBytecode();
    }
	
    
    
    
    
    
    


}
