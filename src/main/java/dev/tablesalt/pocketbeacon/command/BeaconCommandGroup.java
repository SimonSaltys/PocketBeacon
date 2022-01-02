package dev.tablesalt.pocketbeacon.command;

import org.mineacademy.fo.command.SimpleCommandGroup;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BeaconCommandGroup extends SimpleCommandGroup {

	@Getter
	private final static SimpleCommandGroup instance = new BeaconCommandGroup();

	@Override
	protected void registerSubcommands() {

		registerSubcommand(new GiveBeaconCommand(this));
	}

	@Override
	protected String getCredits() {
		return "Visit the (wiki site) for more info.";
	}
}