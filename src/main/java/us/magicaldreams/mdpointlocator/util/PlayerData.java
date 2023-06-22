package us.magicaldreams.mdpointlocator.util;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Zackery Anderson
 * ©2023
 */

public class PlayerData {

    public static HashMap<UUID, PointData> playerData;

    public void addToHashMap(UUID playerId, Material block, int y, Location pos) {
        PointData data;

        if (playerId != null) {
            if (playerData == null) {
                data = getPointData(block, y, pos);

                playerData = new HashMap<>();
                playerData.put(playerId, data);

            } else {

                if (!playerData.isEmpty() && playerData.containsKey(playerId)) {
                    data = playerData.get(playerId);

                    if (block != Material.AIR) {
                        data.SetBlock(block);
                    }

                    if (y != -1) {
                        data.SetY(y);
                    }

                    if (pos != null) { data.SavePosition(pos); }

                    playerData.replace(playerId, data);
                }
                else {
                    data = getPointData(block, y, pos);

                    playerData.put(playerId, data);
                }
            }
        }
    }

    private PointData getPointData(Material block, int y, Location pos) {
        PointData data;
        data = new PointData();

        if (block != Material.AIR) { data.SetBlock(block); }
        if (y != -1) { data.SetY(y); }
        if (pos != null) { data.SavePosition(pos); }
        return data;
    }

    public PointData getPointData(UUID playerId) {
        PointData data;

        if (playerId != null) {
            if (!playerData.isEmpty() && playerData.containsKey(playerId)) {
                data = playerData.get(playerId);
                return data;
            }
        }
        return null;
    }

    public void clearHashMap() {
        playerData.clear();
    }
}
