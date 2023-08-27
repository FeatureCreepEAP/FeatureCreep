package com.asbestosstar.dnfjava.yum;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class YumRepo {

  public String name;
  public URL baseurl;
  public URL metalink;
  public boolean enabled;
  public String gpgkey;
  public boolean gpgcheck;
  public File file;
  public URL preferred;
public URL repodata;
public List<Entry> entries;  

  public YumRepo(String name, URL baseurl, URL metalink, boolean enabled, String gpgkey, boolean gpgcheck, File file) {
   
	this.name = name;
    this.baseurl = baseurl;
    this.metalink = metalink;
    this.enabled = enabled;
    this.gpgkey = gpgkey;
    this.gpgcheck = gpgcheck;
    this.file = file;

    if (metalink != null) {
      this.preferred = metalink;
    } else {
      this.preferred = baseurl;
    }
    
    
    if(gpgcheck==true) {
    
    if(gpgkey.startsWith("file://")) {
    	File gpgfil=new File(gpgkey.replace("file://", ""));
	    this.gpgkey= parseGPGKeyFile(gpgfil);
    }
    }else {
    	this.gpgcheck=false;
    }
    
    if(enabled!=true) {
    this.enabled=false;
    }

    try {
		repodata = new URL(new String(preferred.toString()+"/repodata/"));
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    

    try {
	entries=	parseYumRepoPacks();
	} catch (IOException | XMLStreamException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    
    
  }

  public static YumRepo fromFile(File file) {
    //FileInputStream from file
    try {
      String[] lines = Files.lines(file.toPath())
        .toArray(String[]::new);

      String name = null;
      URL baseurl = null;
      URL metalink = null;
      boolean enabled = false;
      String gpgkey = null;
      boolean gpgcheck = false;

      for (String line: lines) {
        if (line.startsWith("name")) {
          name = line.replace("name=", "");
        }
        if (line.startsWith("gpgkey")) {
          name = line.replace("gpgkey=", "");
        } else if (line.startsWith("baseurl")) {
          baseurl = new URL(line.replace("baseurl=", ""));
        } else if (line.startsWith("metalink")) {
          baseurl = new URL(line.replace("metalink=", ""));
        } else if (line.startsWith("enabled")) {
          if (line.replace("enabled=", "").equals("1")) {
            enabled = true;
          } else {
            enabled = false;
          }

        } else if (line.startsWith("gpgcheck")) {
          if (line.replace("gpgcheck=", "").equals("1")) {
            enabled = true;
          } else {
            enabled = false;
          }

        }

        
        return new YumRepo(name,baseurl,metalink,enabled,gpgkey,gpgcheck,file);
        
        
        
        
      }

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return null;

  }
  
  public static String parseGPGKeyFile(File file) {
	  
	  
	  
	  try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
          StringBuilder keyContents = new StringBuilder();
          String line;
          while ((line = reader.readLine()) != null) {
              if ((!line.trim().isEmpty()) &&
                  (!line.trim().equals("-----BEGIN PGP PUBLIC KEY BLOCK-----")) &&
                  (!line.trim().equals("-----END PGP PUBLIC KEY BLOCK-----"))) {
                //  keyContents.append(line).append('\n');
              }
          }
          return keyContents.toString();
      } catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  return null;
	  
	  
	  
	  
  }
  
  
  
  public byte[] downloadPackage(String package_name,String min_version) throws IOException {
	  
	 // int min_version_int = Integer.parseInt(min_version);
	  String latest_version = findLatestVersion(package_name);
	  
	  if(min_version.compareTo(latest_version) == 0 || min_version.compareTo(latest_version) == -1) {
	  for(Entry entry:entries) {
		  
		  if(entry.name.equals(package_name) && entry.versionRelease.equals(latest_version)) {
			  
			  URL url = new URL(preferred.toString()+"/"+entry.location);
			  InputStream in = new BufferedInputStream(url.openStream());
			  //return byte[] from in

			  java.io.ByteArrayOutputStream byteout = new java.io.ByteArrayOutputStream();

				int res = 0;
				byte buf[] = new byte[1024];
				while (res >= 0) {
				    res = in.read(buf, 0, buf.length);
				    if (res > 0) {
				        byteout.write(buf, 0, res);
				    }
				}
				byte uncompressed[] = byteout.toByteArray();
			  
			  return uncompressed;
			  
		  }
		  
		  
	  }
	  }
	  return null;
  }

public String findLatestVersion(String package_name) {
	// TODO Auto-generated method stub

	ArrayList<String> versions = new ArrayList<String>();
	
	for(Entry entry: entries) {
		if(entry.name.equals(package_name)) {
	versions.add(entry.versionRelease);		
		}
		
	}
	
	String latest=null;
	for(int i=0;i<versions.size();i++) {
if(latest==null) {
	latest=versions.get(i);
}else {
	
	int result = latest.compareTo(versions.get(i));

    if (result < 0) {
latest=versions.get(i);
    } 
	
	
}

		
		}
	

return latest;
}
	
	
   

  
  
  
    public byte[] getRepoDataXMLByte() {
    	 URL url = this.getRepoDataXML();
    	    
    	    try (InputStream in = new BufferedInputStream(url.openStream());
    	         ByteArrayOutputStream out = new ByteArrayOutputStream()) {
    	        
    	        byte[] buffer = new byte[1024];
    	        int bytesRead;
    	        while ((bytesRead = in.read(buffer, 0, buffer.length)) != -1) {
    	            out.write(buffer, 0, bytesRead);
    	        }
    	        byte[] bytes = out.toByteArray();
    	        //bytes is gz make it not gz
    	    	java.util.zip.GZIPInputStream gzin = new java.util.zip.GZIPInputStream(in);
    	    	java.io.ByteArrayOutputStream byteout = new java.io.ByteArrayOutputStream();

    	    	int res = 0;
    	    	byte buf[] = new byte[1024];
    	    	while (res >= 0) {
    	    	    res = gzin.read(buf, 0, buf.length);
    	    	    if (res > 0) {
    	    	        byteout.write(buf, 0, res);
    	    	    }
    	    	}
    	    	byte uncompressed[] = byteout.toByteArray();

    	        
    	        
    	        return uncompressed;
    	        
    		
    		
    		
    		
    		
    		
    		
    	
    	    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return null;

    }
    
    
  
public URL getRepoDataXML() {
    try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(repodata.openStream()));

        String line;
        Pattern filePattern = Pattern.compile("<a href=\"([^\"]+)\"");
        while ((line = reader.readLine()) != null) {
            Matcher matcher = filePattern.matcher(line);
            while (matcher.find()) {
                String fileName = matcher.group(1);
                if (fileName.endsWith("-primary.xml.gz")) {
                    String xmlUrlString = repodata + fileName;
                    URL xmlUrl = new URL(xmlUrlString);
                    System.out.println("XML URL: " + xmlUrl);
                    reader.close();
                    return xmlUrl;
                }
            }
        }
        
        reader.close();
        System.out.println("No XML file found.");
    } catch (IOException e) {
        e.printStackTrace();
    }

return null;
}  






public  List<Entry> parseYumRepoPacks() throws IOException, XMLStreamException {
  
	
	List<Entry> entries = new ArrayList<Entry>();
        try {
			 entries = parseYumRepoXML(getRepoDataXMLByte());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		return entries;

}







private List<Entry> parseYumRepoXML(byte[] repoDataXMLByte) {
	// TODO Auto-generated method stub
	
	 try {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		 DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		InputStream repoDataXML = new ByteArrayInputStream(repoDataXMLByte);
		 
		 Document doc = dBuilder.parse(repoDataXML);

		 // Normalize the document
		 doc.getDocumentElement().normalize();

		 // Find the package nodes
		 NodeList packageList = doc.getElementsByTagName("package");

		 // Iterate over each package
		 for (int i = 0; i < packageList.getLength(); i++) {
		     Node packageNode = packageList.item(i);
		     if (packageNode.getNodeType() == Node.ELEMENT_NODE) {
		         Element packageElement = (Element) packageNode;

		         // Extract required information
		         String packageName = getTextContent(packageElement, "name");
		         String versionRelease = getVersionRelease(packageElement);
		         String arch = getTextContent(packageElement, "arch");
		         String summary = getTextContent(packageElement, "summary");
		         List<String> requires = getRequires(packageElement);
		         List<String> fileList = getFileList(packageElement);
String location = getLocation(packageElement);
		         
		         // Print the extracted information
		         System.out.println("Package Name: " + packageName);
		         System.out.println("Version and Release: " + versionRelease);
		         System.out.println("Arch: " + arch);
		         System.out.println("Summary: " + summary);
		         System.out.println("Requires: " + requires);
		         System.out.println("File List: " + fileList);

		         Entry entry = new Entry(packageName, arch, versionRelease, summary, requires, fileList, location);
		         this.entries.add(entry);
		         // Retrieve other required information
		         // ...

		         System.out.println("==============");
		     }
		 
 }
	} catch (ParserConfigurationException | SAXException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	 return entries;

     
     
     
}
	

public static String getTextContent(Element element, String tagName) {
    NodeList nodeList = element.getElementsByTagName(tagName).item(0).getChildNodes();
    Node node = nodeList.item(0);
    return node.getNodeValue();
}
public static String getLocation(Element element) {
    String location = element.getElementsByTagName("location").item(0).getAttributes().getNamedItem("href").getNodeValue();
    return location;
}

public static String getVersionRelease(Element element) {
    String version = element.getElementsByTagName("version").item(0).getAttributes().getNamedItem("ver").getNodeValue();
    String release = element.getElementsByTagName("version").item(0).getAttributes().getNamedItem("rel").getNodeValue();
    return version + "-" + release;
}

public static List<String> getRequires(Element element) {
    List<String> requires = new ArrayList<>();
    NodeList requiresList = element.getElementsByTagName("rpm:requires").item(0).getChildNodes();
    for (int i = 0; i < requiresList.getLength(); i++) {
        Node requiresNode = requiresList.item(i);
        if (requiresNode.getNodeType() == Node.ELEMENT_NODE) {
            Element requiresElement = (Element) requiresNode;
            requires.add(requiresElement.getAttribute("name"));
        }
    }
    return requires;
}


public static List<String> getFileList(Element element) {
    List<String> fileList = new ArrayList<>();

    NodeList filesList = element.getElementsByTagName("format");
    for (int i = 0; i < filesList.getLength(); i++) {
        Node formatNode = filesList.item(i);
        if (formatNode.getNodeType() == Node.ELEMENT_NODE) {
            Element formatElement = (Element) formatNode;
            
            NodeList fileNodes = formatElement.getElementsByTagName("file");
            for (int j = 0; j < fileNodes.getLength(); j++) {
                Node fileNode = fileNodes.item(j);
                if (fileNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element fileElement = (Element) fileNode;
                    fileList.add(fileElement.getTextContent());
                }
            }
        }
    }

    return fileList;
}






public static class Entry {
    
	 public String pkgid;
     public String name;
     public String arch;
     public String versionRelease;
     public List<String> requires = new ArrayList<String>();
     public List<String> files = new ArrayList<String>();
     public String location;

     public Entry(String pkgid, String name, String arch, String versionRelease, String summary, List<String> requires2, List<String> fileList, String location) {
         this.pkgid = pkgid;
         this.name = name;
         this.arch = arch;
         this.versionRelease = versionRelease;
     this.requires=requires;
     this.files=files;
this.location=location;         
     }
	
     public Entry(String name, String arch, String versionRelease,String summary, List<String> requires2, List<String> fileList,String location) {
         //this.pkgid = pkgid;
         this.name = name;
         this.arch = arch;
         this.versionRelease = versionRelease;
     this.requires=requires2;
     this.files=fileList;
     this.location=location;         

         
     }
	
}

  
  

}