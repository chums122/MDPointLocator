package us.magicaldreams.mdpointlocator.commands.subcommands.point;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import us.magicaldreams.mdpointlocator.command.MDSubCommand;
import us.magicaldreams.mdpointlocator.util.CommonUtil;

/**
 * Created by Trevor Chumbley
 * 9/22/2022 - 1:41 PM
 **/

public class PointPlotSubCommand implements MDSubCommand {
    @Override
    public void onCommand(CommandSender sender, Command command, String[] args) {

    }

    public String getPermission() {
        return CommonUtil.getPermissionNode("create");
    }

    public String getUsage() {
        return "/point plot <name> <length> <heading>";
    }

    public String getDescription() {
        return "Plot a point.";
    }
}
