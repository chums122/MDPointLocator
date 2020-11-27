package us.magicaldreams.mdpointlocator;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import us.magicaldreams.mdpointlocator.PointCommand;
import us.magicaldreams.mdpointlocator.PointSave;

public final class MDPointLocator extends JavaPlugin {

	public static HashMap<UUID, PointData> playerData = new HashMap<UUID, PointData>();
	
	private static MDPointLocator main;
	public static MDPointLocator getMain() {
		return main;
	}

	@Override
	public void onEnable() {

		main = this;
		
		// Config setup for PointLocator
		PointSave.setup();
		PointSave.get().options().copyDefaults();
		PointSave.save();

		this.getCommand("point").setExecutor(new PointCommand());
		this.getCommand("point15").setExecutor(new PointCommand());
		this.getCommand("point2").setExecutor(new PointCommand());
		this.getCommand("pointsave").setExecutor(new PointCommand());
		this.getCommand("pointdel").setExecutor(new PointCommand());
		this.getCommand("pointlist").setExecutor(new PointCommand());
		this.getCommand("pointinfo").setExecutor(new PointCommand());
		this.getCommand("pointsety").setExecutor(new PointCommand());
		this.getCommand("pointsetmaterial").setExecutor(new PointCommand());
		System.out.println("MDPointLocator > Plugin loaded successfully");
	}
	
	@Override
	public void onDisable() {
		playerData.clear();
	}

	public void addToHashMap(UUID playerID, Material block, int y) {
		
		PointData data;

		if(playerID != null) {

			if(!playerData.isEmpty() && playerData.containsKey(playerID)) {
				
				data = playerData.get(playerID);

				if(block != Material.AIR) {
					data.setBlock(block);					
				}

				if(y != 0) {
					data.setY(y);
				}
				
				playerData.replace(playerID, data);

			} else {
				
				data = new PointData();
				
				if(block != Material.AIR) {
					data.setBlock(block);					
				}

				if(y != 0) {
					data.setY(y);
				}
				
				playerData.put(playerID, data);
				
			}

		}

	}
	
	public PointData getPointData(UUID playerID) {
		
		PointData data;
		
		if(playerID != null) {
			
			if(playerData.containsKey(playerID)) {
				data = playerData.get(playerID);
				return data;
			}
			
		}
		
		return null;
	}
	
	public void removeFromHashMap(UUID playerID) {
		
		if(playerID != null) {
			if(playerData.containsKey(playerID)) {
				playerData.remove(playerID);
			}
		}
		
	}

}
