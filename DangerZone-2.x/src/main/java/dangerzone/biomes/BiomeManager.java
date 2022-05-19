package dangerzone.biomes;

import dangerzone.Chunk;
import dangerzone.CreatureTypes;
import dangerzone.DangerZone;
import dangerzone.World;
import dangerzone.threads.SpawnerThread;

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


public class BiomeManager {
	
	public Biome biomes[];
	public static final int max_biomes = 64;
	public int nregistered = 0;
	
	public BiomeManager(){
		int i;
		biomes = new Biome[max_biomes];
		for(i=0;i<max_biomes;i++)biomes[i] = null;		
	}
	
	public int registerBiome(Biome b){		
		int i;
		for(i=0;i<max_biomes;i++){
			if(biomes[i]==null)break;
		}
		if(i<0 || i>=max_biomes)return -1;
		biomes[i] = b;
		nregistered++;
		return i;
	}
	
	//For each biome, calculate it's value,
	//then just pick the highest...
	public int getWhichBiome(int x, int y, int z){
		if(nregistered < 2)return 0;		
		int highest = 0;
		float highval = -999f;
		float curval = -999f;
		for(int i=0;i<nregistered;i++){
			curval = getValueForBiome(i, x, y, z);
			//System.out.printf("i, curval == %d, %f\n", i, curval);
			if(curval > highval){
				highval = curval;
				highest = i;
			}
		}
		return highest;
	}
	
	public float getValueForBiome(int bionumber, int x, int y, int z){
		return biomes[bionumber].getValueForBiome(x, y, z);
	}
	
	public Biome getBiomeForChunk(Chunk c, int d, int x, int y, int z){
		if(nregistered == 0)return null;
		if(nregistered == 1)return biomes[0];
		
		//Save ourselves some work and see if the chunk remembers its biome!
		if(c != null && c.mybiome != null)return c.mybiome;
		
		//Save ourselves some work and see if the chunk remembers its biome!
		Chunk t = null;
		if(c == null){
			t = DangerZone.server_chunk_cache.getChunk(null, d, x, y, z);
			if(t != null){
				if(t.mybiome != null)return t.mybiome;
			}
		}

		return biomes[getWhichBiome(x, y, z)];
	}
	
	//Kind of meh, but gets (most of? some of?) the job done...
	//TODO - save raw height maps in chunk so we don't re-calculate them!!!
	//TODO - save raw height maps in chunk so we don't re-calculate them!!!
	//TODO - save raw height maps in chunk so we don't re-calculate them!!!
	//TODO - save raw height maps in chunk so we don't re-calculate them!!!
	//TODO - save raw height maps in chunk so we don't re-calculate them!!!
	/*
	 * Would like to eventually merge/scale biomes instead of average.
	 */
	public void generate(World w, int d, Biome b, Chunk c, int cx, int cz){
		int dirtlevelmap[][] = new int[16][16];
		int stonelevelmap[][] = new int[16][16];
		int bottomlevelmap[][] = new int[16][16];
		
		if(nregistered == 1){ 
			//easy - only one height generator
			b.generateheightmaps( w,  d,  c,  cx,  cz, dirtlevelmap, stonelevelmap, bottomlevelmap);		
			b.generate( w,  d,  c,  cx,  cz, dirtlevelmap, stonelevelmap, bottomlevelmap);
			return;
		}
		
		//build our starting height maps
		b.generateheightmaps( w,  d,  c,  cx,  cz, dirtlevelmap, stonelevelmap, bottomlevelmap);	
		
		//merge height differences between biomes
		//works good enough...
		//
		int i, j;
		int m, n;
		int mergedist = 1; //always 1 for now, until TODO above is fixed!!!
		int smoothdist = 5;
		
		//if all around us are the same biome, we don't need to do anything!
		Boolean doit = false;
		for(i=-mergedist;i<=mergedist;i++){
			for(j=-mergedist;j<=mergedist;j++){
				if(i==0&&j==0)continue;
				Biome tb = w.getBiome(d, (cx+i)<<4, 0, (cz+j)<<4);
				if(tb == null){
					doit = true;
					break;
				}
				if(!tb.uniquename.equals(b.uniquename)){
					doit = true;
				}
			}
		}
		
		if(doit){
			int dirtarray[][] = new int[16*((2*mergedist)+1)][16*((2*mergedist)+1)];
			int stonearray[][] = new int[16*((2*mergedist)+1)][16*((2*mergedist)+1)];
			int bottomarray[][] = new int[16*((2*mergedist)+1)][16*((2*mergedist)+1)];
			int tdirt[][] = new int[16][16];
			int tstone[][] = new int[16][16];
			int tbottom[][] = new int[16][16];
			
			//copy ourselves in!
			for(i=0;i<16;i++){
				for(j=0;j<16;j++){
					dirtarray[i+(16*mergedist)][j+(16*mergedist)] = dirtlevelmap[i][j];
					stonearray[i+(16*mergedist)][j+(16*mergedist)] = stonelevelmap[i][j];
					bottomarray[i+(16*mergedist)][j+(16*mergedist)] = bottomlevelmap[i][j];					
				}
			}
			
			//generate and copy into large array
			for(i=-mergedist;i<=mergedist;i++){
				for(j=-mergedist;j<=mergedist;j++){
					if(i==0&&j==0)continue;
					Biome tb = w.getBiome(d, (cx+i)<<4, 0, (cz+j)<<4);
					if(tb == null){
						for(m=0;m<16;m++){
							for(n=0;n<16;n++){
								dirtarray[m+((i+mergedist)*16)][n+((j+mergedist)*16)] = 0;
								stonearray[m+((i+mergedist)*16)][n+((j+mergedist)*16)] = 0;
								bottomarray[m+((i+mergedist)*16)][n+((j+mergedist)*16)] = 0;					
							}
						}
						continue;
					}
					tb.generateheightmaps( w,  d,  null,  cx+i,  cz+j, tdirt, tstone, tbottom);	
					for(m=0;m<16;m++){
						for(n=0;n<16;n++){
							dirtarray[m+((i+mergedist)*16)][n+((j+mergedist)*16)] = tdirt[m][n];
							stonearray[m+((i+mergedist)*16)][n+((j+mergedist)*16)] = tstone[m][n];
							bottomarray[m+((i+mergedist)*16)][n+((j+mergedist)*16)] = tbottom[m][n];					
						}
					}
				}
			}
			
			//clear
			for(i=0;i<16;i++){
				for(j=0;j<16;j++){
					dirtlevelmap[i][j] = 0;
					stonelevelmap[i][j] = 0;
					bottomlevelmap[i][j] = 0;					
				}
			}
			
			//accumulate
			for(i=0;i<16;i++){
				for(j=0;j<16;j++){
					//smoother
					for(m=-(smoothdist);m<=(smoothdist);m++){
						for(n=-(smoothdist);n<=(smoothdist);n++){
							dirtlevelmap[i][j] += dirtarray[i+(mergedist*16)+m][j+(mergedist*16)+n];
							stonelevelmap[i][j] += stonearray[i+(mergedist*16)+m][j+(mergedist*16)+n];
							bottomlevelmap[i][j] += bottomarray[i+(mergedist*16)+m][j+(mergedist*16)+n];	
						}
					}				
				}
			}
			
			//divide
			for(i=0;i<16;i++){
				for(j=0;j<16;j++){
					dirtlevelmap[i][j] /= (((smoothdist*2)+1)*((smoothdist*2)+1)) ;
					stonelevelmap[i][j] /= (((smoothdist*2)+1)*((smoothdist*2)+1));
					bottomlevelmap[i][j] /= (((smoothdist*2)+1)*((smoothdist*2)+1));					
				}
			}
		}
		
		b.generate( w,  d,  c,  cx,  cz, dirtlevelmap, stonelevelmap, bottomlevelmap);
			
			
	}
	
	//callback from chunk to finish decorating and populate
	public void decorate(World w, int d, Chunk c, int x, int y, int z){
		if(c.mybiome == null)c.mybiome = w.getBiome(d, x, y, z);
		if(c.mybiome != null){
			//world decorator first, because large underground and surface structures... caves and things!
			w.decorate(w, d, c.mybiome, x>>4, z>>4);
			c.mybiome.decorate(w, d, c, x>>4, z>>4);
		}
		SpawnerThread.doSpawnChunk(c, w, d, x, z, CreatureTypes.PERMANENT); //spawn some real critters!
	}
		

}
