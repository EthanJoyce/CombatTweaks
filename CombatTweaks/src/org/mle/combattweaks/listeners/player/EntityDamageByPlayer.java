package org.mle.combattweaks.listeners.player;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.mle.combattweaks.pve.damagemods.PvEHPDamageMultipliers;
import org.mle.combattweaks.pve.damagemods.PvEStaticDamage;

public enum EntityDamageByPlayer {;
    
    public static void onEntityDamageByPlayer(Player player, LivingEntity entity, EntityDamageByEntityEvent e) {
        float damageMultiplier = PvEHPDamageMultipliers.getDamageMultiplier(player.getItemInHand().getType());
        e.setDamage(e.getDamage() * damageMultiplier);
        
        if(PvEStaticDamage.getMeleeList().containsKey(player.getItemInHand().getType())) {
            e.setDamage(0);
            entity.damage(PvEStaticDamage.getDamage(player.getItemInHand().getType()));
        }
    }
    
    
}
