package dangerzone.threads;

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

import java.io.IOException;
import java.nio.ByteBuffer;

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

import dangerzone.DangerZone;
import dangerzone.NetworkOutputBuffer;


/*
 * Just sits around and writes buffers from the list.
 * Need to have my own buffer-level management.
 * This thread can get stuck while the buffers back up,
 * thus letting the upper threads know to throttle output...
 */
public class NetworkSender implements Runnable {
	

	private NetworkOutputBuffer nb;
		
	public NetworkSender(NetworkOutputBuffer nob){
		nb = nob;
	}
	
	public void run()  {
		ByteBuffer b = null;
		
		while(DangerZone.gameover == 0 && nb.error_happened == false){
			
			nb.lock.lock();
			while(nb.output_list.size() > 0) {
				b = nb.output_list.get(0);
				nb.output_list.remove(0);
				nb.lock.unlock();
				
				
				try {
					nb.out.write(b.array(), 0, b.position());
				} catch (IOException e) {
					nb.error_happened = true;
				}
				
				b.rewind();
				b.position(0);
				nb.lock.lock();
				nb.free_list.add(b);
			}
		
			nb.lock.unlock();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
			
			
		}
	
	}
	


}
