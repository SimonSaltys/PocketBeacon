package dev.tablesalt.pocketbeacon.beacon;

import com.sk89q.worldedit.bukkit.fastutil.Hash;
import dev.tablesalt.pocketbeacon.BeaconPlugin;
import dev.tablesalt.pocketbeacon.PlayerCache;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.MathUtil;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.model.SimpleSound;
import org.mineacademy.fo.remain.CompMaterial;

import javax.swing.text.StyleContext;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@UtilityClass
public class PocketBeacons {


	public static void giveBeacon(Player target) {
		target.getInventory().addItem(getBeaconItem());
	}

	public static ItemStack getBeaconItem() {
		return ItemCreator.builder().material(CompMaterial.BEACON).name("&fPocket Beacon").glow(true).lore(
				"Right/Left Click to Open").build().make();
	}

	public static ShapedRecipe getRecipe() {
		NamespacedKey key = new NamespacedKey(BeaconPlugin.getInstance(), "pocket_beacon");

		ShapedRecipe recipe = new ShapedRecipe(key, getBeaconItem());

		recipe.shape("SSS", "SSS", "SSS");
		recipe.setIngredient('S', Material.BEACON);

		return recipe;
	}

	public static void updateEffect(Player player, BeaconState beaconState) {
		PlayerCache cache = PlayerCache.getCache(player);
		PotionEffectType previousEffect = BeaconState.toPotionEffectType(cache.getCurrentState());
		PotionEffectType nextEffect = BeaconState.toPotionEffectType(beaconState);


		if (previousEffect != null && previousEffect != nextEffect) {
			player.removePotionEffect(previousEffect);
		}

		if (nextEffect != null) {
			player.addPotionEffect(new PotionEffect(nextEffect, 20 * 4, 1));
		}
		cache.setCurrentState(beaconState);
	}

	public static boolean isHolding(Player player) {
		return player.getInventory().getItemInOffHand().isSimilar(getBeaconItem()) ||
				player.getInventory().getItemInMainHand().isSimilar(getBeaconItem());
	}


}
