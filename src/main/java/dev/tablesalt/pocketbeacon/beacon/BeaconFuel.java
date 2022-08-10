package dev.tablesalt.pocketbeacon.beacon;

import dev.tablesalt.pocketbeacon.settings.Settings;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.collection.SerializedMap;
import org.mineacademy.fo.model.ConfigSerializable;
import org.mineacademy.fo.settings.YamlConfig;

public class BeaconFuel extends YamlConfig implements ConfigSerializable {

	@Getter
	@Setter
	private ItemStack fuel;

	@Getter
	@Setter
	private boolean isBurning = false;

	public BeaconFuel(ItemStack fuel) {
		this.fuel = fuel;
	}


	public boolean empty() {
		return fuel.getAmount() < 1 || fuel.getType().equals(Material.AIR);
	}

	public void setAmount(int amount) {
		fuel.setAmount(amount);
	}

	@Override
	public SerializedMap saveToMap() {
		SerializedMap map = new SerializedMap();
		map.put("Fuel", fuel);
		return map;
	}

	//-----------------------------------//-----------------------------------//
	//                                  STATIC
	//-----------------------------------//-----------------------------------//

	public static int getTier(BeaconFuel fuel) {

		if(fuel != null)
			switch (getBaseMaterial(fuel.getFuel())) {
				case COAL:
					return 1;
				case DIAMOND:
				case EMERALD:
					return 3;
				case IRON_INGOT:
				case GOLD_INGOT:
					return 2;
		}

		return 0;
	}

	public static boolean isFuel(ItemStack itemStack) {

		return switch (getBaseMaterial(itemStack)) {
			case COAL, DIAMOND, EMERALD, IRON_INGOT, GOLD_INGOT -> true;
			default -> false;
		};
	}

	public static int getEffectMultiplier(BeaconFuel fuel) {

		return switch (getBaseMaterial(fuel.getFuel())) {
			case COAL -> Settings.FuelTypes.COALMULTIPLIER;
			case IRON_INGOT -> Settings.FuelTypes.IRONMULTIPLIER;
			case GOLD_INGOT -> Settings.FuelTypes.GOLDMULTIPLIER;
			case DIAMOND -> Settings.FuelTypes.DIAMONDMULTIPLIER;
			case EMERALD -> Settings.FuelTypes.EMERALDMULTIPLIER;
			default -> 0;
		};
	}

	public static int getBurnTime(BeaconFuel fuel) {
		int burnTime = switch (getBaseMaterial(fuel.getFuel())) {
			case COAL -> Settings.FuelTypes.COALBURN.getTimeTicks();
			case IRON_INGOT -> Settings.FuelTypes.IRONBURN.getTimeTicks();
			case GOLD_INGOT -> Settings.FuelTypes.GOLDBURN.getTimeTicks();
			case DIAMOND -> Settings.FuelTypes.DIAMONDBURN.getTimeTicks();
			case EMERALD -> Settings.FuelTypes.EMERALDBURN.getTimeTicks();
			default -> 20;
		};

		if (fuel.getFuel().getType().isBlock() && isFuel(fuel.getFuel())) {
			burnTime *= Settings.FuelTypes.BLOCK_BURN_MULTIPLIER;
		}
		return burnTime;
	}

	//kinda hacky but works for now.
	private static Material getBaseMaterial(ItemStack item) {

		if (item == null) return Material.AIR;

		Material type = item.getType();

		if (type.isBlock() && type.name().contains("_BLOCK")) {
			String[] words = type.name().split("_");
			String baseName = words[0];

			switch (baseName.toUpperCase()) {
				case "COAL":
					return Material.COAL;
				case "IRON":
					return Material.IRON_INGOT;
				case "GOLD":
					return Material.GOLD_INGOT;
				case "DIAMOND":
					return Material.DIAMOND;
				case "EMERALD":
					return Material.EMERALD;
			}
		}
		return type;

	}

	public static BeaconFuel deserialize(SerializedMap map) {
		try {
			return new BeaconFuel(map.getItemStack("Fuel"));
		} catch (Exception ignored){}
		return new BeaconFuel(new ItemStack(Material.AIR));
	}


}
