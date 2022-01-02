package dev.tablesalt.pocketbeacon.settings;

import org.mineacademy.fo.model.SimpleTime;
import org.mineacademy.fo.settings.SimpleSettings;

public class Settings extends SimpleSettings {

	@Override
	protected int getConfigVersion() {
		return 2;
	}


	public static class FuelTypes {

		public static Integer BLOCK_BURN_MULTIPLIER = getInteger("Block_Burn_Multiplier");

		public static Integer COALMULTIPLIER, IRONMULTIPLIER, GOLDMULTIPLIER, EMERALDMULTIPLIER, DIAMONDMULTIPLIER;

		public static SimpleTime COALBURN, IRONBURN, GOLDBURN, EMERALDBURN, DIAMONDBURN;

		//todo update config version
		private static void init() {
			pathPrefix("Fuel_Types");
			COALMULTIPLIER = getInteger("Coal.Multiplier");
			IRONMULTIPLIER = getInteger("Iron.Multiplier");
			GOLDMULTIPLIER = getInteger("Gold.Multiplier");
			EMERALDMULTIPLIER = getInteger("Emerald.Multiplier");
			DIAMONDMULTIPLIER = getInteger("Diamond.Multiplier");

			COALBURN = getTime("Coal.Burn_Time");
			IRONBURN = getTime("Iron.Burn_Time");
			GOLDBURN = getTime("Gold.Burn_Time");
			EMERALDBURN = getTime("Emerald.Burn_Time");
			DIAMONDBURN = getTime("Diamond.Burn_Time");
		}
	}


}
