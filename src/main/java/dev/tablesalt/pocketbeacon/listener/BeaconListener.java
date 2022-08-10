package dev.tablesalt.pocketbeacon.listener;

import dev.tablesalt.pocketbeacon.beacon.BeaconMenu;
import dev.tablesalt.pocketbeacon.beacon.BeaconTaskManager;
import dev.tablesalt.pocketbeacon.beacon.BeaconUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.Common;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeaconListener implements Listener {

	@Getter
	private static final BeaconListener instance = new BeaconListener();

	@EventHandler
	public void onItemClick(PlayerInteractEvent event) {


		Player player = event.getPlayer();

		if ((event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) &&
				(player.getInventory().getItemInMainHand().isSimilar(BeaconUtil.getBeaconItem()))) {

			new BeaconMenu().displayTo(player);
		}

	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		if (!player.getDiscoveredRecipes().contains(BeaconUtil.getKey())) {
			player.discoverRecipe(BeaconUtil.getKey());
			Common.tell(player, "&bPssssst &9You Unlocked the pocket beacon recipe.");
		}


	}


	@EventHandler
	public void onBeaconPlace(BlockPlaceEvent event) {
		ItemStack beacon = BeaconUtil.getBeaconItem();

		if (event.getItemInHand().isSimilar(beacon)) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		event.getPlayer().updateInventory();
		BeaconTaskManager.getInstance().stop(event.getPlayer());
	}


}
