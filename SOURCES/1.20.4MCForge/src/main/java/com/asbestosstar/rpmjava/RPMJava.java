package com.asbestosstar.rpmjava;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.asbestosstar.cpiojava.CpioRpm;
import com.asbestosstar.cpiojava.Main;

import io.kaitai.struct.ByteBufferKaitaiStream;

public class RPMJava {

	
	public static void main(String[] args) {
try {
	System.out.println(args[0]);
	Rpm rpm = Rpm.fromFile(args[0]);
	
	//https://stackoverflow.com/questions/12531579/uncompress-a-gzip-string-in-java
	// With 'gzip' being the compressed buffer
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

	
//	System.out.println(new String(uncompressed));


	File temp = new File(args[1]+"/temp_cpio.cpio");
temp.getParentFile().mkdirs();



//temp.deleteOnExit();
CpioRpm cpio = new CpioRpm(new ByteBufferKaitaiStream(uncompressed));
//CpioRpm.fromFile("/home/rhel/Descargas/filenamezzzutarrpmodc.cpio");

for (CpioRpm.File file:cpio.files()) {
	String file_name = Main.getFileName(file);
	System.out.println(file_name);
	new File(args[1]+file_name).getParentFile().mkdirs();
	if (file.fileData().length != 0) {

	try {
		OutputStream outputStream = new FileOutputStream(args[1]+file_name);
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
	System.out.println("Format should be /path/to.rpm /path/to/output/");
	e.printStackTrace();
}
		
		
	}
	
	
	
}
