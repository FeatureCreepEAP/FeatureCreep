/**
 * 
 */
package com.asbestosstar.cpiojava;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author rhel & generalmimon, GreyCat, davidhicks, Kaitai Struct authors
 *
 */
public class Main {

	/**
	 * @param args, arg0 = cpio, arg1=outputdir
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CpioOldLe cpio;
		try {
		cpio =	CpioOldLe.fromFile(args[0]);
	for (CpioOldLe.File file:cpio.files()) {
		String file_name = getFileName(file);
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
		
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Format should be: file output_dir");
			e.printStackTrace();
		}
	
		
		
		
	}
	
	
	
	
	public static String getFileName(CpioOldLe.File file) {
		return new String(file.pathName());
	}
	
	public static String getFileName(CpioRpm.File file) {
		return new String(file.pathName());
	}
	

}
