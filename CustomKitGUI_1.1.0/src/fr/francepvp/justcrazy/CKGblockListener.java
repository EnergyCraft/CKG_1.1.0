package fr.francepvp.justcrazy;


import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class CKGblockListener implements Listener {
    private final customkitgui plugin;
    public CKGblockListener(customkitgui instance) {
    	plugin = instance;
    }
	String tempKIT = null;
	@EventHandler
	public void clickListener(PlayerInteractEvent event) {
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || (event.getAction().equals(Action.LEFT_CLICK_BLOCK))) {
			if (event.getClickedBlock().getType().equals(Material.GOLD_BLOCK)) {
				Block b = event.getClickedBlock();
				if (locationContains(b, event.getPlayer())) {
					tempKIT = plugin.getLoc().getString("loc." + b.getX() + b.getY() + b.getZ() + ".TypeKit");
					if (plugin.getConfig().getStringList("CKG.EnabledKits").contains(tempKIT)) {
						plugin.vi.OpenINV(event.getPlayer(), tempKIT, null, "slot11");
						b.getWorld().playEffect(b.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
						b.getWorld().playSound(b.getLocation(), Sound.NOTE_BASS_DRUM, 1,1);
					}
					event.setCancelled(true);
				}
			}
		}
	}
	public boolean locationContains(Block loc, Player p) {
		if (plugin.getLoc().contains("loc." + loc.getX() + loc.getY() + loc.getZ())) {
			if (p.hasPermission("CKG.use")) {
				return true;
			}
			p.sendMessage("§4You don't have enought permission");
			return false;
		}
		return false;

	}
}
