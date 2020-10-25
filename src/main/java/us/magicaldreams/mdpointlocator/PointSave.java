package us.magicaldreams.mdpointlocator;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.FileConfiguration;

public class PointSave {

    //private static String filename = "pointLocation.yml";
    private static File file;
    private static FileConfiguration customFile;

    // Creates or finds the PointLocator config
    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("MDPointLocator").getDataFolder(), "pointLocator.yml");

        // Generates config if it does not exist
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                //
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get() {
        return customFile;
    }

    // Saves PointLocator config
    public static void save() {
        try {
            customFile.save(file);
        } catch (IOException e) {
            System.out.println("MDPointLocator > Error saving point locator config file...");
        }
    }

    // Reloads the PointLocator config
    public static void reload() {
        customFile = YamlConfiguration.loadConfiguration(file);
    }
}

