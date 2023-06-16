package us.magicaldreams.mdpointlocator.commands.subcommands.point;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.magicaldreams.mdpointlocator.MDPointLocator;
import us.magicaldreams.mdpointlocator.command.MDSubCommand;
import us.magicaldreams.mdpointlocator.util.CommonUtil;

public class PointMaterialSubCommand implements MDSubCommand
{
    @Override
    public void onCommand(CommandSender sender, Command command, String[] args) {

        Player player = (Player)sender;
        MDPointLocator plugin = MDPointLocator.getInstance();

        if(CommonUtil.isNullArgument(args, 1)) {
            CommonUtil.getMissingArgsMsg(getUsage());
        } else {
            if(Material.getMaterial(args[1].toUpperCase()).isBlock()) {
                Material block = Material.getMaterial(args[1].toUpperCase());
                plugin.getPlayerData().addToHashMap(player.getUniqueId(), block, -1, null);
                player.sendMessage(CommonUtil.getBrandedMsgPrefix(ChatColor.AQUA + "Point material set to: " + ChatColor.GREEN + args[1].toUpperCase()));
            } else {
                player.sendMessage(CommonUtil.getBrandedMsgPrefix(ChatColor.RED + "The material type you selected is not a block type!"));
            }
        }
    }

    @Override
    public String getPermission() {
        return CommonUtil.getPermissionNode();
    }

    @Override
    public String getUsage() {
        return "/point material <material>";
    }

    @Override
    public String getDescription() {
        return "Sets the block material for plotted points and lines.";
    }
}