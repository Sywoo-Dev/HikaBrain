package fr.sywoo.hickabrain;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import fr.sywoo.hickabrain.function.Round;
import fr.sywoo.hickabrain.function.ScoreBoard;
import fr.sywoo.hickabrain.listener.ListenerManager;
import fr.sywoo.hickabrain.utils.Cuboid;
import fr.sywoo.hickabrain.utils.TeamsUtils;

public class Main extends JavaPlugin {

	private static Main instance;

	public ArrayList<Location> ModifiedBlocks = new ArrayList<>();
	public Round round;
	public ScoreBoard scoreboard = new ScoreBoard();

	public Cuboid cuboid;

	public FileConfiguration config;

	public TeamsUtils blue = new TeamsUtils("blue", 1, "§b", true, "§b", false);
	public TeamsUtils red = new TeamsUtils("red", 1, "§c", true, "§c", false);

	public int redPoints = 0;
	public int bluePoints = 0;
	public int maxbuild = 256;

	public Location redSpawn;
	public Location blueSpawn;

	public Location center;

	public Location c1;
	public Location c2;

	public int WinPoints;

	public String endCommand;
	public String sbtitle;
	public String sbIp;
	
	public boolean isLaunch = false;

	@Override
	public void onLoad() {

		instance = this;

	}

	@Override
	public void onEnable() {

		new ListenerManager().registers();
		round = new Round();
		round.setSpawns();
		red.build();
		blue.build();
		Bukkit.getWorld("world").setGameRuleValue("keepInventory", "true");

		if (c1 != null && c2 != null) {
			cuboid = new Cuboid(c1, c2);
		} else {
			Bukkit.getConsoleSender()
			.sendMessage("§c[Alert] §aLa zone de réinitialisation des blocks a été mal définie !");
		}

		config = getConfig();
		File file = new File(getDataFolder(), "config.yml");
		if (!file.exists()) {
		    getLogger().info("§2Create Config.yml");
			config.addDefault("points", 10);
			config.addDefault("scoreboard.title", "&9HikaBrain");
			config.addDefault("scoreboard.footer", "&6play.scopegames.fr");
			config.addDefault("finnish.command", "Commande sans / | Command without /");
			config.options().copyDefaults(true);
			Bukkit.getConsoleSender().sendMessage("§aVotre Fichier De configuration a bien été créé");
			saveDefaultConfig();
			saveConfig();
		} else {

			
			
		}
		
		endCommand = config.getString("finnish.command");
		WinPoints = config.getInt("points");
		sbtitle = config.getString("scoreboard.title");
		sbIp = config.getString("scoreboard.footer");

	}



	@Override
	public void onDisable() {
		red.team.unregister();
		blue.team.unregister();
	}

	public static Main getInstance() {
		return instance;
	}

}
