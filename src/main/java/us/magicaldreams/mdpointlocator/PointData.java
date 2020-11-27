package us.magicaldreams.mdpointlocator;

import org.bukkit.Material;

public class PointData {

	private Material BlockType = null;
	private int Y = 0;
	
	public void setBlock(Material b) {
		if(b != null) {
			this.BlockType = b;
		}
	}
	
	public void setY(int y) {
		if(y != 0) {
			this.Y = y;
		}
	}
	
	public Material getBlockType() {
		return this.BlockType;
	}
	
	public int getY() {
		return this.Y;
	}
	
	
}
