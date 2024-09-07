package asbestosstar;

import java.lang.instrument.Instrumentation;

import featurecreep.api.GameInjections;
import featurecreep.unsupported.ModuleRemapper;

public class FCAgent {

	public static void premain(String args, Instrumentation instrumentation) {

		//instrumentation.addTransformer(FCLoaderBasic.fromClassTransformer(new FCClassTransformers()), true);

		if (instrumentation.getClass().getName().equals("sun.instrument.InstrumentationImpl")) {// creo es la manera
																								// mejor
			GameInjections.instrumentation = instrumentation;
	
			if (!ModuleRemapper.completa) {
				ModuleRemapper.remapMods();
				GameInjections.agent_mode=true;
			}
		
		}

		

	}

}
