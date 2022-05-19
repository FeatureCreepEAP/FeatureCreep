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

public class Fastmath {
	
	//because on-the-fly calculations of sin and cos are painful, and we don't need 10 digits of accuracy,
	//make a pre-calculated table that gives us roughly 3-4 digits! Much faster to just look it up!
	
	public static double sintable[] = null;
	
	public static void inittable(){
		double dval = 0;
		double dindex;
		int index;
		sintable = new double [10000];
		
		for(index = 0; index < 10000; index++){
			dindex = index;
			dval = (Math.PI*2d)*(dindex/10000d);
			sintable[index] = Math.sin(dval);
		}
		
	}
	
	public static double sin(double rads){
		double retval = 0;
		int index = 0;
		
		if(sintable == null)inittable();
		
		retval = rads % (Math.PI*2d);
		retval = (retval * 10000d) / (Math.PI*2d);
		index = (int)retval;
		
		if(index < 0)index = 0;
		if(index > 9999)index = 9999;
		retval = sintable[index];
		return retval;
	}
	
	public static double cos(double rads){
		double retval = 0;
		int index = 0;
		
		if(sintable == null)inittable();
		
		retval = (rads+(Math.PI/2d)) % (Math.PI*2d); //cos is just 90 degrees off from sin
		retval = (retval * 10000d) / (Math.PI*2d);
		index = (int)retval;
		
		if(index < 0)index = 0;
		if(index > 9999)index = 9999;
		retval = sintable[index];
		return retval;
	}

}
