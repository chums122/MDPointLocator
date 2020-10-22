package us.magicaldreams.mdpointlocator;

import java.util.Arrays;
import java.util.List;
import org.bukkit.*;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PointCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        String staffPrefix = "&cCM &8» &a";

        // Checks permissions
        Player p = ((Player) sender).getPlayer();
        if (p.hasPermission("md.point")) {

            // Command: /point <name> <length> <heading>
            if (label.equalsIgnoreCase("point")) {

                // If arguments are missing
                if (isNullArgument(args, 0) || isNullArgument(args, 1) || isNullArgument(args, 2)) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', staffPrefix + "&aPointLocator &8» &eMissing arguments! Please use /point <Name> <Length> <Heading>"));
                    return true;
                } else if(!isDouble(args[1]) || !isDouble(args[2])){
                    // If length and heading are not integers/double
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', staffPrefix + "&aPointLocator &8» &eLength and Heading must be numbers! Please use /point <Name> <Length> <Heading>"));
                    return true;
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
                        Location loc = new Location(p.getWorld(),x,p.getLocation().getY(),z);

                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', staffPrefix + "&aPointLocator &8» &bTeleported to &aX:" + x + " Y:" + p.getLocation().getBlockY() + " Z:" + z));
                        loc.getBlock().setType(Material.GLASS);
                        p.teleport(loc);

                        return true;
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', staffPrefix + "&aPointLocator &8» &eStart point &7" + args[0] + " &edoes not exist!"));
                    }
                }
                return true;
            }

            // Command: /point15 <name> <length> <heading>
            if (label.equalsIgnoreCase("point15")) {

                // If arguments are missing
                if (isNullArgument(args, 0) || isNullArgument(args, 1) || isNullArgument(args, 2)) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', staffPrefix + "&aPointLocator &8» &eMissing arguments! Please use /point <Name> <Length> <Heading>"));
                    return true;
                } else if(!isDouble(args[1]) || !isDouble(args[2])){
                    // If length and heading are not integers/double
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', staffPrefix + "&aPointLocator &8» &eLength and Heading must be numbers! Please use /point <Name> <Length> <Heading>"));
                    return true;
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
                        Location loc = new Location(p.getWorld(),x,p.getLocation().getY(),z);

                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', staffPrefix + "&aPointLocator &8» &bTeleported to &aX:" + x + " Y:" + p.getLocation().getBlockY() + " Z:" + z));

                        loc.getBlock().setType(Material.GLASS);
                        p.teleport(loc);

                        return true;
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', staffPrefix + "&aPointLocator &8» &eStart point &7" + args[0] + " &edoes not exist!"));
                    }
                }
                return true;
            }

            // Command: /point2 <name> <length> <heading>
            if (label.equalsIgnoreCase("point2")) {

                // If arguments are missing
                if (isNullArgument(args, 0) || isNullArgument(args, 1) || isNullArgument(args, 2)) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', staffPrefix + "&aPointLocator &8» &eMissing arguments! Please use /point <Name> <Length> <Heading>"));
                    return true;
                } else if(!isDouble(args[1]) || !isDouble(args[2])){
                    // If length and heading are not integers/double
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', staffPrefix + "&aPointLocator &8» &eLength and Heading must be numbers! Please use /point <Name> <Length> <Heading>"));
                    return true;
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
                        Location loc = new Location(p.getWorld(),x,p.getLocation().getY(),z);

                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', staffPrefix + "&aPointLocator &8» &bTeleported to &aX:" + x + " Y:" + p.getLocation().getBlockY() + " Z:" + z));

                        loc.getBlock().setType(Material.GLASS);
                        p.teleport(loc);

                        return true;
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', staffPrefix + "&aPointLocator &8» &eStart point &7" + args[0] + " &edoes not exist!"));
                    }
                }
                return true;
            }

            // Command: /pointdel <name>
            if (label.equalsIgnoreCase("pointdel")) {
                if (isNullArgument(args, 0)) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', staffPrefix + "&aPointLocator &8» &eMissing arguments! Please use /pointdel <Name>"));
                } else {
                    if (PointSave.get().contains(args[0])) {
                        PointSave.get().set(args[0], null);
                        PointSave.save();
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', staffPrefix + "&aPointLocator &8» &bStart point &a" + args[0] + " &bhas been deleted!"));
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', staffPrefix + "&aPointLocator &8» &eStart point &7" + args[0] + " &edoes not exist!"));
                    }
                }
                return true;
            }

            // Command: /pointinfo <name>
            if (label.equalsIgnoreCase("pointinfo")) {
                if (isNullArgument(args, 0)) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', staffPrefix + "&aPointLocator &8» &eMissing arguments! Please use /pointinfo <Name>"));
                    return true;
                } else {
                    if(PointSave.get().contains(args[0])) {
                        List<String> coords = PointSave.get().getStringList(args[0]);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', staffPrefix + "&aPointLocator &8» &eStart point &7= &eX:" + coords.get(0) + " Z:" + coords.get(1)));
                        return true;
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', staffPrefix + "&aPointLocator &8» &eStart point &7" + args[0] + " &edoes not exist!"));
                    }
                }
                return true;
            }

            // Command: /pointlist
            if (label.equalsIgnoreCase("pointlist")) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', staffPrefix + "&aPointLocator &8» &bCurrent start points include: &a" + PointSave.get().getKeys(false)));
                return true;
            }

            // Command: /pointsave <x> <z> <name>
            if (label.equalsIgnoreCase("pointsave")) {

                PointSave.setup();

                // If arguments are missing
                if (isNullArgument(args, 0) || isNullArgument(args, 1) || isNullArgument(args, 2)) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', staffPrefix + "&aPointLocator &8» &eMissing arguments! Please use /pointsave <X> <Z> <Name>"));
                    return true;
                } else if (!isInteger(args[0]) || !isInteger(args[1])) {
                    // If x and y are not integers
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', staffPrefix + "&aPointLocator &8» &eX and Z must be numbers!"));
                    return true;
                } else {
                    // If successful
                    if(!PointSave.get().contains(args[2])) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', staffPrefix + "&aPointLocator &8» &bStart point &a" + args[2] + " &bhas been saved!"));
                        List<String> coords = Arrays.asList(args[0], args[1]);
                        PointSave.get().set(args[2], coords);
                        PointSave.save();
                        return true;
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', staffPrefix + "&aPointLocator &8» &eStart point already exists!"));
                    }
                }
                return true;
            }

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