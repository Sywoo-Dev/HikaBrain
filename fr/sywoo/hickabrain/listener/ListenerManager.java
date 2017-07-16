package fr.sywoo.hickabrain.listener;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import fr.sywoo.hickabrain.Main;
import fr.sywoo.hickabrain.listener.player.PlayerDamage;
import fr.sywoo.hickabrain.listener.player.PlayerDeath;
import fr.sywoo.hickabrain.listener.player.PlayerJoin;
import fr.sywoo.hickabrain.listener.player.PlayerMove;
import fr.sywoo.hickabrain.listener.server.RainManager;
import fr.sywoo.hickabrain.listener.world.BlockBreak;
import fr.sywoo.hickabrain.listener.world.BlockPlace;

public class ListenerManager {

	private Main instance = Main.getInstance();
	
	public void registers() {

		PluginManager plugin = Bukkit.getPluginManager();
		
		plugin.registerEvents(new BlockPlace(), instance);
		plugin.registerEvents(new BlockBreak(), instance);
		plugin.registerEvents(new PlayerMove(), instance);
		plugin.registerEvents(new PlayerJoin(), instance);
		plugin.registerEvents(new PlayerDamage(), instance);
		plugin.registerEvents(new PlayerDeath(), instance);
		plugin.registerEvents(new RainManager(), instance);
		
	}
	
	
	
	

}
