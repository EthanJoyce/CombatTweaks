package org.mle.combattweaks.pve.damagemods;

import java.security.SecureRandom;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public enum PvEBetterBlocking {;
    
    public static boolean enabled = true;
    private static float chance = 0.5f;
    private static float damageMultiplier = 0.0f;
    private static int weaponDamage = 1;
    private static boolean sendMsg = true;
    private static String message = ChatColor.GREEN + "You blocked that attack!";
    private static boolean disableVanillaBlocking = true;
    
    private static SecureRandom random = new SecureRandom();
    
    
    public static void onPlayerHitWhileBlocking(Player player, LivingEntity damager, EntityDamageByEntityEvent e) {
        if(disableVanillaBlocking && e.getDamage() != 1.0)
            e.setDamage(e.getDamage() * 2);
        if(!enabled)
            return;
        
        float randFloat = random.nextFloat();
        boolean lucky = (randFloat <= chance);
        
        if(lucky) {
            ItemStack item = player.getItemInHand();
            item.setDurability((short) (item.getDurability() + weaponDamage));
            
            if(item.getDurability() >= item.getType().getMaxDurability()) {
                if(item.getAmount() > 1) item.setAmount(item.getAmount() - 1);
                else player.setItemInHand(new ItemStack(Material.AIR));
            }
            
            
            e.setDamage(e.getDamage() * damageMultiplier);
            
            if(sendMsg)
                player.sendMessage(message);
        }
    }
    
    
	public static void loadOptions(FileConfiguration config) {
	    enabled = config.getBoolean("PvE.Mechanics.Better-Blocking.enabled", enabled);
	    chance = (float) config.getDouble("PvE.Mechanics.Better-Blocking.chance", 0.5);
	    damageMultiplier = (float) config.getDouble("PvE.Mechanics.Better-Blocking.damage_multiplier", 0.5);
	    weaponDamage = config.getInt("PvE.Mechanics.Better-Blocking.weapon_durability_damage", weaponDamage);
	    sendMsg = config.getBoolean("PvE.Mechanics.Better-Blocking.send-msg", sendMsg);
	    message = config.getString("PvE.Mechanics.Better-Blocking.block-msg", message);
	    disableVanillaBlocking = config.getBoolean("PvE.Mechanics.Better-Blocking.disable-vanilla-blocking", disableVanillaBlocking);
	}
	
	public static void addDefaults(FileConfiguration config) {
	    config.addDefault("PvE.Mechanics.Better-Blocking.enabled", enabled);
	    config.addDefault("PvE.Mechanics.Better-Blocking.chance", chance);
	    config.addDefault("PvE.Mechanics.Better-Blocking.damage_multiplier", damageMultiplier);
	    config.addDefault("PvE.Mechanics.Better-Blocking.weapon_durability_damage", weaponDamage);
	    config.addDefault("PvE.Mechanics.Better-Blocking.send-msg", sendMsg);
	    config.addDefault("PvE.Mechanics.Better-Blocking.block-msg", message);
	    config.addDefault("PvE.Mechanics.Better-Blocking.disable-vanilla-blocking", disableVanillaBlocking);
	}
    
    
}
