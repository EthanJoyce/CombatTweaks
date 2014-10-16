package org.mle.combattweaks.pvp.damagemods;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public enum PvPBetterBlocking {;
    
    private static boolean enabled = true;
    private static float chance = 0.5f;
    private static float damageMultiplier = 0.0f;
    private static int weaponDamage = 1;
    private static List<Integer> unblockableItems = Arrays.asList(new Integer[] { 0 });
    private static boolean sendFailMsg = true;
    private static String failMessage = ChatColor.RED + "That player broke through your block!";
    private static boolean sendMsg = true;
    private static String message = ChatColor.GREEN + "You blocked that attack!";
    private static boolean disableVanillaBlocking = true;
    
    private static SecureRandom random = new SecureRandom();
    
    
    public static void onPlayerHitWhileBlocking(Player player, Player damager, EntityDamageByEntityEvent e) {
        if(disableVanillaBlocking && e.getDamage() != 1.0)
            e.setDamage(e.getDamage() * 2);
        if(!enabled)
            return;
        
        
        float randFloat = random.nextFloat();
        boolean lucky = (randFloat <= chance);
        
        if(lucky) {
            if(unblockableItems.contains(damager.getItemInHand().getTypeId())) {
                if(sendFailMsg)
                    player.sendMessage(failMessage);
                
                return;
            }
            
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
	    enabled = config.getBoolean("PvP.Mechanics.Better-Blocking.enabled", enabled);
	    chance = (float) config.getDouble("PvP.Mechanics.Better-Blocking.chance", chance);
	    damageMultiplier = (float) config.getDouble("PvP.Mechanics.Better-Blocking.damage_multiplier", damageMultiplier);
	    weaponDamage = config.getInt("PvP.Mechanics.Better-Blocking.weapon_durability_damage", weaponDamage);
	    unblockableItems = config.getIntegerList("PvP.Mechanics.Better-Blocking.unblockable-item-ids");
	    sendFailMsg = config.getBoolean("PvP.Mechanics.Better-Blocking.send-fail-msg", sendMsg);
	    failMessage = config.getString("PvP.Mechanics.Better-Blocking.fail-msg", message);
	    sendMsg = config.getBoolean("PvP.Mechanics.Better-Blocking.send-block-msg", sendMsg);
	    message = config.getString("PvP.Mechanics.Better-Blocking.block-msg", message);
	    disableVanillaBlocking = config.getBoolean("PvP.Mechanics.Better-Blocking.disable-vanilla-blocking", disableVanillaBlocking);
	}
	
	public static void addDefaults(FileConfiguration config) {
	    config.addDefault("PvP.Mechanics.Better-Blocking.enabled", enabled);
	    config.addDefault("PvP.Mechanics.Better-Blocking.chance", chance);
	    config.addDefault("PvP.Mechanics.Better-Blocking.damage_multiplier", damageMultiplier);
	    config.addDefault("PvP.Mechanics.Better-Blocking.weapon_durability_damage", weaponDamage);
	    config.addDefault("PvP.Mechanics.Better-Blocking.unblockable-item-ids", unblockableItems);
	    config.addDefault("PvP.Mechanics.Better-Blocking.send-fail-msg", sendFailMsg);
	    config.addDefault("PvP.Mechanics.Better-Blocking.fail-msg", failMessage);
	    config.addDefault("PvP.Mechanics.Better-Blocking.send-block-msg", sendMsg);
	    config.addDefault("PvP.Mechanics.Better-Blocking.block-msg", message);
	    config.addDefault("PvP.Mechanics.Better-Blocking.disable-vanilla-blocking", disableVanillaBlocking);
	}
    
    
}
