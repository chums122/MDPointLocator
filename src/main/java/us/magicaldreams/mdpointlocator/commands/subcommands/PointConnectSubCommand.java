package us.magicaldreams.mdpointlocator.commands.subcommands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.magicaldreams.mdpointlocator.MDPointLocator;
import us.magicaldreams.mdpointlocator.command.MDSubCommand;
import us.magicaldreams.mdpointlocator.util.CommonUtil;
import us.magicaldreams.mdpointlocator.util.PointData;

/**
 * Created by Zackery Anderson
 * ©2023
 */

public class PointConnectSubCommand implements MDSubCommand
{
    @Override
    public void onCommand(CommandSender sender, Command command, String[] args) {

        Player player = (Player) sender;
        MDPointLocator plugin = MDPointLocator.getInstance();

        if(args.length > 1) {
            player.sendMessage(CommonUtil.getTooManyArgsMsg(getUsage()));
            return;
        }

        PointData data = plugin.getPlayerData().getPointData(player.getUniqueId());

        if(data.GetPos1() == null && data.GetPos2() == null) {
            player.sendMessage(CommonUtil.getBrandedMsgPrefix(ChatColor.RED +"Missing position! " + ChatColor.YELLOW + "Please make sure two points have been created!"));
        }
        else {
            player.performCommand("/pos1 " + data.GetPos1().getBlockX() + "," + data.GetPos1().getBlockY() + "," + data.GetPos1().getBlockZ());
            player.performCommand("/pos2 " + data.GetPos2().getBlockX() + "," + data.GetPos2().getBlockY() + "," + data.GetPos2().getBlockZ());
            player.performCommand("/line " + data.GetBlockType());
            player.sendMessage(CommonUtil.getBrandedMsgPrefix(ChatColor.AQUA + "Line created between the points successfully."));
        }
    }

    @Override
    public String getPermission() {
        return CommonUtil.getPermissionNode("connect");
    }

    @Override
    public String getUsage() {
        return "/point connect";
    }

    @Override
    public String getDescription() {
        return "Connect a line between the last two plotted points.";
    }
}