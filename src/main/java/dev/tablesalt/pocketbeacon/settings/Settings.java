package dev.tablesalt.pocketbeacon.settings;

import org.mineacademy.fo.model.SimpleTime;
import org.mineacademy.fo.settings.SimpleSettings;

import java.util.List;
import java.util.Map;

public class Settings extends SimpleSettings {

	@Override
	protected int getConfigVersion() {
		return 3;
	}


	public static class FuelTypes {

		public static Integer BLOCK_BURN_MULTIPLIER = getInteger("Block_Burn_Multiplier");

		public static Integer COALMULTIPLIER, IRONMULTIPLIER, GOLDMULTIPLIER, EMERALDMULTIPLIER, DIAMONDMULTIPLIER;

		public static SimpleTime COALBURN, IRONBURN, GOLDBURN, EMERALDBURN, DIAMONDBURN;

		//todo update config version
		private static void init() {
			COALMULTIPLIER = getInteger("Fuel_Types.Coal.Multiplier");
			IRONMULTIPLIER = getInteger("Fuel_Types.Iron.Multiplier");
			GOLDMULTIPLIER = getInteger("Fuel_Types.Gold.Multiplier");
			EMERALDMULTIPLIER = getInteger("Fuel_Types.Emerald.Multiplier");
			DIAMONDMULTIPLIER = getInteger("Fuel_Types.Diamond.Multiplier");

			COALBURN = getTime("Fuel_Types.Coal.Burn_Time");
			IRONBURN = getTime("Fuel_Types.Iron.Burn_Time");
			GOLDBURN = getTime("Fuel_Types.Gold.Burn_Time");
			EMERALDBURN = getTime("Fuel_Types.Emerald.Burn_Time");
			DIAMONDBURN = getTime("Fuel_Types.Diamond.Burn_Time");
		}
	}

	public static class Recipe {

		public static List<String> SHAPE = getStringList("Recipe.Shape");
		public static Map<String, String> INGREDIENTS;

		private static void init() {
			INGREDIENTS = getMap("Recipe.Ingredients", String.class, String.class);
			for (String key : INGREDIENTS.keySet()) {
				if (key.length() != 1) {
					throw new IllegalArgumentException("Recipe.Ingredient keys should be single characters, matching those found in Recipe.Shape");
				}
			}
		}
	}

//	public static class Recipe {
//		public static String RECIPE = getString("Recipe");
//	}


}
