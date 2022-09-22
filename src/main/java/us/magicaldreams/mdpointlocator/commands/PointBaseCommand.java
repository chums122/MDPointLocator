package us.magicaldreams.mdpointlocator.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
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

        // Check if args less than 1 or if sub command exists
        if ((args.length < 1) || (!commands.containsKey(args[0].toLowerCase()))) {
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