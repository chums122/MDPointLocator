package us.magicaldreams.mdpointlocator.commands.subcommands.point;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import us.magicaldreams.mdpointlocator.command.MDSubCommand;
import us.magicaldreams.mdpointlocator.commands.PointBaseCommand;
import us.magicaldreams.mdpointlocator.util.CommonUtil;

import java.util.Map;

/**
 * Created by Trevor Chumbley
 * 9/21/2022 - 2:38 PM
 **/

public class PointCreateSubCommand implements MDSubCommand {

    @Override
    public void onCommand(CommandSender sender, Command command, String[] args) {
        sender.sendMessage("works");
    }

    public String getPermission() {
        return CommonUtil.getPermissionNode();
    }

    @Override
    public String getUsage() {
        return "/point create <> <> <>";
    }

    public String getDescription() {
        return "Create a new global starting point.";
    }

}
