package featurecreep.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

import org.jboss.modules.ClassTransformer;
import org.jboss.modules.Module;
import org.jboss.modules.ModuleIdentifier;
import org.jboss.modules.ModuleLoadException;
import org.jboss.modules.ModuleLoader;
import org.jboss.modules.ModuleSpec;
import org.jboss.modules.maven.MavenResolver;
import org.jboss.modules.xml.ModuleXmlParser;
import org.jboss.modules.xml.ModuleXmlParser.ResourceRootFactory;

public interface FCLoaderBasic {

  public void setFCFile(File fc_file); //DONOT Use
  public File getFCFile(); //DONOT Use
  public void setDebugMode(boolean bool);
  public boolean getDebugMode();
  public Path[] getModulePKZipLocations();
  public Path[] getClassPathPKZipLocations();
  
  default Path[] getCombindedModulePKZipLocations() {
    ArrayList < Path > combined = new ArrayList < Path > ();
    for (int m = 0; m < getModulePKZipLocations().length; m++) {
      combined.add(getModulePKZipLocations()[m]);
    }
    for (int c = 0; c < getClassPathPKZipLocations().length; c++) {
      combined.add(getClassPathPKZipLocations()[c]);
    }
    //https://stackoverflow.com/questions/18119494/why-cant-cast-object-to-string#18119737
    return combined.toArray(new Path[getModulePKZipLocations().length + getClassPathPKZipLocations().length]);
  }
  public Set<String> getNeededPackages();
  public
  default ModuleLoader getLoader() {
    return getBootModuleLoader();
  }
  public ArrayList < Module > getModules();
  public ArrayList < Module > getRunModules();

  public void loadMods();
  public void runMods();
  public void runModule(String name);
  public
  default void runModule(ModuleIdentifier id) {
    runModule(id.getName());
  }
  public ModuleLoader getBootModuleLoader();
  public
  default InputStream getModuleXMLFromJarAsInputStream(File location) throws IOException {
    JarFile jar;

    jar = new JarFile(location.toString());
    StringBuilder contentBuilder = new StringBuilder();
    InputStream stream = jar.getInputStream(jar.getJarEntry("module.xml"));
    return stream;

  }
  public
  default String getModuleXMLFromJarAsString(File location) throws IOException {
    String text = new BufferedReader(
        new InputStreamReader(getModuleXMLFromJarAsInputStream(location), StandardCharsets.UTF_8))
      .lines()
      .collect(Collectors.joining("\n"));
    return text;

    // TODO Auto-generated catch block
    //System.out.println(location.toString() + " Likely not a PkZip/Jar File");

  }

  public
  default ModuleSpec getModuleSpecFromXMLJar(File location) throws IOException, ModuleLoadException {

    return ModuleXmlParser.parseModuleXml(ResourceRootFactory.getDefault(), MavenResolver.createDefaultResolver(), location.toString(), getModuleXMLFromJarAsInputStream(location), location.toString(), getLoader(), location.toString());

    // TODO Auto-generated catch block

  }

  public
  default boolean checkIfPKZipHasModuleXML(JarFile location) {
    try {
      InputStream stream = location.getInputStream(location.getJarEntry("module.xml"));
      return true;
    } catch (IOException e) {
      // TODO Auto-generated catch block
      System.out.println(location.toString() + " Likely not a PkZip/Jar File or does not have module.xml, note that module.xml parsing is still new so expect issues");
      return false;
      //	e.printStackTrace();
    }

  }

  public ArrayList < ClassTransformer > getTransformers();
  
  public default ArrayList <File> getRunOnlyFiles(){
	  ArrayList <File> fils = new ArrayList <File> ();
	  for (int m = 0; m < getModulePKZipLocations().length; m++) {
		
		for (int f = 0; f <getModulePKZipLocations()[m].toFile().listFiles().length; f++) {
		  fils.add(getModulePKZipLocations()[m].toFile().listFiles()[f]);
		}
	  }
	  return fils;
  }

 public default ArrayList <File> getClassPathFiles(){
	  ArrayList <File> fils = new ArrayList <File> ();
	  for (int r = 0; r < getClassPathPKZipLocations().length; r++) {
		for (int f = 0; f <getClassPathPKZipLocations()[r].toFile().listFiles().length; f++) {
		  fils.add(getClassPathPKZipLocations()[r].toFile().listFiles()[f]);
		 
		}
	  }
	  return fils;
  }
 
 public default ArrayList <File> getCombinedFiles(){
	  ArrayList <File> fils = new ArrayList <File> ();
	  for (int r = 0; r < getClassPathPKZipLocations().length; r++) {
		for (int f = 0; f <getClassPathPKZipLocations()[r].toFile().listFiles().length; f++)
		  fils.add(getClassPathPKZipLocations()[r].toFile().listFiles()[f]);
	  }
	  for (int m = 0; m < getModulePKZipLocations().length; m++) {
			for (int f = 0; f <getModulePKZipLocations()[m].toFile().listFiles().length; f++)
			  fils.add(getModulePKZipLocations()[m].toFile().listFiles()[f]);
		  }
	  
	  
	  return fils;
 }
 
 
 public void addNeededPackages(String[] packages_needed);
 
 
  
}