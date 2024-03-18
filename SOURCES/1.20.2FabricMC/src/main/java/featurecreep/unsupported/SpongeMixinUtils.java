package featurecreep.unsupported;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import featurecreep.FeatureCreep;
import featurecreep.loader.utils.ClassPathUtils;

public class SpongeMixinUtils {

	public static String[] getSpongeMixinClassTargets() {
		List<String> all_classes = ClassPathUtils.getClasses(FeatureCreep.loader);
		   // 使用Java流API过滤类名  
//        List<String> filtered_classes = all_classes.stream()  
//                .filter(className -> Arrays.stream(TransformerTargets.denylisted_packages)  
//                        .noneMatch(denylistedPackage -> className.startsWith(denylistedPackage)))  
//                .collect(Collectors.toList());  For vainilla
        
		// 使用Java流API过滤类名  
		List<String> filtered_classes = all_classes.stream()  
		        .filter(className -> Arrays.stream(TransformerTargets.denylisted_packages)  
		                .noneMatch(denylistedPackage -> className.startsWith(denylistedPackage)))  
		        .filter(className -> className.contains(".")) // Add this filter to remove classes without a dot  
		        .collect(Collectors.toList());  
		  
        
        
        // 打印过滤后的类名列表  
     //   filtered_classes.forEach(System.out::println);  
 return filtered_classes.toArray(new String[0]);
	
	}
	
}
