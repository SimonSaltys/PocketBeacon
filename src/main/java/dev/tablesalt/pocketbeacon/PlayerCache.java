package dev.tablesalt.pocketbeacon;

import dev.tablesalt.pocketbeacon.beacon.BeaconFuel;
import dev.tablesalt.pocketbeacon.beacon.BeaconState;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompMaterial;
import org.mineacademy.fo.settings.YamlSectionConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerCache extends YamlSectionConfig {

	@Getter
	@Setter
	private BeaconState currentState = BeaconState.NO_EFFECT;

	@Getter
	@Setter
	private BeaconFuel beaconFuel;


	protected PlayerCache(final String uuid) {
		super(uuid);
		loadConfiguration(null, "data.db");
	}


	@Override
	protected void onLoadFinish() {
		if (isSet("Beacon_Fuel"))
			beaconFuel = BeaconFuel.deserialize(getMap("Beacon_Fuel"));

		if (isSet("Effect_State"))
			currentState = getEnum("Effect_State", BeaconState.class);
	}

	public void saveData() {


		if (beaconFuel != null) {
			save("Beacon_Fuel", beaconFuel.serialize());
			save("Effect_State", currentState);
		} else {
			save("Beacon_Fuel", ItemCreator.of(CompMaterial.AIR).build().make());
			save("Effect_State", BeaconState.NO_EFFECT);

		}

	}
	//-----------------------------------//-----------------------------------//
	//                                  STATIC
	//-----------------------------------//-----------------------------------//

	@Getter
	private static Map<UUID, PlayerCache> cacheMap = new HashMap<>();

	public static PlayerCache getCache(Player player) {
		PlayerCache cache = cacheMap.get(player.getUniqueId());

		if (cache == null) {
			cache = new PlayerCache(player.getUniqueId().toString());
			cacheMap.put(player.getUniqueId(), cache);
		}
		return cache;
	}
}
