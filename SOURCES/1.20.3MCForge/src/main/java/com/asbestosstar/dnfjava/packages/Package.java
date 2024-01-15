package com.asbestosstar.dnfjava.packages;

import javax.xml.stream.XMLStreamException;

import org.jboss.staxmapper.XMLElementReader;
import org.jboss.staxmapper.XMLExtendedStreamReader;

import com.asbestosstar.dnfjava.DnfJava;

public interface Package extends XMLElementReader<Package>{


	
	

    @Override
    public default void readElement(XMLExtendedStreamReader xmlExtendedStreamReader, Package myObject) throws XMLStreamException {
        // Read XML elements and map them to Java object properties
    	while (xmlExtendedStreamReader.hasNext()) {
            xmlExtendedStreamReader.next();
            if (xmlExtendedStreamReader.isStartElement()) {
                String elementName = xmlExtendedStreamReader.getLocalName();
                switch (elementName) {
                    case "name":
                    	myObject.setName(xmlExtendedStreamReader.getElementText());
                        break;
                    case "version":
                        myObject.setVersion(xmlExtendedStreamReader.getElementText());
                        break;
                    case "dependencies":
                        myObject.setDependencies(xmlExtendedStreamReader.getElementText().split(","));
                        break;
                    case "repo":
                        myObject.setRepo(xmlExtendedStreamReader.getElementText());
                        break;
                    // Add more cases for other elements and their corresponding properties
                }
            }
        }
    }



public String getName();
public void setName(String name);
public String getVersion();
public void setVersion(String version);
public String[] getDependencies();
public void setDependencies(String[] dependencies);
public String getRepo();
public void setRepo(String repo);
public String getXMLSpecLocation();
public void setXMLSpecLocation(String xmlSpecLocation);


public void install(DnfJava instance);


}

	
	
	

