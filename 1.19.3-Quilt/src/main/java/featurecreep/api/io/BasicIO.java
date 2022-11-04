package featurecreep.api.io;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class BasicIO {

	

	//https://www.javatpoint.com/new-line-in-java
	public static String getNewLineSeperator()
	{return System.getProperty("line.separator");  }
	
	
	//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/#2-using-fileslines-java-8
	public static String getFileContents(File file)
	{
		
		URI filePath = file.toURI();
		StringBuilder contentBuilder = new StringBuilder();

		try (Stream<String> stream 
		  = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8))
		{
		  //Read the content with Stream
		  stream.forEach(s -> contentBuilder.append(s).append(getNewLineSeperator()));
		}
		catch (IOException e)
		{
		  e.printStackTrace();
		}

		String fileContent = contentBuilder.toString();

	
	return fileContent;
	}
	
	
	
	
	//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/#2-using-fileslines-java-8
	public static String getFileContentsOneLine(File file)
	{
		
		URI filePath = file.toURI();
		StringBuilder contentBuilder = new StringBuilder();

		try (Stream<String> stream 
		  = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8))
		{
		  //Read the content with Stream
		  stream.forEach(s -> contentBuilder.append(s));
		}
		catch (IOException e)
		{
		  e.printStackTrace();
		}

		String fileContent = contentBuilder.toString();

	
	return fileContent;
	}
	
	
	
	
	
}
