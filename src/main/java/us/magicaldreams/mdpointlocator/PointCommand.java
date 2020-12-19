package us.magicaldreams.mdpointlocator;

import java.util.Arrays;
import java.util.List;
import org.bukkit.*;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PointCommand implements CommandExecutor {

	MDPointLocator plugin = MDPointLocator.getMain();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){


		// Checks permissions
		Player p = ((Player) sender).getPlayer();
		if (p.hasPermission("md.point")) {

			switch(label.toLowerCase()) {

			case "point": // Command: /point <name> <length> <heading>
				return point(args, p);

			case "point15": // Command: /point15 <name> <length> <heading>
				return point15(args, p);

			case "point2": // Command: /point2 <name> <length> <heading>
				return point2(args, p);

			case "pointdel": // Command: /pointdel <name>
				return pointdel(args, p);

			case "pointinfo": // Command: /pointinfo <name>
				return pointinfo(args, p);

			case "pointlist": // Command: /pointlist
				return pointlist(args, p);

			case "pointsave": // Command: /pointsave <x> <z> <name>
				return pointsave(args, p);

			case "pointsety": // Command: /pointsety <y>
				return pointSetY(args, p);

			case "pointsetmaterial": // Command: /pointsetmaterial <material>
				return pointSetMaterial(args, p);

			case "pointsconnect":
				return pointsConnect(args, p);
			}

		}
		return true;
	}

	private boolean point(String[] args, Player p) {

		// If arguments are missing
		if (isNullArgument(args, 0) || isNullArgument(args, 1) || isNullArgument(args, 2)) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPointLocator &8» &eMissing arguments! Please use /point <Origin Name> <Length> <Heading>"));
		} else if(!isDouble(args[1]) || !isDouble(args[2])){
			// If length and heading are not integers/double
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPointLocator &8» &eLength and Heading must be numbers! Please use /point <Origin Name> <Length> <Heading>"));
		} else {
			// If successful
			if(PointSave.get().contains(args[0])) {
				List<String> coords = PointSave.get().getStringList(args[0]);
				int x = Integer.parseInt(coords.get(0));
				int z = Integer.parseInt(coords.get(1));
				double length = Double.parseDouble(args[1]);
				double heading = Double.parseDouble(args[2]);

				double rad = heading / 180.0 * Math.PI;

				int xchange = (int)(Math.round(length * Math.sin(rad)));
				int zchange = (int)(Math.round(length * Math.cos(rad)));

				x = x + xchange;
				z = z - zchange;

				if(plugin.getPointData(p.getUniqueId()) == null) {
					plugin.addToHashMap(p.getUniqueId(), Material.WHITE_WOOL, p.getLocation().getBlockY(), null);
				}
				
				PointData data = plugin.getPointData(p.getUniqueId());

				Location loc = new Location(p.getWorld(),x,data.getY(),z);                
				plugin.addToHashMap(p.getUniqueId(), Material.AIR, -1, loc);

				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPointLocator &8» &bBlock set to &aX:" + x + " Y:" + data.getY() + " Z:" + z + " successfully"));
				loc.getBlock().setType(data.getBlockType());

			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPointLocator &8» &eStart point &7" + args[0] + " &edoes not exist!"));
			}
		}
		return true;

	}

	private boolean point15(String[] args, Player p) {

		// If arguments are missing
		if (isNullArgument(args, 0) || isNullArgument(args, 1) || isNullArgument(args, 2)) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPointLocator &8» &eMissing arguments! Please use /point <Origin Name> <Length> <Heading>"));
		} else if(!isDouble(args[1]) || !isDouble(args[2])){
			// If length and heading are not integers/double
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPointLocator &8» &eLength and Heading must be numbers! Please use /point <Origin Name> <Length> <Heading>"));
		} else {
			// If successful
			if(PointSave.get().contains(args[0])) {
				List<String> coords = PointSave.get().getStringList(args[0]);
				int x = Integer.parseInt(coords.get(0));
				int z = Integer.parseInt(coords.get(1));
				double length = Double.parseDouble(args[1]);
				double heading = Double.parseDouble(args[2]);

				double rad = heading / 180.0 * Math.PI;

				int xchange = (int)(Math.round(1.5 * (length * Math.sin(rad))));
				int zchange = (int)(Math.round(1.5 * (length * Math.cos(rad))));

				x = x + xchange;
				z = z - zchange;

				if(plugin.getPointData(p.getUniqueId()) == null) {
					plugin.addToHashMap(p.getUniqueId(), Material.WHITE_WOOL, p.getLocation().getBlockY(), null);
				}               
					
				PointData data = plugin.getPointData(p.getUniqueId());

				Location loc = new Location(p.getWorld(),x,p.getLocation().getY(),z,p.getLocation().getYaw(),p.getLocation().getPitch());                
				plugin.addToHashMap(p.getUniqueId(), Material.AIR, -1, loc);

				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPointLocator &8» &bBlock set to &aX:" + x + " Y:" + data.getY() + " Z:" + z + " successfully"));

				loc.getBlock().setType(data.getBlockType());

			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPointLocator &8» &eStart point &7" + args[0] + " &edoes not exist!"));
			}
		}
		return true;

	}

	private boolean point2(String[] args, Player p) {

		// If arguments are missing
		if (isNullArgument(args, 0) || isNullArgument(args, 1) || isNullArgument(args, 2)) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPointLocator &8» &eMissing arguments! Please use /point <Origin Name> <Length> <Heading>"));
		} else if(!isDouble(args[1]) || !isDouble(args[2])){
			// If length and heading are not integers/double
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPointLocator &8» &eLength and Heading must be numbers! Please use /point <Origin Name> <Length> <Heading>"));
		} else {
			// If successful
			if(PointSave.get().contains(args[0])) {
				List<String> coords = PointSave.get().getStringList(args[0]);
				int x = Integer.parseInt(coords.get(0));
				int z = Integer.parseInt(coords.get(1));
				double length = Double.parseDouble(args[1]);
				double heading = Double.parseDouble(args[2]);

				double rad = heading / 180.0 * Math.PI;

				int xchange = (int)(Math.round(2 * (length * Math.sin(rad))));
				int zchange = (int)(Math.round(2 * (length * Math.cos(rad))));

				x = x + xchange;
				z = z - zchange;

				if(plugin.getPointData(p.getUniqueId()) == null) {
					plugin.addToHashMap(p.getUniqueId(), Material.WHITE_WOOL, p.getLocation().getBlockY(), null);
				}
				
				PointData data = plugin.getPointData(p.getUniqueId());

				Location loc = new Location(p.getWorld(),x,p.getLocation().getY(),z,p.getLocation().getYaw(),p.getLocation().getPitch());
				plugin.addToHashMap(p.getUniqueId(), Material.AIR, -1, loc);

				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPointLocator &8» &bBlock set to &aX:" + x + " Y:" + data.getY() + " Z:" + z + " successfully"));

				loc.getBlock().setType(data.getBlockType());

			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPointLocator &8» &eStart point &7" + args[0] + " &edoes not exist!"));
			}
		}
		return true;

	}

	private boolean pointsave(String[] args, Player p) {

		PointSave.setup();

		// If arguments are missing
		if (isNullArgument(args, 0) || isNullArgument(args, 1) || isNullArgument(args, 2)) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPointLocator &8» &eMissing arguments! Please use /pointsave <X> <Z> <Origin Name>"));
		} else if (!isInteger(args[0]) || !isInteger(args[1])) {
			// If x and y are not integers
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPointLocator &8» &eX and Z must be numbers!"));
		} else {
			// If successful
			if(!PointSave.get().contains(args[2])) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPointLocator &8» &bStart point &a" + args[2] + " &bhas been saved!"));
				List<String> coords = Arrays.asList(args[0], args[1]);
				PointSave.get().set(args[2], coords);
				PointSave.save();
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPointLocator &8» &eStart point already exists!"));
			}
		}
		return true;

	}

	private boolean pointdel(String[] args, Player p) {

		if (isNullArgument(args, 0)) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPointLocator &8» &eMissing arguments! Please use /pointdel <Origin Name>"));
		} else {
			if (PointSave.get().contains(args[0])) {
				PointSave.get().set(args[0], null);
				PointSave.save();
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPointLocator &8» &bStart point &a" + args[0] + " &bhas been deleted!"));
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPointLocator &8» &eStart point &7" + args[0] + " &edoes not exist!"));
			}
		}
		return true;

	}

	private boolean pointlist(String[] args, Player p) {

		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPointLocator &8» &bCurrent start points include: &a" + PointSave.get().getKeys(false)));
		return true;

	}

	private boolean pointinfo(String[] args, Player p) {

		if (isNullArgument(args, 0)) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPointLocator &8» &eMissing arguments! Please use /pointinfo <Origin Name>"));
		} else {
			if(PointSave.get().contains(args[0])) {
				List<String> coords = PointSave.get().getStringList(args[0]);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPointLocator &8» &eStart point &7= &eX:" + coords.get(0) + " Z:" + coords.get(1)));
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPointLocator &8» &eStart point &7" + args[0] + " &edoes not exist!"));
			}
		}
		return true;



	}

	private boolean pointSetY(String[] args, Player p) {
		if(isNullArgument(args, 0)) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPointLocator &8» &eMissing arguments! Please use /pointsety <Y Value>"));
		} else {

			plugin.addToHashMap(p.getUniqueId(), Material.AIR, Integer.parseInt(args[0]), null);

			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPointLocator &8» &bBlock elevation saved for your points"));

		}

		return true;
	}

	private boolean pointSetMaterial(String [] args, Player p) {
		if(isNullArgument(args, 0)) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPointLocator &8» &eMissing arguments! Please use /pointsetblock <Blocktype>"));
		} else {

			if(Material.getMaterial(args[0].toUpperCase()).isBlock()) {

				Material m = Material.getMaterial(args[0].toUpperCase());
				plugin.addToHashMap(p.getUniqueId(), m, -1, null);

				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPointLocator &8» &bBlock type saved for your points"));

			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPointLocator &8» &eThe material type you selected is not a block type"));
			}    		

		}

		return true;
	}
	private boolean pointsConnect(String[] args, Player p) {

		PointData data = plugin.getPointData(p.getUniqueId());

		if(data.getPos1() == null && data.getPos2() == null) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPointLocator &8» &eMissing position! Please make sure two points have been created"));
		} else {
			p.performCommand("/pos1 " + data.getPos1().getBlockX() + "," + data.getPos1().getBlockY() + "," + data.getPos1().getBlockZ());
			p.performCommand("/pos2 " + data.getPos2().getBlockX() + "," + data.getPos2().getBlockY() + "," + data.getPos2().getBlockZ());
			p.performCommand("/line " + data.getBlockType());
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPointLocator &8» &bLine created between the points successfully "));
		}

		return true;
	}

	// Function checks if input is double
	private boolean isDouble(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	// Function checks if input is string
	private boolean isNullArgument(String[] args, int index) {
		try {
			@SuppressWarnings("unused")
			String temp = args[index];
			return false;
		} catch (IndexOutOfBoundsException ex) {
			return true;
		}
	}

	// Function checks if input is integer
	private boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

}