package dev.tablesalt.pocketbeacon.beacon;

import dev.tablesalt.pocketbeacon.PlayerCache;
import dev.tablesalt.pocketbeacon.settings.Settings;
import github.scarsz.discordsrv.dependencies.trove.iterator.TPrimitiveIterator;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.Valid;
import org.mineacademy.fo.collection.SerializedMap;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.model.ConfigSerializable;
import org.mineacademy.fo.remain.CompMaterial;
import org.mineacademy.fo.settings.YamlConfig;

import java.awt.*;
import java.util.HashMap;
import java.util.UUID;

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


	public boolean isEmpty() {
		return fuel.getAmount() < 1 || fuel.getType().equals(Material.AIR);
	}

	public void setAmount(int amount) {
		fuel.setAmount(amount);
	}

	@Override
	public SerializedMap serialize() {
		SerializedMap map = new SerializedMap();
		map.put("Fuel", fuel);
		return map;
	}

	//-----------------------------------//-----------------------------------//
	//                                  STATIC
	//-----------------------------------//-----------------------------------//


	public static int getTier(ItemStack itemStack) {

		switch (itemStack.getType()) {
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

		switch (itemStack.getType()) {
			case COAL:
			case DIAMOND:
			case EMERALD:
			case IRON_INGOT:
			case GOLD_INGOT:
				return true;
			default:
				return false;
		}
	}

	public static int getEffectMultiplier(ItemStack itemStack) {
		switch (itemStack.getType()) {
			case COAL:
				return Settings.FuelTypes.COALMULTIPLIER;
			case IRON_INGOT:
				return Settings.FuelTypes.IRONMULTIPLIER;
			case GOLD_INGOT:
				return Settings.FuelTypes.GOLDMULTIPLIER;
			case DIAMOND:
				return Settings.FuelTypes.DIAMONDMULTIPLIER;
			case EMERALD:
				return Settings.FuelTypes.EMERALDMULTIPLIER;

			default:
				return 0;
		}
	}

	public static int getBurnTime(ItemStack itemStack) {
		switch (itemStack.getType()) {
			case COAL:
				return Settings.FuelTypes.COALBURN;
			case IRON_INGOT:
				return Settings.FuelTypes.IRONBURN;
			case GOLD_INGOT:
				return Settings.FuelTypes.GOLDBURN;
			case DIAMOND:
				return Settings.FuelTypes.DIAMONDBURN;
			case EMERALD:
				return Settings.FuelTypes.EMERALDBURN;
			default:
				return 20;
		}
	}

	public static BeaconFuel deserialize(SerializedMap map) {
		return new BeaconFuel(map.getItem("Fuel"));
	}


}
