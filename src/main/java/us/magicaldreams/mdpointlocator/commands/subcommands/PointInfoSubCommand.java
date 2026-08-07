package us.magicaldreams.mdpointlocator.commands.subcommands;

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

        if (args.length < 2) {
            sender.sendMessage(CommonUtil.getMissingArgsMsg(getUsage()));
            return;
        } else if (args.length > 2) {
            sender.sendMessage(CommonUtil.getTooManyArgsMsg(getUsage()));
            return;
        }

        String plotName = args[1];

        if (PointConfig.getConfig().contains(plotName)) {

            List<String> coords = PointConfig.getConfig().getStringList(plotName);
            String x = coords.get(0);
            String z = coords.get(1);
            String scaleMultiplier = "1";

            try {
                scaleMultiplier = coords.get(2);
            } catch (NullPointerException ignored) {
                // scale = 1.0
            }

            sender.sendMessage(CommonUtil.getBrandedMsgPrefix(ChatColor.YELLOW + "Showing point info for " + ChatColor.GOLD + plotName + ":"));
            sender.sendMessage(CommonUtil.getBrandedMsgPrefix(
                    ChatColor.GOLD + "X: " + ChatColor.YELLOW + x + " " +
                    ChatColor.GOLD + "Z: " + ChatColor.YELLOW + z + " " +
                    ChatColor.GOLD + "Scale: " + ChatColor.YELLOW + scaleMultiplier + ":1"));
        } else {
            sender.sendMessage(CommonUtil.getStartPointNotExistMsg(plotName));
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
