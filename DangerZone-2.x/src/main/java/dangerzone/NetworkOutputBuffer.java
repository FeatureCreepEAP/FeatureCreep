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

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import dangerzone.threads.NetworkSender;


public class NetworkOutputBuffer {
	public int currentlen = 0;
	public ByteBuffer currbuffer = null;
	public int maxlen = 2500;
	public int maxlist = 1024;
	public OutputStream out = null;
	public boolean error_happened = false;
	public float avgfill = 10f;	
	
	public Lock lock = new ReentrantLock();
	public List<ByteBuffer> output_list;
	public List<ByteBuffer> free_list;
	private NetworkSender ns = null;
	
	public NetworkOutputBuffer(OutputStream inout, int howmany){
		out = inout;
	
		output_list = new ArrayList<ByteBuffer>();
		free_list = new ArrayList<ByteBuffer>();
		
		maxlist = howmany;
		
		//preallocate a few
		for(int i=0;i<maxlist;i++) {
			currbuffer = ByteBuffer.allocate(maxlen+200);	//a little extra...	
			currbuffer.rewind();
			currbuffer.position(0);
			free_list.add(currbuffer);
		}

		
		//one to start with
		currbuffer = ByteBuffer.allocate(maxlen+200);	//a little extra...	
		currbuffer.rewind();
		currbuffer.position(0);
		
		//start up our sender thread.
		ns = new NetworkSender(this);
		Thread it = new Thread(ns);	//Fire it up!
		//it.setPriority(Thread.MAX_PRIORITY); //get the data on the line.
		it.start();	
	}
	
	public void close(){
		error_happened = true; //will kill NetworkSender
		ns = null; //clear reference
	}
	
	public float update_pct_full() {
		lock.lock();
		float lenf = output_list.size();
		float maxlenf = maxlist;
		lock.unlock();
		float pctf = (lenf/maxlenf)*100f;
		
		avgfill = ((avgfill*9f)+pctf)/10f;		
		return avgfill;
	}
	
	public boolean errorOccurred(){
		return error_happened;
	}
	
	public void flush(){
		if(error_happened)return;
		if(currentlen > 0){
			lock.lock();
			output_list.add(currbuffer);

			//FULL! wait for list to clear out a little.
			while(free_list.size() <= 0) {
				lock.unlock();
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					//e.printStackTrace();
				}
				lock.lock();
			}
			currbuffer = free_list.get(0);
			currbuffer = free_list.remove(0);
			currbuffer.rewind();
			currbuffer.position(0);
			currentlen = 0;
			
			lock.unlock();
		}
	}
	
	public void writeInt(int in){
		if(error_happened)return;
		if(maxlen-currentlen < 4){
			flush();
		}
		currbuffer.putInt(in);
		currentlen += 4;
	}
	
	public void writeShort(short in){
		if(error_happened)return;
		if(maxlen-currentlen < 2){
			flush();
		}
		currbuffer.putShort(in);
		currentlen += 2;
	}
	
	public void writeByte(byte in){
		if(error_happened)return;
		if(maxlen-currentlen < 1){
			flush();
		}
		currbuffer.put(in);
		currentlen += 1;
	}
	
	public void writeFloat(float in){
		if(error_happened)return;
		if(maxlen-currentlen < 4){
			flush();
		}
		currbuffer.putFloat(in);
		currentlen += 4;
	}
	
	public void writeDouble(double in){
		if(error_happened)return;
		if(maxlen-currentlen < 8){
			flush();
		}
		currbuffer.putDouble(in);
		currentlen += 8;
	}
	
	public void writeLong(long in){
		if(error_happened)return;
		if(maxlen-currentlen < 8){
			flush();
		}
		currbuffer.putLong(in);
		currentlen += 8;
	}
	
	public void writeString(String in){
		if(error_happened)return;
		int len = 0;
		byte [] stringbytes = null;
		
		if(in != null){
			try {
				stringbytes = in.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				stringbytes = in.getBytes();
			}
			len = stringbytes.length;
		}
		writeInt(len);
		
		if(maxlen-currentlen < len){
			flush();
		}
		if(len > 0){
			currbuffer.put(stringbytes, 0, len);
		}
		
		currentlen += len;
	}
	
	public void writeShortArray(short sh[], int len){
		if(error_happened)return;
		writeInt(len);
		if(error_happened)return;
		if(len > 0){
			if(maxlen-currentlen < 2){
				flush();
				if(error_happened)return;
			}
			for(int i=0;i<len;i++){
				currbuffer.putShort(sh[i]);
				currentlen += 2;
				if(maxlen-currentlen < 2){
					flush();
					if(error_happened)return;
				}
			}
			
		}
	}
	
	public void writeByteArray(byte sh[], int len){
		if(error_happened)return;
		writeInt(len);
		if(error_happened)return;
		if(len > 0){
			
			//if it is a big array, clear out the buffer first.
			if(maxlen-currentlen < 100){
				flush();
				if(error_happened)return;
			}			
			
			int bytes_to_write = 0;
			int bytes_written = 0;
			
			while(bytes_written < len) {
				bytes_to_write = len-bytes_written;
				if(bytes_to_write > maxlen-currentlen)bytes_to_write = maxlen-currentlen; //only as much as will fit
				currbuffer.put(sh, bytes_written, bytes_to_write);				
				bytes_written += bytes_to_write;
				currentlen += bytes_to_write;
				//flush and start over!
				flush();
				if(error_happened)return;
			}
		}
	}

}
