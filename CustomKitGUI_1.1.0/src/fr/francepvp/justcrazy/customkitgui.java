package fr.francepvp.justcrazy;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class customkitgui extends JavaPlugin {
	// LOADDDDDD
	File PluginFolder;
	File configFile;
	File saveFolder;
	File saveFile;
	File locationFile;
	File kitsFolder;
	FileConfiguration save = null;
	FileConfiguration location = null;
	FileConfiguration kits = null;
	File kitsFile;
	// LIST OF KITSSSS
	List<String> KitLists;
	List<String> DefaultList;
	List<String> Enabled;
	List<String> DefaultLore2;
	////// SUB CLASS
	private final CKGblockListener bl = new CKGblockListener(this);
	private final CKGclickListener cl = new CKGclickListener(this);
	public final CKGvirtualinventory vi = new CKGvirtualinventory(this);
	public final CKGdelayManager dm = new CKGdelayManager(this);
	public final CKGautomaticrunner ar = new CKGautomaticrunner(this);
	public final PluginManager pm = Bukkit.getPluginManager();
	// CHARGGGGG
	public void onEnable(){
		////////////////////////////////////////////////
		getLogger().log(Level.INFO, "CustomKitGUI ( CKG ) is loading");
		KitLists = new ArrayList<String>();
		DefaultLore2 = new ArrayList<String>();
		DefaultList = new ArrayList<String>();
		Enabled = new ArrayList<String>();
		PluginFolder = getDataFolder();
		configFile = new File(PluginFolder, "config.yml");
		saveFolder = getDataFolder();
		saveFile = new File(saveFolder, "save.yml");
		locationFile = new File(saveFolder, "location.yml");
		kitsFolder = getDataFolder();
		kitsFile = new File(kitsFolder, "kits.yml");
		pm.registerEvents(bl, this);
		pm.registerEvents(cl, this);
		reloadLoc();
		reloadKits();
		loadglobalconfig();
		////////////////////////////////////////////////
		///////////////////////////////////////////////
	}
	// Unload
	public void onDisable(){
	}
	public void onReload() {
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] str) {
		if (cmd.getName().equalsIgnoreCase("ckg")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("§cConsole not allowed to use this command");
				return true;
			}
			Player p = (Player) sender;
			if (!p.hasPermission("ckg.command")) {
				p.sendMessage("§4You don't have enought permission");
				return true;
			}
			if (str.length > 0) {
				if (str[0].equalsIgnoreCase("help")) {
					p.sendMessage("§6§n=§e§l CustomKitGUI§e Help page");
					p.sendMessage("§e->§6 /ckg create [CONTAINER]§7 create a new container");
					p.sendMessage("§e->§6 /ckg modify [CONTAINER] [SLOT0-8]§7 Show what can be changed");
					p.sendMessage("§e->§6 /ckg info [CONTAINER]§7 display some information");
					p.sendMessage("§e->§6 /ckg cc [CONTAINER]§7 link a goldblock and a container");
					p.sendMessage("§e->§6 /ckg rc [CONTAINER]§7 break the link with the container");
					p.sendMessage("§e->§6 /ckg view [CONTAINER]§7 Virtualize the container");
					p.sendMessage("§e->§6 /ckg perm§7 See the available permissions");
					p.sendMessage("§e->§6 /ckg removedelay [CONTAINER] [SLOT0-8] {PLAYER}§7 bypass the delay");
				}
				if (str[0].equalsIgnoreCase("view")) {
					if (p.isOp() || p.hasPermission("CKG.view")) {
						if (str.length > 1) {
							if (getConfig().contains("CKG.kits." + str[1])) {
								vi.OpenINV(p, str[1], null, "slot11");
							} else {
								p.sendMessage("§cCould not find the CONTAINER '" + str[1] + "'.");
								p.sendMessage("§c-> Try /ckg list");
							}
						} else {
							p.sendMessage("§aUsage: /ckg view§c [CONTAINER]");
						}
					}
				}
				if (str[0].equalsIgnoreCase("create")) {
					if (p.isOp() || p.hasPermission("CKG.create")) {
						if (str.length > 1) {
							if (getConfig().contains("CKG.kits." + str[1])) {
								p.sendMessage("§6[§eCKG§6]§e Kits already exist.");
								return true;
							}
							create(str[1]);
							p.sendMessage("§6[§eCKG§6]§e CONTAINER §6" + str[1] + "§e Successfully created !");
							p.sendMessage("§6[§eCKG§6]§e Just type /ckg modify [CONTAINER] to continue creation");
						} else {
							p.sendMessage("§6[§eCKG§6]§e usage: /ckg create [CONTAINER]");	
						}
					} else {
						p.sendMessage("§cManage your business.");
					}
				}
				if (str[0].equalsIgnoreCase("bypassdelay") || str[0].equalsIgnoreCase("removedelay")) {
					if (!p.hasPermission("CKG.removedelay") || !p.hasPermission("CKG.bypassdelay")) {
						p.sendMessage("§cManage your business");
						return true;
					}
					if (str.length > 3) {
						if (!getConfig().contains("CKG.kits." + str[1])) {
							p.sendMessage("§cContainer Not Found ! ( /ckg list )");
							return true;
						}
						dm.RemoveAwaitingTime(str[3], str[1], str[2]);
						return true;
					} else if (str.length > 2){
						if (!getConfig().contains("CKG.kits." + str[1])) {
							p.sendMessage("§cContainer Not Found ! ( /ckg list )");
							return true;
						}
						dm.RemoveAwaitingTime(p.getName(), str[1], str[2]);
						return true;
					} else if (str.length > 1) {
						if (!getConfig().contains("CKG.kits." + str[1])) {
							p.sendMessage("§cContainer Not Found ! ( /ckg list )");
							return true;
						}
						p.sendMessage("§a/ckg " + str[0] + " " + str[1] + " §cSLOT {PLAYER}");
						return true;
					}
					p.sendMessage("§a/ckg " + str[0] + " §cCONTAINER SLOT {PLAYER}");
				}
				if (str[0].equalsIgnoreCase("modify")) {
					if (!p.hasPermission("CKG.modify")) {
						p.sendMessage("§cManage your business");
						return true;
					}
					if (str.length > 1) {
						if (getConfig().contains("CKG.kits." + str[1])) {
							if (str.length > 2) {
								if (getConfig().contains("CKG.kits." + str[1] + "." + str[2])) {
									if (str.length > 3) {
										if (str[3].equalsIgnoreCase("setamount") || str[3].equalsIgnoreCase("sa")) {
											if (str.length > 4) {
												getConfig().set("CKG.kits." + str[1] + "." + str[2] + ".amount", str[4]);
												saveConfig();
												p.sendMessage("§e>§a Action Success");
											} else {
												p.sendMessage("§e>§a /ckg modify [KIT] [SLOT] amount §cInteger");
											}
											return true;
										}
										if(str[3].equalsIgnoreCase("setID") || str[3].equalsIgnoreCase("sid")) {
											if (str.length > 4) {
												getConfig().set("CKG.kits." + str[1] + "." + str[2] + ".item", str[4]);
												saveConfig();
												p.sendMessage("§e>§a Action Success");
											} else {
												p.sendMessage("§e>§a /ckg modify [KIT] [SLOT] itemid §cInteger");
											}
											return true;
										}
										if (str[3].equalsIgnoreCase("setGive") || str[3].equalsIgnoreCase("sg")) {
											String kit = str[1];
											String slot = str[2];
											vi.OpenINV(p, "createNEW", kit, slot);
											return true;
										}
										if (str[3].equalsIgnoreCase("setperm") || str[3].equalsIgnoreCase("sp")) {
											if (str.length > 4) {
												if (str[4].equalsIgnoreCase("none")) {
													getConfig().set("CKG.kits." + str[1] + "." + str[2] + ".permission", null);
													p.sendMessage("§aPermission has been blanked !");
													return true;
												}
												getConfig().set("CKG.kits." + str[1] + "." + str[2] + ".permission", str[4]);
												p.sendMessage("§aPermission modified !");
											} else {
												p.sendMessage("§e>§c Error, missing permission");
												p.sendMessage("§7§otype none as permission to reset it");
												p.sendMessage("§e>§a /ckg modify [KIT] [SLOT] sp §cpermission");
											}
											return true;
										}
										if (str[3].equalsIgnoreCase("enabled")) {
											if (str.length > 4) {
												if (str[4].equalsIgnoreCase("true")) {
													getConfig().set("CKG.kits." + str[1] + "." + str[2] + ".enabled", true);
													p.sendMessage("§e>§a Action Success");
													saveConfig();
													return true;
												} else if (str[4].equalsIgnoreCase("false")) {
													getConfig().set("CKG.kits." + str[1] + "." + str[2] + ".enabled", false);
													p.sendMessage("§e>§a Action Success");
													saveConfig();
													return true;
												}
											} else {
												p.sendMessage("§e>§a /ckg modify [KIT] [SLOT] setName §ctrue/false");
												return true;
											}
											p.sendMessage("§e>§a /ckg modify [KIT] [SLOT] setName §ctrue/false");
											return true;
										}
										if (str[3].equalsIgnoreCase("delay")) {
											if (str.length > 4) {
												try {
													getConfig().set("CKG.kits." + str[1] + "." + str[2] + ".delay", str[4]);
													p.sendMessage("§e>§a Action Success");
													saveConfig();
													return true;
												}
												catch (Exception ex) {
													p.sendMessage("§e>§c Number in second expected");
													p.sendMessage("§e>§a /ckg modify [KIT] [SLOT] delay §cInteger");
													return true;
												}
											} else {
												p.sendMessage("§e>§c Integer missing");
												p.sendMessage("§e>§a /ckg modify [KIT] [SLOT] delay §cInteger");
												return true;
											}
										}
										if (str[3].equalsIgnoreCase("DisplayDamage") ||str[3].equalsIgnoreCase("dd")) {
											if (str.length > 4) {
												if (str[4].equalsIgnoreCase("true")) {
													getConfig().set("CKG.kits." + str[1] + "." + str[2] + ".display", true);
													p.sendMessage("§e>§a Action Success");
													saveConfig();
													return true;
												} else if (str[4].equalsIgnoreCase("false")) {
													getConfig().set("CKG.kits." + str[1] + "." + str[2] + ".display", false);
													p.sendMessage("§e>§a Action Success");
													saveConfig();
													return true;
												}
											} else {
												p.sendMessage("§e>§c Missing argument");
												p.sendMessage("§e>§a /ckg modify [KIT] [SLOT] delayDisplay true/false");
											}
										}
										if (str[3].equalsIgnoreCase("setName") || str[3].equalsIgnoreCase("sn")) {
											if (str.length > 4) {
												if (str[4] instanceof String) {
													if (str.length == 6) {
														getConfig().set("CKG.kits." + str[1] + "." + str[2] + ".itemName", str[4] + " " + str[5]);
													} else {
														getConfig().set("CKG.kits." + str[1] + "." + str[2] + ".itemName", str[4]);	
													}
													saveConfig();
													p.sendMessage("§e>§a Action Success");
												}
											} else {
												p.sendMessage("§e>§a /ckg modify [CONTAINER] [SLOT] setName §cString");
											}
											return true;
										}
										if (str[3].equalsIgnoreCase("setLore") || str[3].equalsIgnoreCase("sl")) {
											if (str.length > 4) {
												String[] test = str[4].split("/");
												getConfig().set("CKG.kits." + str[1] + "." + str[2] + ".itemLore", test);
												saveConfig();
												reloadConfig();
												p.sendMessage("§eLore saved!");
											}
											return true;
										}
									}
									p.sendMessage("§6[§eCKG§6]§e Available in games changment:");
									p.sendMessage("§e- >§6 setAmount(sa) [INT]");
									p.sendMessage("§e- >§6 setID(sID) [INT]");
									p.sendMessage("§e- >§6 setName(sn) [STRING (2)]");
									p.sendMessage("§e- >§6 setGive(sg) [OPENINV]");
									p.sendMessage("§e- >§6 DisplayDamage(dd) [TRUE/FALSE]");
									p.sendMessage("§e- >§6 delay [INT]");
									p.sendMessage("§e- >§6 enabled [TRUE/FALSE]");
									p.sendMessage("§e- >§6 setLore(sl) [STRING/STRING]");
								} else {
									p.sendMessage("§e> §cKit not found");
									p.sendMessage("§e> §a/ckg modify [CONTAINER] §c[SLOT]");
									p.sendMessage("§e> §aAvailable slot : SLOT[0-8]");
								}
							} else {
								p.sendMessage("§e> §cSlot not found");
								p.sendMessage("§e> §a/ckg modify [CONTAINER] §c[SLOT]");
								p.sendMessage("§e> §aAvailable slot : SLOT[0-8]");
							}
						} else {
							p.sendMessage("§e> §cType '/ckg list' to get list");
							p.sendMessage("§e> §a/ckg modify §c[CONTAINER] [SLOT]");
						}
					} else {
						p.sendMessage("§e> §a/ckg modify §c[CONTAINER] [SLOT]");
					}
				}
				if (str[0].equalsIgnoreCase("createContainer") || str[0].equalsIgnoreCase("cc")) {
					if (str.length > 1) {
						if (p.getTargetBlock(null, 10).getType().equals(Material.GOLD_BLOCK)) {
							Block b = p.getTargetBlock(null, 10);
							if (getLoc().contains("loc." + b.getX() + b.getY() + b.getZ())) {
								p.sendMessage("§6[§eCKG§6]§e GOLDBLOCK already initialized and work perfectly");
								return true;
							}
							if (getConfig().getStringList("CKG.EnabledKits").contains(str[1])) {
								getLoc().set("loc." + b.getX() + b.getY() + b.getZ() + ".TypeKit", str[1]);
								p.playEffect(b.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
								p.sendMessage("§7Kits Machine successfully created !");
								locSave();
								return true;
							} else {
								p.sendMessage("§7Invalid container name");
								return true;
							}	
						} else {
							p.sendMessage("§6[§eCKG§6]§e You must point GOLD BLOCK to create Kit Container");
							return true;
						}
					}
					p.sendMessage("§6[§eCKG§6]§e Usage: /ckg cc [CONTAINER]");
					return true;
				}
				if (str[0].equalsIgnoreCase("removeContainer") || str[0].equalsIgnoreCase("rc")) {
					Block b = p.getTargetBlock(null, 10);
					if (!getLoc().contains("loc." + b.getX() + b.getY() + b.getZ())) {
						p.sendMessage("§cPlease look at the container");
						return true;
					} else {
						getLoc().set("loc." + b.getX() + b.getY() + b.getZ(), null);
						locSave();
						p.sendMessage("§aSuccessfully reset");
					}
				}
				if (str[0].equalsIgnoreCase("permissions") || str[0].equalsIgnoreCase("perm")) {
					p.sendMessage("§ePermission List");
					p.sendMessage("§9CKG.use§3 use the container thanks to GoldBlock");
					p.sendMessage("§9CKG.command§3 access to the command");
					p.sendMessage("§9CKG.create§3 allow player to create a CONTAINER");
					p.sendMessage("§9CKG.modify§3 allow player to modify kits/slots/containers");
					p.sendMessage("§9CKG.remove§3 allow player to remove CONTAINER");
					p.sendMessage("§9CKG.info§3 allow player to access information");
					p.sendMessage("§9CKG.removedelay§3 allow player to use command removedelay");
				}
				if (str[0].equalsIgnoreCase("remove") || str[0].equalsIgnoreCase("rem")) {
					if (!p.hasPermission("CKG.remove")) {
						p.sendMessage("§cManage your business");
						return true;
					}
					if (str.length > 1) {
						if (getConfig().contains("CKG.kits." + str[1])) {
							List<String> temp = getConfig().getStringList("CKG.EnabledKits");
							temp.remove(str[1]);
							getConfig().set("CKG.EnabledKits", temp);
							getConfig().set("CKG.kits." + str[1], null);
							p.sendMessage("§aCONTAINER Removed.");
							saveConfig();
						} else {
							p.sendMessage("§cPlease specify a CONTAINER");
							p.sendMessage("§e- >§6 try /ckg list to get list");
						}
					} else {
						p.sendMessage("§e- >§6 try /ckg list to get list");
						p.sendMessage("§6[§eCKG§6]§e §a/ckg remove §cCONTAINER");
					}
				}
				if (str[0].equalsIgnoreCase("list") || str[0].equalsIgnoreCase("ls")) {
					if (getConfig().contains("CKG.kits")) {
						List<String> test = getConfig().getStringList("CKG.EnabledKits");
						int size = test.size();
						p.sendMessage("§aAvailable kits :");
						ar.commandList(p, size, test, false);
						
					}
				}
				if (str[0].equalsIgnoreCase("info") || str[0].equalsIgnoreCase("inf")) {
					if (!p.hasPermission("CKG.info")) {
						return true;
					}
					if (str.length > 1) {
						if (str.length > 2) {
							if (getConfig().contains("CKG.kits." + str[1] + "." + str[2])) {
								if (getConfig().contains("CKG.kits." + str[1])) {
									if (getConfig().contains("CKG.kits." + str[1] + "." + str[2])) {
										if (getKits().contains(str[1] + "." + str[2])) {
											p.sendMessage("§aThere is an attached Kit");
										} else {
											p.sendMessage("§cThere is no attached Kit");
										}
									} else {
										p.sendMessage("§cThere is no attached Kit");
									}
									if (getConfig().contains("CKG.kits." + str[1] + "." + str[2] + ".delay")) {
										p.sendMessage("§eDelay: §6" + getConfig().get("CKG.kits." + str[1] + "." + str[2] + ".delay"));
									} else {
										p.sendMessage("§eDelay: §6none");
									}
									if (getConfig().contains("CKG.kits." + str[1] + "." + str[2] + ".itemName")) {
										p.sendMessage("§ename: §6" + getConfig().get("CKG.kits." + str[1] + "." + str[2] + ".itemName"));
									}
									if (getConfig().contains("CKG.kits." + str[1] + "." + str[2] + ".item")) {
										p.sendMessage("§eID item: §6" + getConfig().get("CKG.kits." + str[1] + "." + str[2] + ".item"));
									}
									if (getConfig().contains("CKG.kits." + str[1] + "." + str[2] + ".amount")) {
										p.sendMessage("§eAMOUNT item: §6" + getConfig().get("CKG.kits." + str[1] + "." + str[2] + ".amount"));
									}
									if (getConfig().contains("CKG.kits." + str[1] + "." + str[2] + ".permission")) {
										p.sendMessage("§ePermission:§6 " + getConfig().get("CKG.kits." + str[1] + "." + str[2] + ".permission"));
									} else {
										p.sendMessage("§ePermission:§6 none");
									}
								}
							} else {
								p.sendMessage("§cSlot not found");
							}
						} else {
							if (getConfig().contains("CKG.kits." + str[1])) {
								p.sendMessage("§e=========== §6" + str[1]);
								if (getConfig().contains("CKG.kits." + str[1] + ".slot0.enabled")) {
									if (getConfig().getBoolean("CKG.kits." + str[1] + ".slot0.enabled")) {
										p.sendMessage("§a[V] slot0");									
									} else {
										p.sendMessage("§c[X] slot0");	
									}
								} else {
									p.sendMessage("§c[X] slot0");
								}
								if (getConfig().contains("CKG.kits." + str[1] + ".slot1.enabled")) {
									if (getConfig().getBoolean("CKG.kits." + str[1] + ".slot1.enabled")) {
										p.sendMessage("§a[V] slot1");									
									} else {
										p.sendMessage("§c[X] slot1");	
									}
								} else {
									p.sendMessage("§c[X] slot1");
								}
								if (getConfig().contains("CKG.kits." + str[1] + ".slot2.enabled")) {
									if (getConfig().getBoolean("CKG.kits." + str[1] + ".slot2.enabled")) {
										p.sendMessage("§a[V] slot2");									
									} else {
										p.sendMessage("§c[X] slot2");	
									}
								} else {
									p.sendMessage("§c[X] slot2");
								}
								if (getConfig().contains("CKG.kits." + str[1] + ".slot3.enabled")) {
									if (getConfig().getBoolean("CKG.kits." + str[1] + ".slot3.enabled")) {
										p.sendMessage("§a[V] slot3");									
									} else {
										p.sendMessage("§c[X] slot3");	
									}
								} else {
									p.sendMessage("§c[X] slot3");
								}
								if (getConfig().contains("CKG.kits." + str[1] + ".slot4.enabled")) {
									if (getConfig().getBoolean("CKG.kits." + str[1] + ".slot4.enabled")) {
										p.sendMessage("§a[V] slot4");									
									} else {
										p.sendMessage("§c[X] slot4");	
									}
								} else {
									p.sendMessage("§c[X] slot4");
								}
								if (getConfig().contains("CKG.kits." + str[1] + ".slot5.enabled")) {
									if (getConfig().getBoolean("CKG.kits." + str[1] + ".slot5.enabled")) {
										p.sendMessage("§a[V] slot5");									
									} else {
										p.sendMessage("§c[X] slot5");	
									}
								} else {
									p.sendMessage("§c[X] slot5");
								}
								if (getConfig().contains("CKG.kits." + str[1] + ".slot6.enabled")) {
									if (getConfig().getBoolean("CKG.kits." + str[1] + ".slot6.enabled")) {
										p.sendMessage("§a[V] slot6");									
									} else {
										p.sendMessage("§c[X] slot6");	
									}
								} else {
									p.sendMessage("§c[X] slot6");
								}
								if (getConfig().contains("CKG.kits." + str[1] + ".slot7.enabled")) {
									if (getConfig().getBoolean("CKG.kits." + str[1] + ".slot7.enabled")) {
										p.sendMessage("§a[V] slot7");									
									} else {
										p.sendMessage("§c[X] slot7");	
									}
								} else {
									p.sendMessage("§c[X] slot7");
								}
								if (getConfig().contains("CKG.kits." + str[1] + ".slot8.enabled")) {
									if (getConfig().getBoolean("CKG.kits." + str[1] + ".slot8.enabled")) {
										p.sendMessage("§a[V] slot8");									
									} else {
										p.sendMessage("§c[X] slot8");	
									}
								} else {
									p.sendMessage("§c[X] slot8");
								}
								p.sendMessage("§e/ckg info [CONTAINER] [SLOT]");
							} else {
								p.sendMessage("§cContainer not found");
								p.sendMessage("§aUtilisation: /ckg info §c[CONTAINER]");
							}	
						}
					} else {
						p.sendMessage("§aUtilisation: /ckg info §c[CONTAINER]");
					}
				}
			} else {
				p.sendMessage("§6=============§e CustomKitGUI V1.1.0§6 ==============");
				p.sendMessage("§7http://dev.bukkit.org/bukkit-plugins/CustomKitGUI/");
				p.sendMessage("§6================================================");
				p.sendMessage("            §e->§6 /ckg help §e<-");
				return true;
			}
		}
		return true;
		
	}
	////////////////////////////////////////////////////////
	/////// DEFAULT SLOTS CREATION ////////////////////////
	public void create(String s) {
		List<String> s2 = getConfig().getStringList("CKG.EnabledKits");
		s2.add(s);
		getConfig().set("CKG.kits." + s + ".slot0.item", 341);
		getConfig().set("CKG.kits." + s + ".slot0.amount", 1);
		getConfig().set("CKG.kits." + s + ".slot0.itemName", "slot0");
		getConfig().set("CKG.kits." + s + ".slot1.item", 332);
		getConfig().set("CKG.kits." + s + ".slot1.amount", 2);
		getConfig().set("CKG.kits." + s + ".slot1.itemName", "slot1");
		getConfig().set("CKG.kits." + s + ".slot2.item", 341);
		getConfig().set("CKG.kits." + s + ".slot2.amount", 3);
		getConfig().set("CKG.kits." + s + ".slot2.itemName", "slot2");
		getConfig().set("CKG.kits." + s + ".slot3.item", 332);
		getConfig().set("CKG.kits." + s + ".slot3.amount", 4);
		getConfig().set("CKG.kits." + s + ".slot3.itemName", "slot3");
		getConfig().set("CKG.kits." + s + ".slot4.item", 341);
		getConfig().set("CKG.kits." + s + ".slot4.amount", 5);
		getConfig().set("CKG.kits." + s + ".slot4.itemName", "slot4");
		getConfig().set("CKG.kits." + s + ".slot5.item", 332);
		getConfig().set("CKG.kits." + s + ".slot5.amount", 6);
		getConfig().set("CKG.kits." + s + ".slot5.itemName", "slot5");
		getConfig().set("CKG.kits." + s + ".slot6.item", 341);
		getConfig().set("CKG.kits." + s + ".slot6.amount", 7);
		getConfig().set("CKG.kits." + s + ".slot6.itemName", "slot6");
		getConfig().set("CKG.kits." + s + ".slot7.item", 332);
		getConfig().set("CKG.kits." + s + ".slot7.amount", 8);
		getConfig().set("CKG.kits." + s + ".slot7.itemName", "slot7");
		getConfig().set("CKG.kits." + s + ".slot8.item", 341);
		getConfig().set("CKG.kits." + s + ".slot8.amount", 9);
		getConfig().set("CKG.kits." + s + ".slot8.itemName", "slot8");
		getConfig().set("CKG.EnabledKits", s2);
		saveConfig();
	}
	////////////////////////////////////////////////////////
	//// CONFIG AND FILE CONFIGURATION ET STARTMENT ///////
	@SuppressWarnings("unchecked")
	public void loadglobalconfig() {
		if (!PluginFolder.exists()) {
			PluginFolder.mkdir();
		}
		if (!configFile.exists()) {
			try {
				configFile.createNewFile();
			} catch (IOException e) {
				getLogger().log(Level.SEVERE, "Could not create save config, CKG has been disabled");
				this.setEnabled(false);
			}
			getConfig().set("CKG.option.autosave.enabled", true);
			getConfig().set("CKG.option.autosave.delay", 900);
			create("DefaultKit");
		}
		KitLists = (List<String>) getConfig().getList("CKG.EnabledKits");
		if (!saveFile.exists()) {
			try {
				saveFile.createNewFile();
				save = YamlConfiguration.loadConfiguration(saveFile);
			} catch (IOException e) {
				getLogger().log(Level.SEVERE, "Could not create save config, CKG has been disabled");
				this.setEnabled(false);
			}
			
		}
		
	}
	/////////////// SAVE //////////////////////////////////////////
	public FileConfiguration getSave() {
	    if (save == null) {
	        this.reloadSave();
	    }
	    return save;
	}
	public void savesave() {
	    if (save == null || saveFile == null) {
	    return;
	    }
	    try {
	        getSave().save(saveFile);
	    } catch (IOException ex) {
	        this.getLogger().log(Level.SEVERE, "Could not save config to " + saveFile, ex);
	    }
	}
	public void reloadSave() {
	    if (saveFile == null) {
	    saveFile = new File(getDataFolder(), "save.yml");
	    }
	    save = YamlConfiguration.loadConfiguration(saveFile);
	 
	    // Look for defaults in the jar
	    InputStream defConfigStream = this.getResource("config.yml");
	    if (defConfigStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        save.setDefaults(defConfig);
	    }
	    if (!saveFile.exists()) {
	    	try {
				saveFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	}
	////////// LOCATION //////////////////////////////////////////
	public FileConfiguration getLoc() {
		if (location == null) {
			this.reloadLoc();
		}
		return location;
	}
	public void locSave() {
	    if (location == null || locationFile == null) {
	    return;
	    }
	    try {
	        getLoc().save(locationFile);
	    } catch (IOException ex) {
	        this.getLogger().log(Level.SEVERE, "Could not save config to " + locationFile, ex);
	    }
	}
	public void reloadLoc() {
	    if (locationFile == null) {
	    locationFile = new File(getDataFolder(), "location.yml");
	    }
	    location = YamlConfiguration.loadConfiguration(locationFile);
	    // Look for defaults in the jar
	    InputStream defConfigStream = this.getResource("config.yml");
	    if (defConfigStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        location.setDefaults(defConfig);
	    }
	    if (!locationFile.exists()) {
	    	try {
				locationFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	}
	//////// KITS /////////////////////////////////////////////////
	public FileConfiguration getKits() {
		if (kits == null) {
			this.reloadKits();
		}
		return kits;
	}
	public void kitsSave() {
	    if (kits == null || kitsFile == null) {
	    return;
	    }
	    try {
	        getKits().save(kitsFile);
	    } catch (IOException ex) {
	        this.getLogger().log(Level.SEVERE, "Could not save config to " + kitsFile, ex);
	    }
	}
	public void reloadKits() {
	    if (kitsFile == null) {
	    kitsFile = new File(getDataFolder(), "kits.yml");
	    }
	    kits = YamlConfiguration.loadConfiguration(kitsFile);
	    // Look for defaults in the jar
	    InputStream defConfigStream = this.getResource("kits.yml");
	    if (defConfigStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        kits.setDefaults(defConfig);
	    }
	    if (!kitsFile.exists()) {
	    	try {
	    		kitsFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	}
}
