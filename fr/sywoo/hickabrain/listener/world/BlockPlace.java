package fr.sywoo.hickabrain.listener.world;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import fr.sywoo.hickabrain.Main;

public class BlockPlace implements Listener {
	
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event){
		
		if(event.getBlock().getLocation().getY() >= Main.getInstance().maxbuild){
			event.setCancelled(true);
			event.getPlayer().sendMessage("§cVous ne pouvez pas poser plus haut !");
			return;
		}
		
		Main.getInstance().ModifiedBlocks.add(event.getBlock().getLocation());
		
		
	}

}
