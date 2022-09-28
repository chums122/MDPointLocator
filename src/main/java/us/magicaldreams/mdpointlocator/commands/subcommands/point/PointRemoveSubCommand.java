package us.magicaldreams.mdpointlocator.commands.subcommands.point;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import us.magicaldreams.mdpointlocator.PointConfig;
import us.magicaldreams.mdpointlocator.command.MDSubCommand;
import us.magicaldreams.mdpointlocator.util.CommonUtil;

/**
 * Created by Trevor Chumbley
 * 9/28/2022 - 11:57 AM
 **/

public class PointRemoveSubCommand implements MDSubCommand {
    @Override
    public void onCommand(CommandSender sender, Command command, String[] args) {

        if (args.length < 1) {
            sender.sendMessage(CommonUtil.getBrandedMsgPrefix(ChatColor.RED + "Missing arguments! Please use " + getUsage()));
            return;
        } else if (args.length > 1) {
            sender.sendMessage(CommonUtil.getBrandedMsgPrefix(ChatColor.RED + "Too many arguments! Please use " + getUsage()));
            return;
        }

        if (PointConfig.getConfig().contains(args[0])) {
            PointConfig.getConfig().set(args[0], null);
            PointConfig.saveConfig();
            sender.sendMessage(CommonUtil.getBrandedMsgPrefix(ChatColor.AQUA + "Start point " + ChatColor.GREEN + args[0] + ChatColor.AQUA + " has been deleted!"));
        } else {
            sender.sendMessage(CommonUtil.getBrandedMsgPrefix(ChatColor.RED + "Start point" + ChatColor.YELLOW + args[0] + ChatColor.RED + "does not exist!"));
            sender.sendMessage(CommonUtil.getPointListHelpLine());
        }
    }

    @Override
    public String getPermission() {
        return CommonUtil.getPermissionNode("remove");
    }

    @Override
    public String getUsage() {
        return "/point remove <name>";
    }

    @Override
    public String getDescription() {
        return "Remove a global starting point.";
    }
}
