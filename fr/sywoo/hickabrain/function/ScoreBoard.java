package fr.sywoo.hickabrain.function;

import java.util.HashMap;

import org.bukkit.entity.Player;

import fr.sywoo.hickabrain.Main;
import fr.sywoo.hickabrain.utils.ScoreboardSign;

public class ScoreBoard {
	
	HashMap<Player, ScoreboardSign> scoreboard = new HashMap<>();
	
	public void setScoreBoard(Player player){
		
		ScoreboardSign score = new ScoreboardSign(player, Main.getInstance().sbtitle.replace("&", "§"));
		score.create();
		score.setLine(1, "§1");
		score.setLine(2, "§c➥Rouge: " + 0 + "§a/" + Main.getInstance().WinPoints);
		score.setLine(3, "§b➥Bleu: " + 0 + "§a/" + Main.getInstance().WinPoints);
		score.setLine(4, "§3");
		score.setLine(5, Main.getInstance().sbIp.replace("&", "§"));
		scoreboard.put(player, score);
		
	}
	
	public void updateRed(Player player){
		ScoreboardSign score = scoreboard.get(player);
		score.setLine(2, "§c➥Rouge: " + Main.getInstance().redPoints + "§a/" + Main.getInstance().WinPoints);
	}
	
	public void updateBlue(Player player){
		ScoreboardSign score = scoreboard.get(player);
		score.setLine(3, "§b➥Bleu: " + Main.getInstance().bluePoints + "§a/" + Main.getInstance().WinPoints);
	}
	
	
	public void removeScoreBoard(Player player){
	
		ScoreboardSign score = scoreboard.get(player);
		score.destroy();
		
	}
	
	

}
