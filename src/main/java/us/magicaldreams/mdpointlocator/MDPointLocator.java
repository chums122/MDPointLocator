package us.magicaldreams.mdpointlocator;

import org.bukkit.plugin.java.JavaPlugin;

public final class MDPointLocator extends JavaPlugin {

    @Override
    public void onEnable() {

        // Config setup for PointLocator
        PointSave.setup();
        PointSave.get().options().copyDefaults();
        PointSave.save();

        this.getCommand("point").setExecutor(new PointCommand());
        this.getCommand("point15").setExecutor(new PointCommand());
        this.getCommand("point2").setExecutor(new PointCommand());
        this.getCommand("pointsave").setExecutor(new PointCommand());
        this.getCommand("pointdel").setExecutor(new PointCommand());
        this.getCommand("pointlist").setExecutor(new PointCommand());
        this.getCommand("pointinfo").setExecutor(new PointCommand());
        System.out.println("MDPointLocator > Plugin loaded successfully");
    }

}
