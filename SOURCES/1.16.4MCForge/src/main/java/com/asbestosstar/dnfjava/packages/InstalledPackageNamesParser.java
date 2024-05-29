package com.asbestosstar.dnfjava.packages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.jboss.staxmapper.XMLExtendedStreamWriter;
import org.jboss.staxmapper.XMLMapper;

import com.asbestosstar.dnfjava.DnfJava;

public class InstalledPackageNamesParser {

	public static File db;

	
	
	public static void getPackageNames(DnfJava instance) {
		//TODO Auto-generated method stub
		//TODO We need to convert to SQLiteEventually

		
		db = new File(instance.package_database+"/history.xml");
		
	if (!db.exists()) {
		makeNewPackageDatabase(instance);
	}
		//instance.package_database
		
		
	}

	public static void makeNewPackageDatabase(DnfJava instance) {
		// TODO Auto-generated method stub
		instance.package_database.toFile().mkdirs();
		//create db as a blank file
		try {
			db.createNewFile();
			instance.installed_packages.add(new RPMPackage("dnf","1.0",new String[] {},"BaseOS",instance.xmlSpecLocation+"/dnf.spec.xml"));
			instance.installed_packages.add(new RPMPackage("yum","1.0",new String[] {},"BaseOS",instance.xmlSpecLocation+"/yum.spec.xml"));
			instance.installed_packages.add(new RPMPackage("rpm","1.0",new String[] {},"BaseOS",instance.xmlSpecLocation+"/rpm.spec.xml"));
			saveXMLFromPackages(instance);
		} catch (IOException | FactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

 
	
	
	public static void saveXMLFromPackages(DnfJava instance) {
	
		
		XMLMapper xml = XMLMapper.Factory.create();
		//XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(db));
		//xml.parseDocument("packages", reader);

		try {
			XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
			XMLStreamWriter writer = xmlOutputFactory.createXMLStreamWriter(new FileOutputStream(db));			
			writer.writeStartDocument();		
			writer.writeStartDocument("packages");


			for (Package pkg: instance.installed_packages) {
				writer.writeStartElement("package");
				writer.writeStartElement("name");
				writer.writeCharacters(pkg.getName());
				writer.writeEndElement();
			    writer.writeStartElement("repo");
			    writer.writeCharacters(pkg.getRepo());
			    writer.writeEndElement();
			    writer.writeStartElement("dependencies");
			    writer.writeCharacters(String.join(",",pkg.getDependencies()));
			    writer.writeEndElement();
			    writer.writeStartElement("version");
			    writer.writeCharacters(pkg.getVersion());
			    writer.writeEndElement();
			    writer.writeStartElement("xmlSpecLocation");
			    writer.writeCharacters(pkg.getVersion());
			    writer.writeEndElement();
			    writer.writeEndElement();
				
			}


			writer.writeEndDocument();

			writer.flush();
			writer.close();
		} catch (FileNotFoundException | FactoryConfigurationError | XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	
	public static void addPackage(DnfJava instance, Package pack) {
		instance.installed_packages.add(pack);
	}
	
	
}
