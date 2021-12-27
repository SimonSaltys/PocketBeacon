package dev.tablesalt.pocketbeacon.beacon;

import org.bukkit.block.Beacon;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public enum BeaconState {
    NO_EFFECT, JUMP_BOOST, SPEED, HASTE, SLOW_FALLING, RESISTANCE;


    public static PotionEffectType toPotionEffectType(BeaconState beaconState) {
        switch (beaconState) {

            case NO_EFFECT:
                return null;
            case SPEED:
                return PotionEffectType.SPEED;
            case JUMP_BOOST:
                return PotionEffectType.JUMP;
            case HASTE:
                return PotionEffectType.FAST_DIGGING;
            case RESISTANCE:
                return PotionEffectType.DAMAGE_RESISTANCE;
            case SLOW_FALLING:
                return PotionEffectType.SLOW_FALLING;
        }
        return null;
    }
}
