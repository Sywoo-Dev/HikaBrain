package fr.sywoo.hickabrain.listener.player;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import fr.sywoo.hickabrain.Main;

public class PlayerJoin implements Listener {

	int task;
	int timer = 6;
	boolean started = false;
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
	
		Player player = event.getPlayer();
		event.setJoinMessage("§6" + player.getDisplayName() + " §aa rejoin le jeu !");
		player.getInventory().clear();
		player.getInventory().setHelmet(new ItemStack(Material.AIR));
		player.getInventory().setChestplate(new ItemStack(Material.AIR));
		player.getInventory().setLeggings(new ItemStack(Material.AIR));
		player.getInventory().setBoots(new ItemStack(Material.AIR));
		

			Main.getInstance().scoreboard.setScoreBoard(player);


		if(Bukkit.getOnlinePlayers().size() == 1){
			Main.getInstance().blue.addPlayer(player);
		}else if(Bukkit.getOnlinePlayers().size() == 2){
			Main.getInstance().red.addPlayer(player);
		}else{
			player.setGameMode(GameMode.SPECTATOR);
		}
		
		if (Bukkit.getOnlinePlayers().size() == 2) {
			task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

				@Override
				public void run() {

					started = true;
					timer--;
					Bukkit.broadcastMessage("§aLa partie commence dans " + timer + "s");
					if (timer == 0) {
						Bukkit.getScheduler().cancelTask(task);
						Main.getInstance().isLaunch = true;
						Main.getInstance().round.start();

					}

				}
			}, 20, 20);
		}
		
		
	}
	
	public void onQuit(PlayerQuitEvent event){
		event.setQuitMessage(null);
		if(Bukkit.getOnlinePlayers().size() < 2){
			Bukkit.broadcastMessage("§6Lancement annulé ! §cVotre Partenaire a quitter la partie !");
			Bukkit.getScheduler().cancelTask(task);
			timer = 6;
			started = false;
			if(Main.getInstance().blue.contain(event.getPlayer())){
				Main.getInstance().blue.removePlayer(event.getPlayer());
			}
			if(Main.getInstance().red.contain(event.getPlayer())){
				Main.getInstance().red.removePlayer(event.getPlayer());
			}
			
		}
		Main.getInstance().scoreboard.removeScoreBoard(event.getPlayer());
	}
	
}
