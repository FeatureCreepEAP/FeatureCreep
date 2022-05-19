package featurecreep.loader;

import java.io.File;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import featurecreep.FeatureCreep;

public class FCLoaderBasicR2 {

	//Soon We will use this
	public static void loadMods(String pathstring)
	{
		File file = new File(pathstring);

	System.out.println(file.toString());
		
		String contents[] = file.list();
		System.out.println("List of files and directories in the specified directory:");
		//I need to make this multicore



		if (contents != null) {
		for(int i=0; i<contents.length; i++) {
		   System.out.println("FeatureCreep is trying to load "+contents[i]);

		   try {
			   
				 String oldclasspath = System.getProperty("java.class.path");
			      String mods = String.join(File.pathSeparator + pathstring, contents);
  				 String classpath = oldclasspath + File.pathSeparator + pathstring + mods;
			       
			   
			   
			   
			   JarFile jar = new JarFile(file +"/"+ contents[i]);
			   Manifest man = jar.getManifest();
			   Attributes mainAttributes = man.getMainAttributes();
			   String mainClass = mainAttributes.getValue(Attributes.Name.MAIN_CLASS);

			   

			   File fcfile =   new File(FeatureCreep.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());  
		
			   System.out.println(mainClass);
			   if (mainClass != null) {
			   String[] args = new String[] { "-cp", classpath, mainClass};
			   org.jboss.modules.Main.main(args);
			   }
			

		   
		   } catch (Throwable e) {
		   	// TODO Auto-generated catch block
		   	e.printStackTrace();
		   	//I need to make this display different messages if it is detected to be a mod from another platform. It is planned
		   }

		}


		}else {
			FeatureCreep.LOGGER.info("No Mods Found in Mods Folder");
		}


	
	}
		
	
	
	
	//Soon We will use this
		public static void loadMods(String [] pathstring, String[] otherclasspathlocations)
		{
			String alljars = "";
			String allmods = "";
			
			for(int l=0; l<pathstring.length; l++) {
			File modfile = new File(pathstring[l]);
String[] pathstringcontents = modfile.list();
for(int c=0; c<pathstringcontents.length; c++) {

			allmods = allmods + pathstring[l] + pathstringcontents[c] + File.pathSeparator;
}


			
			}
			
			for(int p=0; p<otherclasspathlocations.length; p++) {
				File classpathfile = new File(otherclasspathlocations[p]);
	String[] classpathstringcontents = classpathfile.list();
	
	if (classpathstringcontents != null) {
	for(int j=0; j<classpathstringcontents.length; j++) {

				alljars = alljars + otherclasspathlocations[p] + classpathstringcontents[j] + File.pathSeparator;
	}
	
	}

	
				}
	
			
			alljars = alljars+allmods;
			String[] allmodsarray = allmods.split(File.pathSeparator);
			
		System.out.println(alljars.toString());
			
			//String contents[] = file.list();
			System.out.println("List of files and directories in the specified directory:");
			//I need to make this multicore



			if (allmodsarray != null) {
			for(int i=0; i<allmodsarray.length; i++) {
			   System.out.println("FeatureCreep is trying to load "+allmodsarray[i]);

			   try {
				   
					 String oldclasspath = System.getProperty("java.class.path");
				      String mods = String.join(File.pathSeparator + pathstring, allmodsarray);
	  				 String classpath = oldclasspath + File.pathSeparator + alljars;
				       
				   System.out.println(classpath);
				   
				   
				   JarFile jar = new JarFile(allmodsarray[i]);
				   Manifest man = jar.getManifest();
				   Attributes mainAttributes = man.getMainAttributes();
				   String mainClass = mainAttributes.getValue(Attributes.Name.MAIN_CLASS);

				   

				   File fcfile =   new File(FeatureCreep.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());  
			
				   System.out.println(mainClass);
				   if (mainClass != null) {
				   String[] args = new String[] { "-cp", classpath, mainClass};
				   org.jboss.modules.Main.main(args);
				   }
				

			   
			   } catch (Throwable e) {
			   	// TODO Auto-generated catch block
			   	e.printStackTrace();
			   	//I need to make this display different messages if it is detected to be a mod from another platform. It is planned
			   }

			}


			}else {
				FeatureCreep.LOGGER.info("No Mods Found in Mods Folder");
			}


		
		}
	
	
	
	
	
	
	}
	

	
