package dev.tablesalt.pocketbeacon;

import dev.tablesalt.pocketbeacon.beacon.BeaconFuel;
import dev.tablesalt.pocketbeacon.beacon.BeaconState;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerCache {

    @Getter
    @Setter
    private BeaconState currentState = BeaconState.NO_EFFECT;

    @Getter
    @Setter
    private BeaconFuel beaconFuel;


    //-----------------------------------//-----------------------------------//
    //                                  STATIC
    //-----------------------------------//-----------------------------------//

    @Getter
    private static Map<UUID, PlayerCache> cacheMap = new HashMap<>();


    public static PlayerCache getCache(Player player) {
        PlayerCache cache = cacheMap.get(player.getUniqueId());

        if (cache == null) {
            cache = new PlayerCache();
            cacheMap.put(player.getUniqueId(), cache);
        }
        return cache;
    }


}
