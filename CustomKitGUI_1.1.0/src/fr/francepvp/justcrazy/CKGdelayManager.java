package fr.francepvp.justcrazy;

import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CKGdelayManager {
	private final customkitgui plugin;
    public CKGdelayManager(customkitgui instance) {
    	plugin = instance;
    }
    HashMap<Player, Integer> DebutedDelay;
    HashMap<Player, Integer> ActionedDelay;
    public Integer RequestAwaitingTime(Player p, String kit, String slot) {
    	if (plugin.getSave().contains("delay." + kit + "." + slot + "." + p.getName())) {
    		if (plugin.getSave().get("delay." + kit + "." + slot + "." + p.getName() + ".DelayTime").equals(0)) {
    			return 5;
    		}
    		int current = (int) (System.currentTimeMillis() / 1000);
        	int debuted = (int) plugin.getSave().get("delay." + kit + "." + slot + "." + p.getName() + ".DebutedDelay");
        	int delay = 132645;
    		if (plugin.getConfig().get("CKG.kits." + kit + "." + slot + ".delay") instanceof String) {
    			delay = Integer.parseInt((String) plugin.getConfig().get("CKG.kits." + kit + "." + slot + ".delay"));
    		} else if (plugin.getConfig().get("CKG.kits." + kit + "." + slot + ".delay") instanceof Integer) {
    			delay = (int) plugin.getConfig().get("CKG.kits." + kit + "." + slot + ".delay");
    		}
        	if (current < debuted + delay) {
        		int caca = debuted + delay;
        		int awaiting = current - caca;
        		return awaiting;
        	} else if (current >= debuted + delay) {
        		plugin.getSave().set("delay." + kit + "." + slot + "." + p.getName() + ".DelayTime", 0);
        		plugin.getSave().set("delay." + kit + "." + slot + "." + p.getName() + ".DebutedDelay", 0);
        		plugin.savesave();
        		return 5;
        	}
        	return 5;	
    	}
    	return 5;
    }
    public void CreateAwaitingTime(Player p, String kit, String slot) {
    	if (plugin.getConfig().contains("CKG.kits." + kit + "." + slot + ".delay")) {
    		int o = 132645;
    		if (plugin.getConfig().get("CKG.kits." + kit + "." + slot + ".delay") instanceof String) {
    			o = Integer.parseInt((String) plugin.getConfig().get("CKG.kits." + kit + "." + slot + ".delay"));
    		} else if (plugin.getConfig().get("CKG.kits." + kit + "." + slot + ".delay") instanceof Integer) {
    			o = (int) plugin.getConfig().get("CKG.kits." + kit + "." + slot + ".delay");
    		}
    		if (o == 132645) {
    			p.sendMessage("§cERROR");
    		} else {
    	    	plugin.getSave().set("delay." + kit + "." + slot + "." + p.getName() + ".DelayTime", o);    			
    		}
    	} else {
    		return;
    	}
    	int current = (int) (System.currentTimeMillis() / 1000);
    	plugin.getSave().set("delay." + kit + "." + slot + "." + p.getName() + ".DebutedDelay", current);
    	plugin.savesave();
    	plugin.getLogger().log(Level.INFO, "Player " + p.getName() + " wait " + current + " before " + kit + slot);
    }
    public void RemoveAwaitingTime(String p2, String kit, String slot) {
    	Player p = Bukkit.getPlayer(p2);
    	if (plugin.getConfig().contains("CKG.kits." + kit + "." + slot + ".delay")) {
    		if (plugin.getSave().contains("delay." + kit + "." + slot + "." + p.getName())) {
    			plugin.getSave().set("delay." + kit + "." + slot + "." + p.getName(), null);
    			p.sendMessage("kit '" + plugin.getConfig().get("CKG.kits." + kit + "." + slot + ".itemName") + " in container '" + kit + "' has been cleared for player " + p.getName());
    			p.sendMessage("§a|V] Cleared !");
    		} else {
    			p.sendMessage("§cdelay not found");
    		}
    	} else {
    		p.sendMessage("§cdelay not found");
    	}
    }
}
