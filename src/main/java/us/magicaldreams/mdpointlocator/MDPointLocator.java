package us.magicaldreams.mdpointlocator;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class MDPointLocator extends JavaPlugin {

    @Override
    public void onEnable() {

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
    }

}
