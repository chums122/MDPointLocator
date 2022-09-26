package us.magicaldreams.mdpointlocator.util;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import us.magicaldreams.mdpointlocator.MDPointLocator;

/**
 * Created by Trevor Chumbley
 * 9/21/2022 - 4:21 PM
 **/

public class CommonUtil {
    static String brandedMessagePrefix = ChatColor.GREEN + "PointLocator " + ChatColor.GRAY + "» ";
    static String brandedMessagePrefixMD = ChatColor.GREEN + "MDPointLocator " + ChatColor.GRAY + "» ";
    static String permissionNode = "md.point";
    static String noPermissionMsg = ChatColor.RED + "Sorry, you don't have permission for that command!";

    public static String getPluginVersion() {
        PluginDescriptionFile pluginDescriptionFile = MDPointLocator.getInstance().getDescription();
        return pluginDescriptionFile.getVersion();
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

}
