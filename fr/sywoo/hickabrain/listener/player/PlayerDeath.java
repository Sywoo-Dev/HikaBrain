package fr.sywoo.hickabrain.listener.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import fr.sywoo.hickabrain.Main;

public class PlayerDeath implements Listener {
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event){
		
		Player player = event.getEntity();
		player.spigot().respawn();
		event.setDeathMessage(null);
		event.getDrops().clear();
		Main.getInstance().round.setPartyInventory(player);
		
	}
	

}
