package us.magicaldreams.mdpointlocator.util;

import org.bukkit.Location;
import org.bukkit.Material;

/**
 * Created by Zackery Anderson
 * ©2023
 */

public class PointData
{
    private Material BlockType = Material.WHITE_WOOL;
    private int Y = -1;
    private Location pos1;
    private Location pos2;

    public void SetBlock(Material block)
    {
        if(block != null)
        {
            this.BlockType = block;
        }
    }

    public void SetY(int y)
    {
        if(y != 0){
            this.Y = y;
        }
    }

    public void SavePosition(Location location)
    {
        if(this.pos1 == null && this.pos2 == null)
        {
            this.pos1 = location;
        }
        else if (this.pos1 != null && this.pos2 == null)
        {
            this.pos2 = location;
        }
        else
        {
            this.pos1 = this.pos2;
            this.pos2 = location;
        }
    }

    public Material GetBlockType()
    {
        return this.BlockType;
    }

    public int GetY()
    {
        return this.Y;
    }

    public Location GetPos1()
    {
        return this.pos1;
    }

    public Location GetPos2()
    {
        return this.pos2;
    }
}
