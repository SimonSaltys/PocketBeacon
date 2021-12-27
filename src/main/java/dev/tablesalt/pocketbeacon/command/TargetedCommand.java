package dev.tablesalt.pocketbeacon.command;

import org.bukkit.entity.Player;
import org.mineacademy.fo.command.SimpleCommandGroup;
import org.mineacademy.fo.command.SimpleSubCommand;

public abstract class TargetedCommand extends SimpleSubCommand {

	public TargetedCommand(SimpleCommandGroup parent, String sublabel) {
		super(parent, sublabel);

		setMinArguments(1);
		setUsage("<target>");

	}

	@Override
	protected final void onCommand() {
		final Player target = findPlayer(args[0], "&cUnable to find player " + args[0]);

		onCommandFor(target);


	}

	protected abstract void onCommandFor(Player target);


}
