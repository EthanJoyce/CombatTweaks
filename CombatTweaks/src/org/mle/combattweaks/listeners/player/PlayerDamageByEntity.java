package org.mle.combattweaks.listeners.player;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.mle.combattweaks.pve.damagemods.PvEBetterBlocking;

public enum PlayerDamageByEntity {;
    
    public static void onPlayerDamageByEntity(Player player, LivingEntity damager, EntityDamageByEntityEvent e) {
        if(player.isBlocking())
            PvEBetterBlocking.onPlayerHitWhileBlocking(player, damager, e);
    }
    
    
}
