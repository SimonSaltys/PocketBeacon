package dev.tablesalt.pocketbeacon.command;

import dev.tablesalt.pocketbeacon.beacon.BeaconTaskManager;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.command.SimpleCommandGroup;
import org.mineacademy.fo.command.SimpleSubCommand;

public class BeaconDebugCommand extends SimpleSubCommand {

	protected BeaconDebugCommand(final SimpleCommandGroup parent) {
		super(parent, "debug");
		setDescription("&7Debugs the Plugin");
	}


	@Override
	protected void onCommand() {
		Common.broadcast(BeaconTaskManager.getInstance().getRunnableMap().toString());
	}
}
