package us.magicaldreams.mdpointlocator;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import us.magicaldreams.mdpointlocator.commands.PointBaseCommand;
import us.magicaldreams.mdpointlocator.commands.subcommands.point.*;
import us.magicaldreams.mdpointlocator.util.CommonUtil;
import us.magicaldreams.mdpointlocator.util.PointConfig;

import java.util.logging.Level;

public final class MDPointLocator extends JavaPlugin {

    private static MDPointLocator instance;

    @Override
    public void onEnable() {

        // Set main instance
        instance = this;

        // Config setup
        PointConfig.init();

        // Command setup
        registerCommands();

        // Log startup message
        getLogger().log(Level.INFO, CommonUtil.getBrandedConsoleMsg("Enabled v" + CommonUtil.getPluginVersion()));
    }

    @Override
    public void onDisable() {
        // Save config
        PointConfig.saveConfig();
    }

    private void registerCommands() {
        PointBaseCommand pointBaseCommand = new PointBaseCommand();
        getCommand("point").setExecutor(pointBaseCommand);
        pointBaseCommand.registerCommand("help", new PointHelpSubCommand());
        pointBaseCommand.registerCommand("list", new PointListSubCommand());
        pointBaseCommand.registerCommand("info", new PointInfoSubCommand());
        pointBaseCommand.registerCommand("plot", new PointPlotSubCommand());
        pointBaseCommand.registerCommand("create", new PointCreateSubCommand());
        pointBaseCommand.registerCommand("remove", new PointRemoveSubCommand());
    }

    public static MDPointLocator getInstance() {
        return instance;
    }

}
