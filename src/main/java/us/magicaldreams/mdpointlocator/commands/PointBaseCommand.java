package us.magicaldreams.mdpointlocator.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.magicaldreams.mdpointlocator.command.MDSubCommand;
import us.magicaldreams.mdpointlocator.util.CommonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Trevor Chumbley
 * 9/21/2022 - 2:36 PM
 **/

public class PointBaseCommand implements CommandExecutor {

    //Point base command class
    private static Map<String, MDSubCommand> commands = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Check if args length less than 1
        if (args.length < 1) {
            sender.sendMessage(CommonUtil.getPluginAboutMessage());
            return true;
        }

        // Check if sender instanceof player
        if (!(sender instanceof Player)) {
            sender.sendMessage(CommonUtil.getBrandedConsoleMsg(ChatColor.RED + "Sorry, only players may run point locator commands!"));
            return true;
        }

        // Check if sender has base permission
        if (!(sender.hasPermission(CommonUtil.getPermissionNode()))) {
            sender.sendMessage(CommonUtil.getNoPermissionMsg());
            return true;
        }

        // Check if sub command doesn't exist
        if (!commands.containsKey(args[0].toLowerCase())) {
            // Run help sub command if no, incorrect, or unknown sub command
            commands.get("help").onCommand(sender, command, args);
            return true;
        }

        // Check if the sender has permission
        if (!(sender.hasPermission(commands.get(args[0]).getPermission()))) {
            sender.sendMessage(CommonUtil.getNoPermissionMsg());
            return true;
        }

        //Execute the sub command
        commands.get(args[0]).onCommand(sender, command, args);
        return true;
    }

    public void registerCommand(String cmd, MDSubCommand subCommand) {
        commands.put(cmd, subCommand);
    }

    public static Map<String, MDSubCommand> getCommands() {
        return commands;
    }


}