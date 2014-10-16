package org.mle.combattweaks;

import java.io.IOException;
import java.util.Arrays;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.combattweaks.Metrics;
import org.mle.combattweaks.listeners.CombatTweaksListener;
import org.mle.combattweaks.pve.damagemods.PvEBetterBlocking;
import org.mle.combattweaks.pve.damagemods.PvEHPDamageMultipliers;
import org.mle.combattweaks.pve.damagemods.PvEStaticDamage;
import org.mle.combattweaks.pvp.damagemods.ArmorDamageModifiers;
import org.mle.combattweaks.pvp.damagemods.PvPBetterBlocking;
import org.mle.combattweaks.pvp.damagemods.PvPHPDamageMultipliers;
import org.mle.combattweaks.pvp.damagemods.PvPStaticDamage;

public class CombatTweaks extends JavaPlugin {
    
    public static CombatTweaks instance;
    
    
	public void onEnable() {
	    instance = this;
	    
		loadConfiguration();
		
		getServer().getPluginManager().registerEvents(new CombatTweaksListener(), this);
		getCommand("combattweaks").setExecutor(new CombatTweaksCommandExecutor());
		
		try {
		    Metrics metrics = new Metrics(this);
		    metrics.start();
		} catch(IOException ex) {
		    ex.printStackTrace();
		    getServer().getLogger().severe("[CombatTweaks] Error starting Metrics: \"" + ex.getMessage() + "\".");
		}
	}
	
	
	public void loadConfiguration() {
	    getConfig().addDefault("config.version", getDescription().getVersion());
	    
	    getConfig().addDefault("PvP.Damage_Multipliers.HP.Melee", Arrays.asList(new String[] { "276 1.0", "279 1.0" }));
	    getConfig().addDefault("PvP.Damage_Modifiers.Armor_Durability.Melee", Arrays.asList(new String[] { "279 0" }));
	    getConfig().addDefault("PvP.Static_Damage.HP.Melee", Arrays.asList(new String[] {  }));
	    
	    getConfig().addDefault("PvE.Damage_Multipliers.HP.Melee", Arrays.asList(new String[] { "276 1.0", "279 1.0" }));
	    getConfig().addDefault("PvE.Static_Damage.HP.Melee", Arrays.asList(new String[] {  }));
	    
	    PvPBetterBlocking.addDefaults(getConfig());
	    PvEBetterBlocking.addDefaults(getConfig());
	    
	    getConfig().options().copyDefaults(true);
	    saveConfig();
	    
	    
	    try {
		    PvPHPDamageMultipliers.loadMeleeMultipliers(getConfig());
		    PvEHPDamageMultipliers.loadMeleeMultipliers(getConfig());
		    
		    ArmorDamageModifiers.loadMeleeMultipliers(getConfig());
		    
		    PvPBetterBlocking.loadOptions(getConfig());
		    PvEBetterBlocking.loadOptions(getConfig());
		    
		    PvPStaticDamage.loadMeleeMultipliers(getConfig());
		    PvEStaticDamage.loadMeleeMultipliers(getConfig());
		} catch(InvalidConfigurationException ex) {
		    getServer().getLogger().severe("[CombatTweaks] Error loading config: \"" + ex.getMessage() + "\"");
		    getServer().getLogger().severe("[CombatTweaks] Disabling CombatTweaks.");
		    
		    getServer().getPluginManager().disablePlugin(this);
		}
	}
	
    
}
