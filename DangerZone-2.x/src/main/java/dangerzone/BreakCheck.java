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

import dangerzone.entities.Entity;

public class BreakCheck {
	
	//extend and override this and register it with BreakChecks!
	//Your version can make whatever checks it wants.
	//Return false, and the block will not be changed.
	public boolean canChangeBlock(Entity e, int d, int x, int y, int z, int tobid, int tometa){
		return true;
	}
	
	public boolean fireDamage(Entity e, int d, int x, int y, int z){
		return true;
	}
	
	//for blocks that spread, like lava
	public boolean canChangeBlock(World w, int od, int ox, int oy, int oz, int d, int x, int y, int z, int tobid, int tometa){
		return true;
	}
	
	//for players accessing chests and things
	public boolean canTakeStuff(Player p, Entity e){
		return true;
	}

}
