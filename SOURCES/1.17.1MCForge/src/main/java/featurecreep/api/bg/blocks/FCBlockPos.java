package featurecreep.api.bg.blocks;

import game.BlockPos;

public class FCBlockPos extends BlockPos{

	public int x;
	public int y;
	public int z;
	
	public FCBlockPos(int x, int y, int z) {
		super(x, y, z);
		// TODO Auto-generated constructor stub
	this.x = x;
	this.y = z;
	this.z = z;

	}
	
	public int getXLocation() {
		return x;
	}
	
	public int getYLocation() {
		return y;
	}
	
	public int getZLocation() {
		return z;
	}
	
	public void setXLocation(int x) {
		this.x = x;
	}
	
	public void setYLocation( int y) {
		this.y = y;
	}
	
	public void setZLocation(int z) {
		this.z = z;
	}
	
}
