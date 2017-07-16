package fr.sywoo.hickabrain.function;

import java.util.HashMap;

import org.bukkit.entity.Player;

import fr.sywoo.hickabrain.Main;
import fr.sywoo.hickabrain.utils.ScoreboardSign;
import fr.sywoo.hickabrain.utils.ScoreboardSign1_9;

public class ScoreBoard {

	HashMap<Player, ScoreboardSign> scoreboard = new HashMap<>();

	HashMap<Player, ScoreboardSign1_9> scoreboard1_9 = new HashMap<>();


	public void setScoreBoard(Player player){

		if (Main.getInstance().Version.contains("1.8")) {
			ScoreboardSign score;
			score = new ScoreboardSign(player, Main.getInstance().sbtitle.replace("&", "§"));
			score.create();
			score.setLine(1, "§1");
			score.setLine(2, "§c➥Rouge: " + 0 + "§a/" + Main.getInstance().WinPoints);
			score.setLine(3, "§b➥Bleu: " + 0 + "§a/" + Main.getInstance().WinPoints);
			score.setLine(4, "§3");
			score.setLine(5, Main.getInstance().sbIp.replace("&", "§"));
			scoreboard.put(player, score);
		}else if(Main.getInstance().Version.contains("1.9")){
			ScoreboardSign1_9 score1_9;
			score1_9 = new ScoreboardSign1_9(player, Main.getInstance().sbtitle.replace("&", "§"));
			score1_9.create();
			score1_9.setLine(1, "§1");
			score1_9.setLine(2, "§c➥Rouge: " + 0 + "§a/" + Main.getInstance().WinPoints);
			score1_9.setLine(3, "§b➥Bleu: " + 0 + "§a/" + Main.getInstance().WinPoints);
			score1_9.setLine(4, "§3");
			score1_9.setLine(5, Main.getInstance().sbIp.replace("&", "§"));
			scoreboard1_9.put(player, score1_9);		}


	}

	public void updateRed(Player player){
		if (Main.getInstance().Version.contains("1.8")) {
			ScoreboardSign score = scoreboard.get(player);
			score.setLine(2, "§c➥Rouge: " + Main.getInstance().redPoints + "§a/" + Main.getInstance().WinPoints);
		}
		if (Main.getInstance().Version.contains("1.9")) {
			ScoreboardSign1_9 score = scoreboard1_9.get(player);
			score.setLine(2, "§c➥Rouge: " + Main.getInstance().redPoints + "§a/" + Main.getInstance().WinPoints);
		}
	}

	public void updateBlue(Player player){
		if (Main.getInstance().Version.contains("1.8")) {
			ScoreboardSign score = scoreboard.get(player);
			score.setLine(3, "§b➥Bleu: " + Main.getInstance().bluePoints + "§a/" + Main.getInstance().WinPoints);
		}
		if (Main.getInstance().Version.contains("1.9")) {
			ScoreboardSign1_9 score = scoreboard1_9.get(player);
			score.setLine(3, "§b➥Bleu: " + Main.getInstance().bluePoints + "§a/" + Main.getInstance().WinPoints);
		}
	}


	public void removeScoreBoard(Player player){

		if (Main.getInstance().Version.contains("1.8")) {
			ScoreboardSign score = scoreboard.get(player);
			score.destroy();
		}
		if (Main.getInstance().Version.contains("1.9")) {
			ScoreboardSign1_9 score = scoreboard1_9.get(player);
			score.destroy();
		}

	}



}
