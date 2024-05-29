package com.asbestosstar.dnfjava.packages;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.asbestosstar.dnfjava.DnfJava;
import com.asbestosstar.dnfjava.yum.YumRepo;

public class YumPackage implements Package{

	public String name;
	public String version;
	public String[] dependencies;
	public String repo;
	public String xmlSpecLocation;
	
	
	public YumPackage(String name) {
		this.name=name;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}
	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return version;
	}
	@Override
	public void setVersion(String version) {
		// TODO Auto-generated method stub
		this.setVersion(version);
	}
	@Override
	public String[] getDependencies() {
		// TODO Auto-generated method stub
		return dependencies;
	}
	@Override
	public void setDependencies(String[] dependencies) {
		// TODO Auto-generated method stub
		this.setDependencies(dependencies);
	}
	@Override
	public String getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}
	@Override
	public void setRepo(String repo) {
		// TODO Auto-generated method stub
	this.setRepo(repo);	
	}
	@Override
	public String getXMLSpecLocation() {
		// TODO Auto-generated method stub
		return xmlSpecLocation;
	}
	@Override
	public void setXMLSpecLocation(String xmlSpecLocation) {
		// TODO Auto-generated method stub
	this.xmlSpecLocation = xmlSpecLocation;	
	}
	@Override
	public void install(DnfJava instance) {
		// TODO Auto-generated method stub
	String latest = null;
	YumRepo rep = null;	
	for(YumRepo repo:instance.repos) {
		String new_latest = repo.findLatestVersion(name);
		if(latest!= null) {
			int result = latest.compareTo(new_latest);

		    if (result < 0) {
		latest=new_latest;
rep=repo;
		    } 
		}else {
			latest=new_latest;
		rep=repo;
		}
		
		
	}
	if(latest!= null) {
        try {
			
        	RPMPackage rpm =	RPMPackage.fromBytes(rep.downloadPackage(name, latest), instance);
       this.setDependencies(null);
this.setRepo(rpm.repo);
this.setVersion(rpm.version);
this.setXMLSpecLocation(rpm.xmlSpecLocation);
        	rpm.install(instance);
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }else {
    	System.out.println("Could not find "+name);
    }
	
		
		
		
	}
	
	 public static byte[] download(URL url) {
		    try {
		      URLConnection connection = url.openConnection();
		      // Download the file and convert it to a byte array
		      try (InputStream inputStream = connection.getInputStream(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
		        byte[] buffer = new byte[1024];
		        int length;
		        while ((length = inputStream.read(buffer)) != -1) {
		          outputStream.write(buffer, 0, length);
		        }
		        byte[] fileContents = outputStream.toByteArray();
		        return fileContents;
		        // Do something with the file contents
		      }
		    } catch (IOException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		    }
		    return null;
		  }
	
	
}
