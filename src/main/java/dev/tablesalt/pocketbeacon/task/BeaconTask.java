package dev.tablesalt.pocketbeacon.task;

import com.palmergames.bukkit.towny.event.RenameNationEvent;
import dev.tablesalt.pocketbeacon.PlayerCache;
import dev.tablesalt.pocketbeacon.beacon.BeaconState;
import dev.tablesalt.pocketbeacon.beacon.PocketBeacons;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.mineacademy.fo.remain.Remain;

public class BeaconTask extends BukkitRunnable {


    @Override
    public void run() {
        for (Player player : Remain.getOnlinePlayers()) {
            if (player.getInventory().getItemInOffHand().equals(PocketBeacons.getBeaconItem()) ||
                    player.getInventory().getItemInMainHand().equals(PocketBeacons.getBeaconItem())) {
                switch (PlayerCache.getCache(player).getCurrentState()) {
                    case NO_EFFECT:
                        PocketBeacons.updateEffect(player, BeaconState.NO_EFFECT);
                        break;
                    case SPEED:
                        PocketBeacons.updateEffect(player, BeaconState.SPEED);
                        break;
                    case JUMP_BOOST:
                        PocketBeacons.updateEffect(player, BeaconState.JUMP_BOOST);
                        break;
                    case HASTE:
                        PocketBeacons.updateEffect(player, BeaconState.HASTE);
                        break;
                    case RESISTANCE:
                        PocketBeacons.updateEffect(player, BeaconState.RESISTANCE);
                        break;
                    case SLOW_FALLING:
                        PocketBeacons.updateEffect(player, BeaconState.SLOW_FALLING);
                        break;
                }
            }
        }
    }
}
