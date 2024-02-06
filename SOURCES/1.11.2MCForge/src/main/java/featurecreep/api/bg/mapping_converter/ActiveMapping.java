package featurecreep.api.bg.mapping_converter;

import com.asbestosstar.assistremapper.Mappings;

import javassist.ByteArrayClassPath;
import javassist.ClassPool;
import javassist.CtMethod;
import javassist.NotFoundException;

public class ActiveMapping {

	
	
	
public static ActiveMapping YARN = new ActiveMapping("yarn");	
public static ActiveMapping QUILT = new ActiveMapping("quilt");	
public static ActiveMapping HASHED_MOJMAP = new ActiveMapping("hashed");	
public static ActiveMapping PARCHMENT = new ActiveMapping("sugarcane");	
public static ActiveMapping PIDGEN = new ActiveMapping("pidgen");	
public static ActiveMapping SPIGOT = new ActiveMapping("spigot");	
public static ActiveMapping FABRICMC_INTERMEDIARY = new ActiveMapping("fain");	
public static ActiveMapping LEGACY_FABRICMC_INTERMEDIARY = new ActiveMapping("li");	
public static ActiveMapping SRG = new ActiveMapping("srg");	
public static ActiveMapping PARCHSRG = new ActiveMapping("parchsrg");	//SRG But with Moj/Parch classnames instead of the c_123 class names
public static ActiveMapping FEATURECREEP_INTERMEDIARY = new ActiveMapping("fci");
public static ActiveMapping OBF = new ActiveMapping("obf");
public static ActiveMapping MCP = new ActiveMapping("mcp");
public static ActiveMapping DYNAMIC_MAPPINGS = new ActiveMapping("dynamic");
public static ActiveMapping DANGERZONE = new ActiveMapping("dangerzone");



public Mappings mappings;
public String name;
public static boolean determained=false;//should be nonstatic but call is static








	
public ActiveMapping(String string) {
	// TODO Auto-generated constructor stub
	this.name=string;
}


public 	static ActiveMapping fromTransformer(byte[] bytes, String class_name) {
	String classname=class_name.replace("/", ".");
	if(classname.startsWith("game.")||classname.startsWith("obf.")) {
		determained=true;
		return FEATURECREEP_INTERMEDIARY;
	}else if(classname.startsWith("dangerzone")) {
		determained=true;
		return DANGERZONE;
	}else if(classname.startsWith("net.minecraft.class_")) {
		determained=true;
		return FABRICMC_INTERMEDIARY;
	}else if(classname.startsWith("net.minecraft.unmapped")) {
		determained=true;
		return HASHED_MOJMAP;
	}else if(classname.startsWith("net.minecraft.src.C_")) {//the C_ is required because some mappings like old mcp start with net.minecraft.src
		determained=true;
		return SRG;
	}else if(classname.startsWith("net.minecraft.")) {
		ClassPool.getDefault().appendClassPath(new ByteArrayClassPath(classname, bytes));
		try {
			for (CtMethod def :ClassPool.getDefault().get(classname).getMethods()) {
				if(def.getName().startsWith("m_")) {
					determained=true;
					return PARCHSRG;	
				}
				//else if (def.getName().startsWith("method_")) { YARN
				//	return 
				//}
				
			}
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		determained=true;
		return PARCHSRG;//temp
		
		
		//TODO QUILT and MCP and DYNAMIC MAPPINGS and PARCHMENT
	}else {
		determained=true;
		return OBF; //TODO Other mappings
	}
	
	
}


public boolean hasMappings() {
	if(mappings != null) {
		return true;
	}
	return false;
}

public Mappings setMappings(Mappings mappings) {
	this.mappings=mappings;
	return mappings;
}

public Mappings getMappings() {
	return mappings;
}	
	
}

