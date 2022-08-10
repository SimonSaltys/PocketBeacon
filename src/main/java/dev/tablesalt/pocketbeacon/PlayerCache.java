package dev.tablesalt.pocketbeacon;

import dev.tablesalt.pocketbeacon.beacon.BeaconFuel;
import dev.tablesalt.pocketbeacon.beacon.BeaconState;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.settings.YamlConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class PlayerCache extends YamlConfig {

	@Getter
	@Setter
	private BeaconState currentState = BeaconState.NO_EFFECT;

	@Getter
	@Setter
	private BeaconFuel beaconFuel;
	private final UUID uuid;

	protected PlayerCache(final String uuid) {
		this.uuid = UUID.fromString(uuid);
		loadConfiguration(null, "data.db");
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		if (isSet(uuid + "Beacon_Fuel"))
			beaconFuel = BeaconFuel.deserialize(getMap(uuid + "Beacon_Fuel"));

		if (isSet(uuid +"Effect_State"))
			currentState = get(uuid +"Effect_State", BeaconState.class); else
				currentState = BeaconState.NO_EFFECT;

	}

	public void saveData() {


		if (beaconFuel != null) {
			save(uuid + "Beacon_Fuel", beaconFuel.saveToMap());
			save(uuid + "Effect_State", currentState);
		} else {
			save(uuid + "Beacon_Fuel",  new BeaconFuel(new ItemStack(Material.AIR)).saveToMap());
			save(uuid + "Effect_State", BeaconState.NO_EFFECT);

		}

	}
	//-----------------------------------//-----------------------------------//
	//                                  STATIC
	//-----------------------------------//-----------------------------------//

	@Getter
	private static final Map<UUID, PlayerCache> cacheMap = new HashMap<>();

	public static PlayerCache getCache(Player player) {
		PlayerCache cache = cacheMap.get(player.getUniqueId());

		if (cache == null) {
			cache = new PlayerCache(player.getUniqueId().toString());
			cacheMap.put(player.getUniqueId(), cache);
		}
		return cache;
	}
}
