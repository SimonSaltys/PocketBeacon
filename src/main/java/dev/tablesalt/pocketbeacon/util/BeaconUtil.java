package dev.tablesalt.pocketbeacon.util;

import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompMaterial;

import java.util.Random;

@UtilityClass
public class BeaconUtil {


	public ItemStack getColorfulGlass() {
		Random random = new Random();


		switch (random.nextInt(4)) {
			case 1:
				return makeGlass(Material.BLUE_STAINED_GLASS_PANE);
			case 2:
				return makeGlass(Material.PURPLE_STAINED_GLASS_PANE);
			case 3:
				return makeGlass(Material.RED_STAINED_GLASS_PANE);
		}

		return makeGlass(Material.PINK_STAINED_GLASS_PANE);

	}


	private ItemStack makeGlass(Material material) {
		return ItemCreator.of(CompMaterial.fromMaterial(material), "").glow(true).build().make();
	}

}
