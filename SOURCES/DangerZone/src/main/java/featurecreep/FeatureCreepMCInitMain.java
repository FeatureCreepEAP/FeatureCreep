package featurecreep;

import org.jboss.logging.Logger;

import dangerzone.BaseMod;
import dangerzone.DangerZone;

public class FeatureCreepMCInitMain extends BaseMod {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = Logger.getLogger("FeatureCreep");

	public FeatureCreepMCInitMain() {
		super();
		LOGGER.info("Loading FeatureCreep Initialisation Class");

		// FeatureCreep.onInitialise();
	}

	public String getModName() {
		return "FeatureCreepDZ 4.0ESR";
	}

	public String versionBuiltWith() {
		return DangerZone.versionstring;
		// :troll:
	}

	public void registerThings() {
		// We need some content in the world!
		FeatureCreep.onInitialise();

	}

}
