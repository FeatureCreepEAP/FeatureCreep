package featurecreep.api.bg.model;

/** Implemented by block entities and entities that draw a featurecreep model. */
public interface ModelHolder {

	ModelSpec modelSpec();

	double animationSeconds(float partialTick);
}
