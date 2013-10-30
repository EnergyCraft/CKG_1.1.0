package fr.francepvp.justcrazy;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class CKGautomaticrunner {
    private final customkitgui plugin;
    public CKGautomaticrunner(customkitgui instance) {
    	plugin = instance;
    }
    public void commandList(final Player p, final Integer size, final List<String> total, final Boolean repeat) {
    	Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("CustomKitGUI"), new Runnable() {
			@Override
			public void run() {
				int max;
				max = size - 1;
				p.sendMessage("§c->§a " + total.get(max));
				if (max == 0) {
					p.sendMessage("§c------- 1/1 ----------");
					return;
				}
				commandList(p, max, total, true);
			}
    		
    	}, 1);
    }
    public void autoSave(final Integer i) {
    	Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("CustomKitGUI"), new Runnable() {
			public void run() {
				plugin.savesave();
				plugin.saveConfig();
				plugin.locSave();
				plugin.kitsSave();
				autoSave(i);
			}
    		
    	}, i);
    }
    public void AnimatedFlame(final Block loc) {
    	Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("CustomKitGUI"), new Runnable() {
			@Override
			public void run() {
				Location l;
	        	l = loc.getLocation();
	        	loc.getWorld().playEffect(l, Effect.MOBSPAWNER_FLAMES, 1, 1);
	        	AnimatedFlame(loc);
			}
    	}, 20);
    }
}
