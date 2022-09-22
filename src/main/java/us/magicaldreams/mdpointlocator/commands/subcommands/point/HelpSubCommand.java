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
 * 9/21/2022 - 2:37 PM
 **/

public class HelpSubCommand implements MDSubCommand {

    @Override
    public void onCommand(CommandSender sender, Command command, String[] args) {
        Map<String, MDSubCommand> commands = PointBaseCommand.getCommands();

        sender.sendMessage(CommonUtil.getBrandedMsgPrefix(ChatColor.AQUA + "Point Locator Commands:"));
        for (Map.Entry<String, MDSubCommand> entry : commands.entrySet()) {
            if (sender.hasPermission(entry.getValue().getPermission())) {
                sender.sendMessage(CommonUtil.getHelpEntry(entry.getValue().getUsage(), entry.getValue().getDescription()));
            }
        }
    }

    public String getPermission() {
        return CommonUtil.getPermissionNode();
    }

    @Override
    public String getUsage() {
        return "/point help";
    }

    public String getDescription() {
        return "Displays this help menu.";
    }

}
