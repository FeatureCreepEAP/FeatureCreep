package featurecreep.api.bg;

import featurecreep.FeatureCreep;
import featurecreep.loader.eventviewer.events.MonoAwareEvent;
import featurecreep.loader.eventviewer.listeners.IncompatibleEventListenerMethodReference;
import game.BiomeGenerationSettings;

public class BlockGameVersionDependentEvents {

	public static MonoAwareEvent<BiomeGenerationSettings.Builder, Void> BiomeDecoratorGroupingsAddMineables;//Name is BiomeDecoratorGroupingsAddMineables
	

	//Called By the Coremod in GameInjections for BiomeDecoratorGroupings
	@SuppressWarnings("unchecked")
	public static void biomeDecoratorGroupingsAddMineables(BiomeGenerationSettings.Builder p_126811_) {
		if (BiomeDecoratorGroupingsAddMineables == null) {
			BlockGameVersionDependentEvents.BiomeDecoratorGroupingsAddMineables = (MonoAwareEvent<BiomeGenerationSettings.Builder, Void>) FeatureCreep.loader
					.getEventViewer().addEvent("BiomeDecoratorGroupingsAddMineables", p_126811_);
		}
		try {
			BlockGameVersionDependentEvents.BiomeDecoratorGroupingsAddMineables.invoke();
		} catch (IncompatibleEventListenerMethodReference e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	

}
