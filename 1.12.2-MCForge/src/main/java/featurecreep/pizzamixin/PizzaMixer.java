package featurecreep.pizzamixin;

import java.util.ArrayList;

import javassist.ByteArrayClassPath;
import javassist.ClassClassPath;
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
import net.pizzacrust.mixin.Mixin;
import net.pizzacrust.mixin.MixinBridge;
import net.pizzacrust.mixin.MixinTransformer;

public class PizzaMixer {

	public static void mix(String name) {
		// TODO Auto-generated method stub
		
		
		
		 try {
	            System.out.println("MixinAgent -> Detecting Mixins...");
	            ArrayList<CtClass> mixins = new ArrayList<>();
	                if (ClassPool.getDefault().getCtClass(name).hasAnnotation(Mixin.class)) {
	                    mixins.add(ClassPool.getDefault().getCtClass(name));
	                }
	           
		 
		 
	            System.out.println("MixinAgent -> Detected " + mixins.size() + " mixins.");
	            System.out.println("MixinAgent -> Moving into transformation stage...");
	            for (CtClass mixinClass : mixins) {
	                Mixin mixin = (Mixin) mixinClass.getAnnotation(Mixin.class);
	                MixinTransformer transformer = new MixinTransformer(mixin.value(), mixinClass);
	                try {
	                    transformer.transform();
	                } catch (Exception e) {
	                    System.out.println("MixinAgent -> Failed to load mixin class " + mixinClass.getName() + "!");
	                    e.printStackTrace();
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.exit(0);
	            return;
	        }
	    
		
		
	}

	
	
	
	
	
	public static byte[] mix(Class mix, byte[] bits) {
		// TODO Auto-generated method stub
		
		byte[] end_bits = null;
		
		
		
		 try {
	            System.out.println("MixinAgent -> Detecting Mixins...");
	            ArrayList<CtClass> mixins = new ArrayList<>();
	            ClassPool pool = ClassPool.getDefault();
	            pool.appendClassPath(new ClassClassPath(mix));

	                if (pool.getCtClass(mix.getName()).hasAnnotation(Mixin.class)) {
	                    mixins.add(pool.getCtClass(mix.getName()));
	                }
	           
		 
		 
	            System.out.println("MixinAgent -> Detected " + mixins.size() + " mixins.");
	            System.out.println("MixinAgent -> Moving into transformation stage...");
	            for (CtClass mixinClass : mixins) {
	                Mixin mixin = (Mixin) mixinClass.getAnnotation(Mixin.class);
	                try {
	                end_bits =    PizzaTransformer.transform(mixin.value(), mixinClass, bits);
	                } catch (Exception e) {
	                    System.out.println("MixinAgent -> Failed to load mixin class " + mixinClass.getName() + "!");
	                    e.printStackTrace();
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.exit(0);
	            return bits;
	        }
	    
         return end_bits;

		
	}
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}	
	

