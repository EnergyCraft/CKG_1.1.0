package fr.francepvp.justcrazy;

import java.util.ArrayList;
import java.util.logging.Level;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class CKGclickListener implements Listener {
    private final customkitgui plugin;
    public CKGclickListener(customkitgui instance) {
    	plugin = instance;
    }
	@EventHandler
	public void onClickINV(InventoryClickEvent click) {
		Player p = (Player) click.getWhoClicked();
		String inv = click.getInventory().getName();
		if (VerifInv(click.getInventory().getName())) {
			if (plugin.getKits().contains(inv + ".slot" + click.getSlot())) {
				if (plugin.getConfig().contains("CKG.kits." + inv + ".slot" + click.getSlot() + ".enabled")) {
					if (click.getCurrentItem().getItemMeta().getDisplayName().contains("[V]")) {
						ItemStack[] main = null;
						if (plugin.getKits().get(inv + ".slot" + click.getSlot()) instanceof ArrayList) {
							@SuppressWarnings("unchecked")
							ArrayList<ItemStack> kits = (ArrayList<ItemStack>) plugin.getKits().get(inv + ".slot" + click.getSlot());
							main = kits.toArray(new ItemStack[kits.size()]);
						} else if (plugin.getKits().get(inv + ".slot" + click.getSlot()) instanceof ItemStack[]) {
							main = (ItemStack[]) plugin.getKits().get(inv + ".slot" + click.getSlot());	
						}
						PlayerInventory pInv = p.getInventory();
						try {
							pInv.addItem(main[0]);
						}
						catch (Exception ex) {
							
						}
						try {
							pInv.addItem(main[1]);
						}
						catch (Exception ex) {
							
						}
						try {
							pInv.addItem(main[2]);
						}
						catch (Exception ex) {
							
						}
						try {
							pInv.addItem(main[3]);
						}
						catch (Exception ex) {
						}
						try {
							pInv.addItem(main[4]);
						}
						catch (Exception ex) {
							
						}
						try {
							pInv.addItem(main[5]);
						}
						catch (Exception ex) {
						}
						try {
							pInv.addItem(main[6]);
						}
						catch (Exception ex) {
						}
						try {
							pInv.addItem(main[7]);
						}
						catch (Exception ex) {
						}
						try {
							pInv.addItem(main[8]);
						}
						catch (Exception ex) {
						}
						try {
							pInv.addItem(main[9]);
						}
						catch (Exception ex) {
							plugin.getLogger().log(Level.WARNING, "the kit " + click.getInventory().getName() + " on the slot " + click.getSlot() + " is not full");
						}
						p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
						plugin.dm.CreateAwaitingTime(p, inv, "slot" + click.getSlot());
						click.setCancelled(true);
						p.closeInventory();
					} else {
						p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 1, 1);
						click.setCancelled(true);
					}
				} else {
					p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 1, 1);
					click.setCancelled(true);
				}
			} else {
				p.sendMessage("§cthe slot has no kit assigned");
				click.setCancelled(true);
				p.closeInventory();
			}
		}
	}
	public boolean VerifInv(String str) {
		if (plugin.getConfig().contains("CKG.kits." + str)) {
			return true;
		}
		return false;
	}
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent close) {
		if (close.getInventory().getName().equals(plugin.vi.kit2 + ":" + plugin.vi.slot2 )) {
			Player p = (Player)close.getPlayer();
			try {
				String kit = plugin.vi.kit2;
				plugin.getKits().set(kit + "." + plugin.vi.slot2, close.getInventory().getContents());
				p.sendMessage("§7Inventory saved !");
				plugin.kitsSave();
				plugin.reloadKits();
			}
		    catch (Exception ex)
		    {
		      plugin.getLogger().info(ex.getLocalizedMessage());
		    }	
		}
	}
}
