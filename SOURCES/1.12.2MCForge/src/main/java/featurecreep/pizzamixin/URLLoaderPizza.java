package featurecreep.pizzamixin;

import java.net.URL;
import java.net.URLClassLoader;

public class URLLoaderPizza extends URLClassLoader{
	
	//I referenced LoaderComplexs Code for This
	
	  public URLLoaderPizza (ClassLoader parent)
	    {
		  super( new URL[] {}, parent);
		}
	  public URLLoaderPizza ()
	    {
		  super( new URL[] {});
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


