package featurecreep.api;

import java.io.IOException;

import javassist.CannotCompileException;
import javassist.NotFoundException;

public class GameInjections {

	public static void inject()
	{
		try {
			GameOptionsInjection();
			TitleScreenInjection();
		} catch (NotFoundException | CannotCompileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void GameOptionsInjection() throws NotFoundException, CannotCompileException, IOException
	{

	}
	

	public static void TitleScreenInjection() throws NotFoundException, CannotCompileException, IOException
	{
		

	}
	
	
	
	
}
