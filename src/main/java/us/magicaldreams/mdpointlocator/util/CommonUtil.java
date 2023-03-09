package us.magicaldreams.mdpointlocator.util;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.PluginDescriptionFile;
import us.magicaldreams.mdpointlocator.MDPointLocator;

/**
 * Created by Trevor Chumbley
 * 9/21/2022 - 4:21 PM
 **/

public class CommonUtil {
    static PluginDescriptionFile pluginDescriptionFile = MDPointLocator.getInstance().getDescription();
    static String brandedMessagePrefix = ChatColor.GREEN + "PointLocator " + ChatColor.GRAY + "» ";
    static String brandedMessagePrefixMD = ChatColor.GREEN + "MDPointLocator " + ChatColor.GRAY + "» ";
    static String permissionNode = "md.point";
    static String noPermissionMsg = ChatColor.RED + "Sorry, you don't have permission for that command!";

    public static String getPluginVersion() {
        return pluginDescriptionFile.getVersion();
    }

    public static String getPluginAuthors() {
        return pluginDescriptionFile.getAuthors().toString().substring(1, pluginDescriptionFile.getAuthors().toString().length() - 1);
    }

    public static String getPluginAboutMessage() {
        return getBrandedConsoleMsg(ChatColor.RED + "No sub command provided! /point help for a list of commands. \n" +
                getBrandedConsoleMsg("Version " + getPluginVersion() + " by " + getPluginAuthors()));
    }

    public static String getBrandedMsgPrefix(String input) {
        return brandedMessagePrefix + input;
    }

    public static String getBrandedConsoleMsg(String input) {
        return brandedMessagePrefixMD + ChatColor.AQUA + input;
    }

    public static String getHelpEntry(String subCmdUsage, String description) {
        return ChatColor.YELLOW + subCmdUsage + ChatColor.GRAY + " - " + ChatColor.WHITE + description;
    }

    public static String getNoPermissionMsg() {
        return getBrandedMsgPrefix(noPermissionMsg);
    }

    public static String getPermissionNode() {
        return permissionNode;
    }

    public static String getPermissionNode(String permission) {
        return permissionNode + "." + permission;
    }

    public static String getStartPointNotExistMsg(String pointName) {
        return getBrandedMsgPrefix(ChatColor.RED + "Start point " + ChatColor.YELLOW + pointName + ChatColor.RED + " does not exist!");
    }

    public static String getPointListHelpLine() {
        return getBrandedMsgPrefix(ChatColor.YELLOW + "Please run " + ChatColor.GOLD + "/point list" + ChatColor.YELLOW + " for a list of available points.");
    }

    public static Location getPointLocation(double length, double heading, double pointMultiplier, World world, double x, double y, double z, float yaw, float pitch) {
        double rad = heading / 180.0 * Math.PI;
        int xchange = (int)(Math.round(pointMultiplier * (length * Math.sin(rad))));
        int zchange = (int)(Math.round(pointMultiplier * (length * Math.cos(rad))));
        x = x + xchange;
        z = z - zchange;
//        x = x + (int)(Math.round(pointMultiplier * (length * Math.sin(rad))));
//        z = z + (int)(Math.round(pointMultiplier * (length * Math.cos(rad))));
        return new Location(world, x, y, z, yaw, pitch);
    }

    public static String getMissingArgsMsg(String usage) {
        return getBrandedMsgPrefix(ChatColor.RED + "Missing arguments! Please use " + usage);
    }

    public static String getTooManyArgsMsg(String usage) {
        return getBrandedMsgPrefix(ChatColor.RED + "Too many arguments! Please use " + usage);
    }

    // Function checks if input is string
    public static boolean isNullArgument(String[] args, int index) {
        try {
            String temp = args[index];
            return false;
        } catch (IndexOutOfBoundsException ex) {
            return true;
        }
    }

    // Function checks if input is integer
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    // Function checks if input is double
    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

}
