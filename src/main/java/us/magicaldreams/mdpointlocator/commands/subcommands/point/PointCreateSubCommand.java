package us.magicaldreams.mdpointlocator.commands.subcommands.point;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.magicaldreams.mdpointlocator.util.PointConfig;
import us.magicaldreams.mdpointlocator.command.MDSubCommand;
import us.magicaldreams.mdpointlocator.util.CommonUtil;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Trevor Chumbley
 * 9/21/2022 - 2:38 PM
 **/

public class PointCreateSubCommand implements MDSubCommand {

    @Override
    public void onCommand(CommandSender sender, Command command, String[] args) {
        Player player = (Player) sender;
        double scale = 1.0;

        if (args.length < 4) {
            player.sendMessage(CommonUtil.getMissingArgsMsg(getUsage()));
            return;
        } else if (args.length > 5) {
            player.sendMessage(CommonUtil.getTooManyArgsMsg(getUsage()));
            return;
        } else if (!CommonUtil.isInteger(args[2]) || !CommonUtil.isInteger(args[3])) {
            // If x and y are not integers
            player.sendMessage(CommonUtil.getBrandedMsgPrefix(ChatColor.RED + "X and Z must be numbers!"));
            return;
        }

        String plotName = args[1];
        String xStr = args[2];
        String zStr = args[3];

        // Check if scale defined
        if (!CommonUtil.isNullArgument(args, 4)) {
            if ((CommonUtil.isInteger(args[4])) || (CommonUtil.isDouble(args[4]))) {
                scale = Double.parseDouble(args[4]);
            } else {
                player.sendMessage(CommonUtil.getBrandedMsgPrefix(ChatColor.RED + "Scale must be in the form of a number!"));
                return;
            }
        }

        if (!PointConfig.getConfig().contains(plotName)) {
            player.sendMessage(CommonUtil.getBrandedMsgPrefix(ChatColor.AQUA + "The start point " + ChatColor.GREEN + plotName + ChatColor.AQUA + " has been created with a scale of " + scale + ":1!"));
            List<String> coords = Arrays.asList(xStr, zStr, String.valueOf(scale));
            PointConfig.getConfig().set(plotName, coords);
            PointConfig.saveConfig();
        } else {
            player.sendMessage(CommonUtil.getBrandedMsgPrefix(ChatColor.RED + "That start point already exists!"));
            player.sendMessage(CommonUtil.getPointListHelpLine());
        }
    }

    public String getPermission() {
        return CommonUtil.getPermissionNode("create");
    }

    public String getUsage() {
        return "/point create <name> <x> <z> (scale)";
    }

    public String getDescription() {
        return "Create a new global starting point.";
    }

}
