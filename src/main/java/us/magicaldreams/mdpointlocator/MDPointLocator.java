package us.magicaldreams.mdpointlocator;

import org.bukkit.plugin.java.JavaPlugin;
import us.magicaldreams.mdpointlocator.commands.PointBaseCommand;
import us.magicaldreams.mdpointlocator.commands.subcommands.point.*;
import us.magicaldreams.mdpointlocator.util.CommonUtil;
import us.magicaldreams.mdpointlocator.util.PlayerData;
import us.magicaldreams.mdpointlocator.util.PointConfig;

import java.util.logging.Level;

import static us.magicaldreams.mdpointlocator.util.CommonUtil.isClassAvailable;

public final class MDPointLocator extends JavaPlugin {

    private static MDPointLocator instance;
    private static PlayerData playerData;
    private boolean isWorldEditLoaded;

    @Override
    public void onEnable() {

        // Set main instance
        instance = this;

        // New instance of player data
        playerData = new PlayerData();

        // Check if WorldEdit is loaded
        isWorldEditLoaded = isClassAvailable("com.sk89q.worldedit.WorldEdit");

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
        getCommand("point").setTabCompleter(pointBaseCommand);
        pointBaseCommand.registerCommand("help", new PointHelpSubCommand());
        pointBaseCommand.registerCommand("list", new PointListSubCommand());
        pointBaseCommand.registerCommand("info", new PointInfoSubCommand());
        pointBaseCommand.registerCommand("plot", new PointPlotSubCommand());
        pointBaseCommand.registerCommand("create", new PointCreateSubCommand());
        pointBaseCommand.registerCommand("remove", new PointRemoveSubCommand());
        pointBaseCommand.registerCommand("sety", new PointSetYSubCommand());
        pointBaseCommand.registerCommand("material", new PointMaterialSubCommand());

        // If worldedit is loaded, load connect command
        if (isWorldEditLoaded) {
            pointBaseCommand.registerCommand("connect", new PointConnectSubCommand());
        }
    }

    public static MDPointLocator getInstance() {
        return instance;
    }

    public PlayerData getPlayerData() {
        if (playerData == null) {
            playerData = new PlayerData();
        }
        return playerData;
    }


}
