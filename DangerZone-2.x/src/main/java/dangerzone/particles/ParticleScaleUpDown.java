package dangerzone.particles;



public class ParticleScaleUpDown extends Particle {
	
	public ParticleScaleUpDown(){
		super();
	}
	
	public void update(float deltaT){
		super.update(deltaT);
		
		float flt = lifetimeticker;
		float mlt = maxlifetime/2;
		renderscale = Math.abs(flt-mlt);
		renderscale = mlt - renderscale;
		renderscale = renderscale / mlt;
		renderscale *= scale;
		if(renderscale < 0.01f)renderscale = 0.01f;
		if(renderscale > 10f)renderscale = 10f;

	}

}
