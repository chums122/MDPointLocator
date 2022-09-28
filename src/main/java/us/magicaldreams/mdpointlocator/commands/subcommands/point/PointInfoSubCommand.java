package us.magicaldreams.mdpointlocator.commands.subcommands.point;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import us.magicaldreams.mdpointlocator.util.PointConfig;
import us.magicaldreams.mdpointlocator.command.MDSubCommand;
import us.magicaldreams.mdpointlocator.util.CommonUtil;

import java.util.List;

/**
 * Created by Trevor Chumbley
 * 9/28/2022 - 12:16 PM
 **/

public class PointInfoSubCommand implements MDSubCommand {

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

            List<String> coords = PointConfig.getConfig().getStringList(args[0]);
            String x = coords.get(0);
            String z = coords.get(1);
            String scaleMultiplier = "1";

            try {
                scaleMultiplier = coords.get(2);
            } catch (NullPointerException ignored) {
                // scale = 1.0
            }

            sender.sendMessage(CommonUtil.getBrandedMsgPrefix(ChatColor.YELLOW + "Showing point info for " + ChatColor.GOLD + args[0] + ":"));
            sender.sendMessage(CommonUtil.getBrandedMsgPrefix(
                    ChatColor.GOLD + "X: " + ChatColor.AQUA + x +
                    ChatColor.GOLD + "Z: " + ChatColor.AQUA + z +
                    ChatColor.GOLD + "Scale: " + ChatColor.AQUA + scaleMultiplier + ":1"));
        } else {
            sender.sendMessage(CommonUtil.getStartPointNotExistMsg(args[0]));
            sender.sendMessage(CommonUtil.getBrandedMsgPrefix(CommonUtil.getPointListHelpLine()));
        }
    }

    @Override
    public String getPermission() {
        return CommonUtil.getPermissionNode();
    }

    @Override
    public String getUsage() {
        return "/point info <name>";
    }

    @Override
    public String getDescription() {
        return "View info for a starting point.";
    }
}
