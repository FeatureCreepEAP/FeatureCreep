package com.asbestosstar.dnfjava.packages;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.asbestosstar.cpiojava.CpioRpm;
import com.asbestosstar.cpiojava.Main;
import com.asbestosstar.dnfjava.DnfJava;
import com.asbestosstar.rpmjava.Rpm;

import io.kaitai.struct.ByteBufferKaitaiStream;

public class RPMPackage implements Package{

	public String name;
	public String version;
	public String[] dependencies;
	public String repo;
	public String xmlSpecLocation;	
	public Rpm rpm;
	
	public RPMPackage() {}
	public RPMPackage(String name, String version, String[] dependencies, String repo, String xmlSpecLocation) {
		super();
        this.name = name;
        this.version = version;
        this.dependencies = dependencies;
        this.repo = repo;
        this.xmlSpecLocation = xmlSpecLocation;
	}
	
	public RPMPackage(String name, String version, String[] dependencies, String repo, String xmlSpecLocation, Rpm rpm) {
		super();
        this.name = name;
        this.version = version;
        this.dependencies = dependencies;
        this.repo = repo;
        this.xmlSpecLocation = xmlSpecLocation;
	this.rpm=rpm;
	}
	
	public static RPMPackage fromBytes(byte[] bytes, DnfJava instance) {
	Rpm rpm =	new Rpm(new ByteBufferKaitaiStream(bytes));
		return new RPMPackage(rpm.lead().packageName(), String.valueOf(rpm.lead().version().major()) +"."+ String.valueOf(rpm.lead().version().minor()), new String[] {""},instance.xmlSpecLocation+"/"+rpm.lead().packageName()+".xml", "@commandline", rpm);
		
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
		
		if(this.rpm!=null) {
			
			
			try {
				java.io.ByteArrayInputStream bytein = new java.io.ByteArrayInputStream(rpm.payload());
				java.util.zip.GZIPInputStream gzin = new java.util.zip.GZIPInputStream(bytein);
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

				
//			System.out.println(new String(uncompressed));


//		File temp = new File(args[1]+"/temp_cpio.cpio");
//	temp.getParentFile().mkdirs();



//temp.deleteOnExit();
CpioRpm cpio = new CpioRpm(new ByteBufferKaitaiStream(uncompressed));
//CpioRpm.fromFile("/home/rhel/Descargas/filenamezzzutarrpmodc.cpio");

for (CpioRpm.File file:cpio.files()) {
				String file_name = Main.getFileName(file);
				System.out.println(file_name);
String macro_name = instance.replaceMacrosInString(file_name);			
new File(macro_name).getParentFile().mkdirs();	
				
				if (file.fileData().length != 0) {

				try {
					OutputStream outputStream = new FileOutputStream(macro_name);
					outputStream.write(file.fileData());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	

				
				}
				
				}


//Main.main(new String[] {args[1]+"/temp_cpio.cpio",args[1]});
				System.out.println("Done");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		
	}
	
}
