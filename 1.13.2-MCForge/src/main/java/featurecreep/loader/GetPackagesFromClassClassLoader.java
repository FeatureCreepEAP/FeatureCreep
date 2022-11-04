package featurecreep.loader;

import java.util.ArrayList;
import java.util.List;

public class GetPackagesFromClassClassLoader {

	
	
	public static String [] getPacakgesFromClassLoaderClassAsStringArray(Class clazz){
		
String[] packages_needed;
		
		List<String> package_list= new ArrayList();
		
		for (int j = 0; j < Package.getPackages().length; j++) {
		
		//	System.out.println(FeatureCreep.class.getClassLoader().getDefinedPackages()[j]);
			
			package_list.add(Package.getPackages()[j].getName().toString().replace(".", "/"));
		//	System.out.println(FeatureCreep.class.getClassLoader().getDefinedPackages()[j].toString());
			
		}
		packages_needed = package_list.toArray(new String[package_list.size()]);
		
		
		for (int p = 0; p < packages_needed.length; p++) {
			
			//	System.out.println(FeatureCreep.class.getClassLoader().getDefinedPackages()[j]);
				

			}
		
		
		return packages_needed;
		
	}
	
	
}

