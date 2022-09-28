package us.magicaldreams.mdpointlocator;

import org.bukkit.plugin.java.JavaPlugin;
import us.magicaldreams.mdpointlocator.commands.PointBaseCommand;
import us.magicaldreams.mdpointlocator.commands.subcommands.point.PointHelpSubCommand;
import us.magicaldreams.mdpointlocator.commands.subcommands.point.PointCreateSubCommand;
import us.magicaldreams.mdpointlocator.commands.subcommands.point.PointPlotSubCommand;
import us.magicaldreams.mdpointlocator.commands.subcommands.point.PointRemoveSubCommand;
import us.magicaldreams.mdpointlocator.util.CommonUtil;

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
        pointBaseCommand.registerCommand("plot", new PointPlotSubCommand());
        pointBaseCommand.registerCommand("create", new PointCreateSubCommand());
        pointBaseCommand.registerCommand("remove", new PointRemoveSubCommand());
    }

    public static MDPointLocator getInstance() {
        return instance;
    }

}
