package dev.tablesalt.pocketbeacon.beacon;

import com.comphenix.net.bytebuddy.build.Plugin;
import dev.tablesalt.pocketbeacon.PlayerCache;
import lombok.Getter;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.mineacademy.fo.ChatUtil;
import org.mineacademy.fo.MathUtil;
import org.mineacademy.fo.Valid;
import org.mineacademy.fo.menu.Menu;
import org.mineacademy.fo.menu.button.Button;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.model.SimpleSound;
import org.mineacademy.fo.remain.CompChatColor;
import org.mineacademy.fo.remain.CompSound;

import java.util.Random;

public class BeaconButton extends Button {

	private final BeaconState beaconState;

	private final ItemStack item;


	public BeaconButton(BeaconState beaconState, ItemStack item) {
		this.beaconState = beaconState;
		this.item = item;

	}


	@Override
	public void onClickedInMenu(Player player, Menu menu, ClickType clickType) {

		PlayerCache cache = PlayerCache.getCache(player);

		if (cache.getBeaconFuel() == null || !cache.getBeaconFuel().isBurning()) {
			menu.animateTitle(ChatUtil.generateGradient("No Fuel Present", CompChatColor.RED, CompChatColor.DARK_RED));
			new SimpleSound(Sound.BLOCK_CALCITE_BREAK, 5, MathUtil.range(2, 0, 1)).play(player);
			return;
		}

		PotionEffectType effectType = BeaconState.toPotionEffectType(beaconState);


		if (effectType != null && player.hasPotionEffect(effectType)) {
			menu.animateTitle(ChatUtil.generateGradient("Effect Already Active", CompChatColor.RED, CompChatColor.DARK_RED));
			new SimpleSound(Sound.BLOCK_CALCITE_BREAK, 5, MathUtil.range(2, 0, 1)).play(player);
			return;
		}

		PocketBeacons.updateEffect(player, beaconState);

		new SimpleSound(Sound.BLOCK_CHAIN_BREAK, 5, MathUtil.range(7, 3, 7)).play(player);
		new SimpleSound(Sound.BLOCK_END_PORTAL_FRAME_FILL, 5, MathUtil.range(2, 0, 1)).play(player);


		menu.restartMenu();
	}

	@Override
	public ItemStack getItem() {
		return ItemCreator.of(item).glow(true).build().make();

	}
}
