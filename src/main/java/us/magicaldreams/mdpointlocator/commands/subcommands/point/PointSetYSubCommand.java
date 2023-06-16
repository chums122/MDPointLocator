package us.magicaldreams.mdpointlocator.commands.subcommands.point;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.magicaldreams.mdpointlocator.MDPointLocator;
import us.magicaldreams.mdpointlocator.command.MDSubCommand;
import us.magicaldreams.mdpointlocator.util.CommonUtil;
import us.magicaldreams.mdpointlocator.util.PlayerData;

public class PointSetYSubCommand implements MDSubCommand
{
    @Override
    public void onCommand(CommandSender sender, Command command, String[] args) {

        Player player = (Player)sender;
        MDPointLocator plugin = MDPointLocator.getInstance();

        if(CommonUtil.isNullArgument(args, 1)) {
            CommonUtil.getMissingArgsMsg(getUsage());
        } else {
            PlayerData data = plugin.getPlayerData();
            data.addToHashMap(player.getUniqueId(), Material.AIR, Integer.parseInt(args[1]), null);
            player.sendMessage(CommonUtil.getBrandedMsgPrefix(ChatColor.AQUA + "Points will now spawn at Y: " + ChatColor.GREEN + args[1]));
        }

    }

    @Override
    public String getPermission() {
        return CommonUtil.getPermissionNode();
    }

    @Override
    public String getUsage() {
        return "/point sety <value>";
    }

    @Override
    public String getDescription() {
        return "Sets the Y value for plotted points.";
    }
}