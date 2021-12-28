package dev.tablesalt.pocketbeacon.beacon;

import com.massivecraft.massivecore.nms.TeamOptionKey;

import dev.tablesalt.pocketbeacon.PlayerCache;
import org.bukkit.Sound;
import org.bukkit.block.Beacon;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.mineacademy.fo.*;
import org.mineacademy.fo.menu.Menu;
import org.mineacademy.fo.menu.button.Button;
import org.mineacademy.fo.menu.button.ButtonMenu;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.menu.model.MenuClickLocation;
import org.mineacademy.fo.model.SimpleSound;
import org.mineacademy.fo.remain.CompChatColor;
import org.mineacademy.fo.remain.CompMaterial;

import java.util.ArrayList;
import java.util.List;

public class BeaconMenu extends Menu {


	private final ButtonMenu fuelButton;
	private final ButtonMenu tierOne;
	private final ButtonMenu tierTwo;
	private final ButtonMenu tierThree;

	private final BeaconButton clearButton;


	public BeaconMenu() {


		setSize(9);
		setTitle(ChatUtil.generateGradient("Pocket Beacon", CompChatColor.AQUA, CompChatColor.DARK_BLUE));

		//----------------------------------- Tier One (Coal) -----------------------------------//
		BeaconButton nightButton = new BeaconButton(BeaconState.NIGHT_VISION,
				ItemCreator.of(CompMaterial.GOLDEN_CARROT).name("Night Vision").hideTags(true).build().make());

		BeaconButton slowFallingButton = new BeaconButton(BeaconState.SLOW_FALLING,
				ItemCreator.of(CompMaterial.FEATHER).name("Slow Falling").hideTags(true).build().make());

		BeaconButton jumpButton = new BeaconButton(BeaconState.JUMP_BOOST,
				ItemCreator.of(CompMaterial.RABBIT_FOOT).name("Jump Boost").hideTags(true).build().make());

		BeaconButton speedButton = new BeaconButton(BeaconState.SPEED,
				ItemCreator.of(CompMaterial.SUGAR).name("Speed").hideTags(true).build().make());

		//----------------------------------- Tier One (Coal) -----------------------------------//


		//----------------------------------- Tier Two (Iron / Gold) -----------------------------------//
		BeaconButton hasteButton = new BeaconButton(BeaconState.HASTE,
				ItemCreator.of(CompMaterial.GOLDEN_PICKAXE).name("Haste").hideTags(true).build().make());

		BeaconButton waterButton = new BeaconButton(BeaconState.WATER_BREATHING,
				ItemCreator.of(CompMaterial.KELP).name("Water Breathing").hideTags(true).build().make());

		BeaconButton swimButton = new BeaconButton(BeaconState.FAST_SWIMMING,
				ItemCreator.of(CompMaterial.TROPICAL_FISH).name("Dolphins Grace").hideTags(true).build().make());

		BeaconButton levitationButton = new BeaconButton(BeaconState.LEVITATION,
				ItemCreator.of(CompMaterial.SHULKER_SHELL).name("Levitation").hideTags(true).build().make());

		//----------------------------------- Tier Tier Two (Iron / Gold) -----------------------------------//


		//----------------------------------- Tier Three (Diamond / Emerald ) -----------------------------------//

		BeaconButton resistanceButton = new BeaconButton(BeaconState.RESISTANCE,
				ItemCreator.of(CompMaterial.SHIELD).name("Resistance").hideTags(true).build().make());


		BeaconButton fireButton = new BeaconButton(BeaconState.FIRE_RESISTANCE,
				ItemCreator.of(CompMaterial.MAGMA_CREAM).name("Fire Resistance").hideTags(true).build().make());

		BeaconButton saturationButton = new BeaconButton(BeaconState.SATURATION,
				ItemCreator.of(CompMaterial.COOKED_BEEF).name("Saturation").hideTags(true).build().make());


		BeaconButton strengthButton = new BeaconButton(BeaconState.STRENGTH,
				ItemCreator.of(CompMaterial.NETHERITE_AXE).name("Strength").hideTags(true).build().make());

		//----------------------------------- Tier Three (Diamond / Emerald ) -----------------------------------//


		clearButton = new BeaconButton(BeaconState.NO_EFFECT,
				ItemCreator.of(CompMaterial.MILK_BUCKET).name("Clear Effect").hideTags(true).build().make());

		tierOne = new ButtonMenu(new BeaconTierMenu(new BeaconButton[]{
				nightButton, slowFallingButton, speedButton, jumpButton
		}, "Pocket Beacon Tier One"), CompMaterial.COAL_BLOCK, "Tier 1");

		tierTwo = new ButtonMenu(new BeaconTierMenu(new BeaconButton[]{
				hasteButton, waterButton, swimButton, levitationButton
		}, "Pocket Beacon Tier Two"), CompMaterial.IRON_BLOCK, "Tier 2");

		tierThree = new ButtonMenu(new BeaconTierMenu(new BeaconButton[]{
				resistanceButton, fireButton, saturationButton, strengthButton
		}, "Pocket Beacon Tier Three"), CompMaterial.GOLD_BLOCK, "Tier 3");


		fuelButton = new ButtonMenu(new FuelMenu(), CompMaterial.BLAST_FURNACE,
				"Fuel Menu",
				"Click to Open",
				"The Refueling Menu");


	}


	@Override
	public ItemStack getItemAt(int slot) {
		//getting the players cache
		PlayerCache cache = PlayerCache.getCache(getViewer());
		int tier = 1;

		if (cache.getBeaconFuel() != null)
			tier = BeaconFuel.getTier(cache.getBeaconFuel().getFuel());

		if (slot == 7)
			return clearButton.getItem();

		//tier one
		if (tier == 1) {
			if (slot == 1)
				return tierOne.getItem();
		}
		//tier two
		if (tier == 2) {
			if (slot == 1)
				return tierOne.getItem();
			if (slot == 2)
				return tierTwo.getItem();
		}

		//tier three
		if (tier == 3) {
			if (slot == 1)
				return tierOne.getItem();
			if (slot == 2)
				return tierTwo.getItem();
			if (slot == 3)
				return tierThree.getItem();
		}

		//centerpiece
		if (slot == getCenterSlot())
			return ItemCreator.of(CompMaterial.BEACON, "&fPocket Beacon").glow(true).build().make();

		//fuel menu
		if (slot == 8)
			return fuelButton.getItem();

		//filler glass
		if (slot > 0 && slot < 9) {
			return ItemCreator.of(CompMaterial.PURPLE_STAINED_GLASS_PANE, "").build().make();
		}

		return null;
	}

	@Override
	protected String[] getInfo() {
		return new String[]{
				"Pocket Beacon Menu!",
				"Click one of the effects",
				"to activate the beacon."
		};
	}


	private class BeaconTierMenu extends Menu {
		//All sizes of list must be 4... not bothering to check
		private final BeaconButton[] beaconButtons;


		BeaconTierMenu(BeaconButton[] buttons, String title) {
			super(BeaconMenu.this);
			setSize(9);
			setTitle(ChatUtil.generateGradient(title, CompChatColor.AQUA, CompChatColor.DARK_BLUE));
			beaconButtons = buttons;
		}


		@Override
		public ItemStack getItemAt(int slot) {

			switch (slot) {
				case 2:
					return beaconButtons[0].getItem();
				case 3:
					return beaconButtons[1].getItem();
				case 5:
					return beaconButtons[2].getItem();
				case 6:
					return beaconButtons[3].getItem();
			}

			//centerpiece
			if (slot == getCenterSlot())
				return ItemCreator.of(CompMaterial.BEACON, "&fPocket Beacon").glow(true).build().make();


			if (slot == 1 || slot == 7) {
				return ItemCreator.of(CompMaterial.YELLOW_STAINED_GLASS_PANE, "").build().make();
			}

			return null;

		}

		@Override
		protected String[] getInfo() {
			return new String[]{
					"TODO... ",
					"Explain Tier System",
					"Here"
			};
		}
	}


	private final class FuelMenu extends Menu {
		FuelMenu() {
			super(BeaconMenu.this);
			setTitle(ChatUtil.generateGradient("Pocket Beacon Fuel", CompChatColor.AQUA, CompChatColor.DARK_BLUE));
			setSize(9);
		}


		@Override
		public ItemStack getItemAt(int slot) {
			PlayerCache cache = PlayerCache.getCache(getViewer());

			if (slot == getCenterSlot()) {

				if (cache.getBeaconFuel() == null) return null;

				return cache.getBeaconFuel().getFuel();
			}
			if (slot > 0 && slot < 9) {
				return ItemCreator.of(CompMaterial.RED_STAINED_GLASS_PANE, "").build().make();
			}

			return null;

		}

		protected String[] getInfo() {
			return new String[]{
					"TODO... ",
					"Explain Fuel System",
					"Here"
			};
		}


		@Override
		protected boolean isActionAllowed(MenuClickLocation location, int slot, ItemStack clicked, ItemStack cursor) {

			if (location.equals(MenuClickLocation.MENU)) {
				return slot == getCenterSlot();
			}

			return location == MenuClickLocation.PLAYER_INVENTORY && (clicked == null || BeaconFuel.isFuel(clicked));
		}


		@Override
		protected void onMenuClose(Player player, Inventory inventory) {
			super.onMenuClose(player, inventory);
			PlayerCache cache = PlayerCache.getCache(player);

			if (inventory.getItem(getCenterSlot()) == null) {
				cache.setBeaconFuel(null);
			} else {

				cache.setBeaconFuel(new BeaconFuel(inventory.getItem(getCenterSlot())));

				if (!cache.getBeaconFuel().isBurning() && !cache.getBeaconFuel().isEmpty()) {
					cache.getBeaconFuel().setBurning(true);


					//todo error somewhere not decrementing fuel items
					BeaconTaskManager.getInstance().start(player, new BukkitRunnable() {
						final BeaconFuel currentFuel = PlayerCache.getCache(player).getBeaconFuel();
						final int burnTime = BeaconFuel.getBurnTime(currentFuel.getFuel());

						int amountLeft = cache.getBeaconFuel().getFuel().getAmount();
						int timer = 0;

						@Override
						public void run() {

							if (cache.getCurrentState().equals(BeaconState.NO_EFFECT) || !PocketBeacons.isHolding(player)) {
								return;
							}

							//checks when an item should be burned
							if (timer >= burnTime) {
								amountLeft--;
								currentFuel.setAmount(amountLeft);
								timer = 0;

								new SimpleSound(Sound.BLOCK_BLASTFURNACE_FIRE_CRACKLE, 5, 1).play(player);
								return;
							}


							//handles logic when player is viewing the ticking inventory
							if (isViewing(player)) {
								ItemStack cursorItem = getViewer().getItemOnCursor();

								if (BeaconFuel.isFuel(cursorItem)) {
									cancel();
									return;
								}
								restartMenu();
							}

							//stops the fuel ticking when the fuel is empty
							if (currentFuel.isEmpty() || (cache.getBeaconFuel() != null && !cache.getBeaconFuel().isBurning())) {
								setItem(getCenterSlot(), null);
								new SimpleSound(Sound.BLOCK_BEACON_DEACTIVATE, 10, 1).play(player);
								cancel();
								return;
							}
							timer++;

							Common.broadcast(timer + ": " + amountLeft);

						}

						public void cancel() {
							cache.getBeaconFuel().setBurning(false);
							BeaconTaskManager.getInstance().stop(player);
							cache.setCurrentState(BeaconState.NO_EFFECT);
							super.cancel();

						}

					});
				}
			}
		}
	}


}



