import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Install {

  public static void main(String[] args) {

    System.out.println("Please put the full directory of your .minecraft folder with your VanillaLauncher or TLauncher Instance, no quotes, often /home/<user>/.minecraft/");
    Scanner scan = new Scanner(System.in);
    String mcfolder = scan.nextLine();
    System.out.println("Installing to " + mcfolder);

    java.util.jar.JarFile jar = null;
    try {
      jar = new java.util.jar.JarFile(new File(Install.class.getProtectionDomain().getCodeSource().getLocation()
        .toURI()).getPath()); //https://stackoverflow.com/questions/320542/how-to-get-the-path-of-a-running-jar-file#320595
    } catch (IOException | URISyntaxException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    java.util.Enumeration enumEntries = jar.entries();

    while (enumEntries.hasMoreElements()) {
      java.util.jar.JarEntry file = (java.util.jar.JarEntry) enumEntries.nextElement();
      java.io.File f = new java.io.File(mcfolder + java.io.File.separator + file.getName());

      if (f.toString().contains("fc4pre13-1.18.2")) {

        System.out.println(f);


        try {
			java.io.InputStream is = jar.getInputStream(file); // get the input stream
			java.io.FileOutputStream fos = new java.io.FileOutputStream(f);
			while (is.available() > 0) { // write contents of 'is' to 'fos'
			  //if (f.toString().contains("gfx")) {
			  fos.write(is.read());
			  //	System.out.println(file.toString());
			  //}

			}
    fos.close();
			is.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

      } else if (f.toString().contains("json")) {
        java.io.File osf = new java.io.File(mcfolder + java.io.File.separator + "versions" + java.io.File.separator + "FC4pre13-1.18.2" + java.io.File.separator + file.getName());
        System.out.println(osf);


        try {
        	osf.getParentFile().mkdirs();
			java.io.InputStream is = jar.getInputStream(file); // get the input stream
			java.io.FileOutputStream fos = new java.io.FileOutputStream(osf);
			while (is.available() > 0) { // write contents of 'is' to 'fos'
			  //if (f.toString().contains("gfx")) {
			  fos.write(is.read());
			  //	System.out.println(file.toString());
			  //}

			}
			fos.close();
			is.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

      }

    }
    try {
		jar.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

  }

}