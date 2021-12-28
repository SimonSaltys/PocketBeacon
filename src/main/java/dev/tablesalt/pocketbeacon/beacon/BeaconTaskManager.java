package dev.tablesalt.pocketbeacon.beacon;

import dev.tablesalt.pocketbeacon.BeaconPlugin;
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
			Common.broadcast("Starting Runnable");
			runnable.runTaskTimer(BeaconPlugin.getInstance(), 0, 5);
			runnableMap.put(player.getUniqueId(), runnable);
		}


	}

	public void stop(Player player) {
		if (runnableMap.containsKey(player.getUniqueId())) {
			Common.broadcast("Stopping Runnable");
			runnableMap.remove(player.getUniqueId());
		}


	}

}
