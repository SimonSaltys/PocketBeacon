package dev.tablesalt.pocketbeacon.beacon;

import dev.tablesalt.pocketbeacon.BeaconPlugin;
import dev.tablesalt.pocketbeacon.PlayerCache;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompMaterial;

@UtilityClass
public class PocketBeacons {

	@Getter
	private static final NamespacedKey key = new NamespacedKey(BeaconPlugin.getInstance(), "pocket_beacon");

	public static void giveBeacon(Player target) {
		target.getInventory().addItem(getBeaconItem());
	}

	public static ItemStack getBeaconItem() {
		return ItemCreator.builder().material(CompMaterial.BEACON).name("&fPocket Beacon").glow(true).lore(
				"Right/Left Click to Open").build().make();
	}

	public static ShapedRecipe getRecipe() {


		ShapedRecipe recipe = new ShapedRecipe(key, getBeaconItem());

		recipe.shape("DSD", "NBN", "DSD");
		recipe.setIngredient('B', Material.BEACON);
		recipe.setIngredient('N', Material.NETHERITE_INGOT);
		recipe.setIngredient('D', Material.DIAMOND_BLOCK);
		recipe.setIngredient('S', Material.SHULKER_SHELL);

		return recipe;
	}

	public static void updateEffect(Player player, BeaconState beaconState) {
		

		PlayerCache cache = PlayerCache.getCache(player);
		PotionEffectType previousEffect = BeaconState.toPotionEffectType(cache.getCurrentState());
		PotionEffectType nextEffect = BeaconState.toPotionEffectType(beaconState);


		if (previousEffect != null && previousEffect != nextEffect) {
			player.removePotionEffect(previousEffect);
		}

		if (nextEffect != null && cache.getBeaconFuel() != null) {
			player.addPotionEffect(new PotionEffect(nextEffect,
					(beaconState == BeaconState.NIGHT_VISION ? 20 : 2) * 20
					, BeaconFuel.getEffectMultiplier(cache.getBeaconFuel())));
		}
		cache.setCurrentState(beaconState);
	}

	public static boolean isHolding(Player player) {
		return player.getInventory().getItemInOffHand().isSimilar(getBeaconItem()) ||
				player.getInventory().getItemInMainHand().isSimilar(getBeaconItem());
	}


}
