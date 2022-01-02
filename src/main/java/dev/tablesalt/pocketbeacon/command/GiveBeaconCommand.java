package dev.tablesalt.pocketbeacon.command;

import dev.tablesalt.pocketbeacon.beacon.BeaconUtil;
import org.bukkit.entity.Player;
import org.mineacademy.fo.command.SimpleCommandGroup;
import org.mineacademy.fo.remain.CompSound;

public class GiveBeaconCommand extends TargetedCommand {


	protected GiveBeaconCommand(final SimpleCommandGroup parent) {
		super(parent, "give");
		setPermission("pocketbeacon.give");
		setDescription("&7Gives the target a pocket beacon");
	}

	protected void onCommandFor(Player target) {
		checkPerm("pocketbeacon.give");
		BeaconUtil.giveBeacon(target);
		CompSound.ITEM_PICKUP.play(target);
	}
}
