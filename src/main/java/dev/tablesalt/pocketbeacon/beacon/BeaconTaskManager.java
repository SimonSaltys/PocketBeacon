package dev.tablesalt.pocketbeacon.beacon;

import com.comphenix.protocol.PacketType;
import dev.tablesalt.pocketbeacon.BeaconPlugin;
import dev.tablesalt.pocketbeacon.PlayerCache;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.block.Beacon;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.Valid;

import java.util.HashMap;
import java.util.UUID;

public class BeaconTaskManager {

	@Getter
	private final static BeaconTaskManager instance = new BeaconTaskManager();

	@Getter
	private HashMap<UUID, BukkitRunnable> runnableMap = new HashMap<>();


	private BeaconTaskManager() {
	}

	public void start(Player player, BukkitRunnable runnable) {
		if (!runnableMap.containsKey(player.getUniqueId())) {
			PlayerCache.getCache(player).saveData();
			runnable.runTaskTimer(BeaconPlugin.getInstance(), 0, 5);
			runnableMap.put(player.getUniqueId(), runnable);
		}


	}

	public void stop(Player player) {
		if (runnableMap.containsKey(player.getUniqueId())) {
			PlayerCache.getCache(player).saveData();
			runnableMap.get(player.getUniqueId()).cancel();
			runnableMap.remove(player.getUniqueId());

		}
	}

}
