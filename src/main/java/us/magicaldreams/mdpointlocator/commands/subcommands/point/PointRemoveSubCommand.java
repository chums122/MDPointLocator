package us.magicaldreams.mdpointlocator.commands.subcommands.point;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import us.magicaldreams.mdpointlocator.util.PointConfig;
import us.magicaldreams.mdpointlocator.command.MDSubCommand;
import us.magicaldreams.mdpointlocator.util.CommonUtil;

/**
 * Created by Trevor Chumbley
 * 9/28/2022 - 11:57 AM
 **/

public class PointRemoveSubCommand implements MDSubCommand {
    @Override
    public void onCommand(CommandSender sender, Command command, String[] args) {

        if (args.length < 2) {
            sender.sendMessage(CommonUtil.getMissingArgsMsg(getUsage()));
            return;
        } else if (args.length > 2) {
            sender.sendMessage(CommonUtil.getTooManyArgsMsg(getUsage()));
            return;
        }

        String plotName = args[1];

        if (PointConfig.getConfig().contains(plotName)) {
            PointConfig.getConfig().set(plotName, null);
            PointConfig.saveConfig();
            sender.sendMessage(CommonUtil.getBrandedMsgPrefix(ChatColor.AQUA + "Start point " + ChatColor.GREEN + plotName + ChatColor.AQUA + " has been deleted!"));
        } else {
            sender.sendMessage(CommonUtil.getStartPointNotExistMsg(args[0]));
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
