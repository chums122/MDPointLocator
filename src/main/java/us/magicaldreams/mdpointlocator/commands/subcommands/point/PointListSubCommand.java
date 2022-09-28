package us.magicaldreams.mdpointlocator.commands.subcommands.point;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import us.magicaldreams.mdpointlocator.util.PointConfig;
import us.magicaldreams.mdpointlocator.command.MDSubCommand;
import us.magicaldreams.mdpointlocator.util.CommonUtil;

/**
 * Created by Trevor Chumbley
 * 9/28/2022 - 1:26 PM
 **/

public class PointListSubCommand implements MDSubCommand {
    @Override
    public void onCommand(CommandSender sender, Command command, String[] args) {
        sender.sendMessage(CommonUtil.getBrandedMsgPrefix(ChatColor.AQUA + "Current start points include: " + ChatColor.GREEN + PointConfig.getConfig().getKeys(false)));
    }

    @Override
    public String getPermission() {
        return CommonUtil.getPermissionNode();
    }

    @Override
    public String getUsage() {
        return "/point list";
    }

    @Override
    public String getDescription() {
        return "Lists available starting points.";
    }
}
