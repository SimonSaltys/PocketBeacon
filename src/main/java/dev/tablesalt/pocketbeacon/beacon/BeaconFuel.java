package dev.tablesalt.pocketbeacon.beacon;

import dev.tablesalt.pocketbeacon.PlayerCache;
import dev.tablesalt.pocketbeacon.settings.Settings;
import github.scarsz.discordsrv.dependencies.trove.iterator.TPrimitiveIterator;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.block.Beacon;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.ItemUtil;
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
            case COAL_BLOCK:
                return 1;
            case DIAMOND:
            case DIAMOND_BLOCK:
            case EMERALD:
            case EMERALD_BLOCK:
                return 3;
            case IRON_INGOT:
            case IRON_BLOCK:
            case GOLD_INGOT:
            case GOLD_BLOCK:
                return 2;
        }

        return 0;
    }

    public static boolean isFuel(ItemStack itemStack) {
        switch (itemStack.getType()) {
            case COAL:
            case COAL_BLOCK:
            case DIAMOND:
            case DIAMOND_BLOCK:
            case EMERALD:
            case EMERALD_BLOCK:
            case IRON_INGOT:
            case IRON_BLOCK:
            case GOLD_INGOT:
            case GOLD_BLOCK:
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

        int burnTime = 0;

        switch (itemStack.getType()) {
            case COAL:
            case COAL_BLOCK:
                burnTime += Settings.FuelTypes.COALBURN;
                break;
            case IRON_INGOT:
            case IRON_BLOCK:
                burnTime += Settings.FuelTypes.IRONBURN;
                break;
            case GOLD_INGOT:
            case GOLD_BLOCK:
                burnTime += Settings.FuelTypes.GOLDBURN;
                break;
            case DIAMOND:
            case DIAMOND_BLOCK:
                burnTime += Settings.FuelTypes.DIAMONDBURN;
                break;
            case EMERALD:
            case EMERALD_BLOCK:
                burnTime += Settings.FuelTypes.EMERALDBURN;
                break;
        }

        if (itemStack.getType().isBlock() && isFuel(itemStack)) {
            burnTime *= Settings.FuelTypes.BLOCK_BURN_MULTIPLIER;
        }

        return burnTime;


    }

    public static BeaconFuel deserialize(SerializedMap map) {
        return new BeaconFuel(map.getItem("Fuel"));
    }


}
