package fr.sywoo.hickabrain.listener.player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.sywoo.hickabrain.Main;

public class PlayerMove implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMove(PlayerMoveEvent event) {

		Player player = event.getPlayer();

		if (Main.getInstance().isLaunch) {

			Location location = new Location(player.getWorld(), player.getLocation().getX(),
					player.getLocation().getY() - 1, player.getLocation().getZ());
			if (location.getY() < Main.getInstance().maxbuild - 1) {
				if (Main.getInstance().blue.contain(player)) {
					if (location.getBlock().getType() == Material.STAINED_CLAY) {
						if (location.getBlock().getData() == 14) {
							Main.getInstance().round.winPoint(player);
							for (Player players : Bukkit.getOnlinePlayers()) {
								Main.getInstance().scoreboard.updateBlue(players);
							}
						}
					}
				} else if (Main.getInstance().red.contain(player)) {
					if (location.getBlock().getType() == Material.STAINED_CLAY) {
						if (location.getBlock().getData() == 11) {
							Main.getInstance().round.winPoint(player);
							for (Player players : Bukkit.getOnlinePlayers()) {
								Main.getInstance().scoreboard.updateRed(players);
							}
						}
					}
				}
			}
		}
		if (player.getLocation().getY() < 10) {
			if (Main.getInstance().isLaunch) {
				if (Main.getInstance().red.contain(player)) {
					player.teleport(Main.getInstance().redSpawn);
					Main.getInstance().round.setPartyInventory(player);
				}
				if (Main.getInstance().blue.contain(player)) {
					player.teleport(Main.getInstance().blueSpawn);
					Main.getInstance().round.setPartyInventory(player);
				}
			} else {
				player.teleport(Main.getInstance().center);
			}

		}

	}

}
