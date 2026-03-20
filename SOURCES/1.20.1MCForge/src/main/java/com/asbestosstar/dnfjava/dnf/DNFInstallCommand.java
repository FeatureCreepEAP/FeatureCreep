package com.asbestosstar.dnfjava.dnf;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import com.asbestosstar.dnfjava.DnfJava;
import com.asbestosstar.dnfjava.packages.RPMPackage;
import com.asbestosstar.dnfjava.packages.YumPackage;

public class DNFInstallCommand {
	
	public ArrayList<String> arguments = new ArrayList<String>();
	public ArrayList<String> packages = new ArrayList<String>();
	
	//Constructor that accetps args
	public DNFInstallCommand(String[] args, DnfJava instance) {
     
		//Write code to sort through all the args and sort them if they have "-" in them
		for(String arg: args) {
			if(arg.startsWith("-")) {
	
				if (arg.startsWith("--")) {
					arguments.add(arg.replace("--", ""));
				}else {
					//Remove the - 
String letters = arg.replace("-", "");
//Split the Sring Letters into an ArrayList of each letter
for (char letter: letters.toCharArray()) {
	//convert letter to a string
arguments.add(String.valueOf(letter));
}

					
				}
					
					
					
			}else {
				//Add packages
				packages.add(arg);
				
				
			}
			
			
			for	(String pack: packages) {
				if(pack.endsWith(".rpm")) {
					byte[] rpmbytes = null;
					if(pack.startsWith("http:")||pack.startsWith("https:")||pack.startsWith("ftp:")||pack.startsWith("ftps:")||pack.startsWith("sftp:")||pack.startsWith("smb:")||pack.startsWith("nfs:")||pack.startsWith("s3:")||pack.startsWith("gsiftp:")||pack.startsWith("gopher:")||pack.startsWith("git:")||pack.startsWith("svn:")||pack.startsWith("freenet:")||pack.startsWith("file:")||pack.startsWith("ssh")) {
						//We still need to parse for .onion sites
					
						 try {
							InputStream in = new BufferedInputStream(new URL(pack).openStream());
							         ByteArrayOutputStream out = new ByteArrayOutputStream() ;
							        
							        byte[] buffer = new byte[1024];
							        int bytesRead;
							        while ((bytesRead = in.read(buffer, 0, buffer.length)) != -1) {
							            out.write(buffer, 0, bytesRead);
							        }
							        rpmbytes = out.toByteArray();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    	        
						 
						
					}else {
						
						try {
							InputStream in = new FileInputStream(new File(pack));
							 ByteArrayOutputStream out = new ByteArrayOutputStream() ;
							
							byte[] buffer = new byte[1024];
							int bytesRead;
							while ((bytesRead = in.read(buffer, 0, buffer.length)) != -1) {
							    out.write(buffer, 0, bytesRead);
							}
							rpmbytes = out.toByteArray();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
					if(rpmbytes!=null) {
					RPMPackage rpm = RPMPackage.fromBytes(rpmbytes, instance);
					rpm.install(instance);
					instance.installed_packages.add(rpm);
					}
				
				
				
				}else {
			YumPackage yum = new YumPackage(pack);
					yum.install(instance);
					instance.installed_packages.add(yum);
					
				}
			}
			
			
		}
		
		
		
		//We will need to rework to account for args later
		
		
		
		
		
    }
	
	

}
