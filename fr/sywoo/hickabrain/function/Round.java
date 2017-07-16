package fr.sywoo.hickabrain.function;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.util.Vector;

import fr.sywoo.hickabrain.Main;
import fr.sywoo.hickabrain.utils.BlockRadius;

public class Round {

	@SuppressWarnings("deprecation")
	public void setSpawns(){
		Location center = new Location(Bukkit.getWorld(Main.getInstance().getConfig().getString("load.center.world")),
				Main.getInstance().getConfig().getInt("load.center.x"),
				Main.getInstance().getConfig().getInt("load.center.y"),
				Main.getInstance().getConfig().getInt("load.center.z"));
		int amount = 0;
		int slime = 0;
		int iron = 0;
		
		for(Block blocks : new BlockRadius().sphereblock(center, 30)){
			if(blocks.getType() == Material.BEACON){
				Location under = new Location(blocks.getWorld(), blocks.getLocation().getX(),
						blocks.getLocation().getY() -1,
						blocks.getLocation().getZ());
				if(under.getBlock().getType() == Material.STAINED_CLAY){
					if(under.getBlock().getData() == 3){
						Main.getInstance().blueSpawn = blocks.getLocation();
						blocks.setType(Material.AIR);
						amount ++;	
					}
					if(under.getBlock().getData() == 14){
						Main.getInstance().redSpawn = blocks.getLocation();
						blocks.setType(Material.AIR);
						amount ++;
						Main.getInstance().maxbuild = (int) under.getY() + 1;
					}

				}
			}
			if(blocks.getType() == Material.SLIME_BLOCK){
				slime++;
				if(slime == 1){
					Main.getInstance().c1 = blocks.getLocation();
					blocks.setType(Material.OBSIDIAN);
				}
				if(slime == 2){
					Main.getInstance().c2 = blocks.getLocation();		
					blocks.setType(Material.OBSIDIAN);
				}

			}
			if(blocks.getType() == Material.IRON_BLOCK){
				Main.getInstance().center = blocks.getLocation();
				blocks.setType(Material.SANDSTONE);

				iron++;
			}
		}

		if(amount < 2){
			Bukkit.getConsoleSender().sendMessage("§c[Alert] §aVous avez oublié de définir au moins 1 des 2 spawns");
		}
		if(amount > 2){
			Bukkit.getConsoleSender().sendMessage("§c[Alert] §aVous avez définies trop de spawns §c(Max 2)");
		}
		if(slime < 2){
			Bukkit.getConsoleSender().sendMessage("§c[Alert] §aVous n'avez pas définie la zone du pont (Slime)");
		}
		if(slime > 2){
			Bukkit.getConsoleSender().sendMessage("§c[Alert] §aNous avons trouver " + (slime-2) + " blocks de slime en trop !");	
		}
		if(iron > 1){
			Bukkit.getConsoleSender().sendMessage("§c[Alert] §aVous pouvez définir un seul Millieu (Block en Fer)");	
		}

	}

	private void clear() {

		for(Location blocks : Main.getInstance().ModifiedBlocks){
			if(!Main.getInstance().cuboid.contains(blocks)){
				blocks.getBlock().setType(Material.AIR);
			}
			if(Main.getInstance().cuboid.contains(blocks)){
				if(blocks.getBlock().getType() == Material.AIR){
					blocks.getBlock().setType(Material.SANDSTONE);
				}
			}
		}

	}

	private ItemStack item(Material material, Enchantment enchantement) {

		ItemStack item = new ItemStack(material, 1);
		ItemMeta itemmeta = item.getItemMeta();
		itemmeta.addEnchant(enchantement, 3, true);
		itemmeta.spigot().setUnbreakable(true);
		itemmeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		item.setItemMeta(itemmeta);

		return item;
	}

	private ItemStack item(Material material, Enchantment enchantement, Enchantment ench2) {

		ItemStack item = new ItemStack(material, 1);
		ItemMeta itemmeta = item.getItemMeta();
		itemmeta.addEnchant(enchantement, 2, true);
		itemmeta.addEnchant(ench2, 1, true);
		itemmeta.spigot().setUnbreakable(true);
		itemmeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		item.setItemMeta(itemmeta);

		return item;
	}

	private ItemStack armor(Material material, Color color, int strenght){

		ItemStack itemstack = new ItemStack(material);
		LeatherArmorMeta meta = (LeatherArmorMeta) itemstack.getItemMeta();
		meta.setColor(color);
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, strenght, true);
		meta.spigot().setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemstack.setItemMeta(meta);
		return itemstack;

	}

	public void setPartyInventory(Player player) {

		if(Main.getInstance().Version.contains("1.9")){
			player.getInventory().setItemInOffHand(new ItemStack(Material.SANDSTONE, 64, (short) 2));
			player.setCollidable(true);
		}
		
		if (player.getGameMode() != GameMode.SPECTATOR) {
			player.getInventory().clear();
			player.setHealth(20);
			player.getInventory()
			.addItem(item(Material.IRON_SWORD, Enchantment.DAMAGE_ALL, Enchantment.KNOCKBACK));
			player.getInventory().addItem(item(Material.DIAMOND_PICKAXE, Enchantment.DIG_SPEED));
			player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 32));

			while (player.getInventory().firstEmpty() != -1) {
				player.getInventory().addItem(new ItemStack(Material.SANDSTONE, 64, (short) 2));
			}
		}
		if(Main.getInstance().red.contain(player)){
			player.getInventory().setHelmet(armor(Material.LEATHER_HELMET, Color.RED, 1));
			player.getInventory().setChestplate(armor(Material.LEATHER_CHESTPLATE, Color.RED, 3));
			player.getInventory().setLeggings(armor(Material.LEATHER_LEGGINGS, Color.RED, 1));
			player.getInventory().setBoots(armor(Material.LEATHER_BOOTS, Color.RED, 1));

		}
		if(Main.getInstance().blue.contain(player)){
			player.getInventory().setHelmet(armor(Material.LEATHER_HELMET, Color.BLUE, 1));
			player.getInventory().setChestplate(armor(Material.LEATHER_CHESTPLATE, Color.BLUE, 3));
			player.getInventory().setLeggings(armor(Material.LEATHER_LEGGINGS, Color.BLUE, 1));
			player.getInventory().setBoots(armor(Material.LEATHER_BOOTS, Color.BLUE, 1));

		}
		player.getEyeLocation().setDirection(new Vector(Main.getInstance().center.getX(), Main.getInstance().center.getY(), Main.getInstance().center.getZ()));

	}

	private void hasWon() {
		if (Main.getInstance().bluePoints == Main.getInstance().WinPoints) {
			Bukkit.broadcastMessage("§aL'équipe §bBleu §aa gagné !");
			Main.getInstance().isLaunch = false;
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Main.getInstance().endCommand);
		} else if (Main.getInstance().redPoints == Main.getInstance().WinPoints) {
			Bukkit.broadcastMessage("§aL'équipe §cRouge §aa gagné !");
			Main.getInstance().isLaunch = false;
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Main.getInstance().endCommand);
		}else{
			for(Player players : Bukkit.getOnlinePlayers()){
				sendAtSpawn(players);
				setPartyInventory(players);
			}
		}
	}
	
	public void sendAtSpawn(Player players){

			if(Main.getInstance().red.contain(players)){

				players.teleport(Main.getInstance().redSpawn);
				
			}
			if(Main.getInstance().blue.contain(players)){

				players.teleport(Main.getInstance().blueSpawn);
			}
	}

	public void start(){
		for(Player players : Bukkit.getOnlinePlayers()){
			sendAtSpawn(players);
			setPartyInventory(players);
		}
	}
	
	public void winPoint(Player marker) {

		if (Main.getInstance().blue.contain(marker)) {
			Main.getInstance().bluePoints++;
			Bukkit.broadcastMessage("§b" + marker.getDisplayName() + " §aViens de marquer !");

		}
		if (Main.getInstance().red.contain(marker)) {
			Main.getInstance().redPoints++;
			Bukkit.broadcastMessage("§c" + marker.getDisplayName() + " §aViens de marquer !");
		}

		clear();
		hasWon();
		
	}

}
