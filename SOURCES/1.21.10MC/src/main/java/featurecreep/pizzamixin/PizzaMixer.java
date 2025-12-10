package featurecreep.pizzamixin;

//import java.util.ArrayList;

//import javassist.ClassPool;
//import javassist.CtClass;
//import net.pizzacrust.mixin.Mixin;
//import net.pizzacrust.mixin.MixinTransformer;

public class PizzaMixer {

	public static void mix(String name) {
		// TODO Auto-generated method stub

//		try {
//			System.out.println("MixinAgent -> Detecting Mixins...");
//			ArrayList<CtClass> mixins = new ArrayList<>();
//			if (ClassPool.getDefault().getCtClass(name).hasAnnotation(Mixin.class)) {
//				mixins.add(ClassPool.getDefault().getCtClass(name));
//			}
//
//			System.out.println("MixinAgent -> Detected " + mixins.size() + " mixins.");
//			System.out.println("MixinAgent -> Moving into transformation stage...");
//			for (CtClass mixinClass : mixins) {
//				Mixin mixin = (Mixin) mixinClass.getAnnotation(Mixin.class);
//				MixinTransformer transformer = new MixinTransformer(mixin.value(), mixinClass);
//				try {
//					transformer.transform();
//				} catch (Exception e) {
//					System.out.println("MixinAgent -> Failed to load mixin class " + mixinClass.getName() + "!");
//					e.printStackTrace();
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.exit(0);
//			return;
//		}

	}

}
