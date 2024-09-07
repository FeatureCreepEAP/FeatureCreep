package featurecreep;

import featurecreep.loader.ExecutionSide;
import game.ClientMain;

//I need to eventually make a JBoss Modules wrapper around this
public class FeatureCreepMCInit {


	public static ExecutionSide launch_side = ExecutionSide.CLIENT; // On Server do SERVER, this must be on both
																	// GameProviders when the Server one is made, this
																	// is only for CLIENT or SERVER, not CLIENT_ONLY or
																	// SERVER_ONLY, This should not be called, instead
																	// use @BGSide.getExecutionSide()



//Gotta soon make these the actual ones once i figure out the args correctly

	public static void main(String[] args) {
		System.out.println("Starting Game");
		ClientMain.main(args);
	}

}
