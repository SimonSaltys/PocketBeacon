package dev.tablesalt.pocketbeacon.beacon;

import dev.tablesalt.pocketbeacon.PlayerCache;
import github.scarsz.discordsrv.dependencies.trove.iterator.TPrimitiveIterator;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.Valid;

import java.util.HashMap;
import java.util.UUID;

public class BeaconFuel {

    @Getter
    @Setter
    private ItemStack fuel;

    public BeaconFuel(ItemStack fuel) {
        this.fuel = fuel;
    }

    public boolean isEmpty() {
        return fuel.getAmount() <= 1;
    }

    public void setAmount(int amount) {
        fuel.setAmount(amount);
    }

    //-----------------------------------//-----------------------------------//
    //                                  STATIC
    //-----------------------------------//-----------------------------------//

    private static HashMap<UUID, BukkitTask> fuelTick = new HashMap<>();

    public static void startTick(Player player) {

        PlayerCache cache = PlayerCache.getCache(player);

        if (cache.getBeaconFuel().isEmpty()) return;

        //todo how fast the material burns
        int timerStrength = 40;

        Valid.checkBoolean(!fuelTick.containsKey(player.getUniqueId()));
        fuelTick.put(player.getUniqueId(), Common.runTimer(0, timerStrength, () -> {

            int amountLeft = cache.getBeaconFuel().getFuel().getAmount();
            amountLeft--;

            if (amountLeft <= 1) {
                fuelTick.get(player.getUniqueId()).cancel();
                return;
            }
            cache.getBeaconFuel().setAmount(amountLeft);
        }));


    }

    public static void stopTick() {

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


}
