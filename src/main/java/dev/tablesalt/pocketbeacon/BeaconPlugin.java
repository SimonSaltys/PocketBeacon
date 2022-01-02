package dev.tablesalt.pocketbeacon;

import com.earth2me.essentials.libs.configurate.objectmapping.meta.Setting;
import dev.tablesalt.pocketbeacon.beacon.BeaconMenu;
import dev.tablesalt.pocketbeacon.beacon.PocketBeacons;
import dev.tablesalt.pocketbeacon.command.BeaconCommandGroup;
import dev.tablesalt.pocketbeacon.listener.BeaconListener;
import dev.tablesalt.pocketbeacon.settings.Settings;
import dev.tablesalt.pocketbeacon.task.BeaconTask;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.mineacademy.fo.ChatUtil;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.Valid;
import org.mineacademy.fo.model.HookManager;
import org.mineacademy.fo.plugin.SimplePlugin;

/**
 * PluginTemplate is a simple template you can use every time you make
 * a new plugin. This will save you time because you no longer have to
 * recreate the same skeleton and features each time.
 * <p>
 * It uses Foundation for fast and efficient development process.
 */
public final class BeaconPlugin extends SimplePlugin {


	/**
	 * Automatically perform login ONCE when the plugin starts.
	 */
	@Override
	protected void onPluginStart() {
	}

	/**
	 * Automatically perform login when the plugin starts and each time it is reloaded.
	 */
	@Override
	protected void onReloadablesStart() {


		registerCommands("pocketbeacon|beacon|pb", BeaconCommandGroup.getInstance());
		registerEvents(BeaconListener.getInstance());
		Bukkit.addRecipe(PocketBeacons.getRecipe());

		BeaconTask beaconTask = new BeaconTask();
		beaconTask.runTaskTimer(this, 0, 5);


	}

	/* ------------------------------------------------------------------------------- */
	/* Static */
	/* ------------------------------------------------------------------------------- */

	/**
	 * Return the instance of this plugin, which simply refers to a static
	 * field already created for you in SimplePlugin but casts it to your
	 * specific plugin instance for your convenience.
	 *
	 * @return instance of the main plugin
	 */
	public static BeaconPlugin getInstance() {
		return (BeaconPlugin) SimplePlugin.getInstance();
	}
}
