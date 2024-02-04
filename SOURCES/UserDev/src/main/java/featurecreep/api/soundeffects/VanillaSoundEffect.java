package featurecreep.api.soundeffects;

public class VanillaSoundEffect implements AbstractSoundEffect{

	public String event;
	
	public VanillaSoundEffect(String event) {
		this.event = event;
	}
	
	
	@Override
	public String get() {
		// TODO Auto-generated method stub
		return event;
	}

}
