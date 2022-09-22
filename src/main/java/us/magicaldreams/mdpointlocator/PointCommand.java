//package us.magicaldreams.mdpointlocator;
//
//import java.util.Arrays;
//import java.util.List;
//import org.bukkit.*;
//import org.bukkit.command.CommandExecutor;
//import org.bukkit.command.Command;
//import org.bukkit.command.CommandSender;
//import org.bukkit.entity.Player;
//import us.magicaldreams.mdpointlocator.util.CommonUtil;
//
//public class PointCommand implements CommandExecutor {
//    String pointLocatorPre = "&aPointLocator &8» ";
//
//    @Override
//    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
//
//        if (!(sender instanceof Player)) {
//            sender.sendMessage(CommonUtil.brandedChatFormat("&eOnly Players can run that command!"));
//            return true;
//        }
//
//        // Checks permissions
//        Player p = ((Player) sender).getPlayer();
//
//        if (!p.hasPermission("md.point")) {
//            p.sendMessage(ChatColor.RED + "Sorry, you don't have permission for that command!");
//            return true;
//        }
//
//        switch (label.toLowerCase()) {
//            // Command: /point <name> <length> <heading>
//            case "point":
//            case "point15":
//            case "point2":
//                return pointCmd(label, args, p);
//            // Command: /pointdel <name>
//            case "pointdel":
//                return pointDelCmd(args, p);
//            // Command: /pointinfo <name>
//            case "pointinfo":
//                return pointInfoCmd(args, p);
//            // Command: /pointlist
//            case "pointlist":
//                return pointListCmd(p);
//            // Command: /pointsave <x> <z> <name>
//            case "pointsave":
//                return pointSaveCmd(args, p);
//        }
//
//        return true;
//    }
//
//    //point <name> <length> <heading>
//    private boolean pointCmd(String label, String[] args, Player p) {
//        // If arguments are missing
//        if (isNullArgument(args, 0) || isNullArgument(args, 1) || isNullArgument(args, 2)) {
//            p.sendMessage(ChatColor.translateAlternateColorCodes('&', pointLocatorPre + "&eMissing arguments! Please use /" + label + " <Name> <Length> <Heading>"));
//            return true;
//        } else if (!isDouble(args[1]) || !isDouble(args[2])) {
//            // If length and heading are not integers/double
//            p.sendMessage(ChatColor.translateAlternateColorCodes('&', pointLocatorPre + "&eLength and Heading must be numbers! Please use /" + label + " <Name> <Length> <Heading>"));
//            return true;
//        }
//
//        if(PointConfig.getConfig().contains(args[0])) {
//            List<String> coords = PointConfig.getConfig().getStringList(args[0]);
//
//            int x = Integer.parseInt(coords.get(0));
//            int z = Integer.parseInt(coords.get(1));
//
//            double length = Double.parseDouble(args[1]);
//            double heading = Double.parseDouble(args[2]);
//
//            World world = p.getWorld();
//            double y = p.getLocation().getY();
//            float yaw = p.getLocation().getYaw();
//            float pitch = p.getLocation().getPitch();
//
//            double pointMultiplier = 1;
//            switch (label.toLowerCase()) {
//                case "point15":
//                    pointMultiplier = 1.5;
//                    break;
//                case "point2":
//                    pointMultiplier = 2;
//                    break;
//            }
//
//            Location loc = getPointLocation(length, heading, pointMultiplier, world, x, y, z, yaw, pitch);
//
//            p.sendMessage(ChatColor.translateAlternateColorCodes('&', pointLocatorPre + "&bTeleported to &aX:" + x + " Y:" + p.getLocation().getBlockY() + " Z:" + z));
//            loc.getBlock().setType(Material.GLASS);
//            p.teleport(loc);
//
//            return true;
//        } else {
//            p.sendMessage(ChatColor.translateAlternateColorCodes('&', pointLocatorPre + "&eStart point &7" + args[0] + " &edoes not exist!"));
//            return true;
//        }
//    }
//
//    //pointdel <name>
//    private boolean pointDelCmd(String[] args, Player p) {
//        if (isNullArgument(args, 0)) {
//            p.sendMessage(ChatColor.translateAlternateColorCodes('&', pointLocatorPre + "&eMissing arguments! Please use /pointdel <Name>"));
//            return true;
//        }
//
//        if (PointConfig.getConfig().contains(args[0])) {
//            PointConfig.getConfig().set(args[0], null);
//            PointConfig.saveConfig();
//            p.sendMessage(ChatColor.translateAlternateColorCodes('&', pointLocatorPre + "&bStart point &a" + args[0] + " &bhas been deleted!"));
//            return true;
//        } else {
//            p.sendMessage(ChatColor.translateAlternateColorCodes('&', pointLocatorPre + "&eStart point &7" + args[0] + " &edoes not exist!"));
//            return true;
//        }
//    }
//
//    //pointinfo <name>
//    private boolean pointInfoCmd(String[] args, Player p) {
//        if (isNullArgument(args, 0)) {
//            p.sendMessage(ChatColor.translateAlternateColorCodes('&', pointLocatorPre + "&eMissing arguments! Please use /pointinfo <Name>"));
//            return true;
//        }
//
//        if(PointConfig.getConfig().contains(args[0])) {
//            List<String> coords = PointConfig.getConfig().getStringList(args[0]);
//            p.sendMessage(ChatColor.translateAlternateColorCodes('&', pointLocatorPre + "&eStart point &7= &eX:" + coords.get(0) + " Z:" + coords.get(1)));
//            return true;
//        } else {
//            p.sendMessage(ChatColor.translateAlternateColorCodes('&', pointLocatorPre + "&eStart point &7" + args[0] + " &edoes not exist!"));
//            return true;
//        }
//    }
//
//    //pointlist
//    private boolean pointListCmd(Player p) {
//        p.sendMessage(ChatColor.translateAlternateColorCodes('&', pointLocatorPre + "&bCurrent start points include: &a" + PointConfig.getConfig().getKeys(false)));
//        return true;
//    }
//
//    //pointsave <x> <z> <name>
//    private boolean pointSaveCmd(String[] args, Player p) {
//        PointConfig.setupConfig();
//
//        // If arguments are missing
//        if (isNullArgument(args, 0) || isNullArgument(args, 1) || isNullArgument(args, 2)) {
//            p.sendMessage(ChatColor.translateAlternateColorCodes('&', pointLocatorPre + "&eMissing arguments! Please use /pointsave <X> <Z> <Name>"));
//            return true;
//        } else if (!isInteger(args[0]) || !isInteger(args[1])) {
//            // If x and y are not integers
//            p.sendMessage(ChatColor.translateAlternateColorCodes('&', pointLocatorPre + "&eX and Z must be numbers!"));
//            return true;
//        }
//
//        if(!PointConfig.getConfig().contains(args[2])) {
//            p.sendMessage(ChatColor.translateAlternateColorCodes('&', pointLocatorPre + "&bStart point &a" + args[2] + " &bhas been saved!"));
//            List<String> coords = Arrays.asList(args[0], args[1]);
//            PointConfig.getConfig().set(args[2], coords);
//            PointConfig.saveConfig();
//            return true;
//        } else {
//            p.sendMessage(ChatColor.translateAlternateColorCodes('&', pointLocatorPre + "&eStart point already exists!"));
//            return true;
//        }
//    }
//
//    private Location getPointLocation(double length, double heading, double pointMultiplier, World world, double x, double y, double z, float yaw, float pitch) {
//        double rad = heading / 180.0 * Math.PI;
//        x = x + (int)(Math.round(pointMultiplier * (length * Math.sin(rad))));
//        z = z + (int)(Math.round(pointMultiplier * (length * Math.cos(rad))));
//        return new Location(world, x, y, z, yaw, pitch);
//    }
//
//    // Function checks if input is double
//    private boolean isDouble(String s) {
//        try {
//            Double.parseDouble(s);
//            return true;
//        } catch (NumberFormatException nfe) {
//            return false;
//        }
//    }
//
//    // Function checks if input is string
//    private boolean isNullArgument(String[] args, int index) {
//        try {
//            String temp = args[index];
//            return false;
//        } catch (IndexOutOfBoundsException ex) {
//            return true;
//        }
//    }
//
//    // Function checks if input is integer
//    private boolean isInteger(String s) {
//        try {
//            Integer.parseInt(s);
//            return true;
//        } catch (NumberFormatException nfe) {
//            return false;
//        }
//    }
//}