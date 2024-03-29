package us.magicaldreams.mdpointlocator.commands.subcommands.point;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.magicaldreams.mdpointlocator.util.PointConfig;
import us.magicaldreams.mdpointlocator.command.MDSubCommand;
import us.magicaldreams.mdpointlocator.util.CommonUtil;

import java.util.List;

/**
 * Created by Trevor Chumbley
 * 9/22/2022 - 1:41 PM
 **/

public class PointPlotSubCommand implements MDSubCommand {
    @Override
    public void onCommand(CommandSender sender, Command command, String[] args) {
        Player player = (Player) sender;

        // If arguments are missing
        if (args.length < 4) {
            player.sendMessage(CommonUtil.getMissingArgsMsg(getUsage()));
            return;
        } else if (args.length > 4) {
            player.sendMessage(CommonUtil.getTooManyArgsMsg(getUsage()));
            return;
        } else if (!CommonUtil.isDouble(args[2]) || !CommonUtil.isDouble(args[3])) {
            // If length and heading are not integers/double
            player.sendMessage(CommonUtil.getBrandedMsgPrefix(ChatColor.RED + "Length and Heading must be numbers! Please use /" + getUsage()));
            return;
        }

        String plotName = args[1];
        double length = Double.parseDouble(args[2]);
        double heading = Double.parseDouble(args[3]);

        if (PointConfig.getConfig().contains(plotName)) {
            List<String> coords = PointConfig.getConfig().getStringList(plotName);

            int x = Integer.parseInt(coords.get(0));
            int z = Integer.parseInt(coords.get(1));

            World world = player.getWorld();
            int y = (int) player.getLocation().getY();
            float yaw = player.getLocation().getYaw();
            float pitch = player.getLocation().getPitch();

            double scaleMultiplier = 1;
            if (coords.size() == 3) {
                scaleMultiplier = Double.parseDouble(coords.get(2));
            }

            Location loc = CommonUtil.getPointLocation(length, heading, scaleMultiplier, world, x, y, z, yaw, pitch);

            player.sendMessage(CommonUtil.getBrandedMsgPrefix(ChatColor.AQUA + "Teleported to " + ChatColor.GREEN + "X:" + loc.getX() + " Y:" + loc.getY() + " Z:" + loc.getZ()));
            loc.getBlock().setType(Material.GLASS);
            player.teleport(loc);
        } else {
            sender.sendMessage(CommonUtil.getStartPointNotExistMsg(plotName));
            player.sendMessage(CommonUtil.getPointListHelpLine());
        }
    }

    public String getPermission() {
        return CommonUtil.getPermissionNode();
    }

    public String getUsage() {
        return "/point plot <name> <length> <heading>";
    }

    public String getDescription() {
        return "Plot a point.";
    }
}
