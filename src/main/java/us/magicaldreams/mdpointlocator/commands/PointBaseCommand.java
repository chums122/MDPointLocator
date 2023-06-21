package us.magicaldreams.mdpointlocator.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import us.magicaldreams.mdpointlocator.command.MDSubCommand;
import us.magicaldreams.mdpointlocator.commands.subcommands.PointMaterialSubCommand;
import us.magicaldreams.mdpointlocator.util.CommonUtil;

import java.util.*;

/**
 * Created by Trevor Chumbley
 * 9/21/2022 - 2:36 PM
 **/

public class PointBaseCommand implements CommandExecutor, TabCompleter {

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

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        // Check if args is already bussin fr fr ong
        if (args.length > 1) {
            // Tab complete for materials
            if (args[0].equals("material")) {
                List<String> materialCompletions = new ArrayList<>();
                List<String> materialList = new ArrayList<>();
                for(Material material : Material.values()) {
                    materialList.add(material.toString());
                }
                StringUtil.copyPartialMatches(args[1], materialList, materialCompletions);
                return materialCompletions;
            }
            return null;
        }

        // Create new array
        final List<String> completions = new ArrayList<>();
        StringUtil.copyPartialMatches(args[0], CommonUtil.convertMapKeysToIterableString(commands), completions);
        return completions;
    }

    public void registerCommand(String cmd, MDSubCommand subCommand) {
        commands.put(cmd, subCommand);
    }

    public static Map<String, MDSubCommand> getCommands() {
        return commands;
    }

}