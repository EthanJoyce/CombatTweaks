package org.mle.combattweaks.listeners.player;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.mle.combattweaks.pvp.damagemods.ArmorDamageModifiers;
import org.mle.combattweaks.pvp.damagemods.PvPBetterBlocking;
import org.mle.combattweaks.pvp.damagemods.PvPHPDamageMultipliers;
import org.mle.combattweaks.pvp.damagemods.PvPStaticDamage;
import org.mle.combattweaks.util.ItemStackUtils;

public enum PlayerDamageByPlayer {;
    
    public static void onPlayerDamageByPlayer(Player damager, Player player, EntityDamageByEntityEvent e) {
        float damageMultiplier = PvPHPDamageMultipliers.getDamageMultiplier(damager.getItemInHand().getType());
        e.setDamage(e.getDamage() * damageMultiplier);
        
        int armorDamageMod = ArmorDamageModifiers.getDamageModifier(damager.getItemInHand().getType());
        for(ItemStack item : player.getInventory().getArmorContents())
            if(ItemStackUtils.isArmor(item))
                item.setDurability((short) (item.getDurability() + armorDamageMod));
        
        if(player.isBlocking())
            PvPBetterBlocking.onPlayerHitWhileBlocking(player, damager, e);
        
        if(PvPStaticDamage.getMeleeList().containsKey(damager.getItemInHand().getType())) {
            e.setDamage(0);
            player.damage(PvPStaticDamage.getDamage(damager.getItemInHand().getType()));
        }
    }
    
    
}
