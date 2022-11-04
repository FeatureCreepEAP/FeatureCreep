package featurecreep.loader;

import java.net.URL;
import java.net.URLClassLoader;

public class URLAdder extends URLClassLoader{

	
	//I referenced LoaderComplexs Code for This
	
	  public URLAdder (ClassLoader parent)
	    {
		  super( new URL[] {}, parent);
		  }

	  
	  
	  
	  
	@Override
	public void addURL(URL url) {
		super.addURL(url);
	}

	public Class loadClass(String clazz, Boolean bool) throws ClassNotFoundException
	{
		
		return	super.loadClass(clazz, bool);
		
	}
	
}
