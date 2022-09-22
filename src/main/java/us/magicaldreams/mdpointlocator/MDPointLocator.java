package us.magicaldreams.mdpointlocator;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import us.magicaldreams.mdpointlocator.commands.PointBaseCommand;
import us.magicaldreams.mdpointlocator.commands.subcommands.point.HelpSubCommand;
import us.magicaldreams.mdpointlocator.commands.subcommands.point.PointCreateSubCommand;

import java.util.logging.Level;

public final class MDPointLocator extends JavaPlugin {

    @Override
    public void onEnable() {

        // Config setup
        PointConfig.init();

        // Command setup
        registerCommands();

        Bukkit.getLogger().log(Level.INFO, "MDPointLocator > Plugin loaded successfully");
    }

    public void registerCommands() {
        PointBaseCommand pointBaseCommand = new PointBaseCommand();
        getCommand("point").setExecutor(pointBaseCommand);
        pointBaseCommand.registerCommand("help", new HelpSubCommand());
        pointBaseCommand.registerCommand("create", new PointCreateSubCommand());
    }

}
