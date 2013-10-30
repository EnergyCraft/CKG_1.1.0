package fr.francepvp.justcrazy;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CKGvirtualinventory {
	String kit2 = null;
	String slot2 = null;
	private final customkitgui plugin;
    public CKGvirtualinventory(customkitgui instance) {
    	plugin = instance;
    }
	@SuppressWarnings("unchecked")
	public void OpenINV(Player p, String type, String kit, String slot) {
		if (slot.equalsIgnoreCase("slot11")) {
			if (plugin.getConfig().contains("CKG.kits." + type)) {
				ItemStack slot0 = new ItemStack(Material.BEDROCK);
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot0.item")) {
					if (plugin.getConfig().get("CKG.kits." + type + ".slot0.item") instanceof String) {
						if (plugin.getConfig().getString("CKG.kits." + type + ".slot0.item").contains(":")) {
							String[] fixed = plugin.getConfig().getString("CKG.kits." + type + ".slot0.item").split(":");
							slot0.setTypeId(Integer.parseInt(fixed[0]));
							slot0.setDurability(Short.parseShort(fixed[1]));
						} else {
							int i0;
							i0 = Integer.parseInt((String) plugin.getConfig().get("CKG.kits." + type + ".slot0.item"));
							slot0.setTypeId(i0);	
						}
					} else if (plugin.getConfig().get("CKG.kits." + type + ".slot0.item") instanceof Integer) {
						int i0;
						i0 = (int) plugin.getConfig().get("CKG.kits." + type + ".slot0.item");
						slot0.setTypeId(i0);
					}
				} else {
					slot0.setTypeId(7);
				}
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot0.amount")) {
					if (plugin.getConfig().get("CKG.kits." + type + ".slot0.amount") instanceof String) {
						int i0;
						i0 = Integer.parseInt((String) plugin.getConfig().get("CKG.kits." + type + ".slot0.amount"));
						slot0.setAmount(i0);	
					} else if (plugin.getConfig().get("CKG.kits." + type + ".slot0.amount") instanceof Integer) {
						int i0;
						i0 = (int) plugin.getConfig().get("CKG.kits." + type + ".slot0.amount");
						slot0.setAmount(i0);	
					}
				} else {
					slot0.setAmount(1);
				}
				///// WAITING GENERATION //////////
				if (plugin.getConfig().getBoolean("CKG.kits." + type + ".slot0.display")) {
					if (plugin.dm.RequestAwaitingTime(p, type, "slot0").equals(5)) {
						slot0.setDurability((short) 0);
					} else {
						int i = plugin.dm.RequestAwaitingTime(p, type, "slot0");
						if (i > slot0.getDurability()) {
							slot0.setDurability(slot0.getType().getMaxDurability());
						} else if (i < slot0.getDurability()) {
							int y = -i;
							slot0.setDurability((short) y);
						}
					}	
				}
				////////////////////////////////
				// CODE BY JUSTCR4ZY - NO DUPLICATION
				ItemMeta meta0 = slot0.getItemMeta();
				//// META GENERATING
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot0.itemName")) {
					if (plugin.getConfig().contains("CKG.kits." + type + ".slot0.enabled")) {
						if ((boolean) plugin.getConfig().get("CKG.kits." + type + ".slot0.enabled")) {
							String i = (String) plugin.getConfig().get("CKG.kits." + type + ".slot0.itemName");
							if (plugin.dm.RequestAwaitingTime(p, type, "slot0").equals(5)) {
								if (plugin.getConfig().contains("CKG.kits." + type + ".slot0.permission")) {
									if (p.hasPermission(plugin.getConfig().getString("CKG.kits." + type + ".slot0.permission"))) {
										meta0.setDisplayName("§a[V] §9" + i);		
									} else {
										meta0.setDisplayName("§c[X] §9" + i);
										List<String> lore = new ArrayList<String>();
										lore.add("§crestricted");
										meta0.setLore(lore);
									}	
								} else {
									meta0.setDisplayName("§a[V] §9" + i);	
								}	
							} else {
								meta0.setDisplayName("§c[X] §9" + i);
								List<String> lore = new ArrayList<String>();
								lore.add("§cKit available in");
								if (plugin.dm.RequestAwaitingTime(p, type, "slot0") < -120) {
									if (plugin.dm.RequestAwaitingTime(p, type, "slot0") < -3600) {
										int temp = (plugin.dm.RequestAwaitingTime(p, type, "slot0") / 3600);
										if (temp < -48) {
											int temp2 = temp / 24;
											lore.add("§c >§5 " + temp2 + " days");
										} else {
											lore.add("§c >§5 " + temp + " hours");	
										}
									} else {
										int temp = (plugin.dm.RequestAwaitingTime(p, type, "slot0") / 60);
										lore.add("§c >§5 " + temp + " minutes");	
									}
								} else {
									lore.add("§c >§5 " + plugin.dm.RequestAwaitingTime(p, type, "slot0") + " seconds");	
								}
								meta0.setLore(lore);
								
							}
						} else {
							String i = (String) plugin.getConfig().get("CKG.kits." + type + ".slot0.itemName");
							meta0.setDisplayName("§c[X] §9" + i);
						}	
					} else {
						String i = (String) plugin.getConfig().get("CKG.kits." + type + ".slot0.itemName");
						meta0.setDisplayName("§c[X] §9" + i);
					}
				} else {
					meta0.setDisplayName("§4ERROR");
				}
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot0.itemLore") && plugin.dm.RequestAwaitingTime(p, type, "slot0").equals(5)) {
					List<String> i = new ArrayList<String>();
					i.addAll((List<String>) plugin.getConfig().get("CKG.kits." + type + ".slot0.itemLore"));
					meta0.setLore(i);
				} else {
				}
				slot0.setItemMeta(meta0);
				/////////////////////////////////////////////////////////////////////////////////////////////
				//////////////////////////////////////////////////////////////////////////////////////////////
				////////////////////////////////////////////////////////////////////////////////////////////
				
				ItemStack slot1 = new ItemStack(Material.BEDROCK);
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot1.item")) {
					if (plugin.getConfig().get("CKG.kits." + type + ".slot1.item") instanceof String) {
						if (plugin.getConfig().getString("CKG.kits." + type + ".slot1.item").contains(":")) {
							String[] fixed = plugin.getConfig().getString("CKG.kits." + type + ".slot1.item").split(":");
							slot1.setTypeId(Integer.parseInt(fixed[0]));
							slot1.setDurability(Short.parseShort(fixed[1]));
						} else {
							int i0;
							i0 = Integer.parseInt((String) plugin.getConfig().get("CKG.kits." + type + ".slot1.item"));
							slot1.setTypeId(i0);	
						}	
					} else if (plugin.getConfig().get("CKG.kits." + type + ".slot1.item") instanceof Integer) {
						int i0;
						i0 = (int) plugin.getConfig().get("CKG.kits." + type + ".slot1.item");
						slot1.setTypeId(i0);
					}
				} else {
					slot1.setTypeId(7);
				}
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot1.amount")) {
					if (plugin.getConfig().get("CKG.kits." + type + ".slot1.amount") instanceof String) {
						if (plugin.getConfig().getString("CKG.kits." + type + ".slot1.item").contains(":")) {
							String[] fixed = plugin.getConfig().getString("CKG.kits." + type + ".slot1.item").split(":");
							ItemStack Fixed = new ItemStack(Material.WOOL);
							Fixed.setTypeId(Integer.parseInt(fixed[0]));
							slot1 = new ItemStack(Fixed.getType(), 1, Short.parseShort(fixed[1]));
						} else {
							int i0;
							i0 = Integer.parseInt((String) plugin.getConfig().get("CKG.kits." + type + ".slot1.item"));
							slot1.setTypeId(i0);	
						}	
					} else if (plugin.getConfig().get("CKG.kits." + type + ".slot1.amount") instanceof Integer) {
						int i0;
						i0 = (int) plugin.getConfig().get("CKG.kits." + type + ".slot1.amount");
						slot1.setAmount(i0);	
					}
				} else {
					slot1.setAmount(1);
				}
				///// WAITING GENERATION //////////
				if (plugin.getConfig().getBoolean("CKG.kits." + type + ".slot1.display")) {
					if (plugin.dm.RequestAwaitingTime(p, type, "slot1").equals(5)) {
						slot1.setDurability((short) 0);
					} else {
						int i = plugin.dm.RequestAwaitingTime(p, type, "slot1");
						if (i > slot1.getDurability()) {
							slot1.setDurability(slot1.getType().getMaxDurability());
						} else if (i < slot1.getDurability()) {
							int y = -i;
							slot1.setDurability((short) y);
						}
					}	
				}
				////////////////////////////////
				// CODE BY JUSTCR4ZY - NO DUPLICATION
				ItemMeta meta1 = slot1.getItemMeta();
				//// META GENERATING
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot1.itemName")) {
					if (plugin.getConfig().contains("CKG.kits." + type + ".slot1.enabled")) {
						if ((boolean) plugin.getConfig().get("CKG.kits." + type + ".slot1.enabled")) {
							String i = (String) plugin.getConfig().get("CKG.kits." + type + ".slot1.itemName");
							if (plugin.dm.RequestAwaitingTime(p, type, "slot1").equals(5)) {
								if (plugin.getConfig().contains("CKG.kits." + type + ".slot1.permission")) {
									if (p.hasPermission(plugin.getConfig().getString("CKG.kits." + type + ".slot1.permission"))) {
										meta1.setDisplayName("§a[V] §9" + i);		
									} else {
										meta1.setDisplayName("§c[X] §9" + i);
										List<String> lore = new ArrayList<String>();
										lore.add("§cRestricted");
										meta1.setLore(lore);
									}	
								} else {
									meta1.setDisplayName("§a[V] §9" + i);	
								}
							} else {
								meta1.setDisplayName("§c[X] §9" + i);
								List<String> lore = new ArrayList<String>();
								lore.add("§cKit available in");
								if (plugin.dm.RequestAwaitingTime(p, type, "slot1") < -120) {
									if (plugin.dm.RequestAwaitingTime(p, type, "slot1") < -3600) {
										int temp = (plugin.dm.RequestAwaitingTime(p, type, "slot1") / 3600);
										if (temp < -48) {
											int temp2 = temp / 24;
											lore.add("§c >§5 " + temp2 + " days");
										} else {
											lore.add("§c >§5 " + temp + " hours");	
										}
									} else {
										int temp = (plugin.dm.RequestAwaitingTime(p, type, "slot1") / 60);
										lore.add("§c >§5 " + temp + " minutes");	
									}
								} else {
									lore.add("§c >§5 " + plugin.dm.RequestAwaitingTime(p, type, "slot1") + " seconds");	
								}
								meta1.setLore(lore);
								
							}
						} else {
							String i = (String) plugin.getConfig().get("CKG.kits." + type + ".slot1.itemName");
							meta1.setDisplayName("§c[X] §9" + i);
						}	
					} else {
						String i = (String) plugin.getConfig().get("CKG.kits." + type + ".slot1.itemName");
						meta1.setDisplayName("§c[X] §9" + i);
					}
				} else {
					meta1.setDisplayName("§4ERROR");
				}
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot1.itemLore") && plugin.dm.RequestAwaitingTime(p, type, "slot1").equals(5)) {
					List<String> i = new ArrayList<String>();
					i.addAll((List<String>) plugin.getConfig().get("CKG.kits." + type + ".slot1.itemLore"));
					i.toString().replace('_', ' ');
					meta1.setLore(i);
				} else {
				}
				slot1.setItemMeta(meta1);
				//////////////////////////////////////////////////////////////////////////////////////////////////
				////////////////////////////////////////////////////////////////////////////////////////////////
				////////////////////////////////////////////////////////////////////////////////////////////////
				ItemStack slot2 = new ItemStack(Material.BEDROCK);
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot2.item")) {
					if (plugin.getConfig().get("CKG.kits." + type + ".slot2.item") instanceof String) {
						if (plugin.getConfig().getString("CKG.kits." + type + ".slot2.item").contains(":")) {
							String[] fixed = plugin.getConfig().getString("CKG.kits." + type + ".slot2.item").split(":");
							slot2.setTypeId(Integer.parseInt(fixed[0]));
							slot2.setDurability(Short.parseShort(fixed[1]));
						} else {
							int i0;
							i0 = Integer.parseInt((String) plugin.getConfig().get("CKG.kits." + type + ".slot2.item"));
							slot2.setTypeId(i0);	
						}
					} else if (plugin.getConfig().get("CKG.kits." + type + ".slot2.item") instanceof Integer) {
						int i0;
						i0 = (int) plugin.getConfig().get("CKG.kits." + type + ".slot2.item");
						slot2.setTypeId(i0);
					}
				} else {
					slot2.setTypeId(7);
				}
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot2.amount")) {
					if (plugin.getConfig().get("CKG.kits." + type + ".slot2.amount") instanceof String) {
						int i0;
						i0 = Integer.parseInt((String) plugin.getConfig().get("CKG.kits." + type + ".slot2.amount"));
						slot2.setAmount(i0);	
					} else if (plugin.getConfig().get("CKG.kits." + type + ".slot2.amount") instanceof Integer) {
						int i0;
						i0 = (int) plugin.getConfig().get("CKG.kits." + type + ".slot2.amount");
						slot2.setAmount(i0);	
					}
				} else {
					slot2.setAmount(1);
				}
				///// WAITING GENERATION //////////
				if (plugin.getConfig().getBoolean("CKG.kits." + type + ".slot2.display")) {
					if (plugin.dm.RequestAwaitingTime(p, type, "slot2").equals(5)) {
						slot2.setDurability((short) 0);
					} else {
						int i = plugin.dm.RequestAwaitingTime(p, type, "slot2");
						if (i > slot2.getDurability()) {
							slot2.setDurability(slot2.getType().getMaxDurability());
						} else if (i < slot2.getDurability()) {
							int y = -i;
							slot2.setDurability((short) y);
						}
					}	
				}
				////////////////////////////////
				// CODE BY JUSTCR4ZY - NO DUPLICATION
				ItemMeta meta2 = slot2.getItemMeta();
				//// META GENERATING
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot2.itemName")) {
					if (plugin.getConfig().contains("CKG.kits." + type + ".slot2.enabled")) {
						if ((boolean) plugin.getConfig().get("CKG.kits." + type + ".slot2.enabled")) {
							String i = (String) plugin.getConfig().get("CKG.kits." + type + ".slot2.itemName");
							if (plugin.dm.RequestAwaitingTime(p, type, "slot2").equals(5)) {
								if (plugin.getConfig().contains("CKG.kits." + type + ".slot2.permission")) {
									if (p.hasPermission(plugin.getConfig().getString("CKG.kits." + type + ".slot2.permission"))) {
										meta2.setDisplayName("§a[V] §9" + i);		
									} else {
										meta2.setDisplayName("§c[X] §9" + i);
										List<String> lore = new ArrayList<String>();
										lore.add("§crestricted");
										meta2.setLore(lore);
									}	
								} else {
									meta2.setDisplayName("§a[V] §9" + i);	
								}
							} else {
								meta2.setDisplayName("§c[X] §9" + i);
								List<String> lore = new ArrayList<String>();
								lore.add("§cKit available in");
								if (plugin.dm.RequestAwaitingTime(p, type, "slot2") < -120) {
									if (plugin.dm.RequestAwaitingTime(p, type, "slot2") < -3600) {
										int temp = (plugin.dm.RequestAwaitingTime(p, type, "slot2") / 3600);
										lore.add("§c >§5 " + temp + " hours");
									} else {
										int temp = (plugin.dm.RequestAwaitingTime(p, type, "slot2") / 60);
										lore.add("§c >§5 " + temp + " minutes");	
									}
								} else {
									lore.add("§c >§5 " + plugin.dm.RequestAwaitingTime(p, type, "slot2") + " seconds");	
								}
								meta2.setLore(lore);
								
							}
						} else {
							String i = (String) plugin.getConfig().get("CKG.kits." + type + ".slot2.itemName");
							meta2.setDisplayName("§c[X] §9" + i);
						}	
					} else {
						String i = (String) plugin.getConfig().get("CKG.kits." + type + ".slot2.itemName");
						meta2.setDisplayName("§c[X] §9" + i);
					}
				} else {
					meta2.setDisplayName("§4ERROR");
				}
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot2.itemLore") && plugin.dm.RequestAwaitingTime(p, type, "slot2").equals(5)) {
					List<String> i = new ArrayList<String>();
					i.addAll((List<String>) plugin.getConfig().get("CKG.kits." + type + ".slot2.itemLore"));
					meta2.setLore(i);
				} else {
				}
				slot2.setItemMeta(meta2);
				//////////////////////////////////////////////////////////////////////////////////////////////////
				////////////////////////////////////////////////////////////////////////////////////////////////
				////////////////////////////////////////////////////////////////////////////////////////////////
				ItemStack slot3 = new ItemStack(Material.BEDROCK);
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot3.item")) {
					if (plugin.getConfig().get("CKG.kits." + type + ".slot3.item") instanceof String) {
						if (plugin.getConfig().getString("CKG.kits." + type + ".slot3.item").contains(":")) {
							String[] fixed = plugin.getConfig().getString("CKG.kits." + type + ".slot3.item").split(":");
							slot3.setTypeId(Integer.parseInt(fixed[0]));
							slot3.setDurability(Short.parseShort(fixed[1]));
						} else {
							int i0;
							i0 = Integer.parseInt((String) plugin.getConfig().get("CKG.kits." + type + ".slot3.item"));
							slot3.setTypeId(i0);	
						}
					} else if (plugin.getConfig().get("CKG.kits." + type + ".slot3.item") instanceof Integer) {
						int i0;
						i0 = (int) plugin.getConfig().get("CKG.kits." + type + ".slot3.item");
						slot3.setTypeId(i0);
					}
				} else {
					slot3.setTypeId(7);
				}
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot3.amount")) {
					if (plugin.getConfig().get("CKG.kits." + type + ".slot3.amount") instanceof String) {
						int i0;
						i0 = Integer.parseInt((String) plugin.getConfig().get("CKG.kits." + type + ".slot3.amount"));
						slot3.setAmount(i0);	
					} else if (plugin.getConfig().get("CKG.kits." + type + ".slot3.amount") instanceof Integer) {
						int i0;
						i0 = (int) plugin.getConfig().get("CKG.kits." + type + ".slot3.amount");
						slot3.setAmount(i0);	
					}
				} else {
					slot3.setAmount(1);
				}
				///// WAITING GENERATION //////////
				if (plugin.getConfig().getBoolean("CKG.kits." + type + ".slot3.display")) {
					if (plugin.dm.RequestAwaitingTime(p, type, "slot3").equals(5)) {
						slot3.setDurability((short) 0);
					} else {
						int i = plugin.dm.RequestAwaitingTime(p, type, "slot3");
						if (i > slot3.getDurability()) {
							slot3.setDurability(slot3.getType().getMaxDurability());
						} else if (i < slot3.getDurability()) {
							int y = -i;
							slot3.setDurability((short) y);
						}
					}	
				}
				////////////////////////////////
				// CODE BY JUSTCR4ZY - NO DUPLICATION
				ItemMeta meta3 = slot3.getItemMeta();
				//// META GENERATING
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot3.itemName")) {
					if (plugin.getConfig().contains("CKG.kits." + type + ".slot3.enabled")) {
						if ((boolean) plugin.getConfig().get("CKG.kits." + type + ".slot3.enabled")) {
							String i = (String) plugin.getConfig().get("CKG.kits." + type + ".slot3.itemName");
							if (plugin.dm.RequestAwaitingTime(p, type, "slot3").equals(5)) {
								if (plugin.getConfig().contains("CKG.kits." + type + ".slot3.permission")) {
									if (p.hasPermission(plugin.getConfig().getString("CKG.kits." + type + ".slot3.permission"))) {
										meta3.setDisplayName("§a[V] §9" + i);		
									} else {
										meta3.setDisplayName("§c[X] §9" + i);
										List<String> lore = new ArrayList<String>();
										lore.add("§crestricted");
										meta3.setLore(lore);
									}	
								} else {
									meta3.setDisplayName("§a[V] §9" + i);	
								}	
							} else {
								meta3.setDisplayName("§c[X] §9" + i);
								List<String> lore = new ArrayList<String>();
								lore.add("§cKit available in");
								if (plugin.dm.RequestAwaitingTime(p, type, "slot3") < -120) {
									if (plugin.dm.RequestAwaitingTime(p, type, "slot3") < -3600) {
										int temp = (plugin.dm.RequestAwaitingTime(p, type, "slot3") / 3600);
										if (temp < -48) {
											int temp2 = temp / 24;
											lore.add("§c >§5 " + temp2 + " days");
										} else {
											lore.add("§c >§5 " + temp + " hours");	
										}
									} else {
										int temp = (plugin.dm.RequestAwaitingTime(p, type, "slot3") / 60);
										lore.add("§c >§5 " + temp + " minutes");	
									}
								} else {
									lore.add("§c >§5 " + plugin.dm.RequestAwaitingTime(p, type, "slot3") + " seconds");	
								}
								meta3.setLore(lore);
								
							}
						} else {
							String i = (String) plugin.getConfig().get("CKG.kits." + type + ".slot3.itemName");
							meta3.setDisplayName("§c[X] §9" + i);
						}	
					} else {
						String i = (String) plugin.getConfig().get("CKG.kits." + type + ".slot3.itemName");
						meta3.setDisplayName("§c[X] §9" + i);
					}
				} else {
					meta3.setDisplayName("§4ERROR");
				}
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot3.itemLore") && plugin.dm.RequestAwaitingTime(p, type, "slot3").equals(5)) {
					List<String> i = new ArrayList<String>();
					i.addAll((List<String>) plugin.getConfig().get("CKG.kits." + type + ".slot3.itemLore"));
					meta3.setLore(i);
				} else {
				}
				slot3.setItemMeta(meta3);
				//////////////////////////////////////////////////////////////////////////////////////////////////
				////////////////////////////////////////////////////////////////////////////////////////////////
				////////////////////////////////////////////////////////////////////////////////////////////////
				ItemStack slot4 = new ItemStack(Material.BEDROCK);
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot4.item")) {
					if (plugin.getConfig().get("CKG.kits." + type + ".slot4.item") instanceof String) {
						if (plugin.getConfig().getString("CKG.kits." + type + ".slot4.item").contains(":")) {
							String[] fixed = plugin.getConfig().getString("CKG.kits." + type + ".slot4.item").split(":");
							slot4.setTypeId(Integer.parseInt(fixed[0]));
							slot4.setDurability(Short.parseShort(fixed[1]));
						} else {
							int i0;
							i0 = Integer.parseInt((String) plugin.getConfig().get("CKG.kits." + type + ".slot4.item"));
							slot4.setTypeId(i0);	
						}
					} else if (plugin.getConfig().get("CKG.kits." + type + ".slot4.item") instanceof Integer) {
						int i0;
						i0 = (int) plugin.getConfig().get("CKG.kits." + type + ".slot4.item");
						slot4.setTypeId(i0);
					}
				} else {
					slot4.setTypeId(7);
				}
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot4.amount")) {
					if (plugin.getConfig().get("CKG.kits." + type + ".slot4.amount") instanceof String) {
						int i0;
						i0 = Integer.parseInt((String) plugin.getConfig().get("CKG.kits." + type + ".slot4.amount"));
						slot4.setAmount(i0);	
					} else if (plugin.getConfig().get("CKG.kits." + type + ".slot4.amount") instanceof Integer) {
						int i0;
						i0 = (int) plugin.getConfig().get("CKG.kits." + type + ".slot4.amount");
						slot4.setAmount(i0);	
					}
				} else {
					slot4.setAmount(1);
				}
				///// WAITING GENERATION //////////
				if (plugin.getConfig().getBoolean("CKG.kits." + type + ".slot4.display")) {
					if (plugin.dm.RequestAwaitingTime(p, type, "slot4").equals(5)) {
						slot4.setDurability((short) 0);
					} else {
						int i = plugin.dm.RequestAwaitingTime(p, type, "slot4");
						if (i > slot4.getDurability()) {
							slot4.setDurability(slot4.getType().getMaxDurability());
						} else if (i < slot4.getDurability()) {
							int y = -i;
							slot4.setDurability((short) y);
						}
					}	
				}
				////////////////////////////////
				// CODE BY JUSTCR4ZY - NO DUPLICATION
				ItemMeta meta4 = slot4.getItemMeta();
				//// META GENERATING
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot4.itemName")) {
					if (plugin.getConfig().contains("CKG.kits." + type + ".slot4.enabled")) {
						if ((boolean) plugin.getConfig().get("CKG.kits." + type + ".slot4.enabled")) {
							String i = (String) plugin.getConfig().get("CKG.kits." + type + ".slot4.itemName");
							if (plugin.dm.RequestAwaitingTime(p, type, "slot4").equals(5)) {
								if (plugin.getConfig().contains("CKG.kits." + type + ".slot4.permission")) {
									if (p.hasPermission(plugin.getConfig().getString("CKG.kits." + type + ".slot4.permission"))) {
										meta4.setDisplayName("§a[V] §9" + i);		
									} else {
										meta4.setDisplayName("§c[X] §9" + i);
										List<String> lore = new ArrayList<String>();
										lore.add("§crestricted");
										meta4.setLore(lore);
									}	
								} else {
									meta4.setDisplayName("§a[V] §9" + i);	
								}	
							} else {
								meta4.setDisplayName("§c[X] §9" + i);
								List<String> lore = new ArrayList<String>();
								lore.add("§cKit available in");
								if (plugin.dm.RequestAwaitingTime(p, type, "slot4") < -120) {
									if (plugin.dm.RequestAwaitingTime(p, type, "slot4") < -3600) {
										int temp = (plugin.dm.RequestAwaitingTime(p, type, "slot4") / 3600);
										if (temp < -48) {
											int temp2 = temp / 24;
											lore.add("§c >§5 " + temp2 + " days");
										} else {
											lore.add("§c >§5 " + temp + " hours");	
										}
									} else {
										int temp = (plugin.dm.RequestAwaitingTime(p, type, "slot4") / 60);
										lore.add("§c >§5 " + temp + " minutes");	
									}
								} else {
									lore.add("§c >§5 " + plugin.dm.RequestAwaitingTime(p, type, "slot4") + " seconds");	
								}
								meta4.setLore(lore);
								
							}
						} else {
							String i = (String) plugin.getConfig().get("CKG.kits." + type + ".slot4.itemName");
							meta4.setDisplayName("§c[X] §9" + i);
						}	
					} else {
						String i = (String) plugin.getConfig().get("CKG.kits." + type + ".slot4.itemName");
						meta4.setDisplayName("§c[X] §9" + i);
					}
				} else {
					meta4.setDisplayName("§4ERROR");
				}
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot4.itemLore") && plugin.dm.RequestAwaitingTime(p, type, "slot4").equals(5)) {
					List<String> i = new ArrayList<String>();
					i.addAll((List<String>) plugin.getConfig().get("CKG.kits." + type + ".slot4.itemLore"));
					meta4.setLore(i);
				} else {
				}
				slot4.setItemMeta(meta4);
				//////////////////////////////////////////////////////////////////////////////////////////////////
				////////////////////////////////////////////////////////////////////////////////////////////////
				////////////////////////////////////////////////////////////////////////////////////////////////
				ItemStack slot5 = new ItemStack(Material.BEDROCK);
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot5.item")) {
					if (plugin.getConfig().get("CKG.kits." + type + ".slot5.item") instanceof String) {
						if (plugin.getConfig().getString("CKG.kits." + type + ".slot5.item").contains(":")) {
							String[] fixed = plugin.getConfig().getString("CKG.kits." + type + ".slot5.item").split(":");
							slot5.setTypeId(Integer.parseInt(fixed[0]));
							slot5.setDurability(Short.parseShort(fixed[1]));
						} else {
							int i0;
							i0 = Integer.parseInt((String) plugin.getConfig().get("CKG.kits." + type + ".slot5.item"));
							slot5.setTypeId(i0);	
						}
					} else if (plugin.getConfig().get("CKG.kits." + type + ".slot5.item") instanceof Integer) {
						int i0;
						i0 = (int) plugin.getConfig().get("CKG.kits." + type + ".slot5.item");
						slot5.setTypeId(i0);
					}
				} else {
					slot5.setTypeId(7);
				}
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot5.amount")) {
					if (plugin.getConfig().get("CKG.kits." + type + ".slot5.amount") instanceof String) {
						int i0;
						i0 = Integer.parseInt((String) plugin.getConfig().get("CKG.kits." + type + ".slot5.amount"));
						slot5.setAmount(i0);	
					} else if (plugin.getConfig().get("CKG.kits." + type + ".slot5.amount") instanceof Integer) {
						int i0;
						i0 = (int) plugin.getConfig().get("CKG.kits." + type + ".slot5.amount");
						slot5.setAmount(i0);	
					}
				} else {
					slot5.setAmount(1);
				}
				///// WAITING GENERATION //////////
				if (plugin.getConfig().getBoolean("CKG.kits." + type + ".slot5.display")) {
					if (plugin.dm.RequestAwaitingTime(p, type, "slot5").equals(5)) {
						slot5.setDurability((short) 0);
					} else {
						int i = plugin.dm.RequestAwaitingTime(p, type, "slot5");
						if (i > slot5.getDurability()) {
							slot5.setDurability(slot5.getType().getMaxDurability());
						} else if (i < slot5.getDurability()) {
							int y = -i;
							slot5.setDurability((short) y);
						}
					}	
				}
				////////////////////////////////
				// CODE BY JUSTCR4ZY - NO DUPLICATION
				ItemMeta meta5 = slot5.getItemMeta();
				//// META GENERATING
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot5.itemName")) {
					if (plugin.getConfig().contains("CKG.kits." + type + ".slot5.enabled")) {
						if ((boolean) plugin.getConfig().get("CKG.kits." + type + ".slot5.enabled")) {
							String i = (String) plugin.getConfig().get("CKG.kits." + type + ".slot5.itemName");
							if (plugin.dm.RequestAwaitingTime(p, type, "slot5").equals(5)) {
								if (plugin.getConfig().contains("CKG.kits." + type + ".slot5.permission")) {
									if (p.hasPermission(plugin.getConfig().getString("CKG.kits." + type + ".slot5.permission"))) {
										meta5.setDisplayName("§a[V] §9" + i);		
									} else {
										meta5.setDisplayName("§c[X] §9" + i);
										List<String> lore = new ArrayList<String>();
										lore.add("§crestricted");
										meta5.setLore(lore);
									}	
								} else {
									meta5.setDisplayName("§a[V] §9" + i);	
								}
							} else {
								meta5.setDisplayName("§c[X] §9" + i);
								List<String> lore = new ArrayList<String>();
								lore.add("§cKit available in");
								if (plugin.dm.RequestAwaitingTime(p, type, "slot5") < -120) {
									if (plugin.dm.RequestAwaitingTime(p, type, "slot5") < -3600) {
										int temp = (plugin.dm.RequestAwaitingTime(p, type, "slot5") / 3600);
										if (temp < -48) {
											int temp2 = temp / 24;
											lore.add("§c >§5 " + temp2 + " days");
										} else {
											lore.add("§c >§5 " + temp + " hours");	
										}
									} else {
										int temp = (plugin.dm.RequestAwaitingTime(p, type, "slot5") / 60);
										lore.add("§c >§5 " + temp + " minutes");	
									}
								} else {
									lore.add("§c >§5 " + plugin.dm.RequestAwaitingTime(p, type, "slot5") + " seconds");	
								}
								meta5.setLore(lore);
								
							}
						} else {
							String i = (String) plugin.getConfig().get("CKG.kits." + type + ".slot5.itemName");
							meta5.setDisplayName("§c[X] §9" + i);
						}	
					} else {
						String i = (String) plugin.getConfig().get("CKG.kits." + type + ".slot5.itemName");
						meta5.setDisplayName("§c[X] §9" + i);
					}
				} else {
					meta5.setDisplayName("§4ERROR");
				}
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot5.itemLore") && plugin.dm.RequestAwaitingTime(p, type, "slot5").equals(5)) {
					List<String> i = new ArrayList<String>();
					i.addAll((List<String>) plugin.getConfig().get("CKG.kits." + type + ".slot5.itemLore"));
					meta5.setLore(i);
				} else {
				}
				slot5.setItemMeta(meta5);
				//////////////////////////////////////////////////////////////////////////////////////////////////
				////////////////////////////////////////////////////////////////////////////////////////////////
				////////////////////////////////////////////////////////////////////////////////////////////////
				ItemStack slot6 = new ItemStack(Material.BEDROCK);
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot6.item")) {
					if (plugin.getConfig().get("CKG.kits." + type + ".slot6.item") instanceof String) {
						if (plugin.getConfig().getString("CKG.kits." + type + ".slot6.item").contains(":")) {
							String[] fixed = plugin.getConfig().getString("CKG.kits." + type + ".slot6.item").split(":");
							slot6.setTypeId(Integer.parseInt(fixed[0]));
							slot6.setDurability(Short.parseShort(fixed[1]));
						} else {
							int i0;
							i0 = Integer.parseInt((String) plugin.getConfig().get("CKG.kits." + type + ".slot6.item"));
							slot6.setTypeId(i0);	
						}	
					} else if (plugin.getConfig().get("CKG.kits." + type + ".slot6.item") instanceof Integer) {
						int i0;
						i0 = (int) plugin.getConfig().get("CKG.kits." + type + ".slot6.item");
						slot6.setTypeId(i0);
					}
				} else {
					slot6.setTypeId(7);
				}
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot6.amount")) {
					if (plugin.getConfig().get("CKG.kits." + type + ".slot6.amount") instanceof String) {
						int i0;
						i0 = Integer.parseInt((String) plugin.getConfig().get("CKG.kits." + type + ".slot6.amount"));
						slot6.setAmount(i0);	
					} else if (plugin.getConfig().get("CKG.kits." + type + ".slot6.amount") instanceof Integer) {
						int i0;
						i0 = (int) plugin.getConfig().get("CKG.kits." + type + ".slot6.amount");
						slot6.setAmount(i0);	
					}
				} else {
					slot6.setAmount(1);
				}
				///// WAITING GENERATION //////////
				if (plugin.getConfig().getBoolean("CKG.kits." + type + ".slot6.display")) {
					if (plugin.dm.RequestAwaitingTime(p, type, "slot6").equals(5)) {
						slot6.setDurability((short) 0);
					} else {
						int i = plugin.dm.RequestAwaitingTime(p, type, "slot6");
						if (i > slot6.getDurability()) {
							slot6.setDurability(slot6.getType().getMaxDurability());
						} else if (i < slot6.getDurability()) {
							int y = -i;
							slot6.setDurability((short) y);
						}
					}	
				}
				////////////////////////////////
				// CODE BY JUSTCR4ZY - NO DUPLICATION
				ItemMeta meta6 = slot6.getItemMeta();
				//// META GENERATING
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot6.itemName")) {
					if (plugin.getConfig().contains("CKG.kits." + type + ".slot6.enabled")) {
						if ((boolean) plugin.getConfig().get("CKG.kits." + type + ".slot6.enabled")) {
							String i = (String) plugin.getConfig().get("CKG.kits." + type + ".slot6.itemName");
							if (plugin.dm.RequestAwaitingTime(p, type, "slot6").equals(5)) {
								if (plugin.getConfig().contains("CKG.kits." + type + ".slot6.permission")) {
									if (p.hasPermission(plugin.getConfig().getString("CKG.kits." + type + ".slot6.permission"))) {
										meta6.setDisplayName("§a[V] §9" + i);		
									} else {
										meta6.setDisplayName("§c[X] §9" + i);
										List<String> lore = new ArrayList<String>();
										lore.add("§crestricted");
										meta6.setLore(lore);
									}	
								} else {
									meta6.setDisplayName("§a[V] §9" + i);	
								}
							} else {
								meta6.setDisplayName("§c[X] §9" + i);
								List<String> lore = new ArrayList<String>();
								lore.add("§cKit available in");
								if (plugin.dm.RequestAwaitingTime(p, type, "slot6") < -120) {
									if (plugin.dm.RequestAwaitingTime(p, type, "slot6") < -3600) {
										int temp = (plugin.dm.RequestAwaitingTime(p, type, "slot6") / 3600);
										if (temp < -48) {
											int temp2 = temp / 24;
											lore.add("§c >§5 " + temp2 + " days");
										} else {
											lore.add("§c >§5 " + temp + " hours");	
										}
									} else {
										int temp = (plugin.dm.RequestAwaitingTime(p, type, "slot6") / 60);
										lore.add("§c >§5 " + temp + " minutes");	
									}
								} else {
									lore.add("§c >§5 " + plugin.dm.RequestAwaitingTime(p, type, "slot6") + " seconds");	
								}
								meta6.setLore(lore);
								
							}
						} else {
							String i = (String) plugin.getConfig().get("CKG.kits." + type + ".slot6.itemName");
							meta6.setDisplayName("§c[X] §9" + i);
						}	
					} else {
						String i = (String) plugin.getConfig().get("CKG.kits." + type + ".slot6.itemName");
						meta6.setDisplayName("§c[X] §9" + i);
					}
				} else {
					meta6.setDisplayName("§4ERROR");
				}
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot6.itemLore") && plugin.dm.RequestAwaitingTime(p, type, "slot6").equals(5)) {
					List<String> i = new ArrayList<String>();
					i.addAll((List<String>) plugin.getConfig().get("CKG.kits." + type + ".slot6.itemLore"));
					meta6.setLore(i);
				} else {
				}
				slot6.setItemMeta(meta6);
				//////////////////////////////////////////////////////////////////////////////////////////////////
				////////////////////////////////////////////////////////////////////////////////////////////////
				////////////////////////////////////////////////////////////////////////////////////////////////
				ItemStack slot7 = new ItemStack(Material.BEDROCK);
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot7.item")) {
					if (plugin.getConfig().get("CKG.kits." + type + ".slot7.item") instanceof String) {
						if (plugin.getConfig().getString("CKG.kits." + type + ".slot7.item").contains(":")) {
							String[] fixed = plugin.getConfig().getString("CKG.kits." + type + ".slot7.item").split(":");
							slot7.setTypeId(Integer.parseInt(fixed[0]));
							slot7.setDurability(Short.parseShort(fixed[1]));
						} else {
							int i0;
							i0 = Integer.parseInt((String) plugin.getConfig().get("CKG.kits." + type + ".slot7.item"));
							slot7.setTypeId(i0);	
						}
					} else if (plugin.getConfig().get("CKG.kits." + type + ".slot7.item") instanceof Integer) {
						int i0;
						i0 = (int) plugin.getConfig().get("CKG.kits." + type + ".slot7.item");
						slot7.setTypeId(i0);
					}
				} else {
					slot7.setTypeId(7);
				}
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot7.amount")) {
					if (plugin.getConfig().get("CKG.kits." + type + ".slot7.amount") instanceof String) {
						int i0;
						i0 = Integer.parseInt((String) plugin.getConfig().get("CKG.kits." + type + ".slot7.amount"));
						slot7.setAmount(i0);	
					} else if (plugin.getConfig().get("CKG.kits." + type + ".slot7.amount") instanceof Integer) {
						int i0;
						i0 = (int) plugin.getConfig().get("CKG.kits." + type + ".slot7.amount");
						slot7.setAmount(i0);	
					}
				} else {
					slot7.setAmount(1);
				}
				///// WAITING GENERATION //////////
				if (plugin.getConfig().getBoolean("CKG.kits." + type + ".slot7.display")) {
					if (plugin.dm.RequestAwaitingTime(p, type, "slot7").equals(5)) {
						slot7.setDurability((short) 0);
					} else {
						int i = plugin.dm.RequestAwaitingTime(p, type, "slot7");
						if (i > slot7.getDurability()) {
							slot7.setDurability(slot7.getType().getMaxDurability());
						} else if (i < slot7.getDurability()) {
							int y = -i;
							slot7.setDurability((short) y);
						}
					}	
				}
				////////////////////////////////
				// CODE BY JUSTCR4ZY - NO DUPLICATION
				ItemMeta meta7 = slot7.getItemMeta();
				//// META GENERATING
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot7.itemName")) {
					if (plugin.getConfig().contains("CKG.kits." + type + ".slot7.enabled")) {
						if ((boolean) plugin.getConfig().get("CKG.kits." + type + ".slot7.enabled")) {
							String i = (String) plugin.getConfig().get("CKG.kits." + type + ".slot7.itemName");
							if (plugin.dm.RequestAwaitingTime(p, type, "slot7").equals(5)) {
								if (plugin.getConfig().contains("CKG.kits." + type + ".slot7.permission")) {
									if (p.hasPermission(plugin.getConfig().getString("CKG.kits." + type + ".slot7.permission"))) {
										meta7.setDisplayName("§a[V] §9" + i);		
									} else {
										meta7.setDisplayName("§c[X] §9" + i);
										List<String> lore = new ArrayList<String>();
										lore.add("§crestricted");
										meta7.setLore(lore);
									}	
								} else {
									meta7.setDisplayName("§a[V] §9" + i);	
								}	
							} else {
								meta7.setDisplayName("§c[X] §9" + i);
								List<String> lore = new ArrayList<String>();
								lore.add("§cKit available in");
								if (plugin.dm.RequestAwaitingTime(p, type, "slot7") < -120) {
									if (plugin.dm.RequestAwaitingTime(p, type, "slot7") < -3600) {
										int temp = (plugin.dm.RequestAwaitingTime(p, type, "slot7") / 3600);
										if (temp < -48) {
											int temp2 = temp / 24;
											lore.add("§c >§5 " + temp2 + " days");
										} else {
											lore.add("§c >§5 " + temp + " hours");	
										}
									} else {
										int temp = (plugin.dm.RequestAwaitingTime(p, type, "slot7") / 60);
										lore.add("§c >§5 " + temp + " minutes");	
									}
								} else {
									lore.add("§c >§5 " + plugin.dm.RequestAwaitingTime(p, type, "slot7") + " seconds");	
								}
								meta7.setLore(lore);
								
							}
						} else {
							String i = (String) plugin.getConfig().get("CKG.kits." + type + ".slot7.itemName");
							meta7.setDisplayName("§c[X] §9" + i);
						}	
					} else {
						String i = (String) plugin.getConfig().get("CKG.kits." + type + ".slot7.itemName");
						meta7.setDisplayName("§c[X] §9" + i);
					}
				} else {
					meta7.setDisplayName("§4ERROR");
				}
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot7.itemLore") && plugin.dm.RequestAwaitingTime(p, type, "slot7").equals(5)) {
					List<String> i = new ArrayList<String>();
					i.addAll((List<String>) plugin.getConfig().get("CKG.kits." + type + ".slot7.itemLore"));
					meta7.setLore(i);
				} else {
				}
				slot7.setItemMeta(meta7);
				//////////////////////////////////////////////////////////////////////////////////////////////////
				////////////////////////////////////////////////////////////////////////////////////////////////
				////////////////////////////////////////////////////////////////////////////////////////////////
				ItemStack slot8 = new ItemStack(Material.BEDROCK);
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot8.item")) {
					if (plugin.getConfig().get("CKG.kits." + type + ".slot8.item") instanceof String) {
						if (plugin.getConfig().getString("CKG.kits." + type + ".slot8.item").contains(":")) {
							String[] fixed = plugin.getConfig().getString("CKG.kits." + type + ".slot8.item").split(":");
							slot8.setTypeId(Integer.parseInt(fixed[0]));
							slot8.setDurability(Short.parseShort(fixed[1]));
						} else {
							int i0;
							i0 = Integer.parseInt((String) plugin.getConfig().get("CKG.kits." + type + ".slot8.item"));
							slot8.setTypeId(i0);	
						}	
					} else if (plugin.getConfig().get("CKG.kits." + type + ".slot8.item") instanceof Integer) {
						int i0;
						i0 = (int) plugin.getConfig().get("CKG.kits." + type + ".slot8.item");
						slot8.setTypeId(i0);
					}
				} else {
					slot8.setTypeId(7);
				}
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot8.amount")) {
					if (plugin.getConfig().get("CKG.kits." + type + ".slot8.amount") instanceof String) {
						int i0;
						i0 = Integer.parseInt((String) plugin.getConfig().get("CKG.kits." + type + ".slot8.amount"));
						slot8.setAmount(i0);	
					} else if (plugin.getConfig().get("CKG.kits." + type + ".slot8.amount") instanceof Integer) {
						int i0;
						i0 = (int) plugin.getConfig().get("CKG.kits." + type + ".slot8.amount");
						slot8.setAmount(i0);	
					}
				} else {
					slot8.setAmount(1);
				}
				///// WAITING GENERATION //////////
				if (plugin.getConfig().getBoolean("CKG.kits." + type + ".slot8.display")) {
					if (plugin.dm.RequestAwaitingTime(p, type, "slot8").equals(5)) {
						slot8.setDurability((short) 0);
					} else {
						int i = plugin.dm.RequestAwaitingTime(p, type, "slot8");
						if (i > slot8.getDurability()) {
							slot8.setDurability(slot8.getType().getMaxDurability());
						} else if (i < slot8.getDurability()) {
							int y = -i;
							slot8.setDurability((short) y);
						}
					}	
				}
				////////////////////////////////
				// CODE BY JUSTCR4ZY - NO DUPLICATION
				ItemMeta meta8 = slot8.getItemMeta();
				//// META GENERATING
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot8.itemName")) {
					if (plugin.getConfig().contains("CKG.kits." + type + ".slot8.enabled")) {
						if ((boolean) plugin.getConfig().get("CKG.kits." + type + ".slot8.enabled")) {
							String i = (String) plugin.getConfig().get("CKG.kits." + type + ".slot8.itemName");
							if (plugin.dm.RequestAwaitingTime(p, type, "slot8").equals(5)) {
								if (plugin.getConfig().contains("CKG.kits." + type + ".slot8.permission")) {
									if (p.hasPermission(plugin.getConfig().getString("CKG.kits." + type + ".slot8.permission"))) {
										meta8.setDisplayName("§a[V] §9" + i);		
									} else {
										meta8.setDisplayName("§c[X] §9" + i);
										List<String> lore = new ArrayList<String>();
										lore.add("§crestricted");
										meta8.setLore(lore);
									}	
								} else {
									meta8.setDisplayName("§a[V] §9" + i);	
								}	
							} else {
								meta8.setDisplayName("§c[X] §9" + i);
								List<String> lore = new ArrayList<String>();
								lore.add("§cKit available in");
								if (plugin.dm.RequestAwaitingTime(p, type, "slot8") < -120) {
									if (plugin.dm.RequestAwaitingTime(p, type, "slot8") < -3600) {
										int temp = (plugin.dm.RequestAwaitingTime(p, type, "slot8") / 3600);
										if (temp < -48) {
											int temp2 = temp / 24;
											lore.add("§c >§5 " + temp2 + " days");
										} else {
											lore.add("§c >§5 " + temp + " hours");	
										}
									} else {
										int temp = (plugin.dm.RequestAwaitingTime(p, type, "slot8") / 60);
										lore.add("§c >§5 " + temp + " minutes");	
									}
								} else {
									lore.add("§c >§5 " + plugin.dm.RequestAwaitingTime(p, type, "slot8") + " seconds");	
								}
								meta8.setLore(lore);
								
							}
						} else {
							String i = (String) plugin.getConfig().get("CKG.kits." + type + ".slot8.itemName");
							meta8.setDisplayName("§c[X] §9" + i);
						}	
					} else {
						String i = (String) plugin.getConfig().get("CKG.kits." + type + ".slot8.itemName");
						meta8.setDisplayName("§c[X] §9" + i);
					}
				} else {
					meta8.setDisplayName("§4ERROR");
				}
				if (plugin.getConfig().contains("CKG.kits." + type + ".slot8.itemLore") && plugin.dm.RequestAwaitingTime(p, type, "slot8").equals(5)) {
					List<String> i = new ArrayList<String>();
					i.addAll((List<String>) plugin.getConfig().get("CKG.kits." + type + ".slot8.itemLore"));
					meta8.setLore(i);
				} else {
				}
				slot8.setItemMeta(meta8);

				//////////////////////////////////////////////////////////////////////////////////////////////////
				////////////////////////////////////////////////////////////////////////////////////////////////
				////////////////////////////////////////////////////////////////////////////////////////////////
				//// GENERATING ////
				Inventory francepvp = Bukkit.createInventory(null, 9, type);
				francepvp.setItem(0, slot0);
				if (plugin.getConfig().getBoolean("CKG.kits." + type + ".slot1.enabled")) {
					francepvp.setItem(1, slot1);	
				}
				if (plugin.getConfig().getBoolean("CKG.kits." + type + ".slot2.enabled")) {
					francepvp.setItem(2, slot2);	
				}
				if (plugin.getConfig().getBoolean("CKG.kits." + type + ".slot3.enabled")) {
					francepvp.setItem(3, slot3);	
				}
				if (plugin.getConfig().getBoolean("CKG.kits." + type + ".slot4.enabled")) {
					francepvp.setItem(4, slot4);	
				}
				if (plugin.getConfig().getBoolean("CKG.kits." + type + ".slot5.enabled")) {
					francepvp.setItem(5, slot5);	
				}
				if (plugin.getConfig().getBoolean("CKG.kits." + type + ".slot6.enabled")) {
					francepvp.setItem(6, slot6);	
				}
				if (plugin.getConfig().getBoolean("CKG.kits." + type + ".slot7.enabled")) {
					francepvp.setItem(7, slot7);	
				}
				if (plugin.getConfig().getBoolean("CKG.kits." + type + ".slot8.enabled")) {
					francepvp.setItem(8, slot8);	
				}
				
				p.openInventory(francepvp);	
			}
		} else {
			kit2 = kit;
			slot2 = slot;
			Inventory createIt = Bukkit.createInventory(null, 9, kit + ":" + slot);
			if (plugin.getKits().contains(kit + "." + slot)) {
				try {
					ItemStack[] main = null;
					if (plugin.getKits().get(kit + "." + slot) instanceof ArrayList) {
						ArrayList<ItemStack> kits = (ArrayList<ItemStack>) plugin.getKits().get(kit + "." + slot);
						main = kits.toArray(new ItemStack[kits.size()]);
					} else if (plugin.getKits().get(kit + ".slot" + slot) instanceof ItemStack[]) {
						main = (ItemStack[]) plugin.getKits().get(kit + "." + slot);	
					}
					createIt.setContents(main);	
				}
				catch (Exception ex){
					
				}
			}
			p.openInventory(createIt);
		}
		
	}
	 public String addColor(String i) {
		    return ChatColor.translateAlternateColorCodes('&', i);
		  }

}
