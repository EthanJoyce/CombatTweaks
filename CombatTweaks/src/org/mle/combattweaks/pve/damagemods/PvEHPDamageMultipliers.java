package org.mle.combattweaks.pve.damagemods;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.mle.combattweaks.util.NumberUtils;

public enum PvEHPDamageMultipliers {;
    
    private static HashMap<Material, Float> meleeMultipliers = new HashMap<Material, Float>();
    
    
    public static void loadMeleeMultipliers(FileConfiguration config) throws InvalidConfigurationException {
        List<String> list = config.getStringList("PvE.Damage_Multipliers.HP.Melee");
        
        for(String str : list) {
            String[] split = str.split("\\s");
            if(split.length != 2)
                throw new InvalidConfigurationException("Invalid string \"" + str + "\" at \"PvE.Damage_Multipliers.HP.Melee\" in config.yml");
            else if(!NumberUtils.isInt(split[0]))
                throw new InvalidConfigurationException("Invalid Integer \"" + split[0] + "\" at \"PvE.Damage_Multipliers.HP.Melee\" in config.yml");
            else if(Material.getMaterial(Integer.parseInt(split[0])).equals(null))
                throw new InvalidConfigurationException("Invalid Item ID \"" + split[0] + "\" at \"PvE.Damage_Multipliers.HP.Melee\" in config.yml");
            else if(!NumberUtils.isFloat(split[1]))
                throw new InvalidConfigurationException("Invalid Float \"" + split[1] + "\" at \"PvE.Damage_Multipliers.HP.Melee\" in config.yml");
            
            addToMeleeList(Material.getMaterial(Integer.parseInt(split[0])), Float.parseFloat(split[1]));
        }
    }
    
    
    public static HashMap<Material, Float> getMeleeList() {
        return meleeMultipliers;
    }
    
    public static float getDamageMultiplier(Material m) {
        if(meleeMultipliers.containsKey(m)) return meleeMultipliers.get(m);
        else return 1.0f;
    }
    
    public static void addToMeleeList(Material m, float f) {
        meleeMultipliers.put(m, f);
    }
    
    public static void removeFromMeleeList(Material m) {
        meleeMultipliers.remove(m);
    }
    
    
}
