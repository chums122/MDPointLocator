package us.magicaldreams.mdpointlocator;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import us.magicaldreams.mdpointlocator.commands.PointBaseCommand;
import us.magicaldreams.mdpointlocator.commands.subcommands.point.*;
import us.magicaldreams.mdpointlocator.util.CommonUtil;
import us.magicaldreams.mdpointlocator.util.PointConfig;

import java.util.logging.Level;

import java.util.logging.Level;

public final class MDPointLocator extends JavaPlugin {

    private static MDPointLocator instance;

    @Override
    public void onEnable() {

<<<<<<< HEAD
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
=======
        // Config setup for PointLocator
        PointConfig.init();

        this.getCommand("point").setExecutor(new PointCommand());
        this.getCommand("point15").setExecutor(new PointCommand());
        this.getCommand("point2").setExecutor(new PointCommand());
        this.getCommand("pointsave").setExecutor(new PointCommand());
        this.getCommand("pointdel").setExecutor(new PointCommand());
        this.getCommand("pointlist").setExecutor(new PointCommand());
        this.getCommand("pointinfo").setExecutor(new PointCommand());

        Bukkit.getLogger().log(Level.INFO, "MDPointLocator > Plugin loaded successfully");
>>>>>>> main
    }

}
