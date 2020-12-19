package us.magicaldreams.mdpointlocator;

import org.bukkit.Location;
import org.bukkit.Material;

public class PointData {

	private Material BlockType = Material.WHITE_WOOL;
	private int Y = -1;
	private Location pos1;
	private Location pos2;
	
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
	
	public void savePosition(Location loc) {
		
		if(this.pos1 == null && this.pos2 == null) {
			this.pos1 = loc;
		} 
		else if (this.pos1 != null && this.pos2 == null) {
			this.pos2 = loc;
		} 
		else if (this.pos1 != null && this.pos2 != null) {
			this.pos1 = this.pos2;
			this.pos2 = loc;
		}
		
	}
	
	public Material getBlockType() {
		return this.BlockType;
	}
	
	public int getY() {
		return this.Y;
	}
	
	public Location getPos1() {
		return this.pos1;
	}
	
	public Location getPos2() {
		return this.pos2;
	}
	
	
}
