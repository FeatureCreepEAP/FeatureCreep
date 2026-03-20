package featurecreep.api.soundeffects;

@Deprecated(forRemoval = true, since = "13")

public class VanillaSoundEffect implements AbstractSoundEffect {

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