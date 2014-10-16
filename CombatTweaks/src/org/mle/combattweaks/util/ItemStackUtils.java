package org.mle.combattweaks.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum ItemStackUtils {;
    
    public static boolean isArmor(ItemStack item) {
        Material t = item.getType();
        return (t.equals(Material.LEATHER_HELMET) || t.equals(Material.LEATHER_CHESTPLATE) || t.equals(Material.LEATHER_LEGGINGS) || t.equals(Material.LEATHER_BOOTS) || 
                     t.equals(Material.CHAINMAIL_HELMET) || t.equals(Material.CHAINMAIL_CHESTPLATE) || t.equals(Material.CHAINMAIL_LEGGINGS) || t.equals(Material.CHAINMAIL_BOOTS) ||
                     t.equals(Material.GOLD_HELMET) || t.equals(Material.GOLD_CHESTPLATE) || t.equals(Material.GOLD_LEGGINGS) || t.equals(Material.GOLD_BOOTS) ||
                     t.equals(Material.IRON_HELMET) || t.equals(Material.IRON_CHESTPLATE) || t.equals(Material.IRON_LEGGINGS) || t.equals(Material.IRON_BOOTS) ||
                     t.equals(Material.DIAMOND_HELMET) || t.equals(Material.DIAMOND_CHESTPLATE) || t.equals(Material.DIAMOND_LEGGINGS) || t.equals(Material.DIAMOND_BOOTS));
    }
    
    
}
