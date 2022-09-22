package us.magicaldreams.mdpointlocator;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.FileConfiguration;

public class PointConfig {

    //private static String filename = "pointLocation.yml";
    private static File file;
    private static FileConfiguration customFile;

    public static void init() {
        setupConfig();
        getConfig().options().copyDefaults();
        saveConfig();
    }

    // Creates or finds the PointLocator config
    public static void setupConfig() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("MDPointLocator").getDataFolder(), "pointLocator.yml");

        // Generates config if it does not exist
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                Bukkit.getLogger().log(Level.SEVERE, "MDPointLocator > Error creating point locator config file...");
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration getConfig() {
        return customFile;
    }

    // Saves PointLocator config
    public static void saveConfig() {
        try {
            customFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
            Bukkit.getLogger().log(Level.SEVERE, "MDPointLocator > Error saving point locator config file...");
        }
    }

    // Reloads the PointLocator config
    public static void reloadConfig() {
        customFile = YamlConfiguration.loadConfiguration(file);
    }
}

