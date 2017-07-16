package fr.sywoo.hickabrain.listener.world;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import fr.sywoo.hickabrain.Main;

public class BlockBreak implements Listener {

	@EventHandler
	public void onBreak(BlockBreakEvent event){

		if(!Main.getInstance().isLaunch){
			event.setCancelled(true);
			return;
		}
		
		if(event.getBlock().getType() == Material.OBSIDIAN || event.getBlock().getType() == Material.STAINED_CLAY || event.getBlock().getType() == Material.STAINED_GLASS){
			event.setCancelled(true);
			return;
		}
		
		event.getBlock().getDrops().clear();
		
			Main.getInstance().ModifiedBlocks.add(event.getBlock().getLocation());

	}
	
	
}
