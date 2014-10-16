package org.mle.combattweaks.listeners;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.mle.combattweaks.listeners.player.EntityDamageByPlayer;
import org.mle.combattweaks.listeners.player.PlayerDamageByEntity;
import org.mle.combattweaks.listeners.player.PlayerDamageByPlayer;

public class CombatTweaksListener implements Listener {
    
    @EventHandler(priority = EventPriority.MONITOR)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player && e.getEntity() instanceof Player) PlayerDamageByPlayer.onPlayerDamageByPlayer((Player) e.getDamager(), (Player) e.getEntity(), e);
		else if(e.getDamager() instanceof Player && e.getEntity() instanceof LivingEntity) EntityDamageByPlayer.onEntityDamageByPlayer((Player) e.getDamager(), (LivingEntity) e.getEntity(), e);
		else if(!(e.getDamager() instanceof Player) && e.getDamager() instanceof LivingEntity && e.getEntity() instanceof Player) PlayerDamageByEntity.onPlayerDamageByEntity((Player) e.getEntity(), (LivingEntity) e.getDamager(), e);
	}
    
    
}
