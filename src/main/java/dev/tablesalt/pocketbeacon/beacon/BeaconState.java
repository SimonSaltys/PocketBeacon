package dev.tablesalt.pocketbeacon.beacon;

import org.bukkit.potion.PotionEffectType;

public enum BeaconState {
	NO_EFFECT, JUMP_BOOST, SPEED, HASTE,
	SLOW_FALLING, RESISTANCE, NIGHT_VISION,
	FIRE_RESISTANCE, REGENERATION, LEVITATION,
	STRENGTH, FAST_SWIMMING, WATER_BREATHING;

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
			case NIGHT_VISION:
				return PotionEffectType.NIGHT_VISION;
			case FIRE_RESISTANCE:
				return PotionEffectType.FIRE_RESISTANCE;
			case REGENERATION:
				return PotionEffectType.REGENERATION;
			case LEVITATION:
				return PotionEffectType.LEVITATION;
			case STRENGTH:
				return PotionEffectType.INCREASE_DAMAGE;
			case FAST_SWIMMING:
				return PotionEffectType.DOLPHINS_GRACE;
			case WATER_BREATHING:
				return PotionEffectType.WATER_BREATHING;
		}
		return null;
	}
}
