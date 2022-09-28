package us.magicaldreams.mdpointlocator.commands.subcommands.point;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.magicaldreams.mdpointlocator.PointConfig;
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
        if (args.length < 3) {
            player.sendMessage(CommonUtil.getBrandedMsgPrefix(ChatColor.RED + "Missing arguments! Please use " + getUsage()));
            return;
        } else if (args.length > 4) {
            player.sendMessage(CommonUtil.getBrandedMsgPrefix(ChatColor.RED + "Too many arguments! Please use " + getUsage()));
            return;
        } else if (!CommonUtil.isDouble(args[1]) || !CommonUtil.isDouble(args[2])) {
            // If length and heading are not integers/double
            player.sendMessage(CommonUtil.getBrandedMsgPrefix(ChatColor.RED + "Length and Heading must be numbers! Please use /" + getUsage()));
            return;
        }

        if (PointConfig.getConfig().contains(args[0])) {
            List<String> coords = PointConfig.getConfig().getStringList(args[0]);

            int x = Integer.parseInt(coords.get(0));
            int z = Integer.parseInt(coords.get(1));

            double length = Double.parseDouble(args[1]);
            double heading = Double.parseDouble(args[2]);

            World world = player.getWorld();
            double y = player.getLocation().getY();
            float yaw = player.getLocation().getYaw();
            float pitch = player.getLocation().getPitch();

            double scaleMultiplier = 1;
            try {
                scaleMultiplier = Double.parseDouble(coords.get(2));
            } catch (NullPointerException ignored) {
                // scale = 1.0
            }

            Location loc = CommonUtil.getPointLocation(length, heading, scaleMultiplier, world, x, y, z, yaw, pitch);

            player.sendMessage(CommonUtil.getBrandedMsgPrefix("Teleported to " + ChatColor.GREEN + "X:" + x + " Y:" + y + " Z:" + z));
            loc.getBlock().setType(Material.GLASS);
            player.teleport(loc);
        } else {
            player.sendMessage(CommonUtil.getBrandedMsgPrefix(ChatColor.RED + "Start point " + ChatColor.YELLOW + args[0] + ChatColor.RED + "does not exist!"));
        }
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
