package us.magicaldreams.mdpointlocator.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * Created by Trevor Chumbley
 * 9/21/2022 - 3:23 PM
 **/

public interface MDSubCommand {
    void onCommand(CommandSender sender, Command command, String[] args);
    String getPermission();
    String getUsage();
    String getDescription();
//    List<String> onTabComplete(CommandSender commandSender, String[] args);
}
