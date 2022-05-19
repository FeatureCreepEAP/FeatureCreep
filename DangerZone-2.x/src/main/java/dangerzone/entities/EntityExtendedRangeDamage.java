package dangerzone.entities;

/*
 * This code is copyright Richard H. Clark, TheyCallMeDanger, OreSpawn, 2015-2021.
 * You may use this code for reference for modding the DangerZone game program,
 * and are perfectly welcome to cut'n'paste portions for your mod as well.
 * DO NOT USE THIS CODE FOR ANY PURPOSE OTHER THAN MODDING FOR THE DANGERZONE GAME.
 * DO NOT REDISTRIBUTE THIS CODE. 
 * 
 *
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


import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

import dangerzone.DangerZone;
import dangerzone.Explosion;
import dangerzone.Utils;
import dangerzone.World;
import dangerzone.blocks.Block;
import dangerzone.blocks.BlockInstability;
import dangerzone.blocks.Blocks;
import dangerzone.items.Item;
import dangerzone.items.ItemInstability;
import dangerzone.items.Items;



public class EntityExtendedRangeDamage extends Entity {

	public int deathtimer = 60; // a few seconds...
	public Entity thrower = null; //only valid on server-side!
	public double oposx = 0;
	public double oposy = 0;
	public double oposz = 0;
	
	private boolean boomed_once = false;

	public EntityExtendedRangeDamage(World w){
		super(w);
		maxrenderdist = 1; //in blocks
		this.height = 0.01f;
		this.width = 0.01f;
		uniquename = "DangerZone:ExtendedRangeDamage";
		movement_friction = false; //we are traveling in AIR!
		canthitme = true;
		setAttackDamage(0); //damage
		setBID(0); //damage type
		setMaxHealth(0); //explosive power
		setIID(0); //range
		setItemDamage(0); //can destroy items
		setDefense(0); //spread rate
		setGameMode(0); //no fire from explosion! (if != 0)
	}

	public void writeSelf(Properties prop, String tag){
		super.writeSelf(prop, tag);
		prop.setProperty(String.format("%s%s", tag, "DEATHTIMER"), String.format("%d", deathtimer));
	}

	public void readSelf(Properties prop, String tag){
		super.readSelf(prop, tag);
		deathtimer = Utils.getPropertyInt(prop, String.format("%s%s", tag, "DEATHTIMER"), 0, 60, 60);
	}

	public void setDirectionAndVelocity(float x, float y, float z, float velocity, float variability){
		motionx = x*(velocity + ((world.rand.nextFloat()-world.rand.nextFloat())*variability*velocity));
		motiony = y*(velocity + ((world.rand.nextFloat()-world.rand.nextFloat())*variability*velocity));
		motionz = z*(velocity + ((world.rand.nextFloat()-world.rand.nextFloat())*variability*velocity));
	}

	public void setDamageType(int dt){
		setBID(dt); //damagetype
	}
	public void setExplosivePower(int exp){
		setMaxHealth(exp); //explosive power
	}
	public void setRange(int rng){
		setIID(rng); //range
	}	
	public void setSpreadRate(float rate){
		setDefense(rate);
	}
	public void setNoFire(boolean tf){
		if(tf){
			setGameMode(1);
		}else{
			setGameMode(0);
		}
	}
	
	public void update( float deltaT){

		if(this.world.isServer){

			//Check for hitting a block...
			float dist = (float)Math.sqrt(motionx*deltaT*motionx*deltaT + motiony*deltaT*motiony*deltaT + motionz*deltaT*motionz*deltaT);
			float blockdist = 0;
			float edist = 0;
			boolean hitblock = false;
			boolean hitentity = false;
			int x,y,z;
			int lx, ly, lz;
			int bid = 0;
			List<Entity> nearby_list = null;
			ListIterator<Entity> li;
			Entity enthit = null;
			double fx, fy, fz;
			Entity ridden = null; //only valid on server-side!
			float spread = getDefense();
			if(spread < 0)spread = 0;
			if(spread > 1)spread = 1;
			spread /= 2; //save ourselves divide by two a bazillion times later...

			if(oposx == 0){
				oposx = posx;
				oposy = posy;
				oposz = posz;
			}

			//Get a list of entities within reach of largest mob expected because we may hit their hitbox!
			if(getItemDamage() != 0){
				nearby_list = DangerZone.server.entityManager.findALLEntitiesInRange(dist+16f, dimension, posx, posy, posz);
			}else{
				nearby_list = DangerZone.server.entityManager.findEntitiesInRange(dist+16f, dimension, posx, posy, posz);
			}

			lx = ly = lz = 0;

			if(thrower != null){
				ridden = thrower.getRiddenEntity(); //don't hit our mount if we are riding something
				if(ridden == null){
					ridden = thrower.getRiderEntity(); //don't hit our rider
				}
			}
			fx = posx;
			fy = posy;
			fz = posz;

			/*
			 * increment along in the direction we are heading to see if we hit something.
			 * We do this, because we can move more (a lot!) than one block distance in a clock tick!
			 */
			while(blockdist < dist){
				fx = posx + motionx*deltaT*blockdist/dist;
				fy = posy + motiony*deltaT*blockdist/dist;
				fz = posz + motionz*deltaT*blockdist/dist;
				x = (int)fx;
				y = (int)fy;
				z = (int)fz;

				edist = (float) Math.sqrt(((oposx-fx)*(oposx-fx))+((oposy-fy)*(oposy-fy))+((oposz-fz)*(oposz-fz)));
				if(edist > getIID()){
					//ran out of range.
					this.deadflag = true;
					break;
				}

				li = nearby_list.listIterator();
				while(li.hasNext()){
					enthit = (Entity)li.next();
					if(enthit != this && enthit != thrower && enthit != ridden){ //don't hit self or things that shouldn't be hit!
						if(fy > enthit.posy - (spread * blockdist) && fy < enthit.posy+enthit.getHeight()+(spread * blockdist)){
							edist = (float)enthit.getHorizontalDistanceFromEntity(fx, fz); //Center of tip to center of entity
							edist -= getWidth()/2; //width of me
							edist -= (enthit.getWidth()/2); //general hitbox of entity
							edist -= spread * blockdist;
							if(edist < 0){
								if(getItemDamage() == 0){ //normal processing
									if(!enthit.canthitme){
										//Hit something!
										hitentity = true;
										break;
									}
								}else{
									if(enthit instanceof EntityBlockItem){
										//Hit something!
										if(spread == 0){
											hitentity = true; //YES!!! YOU CAN KILL ITEMS AND BLOCKS NOW!!!!
											break;
										}else{
											//kill any items not already dead!
											//notice blockitems DO NOT STOP the projectile...
											if(!enthit.deadflag){
												doHitSomething(hitblock, fx, fy, fz, 0, true, enthit);	
											}
										}
									}else{
										if(enthit instanceof EntityLiving){
											if(!enthit.canthitme){
												//Hit something!
												hitentity = true;
												break;
											}
										}										
									}
								}
							}
						}
					}
				}
				if(hitentity)break;

				//wait until block actually changes
				if(x != lx || y != ly || z != lz){
					lx = x; ly = y; lz = z;
					bid = world.getblock(dimension, x, y, z);
					if(bid != 0){ //Hit a block!!!	
						hitblock = true;
						break;
					}
				}

				blockdist += 0.15f;
			}

			if(hitentity || hitblock){
				//set motion = distance we hit something and stopped.
				motionx = motionx*deltaT*blockdist/dist;
				motiony = motiony*deltaT*blockdist/dist;
				motionz = motionz*deltaT*blockdist/dist;
				doHitSomething(hitblock, fx, fy, fz, bid, hitentity, hitentity?enthit:null);				
				this.deadflag = true;
				return;
			}

			deathtimer--;
			if(deathtimer <= 0){
				this.deadflag = true;
				return;
			}

		}
		super.update( deltaT );
	}


	//override for when we hit something
	public void doHitSomething(boolean hb, double x, double y, double z, int bid, boolean he, Entity ent){
		int nparticle = (int)getAttackDamage();
		if(nparticle < 5)nparticle = 5;
		if(nparticle > 50)nparticle = 50;
		if(he && ent != null){
			if(getAttackDamage() > 0)ent.doAttackFrom(thrower, getBID(), getAttackDamage());			
			if(ent instanceof EntityBlockItem){
				EntityBlockItem ebi = (EntityBlockItem)ent;
				ebi.deadflag = true; //kill it
				Utils.spawnParticlesFromServerScaled(world, "DangerZone:ParticleHurt", nparticle, this.dimension, ent.posx, ent.posy+0.125f, ent.posz, 0, 0.50f);
				if(ebi.getItemID() != 0)Utils.spawnParticlesFromServerScaled(world, "DangerZone:ParticleItem", 25, this.dimension, ent.posx, ent.posy+0.125f, ent.posz, ebi.getItemID(), 0.50f);				
				if(ebi.getBlockID() != 0)Utils.spawnParticlesFromServerScaled(world, "DangerZone:ParticleBreak", 25, this.dimension, ent.posx, ent.posy+0.125f, ent.posz, ebi.getBlockID(), 0.50f);			
				//explode an instability if that is what we hit!
				Item it = Items.getItem(ebi.getItemID());
				if(it != null){
					if(it instanceof ItemInstability){
						ItemInstability iti = (ItemInstability)it;
						Explosion.boom(thrower, world, dimension, x, y, z, (int)iti.mypower, true);
					}
				}

			}else{
				Utils.spawnParticlesFromServerScaled(world, "DangerZone:ParticleHurt", nparticle, this.dimension, x, y, z, 0, 0.25f*(ent.getWidth()+ent.getHeight())/4f);
			}
		}else{
			world.playSound("DangerZone:small_thud", dimension, x, y, z, 0.20f, 1.0f+((world.rand.nextFloat()-world.rand.nextFloat())*0.1f));
			Utils.spawnParticlesFromServerScaled(world, "DangerZone:ParticleDust", nparticle, this.dimension, x, y, z, 0, 0.25f);
			Utils.spawnParticlesFromServerScaled(world, "DangerZone:ParticleBreak", nparticle, this.dimension, x, y, z, bid, 0.25f);
			//explode an instability BLOCK if that is what we hit!
			Block bt = Blocks.getBlock(bid);
			if(bt != null){
				if(bt instanceof BlockInstability){
					BlockInstability iti = (BlockInstability)bt;
					Explosion.boom(thrower, world, dimension, x, y, z, (int)iti.maxdamage, true);
				}
			}
		}
		if(getMaxHealth() > 0){
			if(!boomed_once)Explosion.boom(thrower, world, dimension, x, y, z, (int)getMaxHealth(), true, (getGameMode() == 0));
			boomed_once = true;
		}
	}

}


