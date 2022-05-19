package dangerzone;

/*
 * This code is copyright Richard H. Clark, TheyCallMeDanger, OreSpawn, 2015-2021.
 * You may use this code for reference for modding the DangerZone game program,
 * and are perfectly welcome to cut'n'paste portions for your mod as well.
 * DO NOT USE THIS CODE FOR ANY PURPOSE OTHER THAN MODDING FOR THE DANGERZONE GAME.
 * DO NOT REDISTRIBUTE THIS CODE. 
 * 
 * WARNING: There are bugs. Big bugs. Little bugs. Every size in-between bugs.
 * This code is NOT suitable for use in anything other than this particular game. 
 * NO GUARANTEES of any sort are given, either express or implied, and Richard H. Clark, 
 * TheyCallMeDanger, OreSpawn are not responsible for any damages, direct, indirect, or otherwise. 
 * You should have made backups. It's your own fault for not making them.
 * 
 * NO ATTEMPT AT SECURITY IS MADE. This code is USE AT YOUR OWN RISK.
 * Regardless of what you may think, the reality is, that the moment you 
 * connected your computer to the Internet, Uncle Sam, among many others, hacked it.
 * DO NOT KEEP VALUABLE INFORMATION ON INTERNET-CONNECTED COMPUTERS.
 * Or your phone...
 * 
 */

public class PIDController {
	public double desiredpos;
	public float pfactor;
	public float ifactor;
	public float dfactor;
	public float scale;
	public float iaccum;
	public float ifade;
	public float maxspeed;
	public float minspeed;
	
	/*software PID controller... ish... seems to work...*/
	public PIDController(float sc, float p, float i, float ifd, float d, float mins, float maxs){
		scale = sc;
		pfactor = p;
		ifactor = i;
		ifade = ifd;
		dfactor = d;
		iaccum = 0;
		minspeed = mins;
		maxspeed = maxs;
	}
	
	public void setHoldpos(double pos){
		desiredpos = pos;
		iaccum = 0;
	}
	
	public float getAdjustment(double currentpos, float currentspeed){
		float posdif = (float)(desiredpos - currentpos);
		float adjust = scale * pfactor * posdif; //P
		float reldist = 1;
		
		iaccum += posdif;
		iaccum *= ifade; 
		adjust += (float) (scale * ifactor * iaccum); //I
		
		if(posdif != 0){ 
			reldist = Math.abs(currentspeed / posdif);
			if(reldist > 1)reldist = 1; //throttle throttle!
		}
		adjust -= scale * dfactor * currentspeed; //D
		
		//System.out.printf("p, i, d == %f, %f, %f\n", scale * pfactor * posdif, (float) (scale * ifactor * iaccum), -(scale * dfactor * currentspeed));
		
		if(currentspeed > maxspeed && adjust > 0)return 0;
		if(currentspeed < minspeed && adjust < 0)return 0;
		
		return adjust;
	}

}
